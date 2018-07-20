package com.jxjr.m.DTO;

import java.util.List;

import com.jxjr.m.entity.JrPdaXsckbill;
import com.jxjr.m.entity.JrPdaXsckbillentry;


public class JrXsckBillDTO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private JrPdaXsckbill bill;
	List<JrPdaXsckbillentry> entry;
	
	public JrXsckBillDTO() {
		// TODO 自动生成的构造函数存根
	}

	public JrXsckBillDTO(JrPdaXsckbill bill) {
		this.bill = bill;
	}

	public JrPdaXsckbill getBill() {
		return bill;
	}


	public void setBill(JrPdaXsckbill bill) {
		this.bill = bill;
	}

	public List<JrPdaXsckbillentry> getEntry() {
		return entry;
	}

	public void setEntry(List<JrPdaXsckbillentry> entry) {
		this.entry = entry;
	}


	
	
}
