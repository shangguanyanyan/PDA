package com.jxjr.v.fragment;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnEditorAction;

import com.example.copyofhy.R;
import com.jxjr.contract.XsckContract.IfXsckScanFrgView;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrCpbzdjBillDTO;
import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.m.entity.JrPdaXsckbillentry;
import com.jxjr.utility.AZUIPub;
import com.jxjr.utility.BaseEntryDataChecker;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;
import com.jxjr.utility.PbF;
import com.jxjr.utility.StringUtils;
import com.jxjr.v.activity.SearchItemActivity;
import com.jxjr.v.activity.XsckActivity;
import com.jxjr.v.view.MEditText;

public class XsckScanFrg extends ABaseBillScanFrg<JrPdaXsckbillentry,XsckActivity> implements IfXsckScanFrgView{
	/**
	 * 历史版本=-3,变更中=-2,=-1,新增=0,保存=1,提交=2,
	 * 作废=3,审核=4,下达=5,冻结=6,关闭=7,
	 * 完工=8,完成=90,发布=10,结案=11
	 */
	int entrystatus= 0;
	public String _isqycw="0";
	public int index=0;
	public String _ftype="";
	public boolean matCodeType;		//true表示动态，false表示静态
	
	
	@BindView(R.id.scan_btn_save)
	Button btn_save;
	
	@BindView(R.id.scan_btn_delete)
	Button btn_delete;
	
	@BindView(R.id.scan_btn_reset)
	Button btn_reset;
	
	@BindView(R.id.scan_scanarea)
	MEditText barcode;
	
	@BindView(R.id.scan_Tm)	
	TextView ftm;
	
	@BindView(R.id.scan_MaterialNumber)	
	TextView fmatno;
	String mat_ftype;
	String mat_fparam;
	
	
	@BindView(R.id.scan_MaterialName)	
	TextView fmatname;
	
	@BindView(R.id.scan_MaterialMode)	
	TextView fgg;
	
	@BindView(R.id.scan_lot)	
	TextView flot;
	
	@BindView(R.id.scan_Qty)
	EditText fqty;
	BigDecimal oldfqty=new BigDecimal(0);	//用于修改的时候对比原来的数据
	
	@BindView(R.id.scan_StockName)
	TextView fck;
	
	@BindView(R.id.scan_SpName)
	TextView fsp;	
	
	AlertDialog.Builder alertDialog;
	
	DataChecker checker;
	
	HashMap<String,String> lastParaMap;
	
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	

