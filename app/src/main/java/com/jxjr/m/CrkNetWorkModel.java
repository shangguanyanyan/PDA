package com.jxjr.m;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONException;
//import com.jxjr.contract.CallbackContract.IfSearchMatCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeBarcodeCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeBcprkCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeCprkSrcCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeMatCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeScllSrcCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeThCallbak;
import com.jxjr.contract.CallbackContract.IfdecodeXsckSrcCallbak;
import com.jxjr.contract.SearchMatContract.IfSearchCrkModel;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrBcprkSrcBillDTO;
import com.jxjr.m.DTO.JrCpbzdjBillDTO;
import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.m.DTO.JrXsckSrcBillDTO;
import com.jxjr.m.DTO.JrYlqdBillDTO;
import com.jxjr.m.network.BaseDataRequest;
import com.jxjr.m.network.BaseInfoObjectRequest;

import com.jxjr.m.network.RResult;
import com.jxjr.utility.Constance;
import com.jxjr.utility.HttpFun;
//import com.jxjr.utility.PbF;
import com.jxjr.utility.SelfDefException;
import com.jxjr.utility.StringUtils;
import com.jxjr.utility.TxtReader;

public class CrkNetWorkModel implements IfSearchCrkModel{
	
	List <BaseInfoObjectDTO> itemList=new ArrayList<BaseInfoObjectDTO>();
	BaseInfoObjectDTO itemEntity;
	JrMaterialBaseInfoDTO matEntity;
	JrCpbzdjBillDTO thEntity;
	JrXsckSrcBillDTO xsckSrcEntity;
	JrYlqdBillDTO ylqdEntity;
	JrBcprkSrcBillDTO bcprkSrcEntity;
	String condition;
	String matType=Constance.Mat.type_normal;
	String srcBillNo;
	String stockno;

	
	Boolean c_releaseMode=true;		//当前Activity是否发布模式  false:测试模式
	
	public CrkNetWorkModel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pushPara(String condition) {
		// TODO Auto-generated method stub
		this.condition=condition;
	}
	
	
	@Override
	public void pushSrcBillNo(String srcBillNo) {
		// TODO Auto-generated method stub
		this.srcBillNo=srcBillNo;
	}
	
	@Override
	public void pushStockno(String stockno) {
		// TODO Auto-generated method stub
		this.stockno=stockno;
	}

