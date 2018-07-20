package com.jxjr.m.network;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;

public class BaseDataDownlodad extends Response {
	
	final String baseDataDLURL=Constance.webprefix+"/login!queryBaseData.action";

	String deviceID=GI.deviceId;
	List<BaseInfoObjectDTO> baseInfoObjectDTO;
	
	
	public BaseDataDownlodad() {
		// TODO Auto-generated constructor stub
		super();
	}

	public BaseDataDownlodad(String deviceID) {
		super();
		this.deviceID = deviceID;
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

	public List<BaseInfoObjectDTO> getBaseInfoObjectDTO() {
		return baseInfoObjectDTO;
	}

	public void setBaseInfoObjectDTO(List<BaseInfoObjectDTO> baseInfoObjectDTO) {
		this.baseInfoObjectDTO = baseInfoObjectDTO;
	}
	

	@Override
	public void decode(String data) {
		// TODO Auto-generated method stub
		this.baseInfoObjectDTO=JSON.parseArray(data,BaseInfoObjectDTO.class);
		
	}
	
	public String iGetAction(){
		String url=GI.HOSThttp+baseDataDLURL;
		return igetURL(url);
	}

}
