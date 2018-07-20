package com.jxjr.m.DTO;

import java.util.List;

import com.jxjr.m.entity.JrPdaScllbill;
import com.jxjr.m.entity.JrPdaScllbillentry;


public class JrScllBillDTO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private JrPdaScllbill bill;
	List<JrPdaScllbillentry> entry;
	
	public JrScllBillDTO() {
		// TODO 自动生成的构造函数存根
	}

	public JrScllBillDTO(JrPdaScllbill bill) {
		this.bill = bill;
	}

	public JrPdaScllbill getBill() {
		return bill;
	}


	public void setBill(JrPdaScllbill bill) {
		this.bill = bill;
	}

	public List<JrPdaScllbillentry> getEntry() {
		return entry;
	}

	public void setEntry(List<JrPdaScllbillentry> entry) {
		this.entry = entry;
	}

	
}
