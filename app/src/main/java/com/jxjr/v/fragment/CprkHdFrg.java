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
import com.jxjr.contract.CprkContract.IfCprkHdFrgView;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrCpbzdjBillDTO;
import com.jxjr.m.DTO.JrCprkBillDTO;
import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.m.DTO.JrXsckSrcBillDTO;
import com.jxjr.m.entity.JrPdaCprkbill;
import com.jxjr.m.entity.JrPdaCprkbillentry;
import com.jxjr.m.network.CprkRequest;
import com.jxjr.m.network.RResult;
import com.jxjr.utility.AZUIPub;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;
import com.jxjr.utility.HttpFun;
import com.jxjr.utility.PbF;
import com.jxjr.utility.SelfDefException;
import com.jxjr.utility.StringUtils;
import com.jxjr.v.activity.CprkActivity;
import com.jxjr.v.activity.SearchItemActivity;
import com.jxjr.v.view.MEditText;

public class CprkHdFrg extends ABaseFTBillHdFrg<JrPdaCprkbill,CprkActivity> implements IfCprkHdFrgView{

	CprkActivity mActivity;	

	JrPdaCprkbill billhd;
	
    Button btn_save;
	
	Button btn_submit;
	
	Button btn_exit;
	
	@BindView(R.id.head_editText_单据编号)
	EditText fbillno;	
	
	@BindView(R.id.head_editText_单据日期)
	TextView fdate;
		
	@BindView(R.id.head_editText_登记单号)
	MEditText fdjdno;	
	
	@BindView(R.id.head_editText_生产订单号)
	TextView fscddno;
	
