package com.jxjr.p;

import java.util.Map;

import android.content.Context;
import android.os.Handler;

import com.jxjr.contract.BaseInfoContract;
import com.jxjr.contract.SearchMatContract;
import com.jxjr.contract.ABaseBillContract.IfBaseBillHdFrgView;
import com.jxjr.contract.ABaseBillContract.IfBaseBillScanFrgView;
import com.jxjr.contract.ABaseBillContract.IfBasebillActivityView;
import com.jxjr.contract.ABaseBillContract.IfBasebillModel;
import com.jxjr.contract.CallbackContract.IfBaseInfoDecodeCallbak;
import com.jxjr.contract.CallbackContract.IfLoadDataCallbak;
import com.jxjr.contract.CallbackContract.IfSaveCallbak;
import com.jxjr.contract.CallbackContract.IfSubmitCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeBarcodeCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeMatCallbak;
import com.jxjr.contract.SearchItemContract.IfSearchItemModel;
import com.jxjr.m.BaseInfoModel;
import com.jxjr.m.BaseInfoNetWorkModel;
import com.jxjr.m.BaseMatNetWorkModel;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.utility.Constance;
import com.jxjr.utility.StringUtils;

public abstract class 
		ABasePresenter<V extends IfBasebillActivityView,
					M extends IfBasebillModel> {
	
	protected V view;
	protected M model;
	
	protected IfBaseBillScanFrgView scan_view;
	protected IfBaseBillHdFrgView hd_view;
	
	protected IfBaseBillScanFrgView scan_viewSec;
	
	protected Handler mHandler = new Handler();
	
	
	public ABasePresenter(V view) {
		this.view = view;
	}

	public void onDestroy(){
		this.view=null;
		this.scan_view=null;
		this.hd_view=null;
		this.model=null;
	}	
	/**
	 * @return the scan_view
	 */
	public IfBaseBillScanFrgView getScan_view() {
		return scan_view;
	}

	/**
	 * @param scan_view the scan_view to set
	 */
	public void setScan_view(IfBaseBillScanFrgView scan_view) {
		this.scan_view = scan_view;
	}
	/**
	 * @return the hd_view
	 */
	public IfBaseBillHdFrgView getHd_view() {
		return hd_view;
	}

	/**
	 * @param hd_view the hd_view to set
	 */
	public void setHd_view(IfBaseBillHdFrgView hd_view) {
		this.hd_view = hd_view;
	}	
	/**
	 * @return the scan_viewSec
	 */
	public IfBaseBillScanFrgView getScan_viewSec() {
		return scan_viewSec;
	}

	/**
	 * @param scan_viewSec the scan_viewSec to set
	 */
	public void setScan_viewSec(IfBaseBillScanFrgView scan_viewSec) {
		this.scan_viewSec = scan_viewSec;
	}

	public void refreshingData(int id){
		view.showprocess();
		if(id==0){
			model.igetData(new IfLoadDataCallbak() {

				@Override
				public void popFailure() {
					// TODO Auto-generated method stub
					mHandler.post(new Runnable() {
						@Override
						public void run(){
							view.intiBillfailure();
							view.hideprocess();
						}
					});
				}

				@Override
				public <T> void popSucess(final T entity) {
					// TODO Auto-generated method stub
						mHandler.post(new Runnable() {
						@Override
						public void run() {
							view.iSetData(entity);	
							view.refreshFragmentData();
							view.loadDataSucess();
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
							view.intiBillfailure();
							view.hideprocess();
						}
					});
				}
			});
		}else{
			model.igetData(new IfLoadDataCallbak() {

				@Override
				public void popFailure() {
					// TODO Auto-generated method stub
					mHandler.post(new Runnable() {
						@Override
						public void run(){
							view.intiBillfailure();
							view.hideprocess();
						}
					});
				}

				@Override
				public <T> void popSucess(final T entity) {
					// TODO Auto-generated method stub
						mHandler.post(new Runnable() {
						@Override
						public void run() {
							view.iSetData(entity);	
							view.refreshFragmentData();
							view.loadDataSucess();
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
							view.intiBillfailure();
							view.hideprocess();
						}
					});
				}
			},id);
		}

	}
	public <T> void pushData(T entity){
		model.iSetData(entity);
	}
	public <T> void onSave(){
		
		view.showprocess("保存中...");
		model.onSava(new IfSaveCallbak(){

			@Override
			public <T> void popSucess(final T entity) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						view.iSetData(entity);
						view.refreshFragmentData();
						view.hideprocess();
						view.callbackSucess("保存成功");
					}
				});
			}

			@Override
			public void popFailure(final String error) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						view.loadDatafailure(error);
						view.hideprocess();
					}
				});
			}		
		});		
	}
	public <T> void onSubmit(){
		
		view.showprocess("提交中...");
		model.onSubmit(new IfSubmitCallbak(){

			@Override
			public <T> void popSucess(final T entity) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						view.iSetData(entity);
						view.refreshFragmentData();
						view.hideprocess();
						view.submitcallbackSucess("提交成功");
					}
				});
			}

			@Override
			public void popFailure(final String error) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						view.loadDatafailure(error);
						view.hideprocess();
					}
				});
			}		
		});	
	}
	
	public <T> void decodeBaseInfoScanView(String para,String parentPara
			,final int requestCode,String fItemClass){
		view.showprocess(Constance.ProcessStr.decoding);
		IfSearchItemModel itemModel=new BaseInfoNetWorkModel();
		itemModel.pushPara(para,parentPara,fItemClass);
		itemModel.decodeBarcode(new IfdecodeBarcodeCallbak(){

			@Override
			public void popSucess(final BaseInfoObjectDTO entity) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run(){				
						//List<String[]> displayList=entityList2displayList(list);
						scan_view.popBaseInfoItem(requestCode,entity);
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
						scan_view.loadItemfailure(requestCode,error);
						view.hideprocess();
					}
				});
			}
			
		});	
	}
	
	public <T> void decodeBaseInfoScanView(String para,final int requestCode,String fItemClass){
		decodeBaseInfoScanView(para,"",requestCode,fItemClass);
	}
	
	public <T> void decodeBaseInfoScanView(String para,final int requestCode){
		view.showprocess(Constance.ProcessStr.decoding);
		IfSearchItemModel itemModel=new BaseInfoNetWorkModel();
		String fItemClass=Constance.codeItemClass.get(requestCode);
		itemModel.pushPara(para,fItemClass);
		itemModel.decodeBarcode(new IfdecodeBarcodeCallbak(){

			@Override
			public void popSucess(final BaseInfoObjectDTO entity) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run(){				
						//List<String[]> displayList=entityList2displayList(list);
						scan_view.popBaseInfoItem(requestCode,entity);
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
						scan_view.loadItemfailure(requestCode,error);
						view.hideprocess();
					}
				});
			}
			
		});		
	}
	
	public <T> void decodeBaseInfoScanView(String para,String parentCondition,final int requestCode){
		view.showprocess(Constance.ProcessStr.decoding);
		IfSearchItemModel itemModel=new BaseInfoNetWorkModel();
		String fItemClass=Constance.codeItemClass.get(requestCode);
		itemModel.pushPara(para,parentCondition,fItemClass);
		itemModel.decodeBarcode(new IfdecodeBarcodeCallbak(){

			@Override
			public void popSucess(final BaseInfoObjectDTO entity) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run(){				
						//List<String[]> displayList=entityList2displayList(list);
						scan_view.popBaseInfoItem(requestCode,entity);
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
						scan_view.loadItemfailure(requestCode,error);
						view.hideprocess();
					}
				});
			}
			
		});		
	}
	
	public <T> void decodeBaseInfoHdView(String para,final int requestCode,String fItemClass){
		view.showprocess("解析中...");
		IfSearchItemModel itemModel=new BaseInfoNetWorkModel();
		itemModel.pushPara(para,fItemClass);
		itemModel.decodeBarcode(new IfdecodeBarcodeCallbak(){

			@Override
			public void popSucess(final BaseInfoObjectDTO entity) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run(){				
						//List<String[]> displayList=entityList2displayList(list);
						hd_view.popBaseInfoItem(requestCode,entity);
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
						hd_view.loadItemfailure(requestCode,error);
						view.hideprocess();
					}
				});
			}
			
		});		
	}
	public <T> void decodeBaseInfoHdView(String para,final int requestCode){
		view.showprocess("解析中...");
		IfSearchItemModel itemModel=new BaseInfoNetWorkModel();
		String fItemClass=Constance.codeItemClass.get(requestCode);		
		itemModel.pushPara(para,fItemClass);
		itemModel.decodeBarcode(new IfdecodeBarcodeCallbak(){

			@Override
			public void popSucess(final BaseInfoObjectDTO entity) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run(){				
						//List<String[]> displayList=entityList2displayList(list);
						hd_view.popBaseInfoItem(requestCode,entity);
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
						hd_view.loadItemfailure(requestCode,error);
						view.hideprocess();
					}
				});
			}
			
		});		
	}
	
	
	/*
	 * 通过SQLite解析条码
	 * */
	public void decodeBaseInfoSQLite(final IfBaseBillScanFrgView _view,String para,
			final int requestCode,String documentType,Context context){
		this.scan_view=_view;
		BaseInfoContract.IfBaseInfoModel liteModel=new BaseInfoModel();
		view.showprocess();
		String fItemClass=Constance.codeItemClass.get(requestCode);
		if(StringUtils.isEmpty(fItemClass)){
			fItemClass=Constance.documentTypeItemClass.get(documentType);
		}
		liteModel.pushPara(para,fItemClass);
		liteModel.decodeBarcode(context, new IfBaseInfoDecodeCallbak(){

			@Override
			public void popSucess(Map<String, Object> map) {
				// TODO Auto-generated method stub
				scan_view.popDecodeLiteInfo(map,requestCode);
				scan_view.decodeLiteSucess("");
				view.hideprocess();	
			}

			@Override
			public void popFailure(String error) {
				// TODO Auto-generated method stub
				scan_view.decodeLiteFailure(error,requestCode);
				view.hideprocess();	
			}
			
		});
	}

	public void decodeBaseInfoSQLite(final IfBaseBillHdFrgView _view,String para,
			final int requestCode,String documentType,Context context){
		this.hd_view=_view;
		BaseInfoContract.IfBaseInfoModel liteModel=new BaseInfoModel();
		view.showprocess();
		String fItemClass=Constance.documentTypeItemClass.get(documentType);
		liteModel.pushPara(para,fItemClass);
		liteModel.decodeBarcode(context, new IfBaseInfoDecodeCallbak(){

			@Override
			public void popSucess(Map<String, Object> map) {
				// TODO Auto-generated method stub
				hd_view.popDecodeLiteInfo(map,requestCode);
				hd_view.decodeLiteSucess("");
				view.hideprocess();	
			}

			@Override
			public void popFailure(String error) {
				// TODO Auto-generated method stub
				hd_view.decodeLiteFailure(error,requestCode);
				view.hideprocess();	
			}
			
		});
	}
	
	public void decodeMat(final IfBaseBillScanFrgView _view,String para){
		view.showprocess(Constance.ProcessStr.decoding);
		SearchMatContract.IfSearchMatModel itemModel=new BaseMatNetWorkModel();
		itemModel.pushPara(para);
		itemModel.decodeBarcode(new IfdecodeMatCallbak(){

			@Override
			public void popSucess(final JrMaterialBaseInfoDTO entity) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run(){	
						scan_view.popDecodeMatSucess(entity);
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
						scan_view.popDecodeMatFailure(error);
						view.hideprocess();	
					}
				});
			}			
		});
	}

	
	public void decodeMat(final IfBaseBillScanFrgView _view,String para, String matType){
		view.showprocess(Constance.ProcessStr.decoding);
		SearchMatContract.IfSearchMatModel itemModel=new BaseMatNetWorkModel();
		itemModel.pushPara(para);
		itemModel.decodeBarcode(matType,new IfdecodeMatCallbak(){

			@Override
			public void popSucess(final JrMaterialBaseInfoDTO entity) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run(){	
						scan_view.popDecodeMatSucess(entity);
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
						scan_view.popDecodeMatFailure(error);
						view.hideprocess();	
					}
				});
			}			
		});
	}
	
	//通过仓位带出仓库
	public void decodeWareHouseBYID(final IfBaseBillScanFrgView _scan_view,String fid,final int requestCode){
		view.showprocess("解析中...");
		IfSearchItemModel itemModel=new BaseInfoNetWorkModel();
		itemModel.pushPara(fid, Constance.codeItemClass.get(requestCode));
		itemModel.decodeByForeignID(new IfdecodeBarcodeCallbak(){
			public void popSucess(final BaseInfoObjectDTO entity) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run(){	
						_scan_view.popBaseInfoItemByForeignID(requestCode,entity);
						view.hideprocess();
					}
				});
			}
	
	
			public void popFailure(final String error) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run(){	
						_scan_view.loadItemfailure(requestCode,error);
						view.hideprocess();
					}
				});
			}			
		});
	}
	
	//通过仓库ID带出fparam信息
	public boolean warehouseid2showLocal(Context context,String warehouseid){
		BaseInfoModel sqlmodel=new BaseInfoModel();
		Map<String,String> _map=sqlmodel.warehouseid2Paras(context, warehouseid);
		String fparam=_map.get("fparam");
		return Boolean.parseBoolean(fparam);
	}
	
}

