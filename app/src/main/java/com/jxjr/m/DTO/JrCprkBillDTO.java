package com.jxjr.m.DTO;

import java.util.List;

import com.jxjr.m.entity.JrPdaCprkbill;
import com.jxjr.m.entity.JrPdaCprkbillentry;

public class JrCprkBillDTO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private JrPdaCprkbill bill;
	List<JrPdaCprkbillentry> entry;
	
	public JrCprkBillDTO() {
		// TODO 自动生成的构造函数存根
	}

	public JrCprkBillDTO(JrPdaCprkbill bill) {
		this.bill = bill;
	}

	public JrPdaCprkbill getBill() {
		return bill;
	}


	public void setBill(JrPdaCprkbill bill) {
		this.bill = bill;
	}

	public List<JrPdaCprkbillentry> getEntry() {
		return entry;
	}

	public void setEntry(List<JrPdaCprkbillentry> entry) {
		this.entry = entry;
	}

	
}
