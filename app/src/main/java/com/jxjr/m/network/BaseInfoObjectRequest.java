package com.jxjr.m.network;

import java.util.List;

import com.alibaba.fastjson.JSONException;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;

public class BaseInfoObjectRequest extends Response {

	final String decodeURL=Constance.webprefix+"/base-data!queryDynamicByCode.action";
	final String selectURL=Constance.webprefix+"/base-data!queryDynamicByStr.action";
	final String foreignIDURL=Constance.webprefix+"/base-data!queryWarehouseByLocation.action";
	final String selectLocationURL=Constance.webprefix+"/base-data!queryLocationByWarehouseParam";
	final String decodeLocationURL=Constance.webprefix+"/base-data!queryLocationByWarehouseCode";
	
	
	private String fparam;
	private String fbarCode;
	private String ftype;
	private String fwarehouseid;
	private BaseInfoObjectDTO dtoObj;	
	private List<BaseInfoObjectDTO> dtoList;
	
	public BaseInfoObjectRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseInfoObjectRequest(String fparam, String fbarCode, String ftype,
			BaseInfoObjectDTO dtoObj) {
		super();
		this.fparam = fparam;
		this.fbarCode = fbarCode;
		this.ftype = ftype;
		this.dtoObj = dtoObj;
	}


	/**
	 * @return the fparam
	 */
	public String getFparam() {
		return fparam;
	}

	/**
	 * @param fparam the fparam to set
	 */
	public void setFparam(String fparam) {
		this.fparam = fparam;
	}

	/**
	 * @return the fbarCode
	 */
	public String getFbarCode() {
		return fbarCode;
	}

	/**
	 * @param fbarCode the fbarCode to set
	 */
	public void setFbarCode(String fbarCode) {
		this.fbarCode = fbarCode;
	}

	/**
	 * @return the ftype
	 */
	public String getFtype() {
		return ftype;
	}

	/**
	 * @param ftype the ftype to set
	 */
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	public String getFwarehouseid() {
		return fwarehouseid;
	}

	public void setFwarehouseid(String fwarehouseid) {
		this.fwarehouseid = fwarehouseid;
	}

	/**
	 * @return the dtoObj
	 */
	public BaseInfoObjectDTO getDtoObj() {
		return dtoObj;
	}

	/**
	 * @param dtoObj the dtoObj to set
	 */
	public void setDtoObj(BaseInfoObjectDTO dtoObj) {
		this.dtoObj = dtoObj;
	}

	/**
	 * @return the dtoList
	 */
	public List<BaseInfoObjectDTO> getDtoList() {
		return dtoList;
	}

	/**
	 * @param dtoList the dtoList to set
	 */
	public void setDtoList(List<BaseInfoObjectDTO> dtoList) {
		this.dtoList = dtoList;
	}

	public String iGetDecodeURL(){			//解析条码URL
		String url=GI.HOSThttp+decodeURL;
		return igetURL(url);
	}
	
	public String iGetSelectURL(){
		String url=GI.HOSThttp+selectURL;	//查询名称或编号URL
		return igetURL(url);
	}
	
	public String iGetselectLocationURL(){
		String url=GI.HOSThttp+selectLocationURL;	//查询仓位列表
		return igetURL(url);
	}
	
	public String iGetdecodeLocationURL(){
		String url=GI.HOSThttp+decodeLocationURL;	//查询仓位名称
		return igetURL(url);
	}
	
	public String iGetForeignIDURL(){			//解析其他基础资料
		String url=GI.HOSThttp+foreignIDURL;
		return igetURL(url);
	}
	
	@Override
	public void decode(String data) throws JSONException {
		// TODO Auto-generated method stub
		this.dtoObj=super.getObj(data, BaseInfoObjectDTO.class);
	}
	
	public void decodeList(String data)throws JSONException {
		this.dtoList=super.getObjList(data, BaseInfoObjectDTO.class);
	}
}
