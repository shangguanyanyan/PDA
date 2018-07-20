package com.jxjr.p;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jxjr.contract.CallbackContract.IfSrcDataCallbakMsg;
import com.jxjr.contract.CallbackContract.IfdecodeBarcodeCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeCprkSrcCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeThCallbak;
import com.jxjr.contract.CprkContract;
import com.jxjr.contract.SearchMatContract;
import com.jxjr.contract.CprkContract;
import com.jxjr.contract.CprkContract.IfCprkActivityView;
import com.jxjr.contract.CprkContract.IfCprkScanFrgView;
import com.jxjr.contract.CprkContract.IfCprkHdFrgView;
import com.jxjr.contract.CprkContract.IfCprkHdFrgView;
import com.jxjr.m.CrkNetWorkModel;
import com.jxjr.m.CprkModel;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrCpbzdjBillDTO;
import com.jxjr.m.DTO.JrCprkBillDTO;


import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.m.entity.JrPdaCprkbill;

import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;
import com.jxjr.utility.PbF;

public class CprkPresenter
		extends
		ABasePresenter<CprkContract.IfCprkActivityView, CprkContract.IfCprkModel> {

	public CprkPresenter(IfCprkActivityView view) {
		super(view);
		// TODO Auto-generated constructor stub
		this.model = new CprkModel();
	}
	

	private void srcInsertEntity(BaseInfoObjectDTO srcDTO, JrCprkBillDTO entity) {
		try {
			Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
			JrPdaCprkbill hd = entity.getBill();
			hd.setFbizdate(curDate);
			hd.setFbiztype(PbF.inzStr(srcDTO.getfItemClass()));
			hd.setFcreateuserid(PbF.int2Str(GI.SESSION.getfUserID()));
			hd.setFcreateusername(GI.SESSION.getfUserName());

			view.iSetData(entity);
			view.refreshFragmentData();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 产品入库仓库仓位的扫描
	public void decodeGxBarcode(final IfCprkScanFrgView _view, String para) {
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
						((IfCprkScanFrgView) _view).popDecodeBarcode(true, "",
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
						((IfCprkScanFrgView) _view).popDecodeBarcode(false,
								error, null);
						view.hideprocess();
					}
				});
			}
		});
	}

	// 产品入库仓位的扫描
		public void decodeSpBarcode(final IfCprkScanFrgView _view, String barcode,String stockno) {
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
							((IfCprkScanFrgView) _view).popDecodeBarcode(true, "",
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
							((IfCprkScanFrgView) _view).popDecodeBarcode(false,
									error, null);
							view.hideprocess();
						}
					});
				}
			});
		}

	
	// 产品入库源单的扫描
	public void decodeThBarcode(final IfCprkScanFrgView _view, String para,String srcBillNo) {
		view.showprocess(Constance.ProcessStr.decoding);

		SearchMatContract.IfSearchCrkModel itemModel = new CrkNetWorkModel();
		itemModel.pushPara(para);
		itemModel.pushSrcBillNo(srcBillNo);

		itemModel.decodeCprkBarcode(new IfdecodeThCallbak() {

			@Override
			public void popSucess(final JrCpbzdjBillDTO entity) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						((IfCprkScanFrgView) _view).popDecodeThBarcode(true,
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
						((IfCprkScanFrgView) _view).popDecodeThBarcode(false,
								error, null);
						view.hideprocess();
					}
				});
			}
		});
	}
	
	// 产品入库成品包装登记单号的扫描
		public void decodeSrcBarcode(final IfCprkHdFrgView _view, String para) {
			view.showprocess(Constance.ProcessStr.decoding);

			SearchMatContract.IfSearchCrkModel itemModel = new CrkNetWorkModel();
			itemModel.pushPara(para);

			itemModel.decodeCprkSrcBarcode(new IfdecodeCprkSrcCallbak() {

				@Override
				public void popSucess(final JrCpbzdjBillDTO entity) {
					// TODO Auto-generated method stub
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							((IfCprkHdFrgView) _view).popDecodeSrcbill(true, "",
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
							((IfCprkHdFrgView) _view).popDecodeSrcbill(false,
									error, null);
							view.hideprocess();
						}
					});
				}
			});
		}

}
