package com.jxjr.m.network;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jxjr.utility.GI;
import com.jxjr.utility.StringUtils;

public abstract class Response{

	public Response() {
	}

	protected String deviceID=GI.deviceId;
	
	protected String fuserID;	
	protected String fpassword;	
	protected String facctID;
	
	protected String forgid;
	
	
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
		
	public String getFpassword() {
		if(GI.SESSION!=null){
			return fpassword=GI.SESSION.getfPassword();
		}else{
			return fpassword;
		}
	}


	public String getFacctID() {
		if(GI.SESSION!=null){
			return facctID=GI.SESSION.getfAcctID();
		}else{
			return facctID;
		}
	}

	public String getForgid() {
		return StringUtils.cnulls(this.forgid, GI.forgid);
	}

	public void setForgid(String forgid) {
		this.forgid = forgid;
	}

	public String igetURL(String url){
		return url;
	}

	public abstract void decode(String data) throws JSONException;	
	/**
	 * @return the deviceID
	 */
	public String getDeviceID() {
		return deviceID;
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

//	public String toString(Object obj){
//		return JSON.toJSONString(obj,SerializerFeature.WriteMapNullValue,
//				SerializerFeature.WriteNonStringKeyAsString,
//				SerializerFeature.WriteNullListAsEmpty,
//				SerializerFeature.WriteNullNumberAsZero,
//				SerializerFeature.WriteNullStringAsEmpty);
//	}
	
	public <T> T getObj(String data, Class<T> clazz) {
		return JSON.parseObject(data, clazz);
	}
	
	public <T> List<T> getObjList(String data, Class<T> clazz) {
		return JSON.parseArray(data, clazz);
	}
//	
//	public abstract String dtoObj2String();
}
