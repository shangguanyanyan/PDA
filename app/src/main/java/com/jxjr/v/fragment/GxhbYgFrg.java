package com.jxjr.v.fragment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import android.content.Intent;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

import com.example.copyofhy.R;
import com.jxjr.m.entity.JrPdaGxhbYgentry;
import com.jxjr.utility.AZUIPub;
import com.jxjr.utility.Constance;
import com.jxjr.utility.PbF;
import com.jxjr.v.activity.GxhbActivity;
import com.jxjr.v.activity.SearchItemActivity;
import com.jxjr.v.activity.YgAlterActivity;
import com.jxjr.v.adadper.SjBizAdapter;
import com.jxjr.v.adadper.YgAdapter;


public class GxhbYgFrg extends Fragment implements OnItemClickListener{

	@BindView(R.id.scan_bz)
	EditText fbz;
	
	@BindView(R.id.staff_bz)
	TextView fbzhd;

	@BindView(R.id.ygentry_listview)
	ListView entrylist;
	
	
	List<JrPdaGxhbYgentry> emp;
	
	GxhbActivity mActivity;
	int entrystatus = 0;
	List<JrPdaGxhbYgentry> theEntry=new ArrayList<JrPdaGxhbYgentry>();
	
	List<String[]> entryDisplayList= new ArrayList<String[]>();	
	YgAdapter entryAdapter;
	
	public int index=0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_gxhb_staff,
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
	
	
	
	
	
	void initWidget(){
		mActivity =iGetActivity();
		int[] entryTvIds = new int[] { 
				R.id.ygitem_0,R.id.ygitem_A0,
				R.id.ygitem_1,R.id.ygitem_A1
		};
		entryAdapter = new YgAdapter(mActivity,R.layout.ygentryitem_gxhb,				
				entryDisplayList,entryTvIds);
//		entryAdapter.setListRowColored(true);		//设置相邻列不同颜色
		entryAdapter.setEntryitem_SurfaceId(R.id.ygitem_Surface);
		entrylist.setAdapter(entryAdapter);
		entrylist.setOnItemClickListener(this);
		
		fbzhd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
		fbzhd.setTextColor(android.graphics.Color.BLUE);//文字颜色
	}
	protected GxhbActivity iGetActivity() {
		// TODO Auto-generated method stub
		return (GxhbActivity) getActivity();
	}	
	
	void initData(){
		emp=iGetActivity().getEntity().getYgEntry();
		if(emp==null){
			iGetActivity().getEntity().setYgEntry(new ArrayList<JrPdaGxhbYgentry>());
			emp=iGetActivity().getEntity().getYgEntry();
		}
	}
	void data2Wight(){
		GxhbActivity mActivity= iGetActivity();
		if(mActivity.getEntity()!=null){
			theEntry = mActivity.getEntity().getYgEntry();
		}
		if(theEntry==null){
			return;
		}
		entryList2displayList(theEntry);
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		if(resultCode==0){
			return;
		}
		switch (requestCode) {
			case Constance.requestCode_baseinfobz:{
				if(resultCode==Constance.requestCode_error){
					String error=intent.getStringExtra("error");
					AZUIPub.showMessage(iGetActivity(), Constance.show_Dialog, error);
					mActivity.getEntity().getBill().setFbzId(null);
					mActivity.getEntity().getBill().setFbzName(null);
					break;
				}
				String _fname=intent.getStringExtra("fname");
				String _fid=intent.getStringExtra("fid");
				try{
					mActivity.getEntity().getBill().setFbzId(_fid);
					mActivity.getEntity().getBill().setFbzName(_fname);
					fbz.setText(PbF.inzStr(_fname));
					
					//选择班组成功后的动作。
					
				}catch(Exception e){					
				}
				break;
			}
			case Constance.requestCode_baseinfoYg:{
				if(resultCode==Constance.requestCode_error){
					String error=intent.getStringExtra("error");
					AZUIPub.showMessage(iGetActivity(), Constance.show_Dialog, error);
					break;
				}
				String _fname=intent.getStringExtra("fname");//testA
				String _fid=intent.getStringExtra("fid");//105320
				String _fnumber=intent.getStringExtra("fnumber");//test001
				String _fparam=intent.getStringExtra("fparam");//1
				BigDecimal ygxs=new BigDecimal(_fparam);   //0
				try{
					JrPdaGxhbYgentry ygentry=new JrPdaGxhbYgentry();
					ygentry.setFygid(_fid);
					ygentry.setFygname(_fname);
					ygentry.setFygnumber(_fnumber);
					ygentry.setFygxs(ygxs);

					int fseq=theEntry.size();
					mActivity.changeYgEntryList(fseq,ygentry);//目前fseq没有用
					refreshFragment();
					String result = "";
					//在这里添加方法调用GxhbHdFrg的fragment
					for(int i = 0;i<theEntry.size();i++){
						String name = theEntry.get(i).getFygname();	
						if(i == theEntry.size()-1){
							result = result + name;
						}else{
							result = result + name+",";
						}
					}
					mActivity.getTab0().fygxx.setText(result);	
					mActivity.getTab0().billhd.setFempxx(result);
				}
				catch(Exception e){		
					System.out.println(e.getMessage());
				}
				break;
			}
			case Constance.requestCode_baseinfoYgAlter:{
				if(resultCode==Constance.requestCode_error){
					String error=intent.getStringExtra("error");
					AZUIPub.showMessage(iGetActivity(), Constance.show_Dialog, error);
					break;
				}
				String _fname=intent.getStringExtra("fname");
				String _fid=intent.getStringExtra("fid");
				String _fnumber=intent.getStringExtra("fnumber");
				String _fparam=intent.getStringExtra("fparam");
				BigDecimal ygxs=new BigDecimal(_fparam);   
				try{
					mActivity.getEntity().getYgEntry().get(index).setFygid(_fid);
					mActivity.getEntity().getYgEntry().get(index).setFygname(_fname);
					mActivity.getEntity().getYgEntry().get(index).setFygnumber(_fnumber);
					mActivity.getEntity().getYgEntry().get(index).setFygxs(ygxs);
					refreshFragment();
					//在这里添加调用GxhbHdFrg的fragment
					String result = "";
					for(int i = 0;i<theEntry.size();i++){
						String name = theEntry.get(i).getFygname();	
						if(i == theEntry.size()-1){
							result = result + name;
						}else{
							result = result + name+",";
						}
					}
					mActivity.getTab0().fygxx.setText(result);		
					/*
					 * 1、在这里对班组信息进行修改
					 * 2、提交的数据就是上面传输过去，带逗号分隔符的字符串
					 */
				}
				catch(Exception e){		
					System.out.println(e.getMessage());
				}
				break;
			}
		}
	}

