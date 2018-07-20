package com.jxjr.m.network;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrBcprkSrcBillDTO;
import com.jxjr.m.DTO.JrCpbzdjBillDTO;
import com.jxjr.m.DTO.JrXsckSrcBillDTO;
import com.jxjr.m.DTO.JrYlqdBillDTO;
//import com.jxjr.m.DTO.JrManufactureRecBillDTO;
import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;

public class BaseDataRequest {
	
	final String decodeURL=Constance.webprefix+"/base-data!queryDynamicByCode.action";
	final String matURL=Constance.webprefix+"/base-data!queryMaterialByStr.action";
	final String cprkURL=Constance.webprefix+"/jr-scrkd!queryJrCpbzdjEntryByCode.action";
	final String xsckURL=Constance.webprefix+"/jr-xsckd!queryJrCpbzdjEntryByCode.action";
	final String xsckSrcURL=Constance.webprefix+"/jr-xsckd!queryJrFhtzdByCode.action";
	final String cprkSrcURL=Constance.webprefix+"/jr-scrkd!queryJrCpbzdjByCode.action";
	final String scllSrcURL=Constance.webprefix+"/jr-sclld!queryJrYlqdByCode.action";
	final String bcprkURL=Constance.webprefix+"/jr-bcp-scrkd!queryJrGxpgdByCode.action";
	
	private String deviceID=GI.deviceId;	
	private String fuserID;	
	private String fparam;
	private String fbarCode;
	private String ftype="normal";
	private String forgid;
	private BaseInfoObjectDTO dtoObj;
	private List<BaseInfoObjectDTO> dtoList;
	private JrMaterialBaseInfoDTO matDtoObj;
	private JrCpbzdjBillDTO thDtoObj;
	private JrXsckSrcBillDTO xsckSrcDtoObj;
	private JrYlqdBillDTO scllSrcDtoObj;
	private JrBcprkSrcBillDTO bcprkSrcDtoObj;
	private String fcpbzdjNo;
	private String ffhtzdNo;
	private String fylqdNo;
	
