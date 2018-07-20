package com.jxjr.p;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jxjr.contract.ABaseBillContract.IfBaseBillScanFrgView;
import com.jxjr.contract.CallbackContract.IfSrcDataCallbakMsg;
import com.jxjr.contract.CallbackContract.IfdecodeBarcodeCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeMatCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeTmCallbak;
import com.jxjr.contract.GxhbContract;
import com.jxjr.contract.GxhbContract.IfGxhbScanFrgView;
import com.jxjr.contract.SearchMatContract;
import com.jxjr.contract.GxhbContract.IfGxhbActivityView;
import com.jxjr.m.BaseMatNetWorkModel;
import com.jxjr.m.GxhbModel;
import com.jxjr.m.GxhbNetWorkModel;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrGxpgbillDTO;
import com.jxjr.m.DTO.JrGxhbBillDTO;

import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;

import com.jxjr.m.entity.JrPdaGxpgYgentry;
import com.jxjr.m.entity.JrPdaGxpgbill;
import com.jxjr.m.entity.JrPdaGxpgbillentry;
import com.jxjr.m.entity.JrPdaGxhbbill;
import com.jxjr.utility.Constance;
import com.jxjr.utility.PbF;

public class GxhbPresenter
		extends
		ABasePresenter<GxhbContract.IfGxhbActivityView, GxhbContract.IfGxhbModel> {

	public GxhbPresenter(IfGxhbActivityView view) {
		super(view);
		// TODO Auto-generated constructor stub
		this.model = new GxhbModel();
	}

	public void iGetsrcDTO(String srcbillno, final JrGxhbBillDTO entity) {
		view.showprocess("...");
		model.onloadsrcDTO(srcbillno, new IfSrcDataCallbakMsg<JrGxpgbillDTO>() {

			@Override
			public void popFailure(final String error) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						view.loadSrcfailure(Constance.srcflg_srcbill, error);
						view.hideprocess();
						view.srcCallAction(false);
					}
				});
			}

			@Override
			public void popSucess(String msg, final JrGxpgbillDTO srcDTO) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						srcInsertEntity(srcDTO, entity);
						view.hideprocess();
						view.srcCallAction(true);
					}
				});
			}
		});
	}

	private void srcInsertEntity(JrGxpgbillDTO srcDTO, JrGxhbBillDTO entity) {
		try {
			JrPdaGxpgbill srcHd = srcDTO.getBill();
			JrPdaGxhbbill hd = entity.getBill();
			hd.setFdeptId(PbF.inzStr(srcHd.getFdeptId()));
			hd.setFdeptName(PbF.inzStr(srcHd.getFdeptName()));
			hd.setFdeptNumber(PbF.inzStr(srcHd.getFdeptNumber()));
			hd.setFbzId(PbF.inzStr(srcHd.getFbzId()));
			hd.setFbzName(PbF.inzStr(srcHd.getFbzName()));
			hd.setFbzNumber(PbF.inzStr(srcHd.getFbzNumber()));
			// hd.setFtotalqty(srcHd.);
			// hd.setFsourcebillnumber(srcHd.getFnumber());
			// hd.setFsourcebilltype(srcHd.getFtranstype());

			List<JrPdaGxpgbillentry> srcEList = srcDTO.getEntrys();
			List<JrPdaGxpgYgentry> srcYgList = srcDTO.getYgs();

			Map<String, BigDecimal> mat_QtyMap = new HashMap<String, BigDecimal>();
			HashMap<String, HashMap<String, Object>> matEntryAttr = new HashMap<String, HashMap<String, Object>>();
			BigDecimal totalLeft = new BigDecimal(0);
			for (int i = 0; srcEList != null && i < srcEList.size(); i++) {
				JrPdaGxpgbillentry tmpSrcE = srcEList.get(i);
				// String fmaterialId=tmpSrcE.getFmatId();
				// if(mat_QtyMap.containsKey(fmaterialId)){
				// HashMap<String,Object>
				// _attrMap=matEntryAttr.get(fmaterialId);
				// BigDecimal _fauxQty=(BigDecimal)_attrMap.get("fauxQty");
				// BigDecimal
				// _fcommitQty=(BigDecimal)_attrMap.get("fcommitQty");
				// _fauxQty=_fauxQty.add(tmpSrcE.getFauxQty());
				// _fcommitQty=_fcommitQty.add(tmpSrcE.getFcommitQty());
				// _attrMap.put("fauxQty", _fauxQty);
				// _attrMap.put("fcommitQty", _fcommitQty);
				// matEntryAttr.put(fmaterialId,_attrMap);
				// BigDecimal sum=mat_QtyMap.get(fmaterialId)
				// .add(tmpSrcE.getFauxQty().subtract(tmpSrcE.getFcommitQty()));
				// mat_QtyMap.put(fmaterialId, sum);
				// }else{
				// HashMap<String,Object> _attrMap=new HashMap<String,Object>();
				// _attrMap.put("fauxQty", tmpSrcE.getFauxQty());
				// _attrMap.put("fcommitQty", tmpSrcE.getFcommitQty());
				// _attrMap.put("fmatNo", tmpSrcE.getFmaterialnumber());
				// _attrMap.put("fmatName", tmpSrcE.getFmaterialname());
				// _attrMap.put("fmatModel", tmpSrcE.getFmodel());
				// _attrMap.put("fQty", new BigDecimal(0));
				// // _attrMap.put("fsaleorderNumber",
				// tmpSrcE.getFsaleorderNumber());
				// // _attrMap.put("fsaleorderId", tmpSrcE.getFsaleorderId());
				// // _attrMap.put("fsaleorderEntryId;",
				// tmpSrcE.getFsaleorderEntryId());
				// // _attrMap.put("fsaleorderEntrySeq;",
				// tmpSrcE.getFsaleorderEntrySeq());
				//
				// matEntryAttr.put(fmaterialId, _attrMap);
				//
				// mat_QtyMap.put(fmaterialId,
				// tmpSrcE.getFauxQty().subtract(tmpSrcE.getFcommitQty()));
				// }
				// totalLeft=totalLeft.add(tmpSrcE.getFauxQty().subtract(tmpSrcE.getFcommitQty()));
			}
			view.iSetData(entity);
			view.refreshFragmentData();
			view.popScrData(totalLeft, mat_QtyMap, matEntryAttr, srcEList,
					srcYgList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 工序汇报单的扫描
	public void decodeGxBarcode(final IfGxhbScanFrgView _view, String para) {
		view.showprocess(Constance.ProcessStr.decoding);

		SearchMatContract.IfSearchMatModel itemModel = new GxhbNetWorkModel();
		itemModel.pushPara(para);

		itemModel.decodeBarcode2(new IfdecodeTmCallbak() {

			@Override
			public void popSucess(final JrGxpgbillDTO entity) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						((IfGxhbScanFrgView) _view).popDecodeBarcode(true, "",
								entity);
						
						view.hideprocess();
					}
				});
			}

			@Override
			public void popFailure(final String error) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						((IfGxhbScanFrgView) _view).popDecodeBarcode(false,
								error, null);
						
						view.hideprocess();
					}
				});
			}
		});
	}

	public void decodeQxBarcode(final IfGxhbScanFrgView _view, String para) {
		view.showprocess(Constance.ProcessStr.decoding);

		SearchMatContract.IfSearchMatModel itemModel = new GxhbNetWorkModel();
		itemModel.pushPara(para);
		
		itemModel.decodeBaseBarcode(new IfdecodeBarcodeCallbak() {
			@Override
			public void popSucess(final BaseInfoObjectDTO entity) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						((IfGxhbScanFrgView) _view).popDecodeQxcode(true, "",
								entity);
						view.hideprocess();
					}
				});
			}

			@Override
			public void popFailure(final String error) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						((IfGxhbScanFrgView) _view).popDecodeQxcode(false,
								error, null);
						view.hideprocess();
					}
				});
			}
			
		});
		}
	

}
