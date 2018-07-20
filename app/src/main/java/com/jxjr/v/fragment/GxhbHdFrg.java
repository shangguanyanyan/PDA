package com.jxjr.v.fragment;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import android.R.string;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

import com.example.copyofhy.R;
import com.jxjr.contract.GxhbContract.IfGxhbHdFrgView;
import com.jxjr.m.DTO.JrGxhbBillDTO;
import com.jxjr.m.entity.JrPdaGxhbbill;
import com.jxjr.m.entity.JrPdaGxhbbillentry;
import com.jxjr.utility.AZUIPub;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;
import com.jxjr.utility.PbF;
import com.jxjr.utility.StringUtils;
import com.jxjr.v.activity.GxhbActivity;
//import com.jxjr.v.activity.SearchItemActivity;

public class GxhbHdFrg extends ABaseBillHdFrg<JrPdaGxhbbill,GxhbActivity> 
		implements IfGxhbHdFrgView{
	
	GxhbActivity mActivity;	
	
	JrPdaGxhbbill billhd;
	
	Button btn_save;
	
	Button btn_submit;
	
	Button btn_exit;
	
	@BindView(R.id.head_editText_单据编号)
	EditText fbillno;
	
	@BindView(R.id.scan_fcwdate)
	TextView fcwdate;
	
	@BindView(R.id.scan_date)
	TextView fdate;
	
	@BindView(R.id.scan_dept)
	TextView fdeptName;

	@BindView(R.id.scan_staffdata)
	TextView fygxx;

	@BindView(R.id.scan_billdata)
	TextView fdjxx;
	
	@BindView(R.id.scan_op)
	TextView fczy;
	
	@BindView(R.id.scan_opdate)
	TextView fczdate;
	
	
	static final int DATE_DIALOG_ID = 0;	
		
	AlertDialog.Builder alertDialog;
	
	DataChecker checker;
	
	
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
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mActivity=(GxhbActivity) getActivity();
		initData();
		initWidget();
		checker=new DataChecker();
		alertDialog=iGetActivity().getAlertDialog();
		GxhbActivity mActivity=(GxhbActivity) iGetActivity();
		JrGxhbBillDTO entity= mActivity.getEntity();
		billhd=entity.getBill();
		if(entity==null||billhd==null){
			return;
		}

	}



	protected int setLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_gxhb_hd;
	}
	
	@Override  
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);  
		if (isVisibleToUser) {  
		       //相当于Fragment的onResume  
			if(billhd!=null){
				calcTotalQty();//计算总数量
			}			
		} else {  
		       //相当于Fragment的onPause  
		}  
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		switch (resultCode) {
			case Constance.requestCode_baseinfoDept:{
				String _fname=intent.getStringExtra("fname");
				String _fid=intent.getStringExtra("fid");		
				billhd.setFdeptId(_fid);
				billhd.setFdeptName(_fname);
				fdeptName.setText(PbF.inzStr(_fname));
				break;
			}
			
		}
	}

	protected GxhbActivity iGetActivity() {
		// TODO Auto-generated method stub
		return (GxhbActivity) getActivity();
	}


	@Override
	public void onStart(){
		super.onStart();
	}

	
	

	protected void initWidget() {
		// TODO Auto-generated method stub
		btn_save=(Button)getView().findViewById(R.id.head_btn_save);
		btn_submit=(Button)getView().findViewById(R.id.head_btn_submit);
		btn_exit=(Button)getView().findViewById(R.id.head_btn_billexit);
		
		fdeptName.setOnTouchListener(AZUIPub.noInputToucher);
//		fstocker.setOnTouchListener(AZUIPub.noInputToucher);
//		fauditerUserName.setOnTouchListener(AZUIPub.noInputToucher);
	}


	protected void initData() {
		// TODO Auto-generated method stub
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间 
		Date resDate = new Date();
		Calendar calendar = new GregorianCalendar(); 
		calendar.setTime(curDate);
		/*
		 * 当settime的参数是date类型时，所get的月份数为实际月份数-1
		 * 当settime的参数为自定义整数时，所get的月份数为实际月份数
		 */
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if(day > 25){
			//在这里12=0，即12+1=1，底层模除方法都写好了，所以不用自己写
			calendar.set(calendar.get(Calendar.YEAR),month,1,0,0,0);
			//在gettime 中，会将月份+1
			resDate = calendar.getTime();
		}
		fcwdate.setText(PbF.date2Str(resDate));
		fdate.setText(PbF.date2Str(curDate));
		fczdate.setText(PbF.date2Str(curDate));
		fczy.setText(PbF.inzStr(GI.SESSION.getfUserName()));
	}


	protected void data2Wight() {
		// TODO Auto-generated method stub
		GxhbActivity mActivity=(GxhbActivity) iGetActivity();
		JrGxhbBillDTO entity= mActivity.getEntity();
		billhd=entity.getBill();
		if(entity==null||billhd==null){
			return;
		}

		fbillno.setText(PbF.inzStr(billhd.getFnumber()));
		fdate.setText(PbF.date2Str(billhd.getFdate()));
//		fsourceBillNumber.setText(PbF.inzStr(billhd.getFsourceBillNumber()));
		fdeptName.setText(PbF.inzStr(billhd.getFdeptName()));
		fygxx.setText(PbF.inzStr(billhd.getFempxx()));
		//fdjxx.setText(PbF.inzStr(billhd.getFdjxx()));
		fczy.setText(PbF.inzStr(billhd.getFcreateusername()));
		fczdate.setText(PbF.date2Str(billhd.getFdate()));

	}


	public void wight2Data() {
		// TODO Auto-generated method stub
		GxhbActivity mActivity=(GxhbActivity) iGetActivity();
		JrGxhbBillDTO entity= mActivity.getEntity();
		billhd.setFnumber(AZUIPub.text2String(fbillno));
		billhd.setFdate(AZUIPub.text2Date(fdate));
//		billhd.setFsourceBillNumber(AZUIPub.text2String(fsourceBillNumber));
		billhd.setFdeptName(AZUIPub.text2String(fdeptName));
		billhd.setFempxx(AZUIPub.text2String(fygxx));
		//billhd.setFdjxx(AZUIPub.text2String(fdjxx));
		billhd.setFcreateusername(AZUIPub.text2String(fczy));
		billhd.setFcwdate(AZUIPub.text2Date(fcwdate));
		//billhd.setFcreatedate(AZUIPub.text2Date(fczdate));
		entity.setBill(billhd);
		mActivity.setEntity(entity);
	}
	
	public void refrshdata(){
		fdate.setText(PbF.date2Str(billhd.getFdate()));
		fdeptName.setText(PbF.inzStr(billhd.getFdeptName()));
		fygxx.setText(PbF.inzStr(billhd.getFempxx()));	
		fczdate.setText(PbF.date2Str(billhd.getFdate()));
		fczy.setText(PbF.inzStr(billhd.getFcreateusername()));
	}
	
