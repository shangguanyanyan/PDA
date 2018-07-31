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
import com.jxjr.m.DTO.JrYlqdBillDTO;

public interface ScllContract {
	interface IfScllActivityView extends IfBasebillActivityView{
		public void srcCallAction(boolean flg);
		
	}

	interface IfScllModel extends IfBasebillModel{
		public void onloadsrcDTO(String srcbillno,IfListCallbak ifListCallbak);
	}
	interface IfScllScanFrgView extends IfBaseBillScanFrgView{
		public void popDecodeBarcode(boolean sucflg,String msg,BaseInfoObjectDTO itemDTO);
		public void popDecodeMatBarcode(boolean sucflg,String msg,JrMaterialBaseInfoDTO itemDTO);
	}
	interface IfScllHdFrgView extends IfBaseBillHdFrgView{
		public void popDecodeSrcbill(boolean sucflg,String msg,JrYlqdBillDTO itemDTO);
//		public Map<String, String> getHdMap();
//		public void setHdMap(Map<String, String> hdMap);
//		public void popBaseInfoItemByForeignID(int requestCode,final BaseInfoObjectDTO entity);
	}
}
