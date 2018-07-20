package com.jxjr.m;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONException;
import com.jxjr.contract.CallbackContract.IfSearchCallbak;
import com.jxjr.contract.CallbackContract.IfSearchTmCallbak;
import com.jxjr.contract.CallbackContract.IfSearchMatCallbak;
//import com.jxjr.contract.CallbackContract.IfSearchMatCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeBarcodeCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeMatCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeTmCallbak;
import com.jxjr.contract.SearchMatContract.IfSearchMatModel;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrGxpgbillDTO;
import com.jxjr.m.entity.JrPdaGxpgYgentry;
import com.jxjr.m.entity.JrPdaGxpgbillentry;
import com.jxjr.m.network.BaseDataRequest;
import com.jxjr.m.network.BaseInfoObjectRequest;
import com.jxjr.m.network.GxhbBaseInfoRequest;

import com.jxjr.m.network.RResult;
import com.jxjr.utility.Constance;
import com.jxjr.utility.HttpFun;
//import com.jxjr.utility.PbF;
import com.jxjr.utility.SelfDefException;
import com.jxjr.utility.StringUtils;
import com.jxjr.utility.TxtReader;

public class GxhbNetWorkModel implements IfSearchMatModel{
	
	List <JrPdaGxpgbillentry> itemList=new ArrayList<JrPdaGxpgbillentry>();
	List <JrPdaGxpgYgentry> itemYgList=new ArrayList<JrPdaGxpgYgentry>();
	JrGxpgbillDTO itemEntity;
	BaseInfoObjectDTO baseEntity;
	String condition;
	String matType=Constance.Mat.type_normal;

	
	Boolean c_releaseMode=true;		//当前Activity是否发布模式  false:测试模式
	
	public GxhbNetWorkModel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pushPara(String condition) {
		// TODO Auto-generated method stub
		this.condition=condition;
	}
	
	@Override
	public void igetOutData(final IfSearchTmCallbak callback) {
		// TODO Auto-generated method stub
		new Thread(){
			@Override
			public void run(){
				String response="";
				GxhbBaseInfoRequest request=new GxhbBaseInfoRequest();				
				itemList.clear();
				RResult result=new RResult();
				try{
					
						request.setFparam(condition);
						String requestUrl=request.iGetSelectURL();
						String requestJSON=request.toString();
						response=HttpFun.doPost(requestUrl, requestJSON);
					
					if(null==response){
						throw new SelfDefException(SelfDefException.noReponse);
					}
					result=new RResult(response);					
					if(!result.isFlag()){
						throw new SelfDefException(result.getMessage());
					}	
					request.decode(result.getData());
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
	
	@Override
	public void decodeBarcode2(final IfdecodeTmCallbak callback){
		decodeBarcode2(Constance.Mat.type_normal,callback);
	}
	
	@Override
	public void decodeBarcode2(String _matType,final IfdecodeTmCallbak callback){
		this.matType=_matType;
		new Thread(){
			@Override
			public void run(){
				String response="";
				GxhbBaseInfoRequest request=new GxhbBaseInfoRequest();
				itemEntity=new JrGxpgbillDTO();
				RResult result=new RResult();
				try{
					
						request.setFbarCode(condition);//传入查询单号

						request.setFtype(matType);

						String requestUrl=request.iGetDecodeURL();					
						String requestJSON=request.toString();
						response=HttpFun.doPost(requestUrl, requestJSON);//得到信息
					
					if(null==response){
						throw new SelfDefException(SelfDefException.noReponse);
					}
					result=new RResult(response);					
					if(!result.isFlag()){
						throw new SelfDefException(result.getMessage());
					}
					request.decode(result.getData());
					itemEntity=request.getDtoObj();
					//String _fid=itemEntity.getFid();
//					if(!StringUtils.isEmpty(_fid)||Integer.valueOf(_fid)>0){
						callback.popSucess(itemEntity);
//					}else{
//						throw new SelfDefException(SelfDefException.emptyDecoder);
//					}
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



	@Override
	public void igetOutData(IfSearchMatCallbak callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decodeBarcode(IfdecodeMatCallbak callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decodeBarcode(String matType, IfdecodeMatCallbak callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decodeBaseBarcode(final IfdecodeBarcodeCallbak callback){
		new Thread(){
			@Override
			public void run(){
				String response="";
				BaseDataRequest request=new BaseDataRequest();
				baseEntity=new BaseInfoObjectDTO();
				RResult result=new RResult();
				try{
					
						request.setFbarCode(condition);

						request.setFtype(matType);

						String requestUrl=request.iGetDecodeURL();					
						String requestJSON=request.toString();
						response=HttpFun.doPost(requestUrl, requestJSON);
					
					if(null==response){
						throw new SelfDefException(SelfDefException.noReponse);
					}
					result=new RResult(response);					
					if(!result.isFlag()){
						throw new SelfDefException(result.getMessage());
					}
					
					
						request.decode(result.getData());
						baseEntity=request.getDtoObj();
						String _fid=baseEntity.getfItemID();
						if(!StringUtils.isEmpty(_fid)||Integer.valueOf(_fid)>0){
							callback.popSucess(baseEntity);
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

	
}