//	@Override  
//	public void setUserVisibleHint(boolean isVisibleToUser) {
//		super.setUserVisibleHint(isVisibleToUser);  
//		if (isVisibleToUser) {  
//		       //相当于Fragment的onResume  
//			ManufactureInstockActivity mActivity=iGetActivity();
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

	public void refreshFragment() {
		// TODO Auto-generated method stub
		data2Wight();
	}
	
	public void iGetDefaultFocus(){
		fdeptName.requestFocus();
	}
	
//	private void intiNoSimpleView(){		
//		forgSelecter=new AlertDialog.Builder(iGetActivity());
//		forgSelecter.setTitle(Constance.MutiOrgCon.title);
//    	final List<String> org_tmpKeys=new ArrayList<String>(Constance.MutiOrgCon.forgMap.keySet());
//    	final List<String> org_tmpValues=new ArrayList<String>(Constance.MutiOrgCon.forgMap.values());
//    	
//    	forgname.setOnClickListener(new OnClickListener(){
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//				forgSelecter.setSingleChoiceItems(org_tmpValues.toArray(new String[org_tmpValues.size()]), 
//						org_tmpKeys.indexOf(GI.forgid), new DialogInterface.OnClickListener() {
//		            @Override
//		            public void onClick(DialogInterface dialog, int which) {
//		            	System.out.println("which=="+org_tmpKeys.get(which));
//		            	GI.forgid=org_tmpKeys.get(which);
//		            	forgname.setText(Constance.MutiOrgCon.forgMap.get(GI.forgid));
//		                dialog.dismiss();
//		            }
//		        });				
//				forgSelecter.show();
//			}			
//		});
//	}	
	
	
	// 复杂控件区域 开始
	protected StringBuilder genDateStringBuilder(int year, int monthOfYear,
			int dayOfMonth){
		StringBuilder tmpdate = new StringBuilder().append(year + "-")
				.append(String.format("%02d-", monthOfYear + 1)) // 这里月份要+1
				.append(String.format("%02d", dayOfMonth));
		return tmpdate;
	}
	
	protected Dialog onCreateDialog(TextView view,OnDateSetListener listener){
		String tmpdate = view.getText().toString();
		if(tmpdate==null||"".equals(tmpdate)){
			tmpdate=PbF.date2Str(new Date());
		}
		String[] tmpdateArr = tmpdate.split("-");
		int mYear = Integer.valueOf(tmpdateArr[0]);
		int mMonth = Integer.valueOf(tmpdateArr[1]) - 1; // 这里月份要-1
		int mDay = Integer.valueOf(tmpdateArr[2]);
		return new DatePickerDialog(getActivity(), listener, mYear,
				mMonth, mDay);
	}
	
	private DatePickerDialog.OnDateSetListener cwDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			if(dayOfMonth > 25){
				dayOfMonth = 1;
				monthOfYear += 1; 
			}
			if(monthOfYear == 12){
				year += 1;
			}
			StringBuilder tmpdate = new StringBuilder().append(year + "-")
					.append(String.format("%02d-", (monthOfYear + 1)%12)) // 这里月份要+1
					.append(String.format("%02d", dayOfMonth));
			fcwdate.setText(tmpdate);
		}

	};
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			StringBuilder tmpdate = new StringBuilder().append(year + "-")
					.append(String.format("%02d-", (monthOfYear + 1)%12)) // 这里月份要+1
					.append(String.format("%02d", dayOfMonth));
			fdate.setText(tmpdate);
		}

	};
	// 复杂控件区域 结束
	
	// click 事件区域 开始
	@OnClick(R.id.scan_date)
	public void showDatePicker() {
		// Toast.makeText(mActivity,"哈哈", Toast.LENGTH_SHORT).show();
		onCreateDialog(fdate,mDateSetListener).show();
	}
	@OnClick(R.id.scan_fcwdate)
	public void showCwDatePicker() {
		// Toast.makeText(mActivity,"哈哈", Toast.LENGTH_SHORT).show();
		onCreateDialog(fcwdate,cwDateSetListener).show();
	}
	@OnClick(R.id.head_btn_new)
	public void billNew() {
		GxhbActivity mActivity=(GxhbActivity) iGetActivity();
		mActivity.billNew();
	}
	
	@OnClick(R.id.head_btn_save)
	public void billSave() {
		wight2Data();
		if(!checker.billcheck()){
			return;
		}
		GxhbActivity mActivity=(GxhbActivity) iGetActivity();
		mActivity.billSave();
	}
	
	@OnClick(R.id.head_btn_submit)
	public void billSubmit(){
		wight2Data();
		if(!checker.billcheck()){
			return;
		}
		GxhbActivity mActivity=(GxhbActivity) iGetActivity();
		mActivity.billSubmit();
//		getActivity().finish();
//		Intent intent = new Intent();
//		intent.setClass(iGetActivity(), GxhbActivity.class);
	}
		
	public void disabledButton(){
		btn_save.setEnabled(false);
		btn_submit.setEnabled(false);
	}
	
	@OnClick(R.id.head_btn_billexit)
	public void billExit(){
		getActivity().finish();
	}
