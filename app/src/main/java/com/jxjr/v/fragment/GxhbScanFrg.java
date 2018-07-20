package com.jxjr.v.fragment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnEditorAction;

import com.example.copyofhy.R;
import com.jxjr.contract.GxhbContract.IfGxhbScanFrgView;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrGxpgbillDTO;
import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.m.entity.JrPdaGxhbYgentry;
import com.jxjr.m.entity.JrPdaGxpgbillentry;
import com.jxjr.m.entity.JrPdaGxhbbillentry;
import com.jxjr.m.network.Login;
import com.jxjr.utility.AZUIPub;
import com.jxjr.utility.BaseEntryDataChecker;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;
import com.jxjr.utility.PbF;
import com.jxjr.utility.StringUtils;
//import com.jxjr.v.activity.BaseInfoQueryActivity;
import com.jxjr.v.activity.GxhbActivity;
import com.jxjr.v.activity.SearchItemActivity;
//import com.jxjr.v.activity.SearchItemActivity;
//import com.jxjr.v.activity.SearchMatActivity;
import com.jxjr.v.view.MEditText;

public class GxhbScanFrg extends
		ABaseBillScanFrg<JrPdaGxhbbillentry, GxhbActivity> implements
		IfGxhbScanFrgView {
	/**
	 * 历史版本=-3,变更中=-2,=-1,新增=0,保存=1,提交=2, 作废=3,审核=4,下达=5,冻结=6,关闭=7,
	 * 完工=8,完成=90,发布=10,结案=11
	 */

	int entrystatus = 0;

	public int index = 0;

	GxhbActivity mActivity;

	@BindView(R.id.scan_btn_save)
	Button btn_save;

	@BindView(R.id.scan_btn_delete)
	Button btn_delete;

	@BindView(R.id.scan_btn_reset)
	Button btn_reset;

	@BindView(R.id.scan_ScanArea)
	MEditText barcode;

	@BindView(R.id.scan_产品代码)
	MEditText fmatno;
	String mat_ftype;
	String mat_fparam;

	@BindView(R.id.searcher_产品代码)
	TextView searcher_fmatno;

	@BindView(R.id.head_editText_产品名称)
	TextView fmatname;

	@BindView(R.id.head_editText_规格型号)
	TextView fgg;

	@BindView(R.id.head_editText_工序)
	TextView fgx;

	@BindView(R.id.head_editText_合格数量)
	EditText fhgpqty;
	BigDecimal oldfqty = new BigDecimal(0); // 用于修改的时候对比原来的数据

	@BindView(R.id.head_editText_料返数量)
	EditText fylfgqty;

	@BindView(R.id.head_editText_工返数量)
	EditText fygfgqty;

	@BindView(R.id.head_editText_料废数量)
	EditText fylbfqty;

	@BindView(R.id.head_editText_工废数量)
	EditText fygbfqty;

	@BindView(R.id.headText_缺陷原因)
	TextView fqxyyhd;

	@BindView(R.id.head_editText_缺陷原因)
	EditText fqxyy;

	@BindView(R.id.head_checkBox_是否统计工时)
	CheckBox fistjgs;

	@BindView(R.id.head_editText_工时)
	EditText fgs;

	/*@BindView(R.id.head_editText_一次返工数量)
	EditText ffgqty1;

	@BindView(R.id.head_editText_二次返工数量)
	EditText ffgqty2;

	@BindView(R.id.head_editText_三次返工数量)
	EditText ffgqty3;

	@BindView(R.id.head_editText_四次返工数量)
	EditText ffgqty4;*/
	@BindView(R.id.head_editText_抽样数)
	EditText fcys;
	
	@BindView(R.id.head_editText_抽样不合格数)
	EditText fcybhgs;
	
	AlertDialog.Builder alertDialog;

	DataChecker checker;

	HashMap<String, String> lastParaMap;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	protected int setLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_gxhb_scan;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mActivity = iGetActivity();
		initWidget();
		intiBaseFrg();
		lastParaMap = new HashMap<String, String>();
		intiEntry();
		checker = new DataChecker(mActivity);
		alertDialog = new AlertDialog.Builder(iGetActivity(),
				AlertDialog.THEME_HOLO_DARK).setPositiveButton("确定", null)
				.setTitle("警告");
		//iGetFirstFocus();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		if (resultCode == 0) {
			return;
		}
		switch (requestCode) {
		case Constance.requestCode_baseinfogx: {
			if (resultCode == Constance.requestCode_error) {
				String error = intent.getStringExtra("error");
				AZUIPub.showMessage(iGetActivity(), Constance.show_Dialog,
						error);
				theEntry.setFgxId(null);
				theEntry.setFgxName(null);
				fgx.setText("");
				fgx.requestFocus();
				break;
			}
			String _fname = intent.getStringExtra("fname");
			String _fid = intent.getStringExtra("fid");
			try {
				theEntry.setFgxId(_fid);
				theEntry.setFgxName(_fname);

				fgx.setText(_fname);
			} catch (Exception e) {
			}
			break;
		}
		case Constance.requestCode_baseinfoqxyy: {
			if (resultCode == Constance.requestCode_error) {
				String error = intent.getStringExtra("error");
				AZUIPub.showMessage(iGetActivity(), Constance.show_Dialog,
						error);
				theEntry.setFqxyyId(null);
				theEntry.setFqxyyName(null);
				fqxyy.setText("");
				fqxyy.requestFocus();
				break;
			}
			String _fname = intent.getStringExtra("fname");
			String _fid = intent.getStringExtra("fid");
			try {
				theEntry.setFqxyyId(_fid);
				theEntry.setFqxyyName(_fname);

				fqxyy.setText(PbF.inzStr(_fname));
			} catch (Exception e) {
			}
			break;
		}
		}
	}

	@Override
	//工序派工单->工序汇报
	public void popDecodeBarcode(boolean sucflg, String msg,
			JrGxpgbillDTO itemDTO) {
		// TODO Auto-generated method stub
		if (sucflg) {
			if (itemDTO.getEntrys() != null && itemDTO.getEntrys().size() > 0) {
				if (mActivity.getEntity().getEntrys().size() == 0) {
					Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
					//将表头信息存入
					mActivity.getEntity().getBill().setFdate(curDate);
					mActivity.getEntity().getBill()
							.setFbzId(itemDTO.getBill().getFbzId());						
					mActivity.getEntity().getBill()
							.setFbzName(itemDTO.getBill().getFbzName());
					mActivity.getEntity().getBill()
							.setFbzNumber(itemDTO.getBill().getFbzNumber());
					mActivity.getEntity().getBill()
							.setFdeptId(itemDTO.getBill().getFdeptId());
					mActivity.getEntity().getBill()
							.setFdeptName(itemDTO.getBill().getFdeptName());
					mActivity.getEntity().getBill()
							.setFdeptNumber(itemDTO.getBill().getFdeptNumber());
					mActivity.getEntity().getBill()
							.setFempxx(itemDTO.getBill().getFempxx());
					mActivity
							.getEntity()
							.getBill()
							.setFcreateuserid(
									PbF.int2Str(GI.SESSION.getfUserID()));
					mActivity.getEntity().getBill()
							.setFcreateusername(GI.SESSION.getfUserName());
					
					// mActivity.getEntity().getBill().setFcreatedate(itemDTO.getBill().getFdate());

					mActivity.getTab0().refrshdata();

					mActivity.getTab1().fbz.setText(PbF.inzStr(itemDTO
							.getBill().getFbzName()));
				}
				//填入表体信息
				theEntry.setFtm(itemDTO.getEntrys().get(0).getFtm());
				theEntry.setFid(itemDTO.getEntrys().get(0).getFid());
				theEntry.setFentryid(itemDTO.getEntrys().get(0).getFentryid());
				theEntry.setFseq(itemDTO.getEntrys().get(0).getFseq());//序号
				theEntry.setFgxId(itemDTO.getEntrys().get(0).getFgxId());
				theEntry.setFgxName(itemDTO.getEntrys().get(0).getFgxName());
				theEntry.setFgxNumber(itemDTO.getEntrys().get(0).getFgxNumber());
				theEntry.setFmatId(itemDTO.getEntrys().get(0).getFmatId());
				theEntry.setFmatName(itemDTO.getEntrys().get(0).getFmatName());
				theEntry.setFmatNumber(itemDTO.getEntrys().get(0)
						.getFmatNumber());
				theEntry.setFmatModel(itemDTO.getEntrys().get(0).getFmatModel());
				theEntry.setFhgpqty(itemDTO.getEntrys().get(0).getFqty());
				theEntry.setFgxpgdNo(itemDTO.getBill().getFbillNo());
				theEntry.setFgxpgdId(itemDTO.getEntrys().get(0).getFid());
				theEntry.setFgxpgdentryId(itemDTO.getEntrys().get(0)
						.getFentryid());
				theEntry.setFscddId(itemDTO.getEntrys().get(0).getFscddId());
				theEntry.setFscddentryId(itemDTO.getEntrys().get(0)
						.getFscddentryId());
				theEntry.setFscddNo(itemDTO.getEntrys().get(0).getFscddNo());
				theEntry.setFscddSeq(itemDTO.getEntrys().get(0).getFscddSeq());
				theEntry.setFisjj(itemDTO.getEntrys().get(0).getFisjj());
				theEntry.setFjjcjId(itemDTO.getEntrys().get(0).getFjjcjId());
				theEntry.setFjjcjName(itemDTO.getEntrys().get(0).getFjjcjName());
				theEntry.setFjjcjNumber(itemDTO.getEntrys().get(0)
						.getFjjcjNumber());
				theEntry.setFisrk(itemDTO.getEntrys().get(0).getFisrk());
				theEntry.setFrkckId(itemDTO.getEntrys().get(0).getFrkckId());
				theEntry.setFrkckName(itemDTO.getEntrys().get(0).getFrkckName());
				theEntry.setFrkckNumber(itemDTO.getEntrys().get(0)
						.getFrkckNumber());
				theEntry.setFistjgs(itemDTO.getEntrys().get(0).getFistjgs());
				theEntry.setFsupId(itemDTO.getEntrys().get(0).getFsupId());
				theEntry.setFsupName(itemDTO.getEntrys().get(0).getFsupName());
				theEntry.setFsupNumber(itemDTO.getEntrys().get(0)
						.getFsupNumber());
				theEntry.setFnote(itemDTO.getEntrys().get(0).getFnote());

				fmatno.setText(PbF.inzStr(itemDTO.getEntrys().get(0)
						.getFmatNumber()));
				fmatname.setText(PbF.inzStr(itemDTO.getEntrys().get(0)
						.getFmatName()));
				fgg.setText(PbF.inzStr(itemDTO.getEntrys().get(0)
						.getFmatModel()));
				fgx.setText(PbF.inzStr(itemDTO.getEntrys().get(0).getFgxName()));
				fhgpqty.setText(PbF.number2StrFormat(itemDTO.getEntrys().get(0)
						.getFqty()));
				fistjgs.setChecked("1".equals(itemDTO.getEntrys().get(0)
						.getFistjgs()));

			}
			if (mActivity.getEntity().getEntrys().size() == 0) {
				if (itemDTO.getYgs() != null && itemDTO.getYgs().size() > 0) {
					mActivity.getEntity().getYgEntry().clear();
					for (int i = 0; i < itemDTO.getYgs().size(); i++) {
						JrPdaGxhbYgentry gxhbygentry = new JrPdaGxhbYgentry();
						gxhbygentry
								.setFygid(itemDTO.getYgs().get(i).getFygId());
						gxhbygentry.setFygname(itemDTO.getYgs().get(i)
								.getFygName());
						gxhbygentry.setFygnumber(itemDTO.getYgs().get(i)
								.getFygNumber());
						gxhbygentry
								.setFygxs(itemDTO.getYgs().get(i).getFygxs());
						mActivity.getEntity().getYgEntry().add(gxhbygentry);

					}
					mActivity.getTab1().refreshFragment();
				}
			}
			mActivity.getScanViewBtn().callOnClick();
		} else {
			mActivity.getTab4().showErrorMsg(msg);
			
		}

	}

	@Override
	public void popDecodeQxcode(boolean sucflg, String msg,
			BaseInfoObjectDTO itemDTO) {
		// TODO Auto-generated method stub
		if (sucflg) {
			if (itemDTO != null) {
				theEntry.setFqxyyId(itemDTO.getfItemID());
				theEntry.setFqxyyName(itemDTO.getfName());
				theEntry.setFqxyyNumber(itemDTO.getfNumber());
				fqxyy.setText(itemDTO.getfName());
			} else {
				alertDialog.setMessage(msg);
				alertDialog.show();
				return;
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fb.v.fragment.ABaseBillScanFrg#changeFwarehouseName()
	 */
	@Override
	protected void changeFgxName(String _fname, boolean success) {
		// TODO Auto-generated method stub
		if (success) {
			fgx.setText(_fname);
			// AZUIPub.activityHiddenInput(mActivity);
			// fstockName.clearFocus();
		} else {
			fgx.setText("");
			fgx.requestFocus();
		}
	}

	@Override
	protected void changeFqxyy(String _fname, boolean success) {
		// TODO Auto-generated method stub
		super.changeFqxyy(_fname, success);
		if (success) {
			fqxyy.setText(_fname);
			// AZUIPub.viewHiddenInput(mActivity,fsp);
			// fsp.clearFocus();
		} else {
			fqxyy.setText("");
			fqxyy.requestFocus();
		}
	}

	// void showLocation(Boolean isShow){
	// if(isShow){
	// fsp.setEnabled(isShow);
	// fsp.setFocusable(isShow);
	// fsp.setFocusableInTouchMode(isShow);
	// ((TextView)getView().findViewById(R.id.searcher_spName)).setEnabled(isShow);
	// }else{
	// theEntry.setFlocationid("");
	// theEntry.setFlocationname("");
	// fsp.setEnabled(isShow);
	// fsp.setFocusable(isShow);
	// fsp.setFocusableInTouchMode(isShow);
	// fsp.setText("");
	// ((TextView)getView().findViewById(R.id.searcher_spName)).setEnabled(isShow);
	// }
	// }

	// /* (non-Javadoc)
	// * @see com.fb.v.fragment.ABaseBillScanFrg#changeFwarehouseName()
	// */
	// @Override
	// protected void changeFwarehouseName(String _fname,boolean success){
	// // TODO Auto-generated method stub
	// if(success){
	// fstockName.setText(_fname);
	// AZUIPub.viewHiddenInput(mActivity,fstockName);
	// // fstockName.clearFocus();
	// }else{
	// fstockName.setText("");
	// fstockName.requestFocus();
	// }
	// }

	// @Override
	// protected void changeFlocationName(String _fname, boolean success) {
	// // TODO Auto-generated method stub
	// super.changeFlocationName(_fname, success);
	// if(success){
	// fsp.setText(_fname);
	// AZUIPub.viewHiddenInput(mActivity,fsp);
	// fsp.clearFocus();
	// }else{
	// fsp.setText("");
	// fsp.requestFocus();
	// }
	// }

	protected GxhbActivity iGetActivity() {
		// TODO Auto-generated method stub
		return (GxhbActivity) getActivity();
	}

	// 初始化分录界面的控件
	void initWidget() {
		// * ** noInputToucher 防止跳出软键盘*****
		barcode.setOnTouchListener(AZUIPub.noInputToucher);
		fgx.setOnTouchListener(AZUIPub.noInputToucher);
		fqxyy.setOnTouchListener(AZUIPub.noInputToucher);
		fqxyyhd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); // 下划线
		fqxyyhd.setTextColor(android.graphics.Color.BLUE);// 文字颜色
	}

	void data2Wight() {
		fmatno.setText(PbF.inzStr(theEntry.getFmatNumber()));
		fmatname.setText(PbF.inzStr(theEntry.getFmatName()));
		fgg.setText(PbF.inzStr(theEntry.getFmatModel()));
		fgx.setText(PbF.inzStr(theEntry.getFgxName()));
		fhgpqty.setText(PbF.number2StrFormat(theEntry.getFhgpqty()));
		fylfgqty.setText(PbF.number2StrFormat(theEntry.getFylfgqty()));
		fygfgqty.setText(PbF.number2StrFormat(theEntry.getFygfgqty()));
		fylbfqty.setText(PbF.number2StrFormat(theEntry.getFylbfqty()));
		fygbfqty.setText(PbF.number2StrFormat(theEntry.getFygbfqty()));
		fqxyy.setText(PbF.inzStr(theEntry.getFqxyyName()));
		fistjgs.setChecked("1".equals(theEntry.getFistjgs()));
		fgs.setText(PbF.number2StrFormat(theEntry.getFgs()));
		barcode.setText(PbF.inzStr(theEntry.getFtm()));
		fcys.setText(PbF.number2StrFormat(theEntry.getFcys()));
		fcybhgs.setText(PbF.number2StrFormat(theEntry.getFcybhgs()));
	}

	// 加载分录明细
	boolean ifDataLoaded() {
		if (entrystatus == 0) {
			theEntry = new JrPdaGxhbbillentry();
			intiEntry();
			mat_fparam = "false";
			AZUIPub.touchEnabled(fhgpqty);
			return true;
		} else {
			GxhbActivity mActivity = (GxhbActivity) getActivity();
			if (mActivity.getEntity() == null
					|| mActivity.getEntity().getEntrys() == null
					|| mActivity.getEntity().getEntrys().get(index) == null) {
				return false;
			}
			theEntry = (JrPdaGxhbbillentry) mActivity.getEntity().getEntrys()
					.get(index).clone();
			this.oldfqty = theEntry.getFhgpqty();
			if (theEntry == null) {
				return false;
			}
			// Boolean
			// showsp=mActivity.warehouseid2showLocal(theEntry.getFwarehouseid());
			// this.showLocation(showsp);
			// if(Boolean.parseBoolean(theEntry.igetExt_param())){
			// AZUIPub.touchDisabled(fqty);
			// }else{
			// AZUIPub.touchEnabled(fqty);
			// }
			return true;
		}
	}

	void wight2Data() {
		theEntry.setFmatNumber(AZUIPub.text2String(fmatno));
		theEntry.setFmatName(AZUIPub.text2String(fmatname));
		theEntry.setFmatModel(AZUIPub.text2String(fgg));
		//theEntry.setFtm(AZUIPub.text2String(barcode));//这里存入条码？
		theEntry.setFgxName(AZUIPub.text2String(fgx));
		theEntry.setFhgpqty(AZUIPub.text2BigDcm(fhgpqty));
		theEntry.setFylfgqty(AZUIPub.text2BigDcm(fylfgqty));
		theEntry.setFygfgqty(AZUIPub.text2BigDcm(fygfgqty));
		theEntry.setFylbfqty(AZUIPub.text2BigDcm(fylbfqty));
		theEntry.setFygbfqty(AZUIPub.text2BigDcm(fygbfqty));
		theEntry.setFqxyyName(AZUIPub.text2String(fqxyy));
		theEntry.setFistjgs(AZUIPub.check2Integer(fistjgs).toString());
		theEntry.setFgs(AZUIPub.text2BigDcm(fgs));
		theEntry.setFcys(AZUIPub.text2BigDcm(fcys));
		theEntry.setFcybhgs(AZUIPub.text2BigDcm(fcybhgs));
	}

	void adjustWight() {// 根据数据修改界面
		switch (entrystatus) {
		case 0: {// 新增
			this.btn_delete.setVisibility(View.INVISIBLE);
			this.btn_reset.setVisibility(View.VISIBLE);
			break;
		}
		case 1: {// 修改
			this.btn_delete.setVisibility(View.VISIBLE);
			this.btn_reset.setVisibility(View.INVISIBLE);
			break;
		}
		default:
			break;
		}
	}

	protected void intiEntry() {
		theEntry = new JrPdaGxhbbillentry();
		theEntry.setFhgpqty(new BigDecimal(defaultQty));
		if (!lastParaMap.isEmpty()) {
			popLastPara(); // 取上一次的值
		}
	}

	void pushLastPara() {
		// lastParaMap.put("fwarehouseId", theEntry.getFwarehouseid());
		// lastParaMap.put("fwarehouseName", theEntry.getFwarehousename());
		// lastParaMap.put("flocationId", theEntry.getFlocationid());
		// lastParaMap.put("flocationName", theEntry.getFlocationname());
	}

	void popLastPara() {
		// theEntry.setFwarehouseid(lastParaMap.get("fwarehouseId"));
		// theEntry.setFwarehousename(lastParaMap.get("fwarehouseName"));
		// fstockName.setText(PbF.inzStr(lastParaMap.get("fwarehouseName")));
		// theEntry.setFlocationid(lastParaMap.get("flocationId"));
		// theEntry.setFlocationname(lastParaMap.get("flocationName"));
		// fsp.setText(PbF.inzStr(lastParaMap.get("flocationName")));
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getEntrystatus() {
		return entrystatus;
	}

	public void setEntrystatus(int entrystatus) {
		this.entrystatus = entrystatus;
	}

	public void scan_new() {
		this.index = -1;
		data2Wight();
	}

	public void refreshFragment() {
		ifDataLoaded();
		data2Wight();
		adjustWight();
	}

	public void iGetFirstFocus() {
		barcode.requestFocus();
	}

	// click 事件区域 开始
	@OnClick(R.id.scan_btn_new)
	public void toNew() {
		if (!checker.unSaveCheck()) {
			AlertDialog unCheckDialog = new AlertDialog.Builder(getActivity(),
					AlertDialog.THEME_HOLO_DARK)
					.setTitle(Constance.alert_警告)
					.setMessage(Constance.alert_新增提醒)
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									intiEntry();
									entrystatus = 0;
									refreshFragment();
									adjustWight();
									iGetFirstFocus();
								}

							}).setNegativeButton("取消", null).show();
		} else {
			intiEntry();
			entrystatus = 0;
			refreshFragment();
			adjustWight();
			iGetFirstFocus();
		}
	}

	@OnClick(R.id.scan_btn_save)
	public void scan_save() {
		wight2Data();
		if (!checker.billcheck()) {
			return;
		}

		// scan_saveInnerWithoutSource();

		checker.insertCode(theEntry.getFtm()); // 保存本次QRCode
		mActivity.changeEntryList(index, theEntry, entrystatus);
		mActivity.getTab0().fdjxx.setText("合计"
				+ mActivity.getEntity().getEntrys().size() + "条信息,合格数量"
				+ sumqty());
		intiEntry();//刷新表体
		entrystatus = 0;
		refreshFragment();//刷新fragment
		iGetFirstFocus();
	}

	public BigDecimal sumqty() {
		BigDecimal fsumqty = new BigDecimal("0.0");
		for (int i = 0; i < mActivity.getEntity().getEntrys().size(); i++) {
			fsumqty = fsumqty.add(mActivity.getEntity().getEntrys().get(i)
					.getFhgpqty());
		}
		return fsumqty;
	}

	// 不带有源单的Save
	void scan_saveInnerWithoutSource() {
		mActivity.changeEntryList(index, theEntry, entrystatus);
		if (Boolean.parseBoolean(mat_fparam)) {
			// 动态条码则记住本次条码
			checker.insertCode(theEntry.getFtm());
		}
		pushLastPara();
		intiEntry();
		entrystatus = 0;
		refreshFragment();
		iGetFirstFocus();
	}

	@OnClick(R.id.scan_btn_delete)
	public void scan_delete() {
		this.oldfqty = new BigDecimal(0);
		scan_deleteInner(theEntry.getFhgpqty());
	}

	public void scan_deleteInner(BigDecimal _fqty) {
		// delete_calc(_fqty); //德欧
		mActivity.deleteEntry(index, entrystatus);
		if (Boolean.parseBoolean(mat_fparam)) {
			// 动态条码则记住本次条码
			checker.removeCode(theEntry.getFtm());
		}
		intiEntry();
		entrystatus = 0;
		index = 0;
		refreshFragment();
		iGetFirstFocus();
		//删除时同时删除合计信息
		mActivity.getTab0().fdjxx.setText("合计"
				+ mActivity.getEntity().getEntrys().size() + "条信息,合格数量"
				+ sumqty());
	}

	void delete_calc(BigDecimal _fqty) {
		String _matID = theEntry.getFmatId();
		List<JrPdaGxpgbillentry> srcList = mActivity.getSrcEList();
		HashMap<String, HashMap<String, Object>> matEntryAttr = mActivity
				.getMatEntryAttr();
		HashMap<String, Object> innerEntryAttr = matEntryAttr.get(_matID);
		BigDecimal _fQty = (BigDecimal) innerEntryAttr.get("fQty");//
		Map<String, BigDecimal> mat_QtyMap = mActivity.getMat_QtyMap();
		Map<String, BigDecimal> mat_exceedQtyMap = mActivity
				.getMat_exceedQtyMap();
		BigDecimal maxQty = mat_QtyMap.get(_matID);
		Double _leftQty = _fqty.doubleValue();

		int srcListSize = srcList.size();
		for (int i = srcListSize - 1; i >= 0; i--) {
			if (_leftQty == 0) {
				break;
			}
			JrPdaGxpgbillentry tmpSrcEntry = srcList.get(i);
			String _srcmatid = tmpSrcEntry.getFmatId();
			if (!_matID.equals(_srcmatid)) {
				continue;
			}
			BigDecimal _srcQty = tmpSrcEntry.getFqty();
			boolean breakflg = false;
			if (_leftQty <= _srcQty.doubleValue()) { // 分一次减去源单分录
				tmpSrcEntry.setFqty(_srcQty.subtract(new BigDecimal(_leftQty)));
				breakflg = true;
			}
			srcList.set(i, tmpSrcEntry);
			if (breakflg) {
				break;
			}
		}
		mActivity.setSrcEList(srcList);
		mat_QtyMap.put(_matID, maxQty.add(new BigDecimal(_leftQty)));
		innerEntryAttr.put("fQty", _fQty.subtract(new BigDecimal(_leftQty)));
		matEntryAttr.put(_matID, innerEntryAttr);
		mActivity.setMatEntryAttr(matEntryAttr);
		mActivity.setMat_QtyMap(mat_QtyMap);
		// checker.removeCode(theEntry.getFbarcode());
		mActivity.deleteEntry(index, this.entrystatus);

	}

	@OnClick(R.id.scan_btn_reset)
	public void scan_reset() {
		new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK)
				.setTitle(Constance.alert_提示)
				.setMessage(Constance.alert_SureReset)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						intiEntry();
						refreshFragment();
						iGetFirstFocus();
					}
				}).setNegativeButton("取消", null).show();

	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			if (entrystatus == 0
					&& StringUtils.isEmpty(theEntry.getFmatNumber())
					&& getView().findFocus() == null
					&& (!(getView().findFocus() instanceof EditText))) {

				iGetFirstFocus();
			}// 点扫描后获取焦点
		}
	}

	// @OnClick(R.id.searcher_MaterialNumber)
	// public void matSelect(){
	// Intent intent=new Intent();
	// intent.setClass(iGetActivity(), SearchMatActivity.class);
	// intent.putExtra("selDecType", Constance.selDec_select);
	// intent.putExtra("requestCode", Constance.requestCode_baseinfoMaterial);
	// this.startActivityForResult(intent,
	// Constance.requestCode_baseinfoMaterial);
	// }
	@OnClick(R.id.headText_缺陷原因)
	public void qxyySelect() {
		fqxyy.requestFocus();
		Intent intent = new Intent();
		intent.setClass(iGetActivity(), SearchItemActivity.class);
		intent.putExtra("documentType", Constance.baseinfo_qxyy);
		intent.putExtra("requestCode", Constance.requestCode_baseinfoqxyy);
		this.startActivityForResult(intent, Constance.requestCode_baseinfoqxyy);
	}

	//
	// @OnClick(R.id.searcher_spName)
	// public void spSelect(){
	// fsp.requestFocus();
	// Intent intent=new Intent();
	// intent.setClass(iGetActivity(), SearchItemActivity.class);
	// intent.putExtra("parentCondition",theEntry.getFwarehouseid());
	// intent.putExtra("documentType", Constance.baseinfo_sp);
	// intent.putExtra("requestCode", Constance.requestCode_baseinfoSp);
	// this.startActivityForResult(intent, Constance.requestCode_baseinfoSp);
	// }

	// click 事件区域 结束

	// checkbox 开始
	@OnCheckedChanged(R.id.head_checkBox_是否统计工时)
	public void islpChecked(boolean checked) {
		theEntry.setFistjgs(checked ? "1" : "0");
	}

	// checkbox 结束

	@OnEditorAction(R.id.scan_ScanArea)
	public boolean decodeMat(TextView v, int actionId, KeyEvent event) {
		if ("".equals(AZUIPub.text2String(v).trim())) {
			return false;
		}
		String _barcode = AZUIPub.text2String(v).trim();
		String[] fcode = _barcode.split("/");
		String ftype = fcode[0];
		if ("D1".equals(ftype)) {
			mActivity.getPresenter().decodeGxBarcode(this, _barcode);
		} else if ("B5".equals(ftype)) {
			mActivity.getPresenter().decodeQxBarcode(this, _barcode);
		} else {
			alertDialog.setMessage(Constance.check_SrcBillError).show();
		}

		barcode.setText("");
		new Thread(new Runnable() {
			@Override
			public void run() {
				new Handler(Looper.getMainLooper()).post(new Runnable() {
					@Override
					public void run() {
						barcode.requestFocus();
					}
				});
			}
		}).start();

		return false;
	}
	public void decodeMatByCode(String _barcode) {
		// TODO Auto-generated method stub
		String[] fcode = _barcode.split("/");
		String ftype = fcode[0];
		if ("D1".equals(ftype)) {
			mActivity.getPresenter().decodeGxBarcode(this, _barcode);
		} else if ("B5".equals(ftype)) {
			mActivity.getPresenter().decodeQxBarcode(this, _barcode);
		} else {
			alertDialog.setMessage(Constance.check_SrcBillError).show();
		}
		
	}
	class DataChecker extends BaseEntryDataChecker {
		Boolean enable = true;// 是否需要校验

		boolean _bool = false;

		public DataChecker(Context context) {
			super(context);
		}

		public boolean matCheck(String matid) {
			if (!enable)
				return enable;
			try {
				if (!mActivity.getMat_QtyMap().containsKey(matid)) {
					alertDialog.setMessage(Constance.check_SrcBillNoMatExist)
							.show();
					// Toast.makeText(mActivity, "源单上并无此物料",
					// Toast.LENGTH_SHORT).show();
					return false;
				} else {
					return true;
				}
			} catch (Exception e) {
				alertDialog.setMessage(Constance.check_SrcBillNoMatExist)
						.show();
				// Toast.makeText(mActivity, "源单上并无此物料",
				// Toast.LENGTH_SHORT).show();
				return false;
			}
		}

		// public boolean qtyCheck(String matid,final BigDecimal qty){
		// if(!enable) return enable;
		// if(!matCheck(matid)) return false;
		// // try{
		//
		// //校验 是否超出应收-已收
		// BigDecimal canQty=mActivity.getMat_QtyMap().get(matid).add(oldfqty);
		// if(canQty.compareTo(qty)>=0){
		// scan_saveInner(qty);
		//
		// return true;
		// }else{
		// new AlertDialog.Builder(iGetActivity(),AlertDialog.THEME_HOLO_DARK)
		// .setTitle("警告")
		// .setMessage(String.format(Constance.check_待收已收提示
		// ,canQty,qty)+"是否继续添加？")
		// .setPositiveButton("确定",new DialogInterface.OnClickListener() {
		//
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// // TODO Auto-generated method stub
		// scan_saveInner(qty); //保存方法
		// _bool=true;
		// }
		// })
		// .setNegativeButton("取消",new DialogInterface.OnClickListener() {
		//
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// // TODO Auto-generated method stub
		// _bool=false;
		// }
		// }).show();
		// return true;
		// }
		//
		// }

		public boolean unSaveCheck() {
			if (!enable)
				return enable;
			// if(true) return false;
			if (entrystatus == 1) {
				return true;
			} else if (entrystatus == 0) {
				try {
					if (theEntry == null)
						return true;
					if (!StringUtils.isEmpty(theEntry.getFmatId())) {
						return false;
					} else {
						return true;
					}
				} catch (Exception e) {
					return false;
				}
			}
			return true;
		}

		public boolean billcheck() {
			String msg = "";
			try {
				if (StringUtils.isEmpty(theEntry.getFmatId())) {
					msg += Constance.alert_scanInfoCheck;
				} /*else if (theEntry.getFhgpqty().doubleValue() < 0.000000001) {
					msg += Constance.alert_ZeroQty;
				}*/

			} catch (Exception e) {
				msg = Constance.checkerUndefinedError;
			}
			if ("".equals(msg)) {
				return true;
			} else {
				alertDialog.setMessage(msg);
				alertDialog.show();
				return false;
			}
		}
	}

	

}
