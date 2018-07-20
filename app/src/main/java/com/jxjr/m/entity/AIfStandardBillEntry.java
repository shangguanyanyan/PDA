package com.jxjr.m.entity;

import java.math.BigDecimal;

public interface AIfStandardBillEntry {

	/**
	 * @return the fentryId
	 */
	public String getFentryid() ;

	/**
	 * @param fentryId the fentryId to set
	 */
	public void setFentryid(String fentryid) ;

	/**
	 * @return the fmaterialId
	 */
	public String getFmaterialid() ;

	/**
	 * @param fmaterialId the fmaterialId to set
	 */
	public void setFmaterialid(String fmaterialid) ;

	/**
	 * @return the fmaterialName
	 */
	public String getFmaterialname() ;

	/**
	 * @param fmaterialName the fmaterialName to set
	 */
	public void setFmaterialname(String fmaterialname);

	/**
	 * @return the fmaterialNumber
	 */
	public String getFmaterialnumber();

	/**
	 * @param fmaterialNumber the fmaterialNumber to set
	 */
	public void setFmaterialnumber(String fmaterialnumber) ;

	/**
	 * @return the fmaterialModel
	 */
	public String getFmaterialmodel() ;

	/**
	 * @param fmaterialModel the fmaterialModel to set
	 */
	public void setFmaterialmodel(String fmaterialmodel);

	/**
	 * @return the funitId
	 */
	public String getFunitid();

	/**
	 * @param funitId the funitId to set
	 */
	public void setFunitid(String funitid);

	/**
	 * @return the funitName
	 */
	public String getFunitname();
	
	public BigDecimal getFqty();
	
	public void setFqty(BigDecimal fqty);

	/**
	 * @param funitName the funitName to set
	 */
	public void setFunitname(String funitname);
	
	public String getFbarcode();
	
	public void setFbarcode(String fbarcode);
	
}
