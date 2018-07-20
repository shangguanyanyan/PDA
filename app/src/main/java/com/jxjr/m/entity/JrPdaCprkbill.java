package com.jxjr.m.entity;

// Generated 2017-3-22 20:54:59 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;

/**
 * JrSaleIssueBill generated by hbm2java
 */
public class JrPdaCprkbill implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String fid;
	private String fnumber;
	private Date fbizdate;
	private String fbiztype;
	private String fdeptId;
	private String fdeptNumber;
	private String fdeptName;
	private String fcpbzdjId;
	private String fcpbzdjNo;
	private String fscddId;
	private String fscddNo;
	private String fscddSeq;
	private String fscddentryId;
	private String fbasestatus;
	private String fcreateuserid;
	private String fcreateusername;
	private String forgid;
	private Date fdate;
	private BigDecimal ftotalqty;
	private String fmemo;

	
	public JrPdaCprkbill() {
	}

	public JrPdaCprkbill(int id) {
		this.id = id;
	}

	public JrPdaCprkbill(int id, String fid, String fnumber, Date fbizdate,
			String fbiztype, String fdeptId, String fdeptNumber,
			String fdeptName, String fcpbzdjId, String fcpbzdjNo,
			String fscddId, String fscddNo, String fscddSeq,
			String fscddentryId, String fbasestatus, String fcreateuserid,
			String fcreateusername, String forgid, Date fdate,
			BigDecimal ftotalqty, String fmemo) {
		super();
		this.id = id;
		this.fid = fid;
		this.fnumber = fnumber;
		this.fbizdate = fbizdate;
		this.fbiztype = fbiztype;
		this.fdeptId = fdeptId;
		this.fdeptNumber = fdeptNumber;
		this.fdeptName = fdeptName;
		this.fcpbzdjId = fcpbzdjId;
		this.fcpbzdjNo = fcpbzdjNo;
		this.fscddId = fscddId;
		this.fscddNo = fscddNo;
		this.fscddSeq = fscddSeq;
		this.fscddentryId = fscddentryId;
		this.fbasestatus = fbasestatus;
		this.fcreateuserid = fcreateuserid;
		this.fcreateusername = fcreateusername;
		this.forgid = forgid;
		this.fdate = fdate;
		this.ftotalqty = ftotalqty;
		this.fmemo = fmemo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getFbizdate() {
		return fbizdate;
	}

	public void setFbizdate(Date fbizdate) {
		this.fbizdate = fbizdate;
	}

	public String getFbiztype() {
		return fbiztype;
	}

	public void setFbiztype(String fbiztype) {
		this.fbiztype = fbiztype;
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

	public String getFcpbzdjId() {
		return fcpbzdjId;
	}

	public void setFcpbzdjId(String fcpbzdjId) {
		this.fcpbzdjId = fcpbzdjId;
	}

	public String getFcpbzdjNo() {
		return fcpbzdjNo;
	}

	public void setFcpbzdjNo(String fcpbzdjNo) {
		this.fcpbzdjNo = fcpbzdjNo;
	}

	public String getFscddId() {
		return fscddId;
	}

	public void setFscddId(String fscddId) {
		this.fscddId = fscddId;
	}

	public String getFscddNo() {
		return fscddNo;
	}

	public void setFscddNo(String fscddNo) {
		this.fscddNo = fscddNo;
	}

	public String getFscddSeq() {
		return fscddSeq;
	}

	public void setFscddSeq(String fscddSeq) {
		this.fscddSeq = fscddSeq;
	}

	public String getFscddentryId() {
		return fscddentryId;
	}

	public void setFscddentryId(String fscddentryId) {
		this.fscddentryId = fscddentryId;
	}

	public String getFbasestatus() {
		return fbasestatus;
	}

	public void setFbasestatus(String fbasestatus) {
		this.fbasestatus = fbasestatus;
	}

	public String getFcreateuserid() {
		return fcreateuserid;
	}

	public void setFcreateuserid(String fcreateuserid) {
		this.fcreateuserid = fcreateuserid;
	}

	public String getFcreateusername() {
		return fcreateusername;
	}

	public void setFcreateusername(String fcreateusername) {
		this.fcreateusername = fcreateusername;
	}

	public String getForgid() {
		return forgid;
	}

	public void setForgid(String forgid) {
		this.forgid = forgid;
	}

	public Date getFdate() {
		return fdate;
	}

	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}

	

	public BigDecimal getFtotalqty() {
		return ftotalqty;
	}

	public void setFtotalqty(BigDecimal ftotalqty) {
		this.ftotalqty = ftotalqty;
	}

	public String getFmemo() {
		return fmemo;
	}

	public void setFmemo(String fmemo) {
		this.fmemo = fmemo;
	}

	
	
}
