package com.jxjr.m.DTO;

import java.util.List;

public class JrXsckSrcBillDTO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String fid;
	private String fbillNo;
	private String fcustId;
	private String fcustNumber;
	private String fcustName;
	private String fdeptId;
	private String fdeptNumber;
	private String fdeptName;
	private String fempId;
	private String fempNumber;
	private String fempName;

		

	public JrXsckSrcBillDTO() {
		// TODO 自动生成的构造函数存根
	}



	public JrXsckSrcBillDTO(String fid, String fbillNo, String fcustId,
			String fcustNumber, String fcustName, String fdeptId,
			String fdeptNumber, String fdeptName, String fempId,
			String fempNumber, String fempName) {
		super();
		this.fid = fid;
		this.fbillNo = fbillNo;
		this.fcustId = fcustId;
		this.fcustNumber = fcustNumber;
		this.fcustName = fcustName;
		this.fdeptId = fdeptId;
		this.fdeptNumber = fdeptNumber;
		this.fdeptName = fdeptName;
		this.fempId = fempId;
		this.fempNumber = fempNumber;
		this.fempName = fempName;
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



	public String getFcustId() {
		return fcustId;
	}



	public void setFcustId(String fcustId) {
		this.fcustId = fcustId;
	}



	public String getFcustNumber() {
		return fcustNumber;
	}



	public void setFcustNumber(String fcustNumber) {
		this.fcustNumber = fcustNumber;
	}



	public String getFcustName() {
		return fcustName;
	}



	public void setFcustName(String fcustName) {
		this.fcustName = fcustName;
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



	public String getFempId() {
		return fempId;
	}



	public void setFempId(String fempId) {
		this.fempId = fempId;
	}



	public String getFempNumber() {
		return fempNumber;
	}



	public void setFempNumber(String fempNumber) {
		this.fempNumber = fempNumber;
	}



	public String getFempName() {
		return fempName;
	}



	public void setFempName(String fempName) {
		this.fempName = fempName;
	}



	
}
