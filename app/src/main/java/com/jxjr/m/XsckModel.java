package com.jxjr.m;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.jxjr.contract.CallbackContract.IfListCallbak;
import com.jxjr.contract.CprkContract;
import com.jxjr.contract.CallbackContract.IfLoadDataCallbak;
import com.jxjr.contract.CallbackContract.IfSaveCallbak;
import com.jxjr.contract.CallbackContract.IfSubmitCallbak;
import com.jxjr.contract.XsckContract;

import com.jxjr.m.DTO.JrCpbzdjBillDTO;
import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.m.DTO.JrXsckBillDTO;

import com.jxjr.m.entity.JrPdaXsckbill;
import com.jxjr.m.entity.JrPdaXsckbillentry;
import com.jxjr.m.network.RResult;
import com.jxjr.m.network.XsckRequest;
import com.jxjr.utility.GI;
import com.jxjr.utility.HttpFun;
import com.jxjr.utility.SelfDefException;
import com.jxjr.utility.TxtReader;

//public class ManufactureInstockModel extends ABaseBillModel<JrManufactureRecBillDTO> {
public class XsckModel implements XsckContract.IfXsckModel{
	
	Boolean c_releaseMode=true;
	JrXsckBillDTO theEntity;	
	
	JrCpbzdjBillDTO srcDTO;
	List<JrCpbzdjBillDTO> srcList;
	public XsckModel() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void igetData(final IfLoadDataCallbak callback) {
		// TODO Auto-generated method stub
//		theEntity=new JrXsckBillDTO();
//		theEntity.setBill(new JrPdaXsckbill());
//		theEntity.setEntry(new ArrayList<JrPdaXsckbillentry>());
//		new Thread(){
//			@Override
//			public void run(){
//				RResult result=new RResult();
//				String response="";
//				try{
//					XsckRequest request=new XsckRequest("");
//					String requestJSON=request.toString();
//					if(c_releaseMode){
//						response=HttpFun.doPost(request.iGetBillURL(), requestJSON);
//					}else{
//						TxtReader reader=new TxtReader("/LF","生产入库单 保存.txt");	//storage
//						reader.readTxt();
//						response=reader.getContent();
//					}
//					if(null==response){
//						throw new SelfDefException(SelfDefException.noReponse);
//					}
//					result=new RResult(response);					
//					if(!result.isFlag()){
//						throw new SelfDefException(result.getMessage());
//					}					
//					request.decode(result.getData());
//					theEntity=request.getDtoObj();
//					intiNewBill();	//初始化数据，加入初始化数据
//					callback.popSucess(theEntity);
//				}catch (SelfDefException selfDef){
//					callback.popFailure(selfDef.getMessage());
//				}catch (JSONException e){
//					callback.popFailure(e.getMessage());
//				}catch(Exception e){
//					callback.popFailure("无法加载单据");
//				}
//			}
//		}.start();
	}

	
	
	/* (non-Javadoc)
	 * @see com.lf.contract.ABaseBillContract.IfBasebillModel#igetData(com.lf.contract.CallbackContract.IfLoadDataCallbak, int)
	 */
	@Override
	public void igetData(final IfLoadDataCallbak callback,final int id) {
		// TODO Auto-generated method stub
//		theEntity=new JrXsckBillDTO();
//		theEntity.setBill(new JrPdaXsckbill());
//		theEntity.setEntry(new ArrayList<JrPdaXsckbillentry>());
//		new Thread(){
//			@Override
//			public void run(){
//				RResult result=new RResult();
//				String response="";
//				try{
//					XsckRequest request=new XsckRequest(id+"");
//					String requestJSON=request.toString();
//					if(c_releaseMode){
//						response=HttpFun.doPost(request.iGetBillURL(), requestJSON);
//					}else{
//						TxtReader reader=new TxtReader("/LF","tst_ManufactureInstock_new.txt");	//storage
//						reader.readTxt();
//						response=reader.getContent();
//					}
//					if(null==response){
//						throw new SelfDefException(SelfDefException.noReponse);
//					}
//					result=new RResult(response);					
//					if(!result.isFlag()){
//						throw new SelfDefException(result.getMessage());
//					}					
//					request.decode(result.getData());
//					theEntity=request.getDtoObj();
//					intiNewBill();	//初始化数据，加入初始化数据
//					callback.popSucess(theEntity);
//				}catch (SelfDefException selfDef){
//					callback.popFailure(selfDef.getMessage());
//				}catch (JSONException e){
//					callback.popFailure(e.getMessage());
//				}catch(Exception e){
//					callback.popFailure("无法加载单据");
//				}
//			}
//		}.start();
	}
	
	
	@Override
	public <T> void iSetData(T entity) {
		// TODO Auto-generated method stub
		this.theEntity=(JrXsckBillDTO)entity;
	}

