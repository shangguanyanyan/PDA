package com.jxjr.v.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnEditorAction;


import com.example.copyofhy.R;
import com.jxjr.codescan.MipcaActivityCapture;
import com.jxjr.contract.ABaseBillContract;
import com.jxjr.p.ABasePresenter;
import com.jxjr.utility.AZUIPub;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;
import com.jxjr.v.fragment.ABaseBillEntryFrg;
import com.jxjr.v.view.MEditText;

public abstract class ABaseFTBillActivity<E,P extends ABasePresenter<?, ?>> extends FragmentActivity implements 
	ABaseBillContract.IfBasebillActivityView
	,ABaseBillEntryFrg.OnChangeScanIndexListener{

	Intent originIntent;		//最原始的Intent
	
	protected E entity;
	protected P presenter;
	
	protected  FragmentPagerAdapter mAdapter;  
	protected  List<Fragment> mFragments = new ArrayList<Fragment>();
	
	@BindView(R.id.billdocument_title)
	protected TextView billdocument_title;
	
	@BindView(R.id.billdocument_scanview)
	protected MEditText mscanView;
	@BindView(R.id.scanbtn)
	protected Button scanbtn;
	@BindView(R.id.isoffline)
	protected CheckBox isoffline;
	@BindView(R.id.billdocument_viewer_hd)
	protected Button viewer_hd;
	
	@BindView(R.id.billdocument_viewer_summary)
	protected Button viewer_summary;
	
	@BindView(R.id.billdocument_viewer_entList)
	protected Button viewer_entList;
	
	@BindView(R.id.billdocument_viewer_scan)
	protected Button viewer_scan;
	
	@BindView(R.id.billdocument_viewer_cache)
	protected Button viewer_cache;
	@BindView(R.id.billdocument_viewpager)
	protected ViewPager mViewPager;
	
	protected ProgressDialog mDownloadDialog;

	protected AlertDialog.Builder alertDialog;
	
	protected AlertDialog.Builder existClicker;
	
	/**
	 * 历史版本=-3,变更中=-2,=-1,新增=0,保存=1,提交=2,
	 * 作废=3,审核=4,下达=5,冻结=6,关闭=7,
	 * 完工=8,完成=90,发布=10,结案=11
	 */
	protected int billstatus=0;
	protected int id=0;

	protected String cacheDecode;
	protected boolean offline = true;
	
    @Override  
    protected void onCreate(Bundle savedInstanceState) { 
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_frame_billdocument_f);
    	ButterKnife.bind(this);
    	
    	//mscanView.requestFocus();
    	mscanView.setOnTouchListener(AZUIPub.noInputToucher);    
    	mDownloadDialog=new ProgressDialog(this);
    	mDownloadDialog.setCanceledOnTouchOutside(false);
    	existClicker=new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_DARK)
				.setTitle(Constance.alert_提示)
				.setMessage(Constance.alert_ActivityExit)
				.setPositiveButton("确定", new DialogInterface.OnClickListener(){
		
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						ABaseFTBillActivity.this.finish();
					}
					
				})
				.setNegativeButton("取消",null);
    	Intent intent=getIntent();
    	id=intent.getIntExtra("id", 0);
    	billstatus=intent.getIntExtra("billstatus", 0);
