package com.jxjr.contract;

import java.util.Map;

import com.jxjr.contract.CallbackContract.IfLoadDataCallbak;
import com.jxjr.contract.CallbackContract.IfSaveCallbak;
import com.jxjr.contract.CallbackContract.IfSubmitCallbak;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;

public interface ABaseBillContract {

	public interface IfBasebillActivityView{
		public void showprocess();
		public void showprocess(String message);
		public void hideprocess();
		public void loadDatafailure();
		public void loadDatafailure(String error);
		public void intiBillfailure();
		public void intiBillSucess(String message);
		public void loadDataSucess();
		public void loadSrcSucess(int flg,String message);
		public void loadSrcfailure(int flg,String error);
		public void callbackSucess(String message);
		public void submitcallbackSucess(String message);
		public <T> void iSetData(T entity);
		public void refreshFragmentData();	
	}
	public interface IfBasebillModel{
		public void igetData(IfLoadDataCallbak callback);
		public void igetData(IfLoadDataCallbak callback,int id);
		public <T> void iSetData(T entity);
		public void onSava(IfSaveCallbak callback);
		public void onSubmit(IfSubmitCallbak callback);
	}
//	
//	public interface AIfBaseBillHdFrg{
//		public void refreshFragment();
//	}
	
	public interface IfBaseBillScanFrgView{
		public void refreshFragment();
		public void popBaseInfoItem(int requestCode,BaseInfoObjectDTO ItemDTO);
		public void loadItemfailure(int requestCode,String error);
		public void popDecodeLiteInfo(Map<String,Object> itemMap,int requestCode);
		public void decodeLiteSucess(String msg);
		public void decodeLiteFailure(String error,int requestCode);
		public void popDecodeMatSucess(JrMaterialBaseInfoDTO itemDTO);
		public void popDecodeMatFailure(String error);
		public void popBaseInfoItemByForeignID(int requestCode,BaseInfoObjectDTO ItemDTO);
	}
	public interface IfBaseBillHdFrgView{
		public void refreshFragment();
		public void popBaseInfoItem(int requestCode,BaseInfoObjectDTO ItemDTO);
		public void loadItemfailure(int requestCode,String error);		
		public void popDecodeLiteInfo(Map<String,Object> itemMap,int requestCode);
		public void decodeLiteSucess(String msg);
		public void decodeLiteFailure(String error,int requestCode);
	}
}
