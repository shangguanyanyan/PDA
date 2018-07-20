package com.jxjr.utility;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AZUIPub {
	public static OnTouchListener noInputToucher=new OnTouchListener(){
		//取消软键盘输入法显示
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
            try {
                Class<EditText> cls = EditText.class;
                Method setSoftInputShownOnFocus;
                setSoftInputShownOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setSoftInputShownOnFocus.setAccessible(true);
                setSoftInputShownOnFocus.invoke(v, false);
            } catch (Exception e) {
                e.printStackTrace();
            }	
//			((TextView) v).setInputType(InputType.TYPE_DATETIME_VARIATION_NORMAL);
//			((TextView) v).setCursorVisible(true);
//		//	((TextView) v).setInputType(InputType.TYPE_NULL); // 关闭软键盘      
////			AZUIPub.viewHiddenInput(mActivity,v);
			return false;
		}			
	}; 
	
	//获取版本名称
    public static String getVersionName(Context context){    	
        try {
			PackageManager packageManager = context.getPackageManager();  
			// getPackageName()是你当前类的包名，0代表是获取版本信息  
			PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),0);  
			String version = packInfo.versionName;  
			return version;
		} catch (PackageManager.NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "";
    }
	
	public static void inzText(View view,Object value,Boolean editable){
		if(value==null||"".equals(value.toString())){
			view.setEnabled(editable);
			view.setVisibility(View.VISIBLE);
		}else{
			view.setEnabled(editable);
			view.setVisibility(View.INVISIBLE);
		}
	}	
	
	public static String text2String(TextView v,String defaultStr){
		if(null==v.getText())
			return defaultStr;
		else
			return v.getText().toString();
	}	
	
	public static String text2String(TextView v){
		return text2String(v,"");
	}	
	
	public static BigDecimal text2BigDcm(TextView v,String defaultStr){
		System.out.println(new BigDecimal(text2String(v,defaultStr)));
		return new BigDecimal(text2String(v,defaultStr));		
	}
	
	public static BigDecimal text2BigDcm(TextView v){		
		return text2BigDcm(v,"0");

	}
	
	public static long text2long(TextView v,String defaultStr){
		return Long.parseLong(text2String(v,defaultStr));
	}
	
	public static long text2long(TextView v){
		return Long.parseLong(text2String(v,"0"));
	}
	
	public static Date text2Date(TextView v,String format){
		String datestr="1900-01-10";
		if(null==v.getText()){
			return DateFun.String2Date(datestr, GI.dateFormat);
		}else{
			datestr=v.getText().toString().trim();
			return DateFun.String2Date(datestr, format);
		}
	}
	
	public static Date text2Date(TextView v){
		return text2Date(v,GI.dateFormat);
	}
	
	public static Integer check2Integer(CheckBox v){
		return v.isChecked()?1:0;
	}
	
	public static void finishHiddenInput(Activity activity){
//		InputMethodManager imm =(InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);  
//	    if(imm.isActive()){
//	    	imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS); 
//	    }
		if(activity.getWindow().getAttributes()
				 .softInputMode==WindowManager
				 .LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED){
		 //隐藏软键盘
			activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
			activity.getWindow().getAttributes().softInputMode=WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED; 
		}
	    activity.finish();
	}
	public static void viewHiddenInput(Activity activity,View v){
//		if(activity.getWindow().getAttributes()
//				 .softInputMode==WindowManager
//				 .LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED){
//		 //隐藏软键盘
//			activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//			activity.getWindow().getAttributes().softInputMode=WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED; 
//		}
		InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//		if(imm.isActive()){
//			imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//		}
	}
    public static void showMessage(Context context,int way,String msg){
    	if(way==Constance.show_Toast){
    		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    	}else{
    		new AlertDialog.Builder(context,AlertDialog.THEME_HOLO_DARK)
    		.setPositiveButton("确定", null)
    		.setTitle("Message")
    		.setMessage(msg)
    		.show();

    	}
    }
	
	public static void viewNeedFocus(View v,Boolean Success){
		if(Success){
			
		}else{
			v.requestFocus();
		}
	}
	
	public static void touchDisabled(TextView v){
		v.setEnabled(false);
		v.setFocusable(false);
		v.setFocusableInTouchMode(false);
	}
	
	public static void touchEnabled(TextView v){
		v.setEnabled(true);
		v.setFocusable(true);
		v.setFocusableInTouchMode(true);
	}
}
