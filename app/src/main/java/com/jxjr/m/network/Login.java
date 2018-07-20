package com.jxjr.m.network;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.jxjr.m.DTO.LoginResult;
import com.jxjr.utility.Constance;
import com.jxjr.utility.DateFun;
import com.jxjr.utility.GI;

//import com.kingdee.flt.DTO.LoginResult;

public class Login extends Response{
	
	final String tologinURL= Constance.webprefix+"/login!actionMain.action";
	
	private String username;
	private String password;
	private String facctID;
	private String deviceID=GI.deviceId;
	private String localdate=DateFun.Date2String(new Date(), "yyyy-MM-dd HH:mm:ss"); //GI.localdate;
	private LoginResult loginResult;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
		

	public String getFacctID() {
		return facctID;
	}
	public void setFacctID(String facctID) {
		this.facctID = facctID;
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
	 * @return the localdate
	 */
	public String getLocaldate() {
		return localdate;
	}
	/**
	 * @param localdate the localdate to set
	 */
	public void setLocaldate(String localdate) {
		this.localdate = localdate;
	}
	public LoginResult getLoginResult() {
		return loginResult;
	}
	
	public void setLoginResult(LoginResult loginResult) {
		this.loginResult = loginResult;
	}

	public Login(String username, String password,String facctID) {
		super();
		this.username = username;
		this.password = password;
		this.facctID=facctID;
	}
	
	public Login(){
	}
	
	
	@Override
	public void decode(String response) {
		// TODO Auto-generated method stub
		this.loginResult=JSON.parseObject(response,LoginResult.class);
	}

	public String iGetLoginURL(){
		String url=GI.HOSThttp+tologinURL;
		return igetURL(url);
	}
	
}