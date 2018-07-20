package com.jxjr.utility;

import java.util.TreeSet;

import android.app.AlertDialog;
import android.content.Context;

public class BaseEntryDataChecker {

	TreeSet<String> entryCodeSet= new TreeSet<String>();
	Context context;
	

	public BaseEntryDataChecker(Context context) {
		this.context = context;
	}

	public TreeSet<String> getEntryCodeSet() {
		return entryCodeSet;
	}

	public void insertCode(String code){
		this.entryCodeSet.add(code);
	}
	
	public void removeCode(String code){
		this.entryCodeSet.remove(code);
	}
	
	public boolean contained(String code){
		if(this.entryCodeSet.contains(code)){
			showRepeated(code);
			return true;
		}else{
			return false;
		}
	}
	protected void showRepeated(String code){
		new AlertDialog.Builder(context,AlertDialog.THEME_HOLO_DARK)
		.setPositiveButton("确定", null)
		.setMessage(Constance.alert_RepeatedEntryCode)
		.show();
	}
}
