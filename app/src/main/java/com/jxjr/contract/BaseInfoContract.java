package com.jxjr.contract;

import java.util.List;
import java.util.Map;

import android.content.Context;

import com.jxjr.contract.CallbackContract.IfBaseInfoDecodeCallbak;
import com.jxjr.contract.CallbackContract.IfBaseInfoQueryCallbak;
import com.jxjr.m.pojo.BaseInfoObjectPOJO;

public interface BaseInfoContract {
	interface IfBaseInfoView{
		public void showprocess();
		public void hideprocess();
		public void loadDatafailure(String error);
		public void loadDataSucess();
//		public void refreshList(List<String[]> display);
		public void popQueryList(List <Map<String,Object>> queryList);
	}
	interface  IfBaseInfoModel{
		public void igetOutData(Context context,String dbname,IfBaseInfoQueryCallbak callback);
		//public void pushData(List<Map<String,String[]>> data);
		public void pushPara(String condition,String fItemClass);
		public void decodeBarcode(Context context,IfBaseInfoDecodeCallbak callback);
		public void pushPara(String condition, String fItemClass, String fParam);
		public void igetWarehouseList(Context context, String dbname,
				IfBaseInfoQueryCallbak callback);
		void pushForgid(String _forgid);
		Map<String, String> warehouseid2Paras(Context context,
				String warehouseid);
	}
}