	@Override
	protected int setLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_xsck_scan;
	}
    
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}


	
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mActivity=iGetActivity();
		initWidget();
		intiBaseFrg();
		lastParaMap=new HashMap<String,String>();
		intiEntry();		
		checker=new DataChecker(mActivity);
		alertDialog=new AlertDialog.Builder(iGetActivity(),AlertDialog.THEME_HOLO_DARK)
		.setPositiveButton("确定", null)
		.setTitle("警告");
		
	}

	@Override
	public void onStart(){
		super.onStart();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	
	@Override
	public void popDecodeBarcode(boolean sucflg, String msg,
			BaseInfoObjectDTO itemDTO) {
		// TODO Auto-generated method stub
		if(sucflg){
			if(itemDTO !=null)	{	
				if("B2".equals(_ftype)){
					theEntry.setFspId("");
					theEntry.setFspName("");
					theEntry.setFspNumber("");
					fsp.setText("");
					theEntry.setFstockId(itemDTO.getfItemID());
					theEntry.setFstockName(itemDTO.getfName());
					theEntry.setFstockNumber(itemDTO.getfNumber());
					fck.setText(PbF.inzStr(itemDTO.getfName()));
					_isqycw=itemDTO.getfParam();
				}
				else if("B3".equals(_ftype)){					
					theEntry.setFspId(itemDTO.getfItemID());
					theEntry.setFspName(itemDTO.getfName());
					theEntry.setFspNumber(itemDTO.getfNumber());
					fsp.setText(PbF.inzStr(itemDTO.getfName()));
					
				}			
											
			}
						
		}else{
			alertDialog.setMessage(msg);
			alertDialog.show();
			return;
		}
		
	}
	
//	@Override
//	public void popDecodeMatBarcode(boolean sucflg, String msg,
//			JrMaterialBaseInfoDTO itemDTO) {
//		// TODO Auto-generated method stub
//		if(sucflg){
//			if(itemDTO !=null)	{										
//				theEntry.setFmatId(itemDTO.getFid());
//				theEntry.setFmatModel(itemDTO.getFmodel());
//				theEntry.setFmatName(itemDTO.getFname());
//				theEntry.setFmatNumber(itemDTO.getFnumber());
//				theEntry.setFbatchNo(itemDTO.getFlot());
//				theEntry.setFjysqdNo(mActivity.getEntity().getBill().getFsourcebillno());
//				theEntry.setFjysqdId(mActivity.getEntity().getBill().getFsourcebillid());
//
//				
//				fmatno.setText(PbF.inzStr(itemDTO.getFnumber()));
//				fmatname.setText(PbF.inzStr(itemDTO.getFname()));
//				fgg.setText(PbF.inzStr(itemDTO.getFmodel()));
//				flot.setText(PbF.inzStr(itemDTO.getFlot()));
//				fqty.setEnabled(true);
//							
//			}
//						
//		}else{
//			alertDialog.setMessage(msg);
//			alertDialog.show();
//			return;
//		}
//		
//	}
	
	@Override
	public void popDecodeThBarcode(boolean sucflg, String msg,
			JrCpbzdjBillDTO itemDTO) {
		// TODO Auto-generated method stub
		if(sucflg){
			if(itemDTO !=null)	{	
				theEntry.setFtm(itemDTO.getEntry().get(0).getFtm());
				theEntry.setFdytm(itemDTO.getEntry().get(0).getFdytm());
				theEntry.setFmatId(itemDTO.getBill().getFmatId());
				theEntry.setFmatModel(itemDTO.getBill().getFmatModel());
				theEntry.setFmatName(itemDTO.getBill().getFmatName());
				theEntry.setFmatNumber(itemDTO.getBill().getFmatNumber());
				theEntry.setFqty(itemDTO.getEntry().get(0).getFjs());
				theEntry.setFbatchNo(itemDTO.getBill().getFlot());
				theEntry.setFstockId(itemDTO.getEntry().get(0).getFstockId());
				theEntry.setFstockName(itemDTO.getEntry().get(0).getFstockName());
				theEntry.setFstockNumber(itemDTO.getEntry().get(0).getFstockNumber());
				theEntry.setFspId(itemDTO.getEntry().get(0).getFspId());
				theEntry.setFspName(itemDTO.getEntry().get(0).getFspName());
				theEntry.setFspNumber(itemDTO.getEntry().get(0).getFspNumber());

				ftm.setText(PbF.inzStr(itemDTO.getEntry().get(0).getFtm()));
				fmatno.setText(PbF.inzStr(itemDTO.getBill().getFmatNumber()));
				fmatname.setText(PbF.inzStr(itemDTO.getBill().getFmatName()));
				fgg.setText(PbF.inzStr(itemDTO.getBill().getFmatModel()));
				flot.setText(PbF.inzStr(itemDTO.getBill().getFlot()));
				fck.setText(PbF.inzStr(itemDTO.getEntry().get(0).getFstockName()));
				fsp.setText(PbF.inzStr(itemDTO.getEntry().get(0).getFspName()));
				fqty.setText(PbF.number2StrFormat(itemDTO.getEntry().get(0).getFjs()));			
				fqty.setEnabled(false);
							
			}
						
		}else{
			alertDialog.setMessage(msg);
			alertDialog.show();
			return;
		}
		
	}
	
	@Override  
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);  
		if (isVisibleToUser) {  
			barcode.requestFocus();
		} else {  
		       //相当于Fragment的onPause  
		} 
	}
	
	
	
