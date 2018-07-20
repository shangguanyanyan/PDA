package com.jxjr.v.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.copyofhy.R;
import com.jxjr.m.DTO.RightObjectDTO;
import com.jxjr.utility.GI;


public class IndexActivity extends Activity implements OnItemClickListener {
	//AppHelper myApp;
	List<String> rights;
	private String isall="0";
	
	GridView gv;
	TextView serverName;
	
	Intent intent;
	

	String[] iconName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//myApp = (AppHelper) getApplication();
		initWidget();
//		registerReceiver();
	}

	void initWidget() {
		getRightData();
		setContentView(R.layout.activity_index);
		serverName=(TextView)findViewById(R.id.index_ServerName);
		
		gv = ((GridView) findViewById(R.id.index_gridview));
		gv.setAdapter(new MenuData().getSimpleAdapter());
		gv.setOnItemClickListener(this);
		
		serverName.setText(getIntent().getStringExtra("serverName"));
	}
	
//	void registerReceiver() {
//		IntentFilter filter = new IntentFilter();
//		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//		registerReceiver(broadcastReceiver, filter);
//	}

//	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			NetworkInfo ni = intent
//					.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
//			if (ni.getState() == State.CONNECTED
//					&& ni.getType() == ConnectivityManager.TYPE_WIFI) {
//				if (!GI.WIFI_STATUS) {
//					startService(new Intent(GI.MESSAGE));
//				}
//				GI.WIFI_STATUS = true;
//				return;
//			}
//			if (GI.WIFI_STATUS) {
//				stopService(new Intent(GI.MESSAGE));
//			}
//			GI.WIFI_STATUS = false;
//			Toast toast = Toast.makeText(IndexActivity.this, "WIFI尚未连接",
//					Toast.LENGTH_SHORT);
//			toast.setGravity(Gravity.CENTER, 0, 0);
//			toast.show();
//		}
//	};

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id ) {
		// TODO Auto-generated method stub
		intent = new Intent();

		if ("1".equals(isall)) {
			switch (position) {
			case 0: { // 工序汇报
				intent = new Intent();
				intent.setClass(this, GxhbActivity.class);
				break;
			}

			case 1: { // 产品入库
				intent = new Intent();
				intent.setClass(this, CprkActivity.class);
				break;
			}

			case 2: { // 销售出库
				intent = new Intent();
				intent.setClass(this, XsckActivity.class);
				break;
			}

			case 3: { // 生产领料
				intent = new Intent();
				intent.setClass(this, ScllActivity.class);
				break;
			}

			case 4: { // 半成品入库
				intent = new Intent();
				intent.setClass(this, BcprkActivity.class);
				break;
			}

			case 5: { // 注销
				GI.SESSION = null;
				GI.isLOGIN = false;
				Intent intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
				finish();
				return;
			}
			default:
				break;
			}
			// if(position!=5&&rights.indexOf((position-1)+"")<0){
			// Toast.makeText(this, "无此操作权限!", Toast.LENGTH_SHORT).show();
			// return;
			// }
			startActivity(intent);
		} else {
			@SuppressWarnings("unchecked")
			Map<String, Object> item = (Map<String, Object>) arg0
					.getItemAtPosition(position);
			String menuname = (String) item.get("text");
			// Me+此处加了trycatch,用于捕获粗物,是程序不至于终止
			try {
				if (menuname.equals(this.getString(R.string.menu_gxhb))) {
					intent.setClass(this, GxhbActivity.class);
				} else if (menuname.equals(this.getString(R.string.menu_cprk))) {
					intent.setClass(this, CprkActivity.class);
				} else if (menuname.equals(this.getString(R.string.menu_xsck))) {
					intent.setClass(this, XsckActivity.class);
				} else if (menuname.equals(this.getString(R.string.menu_scll))) {
					intent.setClass(this, ScllActivity.class);
				} else if (menuname.equals(this.getString(R.string.menu_bcprk))) {
					intent.setClass(this, BcprkActivity.class);
				} else if (menuname.equals(this.getString(R.string.frame_注销))) {
					GI.SESSION = null;
					GI.isLOGIN = false;
					Intent intent = new Intent(this, LoginActivity.class);
					startActivity(intent);
					finish();
					return;
				} else {
					return;
				}
				if (rights.indexOf(menuname) < 0) {
					Toast.makeText(this, "无此操作权限!", Toast.LENGTH_SHORT).show();
					return;
				}
				startActivity(intent);
			} catch (Exception e) {
				Toast.makeText(this, "数据结构存在异常,请联系管理员", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}
	
	void getRightData() {
		rights = new ArrayList<String>();
		List<RightObjectDTO> rightObjectDTOs = GI.SESSION.getRightObjectDTO();
		for (RightObjectDTO one : rightObjectDTOs) {
			if("1".equals(one.getFisall())){
				isall="1";
				return;
			}else{
				rights.add(one.getFmodeName());
			}		
		}
	}
	
	private class MenuData {
		String[] iconName = { 
				IndexActivity.this.getString(R.string.menu_gxhb),			
				IndexActivity.this.getString(R.string.menu_cprk),
				IndexActivity.this.getString(R.string.menu_xsck),
				IndexActivity.this.getString(R.string.menu_scll),
				IndexActivity.this.getString(R.string.menu_bcprk),
							
				IndexActivity.this.getString(R.string.frame_注销) 
				};

		int[] icon = { 
				R.mipmap.m11,
				R.mipmap.m22,
				R.mipmap.m23,
				R.mipmap.m12,
				R.mipmap.m41,
				
				R.mipmap.m53
//				
//				R.drawable.m31, R.drawable.m32,
//				R.drawable.m33, R.drawable.m41,
//				R.drawable.m42, R.drawable.m43,
//				R.drawable.m51, R.drawable.m52,
//				R.drawable.m53 
				};

		String[] from = { "image", "text" };
		int[] to = { R.id.image, R.id.text };
		List<Map<String, Object>> data_list;

		SimpleAdapter simpleAdapter;

		public SimpleAdapter getSimpleAdapter() {
			simpleAdapter = new SimpleAdapter(IndexActivity.this, data_list,
					R.layout.activity_index_item, from, to);
			return simpleAdapter;
		}

		public MenuData() {
			// TODO Auto-generated constructor stub
			data_list = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < icon.length; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("image", icon[i]);
				map.put("text", iconName[i]);
				data_list.add(map);
			}
		}
	}
}