	public BaseDataRequest() {
		// TODO Auto-generated constructor stub
	}	


	
	public BaseDataRequest(String deviceID, String fuserID, String fparam,
			String fbarCode, String ftype, String forgid,
			BaseInfoObjectDTO dtoObj, List<BaseInfoObjectDTO> dtoList,
			JrMaterialBaseInfoDTO matDtoObj, JrCpbzdjBillDTO thDtoObj,
			JrXsckSrcBillDTO xsckSrcDtoObj, JrYlqdBillDTO scllSrcDtoObj,
			JrBcprkSrcBillDTO bcprkSrcDtoObj, String fcpbzdjNo,
			String ffhtzdNo, String fylqdNo) {
		super();
		this.deviceID = deviceID;
		this.fuserID = fuserID;
		this.fparam = fparam;
		this.fbarCode = fbarCode;
		this.ftype = ftype;
		this.forgid = forgid;
		this.dtoObj = dtoObj;
		this.dtoList = dtoList;
		this.matDtoObj = matDtoObj;
		this.thDtoObj = thDtoObj;
		this.xsckSrcDtoObj = xsckSrcDtoObj;
		this.scllSrcDtoObj = scllSrcDtoObj;
		this.bcprkSrcDtoObj = bcprkSrcDtoObj;
		this.fcpbzdjNo = fcpbzdjNo;
		this.ffhtzdNo = ffhtzdNo;
		this.fylqdNo = fylqdNo;
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

	public BaseDataRequest(String fparam) {
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
	
	public String getForgid() {
		return forgid;
	}

	public void setForgid(String forgid) {
		this.forgid = forgid;
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



	public JrMaterialBaseInfoDTO getMatDtoObj() {
		return matDtoObj;
	}

	public void setMatDtoObj(JrMaterialBaseInfoDTO matDtoObj) {
		this.matDtoObj = matDtoObj;
	}

	
	
	public JrCpbzdjBillDTO getThDtoObj() {
		return thDtoObj;
	}

	public void setThDtoObj(JrCpbzdjBillDTO thDtoObj) {
		this.thDtoObj = thDtoObj;
	}
	
	public JrXsckSrcBillDTO getXsckSrcDtoObj() {
		return xsckSrcDtoObj;
	}

	public void setXsckSrcDtoObj(JrXsckSrcBillDTO xsckSrcDtoObj) {
		this.xsckSrcDtoObj = xsckSrcDtoObj;
	}
	
	

	public JrYlqdBillDTO getScllSrcDtoObj() {
		return scllSrcDtoObj;
	}


	public void setScllSrcDtoObj(JrYlqdBillDTO scllSrcDtoObj) {
		this.scllSrcDtoObj = scllSrcDtoObj;
	}


	public JrBcprkSrcBillDTO getBcprkSrcDtoObj() {
		return bcprkSrcDtoObj;
	}



	public void setBcprkSrcDtoObj(JrBcprkSrcBillDTO bcprkSrcDtoObj) {
		this.bcprkSrcDtoObj = bcprkSrcDtoObj;
	}



	public String getFcpbzdjNo() {
		return fcpbzdjNo;
	}

	public void setFcpbzdjNo(String fcpbzdjNo) {
		this.fcpbzdjNo = fcpbzdjNo;
	}
	
	

	public String getFfhtzdNo() {
		return ffhtzdNo;
	}

	public void setFfhtzdNo(String ffhtzdNo) {
		this.ffhtzdNo = ffhtzdNo;
	}

	
	public String getFylqdNo() {
		return fylqdNo;
	}


	public void setFylqdNo(String fylqdNo) {
		this.fylqdNo = fylqdNo;
	}


	public void setFuserID(String fuserID) {
		this.fuserID = fuserID;
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
		String url=GI.HOSThttp+matURL;	//查询物料名称或编号
//		String url=GI.HOSThttp+decodeURL;	//查询物料名称或编号
		return igetURL(url);
	}
	
	public String iGetCprkURL(){
		String url=GI.HOSThttp+cprkURL;	//查询物料名称或编号
//		String url=GI.HOSThttp+decodeURL;	//查询物料名称或编号
		return igetURL(url);
	}
	
	public String iGetXsckURL(){
		String url=GI.HOSThttp+xsckURL;	//查询物料名称或编号
//		String url=GI.HOSThttp+decodeURL;	//查询物料名称或编号
		return igetURL(url);
	}
	
	public String iGetXsckSrcURL(){
		String url=GI.HOSThttp+xsckSrcURL;	//查询物料名称或编号
//		String url=GI.HOSThttp+decodeURL;	//查询物料名称或编号
		return igetURL(url);
	}
	
	public String iGetCprkSrcURL(){
		String url=GI.HOSThttp+cprkSrcURL;	//查询物料名称或编号
//		String url=GI.HOSThttp+decodeURL;	//查询物料名称或编号
		return igetURL(url);
	}
	
	public String iGetScllSrcURL(){
		String url=GI.HOSThttp+scllSrcURL;	//查询物料名称或编号
//		String url=GI.HOSThttp+decodeURL;	//查询物料名称或编号
		return igetURL(url);
	}
	
	public String iGetBcprkURL(){
		String url=GI.HOSThttp+bcprkURL;	//查询物料名称或编号
//		String url=GI.HOSThttp+decodeURL;	//查询物料名称或编号
		return igetURL(url);
	}
	
	public void decode(String data) throws JSONException {
		// TODO Auto-generated method stub
		this.dtoObj=getObj(data, BaseInfoObjectDTO.class);
	}
	
	public void matdecode(String data) throws JSONException {
		// TODO Auto-generated method stub
		this.matDtoObj=getObj(data, JrMaterialBaseInfoDTO.class);
	}
	
	public void thdecode(String data) throws JSONException {
		// TODO Auto-generated method stub
		this.thDtoObj=getObj(data, JrCpbzdjBillDTO.class);
	}
	
	public void xsckSrcdecode(String data) throws JSONException {
		// TODO Auto-generated method stub
		this.xsckSrcDtoObj=getObj(data, JrXsckSrcBillDTO.class);
	}
	
	public void cprkSrcdecode(String data) throws JSONException {
		// TODO Auto-generated method stub
		this.thDtoObj=getObj(data, JrCpbzdjBillDTO.class);
	}
	
	public void scllSrcdecode(String data) throws JSONException {
		// TODO Auto-generated method stub
		this.scllSrcDtoObj=getObj(data, JrYlqdBillDTO.class);
	}
	
	public void bcprkdecode(String data) throws JSONException {
		// TODO Auto-generated method stub
		this.bcprkSrcDtoObj=getObj(data, JrBcprkSrcBillDTO.class);
	}
//	public void decodeList(String data) throws JSONException {
//		// TODO Auto-generated method stub
//		this.dtoList=getObjList(data, JrGxpgbillDTO.class);
//	}
}
