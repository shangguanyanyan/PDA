package com.jxjr.m.DTO;

import java.util.List;

public class JrYlqdBillDTO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String fid;
	private String fbillNo;
	private String fdeptId;
	private String fdeptNumber;
	private String fdeptName;

		
	public JrYlqdBillDTO() {
		// TODO 自动生成的构造函数存根
	}


	public JrYlqdBillDTO(String fid, String fbillNo, String fdeptId,
			String fdeptNumber, String fdeptName) {
		super();
		this.fid = fid;
		this.fbillNo = fbillNo;
		this.fdeptId = fdeptId;
		this.fdeptNumber = fdeptNumber;
		this.fdeptName = fdeptName;
	}


	public String getFid() {
		return fid;
	}


	public void setFid(String fid) {
		this.fid = fid;
	}


	public String getFbillNo() {
		return fbillNo;
	}


	public void setFbillNo(String fbillNo) {
		this.fbillNo = fbillNo;
	}


	public String getFdeptId() {
		return fdeptId;
	}


	public void setFdeptId(String fdeptId) {
		this.fdeptId = fdeptId;
	}


	public String getFdeptNumber() {
		return fdeptNumber;
	}


	public void setFdeptNumber(String fdeptNumber) {
		this.fdeptNumber = fdeptNumber;
	}


	public String getFdeptName() {
		return fdeptName;
	}


	public void setFdeptName(String fdeptName) {
		this.fdeptName = fdeptName;
	}



}
