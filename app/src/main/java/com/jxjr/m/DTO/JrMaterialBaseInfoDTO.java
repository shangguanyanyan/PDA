package com.jxjr.m.DTO;

import java.math.BigDecimal;
import java.util.Date;

public class JrMaterialBaseInfoDTO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String fid;
	private String fnumber;
	private String fname;
	private String fmodel;
	private String flot;
	private String fbaseUnitId;
	private String fbaseUnitName;
	private String fbaseUnitNumber;
	private String funitId;
	private String funitNumber;
	private String funitName;
	private String fisbatchManage;
	private String funitConvsRate;
	private BigDecimal fqty;
	private BigDecimal fbaseqty;
	private String fbarCode;
	
	public JrMaterialBaseInfoDTO() {
		// TODO 自动生成的构造函数存根
	}

	public JrMaterialBaseInfoDTO(String fid, String fnumber, String fname,
			String fmodel, String flot, String fbaseUnitId,
			String fbaseUnitName, String fbaseUnitNumber, String funitId,
			String funitNumber, String funitName, String fisbatchManage,
			String funitConvsRate, BigDecimal fqty, BigDecimal fbaseqty,
			String fbarCode) {
		super();
		this.fid = fid;
		this.fnumber = fnumber;
		this.fname = fname;
		this.fmodel = fmodel;
		this.flot = flot;
		this.fbaseUnitId = fbaseUnitId;
		this.fbaseUnitName = fbaseUnitName;
		this.fbaseUnitNumber = fbaseUnitNumber;
		this.funitId = funitId;
		this.funitNumber = funitNumber;
		this.funitName = funitName;
		this.fisbatchManage = fisbatchManage;
		this.funitConvsRate = funitConvsRate;
		this.fqty = fqty;
		this.fbaseqty = fbaseqty;
		this.fbarCode = fbarCode;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFnumber() {
		return fnumber;
	}

	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFmodel() {
		return fmodel;
	}

	public void setFmodel(String fmodel) {
		this.fmodel = fmodel;
	}

	public String getFlot() {
		return flot;
	}

	public void setFlot(String flot) {
		this.flot = flot;
	}

	public String getFbaseUnitId() {
		return fbaseUnitId;
	}

	public void setFbaseUnitId(String fbaseUnitId) {
		this.fbaseUnitId = fbaseUnitId;
	}

	public String getFbaseUnitName() {
		return fbaseUnitName;
	}

	public void setFbaseUnitName(String fbaseUnitName) {
		this.fbaseUnitName = fbaseUnitName;
	}

	public String getFbaseUnitNumber() {
		return fbaseUnitNumber;
	}

	public void setFbaseUnitNumber(String fbaseUnitNumber) {
		this.fbaseUnitNumber = fbaseUnitNumber;
	}

	public String getFunitId() {
		return funitId;
	}

	public void setFunitId(String funitId) {
		this.funitId = funitId;
	}

	public String getFunitNumber() {
		return funitNumber;
	}

	public void setFunitNumber(String funitNumber) {
		this.funitNumber = funitNumber;
	}

	public String getFunitName() {
		return funitName;
	}

	public void setFunitName(String funitName) {
		this.funitName = funitName;
	}

	public String getFisbatchManage() {
		return fisbatchManage;
	}

	public void setFisbatchManage(String fisbatchManage) {
		this.fisbatchManage = fisbatchManage;
	}

	public String getFunitConvsRate() {
		return funitConvsRate;
	}

	public void setFunitConvsRate(String funitConvsRate) {
		this.funitConvsRate = funitConvsRate;
	}

	public BigDecimal getFqty() {
		return fqty;
	}

	public void setFqty(BigDecimal fqty) {
		this.fqty = fqty;
	}

	public BigDecimal getFbaseqty() {
		return fbaseqty;
	}

	public void setFbaseqty(BigDecimal fbaseqty) {
		this.fbaseqty = fbaseqty;
	}

	public String getFbarCode() {
		return fbarCode;
	}

	public void setFbarCode(String fbarCode) {
		this.fbarCode = fbarCode;
	}


	
}
