package com.jxjr.v.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;


import com.example.copyofhy.R;
import com.jxjr.m.entity.JrPdaCprkbillentry;
import com.jxjr.utility.PbF;
import com.jxjr.v.activity.CprkActivity;
import com.jxjr.v.adadper.SjBizAdapter;

public class CprkEntryFrg extends Fragment implements OnItemClickListener{

	public interface OnChangeScanIndexListener {  
	    void iSetScanIndex(int index); 
	    void iSetScanEntrystatus(int entrystatus);
	} 
	
	CprkActivity mActivity;
	
	List<String[]> entryDisplayList= new ArrayList<String[]>();	
	SjBizAdapter entryAdapter;
	
	List<JrPdaCprkbillentry> theEntry=new ArrayList<JrPdaCprkbillentry>();
	
	@BindView(R.id.entry_listview)
	ListView entrylist;

	
	
	@Override  
	public void onAttach(Activity activity) { 
		super.onAttach(activity); 
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_cprk_entry,
				container, false);

		ButterKnife.bind(this, view);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mActivity=iGetActivity();
		initWidget();
		initData();
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
		       //相当于Fragment的onResume  
			refreshFragment();
		} else {  
		       //相当于Fragment的onPause  
		}  
	}
	
	void initWidget(){
		Activity mActivity =iGetActivity();
		int[] entryTvIds = new int[] { 
				R.id.entryitem_0,R.id.entryitem_A0, 
				R.id.entryitem_1,R.id.entryitem_A1, 
				R.id.entryitem_2,R.id.entryitem_A2, 
				R.id.entryitem_3,R.id.entryitem_A3, 
				R.id.entryitem_4,R.id.entryitem_A4,
				R.id.entryitem_5,R.id.entryitem_A5,
				R.id.entryitem_6,R.id.entryitem_A6,
				R.id.entryitem_7,R.id.entryitem_A7
		};
		entryAdapter = new SjBizAdapter(mActivity,R.layout.entryitem_cprk,				
				entryDisplayList,entryTvIds);
		entryAdapter.setListRowColored(true);		//设置相邻列不同颜色
		entryAdapter.setEntryitem_SurfaceId(R.id.entryitem_Surface);
		entrylist.setAdapter(entryAdapter);
		entrylist.setOnItemClickListener(this);
		
	}
	
	void initData(){
		
	}
	
	void data2Wight(){
		CprkActivity mActivity= iGetActivity();
		if(mActivity.getEntity()!=null){
			theEntry = mActivity.getEntity().getEntry();
		}
		if(theEntry==null){
			return;
		}
		entryList2displayList(theEntry);
	}
	
	//逻辑区域开始
	
	
	protected CprkActivity iGetActivity() {
		// TODO Auto-generated method stub
		return (CprkActivity) getActivity();
	}	

	private void entryList2displayList(List<JrPdaCprkbillentry> theEntry){
		entryDisplayList.clear();
		for(int i=0;i<theEntry.size();i++){
			String tmp0=PbF.inzStr(theEntry.get(i).getFtm());
			String tmp1=PbF.inzStr(theEntry.get(i).getFmatNumber());
			String tmp2=PbF.inzStr(theEntry.get(i).getFmatName());
			String tmp3=PbF.inzStr(theEntry.get(i).getFmatModel());
			String tmp4=PbF.inzStr(theEntry.get(i).getFbatchNo());
			String tmp5=PbF.number2StrFormat(theEntry.get(i).getFqty());			
			String tmp6=PbF.inzStr(theEntry.get(i).getFstockName());
			String tmp7=PbF.inzStr(theEntry.get(i).getFspName());
			String tmpArr[]=new String[]{tmp0,tmp1,tmp2,tmp3,tmp4,tmp5,tmp6,tmp7};
			entryDisplayList.add(tmpArr);
		}

	}
	public void refreshFragment(){
		data2Wight();
		entryAdapter.setList(entryDisplayList);
		entryAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		CprkActivity mActivity=iGetActivity();
		mActivity.iSetScanEntrystatus(1);
		mActivity.iSetScanIndex(position);
	}

	

}