	@Override
	public void decodeBaseBarcode(final IfdecodeBarcodeCallbak callback){
		new Thread(){
			@Override
			public void run(){
				String response="";
				BaseDataRequest request=new BaseDataRequest();
				itemEntity=new BaseInfoObjectDTO();
				RResult result=new RResult();
				try{
					
						request.setFbarCode(condition);
						request.setFparam(stockno);

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
						itemEntity=request.getDtoObj();
						String _fid=itemEntity.getfItemID();
						if(!StringUtils.isEmpty(_fid)||Integer.valueOf(_fid)>0){
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

	@Override
	public void decodeMatBarcode(final IfdecodeMatCallbak callback){
		new Thread(){
			@Override
			public void run(){
				String response="";
				BaseDataRequest request=new BaseDataRequest();
				matEntity=new JrMaterialBaseInfoDTO();
				RResult result=new RResult();
				try{
					
						request.setFbarCode(condition);
						request.setFylqdNo(srcBillNo);

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
					
											
						request.matdecode(result.getData());
						matEntity=request.getMatDtoObj();
						String _fid=matEntity.getFid();
						if(!StringUtils.isEmpty(_fid)||Integer.valueOf(_fid)>0){
							callback.popSucess(matEntity);
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


	@Override
	public void decodeCprkBarcode(final IfdecodeThCallbak callback){
		new Thread(){
			@Override
			public void run(){
				String response="";
				BaseDataRequest request=new BaseDataRequest();
				thEntity=new JrCpbzdjBillDTO();
				RResult result=new RResult();
				try{
					
						request.setFbarCode(condition);
						request.setFcpbzdjNo(srcBillNo);

						String requestUrl=request.iGetCprkURL();					
						String requestJSON=request.toString();
						response=HttpFun.doPost(requestUrl, requestJSON);
					
					if(null==response){
						throw new SelfDefException(SelfDefException.noReponse);
					}
					result=new RResult(response);					
					if (!result.isFlag()) {
						throw new SelfDefException(result.getMessage());
					}

					request.thdecode(result.getData());
					thEntity = request.getThDtoObj();
					String _fid = thEntity.getEntry().get(0).getFentryid();
					if (!StringUtils.isEmpty(_fid) || Integer.valueOf(_fid) > 0) {
						callback.popSucess(thEntity);
					} else {
						throw new SelfDefException(
								SelfDefException.emptyDecoder);
					}

				} catch (SelfDefException selfDef) {
					callback.popFailure(selfDef.getMessage());
				} catch (JSONException e) {
					callback.popFailure(e.getMessage());
				} catch (Exception e) {
					callback.popFailure(e.getMessage());
				}
			}
		}.start();
	}
	
	@Override
	public void decodeXsckBarcode(final IfdecodeThCallbak callback){
		new Thread(){
			@Override
			public void run(){
				String response="";
				BaseDataRequest request=new BaseDataRequest();
				thEntity=new JrCpbzdjBillDTO();
				RResult result=new RResult();
				try{
					
						request.setFbarCode(condition);
						request.setFfhtzdNo(srcBillNo);

						String requestUrl=request.iGetXsckURL();					
						String requestJSON=request.toString();
						response=HttpFun.doPost(requestUrl, requestJSON);
					
					if(null==response){
						throw new SelfDefException(SelfDefException.noReponse);
					}
					result=new RResult(response);					
					if (!result.isFlag()) {
						throw new SelfDefException(result.getMessage());
					}

					request.thdecode(result.getData());
					thEntity = request.getThDtoObj();
					String _fid = thEntity.getEntry().get(0).getFentryid();
					if (!StringUtils.isEmpty(_fid) || Integer.valueOf(_fid) > 0) {
						callback.popSucess(thEntity);
					} else {
						throw new SelfDefException(
								SelfDefException.emptyDecoder);
					}

				} catch (SelfDefException selfDef) {
					callback.popFailure(selfDef.getMessage());
				} catch (JSONException e) {
					callback.popFailure(e.getMessage());
				} catch (Exception e) {
					callback.popFailure(e.getMessage());
				}
			}
		}.start();
	}
	
	@Override
	public void decodeXsckSrcBarcode(final IfdecodeXsckSrcCallbak callback){
		new Thread(){
			@Override
			public void run(){
				String response="";
				BaseDataRequest request=new BaseDataRequest();
				xsckSrcEntity=new JrXsckSrcBillDTO();
				RResult result=new RResult();
				try{
					
						request.setFbarCode(condition);

						String requestUrl=request.iGetXsckSrcURL();					
						String requestJSON=request.toString();
						response=HttpFun.doPost(requestUrl, requestJSON);
					
					if(null==response){
						throw new SelfDefException(SelfDefException.noReponse);
					}
					result=new RResult(response);					
					if(!result.isFlag()){
						throw new SelfDefException(result.getMessage());
					}
					request.xsckSrcdecode(result.getData());
					xsckSrcEntity=request.getXsckSrcDtoObj();
					String _fid=xsckSrcEntity.getFid();
					if(!StringUtils.isEmpty(_fid)||Integer.valueOf(_fid)>0){
						callback.popSucess(xsckSrcEntity);
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

	@Override
	public void decodeCprkSrcBarcode(final IfdecodeCprkSrcCallbak callback) {
		// TODO Auto-generated method stub
		new Thread(){
			@Override
			public void run(){
				String response="";
				BaseDataRequest request=new BaseDataRequest();
				thEntity=new JrCpbzdjBillDTO();
				RResult result=new RResult();
				try{
					
						request.setFbarCode(condition);

						String requestUrl=request.iGetCprkSrcURL();					
						String requestJSON=request.toString();
						response=HttpFun.doPost(requestUrl, requestJSON);
					
					if(null==response){
						throw new SelfDefException(SelfDefException.noReponse);
					}
					result=new RResult(response);					
					if(!result.isFlag()){
						throw new SelfDefException(result.getMessage());
					}
					request.cprkSrcdecode(result.getData());
					thEntity=request.getThDtoObj();
					String _fid=thEntity.getBill().getFid();
					if(!StringUtils.isEmpty(_fid)||Integer.valueOf(_fid)>0){
						callback.popSucess(thEntity);
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
	
	@Override
	public void decodeScllSrcBarcode(final IfdecodeScllSrcCallbak callback) {
		// TODO Auto-generated method stub
		new Thread(){
			@Override
			public void run(){
				String response="";
				BaseDataRequest request=new BaseDataRequest();
				ylqdEntity=new JrYlqdBillDTO();
				RResult result=new RResult();
				try{
					
						request.setFbarCode(condition);

						String requestUrl=request.iGetScllSrcURL();					
						String requestJSON=request.toString();
						response=HttpFun.doPost(requestUrl, requestJSON);
					
					if(null==response){
						throw new SelfDefException(SelfDefException.noReponse);
					}
					result=new RResult(response);					
					if(!result.isFlag()){
						throw new SelfDefException(result.getMessage());
					}
					request.scllSrcdecode(result.getData());
					ylqdEntity=request.getScllSrcDtoObj();
					String _fid=ylqdEntity.getFid();
					if(!StringUtils.isEmpty(_fid)||Integer.valueOf(_fid)>0){
						callback.popSucess(ylqdEntity);
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
	
	@Override
	public void decodeBcprkBarcode(final IfdecodeBcprkCallbak callback){
		new Thread(){
			@Override
			public void run(){
				String response="";
				BaseDataRequest request=new BaseDataRequest();
				bcprkSrcEntity=new JrBcprkSrcBillDTO();
				RResult result=new RResult();
				try{
					
						request.setFbarCode(condition);

						String requestUrl=request.iGetBcprkURL();					
						String requestJSON=request.toString();
						response=HttpFun.doPost(requestUrl, requestJSON);
					
					if(null==response){
						throw new SelfDefException(SelfDefException.noReponse);
					}
					result=new RResult(response);					
					if(!result.isFlag()){
						throw new SelfDefException(result.getMessage());
					}
					request.bcprkdecode(result.getData());
					bcprkSrcEntity=request.getBcprkSrcDtoObj();
					String _ftm=bcprkSrcEntity.getFtm();
					if(!StringUtils.isEmpty(_ftm)){
						callback.popSucess(bcprkSrcEntity);
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
