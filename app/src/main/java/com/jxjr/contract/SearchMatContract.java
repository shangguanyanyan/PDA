package com.jxjr.contract;

import java.util.List;

import com.jxjr.contract.CallbackContract.IfSearchMatCallbak;
import com.jxjr.contract.CallbackContract.IfSearchTmCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeBarcodeCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeBcprkCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeCprkSrcCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeMatCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeScllSrcCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeXsckSrcCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeThCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeTmCallbak;
import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;

public interface SearchMatContract {
	interface IfSearchMatView{
		public void showprocess();
		public void hideprocess();
		public void loadDatafailure(String error);
		public void loadDataSucess();
//		public void refreshList(List<String[]> display);
		public void popQueryList(List<JrMaterialBaseInfoDTO> queryList);
		public void popEntity(JrMaterialBaseInfoDTO entity);
		public void decodefailed(String error);
	}
	interface  IfSearchMatModel{
		public void igetOutData(IfSearchMatCallbak callback);
		public void decodeBarcode(IfdecodeMatCallbak callback);//物料
		public void decodeBarcode(String matType,IfdecodeMatCallbak callback);
		//public void pushData(List<Map<String,String[]>> data);
		public void pushPara(String condition);
		public void igetOutData(IfSearchTmCallbak callback);
		public void decodeBarcode2(IfdecodeTmCallbak ifdecodeTmCallbak);
		public void decodeBarcode2(String matType, IfdecodeTmCallbak callback);//箱条码
		public void decodeBaseBarcode(IfdecodeBarcodeCallbak callback);
	}
	interface  IfSearchCrkModel{
		public void pushPara(String condition);
		public void pushSrcBillNo(String srcBillNo);
		public void pushStockno(String stockno);
		public void decodeBaseBarcode(IfdecodeBarcodeCallbak callback);
		public void decodeMatBarcode(IfdecodeMatCallbak callback);
		public void decodeCprkBarcode(IfdecodeThCallbak callback);
		public void decodeXsckBarcode(IfdecodeThCallbak callback);
		public void decodeXsckSrcBarcode(IfdecodeXsckSrcCallbak callback);
		public void decodeCprkSrcBarcode(IfdecodeCprkSrcCallbak callback);
		public void decodeScllSrcBarcode(IfdecodeScllSrcCallbak callback);
		public void decodeBcprkBarcode(IfdecodeBcprkCallbak callback);
	}
}
