package com.jxjr.m.network;

import java.util.List;

import com.alibaba.fastjson.JSONException;
import com.jxjr.m.DTO.JrBcprkBillDTO;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;

public class BcprkRequest extends Response {

	final String billURL=Constance.webprefix+"/jr-manufacture-rec-bill!inputBill.action";
	final String saveURL=Constance.webprefix+"/jr-bcp-scrkd!saveBill.action";
	final String submitURL=Constance.webprefix+"/jr-bcp-scrkd!submitBill.action";
	final String bzdjURL=Constance.webprefix+"/jr-manufacture-rec-bill!queryManufactureTaskBillByCode.action"; 
	String id;
	JrBcprkBillDTO dtoObj;	
	
	private String fbarCode;

	public BcprkRequest() {
		// TODO Auto-generated constructor stub
		super();
	}


	public BcprkRequest(String id) {
		super();
		this.id = id;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the dtoObj
	 */
	public JrBcprkBillDTO getDtoObj() {
		return dtoObj;
	}


	/**
	 * @param dtoObj the dtoObj to set
	 */
	public void setDtoObj(JrBcprkBillDTO dtoObj) {
		this.dtoObj = dtoObj;
	}
	public String getFbarCode() {
		return fbarCode;
	}


	public void setFbarCode(String fbarCode) {
		this.fbarCode = fbarCode;
	}


	public String iGetBillURL(){		//新增和修改
		String url=GI.HOSThttp+billURL;
		return igetURL(url);
	}
	
	public String iGetSaveURL(){
		String url=GI.HOSThttp+saveURL;		//保存
		return igetURL(url);
	}

	public String iGetSubmitURL(){
		String url=GI.HOSThttp+submitURL;		//保存
		return igetURL(url);
	}
	
	public String iGetBzdjURL(){
		String url=GI.HOSThttp+bzdjURL;			//查询源单
		return igetURL(url);
	}
	
	
	@Override
	public void decode(String data) throws JSONException {
		// TODO Auto-generated method stub
		this.dtoObj=super.getObj(data, JrBcprkBillDTO.class);
	}
	
	
}