	/* (non-Javadoc)
	 * @see com.lf.contract.ABaseBillContract.IfBasebillModel#onSava(com.lf.contract.CallbackContract.IfSaveCallbak)
	 */
	@Override
	public void onSava(final IfSaveCallbak callback) {
		// TODO Auto-generated method stub
		new Thread(){
			@Override
			public void run(){
				RResult result=new RResult();
				String response="";
				try{
					XsckRequest request=new XsckRequest();
					//testinfo();
					request.setDtoObj(theEntity);
					//String requestJSON=request.dtoObj2String();
					String requestJSON=request.toString();
					
					response=HttpFun.doPost(request.iGetSaveURL(),requestJSON);
					if(null==response){
						throw new SelfDefException(SelfDefException.noReponse);
					}
					result=new RResult(response);					
					if(!result.isFlag()){
						theEntity.getBill().setId(0);
						for(int i=0;i<theEntity.getEntry().size();i++){
							theEntity.getEntry().get(i).setId(0);
						}
						throw new SelfDefException(result.getMessage());
					}					
					request.decode(result.getData());
					theEntity=request.getDtoObj();
					callback.popSucess(theEntity);
				}catch (SelfDefException selfDef){
					callback.popFailure(selfDef.getMessage());
				}catch (JSONException e){
					callback.popFailure(e.getMessage());
				}catch(Exception e){
					callback.popFailure("无法保存单据");
				}
			}
		}.start();	
	}
	
	@Override
	public void onSubmit(final IfSubmitCallbak callback) {
		// TODO Auto-generated method stub
		new Thread(){
			@Override
			public void run(){
				RResult result=new RResult();
				String response="";
				try{
					XsckRequest request=new XsckRequest();
					//testinfo();
					request.setDtoObj(theEntity);
					//String requestJSON=request.dtoObj2String();
					String requestJSON=request.toString();
					
					response=HttpFun.doPost(request.iGetSubmitURL(),requestJSON);
					if(null==response){
						throw new SelfDefException(SelfDefException.noReponse);
					}
					result=new RResult(response);					
					if(!result.isFlag()){
						throw new SelfDefException(result.getMessage());
					}					
					request.decode(result.getData());
					theEntity=request.getDtoObj();
					callback.popSucess(theEntity);
				}catch (SelfDefException selfDef){
					callback.popFailure(selfDef.getMessage());
				}catch (JSONException e){
					callback.popFailure(e.getMessage());
				}catch(Exception e){
					callback.popFailure("无法保存单据");
				}
			}
		}.start();	
	}
	
	private void intiNewBill() throws Exception{  //初始化数据，加入初始化数据
		JrPdaXsckbill tmpbill=this.theEntity.getBill();
		tmpbill.setFcreateuserid(GI.SESSION.getfUserID()+"");
		tmpbill.setFcreateusername(GI.SESSION.getfUserName());
//		tmpbill.setFdeptId(GI.SESSION.getfBmID()+"");
//		tmpbill.setFdeptName(GI.SESSION.getfBmName());
		this.theEntity.setBill(tmpbill);
	}

//	@Override
//	public void onloadsrcDTO(final String srcbillno, final IfListCallbak callback) {
//		// TODO Auto-generated method stub
//		new Thread(){
//			@Override
//			public void run(){
//				RResult result=new RResult();
//				String response="";
//				try{
//					XsckRecRequest request=new XsckRecRequest();
//					request.setFbarCode(srcbillno);
//					response=HttpFun.doPost(request.iGetBzdjURL(), request.toString());
//					if(null==response){
//						throw new SelfDefException(SelfDefException.noReponse);
//					}
//					result=new RResult(response);					
//					if(!result.isFlag()){
//						throw new SelfDefException(result.getMessage());
//					}					
//					request.decodeSrcDTO(result.getData());
//					srcList=request.getSrcList();
//					callback.popSucess(srcList);
//				}catch (SelfDefException selfDef){
//					callback.popFailure(selfDef.getMessage());
//				}catch (JSONException e){
//					callback.popFailure(e.getMessage());
//				}catch(Exception e){
//					callback.popFailure("无法加载源单。");
//				}
//			}
//		}.start();
//	}

	
}