//    	billdocument_title.setFocusable(true);
//    	billdocument_title.setFocusableInTouchMode(true);
    	scanbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ABaseFTBillActivity.this,MipcaActivityCapture.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivityForResult(intent, 1);
				
			}
			
		});
    }
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		alertDialog=new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_DARK)
		.setPositiveButton("确定", null)
		.setTitle("警告");
	}
	
	@Override  
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		switch(requestCode) {
		case 1:
			if(resultCode == RESULT_OK) {
				Bundle bundle = intent.getExtras();
				cacheDecode = bundle.getString("result");
				mscanView.setText(cacheDecode);
				 Distribution(cacheDecode);
			}
			break;
		}
		
	}
	
	protected void intiPageViewer(){
		addFragments();
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()){
			
			@Override
			public Fragment getItem(int position) {
				// TODO Auto-generated method stub
				return mFragments.get(position);
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mFragments.size();
			}
			
		};	
		mViewPager.setOffscreenPageLimit(5);	//设置Page数量限制，否则空指针异常
		mViewPager.setAdapter(mAdapter);
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener(){

			private int currentIndex=0;
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				resetTabBtn();
				final int upperlimit=5;
				final int lowerlimit=-1;
				
				switch (position){
				case lowerlimit:
					viewer_scan.setEnabled(false);
					break;
				case 0:
					viewer_hd.setEnabled(false);
					break;
				case 1:
					viewer_summary.setEnabled(false);
					break;
				case 2:
					viewer_entList.setEnabled(false);
					break;
				case 3:
					viewer_scan.setEnabled(false);
					break;
				case 4:
					viewer_cache.setEnabled(false);
				case upperlimit:
					viewer_hd.setEnabled(false);
					break;
				}
				currentIndex = position;
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}			
			
		});
		viewer_hd.setEnabled(false);
	}
		
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		presenter.onDestroy();
	}

	protected abstract void addFragments();
	
	protected abstract void intiData();
	
	//click 事件区域  开始
	@OnClick(R.id.billdocument_viewer_hd)
	public void onClick_viewer_hd(){
		mViewPager.setCurrentItem(0);
//		InputMethodManager imm =(InputMethodManager)this.getSystemService(this.INPUT_METHOD_SERVICE);  
//		imm.hideSoftInputFromWindow(billdocument_title.getWindowToken(),0); 
	}
	
	@OnClick(R.id.billdocument_viewer_summary)
	public void onClick_viewer_summary(){
		mViewPager.setCurrentItem(1);
//		InputMethodManager imm =(InputMethodManager)this.getSystemService(this.INPUT_METHOD_SERVICE);  
//		imm.hideSoftInputFromWindow(billdocument_title.getWindowToken(),0); 
	}
	
	@OnClick(R.id.billdocument_viewer_entList)
	public void onClick_viewer_entList(){
		mViewPager.setCurrentItem(2);
//		InputMethodManager imm =(InputMethodManager)this.getSystemService(this.INPUT_METHOD_SERVICE);  
//		imm.hideSoftInputFromWindow(billdocument_title.getWindowToken(),0); 
	}
	
	@OnClick(R.id.billdocument_viewer_scan)
	public void onClick_viewer_scan(){
		viewer_scan_Service();
//		InputMethodManager imm =(InputMethodManager)this.getSystemService(this.INPUT_METHOD_SERVICE);  
//		imm.hideSoftInputFromWindow(billdocument_title.getWindowToken(),0); 
	}	
	@OnClick(R.id.billdocument_viewer_cache)
	public void onClick_viewer_cache(){
		mViewPager.setCurrentItem(4);
	}	
	@OnCheckedChanged(R.id.isoffline)
	public void onChecked(boolean isChecked) {
		if(isChecked) {
			offline = true;
		}else {
			offline = false;
		}
	}
	protected void viewer_scan_Service(){
		mViewPager.setCurrentItem(3);
	}
	//click 事件区域  结束
	
	
	//逻辑区域 开始
	protected void resetTabBtn(){
		viewer_hd.setEnabled(true);
		viewer_summary.setEnabled(true);
		viewer_entList.setEnabled(true);
		viewer_scan.setEnabled(true);
//		InputMethodManager imm =(InputMethodManager)this.getSystemService(this.INPUT_METHOD_SERVICE); 
//		
//		imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS); 
		InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
		if(this.getCurrentFocus()!=null){
			imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
		}
		
	}
	
	/**
	 * @return the presenter
	 */
	public P getPresenter() {
		return presenter;
	}
	
	public E getEntity() {
		return entity;
	}
	
	public void setEntity(E entity) {
		this.entity = entity;
	}
	
	public void changeTabByIndex(int i){
		mViewPager.setCurrentItem(i);
	}	
	
//	public abstract void billSave();//保存
	
	public void billSave(){
		presenter.pushData(entity);
		presenter.onSave();
	}
	
