package com.jxjr.m;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.jxjr.contract.CallbackContract.IfListCallbak;
import com.jxjr.contract.CprkContract;
import com.jxjr.contract.CallbackContract.IfLoadDataCallbak;
import com.jxjr.contract.CallbackContract.IfSaveCallbak;
import com.jxjr.contract.CallbackContract.IfSubmitCallbak;
import com.jxjr.m.DTO.JrCprkBillDTO;
import com.jxjr.m.DTO.JrCpbzdjBillDTO;
import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.m.entity.JrPdaCprkbill;
import com.jxjr.m.entity.JrPdaCprkbillentry;
import com.jxjr.m.network.CprkRequest;
import com.jxjr.m.network.RResult;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;
import com.jxjr.utility.HttpFun;
import com.jxjr.utility.SelfDefException;
import com.jxjr.utility.StringUtils;
import com.jxjr.utility.TxtReader;
import com.jxjr.v.activity.CprkActivity;
import com.jxjr.v.fragment.ABaseFTBillHdFrg;

//public class ManufactureInstockModel extends ABaseBillModel<JrManufactureRecBillDTO> {
public class CprkModel implements CprkContract.IfCprkModel{
	
	Boolean c_releaseMode=true;
	JrCprkBillDTO theEntity;	
	
	JrCpbzdjBillDTO srcDTO;
	List<JrCpbzdjBillDTO> srcList;
	public CprkModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Override
	public void igetData(IfLoadDataCallbak callback) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void igetData(IfLoadDataCallbak callback, int id) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public <T> void iSetData(T entity) {
		// TODO Auto-generated method stub
		this.theEntity=(JrCprkBillDTO)entity;
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
					CprkRequest request=new CprkRequest();
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
					CprkRequest request=new CprkRequest();
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
		JrPdaCprkbill tmpbill=this.theEntity.getBill();
		tmpbill.setFcreateuserid(GI.SESSION.getfUserID()+"");
		tmpbill.setFcreateusername(GI.SESSION.getfUserName());
//		tmpbill.setFdeptId(GI.SESSION.getfBmID()+"");
//		tmpbill.setFdeptName(GI.SESSION.getfBmName());
		this.theEntity.setBill(tmpbill);
	}

	@Override
	public void onloadsrcDTO(final String srcbillno, final IfListCallbak callback) {
		// TODO Auto-generated method stub
//		new Thread(){
//			@Override
//			public void run(){
//				RResult result=new RResult();
//				String response="";
//				try{
//					CprkRequest request=new CprkRequest();
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
	}

	
}
