package com.jxjr.m.entity;

// Generated 2017-3-22 20:54:59 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

/**
 * JrManufactureRecBillEntry generated by hbm2java
 */

public class JrPdaGxhbbillentry implements java.io.Serializable,Cloneable {


	private static final long serialVersionUID = 1L;
	private int id;
	private int parentid;
	private String fid;
	private String fentryid;
	private String fseq;
	public String getFseq() {
		return fseq;
	}

	public void setFseq(String fseq) {
		this.fseq = fseq;
	}


	private String ftm;
	private String fgxpgdNo;
	private String fgxpgdSeq;
	private String fgxpgdId;
	private String fgxpgdentryId;
	private String fscddNo;
	private String fscddSeq;
	private String fscddId;
	private String fscddentryId;
	private String fmatId;
	private String fmatNumber;
	private String fmatName;
	private String fmatModel;
	private String fgxId;
	private String fgxNumber;
	private String fgxName;
	private BigDecimal fhgpqty;
	private BigDecimal fylfgqty;
	private BigDecimal fygfgqty;
	private BigDecimal fylbfqty;
	private BigDecimal fygbfqty;
	private String fqxyyId;
	private String fqxyyNumber;
	private String fqxyyName;
	private BigDecimal fgs;
	private String fisjj;
	private String fjjcjId;
	private String fjjcjNumber;
	private String fjjcjName;
	private String fisrk;
	private String frkckId;
	private String frkckNumber;
	private String frkckName;
	private String fistjgs;
	private String fsupId;
	private String fsupNumber;
	private String fsupName;
	private BigDecimal fcys;
	private BigDecimal fcybhgs;
	private String fnote; 
	
	public JrPdaGxhbbillentry() {
	}