//	@OnClick(R.id.scan_dept)
//	public void deptSelect(){
//		Intent intent=new Intent();
//		intent.setClass(iGetActivity(), SearchItemActivity.class);
//		intent.putExtra("documentType", Constance.baseinfo_dept);
//		intent.putExtra("requestCode", Constance.requestCode_baseinfoDept);
//		this.startActivityForResult(intent, Constance.requestCode_baseinfoDept);
//	}
	
	
	// click 事件区域 结束
	
	//OnEditorAction 区域开始
//	@OnEditorAction(R.id.head_editText_源单编号)
//	public boolean decodeSrcBill(TextView v, int actionId, KeyEvent event){	
//		if("".equals(AZUIPub.text2String(v).trim())){
//			return false;
//		}
////		if(event!=null&&event.getAction()==KeyEvent.ACTION_UP){
//			wight2Data();
//			ManufactureInstockActivity mActivity=iGetActivity();				
//			mActivity.iGetSrcBill(AZUIPub.text2String(srcbillno).trim());
////		}
//		return false;
//	}
//	
//	@OnEditorAction(R.id.head_editText_生产车间)
//	public boolean deptDecode(TextView v, int actionId, KeyEvent event){
//		if(actionId==KeyEvent.ACTION_DOWN){		
//			Intent intent=new Intent();
//			intent.setClass(iGetActivity(), SearchItemActivity.class);				
//			intent.putExtra("selDecType", Constance.selDec_decode);
//			intent.putExtra("documentType", Constance.baseinfo_dept);
//			intent.putExtra("barcode",AZUIPub.text2String(fdeptName).trim());
//			intent.putExtra("requestCode", Constance.requestCode_baseinfoDept);
//			this.startActivityForResult(intent, Constance.requestCode_baseinfoDept);
//		}
//		return false;	
//	}
	
	//OnEditorAction 区域结束

	//OnKey 事件区域 开始
	void iSetOnKeyListener(){
		
	}
	//OnKey 事件区域 结束
	
	// 逻辑区域开始
	private void calcTotalQty(){
		BigDecimal qtySum=new BigDecimal(0);
		try{
			List<JrPdaGxhbbillentry> entrys=
					mActivity.getEntity().getEntrys();	
			for(int i=0;i<entrys.size();i++){
				JrPdaGxhbbillentry _tmpEntry=entrys.get(i);
				BigDecimal _qty=PbF.inzBDecimal(_tmpEntry.getFhgpqty());
				qtySum=qtySum.add(_qty);
			}
			
		}catch(NullPointerException e){
			System.out.println(e.getMessage());
			qtySum=new BigDecimal(0);
		}finally{
			//billhd.setFtotalqty(qtySum);
		}		
	}
	
	
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
//				if(StringUtils.isEmpty(billhd.getFauditerUserId())){
//					msg+="无验收人员信息。";
//				}
//				if(StringUtils.isEmpty(billhd.getFdeptId())){
//					msg+="无生产车间信息。";
//				}
				if(StringUtils.isEmpty(billhd.getFdeptId())
						){
					msg+=Constance.alert_scanInfoCheck;
				}
				GxhbActivity mActivity=(GxhbActivity) iGetActivity();
				int entryCount= mActivity.getEntity().getEntrys().size();
				if(entryCount==0){
					msg+=Constance.alert_EntityNull;
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
