package com.jxjr.utility;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.KeyEvent;

import com.jxjr.m.DTO.LoginResult;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class GI {
	public final static Set<Integer> editStatus=new HashSet();
	static{
		editStatus.add(0);//新增=0
		editStatus.add(1);//保存=1
	}
	
	public final static Set<Integer> scanKeyCode=new HashSet<Integer>(); 		
	static{		
		scanKeyCode.add(KeyEvent.KEYCODE_F9);
		scanKeyCode.add(KeyEvent.KEYCODE_F10);
		scanKeyCode.add(KeyEvent.KEYCODE_F11);
	}
	public static Boolean isScanKeyCode(int keycode){
		if(scanKeyCode.contains(Integer.valueOf(keycode))){
			return true;
		}else{
			return false;
		}
	}
	
	public static String dateFormat="yyyy-MM-dd";
	public static String IMAGE_FOLDER="image.com.LF";
	public static String decimalFormat="%.3f";
	public static int decimalScale=3;
	public static String intFormat="%.0f";
	
	public final static String decede_supplier="supplier";
	public final static String decede_department="department";
	
	public final static String search_supplier=decede_supplier;
	public final static String search_department=decede_department;
	
	public final static boolean c_releaseMode=true;
	
	public final static String EMPTY="";
	//主机
	public static String HOSTPRIX="http://";
	//public static String HOSTAddress="192.168.10.100:8080";
	public static String HOSTAddress="192.168.0.49:8080";
	public static String HOSThttp=HOSTPRIX+HOSTAddress;   //带http://
	
	public static Object QUERY_RESULT="";
	
	//登陆后初始业务数据
	public static LoginResult SESSION=null;
	
	public static String forgid="1";
	
	//public static Boolean isLOGIN=false;
	public static Boolean isLOGIN=false;
	
	
	public final static String deviceId=android.os.Build.SERIAL;
	//public static String localdate=DateFun.Date2String(new Date(), "yyyy-MM-dd HH:mm:ss");
}
