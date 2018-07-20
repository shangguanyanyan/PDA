package com.jxjr.m.DTO;

import java.util.List;

import com.jxjr.m.entity.JrPdaGxpgYgentry;
import com.jxjr.m.entity.JrPdaGxpgbill;
import com.jxjr.m.entity.JrPdaGxpgbillentry;

public class JrGxpgbillDTO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private JrPdaGxpgbill bill;
	List<JrPdaGxpgbillentry> entrys;
	List<JrPdaGxpgYgentry> ygs;
	
	public JrGxpgbillDTO() {
		// TODO 自动生成的构造函数存根
	}

	public JrGxpgbillDTO(JrPdaGxpgbill bill) {
		this.bill = bill;
	}

	public JrPdaGxpgbill getBill() {
		return bill;
	}


	public void setBill(JrPdaGxpgbill bill) {
		this.bill = bill;
	}


	public List<JrPdaGxpgbillentry> getEntrys() {
		return entrys;
	}


	public void setEntrys(List<JrPdaGxpgbillentry> entrys) {
		this.entrys = entrys;
	}

	public List<JrPdaGxpgYgentry> getYgs() {
		return ygs;
	}

	public void setYgs(List<JrPdaGxpgYgentry> ygs) {
		this.ygs = ygs;
	}
	
	
	
}
