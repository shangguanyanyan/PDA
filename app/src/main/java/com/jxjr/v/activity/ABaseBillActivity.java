package com.jxjr.v.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.copyofhy.MyApplication;
import com.example.copyofhy.R;
import com.jxjr.codescan.MipcaActivityCapture;
import com.jxjr.contract.ABaseBillContract;
import com.jxjr.m.pojo.BillCodePOJO;
import com.jxjr.m.pojo.BillCodePOJODao;
import com.jxjr.p.ABasePresenter;
import com.jxjr.utility.AZUIPub;
import com.jxjr.utility.Constance;
import com.jxjr.v.fragment.ABaseBillEntryFrg;
import com.jxjr.v.view.MEditText;
import com.karumi.expandableselector.ExpandableItem;
import com.karumi.expandableselector.ExpandableSelector;
import com.karumi.expandableselector.ExpandableSelectorListener;
import com.karumi.expandableselector.OnExpandableItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnEditorAction;



public abstract class ABaseBillActivity<E, P extends ABasePresenter> extends FragmentActivity
		implements ABaseBillContract.IfBasebillActivityView, ABaseBillEntryFrg.OnChangeScanIndexListener {

	Intent originIntent; // 最原始的Intent

	protected E entity;
	protected P presenter;

	protected FragmentPagerAdapter mAdapter;
	protected List<Fragment> mFragments = new ArrayList<Fragment>();

	@BindView(R.id.billdocument_title)
	protected TextView billdocument_title;

	@BindView(R.id.billdocument_scanview)
	protected MEditText mscanView;

	/*
	 * @BindView(R.id.scanbtn) protected Button scanbtn;
	 */

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
	protected Button viewer_catch;

	@BindView(R.id.billdocument_viewpager)
	protected ViewPager mViewPager;

	/*
	 * @BindView(R.id.scanpic) protected ImageButton tittleBtn;
	 */
	private ExpandableSelector iconsExpandableSelector;

	protected PopupWindow popWindow;

	protected ProgressDialog mDownloadDialog;

	protected AlertDialog.Builder alertDialog;

	protected AlertDialog.Builder existClicker;

	/**
	 * 历史版本=-3,变更中=-2,=-1,新增=0,保存=1,提交=2, 作废=3,审核=4,下达=5,冻结=6,关闭=7,
	 * 完工=8,完成=90,发布=10,结案=11
	 */
	protected int billstatus = 0;
	protected int id = 0;
	protected static boolean offline = false;
	protected String cacheDecode = "";

	protected MyApplication myApplication;
	protected BillCodePOJO billPojo;
	protected BillCodePOJODao dao;

	public BillCodePOJO getBillPojo() {
		return billPojo;
	}

	public BillCodePOJODao getDao() {
		return dao;
	}

	public Button getScanViewBtn() {
		return this.viewer_scan;
	}

	private SQLiteDatabase db;

	public String getCacheDecode() {
		return this.cacheDecode;
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frame_billdocument);
		ButterKnife.bind(this);
		// mscanView.requestFocus();
		mscanView.setOnTouchListener(AZUIPub.noInputToucher);
		mDownloadDialog = new ProgressDialog(this);
		mDownloadDialog.setCanceledOnTouchOutside(false);
		existClicker = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_DARK).setTitle(Constance.alert_提示)
				.setMessage(Constance.alert_ActivityExit)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						ABaseBillActivity.this.finish();
					}

				}).setNegativeButton("取消", null);
		originIntent = getIntent();
		id = originIntent.getIntExtra("id", 0);
		billstatus = originIntent.getIntExtra("billstatus", 0);
		// billdocument_title.setFocusable(true);
		// billdocument_title.setFocusableInTouchMode(true);

		alertDialog = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_DARK).setPositiveButton("确定", null)
				.setTitle("警告");

		/*
		 * scanbtn.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method stub
		 * Intent intent = new Intent();
		 * intent.setClass(ABaseBillActivity.this,MipcaActivityCapture.class);
		 * intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		 * startActivityForResult(intent, 1);
		 * 
		 * }
		 * 
		 * });
		 */
		// 创建dao
		// myApplication = new MyApplication();
		dao = MyApplication.getInstances().getDaoSession().getBillCodePOJODao();
		initializeIconsExpandableSelector();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		// 获取摄像头扫描到的结果
		switch (requestCode) {
		case 1:
			if (resultCode == RESULT_OK) {
				Bundle bundle = intent.getExtras();
				cacheDecode = bundle.getString("result");
				Distribution(cacheDecode);
			}
			break;
		}
	}

	/*
	 * 加载界面部分（开始）
	 */
	protected void intiPageViewer() {
		addFragments();
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public Fragment getItem(int position) {
				// TODO Auto-generated method stub
				return mFragments.get(position);
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				// System.out.println("size====="+ mFragments.size());
				return mFragments.size();
			}

		};
		mViewPager.setOffscreenPageLimit(5); // 设置Page数量限制，否则空指针异常
		mViewPager.setAdapter(mAdapter);

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			private int currentIndex = 0;

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				resetTabBtn();
				final int upperlimit = 5;
				final int lowerlimit = -1;

				switch (position) {
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
					viewer_catch.setEnabled(false);
					break;
				case upperlimit:
					viewer_hd.setEnabled(false);
					break;
				}
				currentIndex = position;
				// getCount();
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

	protected abstract void addFragments();

	private void initializeIconsExpandableSelector() {
		iconsExpandableSelector = (ExpandableSelector) findViewById(R.id.es_icons);
		List<ExpandableItem> expandableItems = new ArrayList<ExpandableItem>();
		ExpandableItem item = new ExpandableItem();
		item.setResourceId(R.mipmap.ic_keyboard_arrow_up_black);
		expandableItems.add(item);
		item = new ExpandableItem();
		item.setResourceId(R.mipmap.ic_gamepad_black);
		expandableItems.add(item);
		item = new ExpandableItem();
		item.setResourceId(R.mipmap.ic_device_hub_black);
		expandableItems.add(item);
		iconsExpandableSelector.showExpandableItems(expandableItems);
		iconsExpandableSelector.setOnExpandableItemClickListener(new OnExpandableItemClickListener() {
			@Override
			public void onExpandableItemClickListener(int index, View view) {
				if (index == 0 && iconsExpandableSelector.isExpanded()) {
					iconsExpandableSelector.collapse();
					updateIconsFirstButtonResource(R.mipmap.ic_keyboard_arrow_up_black);
				}
				switch (index) {
				case 1:
					showToast("Gamepad icon button clicked.");
					break;
				case 2:
					showToast("Hub icon button clicked.");
					break;
				default:
				}
			}
		});
		iconsExpandableSelector.setExpandableSelectorListener(new ExpandableSelectorListener() {
			@Override
			public void onCollapse() {

			}

			@Override
			public void onExpand() {
				updateIconsFirstButtonResource(R.mipmap.ic_keyboard_arrow_down_black);
			}

			@Override
			public void onCollapsed() {

			}

			@Override
			public void onExpanded() {

			}
		});
	}

	private void updateIconsFirstButtonResource(int resourceId) {
		ExpandableItem arrowUpExpandableItem = new ExpandableItem();
		arrowUpExpandableItem.setResourceId(resourceId);
		iconsExpandableSelector.updateExpandableItem(0, arrowUpExpandableItem);
	}

	private void showToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	/*
	 * (结束)
	 */
	protected abstract void intiData();

	/*
	 * 设置PopUpWindow窗口中的布局
	 */
	protected void showPopupWindow() {
		View contentView = LayoutInflater.from(ABaseBillActivity.this).inflate(R.layout.popwindow, null);
		popWindow = new PopupWindow(contentView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.popbackground);
		Drawable drawable = new BitmapDrawable(getResources(), bmp);
		popWindow.setBackgroundDrawable(drawable);
		popWindow.setOutsideTouchable(true);
		// popWindow.showAsDropDown(tittleBtn);
		TextView tv1 = (TextView) contentView.findViewById(R.id.pop_scan);

		tv1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int id = v.getId();
				switch (id) {
				case R.id.pop_scan:
					Intent intent = new Intent();
					intent.setClass(ABaseBillActivity.this, MipcaActivityCapture.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivityForResult(intent, 1);
					popWindow.dismiss();
					break;
				}
			}
		});

	}

	// click 事件区域 开始
	@OnClick(R.id.billdocument_viewer_hd)
	public void onClick_viewer_hd() {
		mViewPager.setCurrentItem(0);
		// InputMethodManager imm
		// =(InputMethodManager)this.getSystemService(this.INPUT_METHOD_SERVICE);
		// imm.hideSoftInputFromWindow(billdocument_title.getWindowToken(),0);
	}

	/*
	 * @OnClick(R.id.scanbtn) public void onClickScan() { Intent intent = new
	 * Intent(); intent.setClass(ABaseBillActivity.this,
	 * MipcaActivityCapture.class); intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	 * startActivityForResult(intent, 1); }
	 */

	@OnClick(R.id.billdocument_viewer_summary)
	public void onClick_viewer_summary() {
		mViewPager.setCurrentItem(1);
		// InputMethodManager imm
		// =(InputMethodManager)this.getSystemService(this.INPUT_METHOD_SERVICE);
		// imm.hideSoftInputFromWindow(billdocument_title.getWindowToken(),0);
	}

	@OnClick(R.id.billdocument_viewer_entList)
	public void onClick_viewer_entList() {
		mViewPager.setCurrentItem(2);
		// InputMethodManager imm
		// =(InputMethodManager)this.getSystemService(this.INPUT_METHOD_SERVICE);
		// imm.hideSoftInputFromWindow(billdocument_title.getWindowToken(),0);
	}

	@OnClick(R.id.billdocument_viewer_scan)
	public void onClick_viewer_scan() {
		viewer_scan_Service();
		// InputMethodManager imm
		// =(InputMethodManager)this.getSystemService(this.INPUT_METHOD_SERVICE);
		// imm.hideSoftInputFromWindow(billdocument_title.getWindowToken(),0);
	}

	@OnClick(R.id.billdocument_viewer_cache)
	public void onClick_viewer_cache() {
		mViewPager.setCurrentItem(4);
	}

	protected void viewer_scan_Service() {
		mViewPager.setCurrentItem(3);
	}

	@OnCheckedChanged(R.id.isoffline)
	public void onChecked(boolean isChecked) {
		if (isChecked) {
			offline = true;
		} else {
			offline = false;
		}
	}

	/*
	 * @OnClick(R.id.scanpic) public void onPopUp() { showPopupWindow(); }
	 */
	// click 事件区域 结束

	// 逻辑区域 开始
	protected void resetTabBtn() {
		viewer_hd.setEnabled(true);
		viewer_summary.setEnabled(true);
		viewer_entList.setEnabled(true);
		viewer_scan.setEnabled(true);
		viewer_catch.setEnabled(true);
		// InputMethodManager imm
		// =(InputMethodManager)this.getSystemService(this.INPUT_METHOD_SERVICE);
		//
		// imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
		InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (this.getCurrentFocus() != null) {
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

	public void changeTabByIndex(int i) {
		mViewPager.setCurrentItem(i);
	}

	// public abstract void billSave();//保存

	public void billSave() {
		presenter.pushData(entity);
		presenter.onSave();
	}

	// public abstract void billSubmit();//提交

	public void billSubmit() {
		presenter.pushData(entity);
		presenter.onSubmit();
	}

	public void billAudit() { // 审核

	}

	public void billExist() {
		existClicker.show();
	}

	// 逻辑区域 结束

	@Override
	public void submitcallbackSucess(String message) {
		// TODO Auto-generated method stub
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void refreshFragmentData() {
		// TODO Auto-generated method stub

	}

	// 接口区域 开始
	@Override
	public void showprocess() {
		// TODO Auto-generated method stub
		mDownloadDialog.setMessage("加载中");
		mDownloadDialog.show();
	}

	@Override
	public void showprocess(String message) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lf.contract.ABaseBillContract.IfBasebillActivityView#loadDatafailure(java
	 * .lang.String)
	 */
	@Override
	public void loadDatafailure(String error) {
		// TODO Auto-generated method stub
		AZUIPub.showMessage(this, Constance.show_Dialog, error);
		// Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fb.v.fragment.ABaseBillEntryFrg.OnChangeScanIndexListener#iSetScanIndex(
	 * int)
	 */
	@Override
	public void iSetScanIndex(int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void iSetYgScanIndex(int index) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fb.v.fragment.ABaseBillEntryFrg.OnChangeScanIndexListener#
	 * iSetScanEntrystatus(int)
	 */
	@Override
	public void iSetScanEntrystatus(int entrystatus) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fb.contract.ABaseBillContract.IfBasebillActivityView#intiBillSucess(java.
	 * lang.String)
	 */
	@Override
	public void intiBillSucess(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void intiBillfailure() {
		//// AZUIPub.showMessage(this,Constance.show_Dialog,"初始化单据失败！");
		//// Toast.makeText(this, "初始化单据失败！", Toast.LENGTH_SHORT).show();
		// new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_DARK)
		// .setPositiveButton("确定", new DialogInterface.OnClickListener() {
		//
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// // TODO Auto-generated method stub
		// if(GI.c_releaseMode){
		// ABaseBillActivity.this.finish();
		// }
		// }
		// })
		// .setTitle("Message")
		// .setMessage("初始化单据失败！")
		// .show();
	}

	@Override
	public <T> void iSetData(T entity) {
		// TODO Auto-generated method stub
		this.entity = (E) entity;
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
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			existClicker.show();
		}

		return super.onKeyDown(keyCode, event);
	}

	// 接口区域 结束

	// 基础资料 查询区域 开始
	/*
	 * 根据仓库ID查询出仓库附加属性信息
	 */
	public boolean warehouseid2showLocal(String warehouseid) {
		return presenter.warehouseid2showLocal(this, warehouseid);
	}

	// 基础资料 查询区域 结束
	/*
	 * 18.6.11 将扫描框移到父类中
	 */
	@OnEditorAction(R.id.billdocument_scanview)
	public boolean decodeMat1(TextView v, int actionId, KeyEvent event) {
		if ("".equals(AZUIPub.text2String(v).trim())) {
			return false;
		}
		cacheDecode = AZUIPub.text2String(v).trim();
		Distribution(cacheDecode);
		mscanView.setText("");
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

	public void Distribution(String catchDecode) {

	}
	/****** 结束 ******/
}
