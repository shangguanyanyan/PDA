package com.jxjr.v.fragment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.content.Intent;
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
import com.jxjr.m.entity.JrPdaCprkbillentry;
import com.jxjr.utility.Constance;
import com.jxjr.utility.PbF;
import com.jxjr.v.activity.CprkActivity;
import com.jxjr.v.adadper.SjBizAdapter;

public class CprkSummaryFrg extends Fragment implements OnItemClickListener{
	
	Boolean c_releaseMode=false;
	
	List<String[]> listDisplayList= new ArrayList<String[]>();	
	SjBizAdapter listAdapter;
	CprkActivity mActivity;
	
	List<JrPdaCprkbillentry> theEntry=new ArrayList<JrPdaCprkbillentry>();
	
	@BindView(R.id.summary_listview)
	ListView summarylist;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_cprk_summary,
				container, false);
		ButterKnife.bind(this, view);
		return view;
		
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
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
		this.mActivity = (CprkActivity)getActivity();
		int[] listViewTvIds = new int[] { 
				R.id.summaryitem_0,R.id.summaryitem_A0, 
				R.id.summaryitem_1,R.id.summaryitem_A1, 
				R.id.summaryitem_2,R.id.summaryitem_A2, 
				R.id.summaryitem_3,R.id.summaryitem_A3, 
				R.id.summaryitem_4,R.id.summaryitem_A4,
				R.id.summaryitem_5,R.id.summaryitem_A5,
				R.id.summaryitem_6,R.id.summaryitem_A6
		};
		listAdapter = new SjBizAdapter(mActivity,R.layout.summaryitem_cprk,				
				listDisplayList,listViewTvIds);
		listAdapter.setListRowColored(true);		//设置相邻列不同颜色
		listAdapter.setEntryitem_SurfaceId(R.id.summaryitem_Surface);
		summarylist.setAdapter(listAdapter);
		summarylist.setOnItemClickListener(this);
	}
	protected CprkActivity iGetActivity(){
		// TODO Auto-generated method stub
		return (CprkActivity) getActivity();
	}	
	void initData(){
//		if(!c_releaseMode){
//			theEntry=new ArrayList<JrSaleIssueBillEntry>();
//			JrSaleIssueBillEntry temp1=new JrSaleIssueBillEntry();
//			theEntry
//		}		
	}
	void data2Wight(){
		if(mActivity.getEntity()!=null){
			theEntry = mActivity.getEntity().getEntry();
		}
		if(theEntry==null){
			return;
		}
		
		summaryList2displayList(theEntry);
	}


	private void summaryList2displayList(List<JrPdaCprkbillentry> theEntry){
		
		listDisplayList.clear();	//显示数据
		Map<String,Integer> groupMap=new HashMap<String,Integer>();
		for(int i=0;i<theEntry.size();i++){
			JrPdaCprkbillentry tmp=theEntry.get(i);
			String matid=tmp.getFmatId();
			String lot=PbF.inzStr(tmp.getFbatchNo());
			String stockid=PbF.inzStr(tmp.getFstockId());
			String spid=PbF.inzStr(tmp.getFspId());
			StringBuilder hzyj = new StringBuilder(matid);  
			hzyj.append(lot).append(stockid).append(spid);
			Double realqty=0.0;
			if(tmp.getFqty()!=null){
				realqty=Double.parseDouble(tmp.getFqty().toString());
			}	
			String tmp0="";
			String tmp1="";
			String tmp2="";
			String tmp3="";
			String tmp4="0";
			String tmp5="";
			String tmp6="";
			if(groupMap.containsKey(hzyj.toString())){
				int k=groupMap.get(hzyj.toString());				
				String[] addupArr=listDisplayList.get(k);
				Double adduprealqty=Double.parseDouble(addupArr[4])+realqty;				
				tmp0=addupArr[0];
				tmp1=addupArr[1];
				tmp2=addupArr[2];
				tmp3=addupArr[3];
				tmp4=PbF.number2Str(adduprealqty,"0");
				tmp5=addupArr[5];
				tmp6=addupArr[6];
				//tmp5=addupArr[5];
				String tmpArr[]=new String[]{tmp0,tmp1,tmp2,tmp3,tmp4,tmp5,tmp6};
				listDisplayList.set(k, tmpArr);
			}else{
				int x =groupMap.size();
				groupMap.put(hzyj.toString(), x);
				tmp0=PbF.inzStr(tmp.getFmatNumber());
				tmp1=PbF.inzStr(tmp.getFmatName());
				tmp2=PbF.inzStr(tmp.getFmatModel());
				tmp3=PbF.inzStr(tmp.getFbatchNo());
				tmp4=PbF.number2Str(realqty,"0");
				tmp5=PbF.inzStr(tmp.getFstockName());
				tmp6=PbF.inzStr(tmp.getFspName());
				String tmpArr[]=new String[]{tmp0,tmp1,tmp2,tmp3,tmp4,tmp5,tmp6};
				listDisplayList.add(tmpArr);	
			}
		
		}
	}
	
	public void refreshFragment(){
		data2Wight();
		listAdapter.setList(listDisplayList);
		listAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		// TODO Auto-generated method stub
		
//		mActivity=iGetActivity();
//		String[] tmp=listDisplayList.get(position);
//		String para=tmp[0];
//		Intent intent=new Intent();
//		//intent.setClass(mActivity, SearchMaterialInvInfoActivity.class);				
//		intent.putExtra("condition",para);
//		intent.putExtra("requestCode", Constance.requestCode_sundryMatInv);
//		mActivity.startActivityForResult(intent, Constance.requestCode_sundryMatInv);
	}

}
