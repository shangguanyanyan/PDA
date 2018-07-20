package com.jxjr.v.activity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;

import com.example.copyofhy.R;
import com.jxjr.contract.GxhbContract.IfGxhbActivityView;
import com.jxjr.m.DTO.JrGxhbBillDTO;
import com.jxjr.m.entity.JrPdaGxhbYgentry;
import com.jxjr.m.entity.JrPdaGxpgYgentry;
import com.jxjr.m.entity.JrPdaGxpgbillentry;
import com.jxjr.m.pojo.BillCodePOJO;
import com.jxjr.m.pojo.BillCodePOJODao;
import com.jxjr.m.entity.JrPdaGxhbbill;
import com.jxjr.m.entity.JrPdaGxhbbillentry;
import com.jxjr.p.GxhbPresenter;
import com.jxjr.utility.AZUIPub;
import com.jxjr.utility.Constance;
import com.jxjr.v.fragment.GxhbEntryFrg;
import com.jxjr.v.fragment.GxhbHdFrg;
import com.jxjr.v.fragment.GxhbScanFrg;
import com.jxjr.v.fragment.GxhbYgFrg;
import com.jxjr.v.fragment.OffLineCatchFrg;
import com.jxjr.v.view.MEditText;

public class GxhbActivity extends
		ABaseBillActivity<JrGxhbBillDTO, GxhbPresenter> implements
		IfGxhbActivityView, GxhbEntryFrg.OnChangeScanIndexListener {

	GxhbHdFrg tab0 = new GxhbHdFrg();
	GxhbYgFrg tab1 = new GxhbYgFrg();
	GxhbEntryFrg tab2 = new GxhbEntryFrg();
	GxhbScanFrg tab3 = new GxhbScanFrg();
	OffLineCatchFrg tab4 = new OffLineCatchFrg();



	Map<String, BigDecimal> mat_QtyMap = new HashMap<String, BigDecimal>();
	Map<String, BigDecimal> mat_exceedQtyMap = new HashMap<String, BigDecimal>();
	BigDecimal totalLeft = new BigDecimal(0);
	HashMap<String, HashMap<String, Object>> matEntryAttr = new HashMap<String, HashMap<String, Object>>();
	List<JrPdaGxpgbillentry> srcEList = new ArrayList<JrPdaGxpgbillentry>();

	List<JrPdaGxpgYgentry> srcYgList = new ArrayList<JrPdaGxpgYgentry>();

	public Map<String, BigDecimal> getMat_QtyMap() {
		return mat_QtyMap;
	}

	public void setMat_QtyMap(Map<String, BigDecimal> mat_QtyMap) {
		this.mat_QtyMap = mat_QtyMap;
	}

	public Map<String, BigDecimal> getMat_exceedQtyMap() {
		return mat_exceedQtyMap;
	}

	public void setMat_exceedQtyMap(Map<String, BigDecimal> mat_exceedQtyMap) {
		this.mat_exceedQtyMap = mat_exceedQtyMap;
	}

	public HashMap<String, HashMap<String, Object>> getMatEntryAttr() {
		return matEntryAttr;
	}

	public void setMatEntryAttr(
			HashMap<String, HashMap<String, Object>> matEntryAttr) {
		this.matEntryAttr = matEntryAttr;
	}

	public List<JrPdaGxpgbillentry> getSrcEList() {
		return srcEList;
	}

	public void setSrcEList(List<JrPdaGxpgbillentry> srcEList) {
		this.srcEList = srcEList;
	}

	public List<JrPdaGxpgYgentry> getSrcYgList() {
		return srcYgList;
	}

	public void setSrcYgList(List<JrPdaGxpgYgentry> srcYgList) {
		this.srcYgList = srcYgList;
	}

	public GxhbHdFrg getTab0() {
		return tab0;
	}

	public GxhbYgFrg getTab1() {
		return tab1;
	}
	public GxhbScanFrg getTab3() {
		return tab3;
	}
	public void setTab1(GxhbYgFrg tab1) {
		this.tab1 = tab1;
	}
	
	public OffLineCatchFrg getTab4() {
		return tab4;
	}

	public void setTab4(OffLineCatchFrg tab4) {
		this.tab4 = tab4;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initWidget();
		intiData();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
	}

	@Override
	protected void intiData() {
		// TODO Auto-generated method stub
		billdocument_title.setText(getString(R.string.menu_gxhb));
		entity = new JrGxhbBillDTO();
		entity.setBill(new JrPdaGxhbbill());
		entity.setEntrys(new ArrayList<JrPdaGxhbbillentry>());
		entity.setYgEntry(new ArrayList<JrPdaGxhbYgentry>());
		presenter = new GxhbPresenter(this);
		presenter.setHd_view(tab0);

		presenter.setScan_view(tab3);
		presenter.refreshingData(id);	
	}

	private void initWidget() {
		super.intiPageViewer();
    	//
	}

	@Override
	protected void addFragments() {
		// TODO Auto-generated method stub
		mFragments.add(tab0);
		mFragments.add(tab1);
		mFragments.add(tab2);
		mFragments.add(tab3);
		mFragments.add(tab4);
		
	}

	@Override
	public void refreshFragmentData() {
		tab0.refreshFragment();
		tab3.refreshFragment();
	}

	public void billNew() {
		this.finish();
		Intent intent = new Intent();
		intent.setClass(this, this.getClass());
		startActivity(intent);
	}
//设置总数（目前使用其他办法得到总数）
	private void calcTotalQty() {
		BigDecimal _talQty = new BigDecimal(0);
		List<JrPdaGxhbbillentry> tmpList = entity.getEntrys();
		for (JrPdaGxhbbillentry t_emp : tmpList) {
			_talQty = _talQty.add(t_emp.getFhgpqty());
		}
		JrPdaGxhbbill _billhd = entity.getBill();
		// _billhd.setFtotalqty(_talQty);
		entity.setBill(_billhd);
		tab0.refreshFragment();
	}

	public void iGetSrcBill(String srcbillno) {
		presenter.iGetsrcDTO(srcbillno, entity);
	}

	public void deleteEntry(int index, int entrystatus) {
		List<JrPdaGxhbbillentry> tmp = entity.getEntrys();
		try {
			if (entrystatus == 1) {
				tmp.remove(index);
			}
		} catch (NullPointerException e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		} finally {
			entity.setEntrys(tmp);
			//这里刷新了单据页面
			//calcTotalQty();
			tab0.refreshFragment();
		}
	}

	public void deleteYgEntry(int index) {
		List<JrPdaGxhbYgentry> tmp = entity.getYgEntry();
		try {
			tmp.remove(index);
		} catch (NullPointerException e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		} finally {
			entity.setYgEntry(tmp);
		}
	}

	public void deleteEntry(int index) {
		List<JrPdaGxhbbillentry> tmp = entity.getEntrys();
		try {
			tmp.remove(index);
		} catch (NullPointerException e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		} finally {
			entity.setEntrys(tmp);
			calcTotalQty();
		}
	}

	public void changeEntryList(int i, JrPdaGxhbbillentry entryItem,
			int entrystatus) {
		List<JrPdaGxhbbillentry> tmp = entity.getEntrys();
		try {
			if (entrystatus == 0) {
				tmp.add(entryItem);
			} else {
				tmp.set(i, entryItem);
			}

		} catch (NullPointerException e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		} finally {
			entity.setEntrys(tmp);
			// calcTotalQty();
		}
	}

	public void changeYgEntryList(int i, JrPdaGxhbYgentry entryItem) {
		List<JrPdaGxhbYgentry> tmp = entity.getYgEntry();
		//将一个员工信息添加到list中
		try {
			tmp.add(entryItem);
			// tmp.set(i, entryItem);
		} catch (NullPointerException e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		} finally {
			entity.setYgEntry(tmp);
		}
	}

	public void changeEntryList(ArrayList<JrPdaGxhbbillentry> entryItemList) {
		List<JrPdaGxhbbillentry> tmpList = entity.getEntrys();
		tmpList.addAll(entryItemList);
		calcTotalQty();
	}

	public void changeScanFrgEntrystatus(int entrystatus) {
		tab3.setEntrystatus(entrystatus);
	}

	public void changeScanFrgIndex(int i) {
		tab3.setIndex(i);
		tab3.refreshFragment();
	}

	// 逻辑区域 结束

	@Override
	public void submitcallbackSucess(String message) {
		// TODO Auto-generated method stub
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		tab0.disabledButton();
	}

	// 抽象方法实现
	@Override
	public void iSetScanIndex(int index) {
		// TODO Auto-generated method stub
		changeScanFrgIndex(index);
		changeTabByIndex(3);
	}

	public void changeYgScanFrgIndex(int i) {
		tab1.setIndex(i);
		// tab1.refreshFragment();
	}

	// 抽象方法实现
	@Override
	public void iSetYgScanIndex(int index) {
		// TODO Auto-generated method stub
		changeYgScanFrgIndex(index);
		// changeTabByIndex(1);
	}

	@Override
	public void iSetScanEntrystatus(int entrystatus) {
		// TODO Auto-generated method stub
		changeScanFrgEntrystatus(entrystatus);
	}

	// @Override
	// public void disabledTwoButton() {
	// // TODO Auto-generated method stub
	// tab0.disabledButton();
	// }

	@Override
	public void srcCallAction(boolean flg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void popScrData(BigDecimal totalLeft,
			Map<String, BigDecimal> mat_QtyMap,
			HashMap<String, HashMap<String, Object>> matEntryAttr,
			List<JrPdaGxpgbillentry> srcEList, List<JrPdaGxpgYgentry> srcYgList) {
		// TODO Auto-generated method stub

	}

	// 抽象方法实现
	
	
	//获得父类扫描得到的条码，进行解析并定位页面
	public void Distribution(String catchDecode){
		super.Distribution(catchDecode);
		if(offline) {
			try {
				//执行离线存储操作
				String cont = catchDecode;
				billPojo = new BillCodePOJO();
				billPojo.setBillEntry(cont);
				List<BillCodePOJO> pojo = dao.queryBuilder().where(BillCodePOJODao.Properties.BillEntry.eq(cont)).list();
				if(pojo.size() == 0) {
					dao.insert(billPojo);
					mViewPager.setCurrentItem(4,false);
					tab4.refreshFragment();
				}else{
					AlertDialog dialog = new AlertDialog.Builder(this)
							.setTitle("提示")
							.setMessage("已存在条码："+cont)
							.setPositiveButton("确定", new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									dialog.dismiss();
								}
							}).create();
					dialog.show();
				}
				
			}catch (Exception e) {
				// TODO: handle exception
				Log.e("存入", "存入出错");
			}
			return;
		}else {
			mViewPager.setCurrentItem(3,false);
			this.tab3.decodeMatByCode(catchDecode);
		}
		
		Log.d("debug", catchDecode);
	}
	

}
