package com.jxjr.m.network;

import java.util.List;

import com.alibaba.fastjson.JSONException;
import com.jxjr.m.DTO.JrGxpgbillDTO;
import com.jxjr.m.DTO.JrGxhbBillDTO;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;

public class GxhbRecRequest extends Response {

	final String billURL=Constance.webprefix+"/jr-manufacture-rec-bill!inputBill.action";
	final String saveURL=Constance.webprefix+"/jr-process-report!saveBill.action";
	final String submitURL=Constance.webprefix+"/jr-process-report!submitBill.action";
	final String bzdjURL=Constance.webprefix+"/jr-manufacture-rec-bill!queryManufactureTaskBillByCode.action"; 
	String id;
	JrGxhbBillDTO dtoObj;	
	
	private String fbarCode;
	
	List<JrGxpgbillDTO> srcList;
	
	JrGxpgbillDTO srcDTO;

	public GxhbRecRequest() {
		// TODO Auto-generated constructor stub
		super();
	}


	public GxhbRecRequest(String id) {
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
	public JrGxhbBillDTO getDtoObj() {
		return dtoObj;
	}


	public List<JrGxpgbillDTO> getSrcList() {
		return srcList;
	}


	public void setSrcList(List<JrGxpgbillDTO> srcList) {
		this.srcList = srcList;
	}

	public JrGxpgbillDTO getSrcDTO() {
		return srcDTO;
	}


	public void setSrcDTO(JrGxpgbillDTO srcDTO) {
		this.srcDTO = srcDTO;
	}


	/**
	 * @param dtoObj the dtoObj to set
	 */
	public void setDtoObj(JrGxhbBillDTO dtoObj) {
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
		this.dtoObj=super.getObj(data, JrGxhbBillDTO.class);
	}
	
	public void decodeSrcList(String data)  throws JSONException {
		this.srcList=super.getObjList(data, JrGxpgbillDTO.class);
	}
	
	//源单解析
	public void decodeSrcDTO(String data)  throws JSONException {
		this.srcDTO=super.getObj(data, JrGxpgbillDTO.class);
	}
}
