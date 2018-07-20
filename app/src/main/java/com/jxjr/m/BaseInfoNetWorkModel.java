package com.jxjr.m;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONException;
import com.jxjr.contract.CallbackContract.IfSearchCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeBarcodeCallbak;
import com.jxjr.contract.SearchItemContract.IfSearchItemModel;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.network.BaseInfoObjectRequest;
import com.jxjr.m.network.RResult;
import com.jxjr.utility.Constance;
import com.jxjr.utility.HttpFun;
import com.jxjr.utility.SelfDefException;
import com.jxjr.utility.StringUtils;
import com.jxjr.utility.TxtReader;

public class BaseInfoNetWorkModel implements IfSearchItemModel {

	List <BaseInfoObjectDTO> itemList=new ArrayList<BaseInfoObjectDTO>();
	BaseInfoObjectDTO itemEntity;
	String condition;
	String parentcondition;
	String fItemClass;
	String forgid;
	
	Boolean c_releaseMode=true;		//当前Activity是否发布模式  false:测试模式
	
	public BaseInfoNetWorkModel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pushPara(String condition,String fItemClass) {
		// TODO Auto-generated method stub
		this.condition=condition;
		this.fItemClass=fItemClass;
	}
	
	@Override
	public void pushPara(String condition,String parentcondition,String fItemClass){
		this.condition=condition;
		this.parentcondition=parentcondition;
		this.fItemClass=fItemClass;
	}
	
	@Override
	public void pushForgid(String _forgid){
		this.forgid=_forgid;
	}
	
	@Override
	public void igetOutData(final IfSearchCallbak callback) {
		// TODO Auto-generated method stub
		if(itemList==null){
			itemList=new ArrayList<BaseInfoObjectDTO>();
		}else{
			itemList.clear();
		}		
		new Thread(){
			@Override
			public void run(){
				String response="";
				BaseInfoObjectRequest request=new BaseInfoObjectRequest();
				request.setForgid(forgid);
				
				RResult result=new RResult();
				try{
					request.setFparam(condition);
					request.setFtype(fItemClass);
					String requestUrl=request.iGetSelectURL();
					if(fItemClass.equals(Constance.codeItemClass.get(Constance.requestCode_baseinfoSp))){
						request.setFwarehouseid(parentcondition);
						requestUrl=request.iGetselectLocationURL();
					}
					String requestJSON=request.toString();
					response=HttpFun.doPost(requestUrl, requestJSON);//获得结果
	
					if(null==response){
						throw new SelfDefException(SelfDefException.noReponse);
					}
					result=new RResult(response);					
					if(!result.isFlag()){
						throw new SelfDefException(result.getMessage());
					}	
					request.decodeList(result.getData());
					itemList=request.getDtoList();
					callback.popSucess(itemList);
					
				}catch (SelfDefException selfDef){
					callback.popFailure(selfDef.getMessage());
				}catch (JSONException e){
					callback.popFailure(e.getMessage());
				}catch(Exception e){
					callback.popFailure(e.getMessage());
				}
			}
		}.start();	
	}


	public void decodeBarcode(final IfdecodeBarcodeCallbak callback){
		new Thread(){
			@Override
			public void run(){
				String response="";
				BaseInfoObjectRequest request=new BaseInfoObjectRequest();
				itemEntity=new BaseInfoObjectDTO();
				RResult result=new RResult();
				try{
					if(c_releaseMode){//发布模式
						request.setFbarCode(condition);
						request.setFtype(fItemClass);
						String requestUrl=request.iGetDecodeURL();
						if(fItemClass.equals(Constance.codeItemClass.get(Constance.requestCode_baseinfoSp))){
							request.setFwarehouseid(parentcondition);
							requestUrl=request.iGetdecodeLocationURL();
						}
						String requestJSON=request.toString();
						response=HttpFun.doPost(requestUrl, requestJSON);
					}else{			//本地读取模式 测试模式
						TxtReader reader=new TxtReader("/LF","tst_supplierEntity.txt");	//storage
						reader.readTxt();
						response=reader.getContent();	
						request.decode(response);
					}
					if(null==response){
						throw new SelfDefException(SelfDefException.noReponse);
					}
					result=new RResult(response);					
					if(!result.isFlag()){
						throw new SelfDefException(result.getMessage());
					}
					if(StringUtils.isEmpty(result.getData())
							||"[]".equals(result.getData())
							||"{}".equals(result.getData())){
						throw new SelfDefException(SelfDefException.emptyData);
					}
					request.decode(result.getData());
					itemEntity=request.getDtoObj();
					String _fid=itemEntity.getfItemID();
					if(!StringUtils.isEmpty(_fid)){
						callback.popSucess(itemEntity);
					}else{
						throw new SelfDefException(SelfDefException.emptyDecoder);
					}
				}catch (SelfDefException selfDef){
					callback.popFailure(selfDef.getMessage());
				}catch (JSONException e){
					callback.popFailure(e.getMessage());
				}catch(Exception e){
					callback.popFailure(e.getMessage());
				}
			}
		}.start();
	}
	
	public void decodeByForeignID(final IfdecodeBarcodeCallbak callback){
		itemEntity=new BaseInfoObjectDTO();
		new Thread(){
			@Override
			public void run(){
				String response="";
				BaseInfoObjectRequest request=new BaseInfoObjectRequest();
				
				RResult result=new RResult();
				try{
					if(c_releaseMode){//发布模式
						request.setFparam(condition);
						request.setFtype(fItemClass);
						String requestUrl=request.iGetForeignIDURL();
						String requestJSON=request.toString();
						response=HttpFun.doPost(requestUrl, requestJSON);
					}else{			//本地读取模式 测试模式
//						TxtReader reader=new TxtReader("/LF","tst_supplierEntity.txt");	//storage
//						reader.readTxt();
//						response=reader.getContent();	
//						request.decode(response);
					}
					if(null==response){
						throw new SelfDefException(SelfDefException.noReponse);
					}
					result=new RResult(response);					
					if(!result.isFlag()){
						throw new SelfDefException(result.getMessage());
					}
					if(StringUtils.isEmpty(result.getData())
							||"[]".equals(result.getData())
							||"{}".equals(result.getData())){
						throw new SelfDefException(SelfDefException.emptyData);
					}
					request.decode(result.getData());
					itemEntity=request.getDtoObj();
					callback.popSucess(itemEntity);
				}catch (SelfDefException selfDef){
					callback.popFailure(selfDef.getMessage());
				}catch (JSONException e){
					callback.popFailure(e.getMessage());
				}catch(Exception e){
					callback.popFailure(e.getMessage());
				}
			}
		}.start();
	}
}
