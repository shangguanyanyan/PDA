package com.jxjr.m.network;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jxjr.utility.DateFun;

/**
 * @author zhang
 * 定义response返回对象
 * */
public class RResult {

	private static final long serialVersionUID = 1L;
	//操作结果
	boolean flag;
	//错误码,根据配置表查询异常信息
	String message;
	//返回结果封装jsonobject
	String data;
	//返回服务端时间
	String serverdate;
	//返回服务端鉴权
	String token;
	
	public RResult(){
		
	}
	
	public RResult(String response){
		if(response!=null){
			JSONObject obj=JSONObject.parseObject(response);
			this.flag=obj.getBooleanValue("flag");
			this.message=obj.getString("message");
			this.data=obj.getString("data");
			this.serverdate=obj.getString("serverdate");
			this.token=obj.getString("token");
		}
	}
	
	public boolean isFlag() {
		return flag;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getServerdate() {
		return serverdate;
	}

	public void setServerdate(String serverdate) {
		this.serverdate = serverdate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}