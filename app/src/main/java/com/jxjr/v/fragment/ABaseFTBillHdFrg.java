package com.jxjr.v.fragment;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;

import com.example.copyofhy.R;
import com.jxjr.contract.ABaseBillContract.IfBaseBillHdFrgView;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.utility.Constance;
import com.jxjr.utility.PbF;
import com.jxjr.v.activity.ABaseFTBillActivity;

public abstract class ABaseFTBillHdFrg<E,A extends ABaseFTBillActivity> extends Fragment 
		implements IfBaseBillHdFrgView{


//	public interface AIfBaseBillHdFrg {
//		public void refreshFragment();
//	}
	@SuppressWarnings("unchecked")
	protected E billhd= (E)new Object();
	
//	@InjectView(R.id.head_btn_save)
//	Button btn_save;
//	
//	@InjectView(R.id.head_btn_submit)
//	Button btn_submit;
//	
//	Button btn_exit;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}	

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
	
	protected abstract int setLayoutID();

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

	}
	
	@Override
	public void onStart(){
		super.onStart();
//		btn_exit=(Button)getActivity().findViewById(R.id.head_btn_billexit);
//
//		btn_exit.setOnClickListener(new View.OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				((A)iGetActivity()).billExist();
//			}
//			
//		});
//		btn_save=(Button)(getView().findViewById(R.id.head_btn_save));
//		btn_submit=(Button)(getView().findViewById(R.id.head_btn_submit));  
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	@Override  
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);  
	}
	
	protected abstract void initWidget();
	
	protected abstract void initData();
	
	protected abstract void data2Wight();
	
	public abstract void wight2Data();
	
//	public abstract void refreshFragment();
	
	protected abstract FragmentActivity iGetActivity();
	
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
	
	// 复杂控件区域 结束
	
