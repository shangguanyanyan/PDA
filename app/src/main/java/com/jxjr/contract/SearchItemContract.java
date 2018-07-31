package com.jxjr.contract;

import java.util.List;

import com.jxjr.contract.CallbackContract.IfSearchCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeBarcodeCallbak;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.m.entity.SearchItemEntity;

public interface SearchItemContract {

	interface IfSearchItemView{
		public void showprocess();
		public void hideprocess();
		public void loadDatafailure(String error);
		public void loadDataSucess();
		public void decodeFailure(String error);
		public void decodeSucess();
//		public void refreshList(List<String[]> display);
		public void popQueryList(List<BaseInfoObjectDTO> queryList);
		public void popEntity(BaseInfoObjectDTO entity);
		public void popForeignEntity(BaseInfoObjectDTO entity);
	}
	interface  IfSearchItemModel{
		public void igetOutData(IfSearchCallbak callback);
		public void decodeBarcode(IfdecodeBarcodeCallbak callback);
		public void decodeByForeignID(IfdecodeBarcodeCallbak callback);
		//public void pushData(List<Map<String,String[]>> data);
		public void pushPara(String condition,String fItemClass);
		public void pushPara(String condition, String parentcondition,
				String fItemClass);
		public void pushForgid(String forgid);
	}
}
