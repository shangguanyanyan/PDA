package com.jxjr.p;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jxjr.contract.CallbackContract.IfSrcDataCallbakMsg;
import com.jxjr.contract.CallbackContract.IfdecodeBarcodeCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeMatCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeThCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeXsckSrcCallbak;
import com.jxjr.contract.XsckContract.IfXsckScanFrgView;
import com.jxjr.contract.SearchMatContract;
import com.jxjr.contract.XsckContract;
import com.jxjr.contract.XsckContract.IfXsckActivityView;
import com.jxjr.contract.XsckContract.IfXsckScanFrgView;
import com.jxjr.contract.XsckContract.IfXsckHdFrgView;
import com.jxjr.m.CrkNetWorkModel;
import com.jxjr.m.XsckModel;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrCpbzdjBillDTO;
import com.jxjr.m.DTO.JrXsckBillDTO;
import com.jxjr.m.DTO.JrXsckSrcBillDTO;

import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.m.entity.JrPdaXsckbill;

import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;
import com.jxjr.utility.PbF;

public class XsckPresenter
		extends
		ABasePresenter<XsckContract.IfXsckActivityView, XsckContract.IfXsckModel> {

	public XsckPresenter(IfXsckActivityView view) {
		super(view);
		// TODO Auto-generated constructor stub
		this.model = new XsckModel();
	}

//	public void iGetsrcDTO(String srcbillno, final JrXsckBillDTO entity) {
//		view.showprocess("...");
//		model.onloadsrcDTO(srcbillno,
//				new IfSrcDataCallbakMsg<BaseInfoObjectDTO>() {
//
//					@Override
//					public void popFailure(final String error) {
//						// TODO Auto-generated method stub
//						mHandler.post(new Runnable() {
//							@Override
//							public void run() {
//								view.loadSrcfailure(Constance.srcflg_srcbill,
//										error);
//								view.hideprocess();
//								view.srcCallAction(false);
//							}
//						});
//					}
//
//					@Override
//					public void popSucess(String msg,
//							final BaseInfoObjectDTO srcDTO) {
//						// TODO Auto-generated method stub
//						mHandler.post(new Runnable() {
//							@Override
//							public void run() {
//								srcInsertEntity(srcDTO, entity);
//								view.hideprocess();
//								view.srcCallAction(true);
//							}
//						});
//					}
//				});
//	}

	private void srcInsertEntity(BaseInfoObjectDTO srcDTO, JrXsckBillDTO entity) {
		try {
			Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
			JrPdaXsckbill hd = entity.getBill();
			hd.setFdate(curDate);
			hd.setFbiztype(PbF.inzStr(srcDTO.getfItemClass()));
			hd.setFcreateuserid(PbF.int2Str(GI.SESSION.getfUserID()));
			hd.setFcreateusername(GI.SESSION.getfUserName());
			hd.setFbizdate(curDate);

			view.iSetData(entity);
			view.refreshFragmentData();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 产品包装登记仓库的扫描
	public void decodeGxBarcode(final IfXsckScanFrgView _view, String para) {
		view.showprocess(Constance.ProcessStr.decoding);

		SearchMatContract.IfSearchCrkModel itemModel = new CrkNetWorkModel();
		itemModel.pushPara(para);

		itemModel.decodeBaseBarcode(new IfdecodeBarcodeCallbak() {

			@Override
			public void popSucess(final BaseInfoObjectDTO entity) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						((IfXsckScanFrgView) _view).popDecodeBarcode(true, "",
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
						((IfXsckScanFrgView) _view).popDecodeBarcode(false,
								error, null);
						view.hideprocess();
					}
				});
			}
		});
	}
	
	// 产品入库仓位的扫描
			public void decodeSpBarcode(final IfXsckScanFrgView _view, String barcode,String stockno) {
				view.showprocess(Constance.ProcessStr.decoding);

				SearchMatContract.IfSearchCrkModel itemModel = new CrkNetWorkModel();
				itemModel.pushPara(barcode);
				itemModel.pushStockno(stockno);

				itemModel.decodeBaseBarcode(new IfdecodeBarcodeCallbak() {

					@Override
					public void popSucess(final BaseInfoObjectDTO entity) {
						// TODO Auto-generated method stub
						mHandler.post(new Runnable() {
							@Override
							public void run() {
								((IfXsckScanFrgView) _view).popDecodeBarcode(true, "",
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
								((IfXsckScanFrgView) _view).popDecodeBarcode(false,
										error, null);
								view.hideprocess();
							}
						});
					}
				});
			}


	// 产品包装登记物料的扫描
//	public void decodeMatBarcode(final IfXsckScanFrgView _view, String para) {
//		view.showprocess(Constance.ProcessStr.decoding);
//
//		SearchMatContract.IfSearchCrkModel itemModel = new CrkNetWorkModel();
//		itemModel.pushPara(para);
//
//		itemModel.decodematBarcode(new IfdecodeMatCallbak() {
//
//			@Override
//			public void popSucess(final JrMaterialBaseInfoDTO entity) {
//				// TODO Auto-generated method stub
//				mHandler.post(new Runnable() {
//					@Override
//					public void run() {
//						((IfXsckScanFrgView) _view).popDecodeMatBarcode(true,
//								"", entity);
//						view.hideprocess();
//					}
//				});
//			}
//
//			@Override
//			public void popFailure(final String error) {
//				// TODO Auto-generated method stub
//				mHandler.post(new Runnable() {
//					@Override
//					public void run() {
//						((IfXsckScanFrgView) _view).popDecodeMatBarcode(false,
//								error, null);
//						view.hideprocess();
//					}
//				});
//			}
//		});
//	}

	// 发货通知单的扫描
	public void decodeThBarcode(final IfXsckScanFrgView _view, String para,String fhtzdNo) {
		view.showprocess(Constance.ProcessStr.decoding);

		SearchMatContract.IfSearchCrkModel itemModel = new CrkNetWorkModel();
		itemModel.pushPara(para);
		itemModel.pushSrcBillNo(fhtzdNo);

		itemModel.decodeXsckBarcode(new IfdecodeThCallbak() {

			@Override
			public void popSucess(final JrCpbzdjBillDTO entity) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						((IfXsckScanFrgView) _view).popDecodeThBarcode(true,
								"", entity);
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
						((IfXsckScanFrgView) _view).popDecodeThBarcode(false,
								error, null);
						view.hideprocess();
					}
				});
			}
		});
	}

	// 销售出库源单编号的扫描
	public void decodeSrcBarcode(final IfXsckHdFrgView _view, String para) {
		view.showprocess(Constance.ProcessStr.decoding);

		SearchMatContract.IfSearchCrkModel itemModel = new CrkNetWorkModel();
		itemModel.pushPara(para);

		itemModel.decodeXsckSrcBarcode(new IfdecodeXsckSrcCallbak() {

			@Override
			public void popSucess(final JrXsckSrcBillDTO entity) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						((IfXsckHdFrgView) _view).popDecodeSrcbill(true, "",
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
						((IfXsckHdFrgView) _view).popDecodeSrcbill(false,
								error, null);
						view.hideprocess();
					}
				});
			}
		});
	}
}
