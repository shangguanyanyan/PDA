package com.jxjr.m.entity;

// Generated 2017-3-22 20:54:59 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;

/**
 * JrManufactureRecBill generated by hbm2java
 */

public class JrPdaGxpgYgentry implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int parentid;
	private String fid;
	private String fentryid;
	private String fygId;
	private String fygNumber;
	private String fygName;
	private BigDecimal fygxs;
	private String fnote;


	
	public JrPdaGxpgYgentry() {
	}

	public JrPdaGxpgYgentry(int id) {
		this.id = id;
	}

	public JrPdaGxpgYgentry(int id, int parentid, String fid,String fentryid,String fygId,			
			String fygNumber, String fygName, BigDecimal fygxs,
			String fnote) {
		this.id = id;
		this.parentid = parentid;
		this.fid=fid;
		this.fentryid=fentryid;
		this.fygId = fygId;
		this.fygNumber = fygNumber;
		this.fygName = fygName;
		this.fygxs=fygxs;
		this.fnote = fnote;
	}

	public int getId() {
		return this.id;
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

	public String getFygId() {
		return fygId;
	}

	public void setFygId(String fygId) {
		this.fygId = fygId;
	}

	public String getFygNumber() {
		return fygNumber;
	}

	public void setFygNumber(String fygNumber) {
		this.fygNumber = fygNumber;
	}

	public String getFygName() {
		return fygName;
	}

	public void setFygName(String fygName) {
		this.fygName = fygName;
	}

	public BigDecimal getFygxs() {
		return fygxs;
	}

	public void setFygxs(BigDecimal fygxs) {
		this.fygxs = fygxs;
	}

	public String getFnote() {
		return fnote;
	}

	public void setFnote(String fnote) {
		this.fnote = fnote;
	}

	
}
