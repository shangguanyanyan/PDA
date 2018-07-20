package com.jxjr.p;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jxjr.contract.CallbackContract.IfSrcDataCallbakMsg;
import com.jxjr.contract.CallbackContract.IfdecodeBarcodeCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeMatCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeBcprkCallbak;
import com.jxjr.contract.BcprkContract;
import com.jxjr.contract.SearchMatContract;
import com.jxjr.contract.BcprkContract.IfBcprkActivityView;
import com.jxjr.contract.BcprkContract.IfBcprkScanFrgView;
import com.jxjr.m.CrkNetWorkModel;
import com.jxjr.m.BcprkModel;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrBcprkBillDTO;
import com.jxjr.m.DTO.JrBcprkSrcBillDTO;

import com.jxjr.m.entity.JrPdaBcprkbill;

import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;
import com.jxjr.utility.PbF;

public class BcprkPresenter
		extends
		ABasePresenter<BcprkContract.IfBcprkActivityView, BcprkContract.IfBcprkModel> {

	public BcprkPresenter(IfBcprkActivityView view) {
		super(view);
		// TODO Auto-generated constructor stub
		this.model = new BcprkModel();
	}
	

	private void srcInsertEntity(BaseInfoObjectDTO srcDTO, JrBcprkBillDTO entity) {
		try {
			Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
			JrPdaBcprkbill hd = entity.getBill();
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

	// 半成品入库仓库仓位的扫描
	public void decodeGxBarcode(final IfBcprkScanFrgView _view, String para) {
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
						((IfBcprkScanFrgView) _view).popDecodeBarcode(true, "",
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
						((IfBcprkScanFrgView) _view).popDecodeBarcode(false,
								error, null);
						view.hideprocess();
					}
				});
			}
		});
	}

	// 半成品入库仓位的扫描
		public void decodeSpBarcode(final IfBcprkScanFrgView _view, String barcode,String stockno) {
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
							((IfBcprkScanFrgView) _view).popDecodeBarcode(true, "",
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
							((IfBcprkScanFrgView) _view).popDecodeBarcode(false,
									error, null);
							view.hideprocess();
						}
					});
				}
			});
		}

		// 半成品入库派工单条码的扫描
				public void decodeBcprkBarcode(final IfBcprkScanFrgView _view,String para){
					view.showprocess(Constance.ProcessStr.decoding);
					
					SearchMatContract.IfSearchCrkModel itemModel=new CrkNetWorkModel();
					itemModel.pushPara(para);

					itemModel.decodeBcprkBarcode(new IfdecodeBcprkCallbak(){
						
						@Override
						public void popSucess(final JrBcprkSrcBillDTO entity) {
							// TODO Auto-generated method stub
							mHandler.post(new Runnable() {
								@Override
								public void run(){	
									((IfBcprkScanFrgView)_view)
									.popDecodeBcprkBarcode(true,"",entity);
									view.hideprocess();	
								}
							});
						}

						@Override
						public void popFailure(final String error) {
							// TODO Auto-generated method stub
							mHandler.post(new Runnable() {
								@Override
								public void run(){	
									((IfBcprkScanFrgView)_view)
									.popDecodeBcprkBarcode(false,error,null);
									view.hideprocess();	
								}
							});
						}			
					}
					);
				}
	

}
