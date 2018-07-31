package com.jxjr.contract;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jxjr.contract.ABaseBillContract.IfBaseBillHdFrgView;
import com.jxjr.contract.ABaseBillContract.IfBaseBillScanFrgView;
import com.jxjr.contract.ABaseBillContract.IfBasebillActivityView;
import com.jxjr.contract.ABaseBillContract.IfBasebillModel;
import com.jxjr.contract.CallbackContract.IfListCallbak;
import com.jxjr.contract.CallbackContract.IfSrcDataCallbakMsg;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrCpbzdjBillDTO;
import com.jxjr.m.DTO.JrGxpgbillDTO;
import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.m.DTO.JrXsckSrcBillDTO;

public interface CprkContract {
	interface IfCprkActivityView extends IfBasebillActivityView{
		public void srcCallAction(boolean flg);
		
	}

	interface IfCprkModel extends IfBasebillModel{
		public void onloadsrcDTO(String srcbillno,IfListCallbak ifListCallbak);
	}
	interface IfCprkScanFrgView extends IfBaseBillScanFrgView{
		public void popDecodeBarcode(boolean sucflg,String msg,BaseInfoObjectDTO itemDTO);
		public void popDecodeThBarcode(boolean sucflg,String msg,JrCpbzdjBillDTO itemDTO);
	}
	interface IfCprkHdFrgView extends IfBaseBillHdFrgView{
		public void popDecodeSrcbill(boolean sucflg,String msg,JrCpbzdjBillDTO itemDTO);
//		public Map<String, String> getHdMap();
//		public void setHdMap(Map<String, String> hdMap);
//		public void popBaseInfoItemByForeignID(int requestCode,final BaseInfoObjectDTO entity);
	}
}