	@BindView(R.id.head_editText_部门)
	EditText fdept;
	
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
		switch (requestCode) {
			case Constance.requestCode_baseinfoDept:{
				if(resultCode==Constance.requestCode_error){
					String error=intent.getStringExtra("error");
					AZUIPub.showMessage(iGetActivity(), Constance.show_Dialog, error);
					billhd.setFdeptId(null);
					billhd.setFdeptNumber(null);
					billhd.setFdeptName(null);
					fdept.setText("");
					fdept.requestFocus();
					break;
				}
				String _fname=intent.getStringExtra("fname");
				String _fnumber=intent.getStringExtra("fnumber");
				String _fid=intent.getStringExtra("fid");
				try{
					billhd.setFdeptId(_fid);
					billhd.setFdeptNumber(_fnumber);
					billhd.setFdeptName(_fname);
					
					fdept.setText(_fname);				
				}catch(Exception e){					
				}
				break;
			}
			
		}
	}


	@Override
	protected int setLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_cprk_hd;
	}



	/* (non-Javadoc)
	 * @see com.lf.v.fragment.ABaseBillHdFrg#iGetActivity()
	 */
	@Override
	protected CprkActivity iGetActivity() {
		// TODO Auto-generated method stub
		return (CprkActivity) getActivity();
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
		mActivity=(CprkActivity) getActivity();
		initData();
		initWidget();
		checker=new DataChecker();
		alertDialog=iGetActivity().getAlertDialog();
		CprkActivity mActivity=(CprkActivity) iGetActivity();
		JrCprkBillDTO entity= mActivity.getEntity();
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
	
	
	@Override
	public void popDecodeSrcbill(boolean sucflg, String msg,
			JrCpbzdjBillDTO itemDTO) {
		// TODO Auto-generated method stub
		if(sucflg){
			if(itemDTO !=null)	{	
				Date curDate = new Date(System.currentTimeMillis());//获取当前时间 
				billhd.setFdate(curDate);
				billhd.setFcreateuserid(PbF.int2Str(GI.SESSION.getfUserID()));
				billhd.setFcreateusername(GI.SESSION.getfUserName());
				billhd.setFbizdate(curDate);
				
				billhd.setFdeptId(itemDTO.getBill().getFdeptId());
				billhd.setFdeptName(itemDTO.getBill().getFdeptName());
				billhd.setFdeptNumber(itemDTO.getBill().getFdeptNumber());
				billhd.setFcpbzdjId(itemDTO.getBill().getFid());
				billhd.setFcpbzdjNo(itemDTO.getBill().getFbillNo());
				billhd.setFscddId(itemDTO.getBill().getFscddId());
				billhd.setFscddNo(itemDTO.getBill().getFscddNo());
				billhd.setFscddSeq(itemDTO.getBill().getFscddSeq());
				billhd.setFscddentryId(itemDTO.getBill().getFscddentryId());

				fdate.setText(PbF.date2Str(curDate));	
				fczdate.setText(PbF.date2Str(curDate));
				fczy.setText(PbF.inzStr(GI.SESSION.getfUserName()));
				fdjdno.setText(itemDTO.getBill().getFbillNo());
				fscddno.setText(itemDTO.getBill().getFscddNo());
				fdept.setText(itemDTO.getBill().getFdeptName());
			}
			else{
				fdjdno.setText("");
				alertDialog.setMessage(Constance.alert_DataNull);
				alertDialog.show();
				return;
			}			
		}else{
			fdjdno.setText("");
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
		
		fdept.setOnTouchListener(AZUIPub.noInputToucher);
//		fdepthd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
//		fdepthd.setTextColor(android.graphics.Color.RED);
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
		CprkActivity mActivity=(CprkActivity) iGetActivity();
		JrCprkBillDTO entity= mActivity.getEntity();
		billhd=entity.getBill();
		if(entity==null||billhd==null){
			return;
		}
		
		fbillno.setText(PbF.inzStr(billhd.getFnumber()));
//		fbarcode.setText(PbF.inzStr(billhd.getFbarcode()));
		fdate.setText(PbF.date2Str(billhd.getFdate()));
		fdept.setText(PbF.inzStr(billhd.getFdeptName()));
		fczy.setText(PbF.inzStr(billhd.getFcreateusername()));	
		fczdate.setText(PbF.date2Str(billhd.getFbizdate()));	
		//ftotalQty.setText(PbF.number2StrFormat(billhd.getFtotalqty()));
//		fcreateUserName.setText(PbF.inzStr(billhd.getFcreateusername()));
		
	}

	@Override
	public void wight2Data() {
		// TODO Auto-generated method stub
		CprkActivity mActivity=(CprkActivity) iGetActivity();
		JrCprkBillDTO entity= mActivity.getEntity();
		billhd.setFnumber(AZUIPub.text2String(fbillno));
		billhd.setFdate(AZUIPub.text2Date(fdate));
		billhd.setFdeptName(AZUIPub.text2String(fdept));
		billhd.setFcreateuserid(PbF.int2Str(GI.SESSION.getfUserID()));
		billhd.setFcreateusername(GI.SESSION.getfUserName());
		billhd.setFbizdate(AZUIPub.text2Date(fdate));
		entity.setBill(billhd);
		mActivity.setEntity(entity);
	}
	
//	@Override  
//	public void setUserVisibleHint(boolean isVisibleToUser) {
//		super.setUserVisibleHint(isVisibleToUser);  
//		if (isVisibleToUser) {  
//		       //相当于Fragment的onResume  
//			SubinwarehsActivity mActivity=iGetActivity();
//			try{
//				if(mActivity.getEntity()!=null&&mActivity.getEntity().getBill()!=null){
//					ftotalQty.setText(PbF.number2Str(
//							mActivity.getEntity().getBill().getFtotalQty()!=null?
//									mActivity.getEntity().getBill().getFtotalQty()
//									:0
//							));
//				}
//			}catch(Exception e){
//				
//			}
//
//
//		} else {  
//		       //相当于Fragment的onPause  
//		}  
//	}
	@Override
	public void refreshFragment() {
		// TODO Auto-generated method stub
		data2Wight();		
	}
	
	public void iGetDefaultFocus(){
		fdept.requestFocus();
	}
	
	public void refrshdata(){
		fdate.setText(PbF.date2Str(billhd.getFdate()));	
		fczdate.setText(PbF.date2Str(billhd.getFdate()));
		fczy.setText(PbF.inzStr(billhd.getFcreateusername()));
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
		CprkActivity mActivity=(CprkActivity) iGetActivity();
		mActivity.billNew();
	}
	
	@OnClick(R.id.head_btn_save)
	public void billSave() {
		wight2Data();
		if(!checker.billcheck()){
			return;
		}
		CprkActivity mActivity=(CprkActivity) iGetActivity();
		try {
			CprkRequest request = new CprkRequest();
			JrCprkBillDTO entity = mActivity.getEntity();
			request.setDtoObj(entity);
			// String requestJSON=request.dtoObj2String();
			String requestJSON = request.toString();
			String requestUrl = request.iGetSaveCheckURL();
			String response = HttpFun.doPost(requestUrl, requestJSON);
			if (null == response) {
				throw new SelfDefException(SelfDefException.noReponse);
			}
			RResult result = new RResult(response);
			if(!result.isFlag()){
				throw new SelfDefException(result.getMessage());
			}else if(result.isFlag()&&!StringUtils.isEmpty(result.getMessage())){
				AlertDialog unCheckDialog=new AlertDialog.Builder(iGetActivity(),AlertDialog.THEME_HOLO_DARK)
				.setTitle(Constance.alert_提示)
				.setMessage(result.getMessage())
				.setPositiveButton("是", new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						CprkActivity mActivity=(CprkActivity) iGetActivity();
						mActivity.billSave();
					}
					
				})
				.setNegativeButton("否",null)
				.show();	
			}else{
				mActivity.billSave();
			}
		} catch (SelfDefException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@OnClick(R.id.head_btn_submit)
	public void billSubmit(){
		wight2Data();
		if(!checker.billcheck()){
			return;
		}
		CprkActivity mActivity=(CprkActivity) iGetActivity();
		mActivity.billSubmit();
		//disabledButton();
	}
	
	public void disabledButton(){
		btn_save.setEnabled(false);
		btn_submit.setEnabled(false);
	}
	
	@OnClick(R.id.head_btn_billexit)
	public void billExit() {
		getActivity().finish();
	}
	
//	@OnClick(R.id.head_editText_仓库)
//	public void stockSelect(){
//		Intent intent=new Intent();
//		intent.setClass(iGetActivity(), BaseInfoQueryActivity.class);
//		intent.putExtra("documentType", Constance.baseinfo_stock);
//		this.startActivityForResult(intent, Constance.requestCode_baseinfoStock);
//	}
	
	@OnClick(R.id.headText_部门)
	public void deptSelect(){
		Intent intent=new Intent();
		intent.setClass(iGetActivity(), SearchItemActivity.class);
		intent.putExtra("documentType", Constance.baseinfo_dept);
		intent.putExtra("requestCode", Constance.requestCode_baseinfoDept);
		this.startActivityForResult(intent, Constance.requestCode_baseinfoDept);
	}
	
	
	// click 事件区域 结束
	
	//OnEditorAction 区域开始
	@OnEditorAction(R.id.head_editText_登记单号)
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
		String _srcbillno=AZUIPub.text2String(v).trim();
		mActivity.getPresenter().decodeSrcBarcode(this, _srcbillno);

		return false;
	}	
	//OnEditorAction 区域结束
	public boolean decodeSrcData(String catchDecode){
		mActivity.getPresenter().decodeSrcBarcode(this, catchDecode);
		return false;
	}
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

//				if(StringUtils.isEmpty(billhd.getFsourceBillId())){
//					msg+="无源单信息。";
//				}		
//				if(StringUtils.isEmpty(billhd.getFstockerUserId())){
//					msg+="无仓管员信息。";
//				}
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
