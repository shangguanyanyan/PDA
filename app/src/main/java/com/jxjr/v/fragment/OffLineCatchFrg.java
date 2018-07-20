package com.jxjr.v.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.copyofhy.MyApplication;
import com.example.copyofhy.R;
import com.jxjr.m.pojo.BillCodePOJO;
import com.jxjr.m.pojo.BillCodePOJODao;
import com.jxjr.utility.PbF;
import com.jxjr.v.activity.GxhbActivity;
import com.jxjr.v.adadper.SjBizAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

public class OffLineCatchFrg extends Fragment implements OnItemClickListener {

	GxhbActivity mActivity;

	List<String[]> entryDisplayList = new ArrayList<String[]>();
	SjBizAdapter entryAdapter;
	@BindView(R.id.code_catch)
	ListView entrylist;
	@BindView(R.id.resove_all)
	Button resoveBtn;
	List<BillCodePOJO> billpojo;
	private AlertDialog.Builder builder;
	protected AlertDialog dialog;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_offlinecatch, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		/*resoveBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				// 实现批量解析的方法：
				billpojo = mActivity.getDao().loadAll();
				for(BillCodePOJO cont : billpojo) {
					mActivity.getTab3().decodeMatByCode(cont.getBillEntry());
				}
				refreshFragment();
			}
		});*/
		// mActivity=getActivity();
		initWidget();
	}

	private void initWidget() {
		// TODO Auto-generated method stub
		Activity mActivity = getActivity();
		int[] entryTvIds = new int[] { R.id.entryitem_0, R.id.entryitem_A0, R.id.entryitem_1, R.id.entryitem_A1 };
		entryAdapter = new SjBizAdapter(mActivity, R.layout.entryitem_codecatch, entryDisplayList, entryTvIds);
		entryAdapter.setListRowColored(true); // 设置相邻列不同颜色
		entryAdapter.setEntryitem_SurfaceId(R.id.entryitem_Surface);
		entrylist.setAdapter(entryAdapter);
		entrylist.setOnItemClickListener(this);
	}


	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			// 相当于Fragment的onResume
			refreshFragment();
		} else {
			// 相当于Fragment的onPause
		}
	}

	public void refreshFragment() {
		// TODO Auto-generated method stub
		data2Wight();
		entryAdapter.setList(entryDisplayList);
		entryAdapter.notifyDataSetChanged();
	}

	/*
	 * 这里填入所存储的数据 每次滑动都会填入
	 */
	private void data2Wight() {
		// TODO Auto-generated method stub
		mActivity = (GxhbActivity) getActivity();
		if (mActivity.getDao() != null) {
			billpojo = mActivity.getDao().loadAll();
		}
		entryList2displayList(billpojo);
	}

	private void deleteOnItem(String[] item) {
		// User findUser =
		// userDao.queryBuilder().where(UserDao.Properties.Name.eq("zhangsan")).build().unique();
		List<BillCodePOJO> pojo = mActivity.getDao().queryBuilder()
				.where(BillCodePOJODao.Properties.BillEntry.eq(item[1])).list();
		if (pojo != null) {
			for (BillCodePOJO billCodePOJO : pojo) {
				mActivity.getDao().delete(billCodePOJO);
			}
			
		}
	}

	private void entryList2displayList(List<BillCodePOJO> billpojo) {
		entryDisplayList.clear();
		for (int i = 0; i < billpojo.size(); i++) {
			String tmp0 = PbF.inzStr(billpojo.get(i).getBillHead());
			String tmp1 = PbF.inzStr(billpojo.get(i).getBillEntry());
			String[] tmparray = new String[] { tmp0, tmp1 };
			entryDisplayList.add(tmparray);
		}
	}
    //点击事件
	/*
	 * 解析所有的点击事件
	 */
	@OnClick(R.id.resove_all)
	public void resoveAll() {
		billpojo = mActivity.getDao().loadAll();
		for(BillCodePOJO cont : billpojo) {
			mActivity.getTab3().decodeMatByCode(cont.getBillEntry());
		}
		refreshFragment();
	}
	/*
	 * 这里写单独点击条码的自动扫描
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		// 解析的方法
		showSimpleDialog(view, position);

		// refreshFragment();
	}
//点击事件结束
	private void showSimpleDialog(View view, final int position) {
		// TODO Auto-generated method stub
		builder = new AlertDialog.Builder(getActivity(),AlertDialog.THEME_HOLO_DARK)
				.setPositiveButton("确定", null).setTitle("警告");
		builder.setIcon(R.mipmap.ic_launcher);
		builder.setTitle(R.string.dialog_title);
		/*
		 * 设置列表项
		 */
		final String[] Items = { "解析条码", "删除条码" };
		builder.setItems(Items, new OnClickListener() {
		

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				switch (which) {
				case 0:
					// 解析单个条码的方法：
					String[] item = (String[]) entryAdapter.getItem(position);
					mActivity.getTab3().decodeMatByCode(item[1]);
					deleteOnItem(item);
					break;
				case 1:
					String[] item1 = (String[]) entryAdapter.getItem(position);
					deleteOnItem(item1);
					refreshFragment();
					break;
				}
			}
		});
		/*AlertDialog dialog = builder.create();
		dialog.show();*/
		builder.show();
	}

	public void showErrorMsg(String msg) {
		builder = new AlertDialog.Builder(getActivity(),AlertDialog.THEME_HOLO_DARK)
				.setPositiveButton("确定", null).setTitle("错误信息：");
		builder.setMessage(msg);
		builder.show();
		
	}
}
