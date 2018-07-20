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

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.copyofhy.R;
import com.jxjr.m.entity.JrPdaBcprkbillentry;
import com.jxjr.utility.Constance;
import com.jxjr.utility.PbF;
import com.jxjr.v.activity.BcprkActivity;
import com.jxjr.v.adadper.SjBizAdapter;

public class BcprkSummaryFrg extends Fragment implements OnItemClickListener{
	
	Boolean c_releaseMode=false;
	
	List<String[]> listDisplayList= new ArrayList<String[]>();	
	SjBizAdapter listAdapter;
	BcprkActivity mActivity;
	
	List<JrPdaBcprkbillentry> theEntry=new ArrayList<JrPdaBcprkbillentry>();
	
	@BindView(R.id.summary_listview)
	ListView summarylist;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_bcprk_summary,
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
		this.mActivity = (BcprkActivity)getActivity();
		int[] listViewTvIds = new int[] { 
				R.id.summaryitem_0,R.id.summaryitem_A0, 
				R.id.summaryitem_1,R.id.summaryitem_A1, 
				R.id.summaryitem_2,R.id.summaryitem_A2, 
				R.id.summaryitem_3,R.id.summaryitem_A3, 
				R.id.summaryitem_4,R.id.summaryitem_A4,
				R.id.summaryitem_5,R.id.summaryitem_A5,
				R.id.summaryitem_6,R.id.summaryitem_A6,
				R.id.summaryitem_7,R.id.summaryitem_A7,
				R.id.summaryitem_8,R.id.summaryitem_A8
		};
		listAdapter = new SjBizAdapter(mActivity,R.layout.summaryitem_bcprk,				
				listDisplayList,listViewTvIds);
		listAdapter.setListRowColored(true);		//设置相邻列不同颜色
		listAdapter.setEntryitem_SurfaceId(R.id.summaryitem_Surface);
		summarylist.setAdapter(listAdapter);
		summarylist.setOnItemClickListener(this);
	}
	protected BcprkActivity iGetActivity(){
		// TODO Auto-generated method stub
		return (BcprkActivity) getActivity();
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


	private void summaryList2displayList(List<JrPdaBcprkbillentry> theEntry){
		
		listDisplayList.clear();	//显示数据
		Map<String,Integer> groupMap=new HashMap<String,Integer>();
		for(int i=0;i<theEntry.size();i++){
			JrPdaBcprkbillentry tmp=theEntry.get(i);
			String scddno=PbF.inzStr(tmp.getFscddNo());
			String scddseq=PbF.inzStr(tmp.getFscddSeq());
			String matid=PbF.inzStr(tmp.getFmatId());
			String lot=PbF.inzStr(tmp.getFbatchNo());
			String stockid=PbF.inzStr(tmp.getFstockId());
			String spid=PbF.inzStr(tmp.getFspId());
			StringBuilder hzyj = new StringBuilder(scddno);  
			hzyj.append(scddseq).append(matid).append(lot).append(stockid).append(spid);
			Double realqty=0.0;
			if(tmp.getFqty()!=null){
				realqty=Double.parseDouble(tmp.getFqty().toString());
			}	
			String tmp0="";
			String tmp1="";
			String tmp2="";
			String tmp3="";
			String tmp4="";
			String tmp5="";
			String tmp6="0";
			String tmp7="";
			String tmp8="";
			if(groupMap.containsKey(hzyj.toString())){
				int k=groupMap.get(hzyj.toString());				
				String[] addupArr=listDisplayList.get(k);
				Double adduprealqty=Double.parseDouble(addupArr[6])+realqty;
				tmp0=addupArr[0];
				tmp1=addupArr[1];
				tmp2=addupArr[2];
				tmp3=addupArr[3];
				tmp4=addupArr[4];
				tmp5=addupArr[5];
				tmp6=PbF.number2Str(adduprealqty,"0");
				tmp7=addupArr[7];
				tmp8=addupArr[8];
				//tmp5=addupArr[5];
				String tmpArr[]=new String[]{tmp0,tmp1,tmp2,tmp3,tmp4,tmp5,tmp6,tmp7,tmp8};
				listDisplayList.set(k, tmpArr);
			}else{
				int x =groupMap.size();
				groupMap.put(hzyj.toString(), x);
				tmp0=PbF.inzStr(tmp.getFscddNo());
				tmp1=PbF.inzStr(tmp.getFscddSeq());
				tmp2=PbF.inzStr(tmp.getFmatNumber());
				tmp3=PbF.inzStr(tmp.getFmatName());
				tmp4=PbF.inzStr(tmp.getFmatModel());
				tmp5=PbF.inzStr(tmp.getFbatchNo());
				tmp6=PbF.number2Str(realqty,"0");
				tmp7=PbF.inzStr(tmp.getFstockName());
				tmp8=PbF.inzStr(tmp.getFspName());
				String tmpArr[]=new String[]{tmp0,tmp1,tmp2,tmp3,tmp4,tmp5,tmp6,tmp7,tmp8};
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
