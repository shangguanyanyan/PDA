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
import com.jxjr.m.DTO.JrBcprkSrcBillDTO;

public interface BcprkContract {
	interface IfBcprkActivityView extends IfBasebillActivityView{
		public void srcCallAction(boolean flg);
		
	}

	interface IfBcprkModel extends IfBasebillModel{
		
	}
	interface IfBcprkScanFrgView extends IfBaseBillScanFrgView{
		public void popDecodeBarcode(boolean sucflg,String msg,BaseInfoObjectDTO itemDTO);
		public void popDecodeBcprkBarcode(boolean sucflg,String msg,JrBcprkSrcBillDTO itemDTO);
	}
	interface IfBcprkHdFrgView extends IfBaseBillHdFrgView{

	}
}
