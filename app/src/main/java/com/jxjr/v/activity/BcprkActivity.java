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
import com.jxjr.contract.BcprkContract.IfBcprkActivityView;

import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrCpbzdjBillDTO;
import com.jxjr.m.DTO.JrBcprkBillDTO;
import com.jxjr.m.entity.JrPdaCpbzdjbill;
import com.jxjr.m.entity.JrPdaCpbzdjbillentry;
import com.jxjr.m.entity.JrPdaBcprkbill;
import com.jxjr.m.entity.JrPdaBcprkbillentry;
import com.jxjr.p.BcprkPresenter;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;

import com.jxjr.v.fragment.BcprkEntryFrg;
import com.jxjr.v.fragment.BcprkHdFrg;
import com.jxjr.v.fragment.BcprkScanFrg;
import com.jxjr.v.fragment.BcprkSummaryFrg;

public class BcprkActivity extends
			ABaseFTBillActivity<JrBcprkBillDTO, BcprkPresenter> implements
			IfBcprkActivityView,BcprkEntryFrg.OnChangeScanIndexListener {
	
	//Boolean editable=true;
	
	BcprkHdFrg tab0;
	BcprkSummaryFrg tab1;
	BcprkEntryFrg tab2;
	BcprkScanFrg tab3;
	
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


	public BcprkHdFrg getTab0() {
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
		
		tab0=new BcprkHdFrg();
		tab1=new BcprkSummaryFrg();
		tab2=new BcprkEntryFrg();
		tab3=new BcprkScanFrg();
		
		mFragments.add(tab0);
		mFragments.add(tab1);
		mFragments.add(tab2);
		mFragments.add(tab3);
	}

	@Override
	protected void intiData() {
		// TODO Auto-generated method stub
		billdocument_title.setText(getString(R.string.menu_bcprk));		
		entity=new JrBcprkBillDTO();
		entity.setBill(new JrPdaBcprkbill());
		entity.setEntry(new ArrayList<JrPdaBcprkbillentry>());
		presenter=new BcprkPresenter(this);
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
		List<JrPdaBcprkbillentry> tmpList=entity.getEntry();
		for(JrPdaBcprkbillentry t_emp:tmpList){
			_talQty=_talQty.add(t_emp.getFqty());
		}
		JrPdaBcprkbill _billhd=entity.getBill();
		_billhd.setFtotalqty(_talQty);
		entity.setBill(_billhd);
		tab0.refreshFragment();
	}
	
	public void deleteEntry(int index,int entrystatus){
		List<JrPdaBcprkbillentry> tmp=entity.getEntry();
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
	
	public void changeEntryList(ArrayList<JrPdaBcprkbillentry> entryItemList){		
		List<JrPdaBcprkbillentry> tmpList=entity.getEntry();
		tmpList.addAll(entryItemList);	
		calcTotalQty();
	}
	
	public void changeEntryList(int i,JrPdaBcprkbillentry entryItem,int entrystatus){
		List<JrPdaBcprkbillentry> tmp=entity.getEntry();
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
			mViewPager.setCurrentItem(3,false);
			this.tab3.decodeMatByCode(fcode[0],fcode[1]);
			Log.d("debug", catchDecode);
		}
	
	
}
