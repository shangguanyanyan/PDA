package com.jxjr.m;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONException;
import com.jxjr.contract.CallbackContract.IfEntitycallBack;
import com.jxjr.contract.CallbackContract.IfLoadDataCallbak;
import com.jxjr.contract.CallbackContract.IfSaveCallbak;
import com.jxjr.contract.CallbackContract.IfSrcDataCallbakMsg;
import com.jxjr.contract.CallbackContract.IfSubmitCallbak;
import com.jxjr.contract.GxhbContract;
import com.jxjr.m.DTO.JrGxpgbillDTO;
import com.jxjr.m.DTO.JrGxhbBillDTO;
import com.jxjr.m.entity.JrPdaGxhbYgentry;
import com.jxjr.m.entity.JrPdaGxhbbill;
import com.jxjr.m.entity.JrPdaGxhbbillentry;
import com.jxjr.m.network.GxhbRecRequest;
import com.jxjr.m.network.RResult;
//import com.jxjr.mcallback.OrdcallBack;
import com.jxjr.utility.GI;
import com.jxjr.utility.HttpFun;
import com.jxjr.utility.SelfDefException;
import com.jxjr.utility.TxtReader;

//public class ManufactureInstockModel extends ABaseBillModel<JrManufactureRecBillDTO> {
public class GxhbModel implements GxhbContract.IfGxhbModel{
	
	JrGxhbBillDTO theEntity;
	
	List<JrGxpgbillDTO> srcList;
	JrGxpgbillDTO srcDTO;
	
	public GxhbModel() {
		// TODO Auto-generated constructor stub
	}

	public void igetData(final int id,final IfEntitycallBack<JrGxhbBillDTO> callback) {
		// TODO Auto-generated method stub
		theEntity=new JrGxhbBillDTO();
		theEntity.setBill(new JrPdaGxhbbill());
		theEntity.setEntrys(new ArrayList<JrPdaGxhbbillentry>());
		new Thread(){
			@Override
			public void run(){
				RResult result=new RResult();
				String response="";
				try{
//					ManufactureRecRequest request=new ManufactureRecRequest(id+"");
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
					
					theEntity=new JrGxhbBillDTO();
					
					intiNewBill();	//初始化数据，加入初始化数据
					callback.popSucess(theEntity);
				}catch (SelfDefException selfDef){
					callback.popFailure(selfDef.getMessage());
				}catch (JSONException e){
					callback.popFailure(e.getMessage());
				}catch(Exception e){
					callback.popFailure("无法加载单据");
				}
			}
		}.start();
	}

	public void iSetData(JrGxhbBillDTO entity) {
		// TODO Auto-generated method stub
		this.theEntity=(JrGxhbBillDTO)entity;
	}

