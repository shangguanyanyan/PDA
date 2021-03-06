package com.jxjr.m.network;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.jxjr.m.DTO.JrManufactureRecBillDTO;
import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;

public class MaterialBaseInfoRequest {
	
	final String decodeURL=Constance.webprefix+"/base-data!queryMaterialByCode.action";
	final String selectURL=Constance.webprefix+"/base-data!queryMaterialByStr.action";
	
	private String deviceID=GI.deviceId;	
	private String fuserID;	
	private String fparam;
	private String fbarCode;
	private String ftype="normal";
	private JrMaterialBaseInfoDTO dtoObj;
	private List<JrMaterialBaseInfoDTO> dtoList;
	
	public MaterialBaseInfoRequest() {
		// TODO Auto-generated constructor stub
	}	

	public MaterialBaseInfoRequest(String deviceID, String fuserID,
			String fparam, String fbarCode, String ftype,
			JrMaterialBaseInfoDTO dtoObj, List<JrMaterialBaseInfoDTO> dtoList) {
		super();
		this.deviceID = deviceID;
		this.fuserID = fuserID;
		this.fparam = fparam;
		this.fbarCode = fbarCode;
		this.ftype = ftype;
		this.dtoObj = dtoObj;
		this.dtoList = dtoList;
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

	public MaterialBaseInfoRequest(String fparam) {
		super();
		this.fparam = fparam;
	}

	/**
	 * @return the deviceID
	 */
	public String getDeviceID() {
		return deviceID;
	}
	/**
	 * @param deviceID the deviceID to set
	 */
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	/**
	 * @return the fuserID
	 */
	public String getFuserID() {
		if(GI.SESSION!=null){
			return fuserID=Integer.toString(GI.SESSION.getfUserID());
		}else{
			return fuserID;
		}
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
	 * @return the dtoObj
	 */
	public JrMaterialBaseInfoDTO getDtoObj() {
		return dtoObj;
	}
	/**
	 * @param dtoObj the dtoObj to set
	 */
	public void setDtoObj(JrMaterialBaseInfoDTO dtoObj) {
		this.dtoObj = dtoObj;
	}	
	
	/**
	 * @return the dtoList
	 */
	public List<JrMaterialBaseInfoDTO> getDtoList() {
		return dtoList;
	}


	/**
	 * @param dtoList the dtoList to set
	 */
	public void setDtoList(List<JrMaterialBaseInfoDTO> dtoList) {
		this.dtoList = dtoList;
	}


	public String igetURL(String url){
		return url;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSON.toJSONString(this,SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNonStringKeyAsString,
				SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullStringAsEmpty);
	}
	
	public <T> T getObj(String data, Class<T> clazz) {
		return JSON.parseObject(data, clazz);
	}
	
	public <T> List<T> getObjList(String data, Class<T> clazz) {
		return JSON.parseArray(data, clazz);
	}
	
	public String iGetDecodeURL(){			//解析物料条码URL
		String url=GI.HOSThttp+decodeURL;
		return igetURL(url);
	}
	
	public String iGetSelectURL(){
		String url=GI.HOSThttp+selectURL;	//查询物料名称或编号
//		String url=GI.HOSThttp+decodeURL;	//查询物料名称或编号
		return igetURL(url);
	}
	
	public void decode(String data) throws JSONException {
		// TODO Auto-generated method stub
		this.dtoObj=getObj(data, JrMaterialBaseInfoDTO.class);
	}
	
	public void decodeList(String data) throws JSONException {
		// TODO Auto-generated method stub
		this.dtoList=getObjList(data, JrMaterialBaseInfoDTO.class);
	}
}