	private void entryList2displayList(List<JrPdaGxhbYgentry> theEntry){
		entryDisplayList.clear();
		for(int i=0;i<theEntry.size();i++){
			String tmp0=PbF.inzStr(theEntry.get(i).getFygname());
			String tmp1=PbF.number2Str(theEntry.get(i).getFygxs());		
			String tmpArr[]=new String[]{tmp0,tmp1};
			entryDisplayList.add(tmpArr);
		}

	}
	public void refreshFragment(){
		data2Wight();
		entryAdapter.setList(entryDisplayList);
		entryAdapter.notifyDataSetChanged();
	}

	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	//Onclick区域
	//新增
	@OnClick(R.id.yg_btn_new)
	public void toNew(){
		//entrylist.requestFocus();
		Intent intent=new Intent();
		intent.setClass(iGetActivity(), SearchItemActivity.class);
		intent.putExtra("documentType", Constance.baseinfo_yg);
		intent.putExtra("requestCode", Constance.requestCode_baseinfoYg);
		this.startActivityForResult(intent, Constance.requestCode_baseinfoYg);
		
	}
	//修改
	@OnClick(R.id.yg_btn_alter)
	public void scan_alter(){
		String fygid=mActivity.getEntity().getYgEntry().get(index).getFygid();
		String fygno=mActivity.getEntity().getYgEntry().get(index).getFygnumber();
		String fygname=mActivity.getEntity().getYgEntry().get(index).getFygname();
		String fygxs=PbF.decimal2StrFormat(mActivity.getEntity().getYgEntry().get(index).getFygxs());
		Intent intent=new Intent();
		intent.setClass(iGetActivity(), YgAlterActivity.class);
		intent.putExtra("position", index);
		intent.putExtra("documentType", Constance.baseinfo_yg);
		intent.putExtra("requestCode", Constance.requestCode_baseinfoYgAlter);
		intent.putExtra("ygid", fygid);
		intent.putExtra("ygno", fygno);
		intent.putExtra("ygname", fygname);
		intent.putExtra("ygxs", fygxs);
		this.startActivityForResult(intent, Constance.requestCode_baseinfoYgAlter);
		
	}
	//删除
	@OnClick(R.id.yg_btn_delete)
	public void scan_delete(){
		scan_deleteInner();
	}
	public void scan_deleteInner(){
	//	delete_calc(_fqty);		//德欧
		mActivity.deleteYgEntry(index);
		index=0;
		refreshFragment();
		String result = "";
		//在这里添加方法调用GxhbHdFrg的fragment
		for(int i = 0;i<theEntry.size();i++){
			String name = theEntry.get(i).getFygname();	
			if(i == theEntry.size()-1){
				result = result + name;
			}else{
				result = result + name+",";
			}
		}
		mActivity.getTab0().fygxx.setText(result);		
		fbz.requestFocus();
	}
	
	@OnClick(R.id.staff_bz)
	public void bzSelect(){
		Intent intent=new Intent();
		intent.setClass(iGetActivity(), SearchItemActivity.class);
		intent.putExtra("documentType", Constance.baseinfo_bz);
		intent.putExtra("requestCode", Constance.requestCode_baseinfobz);
		this.startActivityForResult(intent, Constance.requestCode_baseinfobz);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		index=position;
		entryAdapter.setSelectedPosition(position);
		entryAdapter.notifyDataSetInvalidated();
	}
	
	//Onclick区域结束


}


