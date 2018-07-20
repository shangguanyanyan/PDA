package com.jxjr.p;

import java.util.List;

import android.os.Handler;

import com.jxjr.contract.CallbackContract.IfSearchCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeBarcodeCallbak;
import com.jxjr.contract.SearchItemContract;
import com.jxjr.contract.SearchItemContract.IfSearchItemView;
import com.jxjr.m.BaseInfoNetWorkModel;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;

public class SearchItemPresenter {

	private SearchItemContract.IfSearchItemView view;
	private SearchItemContract.IfSearchItemModel model;
	
	private Handler mHandler = new Handler();
	
	private String documentType="";
	
	private String fItemClass="";
	
	private String parentCondition="";	
	
	
	public String getParentCondition() {
		return parentCondition;
	}

	public void setParentCondition(String parentCondition) {
		this.parentCondition = parentCondition;
	}

	public SearchItemPresenter(IfSearchItemView view,String documentType) {
		this.view = view;
		this.documentType=documentType;
		
		fItemClass=Constance.documentTypeItemClass.get(documentType);
		this.model=new BaseInfoNetWorkModel();
	}

	public void refreshListData(String condition){
		view.showprocess();
		model.pushPara(condition,fItemClass);
		model.igetOutData(new IfSearchCallbak(){

			@Override
			public void popSucess(final List<BaseInfoObjectDTO> list) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run(){				
						//List<String[]> displayList=entityList2displayList(list);
						//view.refreshList(displayList);		
						view.popQueryList(list);
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
						view.loadDatafailure(error);
						view.hideprocess();	
					}
				});
			}
			
		});
	}
	
	public void refreshLocationList(String condition,String parentCondition){
		refreshLocationList(condition,parentCondition,GI.forgid);
	}
	
	public void refreshLocationList(String condition,String parentCondition,String forgid){
		view.showprocess();
		model.pushPara(condition,parentCondition,fItemClass);
		model.pushForgid(forgid);
		model.igetOutData(new IfSearchCallbak(){

			@Override
			public void popSucess(final List<BaseInfoObjectDTO> list) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run(){				
						//List<String[]> displayList=entityList2displayList(list);
						//view.refreshList(displayList);		
						view.popQueryList(list);
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
						view.loadDatafailure(error);
						view.hideprocess();	
					}
				});
			}
			
		});
	}
	
	public void decodeLocationBar(String barcode,String parentCondition){
		view.showprocess();
		model.pushPara(barcode,parentCondition,fItemClass);
		model.decodeBarcode(new IfdecodeBarcodeCallbak(){

			@Override
			public void popSucess(final BaseInfoObjectDTO entity) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run(){				
						//List<String[]> displayList=entityList2displayList(list);
						view.popEntity(entity);
						view.decodeSucess();
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
						view.decodeFailure(error);
						view.hideprocess();	
					}
				});
			}
			
		});
	}
	
	public void decodeBar(String barcode){
		view.showprocess();
		model.pushPara(barcode,fItemClass);
		model.decodeBarcode(new IfdecodeBarcodeCallbak(){

			@Override
			public void popSucess(final BaseInfoObjectDTO entity) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run(){				
						//List<String[]> displayList=entityList2displayList(list);
						view.popEntity(entity);
						view.decodeSucess();
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
						view.decodeFailure(error);
						view.hideprocess();	
					}
				});
			}
			
		});
	}
	
	public void decodeByForeignID(String barcode){
		view.showprocess();
		model.pushPara(barcode,fItemClass);
		model.decodeBarcode(new IfdecodeBarcodeCallbak(){

			@Override
			public void popSucess(BaseInfoObjectDTO entity) {
				// TODO Auto-generated method stub
				view.popEntity(entity);
				view.loadDataSucess();
				view.hideprocess();	
			}

			@Override
			public void popFailure(String error) {
				// TODO Auto-generated method stub
				view.loadDatafailure(error);
				view.hideprocess();	
			}
			
		});
	}
//	public List<String[]> entityList2displayList(List<SearchItemEntity> list){
//		List<String[]> displayList=new ArrayList<String[]>();
//		for(int i=0;i<list.size();i++){
//			SearchItemEntity tmpEntity=list.get(i);
//			String tmp0=tmpEntity.getFnumber();
//			String tmp1=tmpEntity.getFname();
//			displayList.add(new String[]{tmp0,tmp1});
//		}
//		return displayList;
//	}

	public void onDestroy(){
		this.view=null;
		//this.model=null;
	}
}
