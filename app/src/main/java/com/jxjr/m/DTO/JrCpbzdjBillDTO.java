package com.jxjr.m.DTO;

import java.util.List;

import com.jxjr.m.entity.JrPdaCpbzdjbill;
import com.jxjr.m.entity.JrPdaCpbzdjbillentry;

public class JrCpbzdjBillDTO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private JrPdaCpbzdjbill bill;
	List<JrPdaCpbzdjbillentry> entry;
		

	public JrCpbzdjBillDTO() {
		// TODO 自动生成的构造函数存根
	}

	public JrCpbzdjBillDTO(JrPdaCpbzdjbill bill) {
		this.bill = bill;
	}

	public JrPdaCpbzdjbill getBill() {
		return bill;
	}


	public void setBill(JrPdaCpbzdjbill bill) {
		this.bill = bill;
	}

	public List<JrPdaCpbzdjbillentry> getEntry() {
		return entry;
	}

	public void setEntry(List<JrPdaCpbzdjbillentry> entry) {
		this.entry = entry;
	}


	
	
	
	
}