	public void onSava(final IfEntitycallBack<JrGxhbBillDTO> callback) {
		// TODO Auto-generated method stub
		new Thread(){
			@Override
			public void run(){
				RResult result=new RResult();
				String response="";
				try{
					GxhbRecRequest request=new GxhbRecRequest();
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
	

	public void onSubmit(final IfEntitycallBack<JrGxhbBillDTO> callback) {
		// TODO Auto-generated method stub
		new Thread(){
			@Override
			public void run(){
				RResult result=new RResult();
				String response="";
				try{
					GxhbRecRequest request=new GxhbRecRequest();
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
	
	protected void intiNewBill() throws Exception{  //初始化数据，加入初始化数据
		JrPdaGxhbbill tmpbill=this.theEntity.getBill();
		tmpbill.setFcreateuserid(GI.SESSION.getfUserID()+"");
		tmpbill.setFcreateusername(GI.SESSION.getfUserName());
		this.theEntity.setBill(tmpbill);
		this.theEntity.setEntrys(new ArrayList<JrPdaGxhbbillentry>());
		this.theEntity.setYgEntry(new ArrayList<JrPdaGxhbYgentry>());
		
		
	}

	@Override
	public void igetData(final IfLoadDataCallbak callback) {
		// TODO Auto-generated method stub
		theEntity=new JrGxhbBillDTO();
		theEntity.setBill(new JrPdaGxhbbill());
		theEntity.setEntrys(new ArrayList<JrPdaGxhbbillentry>());
		new Thread(){
			@Override
			public void run(){
				RResult result=new RResult();
				String response="";
				try{
					GxhbRecRequest request=new GxhbRecRequest("");
					String requestJSON=request.toString();
						response=HttpFun.doPost(request.iGetBillURL(), requestJSON);

					if(null==response){
						throw new SelfDefException(SelfDefException.noReponse);
					}
					result=new RResult(response);					
					if(!result.isFlag()){
						throw new SelfDefException(result.getMessage());
					}					
					request.decode(result.getData());
					theEntity=request.getDtoObj();
					intiNewBill();	//初始化数据，加入初始化数据
					callback.popSucess(theEntity);
				}catch (SelfDefException selfDef){
					callback.popFailure(selfDef.getMessage());
				}catch (JSONException e){
					callback.popFailure(e.getMessage());
				}catch(Exception e){
					callback.popFailure("无法加载单据");
				}
			}
		}.start();
	}

	
	
	/* (non-Javadoc)
	 * @see com.lf.contract.ABaseBillContract.IfBasebillModel#igetData(com.lf.contract.CallbackContract.IfLoadDataCallbak, int)
	 */
	@Override
	public void igetData(final IfLoadDataCallbak callback,final int id) {
		// TODO Auto-generated method stub
		theEntity=new JrGxhbBillDTO();
		theEntity.setBill(new JrPdaGxhbbill());
		theEntity.setEntrys(new ArrayList<JrPdaGxhbbillentry>());
		new Thread(){
			@Override
			public void run(){
				RResult result=new RResult();
				String response="";
				try{
					GxhbRecRequest request=new GxhbRecRequest(id+"");
					String requestJSON=request.toString();
						response=HttpFun.doPost(request.iGetBillURL(), requestJSON);

					if(null==response){
						throw new SelfDefException(SelfDefException.noReponse);
					}
					result=new RResult(response);					
					if(!result.isFlag()){
						throw new SelfDefException(result.getMessage());
					}					
					request.decode(result.getData());
					theEntity=request.getDtoObj();
					intiNewBill();	//初始化数据，加入初始化数据
					callback.popSucess(theEntity);
				}catch (SelfDefException selfDef){
					callback.popFailure(selfDef.getMessage());
				}catch (JSONException e){
					callback.popFailure(e.getMessage());
				}catch(Exception e){
					callback.popFailure("无法加载单据");
				}
			}
		}.start();
	}
	@Override
	public <T> void iSetData(T entity) {
		// TODO Auto-generated method stub
		this.theEntity=(JrGxhbBillDTO)entity;
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
					GxhbRecRequest request=new GxhbRecRequest();
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
					GxhbRecRequest request=new GxhbRecRequest();
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

	@Override
	public void onloadsrcDTO(final String srcbillno, final IfSrcDataCallbakMsg<JrGxpgbillDTO> callbak) {
		// TODO Auto-generated method stub
		new Thread(){
			@Override
			public void run(){
				RResult result=new RResult();
				String response="";
				try{
					GxhbRecRequest request=new GxhbRecRequest();
					request.setFbarCode(srcbillno);
					response=HttpFun.doPost(request.iGetBzdjURL(), request.toString());
					if(null==response){
						throw new SelfDefException(SelfDefException.noReponse);
					}
					result=new RResult(response);					
					if(!result.isFlag()){
						throw new SelfDefException(result.getMessage());
					}					
					request.decodeSrcDTO(result.getData());
					srcDTO=request.getSrcDTO();
					callbak.popSucess("解析成功！",srcDTO);
				}catch (SelfDefException selfDef){
					callbak.popFailure(selfDef.getMessage());
				}catch (JSONException e){
					callbak.popFailure(e.getMessage());
				}catch(Exception e){
					callbak.popFailure("无法加载源单。");
				}
			}
		}.start();
	}

	
}