//	@Override
//	public void popDecodeMatSucess(JrMaterialBaseInfoDTO itemDTO) {
//		// TODO Auto-generated method stub
//		mat_ftype=itemDTO.getFtype();
//		mat_fparam=itemDTO.getFparam();
//		super.popDecodeMatSucess(itemDTO);
//		barcode.setText(PbF.inzStr(itemDTO.getFbarCode()));
//		if(!StringUtils.isEmpty(itemDTO.getFwarehouseId())){
//			fstockName.setText(PbF.inzStr(itemDTO.getFwarehouseName()));
//		}
//		if(!StringUtils.isEmpty(itemDTO.getFlocationId())){
//			fsp.setText(PbF.inzStr(itemDTO.getFlocationName()));
//		}
//	}
//
//


	@Override
	protected void flocationCallback(String _fid, String _fname,
			String _fnumber) {
		// TODO Auto-generated method stub
		super.flocationCallback(_fid, _fname, _fnumber);
			mActivity.getPresenter().decodeWareHouseBYID(this
					, _fid, Constance.requestCode_baseinfoStock);
	}
	
	
	@Override
	protected void changeFlocationName(String _fname, boolean success) {
		// TODO Auto-generated method stub
		super.changeFlocationName(_fname, success);
		if(success){
			fsp.setText(_fname);
			AZUIPub.viewHiddenInput(mActivity,fsp);
			fsp.clearFocus();
		}else{
			fsp.setText("");
			fsp.requestFocus();
		}	
	}


	protected XsckActivity iGetActivity() {
		// TODO Auto-generated method stub
		return (XsckActivity) getActivity();
	}
	
	void initWidget(){
		barcode.setOnTouchListener(AZUIPub.noInputToucher);

		fsp.setOnTouchListener(AZUIPub.noInputToucher);
		fck.setOnTouchListener(AZUIPub.noInputToucher);
		barcode.requestFocus();
	}
	
	void data2Wight(){	
		ftm.setText(PbF.inzStr(theEntry.getFtm()));
		fmatno.setText(PbF.inzStr(theEntry.getFmatNumber()));
		fmatname.setText(PbF.inzStr(theEntry.getFmatName()));
		fgg.setText(PbF.inzStr(theEntry.getFmatModel()));
		flot.setText(PbF.inzStr(theEntry.getFbatchNo()));
		fqty.setText(PbF.number2StrFormat(theEntry.getFqty()));
		fck.setText(PbF.inzStr(theEntry.getFstockName()));
		fsp.setText(PbF.inzStr(theEntry.getFspName()));
	}
	boolean ifDataLoaded(){
		if(entrystatus==0){			
			theEntry=new JrPdaXsckbillentry();
			intiEntry();
			return true;
		}else{
			XsckActivity mActivity=(XsckActivity) getActivity();
			if(mActivity.getEntity()==null
					||mActivity.getEntity().getEntry()==null
					||mActivity.getEntity().getEntry().get(index)==null){
				return false;
			}
			theEntry=(JrPdaXsckbillentry) mActivity.getEntity().getEntry().get(index).clone();
			this.oldfqty=theEntry.getFqty();
			if(theEntry==null){
				return false;				
			}
			if(!StringUtils.isEmpty(theEntry.getFspId())){
				_isqycw="1";
			}
			
			
//			Boolean showsp=mActivity.warehouseid2showLocal(theEntry.getFstockId());
//			this.showLocation(showsp);
			return true;
		}
	}
	void wight2Data(){
		theEntry.setFtm(AZUIPub.text2String(ftm));
		theEntry.setFmatNumber(AZUIPub.text2String(fmatno));
		theEntry.setFmatName(AZUIPub.text2String(fmatname));
		theEntry.setFmatModel(AZUIPub.text2String(fgg));
		theEntry.setFbatchNo(AZUIPub.text2String(flot));
		theEntry.setFqty(AZUIPub.text2BigDcm(fqty));
		theEntry.setFstockName(AZUIPub.text2String(fck));
		theEntry.setFspName(AZUIPub.text2String(fsp));
	}
	
	//修改当前Fragment的
	void adjustWight(){//根据数据修改界面
		switch(entrystatus){
			case 0:{//新增
				this.btn_delete.setVisibility(View.INVISIBLE);
				this.btn_reset.setVisibility(View.VISIBLE);	
				break;
			}
			case 1:{//修改
				this.btn_delete.setVisibility(View.VISIBLE);
				this.btn_reset.setVisibility(View.INVISIBLE);	
				break;
			}
			default:
				break;
		}
	}
	
