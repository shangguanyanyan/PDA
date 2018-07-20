package com.jxjr.v.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.copyofhy.R;
import com.jxjr.v.activity.ABaseBillActivity;
import com.jxjr.v.adadper.SjBizAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class ABaseBillEntryFrg<E,A extends ABaseBillActivity> 
				extends Fragment implements OnItemClickListener{
	
	public interface OnChangeScanIndexListener {  
	    void iSetScanIndex(int index); 
	    void iSetYgScanIndex(int index); 
	    void iSetScanEntrystatus(int entrystatus);
	}  
	
	List<String[]> entryDisplayList= new ArrayList<String[]>();	
	SjBizAdapter entryAdapter;
	
	List<E> theEntry=new ArrayList<E>();
	
	int[] entryTvIds;
	
	@BindView(R.id.entry_listview)
	ListView entrylist;
	
	A mActivity;
	
	@Override  
	public void onAttach(Activity activity) { 
		super.onAttach(activity); 
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(setLayoutID(),
				container, false);

		ButterKnife.bind(this, view);
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mActivity=(A)getActivity();
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
		
//		entrylist=(ListView)findViewById(R.id.entry_listview);
		int[] entryTvIds = setEntryTvIds();
		
		entryAdapter = new SjBizAdapter(mActivity,setAdapterLayoutID(),				
				entryDisplayList,entryTvIds);
		entryAdapter.setListRowColored(true);		//设置相邻列不同颜色
		//entryAdapter.setEntryitem_SurfaceId(R.id.entryitem_Surface);
		entrylist.setAdapter(entryAdapter);
		entrylist.setOnItemClickListener(this);
		
	}
	
	protected void initData(){
		
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
		mActivity.iSetScanEntrystatus(1);
		mActivity.iSetScanIndex(position);
	}
	protected abstract int setAdapterLayoutID();
	protected abstract int[] setEntryTvIds();	
	protected abstract int setLayoutID();
	protected abstract void data2Wight();
	protected abstract void entryList2displayList(List<E> theEntry);
}
