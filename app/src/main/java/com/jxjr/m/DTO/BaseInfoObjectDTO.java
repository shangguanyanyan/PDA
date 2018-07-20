package com.jxjr.m.DTO;

public class BaseInfoObjectDTO implements java.io.Serializable {

	/**
	 * 基础资料字段
	 */
	private static final long serialVersionUID = 1L;
	private String fItemClass;
	private String fItemID;
	private String fNumber;
	private String fName;
	private String fParam;
	private String forgid;

	/**
	 * 
	 */
	public BaseInfoObjectDTO() {
	}

	public BaseInfoObjectDTO(String fItemClass, String fItemID, String fNumber,
			String fName, String fParam) {
		this.fItemClass = fItemClass;
		this.fItemID = fItemID;
		this.fNumber = fNumber;
		this.fName = fName;
		this.fParam = fParam;
	}
	
	public BaseInfoObjectDTO(String fItemClass, String fItemID, String fNumber,
			String fName, String fParam,String forgid) {
		this.fItemClass = fItemClass;
		this.fItemID = fItemID;
		this.fNumber = fNumber;
		this.fName = fName;
		this.fParam = fParam;
		this.forgid=forgid;
	}

	public String getfItemClass() {
		return fItemClass;
	}

	public void setfItemClass(String fItemClass) {
		this.fItemClass = fItemClass;
	}

	public String getfItemID() {
		return fItemID;
	}

	public void setfItemID(String fItemID) {
		this.fItemID = fItemID;
	}

	public String getfNumber() {
		return fNumber;
	}

	public void setfNumber(String fNumber) {
		this.fNumber = fNumber;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getfParam() {
		return fParam;
	}

	public void setfParam(String fParam) {
		this.fParam = fParam;
	}

	public String getForgid() {
		return forgid;
	}

	public void setForgid(String forgid) {
		this.forgid = forgid;
	}

}