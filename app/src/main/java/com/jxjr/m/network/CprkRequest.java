package com.jxjr.m.network;

import java.util.List;

import com.alibaba.fastjson.JSONException;
import com.jxjr.m.DTO.JrCpbzdjBillDTO;
import com.jxjr.m.DTO.JrCprkBillDTO;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;

public class CprkRequest extends Response {

//	final String billURL=Constance.webprefix+"/jr-manufacture-rec-bill!inputBill.action";
	final String saveURL=Constance.webprefix+"/jr-scrkd!saveBill.action";
	final String submitURL=Constance.webprefix+"/jr-scrkd!submitBill.action";
	final String checkSaveURL=Constance.webprefix+"/jr-scrkd!checkSave.action"; 
	String id;
	JrCprkBillDTO dtoObj;	
	
	private String fbarCode;
	
	List<JrCpbzdjBillDTO> srcList;
	
	JrCpbzdjBillDTO srcDTO;

	public CprkRequest() {
		// TODO Auto-generated constructor stub
		super();
	}


	public CprkRequest(String id) {
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
	public JrCprkBillDTO getDtoObj() {
		return dtoObj;
	}


	public List<JrCpbzdjBillDTO> getSrcList() {
		return srcList;
	}


	public void setSrcList(List<JrCpbzdjBillDTO> srcList) {
		this.srcList = srcList;
	}

	public JrCpbzdjBillDTO getSrcDTO() {
		return srcDTO;
	}


	public void setSrcDTO(JrCpbzdjBillDTO srcDTO) {
		this.srcDTO = srcDTO;
	}


	/**
	 * @param dtoObj the dtoObj to set
	 */
	public void setDtoObj(JrCprkBillDTO dtoObj) {
		this.dtoObj = dtoObj;
	}
	public String getFbarCode() {
		return fbarCode;
	}


	public void setFbarCode(String fbarCode) {
		this.fbarCode = fbarCode;
	}


//	public String iGetBillURL(){		//新增和修改
//		String url=GI.HOSThttp+billURL;
//		return igetURL(url);
//	}
	
	public String iGetSaveURL(){
		String url=GI.HOSThttp+saveURL;		//保存
		return igetURL(url);
	}

	public String iGetSubmitURL(){
		String url=GI.HOSThttp+submitURL;		//保存
		return igetURL(url);
	}
	
	public String iGetSaveCheckURL(){
		String url=GI.HOSThttp+checkSaveURL;			//保存校验
		return igetURL(url);
	}
	
	
	@Override
	public void decode(String data) throws JSONException {
		// TODO Auto-generated method stub
		this.dtoObj=super.getObj(data, JrCprkBillDTO.class);
	}
	
	public void decodeSrcList(String data)  throws JSONException {
		this.srcList=super.getObjList(data, JrCpbzdjBillDTO.class);
	}
	
	//源单解析
	public void decodeSrcDTO(String data)  throws JSONException {
		this.srcDTO=super.getObj(data, JrCpbzdjBillDTO.class);
	}
}
