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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.BindView;

import com.example.copyofhy.R;
import com.jxjr.m.entity.JrPdaGxhbbillentry;
import com.jxjr.utility.PbF;
import com.jxjr.v.activity.GxhbActivity;
import com.jxjr.v.adadper.SjBizAdapter;

public class GxhbEntryFrg extends Fragment implements OnItemClickListener{

	public interface OnChangeScanIndexListener {  
	    void iSetScanIndex(int index); 
	    void iSetScanEntrystatus(int entrystatus);
	}  
	
	GxhbActivity mActivity;
	
	List<String[]> entryDisplayList= new ArrayList<String[]>();	
	SjBizAdapter entryAdapter;
	
	List<JrPdaGxhbbillentry> theEntry=new ArrayList<JrPdaGxhbbillentry>();
	
	@BindView(R.id.entry_listview)
	ListView entrylist;

	
	
	@Override  
	public void onAttach(Activity activity) { 
		super.onAttach(activity); 
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_gxhb_entry,
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
				R.id.entryitem_10,R.id.entryitem_A10,
				R.id.entryitem_13,R.id.entryitem_A13, 
				R.id.entryitem_0,R.id.entryitem_A0, 
				R.id.entryitem_1,R.id.entryitem_A1, 
				R.id.entryitem_2,R.id.entryitem_A2, 
				
				R.id.entryitem_11,R.id.entryitem_A11, //抽样数
				R.id.entryitem_12,R.id.entryitem_A12,
				R.id.entryitem_3,R.id.entryitem_A3, 
				R.id.entryitem_4,R.id.entryitem_A4,
				R.id.entryitem_5,R.id.entryitem_A5,
				R.id.entryitem_6,R.id.entryitem_A6,
				R.id.entryitem_7,R.id.entryitem_A7,
				R.id.entryitem_8,R.id.entryitem_A8,
				R.id.entryitem_9,R.id.entryitem_A9
		};
		entryAdapter = new SjBizAdapter(mActivity,R.layout.entryitem_gxhb,				
				entryDisplayList,entryTvIds);
		entryAdapter.setListRowColored(true);		//设置相邻列不同颜色
		entryAdapter.setEntryitem_SurfaceId(R.id.entryitem_Surface);
		entrylist.setAdapter(entryAdapter);
		entrylist.setOnItemClickListener(this);
		
	}
	
	void initData(){
		
	}
	
	void data2Wight(){
		GxhbActivity mActivity= iGetActivity();
		if(mActivity.getEntity()!=null){
			theEntry = mActivity.getEntity().getEntrys();
		}
		if(theEntry==null){
			return;
		}
		entryList2displayList(theEntry);
	}
	
	//逻辑区域开始
	
	
	protected GxhbActivity iGetActivity() {
		// TODO Auto-generated method stub
		return (GxhbActivity) getActivity();
	}	

	private void entryList2displayList(List<JrPdaGxhbbillentry> theEntry){
		entryDisplayList.clear();
		for(int i=0;i<theEntry.size();i++){
			String tmp10=PbF.inzStr(theEntry.get(i).getFtm());
			String tmp13 = PbF.inzStr(theEntry.get(i).getFseq());//序号fseq
			String tmp0=PbF.inzStr(theEntry.get(i).getFmatNumber());
			String tmp1=PbF.inzStr(theEntry.get(i).getFmatName());
			String tmp2=PbF.inzStr(theEntry.get(i).getFmatModel());
			String tmp11 = PbF.number2StrFormat(theEntry.get(i).getFcys());//抽样数
			String tmp12 = PbF.number2StrFormat(theEntry.get(i).getFcybhgs());//抽样不合格数
			String tmp3=PbF.inzStr(theEntry.get(i).getFgxName());
			String tmp4=PbF.number2StrFormat(theEntry.get(i).getFhgpqty());
			String tmp5=PbF.number2StrFormat(theEntry.get(i).getFylfgqty());
			String tmp6=PbF.number2StrFormat(theEntry.get(i).getFygfgqty());
			String tmp7=PbF.number2StrFormat(theEntry.get(i).getFylbfqty());
			String tmp8=PbF.number2StrFormat(theEntry.get(i).getFygbfqty());
			String tmp9=PbF.inzStr(theEntry.get(i).getFqxyyName());
			String tmpArr[]=new String[]{tmp10,tmp13,tmp0,tmp1,tmp2,tmp11,tmp12,tmp3,tmp4,tmp5,tmp6,tmp7,tmp8,tmp9};
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
		GxhbActivity mActivity=iGetActivity();
		mActivity.iSetScanEntrystatus(1);
		mActivity.iSetScanIndex(position);
	}
}
