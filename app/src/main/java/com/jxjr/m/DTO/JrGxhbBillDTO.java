package com.jxjr.m.DTO;

import java.util.List;

import com.jxjr.m.entity.JrPdaGxhbYgentry;
import com.jxjr.m.entity.JrPdaGxhbbill;
import com.jxjr.m.entity.JrPdaGxhbbillentry;

public class JrGxhbBillDTO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private JrPdaGxhbbill bill;
	List<JrPdaGxhbbillentry> entrys;
	List<JrPdaGxhbYgentry>  ygEntry;
		

	public JrGxhbBillDTO() {
		// TODO 自动生成的构造函数存根
	}

	public JrGxhbBillDTO(JrPdaGxhbbill bill) {
		this.bill = bill;
	}

	public JrPdaGxhbbill getBill() {
		return bill;
	}


	public void setBill(JrPdaGxhbbill bill) {
		this.bill = bill;
	}


	public List<JrPdaGxhbbillentry> getEntrys() {
		return entrys;
	}


	public void setEntrys(List<JrPdaGxhbbillentry> entrys) {
		this.entrys = entrys;
	}

	public List<JrPdaGxhbYgentry> getYgEntry() {
		return ygEntry;
	}

	public void setYgEntry(List<JrPdaGxhbYgentry> ygEntry) {
		this.ygEntry = ygEntry;
	}

	
	
	
	
}
