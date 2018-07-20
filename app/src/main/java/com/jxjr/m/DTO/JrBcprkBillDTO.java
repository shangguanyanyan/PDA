package com.jxjr.m.DTO;

import java.util.List;

import com.jxjr.m.entity.JrPdaBcprkbill;
import com.jxjr.m.entity.JrPdaBcprkbillentry;


public class JrBcprkBillDTO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private JrPdaBcprkbill bill;
	List<JrPdaBcprkbillentry> entry;
	
	public JrBcprkBillDTO() {
		// TODO 自动生成的构造函数存根
	}

	public JrBcprkBillDTO(JrPdaBcprkbill bill) {
		this.bill = bill;
	}

	public JrPdaBcprkbill getBill() {
		return bill;
	}


	public void setBill(JrPdaBcprkbill bill) {
		this.bill = bill;
	}

	public List<JrPdaBcprkbillentry> getEntry() {
		return entry;
	}

	public void setEntry(List<JrPdaBcprkbillentry> entry) {
		this.entry = entry;
	}

	
}