//	public void disabledButton(){
//		btn_save.setEnabled(false);
//		btn_submit.setEnabled(false);
//	}

	//基础资料获取区域
		/* (non-Javadoc)
		 * @see com.fb.contract.ABaseBillContract.IfBaseBillScanFrgView#popBaseInfoItem(int, com.fb.m.DTO.BaseInfoObjectDTO)
		 *	基础资料的回调函数
		 */
	@Override
	public void popBaseInfoItem(int requestCode,BaseInfoObjectDTO ItemDTO) {
		// TODO Auto-generated method stub			
		String _fid=String.valueOf(ItemDTO.getfItemID());
		String _fname=ItemDTO.getfName();
		String _fnumber=ItemDTO.getfNumber();
		String _fparam=ItemDTO.getfParam();
		switch (requestCode){	
			default:
				break;
			case Constance.requestCode_baseinfoWlgs:{
				fwlgsCallback(_fid,_fname,_fnumber);
				break;
			}
			case Constance.requestCode_baseinfoAuditer:{
				fauditerCallback(_fid,_fname,_fnumber);
				break;
			}
			case Constance.requestCode_baseinfoStocker:{
				fstockerCallback(_fid,_fname,_fnumber);
				break;
			}
			case Constance.requestCode_baseinfofHandler:{
				fhanderCallback(_fid,_fname,_fnumber);
				break;
			}
			case Constance.requestCode_baseinfoStock:{
				fwarehouseCallback(_fid,_fname,_fnumber,_fparam);
				break;
			}
			case Constance.requestCode_baseinfoSp:{
				flocationCallback(_fid,_fname,_fnumber);
				break;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.fb.contract.ABaseBillContract.IfBaseBillHdFrgView#loadItemfailure(int, java.lang.String)
	 */
	@Override
	public void loadItemfailure(int requestCode, String error) {
		// TODO Auto-generated method stub
		Toast.makeText(iGetActivity(), error, Toast.LENGTH_SHORT).show();
		switch (requestCode){	
			default:
				break;
			case Constance.requestCode_baseinfoWlgs:{
				changeFwlgsName("",false);
				break;
			}
			case Constance.requestCode_baseinfoAuditer:{
				changeFauditerName("",false);
				break;
			}
			case Constance.requestCode_baseinfoStocker:{
				changeFstockerUserName("",false);
				break;
			}
			case Constance.requestCode_baseinfofHandler:{
				changeFhandlerName("",false);
				break;
			}
			case Constance.requestCode_baseinfoDeliver:{
				
			}
			case Constance.requestCode_baseinfoStock:{
				changeFwarehouseName("","",false);
				break;
			}
			case Constance.requestCode_baseinfoSp:{
				changeFlocationName("",false);
				break;
			}
			
		}
	}	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		String _fname="";
		String _fid="";
		String _fnumber="";
		String _fparam="";
		if(resultCode!=0){
			_fname=intent.getStringExtra("fname");
			_fid=intent.getStringExtra("fid");
			_fnumber=intent.getStringExtra("fnumber");
		}		
		switch (resultCode) {	
			default:
				break;
			case Constance.requestCode_baseinfoWlgs:{
				fwlgsCallback(_fid,_fname,_fnumber);
				break;
			}
			case Constance.requestCode_baseinfoAuditer:{
				fauditerCallback(_fid,_fname,_fnumber);
				break;
			}
			case Constance.requestCode_baseinfoStocker:{
				fstockerCallback(_fid,_fname,_fnumber);
				break;
			}
			case Constance.requestCode_baseinfofHandler:{
				fhanderCallback(_fid,_fname,_fnumber);
				break;
			}
			case Constance.requestCode_baseinfoDeliver:{
				
			}
			case Constance.requestCode_baseinfoStock:{
				fwarehouseCallback(_fid,_fname,_fnumber,_fparam);
				break;
			}
			case Constance.requestCode_baseinfoSp:{
				flocationCallback(_fid,_fname,_fnumber);
				break;
			}
		}
	}
	
	
	protected void fwlgsCallback(String _fid,String _fname,String _fnumber){
		//物流公司
		Class<?> clazz=billhd.getClass();
		try {
			Method setFwlgsid = clazz.getMethod("setFwlgsid",String.class);
			setFwlgsid.invoke(billhd,_fid);
			Method setFwlgsiName = clazz.getMethod("setFwlgsiName",String.class);
			setFwlgsiName.invoke(billhd,_fname);	
			
			changeFwlgsName(_fname,true);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	protected void changeFwlgsName(String _fname,boolean success){		
	}
	
	protected void fauditerCallback(String _fid,String _fname,String _fnumber){
		Class<?> clazz=billhd.getClass();
		try {
			Method setFauditerUserId = clazz.getMethod("setFauditerUserId",String.class);
			setFauditerUserId.invoke(billhd,_fid);
			Method setFauditerUserName = clazz.getMethod("setFauditerUserName",String.class);
			setFauditerUserName.invoke(billhd,_fname);	
			
			changeFauditerName(_fname,true);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	protected void changeFauditerName(String _fname,boolean success){		
	}
	
	protected void fstockerCallback(String _fid,String _fname,String _fnumber){
		Class<?> clazz=billhd.getClass();
		try {
			Method setFstockerUserId = clazz.getMethod("setFstockerUserId",String.class);
			setFstockerUserId.invoke(billhd,_fid);
			Method setFstockerUserName = clazz.getMethod("setFstockerUserName",String.class);
			setFstockerUserName.invoke(billhd,_fname);	
			
			changeFstockerUserName(_fname,true);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	protected void changeFstockerUserName(String _fname,boolean success){		
	}
	
	protected void fhanderCallback(String _fid,String _fname,String _fnumber){
		Class<?> clazz=billhd.getClass();
		try {
			Method setFhandlerId = clazz.getMethod("setFhandlerId",String.class);
			setFhandlerId.invoke(billhd,_fid);
			Method setFhandlerName = clazz.getMethod("setFhandlerName",String.class);
			setFhandlerName.invoke(billhd,_fname);	
			
			changeFhandlerName(_fname,true);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	protected void changeFhandlerName(String _fname,boolean success){	
	}
	
	protected void fwarehouseCallback(String _fid,String _fname,String _fnumber,String _fparam){
		Class<?> clazz=billhd.getClass();
		try {
			Method setFwarehouseId = clazz.getMethod("setFwarehouseId",String.class);
			setFwarehouseId.invoke(billhd,_fid);
			Method setFwarehouseName = clazz.getMethod("setFwarehouseName",String.class);
			setFwarehouseName.invoke(billhd,_fname);		
			
			changeFwarehouseName(_fname,_fparam,true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void changeFwarehouseName(String _fname,String _fparam,boolean success){		
	}
	
	protected void fdeliverCallback(String _fid,String _fname,String _fnumber){
		Class<?> clazz=billhd.getClass();
		try {
			Method setFdeliverUserId = clazz.getMethod("setFdeliverUserId",String.class);
			setFdeliverUserId.invoke(billhd,_fid);
			Method setFdeliverUserName = clazz.getMethod("setFdeliverUserName",String.class);
			setFdeliverUserName.invoke(billhd,_fname);	
			
			changeFdeliverName(_fname,true);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	protected void changeFdeliverName(String _fname,boolean success){	
	}
	protected void flocationCallback(String _fid,String _fname,String _fnumber){
		Class<?> clazz=billhd.getClass();
		try {
			Method setFlocationId = clazz.getMethod("setFlocationId",String.class);
			setFlocationId.invoke(billhd,_fid);
			Method setFlocationName = clazz.getMethod("setFlocationName",String.class);
			setFlocationName.invoke(billhd,_fname);	
			
			changeFlocationName(_fname,true);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	protected void changeFlocationName(String _fname,boolean success){
	}
	
	/* (non-Javadoc)
	 * @see com.fb.contract.ABaseBillContract.IfBaseBillHdFrgView#popDecodeLiteInfo(java.util.Map, int)
	 */
	@Override
	public void popDecodeLiteInfo(Map<String, Object> itemMap, int requestCode) {
		// TODO Auto-generated method stub
		String _fid=(String)itemMap.get("fid");
		String _fnumber=(String)itemMap.get("fnumber");
		String _fname=(String)itemMap.get("fname");
		String _fparam=(String)itemMap.get("fparam");
		switch(requestCode){
			case Constance.requestCode_baseinfoStock:{
				fwarehouseCallback(_fid,_fname,_fnumber,_fparam);
				break;
			}
			case Constance.requestCode_baseinfoWlgs:{
				fwlgsCallback(_fid,_fname,_fnumber);
				break;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.fb.contract.ABaseBillContract.IfBaseBillHdFrgView#decodeLiteSucess(java.lang.String)
	 */
	@Override
	public void decodeLiteSucess(String msg) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.fb.contract.ABaseBillContract.IfBaseBillHdFrgView#decodeLiteFailure(java.lang.String, int)
	 */
	@Override
	public void decodeLiteFailure(String error, int requestCode) {
		// TODO Auto-generated method stub
		Toast.makeText(iGetActivity(), error, Toast.LENGTH_SHORT).show();
	}
	
//	public void onClick(View v) {
//	// TODO Auto-generated method stub
//	getActivity().finish();

	
	
	
}

