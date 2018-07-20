package com.jxjr.m.entity;

// Generated 2017-3-22 20:54:59 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

/**
 * JrManufactureRecBillEntry generated by hbm2java
 */

public class JrPdaCpbzdjbillentry implements java.io.Serializable,Cloneable {


	private static final long serialVersionUID = 1L;
	private String fid;
	private String fentryid;
	private String fdytm;
	private String ftm;
	private BigDecimal fjs;
	private String fnote;
	private String fstockId;
	private String fstockNumber;
	private String fstockName;
	private String fspId;
	private String fspNumber;
	private String fspName;


	public JrPdaCpbzdjbillentry() {
	}

	public JrPdaCpbzdjbillentry(String fid, String fentryid, String fdytm,
			String ftm, BigDecimal fjs, String fnote, String fstockId,
			String fstockNumber, String fstockName, String fspId,
			String fspNumber, String fspName) {
		super();
		this.fid = fid;
		this.fentryid = fentryid;
		this.fdytm = fdytm;
		this.ftm = ftm;
		this.fjs = fjs;
		this.fnote = fnote;
		this.fstockId = fstockId;
		this.fstockNumber = fstockNumber;
		this.fstockName = fstockName;
		this.fspId = fspId;
		this.fspNumber = fspNumber;
		this.fspName = fspName;
	}


	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFentryid() {
		return fentryid;
	}

	public void setFentryid(String fentryid) {
		this.fentryid = fentryid;
	}

	public String getFdytm() {
		return fdytm;
	}

	public void setFdytm(String fdytm) {
		this.fdytm = fdytm;
	}

	public String getFtm() {
		return ftm;
	}

	public void setFtm(String ftm) {
		this.ftm = ftm;
	}

	public BigDecimal getFjs() {
		return fjs;
	}

	public void setFjs(BigDecimal fjs) {
		this.fjs = fjs;
	}

	public String getFnote() {
		return fnote;
	}

	public void setFnote(String fnote) {
		this.fnote = fnote;
	}

	public String getFstockId() {
		return fstockId;
	}

	public void setFstockId(String fstockId) {
		this.fstockId = fstockId;
	}

	public String getFstockNumber() {
		return fstockNumber;
	}

	public void setFstockNumber(String fstockNumber) {
		this.fstockNumber = fstockNumber;
	}

	public String getFstockName() {
		return fstockName;
	}

	public void setFstockName(String fstockName) {
		this.fstockName = fstockName;
	}

	public String getFspId() {
		return fspId;
	}

	public void setFspId(String fspId) {
		this.fspId = fspId;
	}

	public String getFspNumber() {
		return fspNumber;
	}

	public void setFspNumber(String fspNumber) {
		this.fspNumber = fspNumber;
	}

	public String getFspName() {
		return fspName;
	}

	public void setFspName(String fspName) {
		this.fspName = fspName;
	}

	@Override  
    public Object clone() {  
		JrPdaCpbzdjbillentry obj = null;  
        try{  
        	obj = (JrPdaCpbzdjbillentry)super.clone();  
        }catch(CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return obj;  
    }  
}