package com.jxjr.m.DTO;

public class RightObjectDTO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fisall;
	private String fmodeName;
	private String frightValue;
	
	/**
	 * 
	 */
	public RightObjectDTO() {
	}

	public RightObjectDTO(String fisall, String fmodeName, String frightValue) {
		super();
		this.fisall = fisall;
		this.fmodeName = fmodeName;
		this.frightValue = frightValue;
	}

	public String getFisall() {
		return fisall;
	}

	public void setFisall(String fisall) {
		this.fisall = fisall;
	}

	public String getFmodeName() {
		return fmodeName;
	}

	public void setFmodeName(String fmodeName) {
		this.fmodeName = fmodeName;
	}

	public String getFrightValue() {
		return frightValue;
	}

	public void setFrightValue(String frightValue) {
		this.frightValue = frightValue;
	}

	

}