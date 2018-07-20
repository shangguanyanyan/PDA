package com.jxjr.v.fragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import android.view.LayoutInflater;

import com.example.copyofhy.R;
import com.jxjr.contract.XsckContract.IfXsckHdFrgView;
import com.jxjr.contract.XsckContract.IfXsckScanFrgView;

import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrCpbzdjBillDTO;
import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.m.DTO.JrXsckBillDTO;
import com.jxjr.m.DTO.JrXsckSrcBillDTO;
import com.jxjr.m.entity.JrPdaXsckbill;
import com.jxjr.utility.AZUIPub;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;
import com.jxjr.utility.PbF;
import com.jxjr.utility.StringUtils;

import com.jxjr.v.activity.SearchItemActivity;
import com.jxjr.v.activity.XsckActivity;
import com.jxjr.v.view.MEditText;

public class XsckHdFrg extends ABaseFTBillHdFrg<JrPdaXsckbill,XsckActivity> implements IfXsckHdFrgView{

	XsckActivity mActivity;	

	JrPdaXsckbill billhd;
	
    Button btn_save;
	
	Button btn_submit;
	
	Button btn_exit;
	
	@BindView(R.id.head_editText_单据编号)
	EditText fbillno;	
	
	@BindView(R.id.head_editText_单据日期)
	TextView fdate;
		
	@BindView(R.id.head_editText_源单编号)
	MEditText fsrcbillno;
	
	@BindView(R.id.head_editText_客户)
	TextView fcust;
	
	@BindView(R.id.head_editText_业务员)
	TextView femp;
	
	@BindView(R.id.head_editText_部门)
	TextView fdept;
	
	@BindView(R.id.head_editText_合计信息)
	TextView fhjxx;
	
	@BindView(R.id.head_editText_操作员)
	TextView fczy;
	
	@BindView(R.id.head_editText_操作日期)
	TextView fczdate;
	
	static final int DATE_DIALOG_ID = 0;	
	
//	JrPurInWarehsBill billhd=new JrPurInWarehsBill();	
	
	AlertDialog.Builder alertDialog;
	
	DataChecker checker;
	AlertDialog.Builder fdeptSelecter;
	
