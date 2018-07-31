package com.jxjr.contract;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jxjr.contract.ABaseBillContract.IfBaseBillHdFrgView;
import com.jxjr.contract.ABaseBillContract.IfBaseBillScanFrgView;
import com.jxjr.contract.ABaseBillContract.IfBasebillActivityView;
import com.jxjr.contract.ABaseBillContract.IfBasebillModel;
import com.jxjr.contract.CallbackContract.IfSrcDataCallbakMsg;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrGxpgbillDTO;
import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.m.entity.JrPdaGxpgYgentry;
import com.jxjr.m.entity.JrPdaGxpgbillentry;

public interface GxhbContract {
	interface IfGxhbActivityView extends IfBasebillActivityView{
		public void srcCallAction(boolean flg);
		public void popScrData(BigDecimal totalLeft
				,Map<String,BigDecimal> mat_QtyMap
				,HashMap<String,HashMap<String,Object>> matEntryAttr
				,List<JrPdaGxpgbillentry> srcEList,List<JrPdaGxpgYgentry> srcYgList);
	}

	interface IfGxhbModel extends IfBasebillModel{
		public void onloadsrcDTO(String srcbillno,IfSrcDataCallbakMsg<JrGxpgbillDTO> callbak);
	}
	interface IfGxhbScanFrgView extends IfBaseBillScanFrgView{
		public void popDecodeBarcode(boolean sucflg,String msg,JrGxpgbillDTO itemDTO);
		public void popDecodeQxcode(boolean sucflg,String msg,BaseInfoObjectDTO itemDTO);
	}
	interface IfGxhbHdFrgView extends IfBaseBillHdFrgView{
//		public Map<String, String> getHdMap();
//		public void setHdMap(Map<String, String> hdMap);
//		public void popBaseInfoItemByForeignID(int requestCode,final BaseInfoObjectDTO entity);
	}
}
