package com.jxjr.m.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SearchItemEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fid;
	private String fnumber;
	private String fname;
	private Map attr=new HashMap();
	public SearchItemEntity() {
		// TODO Auto-generated constructor stub
	}
	public SearchItemEntity(String fid, String fnumber, String fname, Map attr) {
		super();
		this.fid = fid;
		this.fnumber = fnumber;
		this.fname = fname;
		this.attr = attr;
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
	public Map getAttr() {
		return attr;
	}
	public void setAttr(Map attr) {
		this.attr = attr;
	}	
	
}
