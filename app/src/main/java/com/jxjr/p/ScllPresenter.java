package com.jxjr.p;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jxjr.contract.CallbackContract.IfSrcDataCallbakMsg;
import com.jxjr.contract.CallbackContract.IfdecodeBarcodeCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeMatCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeScllSrcCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeThCallbak;
import com.jxjr.contract.ScllContract;
import com.jxjr.contract.SearchMatContract;
import com.jxjr.contract.ScllContract;
import com.jxjr.contract.ScllContract.IfScllActivityView;
import com.jxjr.contract.ScllContract.IfScllScanFrgView;
import com.jxjr.contract.ScllContract.IfScllHdFrgView;
import com.jxjr.m.CrkNetWorkModel;
import com.jxjr.m.ScllModel;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrScllBillDTO;
import com.jxjr.m.DTO.JrYlqdBillDTO;


import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.m.entity.JrPdaScllbill;

import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;
import com.jxjr.utility.PbF;

public class ScllPresenter
		extends
		ABasePresenter<ScllContract.IfScllActivityView, ScllContract.IfScllModel> {

	public ScllPresenter(IfScllActivityView view) {
		super(view);
		// TODO Auto-generated constructor stub
		this.model = new ScllModel();
	}
	

	private void srcInsertEntity(BaseInfoObjectDTO srcDTO, JrScllBillDTO entity) {
		try {
			Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
			JrPdaScllbill hd = entity.getBill();
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

	// 生产领料仓库仓位的扫描
	public void decodeGxBarcode(final IfScllScanFrgView _view, String para) {
		view.showprocess(Constance.ProcessStr.decoding);

		SearchMatContract.IfSearchCrkModel itemModel = new CrkNetWorkModel();
		itemModel.pushPara(para);

		itemModel.decodeBaseBarcode(new IfdecodeBarcodeCallbak() {

			@Override
			public void popSucess(final BaseInfoObjectDTO entity) {//得到基本物料dto
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						((IfScllScanFrgView) _view).popDecodeBarcode(true, "",
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
						((IfScllScanFrgView) _view).popDecodeBarcode(false,
								error, null);
						view.hideprocess();
					}
				});
			}
		});
	}

	// 生产领料仓位的扫描
		public void decodeSpBarcode(final IfScllScanFrgView _view, String barcode,String stockno) {
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
							((IfScllScanFrgView) _view).popDecodeBarcode(true, "",
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
							((IfScllScanFrgView) _view).popDecodeBarcode(false,
									error, null);
							view.hideprocess();
						}
					});
				}
			});
		}

		// 产品包装登记物料的扫描
				public void decodeMatBarcode(final IfScllScanFrgView _view,String para,String ylqdNo){
					view.showprocess(Constance.ProcessStr.decoding);
					
					SearchMatContract.IfSearchCrkModel itemModel=new CrkNetWorkModel();
					itemModel.pushPara(para);
					itemModel.pushSrcBillNo(ylqdNo);

					itemModel.decodeMatBarcode(new IfdecodeMatCallbak(){
						
						@Override
						public void popSucess(final JrMaterialBaseInfoDTO entity) {
							// TODO Auto-generated method stub
							mHandler.post(new Runnable() {
								@Override
								public void run(){	
									((IfScllScanFrgView)_view)
									.popDecodeMatBarcode(true,"",entity);
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
									((IfScllScanFrgView)_view)
									.popDecodeMatBarcode(false,error,null);
									view.hideprocess();	
								}
							});
						}			
					}
					);
				}
	
	// 生产领料源单用料清单的扫描
		public void decodeSrcBarcode(final IfScllHdFrgView _view, String para) {
			view.showprocess(Constance.ProcessStr.decoding);

			SearchMatContract.IfSearchCrkModel itemModel = new CrkNetWorkModel();
			itemModel.pushPara(para);

			itemModel.decodeScllSrcBarcode(new IfdecodeScllSrcCallbak() {

				@Override
				public void popSucess(final JrYlqdBillDTO entity) {
					// TODO Auto-generated method stub
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							((IfScllHdFrgView) _view).popDecodeSrcbill(true, "",
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
							((IfScllHdFrgView) _view).popDecodeSrcbill(false,
									error, null);
							view.hideprocess();
						}
					});
				}
			});
		}

}
