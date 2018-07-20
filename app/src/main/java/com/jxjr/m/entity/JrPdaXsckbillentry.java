package com.jxjr.m.entity;

// Generated 2017-3-22 20:54:59 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;


public class JrPdaXsckbillentry implements java.io.Serializable,Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int parentid;
	private String fid;
	private String fentryid;
	private String fmatId;
	private String fmatNumber;
	private String fmatName;
	private String fmatModel;
	private String fbatchNo;
	private String fstockId;
	private String fstockNumber;
	private String fstockName;
	private String fspId;
	private String fspNumber;
	private String fspName;
	private BigDecimal fqty;
	private String fnote;
	private String ftm;
	private String fdytm;


	public JrPdaXsckbillentry() {
	}

	public JrPdaXsckbillentry(int id) {
		this.id = id;
	}

	

	public JrPdaXsckbillentry(int id, int parentid, String fid,
			String fentryid, String fmatId, String fmatNumber, String fmatName,
			String fmatModel, String fbatchNo, String fstockId,
			String fstockNumber, String fstockName, String fspId,
			String fspNumber, String fspName, BigDecimal fqty, String fnote,
			String ftm, String fdytm) {
		super();
		this.id = id;
		this.parentid = parentid;
		this.fid = fid;
		this.fentryid = fentryid;
		this.fmatId = fmatId;
		this.fmatNumber = fmatNumber;
		this.fmatName = fmatName;
		this.fmatModel = fmatModel;
		this.fbatchNo = fbatchNo;
		this.fstockId = fstockId;
		this.fstockNumber = fstockNumber;
		this.fstockName = fstockName;
		this.fspId = fspId;
		this.fspNumber = fspNumber;
		this.fspName = fspName;
		this.fqty = fqty;
		this.fnote = fnote;
		this.ftm = ftm;
		this.fdytm = fdytm;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getParentid() {
		return parentid;
	}


	public void setParentid(int parentid) {
		this.parentid = parentid;
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


	public String getFmatId() {
		return fmatId;
	}


	public void setFmatId(String fmatId) {
		this.fmatId = fmatId;
	}


	public String getFmatNumber() {
		return fmatNumber;
	}


	public void setFmatNumber(String fmatNumber) {
		this.fmatNumber = fmatNumber;
	}


	public String getFmatName() {
		return fmatName;
	}


	public void setFmatName(String fmatName) {
		this.fmatName = fmatName;
	}


	public String getFmatModel() {
		return fmatModel;
	}


	public void setFmatModel(String fmatModel) {
		this.fmatModel = fmatModel;
	}


	public String getFbatchNo() {
		return fbatchNo;
	}


	public void setFbatchNo(String fbatchNo) {
		this.fbatchNo = fbatchNo;
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


	public BigDecimal getFqty() {
		return fqty;
	}


	public void setFqty(BigDecimal fqty) {
		this.fqty = fqty;
	}


	public String getFnote() {
		return fnote;
	}


	public void setFnote(String fnote) {
		this.fnote = fnote;
	}


	public String getFtm() {
		return ftm;
	}


	public void setFtm(String ftm) {
		this.ftm = ftm;
	}


	public String getFdytm() {
		return fdytm;
	}

	public void setFdytm(String fdytm) {
		this.fdytm = fdytm;
	}

	@Override  
    public Object clone() {  
		JrPdaXsckbillentry obj = null;  
        try{  
        	obj = (JrPdaXsckbillentry)super.clone();  
        }catch(CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return obj;  
    }  
}