	public JrPdaGxhbbillentry(int id) {
		this.id = id;
	}
	public JrPdaGxhbbillentry(int id,int parentid,String fid,String fentryid,String fseq,String ftm,String fgxpgdNo,
			String fgxpgdSeq,String fgxpgdId,String fgxpgdentryId,String fscddNo,String fscddSeq,
			String fscddId,	String fscddentryId,String fmatId,String fmatNumber,String fmatName,
			String fmatModel,String fgxId,String fgxNumber,String fgxName,	BigDecimal fhgpqty,
			BigDecimal fylfgqty,BigDecimal fygfgqty,BigDecimal fylbfqty,BigDecimal fygbfqty,
			String fqxyyId,String fqxyyNumber,String fqxyyName,	BigDecimal fgs,	String fisjj,
			String fjjcjId,String fjjcjNumber,String fjjcjName,String fisrk,String frkckId,
			String frkckNumber,	String frkckName,String fistjgs,String fsupId,String fsupNumber,
			String fsupName,BigDecimal fcys,BigDecimal fcybhgs,	String fnote){
		super();
		this.id = id;
		this.parentid = parentid;
		this.fid = fid;
		this.fentryid = fentryid;
		this.ftm = ftm;
		this.fgxpgdNo = fgxpgdNo;
		this.fseq = fseq;
		this.fgxpgdSeq = fgxpgdSeq;
		this.fgxpgdId = fgxpgdId;
		this.fgxpgdentryId = fgxpgdentryId;
		this.fscddNo = fscddNo;
		this.fscddSeq = fscddSeq;
		this.fscddId = fscddId;
		this.fscddentryId = fscddentryId;
		this.fmatId = fmatId;
		this.fmatNumber = fmatNumber;
		this.fmatName = fmatName;
		this.fmatModel = fmatModel;
		this.fgxId = fgxId;
		this.fgxNumber = fgxNumber;
		this.fgxName = fgxName;
		this.fhgpqty = fhgpqty;
		this.fylfgqty = fylfgqty;
		this.fygfgqty = fygfgqty;
		this.fylbfqty = fylbfqty;
		this.fygbfqty = fygbfqty;
		this.fqxyyId = fqxyyId;
		this.fqxyyNumber = fqxyyNumber;
		this.fqxyyName = fqxyyName;
		this.fgs = fgs;
		this.fisjj = fisjj;
		this.fjjcjId = fjjcjId;
		this.fjjcjNumber = fjjcjNumber;
		this.fjjcjName = fjjcjName;
		this.fisrk = fisrk;
		this.frkckId = frkckId;
		this.frkckNumber = frkckNumber;
		this.frkckName = frkckName;
		this.fistjgs = fistjgs;
		this.fsupId = fsupId;
		this.fsupNumber = fsupNumber;
		this.fsupName = fsupName;
		this.fcys = fcys;
		this.fcybhgs = fcybhgs;
		this.fnote = fnote;
		
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


	public String getFtm() {
		return ftm;
	}


	public void setFtm(String ftm) {
		this.ftm = ftm;
	}


	public String getFgxpgdNo() {
		return fgxpgdNo;
	}


	public void setFgxpgdNo(String fgxpgdNo) {
		this.fgxpgdNo = fgxpgdNo;
	}


	public String getFgxpgdSeq() {
		return fgxpgdSeq;
	}


	public void setFgxpgdSeq(String fgxpgdSeq) {
		this.fgxpgdSeq = fgxpgdSeq;
	}


	public String getFgxpgdId() {
		return fgxpgdId;
	}


	public void setFgxpgdId(String fgxpgdId) {
		this.fgxpgdId = fgxpgdId;
	}


	public String getFgxpgdentryId() {
		return fgxpgdentryId;
	}


	public void setFgxpgdentryId(String fgxpgdentryId) {
		this.fgxpgdentryId = fgxpgdentryId;
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


	public String getFscddId() {
		return fscddId;
	}


	public void setFscddId(String fscddId) {
		this.fscddId = fscddId;
	}


	public String getFscddentryId() {
		return fscddentryId;
	}


	public void setFscddentryId(String fscddentryId) {
		this.fscddentryId = fscddentryId;
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


	public String getFgxId() {
		return fgxId;
	}


	public void setFgxId(String fgxId) {
		this.fgxId = fgxId;
	}


	public String getFgxNumber() {
		return fgxNumber;
	}


	public void setFgxNumber(String fgxNumber) {
		this.fgxNumber = fgxNumber;
	}


	public String getFgxName() {
		return fgxName;
	}


	public void setFgxName(String fgxName) {
		this.fgxName = fgxName;
	}


	public BigDecimal getFhgpqty() {
		return fhgpqty;
	}


	public void setFhgpqty(BigDecimal fhgpqty) {
		this.fhgpqty = fhgpqty;
	}


	public BigDecimal getFylfgqty() {
		return fylfgqty;
	}


	public void setFylfgqty(BigDecimal fylfgqty) {
		this.fylfgqty = fylfgqty;
	}


	public BigDecimal getFygfgqty() {
		return fygfgqty;
	}


	public void setFygfgqty(BigDecimal fygfgqty) {
		this.fygfgqty = fygfgqty;
	}


	public BigDecimal getFylbfqty() {
		return fylbfqty;
	}


	public void setFylbfqty(BigDecimal fylbfqty) {
		this.fylbfqty = fylbfqty;
	}


	public BigDecimal getFygbfqty() {
		return fygbfqty;
	}


	public void setFygbfqty(BigDecimal fygbfqty) {
		this.fygbfqty = fygbfqty;
	}


	public String getFqxyyId() {
		return fqxyyId;
	}


	public void setFqxyyId(String fqxyyId) {
		this.fqxyyId = fqxyyId;
	}


	public String getFqxyyNumber() {
		return fqxyyNumber;
	}


	public void setFqxyyNumber(String fqxyyNumber) {
		this.fqxyyNumber = fqxyyNumber;
	}


	public String getFqxyyName() {
		return fqxyyName;
	}


	public void setFqxyyName(String fqxyyName) {
		this.fqxyyName = fqxyyName;
	}


	public BigDecimal getFgs() {
		return fgs;
	}


	public void setFgs(BigDecimal fgs) {
		this.fgs = fgs;
	}


	public String getFisjj() {
		return fisjj;
	}


	public void setFisjj(String fisjj) {
		this.fisjj = fisjj;
	}


	public String getFjjcjId() {
		return fjjcjId;
	}


	public void setFjjcjId(String fjjcjId) {
		this.fjjcjId = fjjcjId;
	}


	public String getFjjcjNumber() {
		return fjjcjNumber;
	}


	public void setFjjcjNumber(String fjjcjNumber) {
		this.fjjcjNumber = fjjcjNumber;
	}


	public String getFjjcjName() {
		return fjjcjName;
	}


	public void setFjjcjName(String fjjcjName) {
		this.fjjcjName = fjjcjName;
	}


	public String getFisrk() {
		return fisrk;
	}


	public void setFisrk(String fisrk) {
		this.fisrk = fisrk;
	}


	public String getFrkckId() {
		return frkckId;
	}


	public void setFrkckId(String frkckId) {
		this.frkckId = frkckId;
	}


	public String getFrkckNumber() {
		return frkckNumber;
	}


	public void setFrkckNumber(String frkckNumber) {
		this.frkckNumber = frkckNumber;
	}


	public String getFrkckName() {
		return frkckName;
	}


	public void setFrkckName(String frkckName) {
		this.frkckName = frkckName;
	}


	public String getFistjgs() {
		return fistjgs;
	}


	public void setFistjgs(String fistjgs) {
		this.fistjgs = fistjgs;
	}


	public String getFsupId() {
		return fsupId;
	}


	public void setFsupId(String fsupId) {
		this.fsupId = fsupId;
	}


	public String getFsupNumber() {
		return fsupNumber;
	}


	public void setFsupNumber(String fsupNumber) {
		this.fsupNumber = fsupNumber;
	}


	public String getFsupName() {
		return fsupName;
	}


	public void setFsupName(String fsupName) {
		this.fsupName = fsupName;
	}


	public BigDecimal getFcys() {
		return fcys;
	}


	public void setFcys(BigDecimal fcys) {
		this.fcys = fcys;
	}


	public BigDecimal getFcybhgs() {
		return fcybhgs;
	}


	public void setFcybhgs(BigDecimal fcybhgs) {
		this.fcybhgs = fcybhgs;
	}


	public String getFnote() {
		return fnote;
	}


	public void setFnote(String fnote) {
		this.fnote = fnote;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override  
    public Object clone() {  
		JrPdaGxhbbillentry obj = null;  
        try{  
        	obj = (JrPdaGxhbbillentry)super.clone();  
        }catch(CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return obj;  
    }  
}