//	public abstract void billSubmit();//提交
	
	public void billSubmit(){
		presenter.pushData(entity);
		presenter.onSubmit();
	}
	
	public void billAudit(){		//审核
		
	}

	public void billExist() {
		existClicker.show();
	}
	
	//逻辑区域 结束


	@Override
	public void submitcallbackSucess(String message) {
		// TODO Auto-generated method stub
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}


	@Override
	public void refreshFragmentData() {
		// TODO Auto-generated method stub
				
	}



	//接口区域 开始
	@Override
	public void showprocess() {
		// TODO Auto-generated method stub
		mDownloadDialog.setMessage("加载中");
		mDownloadDialog.show();
	}

	@Override
	public void showprocess(String message){
		mDownloadDialog.setMessage(message);
		mDownloadDialog.show();
	}
	@Override
	public void hideprocess() {
		// TODO Auto-generated method stub
		mDownloadDialog.dismiss();
	}

	@Override
	public void loadDatafailure() {
		// TODO Auto-generated method stub
		loadDatafailure("加载失败！");
	}

	@Override
	public void loadDataSucess() {
		// TODO Auto-generated method stub
		
	}

	
	/* (non-Javadoc)
	 * @see com.lf.contract.ABaseBillContract.IfBasebillActivityView#loadDatafailure(java.lang.String)
	 */
	@Override
	public void loadDatafailure(String error) {
		// TODO Auto-generated method stub
		AZUIPub.showMessage(this, Constance.show_Dialog, error);
//		Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

	}	


	
	/* (non-Javadoc)
	 * @see com.fb.v.fragment.ABaseBillEntryFrg.OnChangeScanIndexListener#iSetScanIndex(int)
	 */
	@Override
	public void iSetScanIndex(int index) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.fb.v.fragment.ABaseBillEntryFrg.OnChangeScanIndexListener#iSetScanEntrystatus(int)
	 */
	@Override
	public void iSetScanEntrystatus(int entrystatus) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.fb.contract.ABaseBillContract.IfBasebillActivityView#intiBillSucess(java.lang.String)
	 */
	@Override
	public void intiBillSucess(String message) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void intiBillfailure(){
//		AZUIPub.showMessage(this,Constance.show_Dialog,"初始化单据失败！");
//		Toast.makeText(this, "初始化单据失败！", Toast.LENGTH_SHORT).show();
		new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_DARK)
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(GI.c_releaseMode){
					ABaseFTBillActivity.this.finish();
				}
			}
		})
		.setTitle("Message")
		.setMessage("初始化单据失败！")
		.show();
	}

	@Override
	public <T> void iSetData(T entity) {
		// TODO Auto-generated method stub
		this.entity=(E) entity;
	}

	@Override
	public void callbackSucess(String message) {
		// TODO Auto-generated method stub
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	/**
	 * @return the alertDialog
	 */
	public AlertDialog.Builder getAlertDialog() {
		return alertDialog;
	}
	
	@Override
	public void loadSrcSucess(int flg, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadSrcfailure(int flg, String error) {
		// TODO Auto-generated method stub
		AZUIPub.showMessage(this, Constance.show_Dialog, error);
//		Toast.makeText(this, error, Toast.LENGTH_SHORT).show();	
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			existClicker.show();			
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	//接口区域 结束
	/*
	 * 18.6.11
	 * 将扫描框移到父类中
	 */
	@OnEditorAction(R.id.billdocument_scanview)
	public boolean decodeMat1(TextView v,int actionId, KeyEvent event){
		if ("".equals(AZUIPub.text2String(v).trim())) {
			return false;
		}
		 cacheDecode = AZUIPub.text2String(v).trim();
		 Distribution(cacheDecode);
		new Thread(new Runnable() {
			@Override
			public void run() {
				new Handler(Looper.getMainLooper()).post(new Runnable() {
					@Override
					public void run() {
						mscanView.requestFocus();
					}
				});
			}
		}).start();
		return false;
		
	}
	public void Distribution(String catchDecode){
		
	}
	/******结束******/
	
	//基础资料 查询区域  开始
	
	/*
	 * 根据仓库ID查询出仓库附加属性信息
	 * */
	public boolean warehouseid2showLocal(String warehouseid){
		return presenter.warehouseid2showLocal(this, warehouseid);
	}
	//基础资料 查询区域  结束
}

