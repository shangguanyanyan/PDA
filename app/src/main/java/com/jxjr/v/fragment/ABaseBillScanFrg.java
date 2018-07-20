package com.jxjr.v.fragment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.ButterKnife;

import com.example.copyofhy.R;
import com.jxjr.contract.ABaseBillContract;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.utility.AZUIPub;
import com.jxjr.utility.Constance;
import com.jxjr.utility.StringUtils;

public abstract class ABaseBillScanFrg<E,A extends FragmentActivity> 
				extends Fragment implements ABaseBillContract.IfBaseBillScanFrgView{

	Double defaultQty=0.0;
	
	
	int entrystatus= 0;
	
	public int index=0;	
	
	protected E theEntry=(E)new Object();
	
	protected A mActivity;
	
	protected HashMap<String,String> lastParaMap=new HashMap<String,String>();
	
	protected HashMap<Integer,Boolean> localtionEnableMap=new HashMap<Integer,Boolean>();
	
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)  
    {  
    	View view =inflater.inflate(setLayoutID(), 
    				container, false);  
      
		ButterKnife.bind(this, view);
		
		return view;	
    } 
	
    protected abstract int setLayoutID();
    
	protected void intiBaseFrg(){
		lastParaMap=new HashMap<String,String>();
	}
	
	
	protected abstract void intiEntry();
	public abstract void refreshFragment();
	public abstract void iGetFirstFocus();
	protected abstract A iGetActivity();
	
	public void disabledScanArea(){
		EditText scanArea=(EditText)getView().findViewById(R.id.scan_ScanArea);
		scanArea.setEnabled(false);
	}
	
	
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
		switch (requestCode){	
			default:
				break;
			case Constance.requestCode_baseinfoStock:{
				fwarehouseCallback(_fid,_fname,_fnumber);
				break;
			}
			case Constance.requestCode_baseinfoSp:{
				flocationCallback(_fid,_fname,_fnumber);
				break;
			}
			case Constance.requestCode_baseinfoInStock:{
				finwarehouseCallback(_fid,_fname,_fnumber);
				break;
			}
			case Constance.requestCode_baseinfoInSp:{
				finlocationCallback(_fid,_fname,_fnumber);
				break;
			}
			case Constance.requestCode_baseinfoOutStock:{
				foutwarehouseCallback(_fid,_fname,_fnumber);
				break;
			}
			case Constance.requestCode_baseinfoOutSp:{
				foutlocationCallback(_fid,_fname,_fnumber);
				break;
			}
			case Constance.requestCode_baseinfoWlgs:{
				fwlgsCallback(_fid,_fname,_fnumber);
				break;
			}
			case Constance.requestCode_baseinfoAuditer:{
				fauditerCallback(_fid,_fname,_fnumber);
				break;
			}
		}		
	}


	@Override
	public void popBaseInfoItemByForeignID(int requestCode, BaseInfoObjectDTO ItemDTO) {
		// TODO Auto-generated method stub
		final String _fid=String.valueOf(ItemDTO.getfItemID());
		final String _fname=ItemDTO.getfName();
		final String _fnumber=ItemDTO.getfNumber();
		String originalFid="";
		Class<?> clazz = theEntry.getClass();
		AlertDialog.Builder _dialog=new AlertDialog.Builder(mActivity)
						.setTitle(Constance.alert_提示)
						.setNegativeButton("取消", null);
		try {
			switch (requestCode){	
				default:
					break;
				case Constance.requestCode_baseinfoOutStock:{
					Method getFoutwarehouseid = clazz.getMethod("getFoutwarehouseid");
					originalFid=(String) getFoutwarehouseid.invoke(theEntry);
					if(StringUtils.isEmpty(originalFid)||_fid.equals(originalFid)){
						foutwarehouseCallback(_fid,_fname,_fnumber);
					}else{
						_dialog.setMessage(Constance.check_WareHouseNotMatch)
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								foutwarehouseCallback(_fid,_fname,_fnumber);
							}
						}).show();
					}
					break;
				}
				case Constance.requestCode_baseinfoInStock:{
					Method getFinwarehouseid = clazz.getMethod("getFinwarehouseid");
					originalFid=(String) getFinwarehouseid.invoke(theEntry);
					if(StringUtils.isEmpty(originalFid)||_fid.equals(originalFid)){
						finwarehouseCallback(_fid,_fname,_fnumber);
					}else{
						_dialog.setMessage(Constance.check_WareHouseNotMatch)
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								finwarehouseCallback(_fid,_fname,_fnumber);
							}
						}).show();
					}
					break;
				}
				case Constance.requestCode_baseinfoStock:{
					Method getFwarehouseid = clazz.getMethod("getFwarehouseid");
					originalFid=(String) getFwarehouseid.invoke(theEntry);
					if(StringUtils.isEmpty(originalFid)||_fid.equals(originalFid)){
						fwarehouseCallback(_fid,_fname,_fnumber);
					}else{
						_dialog.setMessage(Constance.check_WareHouseNotMatch)
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								fwarehouseCallback(_fid,_fname,_fnumber);
							}
						}).show();
					}
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	

	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		String _fname = "";
		String _fid = "";
		String _fnumber = "";
		if (resultCode != 0 && intent!=null) {
			_fname = intent.getStringExtra("fname");
			_fid = intent.getStringExtra("fid");
			_fnumber = intent.getStringExtra("fnumber");
		}else if(intent==null){
			return;
		}
		switch (resultCode) {
			default:
				break;
			case Constance.requestCode_baseinfoMaterial:{
				if("".equals(_fid)){
					changeFmaterialInfo("","","","","",
							"",(double)0,"", "0",(double)0,(double)0, false);
					break;
				}
				String _fmodel=intent.getStringExtra("fmodel");	
				String _funitId=intent.getStringExtra("funitId");
				String _funitName=intent.getStringExtra("funitName");
				String _funitConvsRate=intent.getStringExtra("funitConvsRate");
				String _fassistUnitId=intent.getStringExtra("fassistUnitId");
				String _fassistName=intent.getStringExtra("fassistName");
				String _fassistUnitConvsRate=intent.getStringExtra("fassistUnitConvsRate");
				String _fbaseUnitId=intent.getStringExtra("fbaseUnitId");
				String _fbaseUnitName=intent.getStringExtra("fbaseUnitName");
				String _flot=intent.getStringExtra("flot");
				String _fbarcode=intent.getStringExtra("fbarcode");
				Double _fqty=intent.getDoubleExtra("fqty", 0);
				Double _fbaseqty=intent.getDoubleExtra("fbaseqty",0);
				Double _fassistqty=intent.getDoubleExtra("fassistqty",0);
				fmaterialCallback(_fid, _fname, _fnumber,
						_fmodel,_funitId,_funitName,_funitConvsRate
						,_fassistUnitId,_fassistName,_fassistUnitConvsRate
						,_fbaseUnitId,_fbaseUnitName,_flot
						,_fbarcode,_fqty,_fbaseqty,_fassistqty);
				changeFmaterialInfo(_fid,_fnumber,_fname,_fmodel,_flot,
						_fbaseUnitName,_fqty,_funitName,_funitConvsRate
						,_fbaseqty,_fassistqty,true);
				break;
			}
			case Constance.requestCode_baseinfoStock: {
				fwarehouseCallback(_fid, _fname, _fnumber);
				break;
			}
			case Constance.requestCode_baseinfoSp: {
				flocationCallback(_fid, _fname, _fnumber);
				break;
			}
			case Constance.requestCode_baseinfoInStock: {
				finwarehouseCallback(_fid, _fname, _fnumber);
				break;
			}
			case Constance.requestCode_baseinfoInSp: {
				finlocationCallback(_fid, _fname, _fnumber);
				break;
			}
			case Constance.requestCode_baseinfoOutStock: {
				foutwarehouseCallback(_fid, _fname, _fnumber);
				break;
			}
			case Constance.requestCode_baseinfoOutSp: {
				foutlocationCallback(_fid, _fname, _fnumber);
				break;
			}
			case Constance.requestCode_baseinfoWlgs: {
				fwlgsCallback(_fid, _fname, _fnumber);
				break;
			}
			case Constance.requestCode_baseinfoAuditer: {
				fauditerCallback(_fid, _fname, _fnumber);
				break;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.fb.contract.ABaseBillContract.IfBaseBillScanFrgView#loadItemfailure(int, java.lang.String)
	 */
	@Override
	public void loadItemfailure(int requestCode, String error) {
		// TODO Auto-generated method stub
		AZUIPub.showMessage(iGetActivity(), Constance.show_Dialog, error);
//		Toast.makeText(iGetActivity(), error, Toast.LENGTH_SHORT).show();
		switch (requestCode){	
			default:
				break;
			case Constance.requestCode_baseinfoStock:{
				changeFwarehouseName("",false);
				break;
			}
			case Constance.requestCode_baseinfoSp:{
				changeFlocationName("",false);
				break;
			}
			case Constance.requestCode_baseinfoInStock:{
				changeFinwarehouseName("",false);
				break;
			}
			case Constance.requestCode_baseinfoInSp:{
				changeFinlocationName("",false);
				break;
			}
			case Constance.requestCode_baseinfoOutStock:{
				changeFoutwarehouseName("",false);
				break;
			}
			case Constance.requestCode_baseinfoOutSp:{
				changeFoutlocationName("",false);
				break;
			}
			case Constance.requestCode_baseinfoWlgs:{
				changeFwlgsName("",false);
				break;
			}
			case Constance.requestCode_baseinfoAuditer:{
				changeFauditerName("",false);
				break;
			}
		}
	}
	
	/**
	 * 新·物料回调函数给控件的赋值建议在子类中覆盖
	 * @author	Mr.Shen
	 * @date	2017-08-10 
	 */
	protected void fmaterialCallback(JrMaterialBaseInfoDTO itemDTO, boolean success) {
		try {
			Class<?> clazz = theEntry.getClass();	
			if(success){//回调成功
				String fmaterialId=itemDTO.getFid();
				String fmaterialName=itemDTO.getFname();
				String fmaterialNumber=itemDTO.getFnumber();
				String _fmodel=itemDTO.getFmodel();
				String _funitId=itemDTO.getFunitId();
				String _funitName=itemDTO.getFunitName();
				String _funitConvsRate=itemDTO.getFunitConvsRate();
				String _fbaseUnitId=itemDTO.getFbaseUnitId();
				String _fbaseUnitName=itemDTO.getFbaseUnitName();
				String _flot=itemDTO.getFlot();
				String _fbarcode=itemDTO.getFbarCode();					
				
				Method setFmaterialid = clazz.getMethod("setFmaterialid",
						String.class);
				setFmaterialid.invoke(theEntry, fmaterialId);
				Method setFmaterialname = clazz.getMethod("setFmaterialname",
						String.class);
				setFmaterialname.invoke(theEntry, fmaterialName);
				Method setFmaterialnumber = clazz.getMethod("setFmaterialnumber",
						String.class);
				setFmaterialnumber.invoke(theEntry, fmaterialNumber);
				Method setFmaterialmodel = clazz.getMethod("setFmaterialmodel",
						String.class);
				setFmaterialmodel.invoke(theEntry, _fmodel);
				Method setFunitid = clazz.getMethod("setFunitid",
						String.class);
				setFunitid.invoke(theEntry, _funitId);
				Method setFunitname = clazz.getMethod("setFunitname",
						String.class);
				setFunitname.invoke(theEntry, _funitName);
				Method setFunitconvsrate = clazz.getMethod("setFunitconvsrate",
						String.class);
				setFunitconvsrate.invoke(theEntry, _funitConvsRate);
				Method setFbaseUnitId = clazz.getMethod("setFbaseunitid",
						String.class);
				setFbaseUnitId.invoke(theEntry, _fbaseUnitId);			
				Method setFbaseUnitName = clazz.getMethod("setFbaseunitname",
						String.class);
				setFbaseUnitName.invoke(theEntry, _fbaseUnitName);
				Method setFlot = clazz.getMethod("setFlot",
						String.class);
				setFlot.invoke(theEntry, _flot);
				Method setFbarcode = clazz.getMethod("setFbarcode",
						String.class);
				setFbarcode.invoke(theEntry, _fbarcode);
				
				if(itemDTO.getFqty()==null){//标重
					itemDTO.setFqty(new BigDecimal(defaultQty.toString()));
				}
				if(itemDTO.getFbaseqty()==null){//净重
					itemDTO.setFbaseqty(new BigDecimal(defaultQty.toString()));
				}
				if(itemDTO.getFunitConvsRate()==null){//辅助数量
					itemDTO.setFunitConvsRate("0");
				}
				
			}else{	//扫码失败
				Method setFmaterialid = clazz.getMethod("setFmaterialid",
						String.class);
				setFmaterialid.invoke(theEntry, Constance.nullStr);
				Method setFbaseUnitId = clazz.getMethod("setFbaseunitid",
						String.class);
				setFbaseUnitId.invoke(theEntry, Constance.nullStr);
				Method setFbarcode = clazz.getMethod("setFbarcode",
						String.class);
				setFbarcode.invoke(theEntry, Constance.nullStr);
			}
		} catch (Exception e) {
			AZUIPub.showMessage(iGetActivity(), 
					Constance.show_Dialog,e.getMessage());
		}
	}
	
	protected void fmaterialCallback(String fmaterialId, String fmaterialName, String fmaterialNumber,
			String _fmodel, String _funitId, String _funitName,
			String _funitConvsRate, String _fassistUnitId, String _fassistName,
			String _fassistUnitConvsRate, String _fbaseUnitId,
			String _fbaseUnitName, String _flot, String _fbarcode,
			Double _fqty,Double _fbaseqty,Double _fassistqty) {
		// TODO Auto-generated method stub
		Class<?> clazz = theEntry.getClass();		
		try {
			Method setFmaterialid = clazz.getMethod("setFmaterialid",
					String.class);
			setFmaterialid.invoke(theEntry, fmaterialId);
			Method setFmaterialname = clazz.getMethod("setFmaterialname",
					String.class);
			setFmaterialname.invoke(theEntry, fmaterialName);
			Method setFmaterialnumber = clazz.getMethod("setFmaterialnumber",
					String.class);
			setFmaterialnumber.invoke(theEntry, fmaterialNumber);
			Method setFmaterialmodel = clazz.getMethod("setFmaterialmodel",
					String.class);
			setFmaterialmodel.invoke(theEntry, _fmodel);
			Method setFunitid = clazz.getMethod("setFunitid",
					String.class);
			setFunitid.invoke(theEntry, _funitId);
			Method setFunitname = clazz.getMethod("setFunitname",
					String.class);
			setFunitname.invoke(theEntry, _funitName);
			Method setFunitconvsrate = clazz.getMethod("setFunitconvsrate",
					String.class);
			setFunitconvsrate.invoke(theEntry, _funitConvsRate);
			Method setFassistUnitId = clazz.getMethod("setFassistunitid",
					String.class);
			setFassistUnitId.invoke(theEntry, _fassistUnitId);
			Method setFassistUnitName = clazz.getMethod("setFassistunitname",
					String.class);
			setFassistUnitName.invoke(theEntry, _fassistName);
			Method setFassistUnitConvsRate = clazz.getMethod("setFassistunitconvsrate",
					String.class);
			setFassistUnitConvsRate.invoke(theEntry, _fassistUnitConvsRate);
			Method setFbaseUnitId = clazz.getMethod("setFbaseunitid",
					String.class);
			setFbaseUnitId.invoke(theEntry, _fbaseUnitId);			
			Method setFbaseUnitName = clazz.getMethod("setFbaseunitname",
					String.class);
			setFbaseUnitName.invoke(theEntry, _fbaseUnitName);
			Method setFlot = clazz.getMethod("setFlot",
					String.class);
			setFlot.invoke(theEntry, _flot);
			Method setFbarcode = clazz.getMethod("setFbarcode",
					String.class);
			setFbarcode.invoke(theEntry, _fbarcode);
			
			changeFmaterialInfo(fmaterialId,fmaterialNumber,fmaterialName
				,_fmodel,_flot,_fbaseUnitName,_fqty,_funitName,_funitConvsRate
				,_fbaseqty,_fassistqty,true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *旧的回调方式 
	 */
	protected void changeFmaterialInfo(String fmaterialId,String fmaterialNumber,
			String fmaterialName, String fmodel, String flot,
			String fbaseUnitName, Double fqty, 
			String funitName, String funitConvsRate,Double fbaseqty,Double fassistqty, boolean success) {
	}

	protected void fwarehouseCallback(String _fid, String _fname,
			String _fnumber) {
		Class<?> clazz = theEntry.getClass();
		try {
			Method setFwarehouseId = clazz.getMethod("setFwarehouseid",String.class);
			setFwarehouseId.invoke(theEntry,_fid);
			Method setFwarehouseName = clazz.getMethod("setFwarehousename",String.class);
			setFwarehouseName.invoke(theEntry,_fname);		
			
			changeFwarehouseName(_fname,true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void changeFwarehouseName(String _fname,boolean success){		
	}
	
	protected void changeFgxName(String _fname,boolean success){		
	}
	
	protected void changeFqxyy(String _fname,boolean success){		
	}
	
	protected void flocationCallback(String _fid,String _fname,String _fnumber){
		Class<?> clazz=theEntry.getClass();
		try {
			Method setFlocationId = clazz.getMethod("setFlocationid",String.class);
			setFlocationId.invoke(theEntry,_fid);
			Method setFlocationName = clazz.getMethod("setFlocationname",String.class);
			setFlocationName.invoke(theEntry,_fname);	
			
			changeFlocationName(_fname,true);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	protected void changeFlocationName(String _fname,boolean success){
	}
	
	protected void finwarehouseCallback(String _fid,String _fname,String _fnumber){
		Class<?> clazz=theEntry.getClass();
		try {
			Method setFinwarehouseId = clazz.getMethod("setFinwarehouseid",String.class);
			setFinwarehouseId.invoke(theEntry,_fid);
			Method setFinwarehouseName = clazz.getMethod("setFinwarehousename",String.class);
			setFinwarehouseName.invoke(theEntry,_fname);	
			
			changeFinwarehouseName(_fname,true);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	protected void changeFinwarehouseName(String _fname,boolean success){
	}
	
	protected void finlocationCallback(String _fid,String _fname,String _fnumber){
		Class<?> clazz=theEntry.getClass();
		try {
			Method setFinlocationId = clazz.getMethod("setFinlocationid",String.class);
			setFinlocationId.invoke(theEntry,_fid);
			Method setFinlocationName = clazz.getMethod("setFinlocationname",String.class);
			setFinlocationName.invoke(theEntry,_fname);	
			
			changeFinlocationName(_fname,true);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	protected void changeFinlocationName(String _fname,boolean success){
	}
	
	protected void foutwarehouseCallback(String _fid,String _fname,String _fnumber){
		Class<?> clazz=theEntry.getClass();
		try {
			Method setFoutwarehouseId = clazz.getMethod("setFoutwarehouseid",String.class);
			setFoutwarehouseId.invoke(theEntry,_fid);
			Method setFoutwarehouseName = clazz.getMethod("setFoutwarehousename",String.class);
			setFoutwarehouseName.invoke(theEntry,_fname);	
			
			changeFoutwarehouseName(_fname,true);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	protected void changeFoutwarehouseName(String _fname,boolean success){
	}
	
	protected void foutlocationCallback(String _fid,String _fname,String _fnumber){
		Class<?> clazz=theEntry.getClass();
		try {
			Method setFoutlocationId = clazz.getMethod("setFoutlocationid",String.class);
			setFoutlocationId.invoke(theEntry,_fid);
			Method setFoutlocationName = clazz.getMethod("setFoutlocationname",String.class);
			setFoutlocationName.invoke(theEntry,_fname);	
			
			changeFoutlocationName(_fname,true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void changeFoutlocationName(String _fname,boolean success){
	}

	protected void fwlgsCallback(String _fid,String _fname,String _fnumber){
		//物流公司
		Class<?> clazz=theEntry.getClass();
		try {
			Method setFwlgsid = clazz.getMethod("setFwlgsid",String.class);
			setFwlgsid.invoke(theEntry,_fid);
			Method setFwlgsiName = clazz.getMethod("setFwlgsiname",String.class);
			setFwlgsiName.invoke(theEntry,_fname);	
			
			changeFwlgsName(_fname,true);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	protected void changeFwlgsName(String _fname,boolean success){		
	}
	
	protected void fauditerCallback(String _fid,String _fname,String _fnumber){
		Class<?> clazz=theEntry.getClass();
		try {
			Method setFauditerUserId = clazz.getMethod("setFauditeruserid",String.class);
			setFauditerUserId.invoke(theEntry,_fid);
			Method setFauditerUserName = clazz.getMethod("setFauditerusername",String.class);
			setFauditerUserName.invoke(theEntry,_fname);	
			
			changeFauditerName(_fname,true);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	protected void changeFauditerName(String _fname,boolean success){		
	}
	
	/* (non-Javadoc)
	 * @see com.fb.contract.ABaseBillContract.IfBaseBillScanFrgView#popDecodeLiteInfo(java.util.Map, int)
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
				fwarehouseCallback(_fid,_fname,_fnumber);
				break;
			}
			case Constance.requestCode_baseinfoOutStock: {
				foutwarehouseCallback(_fid, _fname, _fnumber);
				break;
			}
			case Constance.requestCode_baseinfoInStock: {
				finwarehouseCallback(_fid, _fname, _fnumber);
				break;
			}
			case Constance.requestCode_baseinfoWlgs: {
				fwlgsCallback(_fid, _fname, _fnumber);
				break;
			}
		}

	}

	/* (non-Javadoc)
	 * @see com.fb.contract.ABaseBillContract.IfBaseBillScanFrgView#decodeLiteSucess(java.lang.String)
	 */
	@Override
	public void decodeLiteSucess(String msg) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.fb.contract.ABaseBillContract.IfBaseBillScanFrgView#decodeLiteFailure(java.lang.String, int)
	 */
	@Override
	public void decodeLiteFailure(String error, int requestCode) {
		// TODO Auto-generated method stub
		AZUIPub.showMessage(iGetActivity(), Constance.show_Dialog, error);
//		Toast.makeText(iGetActivity(), error, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void popDecodeMatSucess(JrMaterialBaseInfoDTO itemDTO) {
		// TODO Auto-generated method stub
		
		fmaterialCallback(itemDTO,true);
/*		
 * 封存原因：旧的代码，不合理
 * 日期：2017-08-11
		String _fid = itemDTO.getFid();
		String _fnumber = itemDTO.getFnumber();
		String _fname = itemDTO.getFname();
		String _fmodel = itemDTO.getFmodel();
		String _funitId = itemDTO.getFunitId();
		String _funitName = itemDTO.getFunitName();
		String _funitConvsRate = itemDTO.getFunitConvsRate();
		String _fassistUnitId = itemDTO.getFassistUnitId();
		String _fassistName = itemDTO.getFassistName();
		String _fassistUnitConvsRate = itemDTO.getFassistUnitConvsRate();
		String _fbaseUnitId = itemDTO.getFbaseUnitId();
		String _fbaseUnitName = itemDTO.getFbaseUnitName();
		String _flot = itemDTO.getFlot();
		String _fbarcode = itemDTO.getFbarCode();
		Double _fqty = defaultQty;
		if(itemDTO.getFqty()!=null){
			_fqty = itemDTO.getFqty().doubleValue();
		}
		Double _fbaseqty = defaultQty;
		if(itemDTO.getFbaseqty()!=null){
			_fbaseqty = itemDTO.getFbaseqty().doubleValue();
		}
		Double _fassistqty= defaultQty;
		if(itemDTO.getFassistqty()!=null){
			_fassistqty = itemDTO.getFassistqty().doubleValue();
		}
		fmaterialCallback(_fid, _fname, _fnumber, _fmodel, _funitId, 
				_funitName, _funitConvsRate, _fassistUnitId, _fassistName,
				_fassistUnitConvsRate, _fbaseUnitId, _fbaseUnitName, 
				_flot, _fbarcode, _fqty,_fbaseqty,_fassistqty);
	*/
	}

	@Override
	public void popDecodeMatFailure(String error) {
		// TODO Auto-generated method stub
		AZUIPub.showMessage(iGetActivity(), Constance.show_Dialog, error);
		fmaterialCallback(null, false);
//		Toast.makeText(iGetActivity(), error, Toast.LENGTH_SHORT).show();
//		changeFmaterialInfo("","","","","","",defaultQty,"", "0",defaultQty,defaultQty, false);
	}	
	
	//基础资料获取区域 结束
}