	public String _ftype="";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater,container,savedInstanceState);
		View view = inflater.inflate(setLayoutID(),
				container, false);
		ButterKnife.bind(this, view);
		
		return view;
	}
	
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		if(resultCode==0){
			return;
		}
	}


	@Override
	protected int setLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_xsck_hd;
	}



	/* (non-Javadoc)
	 * @see com.lf.v.fragment.ABaseBillHdFrg#iGetActivity()
	 */
	@Override
	protected XsckActivity iGetActivity() {
		// TODO Auto-generated method stub
		return (XsckActivity) getActivity();
	}
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
//		initWidget();
//		checker=new DataChecker();
		alertDialog=iGetActivity().getAlertDialog();		
		mActivity=(XsckActivity) getActivity();
		initData();
		initWidget();
		checker=new DataChecker();
		alertDialog=iGetActivity().getAlertDialog();
		XsckActivity mActivity=(XsckActivity) iGetActivity();
		JrXsckBillDTO entity= mActivity.getEntity();
		billhd=entity.getBill();
		if(entity==null||billhd==null){
			return;
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public void onStart(){
		super.onStart();
	}
	
//	@Override  
//	public void setUserVisibleHint(boolean isVisibleToUser) {
//		super.setUserVisibleHint(isVisibleToUser);  
//		if (isVisibleToUser) {  
//			fsrcbillno.requestFocus();
//		} else {  
//		       //相当于Fragment的onPause  
//		}  
//	}

	@Override
	public void popDecodeSrcbill(boolean sucflg, String msg,
			JrXsckSrcBillDTO itemDTO) {
		// TODO Auto-generated method stub
		if(sucflg){
			if(itemDTO !=null)	{	
				Date curDate = new Date(System.currentTimeMillis());//获取当前时间 
				billhd.setFdate(curDate);
				billhd.setFcreateuserid(PbF.int2Str(GI.SESSION.getfUserID()));
				billhd.setFcreateusername(GI.SESSION.getfUserName());
				billhd.setFbizdate(curDate);
				
				billhd.setFcustId(itemDTO.getFcustId());
				billhd.setFcustName(itemDTO.getFcustName());
				billhd.setFcustNumber(itemDTO.getFcustNumber());	
				billhd.setFempId(itemDTO.getFempId());
				billhd.setFempName(itemDTO.getFempName());
				billhd.setFempNumber(itemDTO.getFempNumber());
				billhd.setFdeptId(itemDTO.getFdeptId());
				billhd.setFdeptName(itemDTO.getFdeptName());
				billhd.setFdeptNumber(itemDTO.getFdeptNumber());
				billhd.setFfhtzdNo(itemDTO.getFbillNo());
				billhd.setFfhtzdId(itemDTO.getFid());
				
				fdate.setText(PbF.date2Str(curDate));	
				fczdate.setText(PbF.date2Str(curDate));
				fczy.setText(PbF.inzStr(GI.SESSION.getfUserName()));
				fcust.setText(itemDTO.getFcustName());
				femp.setText(itemDTO.getFempName());
				fdept.setText(itemDTO.getFdeptName());
			}
			else{
				fsrcbillno.setText("");
				alertDialog.setMessage(Constance.alert_DataNull);
				alertDialog.show();
				return;
			}			
		}else{
			fsrcbillno.setText("");
			alertDialog.setMessage(msg);
			alertDialog.show();
			return;
		}
		
	}
	

	@Override
	protected void initWidget() {
		// TODO Auto-generated method stub
		btn_save=(Button)getView().findViewById(R.id.head_btn_save);
		btn_submit=(Button)getView().findViewById(R.id.head_btn_submit);
		btn_exit=(Button)getView().findViewById(R.id.head_btn_billexit);
		
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间 
		fdate.setText(PbF.date2Str(curDate));	
		fczdate.setText(PbF.date2Str(curDate));
		fczy.setText(PbF.inzStr(GI.SESSION.getfUserName()));
	}

	@Override
	protected void data2Wight() {
		// TODO Auto-generated method stub
		XsckActivity mActivity=(XsckActivity) iGetActivity();
		JrXsckBillDTO entity= mActivity.getEntity();
		billhd=entity.getBill();
		if(entity==null||billhd==null){
			return;
		}
		
		fbillno.setText(PbF.inzStr(billhd.getFnumber()));
		fsrcbillno.setText(PbF.inzStr(billhd.getFfhtzdNo()));
//		fbarcode.setText(PbF.inzStr(billhd.getFbarcode()));
		fdate.setText(PbF.date2Str(billhd.getFdate()));

		fdept.setText(PbF.inzStr(billhd.getFdeptName()));
		femp.setText(PbF.inzStr(billhd.getFempName()));
		fcust.setText(PbF.inzStr(billhd.getFcustName()));
		fczy.setText(PbF.inzStr(billhd.getFcreateusername()));	
		fczdate.setText(PbF.date2Str(billhd.getFbizdate()));	
		//ftotalQty.setText(PbF.number2StrFormat(billhd.getFtotalqty()));
//		fcreateUserName.setText(PbF.inzStr(billhd.getFcreateusername()));
		
	}

	@Override
	public void wight2Data() {
		// TODO Auto-generated method stub
		XsckActivity mActivity=(XsckActivity) iGetActivity();
		JrXsckBillDTO entity= mActivity.getEntity();
		billhd.setFnumber(AZUIPub.text2String(fbillno));
		billhd.setFfhtzdNo(AZUIPub.text2String(fsrcbillno));
		//billhd.setFbiztype(AZUIPub.text2String(fbizType));
		billhd.setFdate(AZUIPub.text2Date(fdate));
		billhd.setFdeptName(AZUIPub.text2String(fdept));
		billhd.setFempName(AZUIPub.text2String(femp));
		billhd.setFcustName(AZUIPub.text2String(fcust));
		entity.setBill(billhd);
		mActivity.setEntity(entity);
	}
	
	
	
	@Override
	public void refreshFragment() {
		// TODO Auto-generated method stub
		data2Wight();		
	}
	
	public void iGetDefaultFocus(){
		fsrcbillno.requestFocus();
	}
	
	// 复杂控件区域 开始
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			StringBuilder tmpdate = new StringBuilder().append(year + "-")
					.append(String.format("%02d-", monthOfYear + 1)) // 这里月份要+1
					.append(String.format("%02d", dayOfMonth));
			fdate.setText(tmpdate);
		}

	};
	
	// 复杂控件区域 结束
	
	// click 事件区域 开始
	@OnClick(R.id.head_editText_单据日期)
	public void showDatePicker() {
		// Toast.makeText(mActivity,"哈哈", Toast.LENGTH_SHORT).show();
		super.onCreateDialog(fdate,mDateSetListener).show();
	}
	
	@OnClick(R.id.head_btn_new)
	public void billNew() {
		XsckActivity mActivity=(XsckActivity) iGetActivity();
		mActivity.billNew();
	}
	
	@OnClick(R.id.head_btn_save)
	public void billSave() {
		wight2Data();
		if(!checker.billcheck()){
			return;
		}
		XsckActivity mActivity=(XsckActivity) iGetActivity();
		mActivity.billSave();
	}
	
	@OnClick(R.id.head_btn_submit)
	public void billSubmit(){
		wight2Data();
		if(!checker.billcheck()){
			return;
		}
		XsckActivity mActivity=(XsckActivity) iGetActivity();
		mActivity.billSubmit();
	}
	
	public void disabledButton(){
		btn_save.setEnabled(false);
		btn_submit.setEnabled(false);
	}
	
	@OnClick(R.id.head_btn_billexit)
	public void billExit() {
		getActivity().finish();
	}
	
	
	// click 事件区域 结束
	
	//OnEditorAction 区域开始
	@OnEditorAction(R.id.head_editText_源单编号)
	public boolean decodeSrcData(TextView v, int actionId, KeyEvent event){		
		if("".equals(AZUIPub.text2String(v).trim())){
			return false;
		}
//		if(StringUtils.isEmpty(ftype.getText().toString())){
//			ftype.requestFocus();
//			v.setText("");
//			alertDialog.setMessage("请先选择单据类型！");
//			alertDialog.show();			
//			return false;
//		}
		String _srcbillno=StringUtils.replaceBlank(AZUIPub.text2String(v));
//				AZUIPub.text2String(v).trim();
		mActivity.getPresenter().decodeSrcBarcode(this, _srcbillno);

		return false;
	}	
	
	//OnEditorAction 区域结束
	
	// 逻辑区域开始
	
	// 逻辑区域结束
	class DataChecker {
		Boolean enable=true;// 是否需要校验
		
		public DataChecker() {
			// TODO Auto-generated constructor stub
		}
		
		public boolean billcheck(){
			String msg="";
			try{

				if(StringUtils.isEmpty(billhd.getFfhtzdNo())){
					msg+="无源单信息。";
				}		
				if(StringUtils.isEmpty(billhd.getFcustId())){
					msg+="无客户信息。";
				}
				if(StringUtils.isEmpty(billhd.getFempId())){
					msg+="无业务员信息。";
				}
				if(StringUtils.isEmpty(billhd.getFdeptId())){
					msg+="无部门信息。";
				}

			}catch(Exception e){
				msg=Constance.checkerUndefinedError;
			}
			if("".equals(msg)){
				return true;
			}else{
				alertDialog.setMessage(msg);
				alertDialog.show();
				return false;
			}
		}
		
	}


}
