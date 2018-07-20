package com.jxjr.v.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import butterknife.BindView;

import com.example.copyofhy.R;

public abstract class YgEntryActivity extends Activity implements OnClickListener{

	
	@BindView(R.id.ygentry_listview)
	ListView entrylist;
	
    @Override  
    protected void onCreate(Bundle savedInstanceState) { 
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_gxhb_staff);
    	entrylist=(ListView) findViewById(R.id.ygentry_listview);
    	
    	//registerForContextMenu(entrylist);
    	
    	// 添加长按点击弹出选择菜单
    	entrylist.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
    				public void onCreateContextMenu(ContextMenu menu, View v,
    						ContextMenuInfo menuInfo) {
    					menu.setHeaderTitle("选择操作");
    					menu.add(0, 0, 0, "更新该条");
    					menu.add(0, 1, 0, "删除该条");
    				}
    			});

    }
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		

	}
	
	@Override  
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
	}
	
	
	
	protected abstract void intiData();
	
//	public void onCreateContextMenu(ContextMenu menu, View v,
//		    ContextMenuInfo menuInfo) {
//		  super.onCreateContextMenu(menu, v, menuInfo);
//		  menu.add(0, 0, 0, "修改信息");
//		  menu.add(0, 1, 0, "删除记录");
//		}
	
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		//info.id得到listview中选择的条目绑定的id
		String id = String.valueOf(info.id);
		switch (item.getItemId()) {
		case 0:
			  //更新事件的方法
			return true;
		case 1:
			//System.out.println("删除"+info.id);
			  //删除事件的方法
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}
	
}