//	//用来改变库位是否能输入
//	void showLocation(Boolean isShow){
//		if(isShow){
//			fsp.setEnabled(isShow);
//			fsp.setFocusable(isShow);
//			fsp.setFocusableInTouchMode(isShow);
//			((TextView)getView().findViewById(R.id.searcher_spName)).setEnabled(isShow);
//		}else{
//			theEntry.setFlocationid("");
//			theEntry.setFlocationname("");
//			fsp.setEnabled(isShow);
//			fsp.setFocusable(isShow);
//			fsp.setFocusableInTouchMode(isShow);
//			fsp.setText("");
//			((TextView)getView().findViewById(R.id.searcher_spName)).setEnabled(isShow);
//		}
//	}
	
	// 新增时初始参数
	protected void intiEntry(){
		this.entrystatus=0;
		//this._isqycw="0";
		theEntry=new JrPdaXsckbillentry();
		theEntry.setFqty(new BigDecimal(defaultQty));
		if(!lastParaMap.isEmpty()){
			popLastPara(); //取上一次的值
		}
		fqty.setEnabled(true);
	}
	
	//保存本次分录一部分参数
	void pushLastPara(){
		lastParaMap.put("fwarehouseId", theEntry.getFstockId());
		lastParaMap.put("fwarehouseName", theEntry.getFstockName());
		lastParaMap.put("flocationId", theEntry.getFspId());
		lastParaMap.put("flocationName", theEntry.getFspName());
	}
	
	//取出上次分录一部分参数
	void popLastPara(){
		theEntry.setFstockId(lastParaMap.get("fwarehouseId"));
		theEntry.setFstockName(lastParaMap.get("fwarehouseName"));
		fck.setText(PbF.inzStr(lastParaMap.get("fwarehouseName")));
		theEntry.setFspId(lastParaMap.get("flocationId"));
		theEntry.setFspName(lastParaMap.get("flocationName"));
		fsp.setText(PbF.inzStr(lastParaMap.get("flocationName")));
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
	
	public void scan_new(){
		this.index=-1;
		data2Wight();
	}	
	
	public void refreshFragment(){
		ifDataLoaded();
		data2Wight();
		adjustWight();
	}
	
	public void iGetFirstFocus(){
		barcode.requestFocus();
	}
	
	// click 事件区域 开始
	@OnClick(R.id.scan_btn_new)
	public void toNew(){
		if(!checker.unSaveCheck()){
			AlertDialog unCheckDialog=new AlertDialog.Builder(getActivity(),AlertDialog.THEME_HOLO_DARK)
			.setTitle(Constance.alert_警告)
			.setMessage(Constance.alert_新增提醒)
			.setPositiveButton("确定", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					intiEntry();
					entrystatus= 0;
					refreshFragment();
					adjustWight();
					iGetFirstFocus();
				}
				
			})
			.setNegativeButton("取消",null)
			.show();				
		}else{
			intiEntry();
			entrystatus= 0;
			refreshFragment();
			adjustWight();
			iGetFirstFocus();
		}
	}

	
	@OnClick(R.id.scan_btn_save)
	public void scan_save(){
		try{
//			this.oldfqty=theEntry.getFqty();
			wight2Data();
			if(!checker.billcheck()){
				return;
			}

			//这里才是包含保存操作的地方
			//checker.qtyCheck(_matID,theEntry.getFqty());
			mActivity.changeEntryList(index,theEntry,entrystatus);
			mActivity.getTab0().fhjxx.setText("合计"+mActivity.getEntity().getEntry().size()+"条信息,总数量"+sumqty());
			intiEntry();
			entrystatus= 0;
			_isqycw="0";
			refreshFragment();
			barcode.setText("");
			iGetFirstFocus();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public BigDecimal sumqty(){
		BigDecimal fsumqty=new BigDecimal("0.0");
		for(int i=0;i<mActivity.getEntity().getEntry().size();i++){
			fsumqty=fsumqty.add(mActivity.getEntity().getEntry().get(i).getFqty());
		}
		return fsumqty;
	}
	
	
	@OnClick(R.id.scan_btn_delete)
	public void scan_delete(){
		this.oldfqty=new BigDecimal(0);
		scan_deleteInner(theEntry.getFqty());
	}
	
	public void scan_deleteInner(BigDecimal _fqty){
		mActivity.deleteEntry(index, entrystatus);	
		mActivity.getTab0().fhjxx.setText("合计"+mActivity.getEntity().getEntry().size()+"条信息,总数量"+sumqty());
		intiEntry();
		entrystatus= 0;
		index=0;
		refreshFragment();
		barcode.setText("");
		iGetFirstFocus();
	}
	
	void delete_calc(BigDecimal subQty){	
		
//		mActivity.setMat_exceedQtyMap(mat_exceedQtyMap);
//		mActivity.setMat_hasQtyMap(mat_hasQtyMap);
		//checker.removeCode(theEntry.getFbarcode());

	}
	
	@OnClick(R.id.scan_btn_reset)
	public void scan_reset(){
		new AlertDialog.Builder(getActivity(),AlertDialog.THEME_HOLO_DARK)
		.setTitle(Constance.alert_提示)
		.setMessage(Constance.alert_SureReset)
		.setPositiveButton("确定", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				intiEntry();
				refreshFragment();
				iGetFirstFocus();
			}
		})
		.setNegativeButton("取消",null)
		.show();	
			
	}
	
	
	// click 事件区域 结束

	
	
	@OnEditorAction(R.id.scan_scanarea)
	public boolean decodeMat(TextView v, int actionId, KeyEvent event){		
		if("".equals(AZUIPub.text2String(v).trim())){
			return false;
		}
		if(!checker.srcbillCheck(mActivity.getEntity().getBill().getFfhtzdNo())){
			return false;
		}
		String _barcode=AZUIPub.text2String(v).trim();
		String [] fcode = _barcode.split("/");
		_ftype = fcode[0];
		if("B2".equals(_ftype)){
			mActivity.getPresenter().decodeGxBarcode(this, _barcode);
		}
		else if("B3".equals(_ftype)){
			if(StringUtils.isEmpty(theEntry.getFstockName())){
				alertDialog.setMessage(Constance.check_emptyWarehouse).show();
			}else{
				if("0".equals(_isqycw)){
					fsp.setText("");
					alertDialog.setMessage("仓库未启用仓位,扫描仓位无效。");
					alertDialog.show();
				}else{
					mActivity.getPresenter().decodeSpBarcode(this, _barcode,theEntry.getFstockNumber());
				}			
			}		
		}
		else if("D2".equals(_ftype)){
			for(int i=0;i<mActivity.getEntity().getEntry().size();i++){
				if(_barcode.equals(mActivity.getEntity().getEntry().get(i).getFdytm())){
					alertDialog.setMessage("该条码已扫描过，不允许重复扫描！").show();
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
			}
			fmatno.setText("");
			fmatname.setText("");
			fgg.setText("");
			flot.setText("");
			mActivity.getPresenter().decodeThBarcode(this, _barcode,mActivity.getEntity().getBill().getFfhtzdNo());
		}
		else{
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
//		materialModel.pushPara(_barcode);
//		super.decodeBaseInfoInner(_barcode, Constance.requestCode_baseinfoMaterial);

		return false;
	}	
	
// 接口区域	结束
	class DataChecker extends BaseEntryDataChecker{	
		Boolean enable=true;// 是否需要校验
		
		boolean _bool=false;
		
		public DataChecker(Context context){
			super(context);
		}

		public boolean matCheck(String matid){
			if(!enable) return enable;
			try{
				if(!mActivity.getMatEntryAttr().containsKey(matid)){
					alertDialog.setMessage(Constance.check_SrcBillNoMatExist).show();
					//Toast.makeText(mActivity, "源单上并无此物料", Toast.LENGTH_SHORT).show();
					return false;
				}else{
					return true;
				}
			}catch(Exception e){
				alertDialog.setMessage(Constance.check_SrcBillNoMatExist).show();
				//Toast.makeText(mActivity, "源单上并无此物料", Toast.LENGTH_SHORT).show();
				return false;
			}
		}
		
		public boolean srcbillCheck(String srcbillno){
			try{
				if(StringUtils.isEmpty(srcbillno)){
					alertDialog.setMessage(Constance.check_emptySrcbillno).show();
					return false;
				}else{
					return true;
				}
			}catch(Exception e){
				alertDialog.setMessage(Constance.check_emptySrcbillno).show();
				//Toast.makeText(mActivity, "源单上并无此物料", Toast.LENGTH_SHORT).show();
				return false;
			}
		}
				

		public boolean unSaveCheck(){
			if(!enable) return enable;
//			if(true) return false;
			if(entrystatus==1){
				return true;
			}else if(entrystatus==0){
				try{
					if(theEntry==null)
						return true;
					if(!StringUtils.isEmpty(theEntry.getFmatId())
							||!StringUtils.isEmpty(theEntry.getFstockName())){
						return false;
					}else{
						return true;
					}					
				}catch(Exception e){
					return false;
				}	
			}
			return true;
		}
		
		//校验分录的必填项
		public boolean billcheck(){
			String msg="";
			try{
				if(StringUtils.isEmpty(theEntry.getFmatId())||StringUtils.isEmpty(theEntry.getFstockName())){
					msg+=Constance.alert_scanInfoCheck;
				}else if(theEntry.getFqty().doubleValue()<0.000000001){
					msg+=Constance.alert_ZeroQty;
				}else if(_isqycw.equals("1")&&StringUtils.isEmpty(theEntry.getFspId())){
					msg+=Constance.check_emptyLocation;
				}
//				else if(StringUtils.isEmpty(theEntry.getFbzwNumber())&&"大桶".equals(theEntry.getFmatModel().toString())){
//					msg+=Constance.alert_桶号必录;
//				}
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
