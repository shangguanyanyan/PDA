package com.jxjr.v.activity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.copyofhy.R;
import com.jxjr.contract.CprkContract.IfCprkActivityView;

import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrCpbzdjBillDTO;
import com.jxjr.m.DTO.JrCprkBillDTO;
import com.jxjr.m.entity.JrPdaCpbzdjbill;
import com.jxjr.m.entity.JrPdaCpbzdjbillentry;
import com.jxjr.m.entity.JrPdaCprkbill;
import com.jxjr.m.entity.JrPdaCprkbillentry;
import com.jxjr.p.CprkPresenter;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;

import com.jxjr.v.fragment.CprkEntryFrg;
import com.jxjr.v.fragment.CprkHdFrg;
import com.jxjr.v.fragment.CprkScanFrg;
import com.jxjr.v.fragment.CprkSummaryFrg;
import com.jxjr.v.fragment.OffLineCatchFrg;

public class CprkActivity extends
			ABaseFTBillActivity<JrCprkBillDTO, CprkPresenter> implements
			IfCprkActivityView,CprkEntryFrg.OnChangeScanIndexListener {
	
	//Boolean editable=true;
	
	CprkHdFrg tab0;
	CprkSummaryFrg tab1;
	CprkEntryFrg tab2;
	CprkScanFrg tab3;
	OffLineCatchFrg tab4;
	
	Map<String,HashMap<String,Object>> matEntryAttr=new LinkedHashMap<String,HashMap<String,Object>>();
//	HashMap<String,BigDecimal> mat_exceedQtyMap=new HashMap<String,BigDecimal>();	//超发/数量
//	HashMap<String,BigDecimal> mat_hasQtyMap=new HashMap<String,BigDecimal>();		//已领/发数量

	public Map<String, HashMap<String, Object>> getMatEntryAttr() {
		return matEntryAttr;
	}

	public void setMatEntryAttr(
			HashMap<String, HashMap<String, Object>> matEntryAttr) {
		this.matEntryAttr = matEntryAttr;
	}


	public CprkHdFrg getTab0() {
		return tab0;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//GI.forgid=GI.SESSION.getForgid();
    	initWidget();
    	intiData();
	}
	
	private void initWidget(){
		super.intiPageViewer();
		
	}

	@Override
	protected void addFragments() {
		// TODO Auto-generated method stub
		
		tab0=new CprkHdFrg();
		tab1=new CprkSummaryFrg();
		tab2=new CprkEntryFrg();
		tab3=new CprkScanFrg();
		tab4 = new OffLineCatchFrg();
		
		mFragments.add(tab0);
		mFragments.add(tab1);
		mFragments.add(tab2);
		mFragments.add(tab3);
		mFragments.add(tab4);
	}

	@Override
	protected void intiData() {
		// TODO Auto-generated method stub
		billdocument_title.setText(getString(R.string.menu_cprk));		
		entity=new JrCprkBillDTO();
		entity.setBill(new JrPdaCprkbill());
		entity.setEntry(new ArrayList<JrPdaCprkbillentry>());
		presenter=new CprkPresenter(this);
		presenter.setHd_view(tab0);
		
		presenter.setScan_view(tab3);
		//presenter.refreshingData(id);
	}
	
	@Override  
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		switch (resultCode) {
			case Constance.requestCode_sundryMatInv:{
				changeTabByIndex(3);
				tab3.toNew();
				break;
			}
		}
	}
	
	
	//逻辑区域 开始
	
	@Override
	public void refreshFragmentData() {
		// TODO Auto-generated method stub
		tab0.refreshFragment();
		tab3.refreshFragment();
	}

	public void billNew(){
		this.finish();
		Intent intent = new Intent();
		intent.setClass(this, this.getClass());
		startActivity(intent);
	}
	
	@Override
	public void billSave() {
		// TODO Auto-generated method stub
		presenter.pushData(entity);
		presenter.onSave();
	}

	@Override
	public void billSubmit() {
		// TODO Auto-generated method stub
		presenter.pushData(entity);
		presenter.onSubmit();
	}

	@Override
	public void billAudit() {
		// TODO Auto-generated method stub
		
	}
	
	private void calcTotalQty(){
		BigDecimal _talQty=new BigDecimal(0);
		List<JrPdaCprkbillentry> tmpList=entity.getEntry();
		for(JrPdaCprkbillentry t_emp:tmpList){
			_talQty=_talQty.add(t_emp.getFqty());
		}
		JrPdaCprkbill _billhd=entity.getBill();
		_billhd.setFtotalqty(_talQty);
		entity.setBill(_billhd);
		tab0.refreshFragment();
	}
	
	public void deleteEntry(int index,int entrystatus){
		List<JrPdaCprkbillentry> tmp=entity.getEntry();
		try{
			if(entrystatus==1){
				tmp.remove(index);
			}			
		}catch(NullPointerException e){
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}catch(IndexOutOfBoundsException e){
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}finally{
			entity.setEntry(tmp);
			calcTotalQty();
		}		
	}
	
	public void changeEntryList(ArrayList<JrPdaCprkbillentry> entryItemList){		
		List<JrPdaCprkbillentry> tmpList=entity.getEntry();
		tmpList.addAll(entryItemList);	
		calcTotalQty();
	}
	
	public void changeEntryList(int i,JrPdaCprkbillentry entryItem,int entrystatus){
		List<JrPdaCprkbillentry> tmp=entity.getEntry();
		try{
			if(entrystatus==0){
				tmp.add(entryItem);
			}else{
				tmp.set(i, entryItem);
			}
		}catch(NullPointerException e){
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}catch(IndexOutOfBoundsException e){
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}finally{
			entity.setEntry(tmp);
			calcTotalQty();
		}
	}
	public void changeScanFrgEntrystatus(int entrystatus){
		tab3.setEntrystatus(entrystatus);
	}
	
	public void changeTabByIndex(int i){
		mViewPager.setCurrentItem(i);
	}
	
	public void changeScanFrgIndex(int i){
		tab3.setIndex(i);
		tab3.refreshFragment();
		tab3.iGetFirstFocus();
	}
	

	
	//逻辑区域 结束	

	@Override
	public void submitcallbackSucess(String message) {
		// TODO Auto-generated method stub
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		tab0.disabledButton();
	}

	/* (non-Javadoc)
	 * @see com.lf.v.fragment.SaleIssueEntryFrg.OnChangeScanIndexListener#iSetScanIndex(int)
	 */
	@Override
	public void iSetScanIndex(int index) {
		// TODO Auto-generated method stub
		changeScanFrgIndex(index);
		changeTabByIndex(3);
	}

	/* (non-Javadoc)
	 * @see com.lf.v.fragment.SaleIssueEntryFrg.OnChangeScanIndexListener#iSetScanEntrystatus(int)
	 */
	@Override
	public void iSetScanEntrystatus(int entrystatus) {
		// TODO Auto-generated method stub
		changeScanFrgEntrystatus(entrystatus);
	}

	
	@Override
	public void srcCallAction(boolean flg){
		//tab0.srcCallAction(flg);
	}

	@Override
	public void iSetYgScanIndex(int index) {
		// TODO Auto-generated method stub
		
	}

	//获得父类扫描得到的条码，进行解析并定位页面
		public void Distribution(String catchDecode){
			super.Distribution(catchDecode);
			String[] fcode = catchDecode.split("/");
			String ftype = fcode[0];
			if(ftype.equals("D2")){
				mViewPager.setCurrentItem(3,false);
				this.tab3.decodeMatByCode(fcode[0],catchDecode);
			}else{
				this.tab0.decodeSrcData(catchDecode);
			}
			
			Log.d("debug", catchDecode);
		}
}
