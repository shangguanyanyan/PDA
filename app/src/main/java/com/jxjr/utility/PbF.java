package com.jxjr.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class PbF {
	
	/*
	 * String 常量区域
	 * */
	public final static String tip_LoginSuc="登录成功！";
	public final static String tip_OperateSuc="操作成功！";
	
	//历史版本=-3,变更中=-2,=-1,新增=0,保存=1,提交=2,作废=3,审核=4,下达=5,冻结=6,关闭=7,投放=8,完成=9
	public final static Map<String,String> billstatuesmap = new HashMap<String,String>();  
	static {  
		billstatuesmap.put("-3", "历史版本");  
		billstatuesmap.put("-2", "变更中");  
		billstatuesmap.put("0", "新增");  
		billstatuesmap.put("1", "保存");  
		billstatuesmap.put("2", "提交");  
		billstatuesmap.put("3", "作废");  
		billstatuesmap.put("4", "审核");  
		billstatuesmap.put("5", "下达"); 
		billstatuesmap.put("6", "冻结"); 
		billstatuesmap.put("7", "关闭"); 
		billstatuesmap.put("8", "投放"); 
		billstatuesmap.put("9", "完成"); 
	} 
	public final static Set<Long> editableStatuesSet=new HashSet<Long>();		//判断状态是否可以编辑
	static{
		editableStatuesSet.add(Long.valueOf(0));
		editableStatuesSet.add(Long.valueOf(1));
		editableStatuesSet.add(Long.valueOf(5));
	}
	
	public static Boolean statues2Editable(Number num){
		return editableStatuesSet.contains(num.longValue());
	}

	//给字符串一个初始值而不是null
	public static String inzStr(String str,String initValue){
		if(str!=null)
			return str;
		else
			return initValue;
	}
	
	public static String inzStr(String str){
		return inzStr(str,"");
	}
	
	public static Integer inzInt(Integer i){
		if(i==null){
			return 0;
		}else{
			return i;
		}
	}
	
	public static BigDecimal inzBDecimal(BigDecimal i,double initValue){
		if(i==null){
			return new BigDecimal(String.valueOf(initValue));
		}else{
			return i;
		}
	}
	public static BigDecimal inzBDecimal(BigDecimal i){
		return inzBDecimal(i,0.0);
	}
	
	public static String date2Str(Date date,Date intiDataValue){
		if(date==null){
			return DateFun.Date2String(intiDataValue, GI.dateFormat);
		}else{
			return DateFun.Date2String(date,GI.dateFormat);
		}
	}
	
	public static String date2Str(Date date){
		return date2Str(date,new Date());
	}
	
	//int 转String
	public static String int2Str(Integer i,String initValue){
		if(i!=null)
			return i.toString();
		else{
			return initValue;
		}
	}
	
	public static String int2Str(Integer i){
		return int2Str(i,"");
	}
	
	public static String number2Str(Number i,String initValue){
		if(i!=null)
			return i.toString();
		else{
			return initValue;
		}
	}
	public static String number2StrFormat(Number i,String initValue,String format){
		if(i!=null)
			return String.format(format, i);
		else{
			return initValue;
		}
	}
	
	public static String number2Str(Number i){
		return number2Str(i,"");
	}
	
	public static String number2StrFormat(Number i,String format){
		return number2StrFormat(i,"0",format);
	}
	
	public static String number2StrFormat(Number i){
		return number2StrFormat(i,"0",GI.decimalFormat);
	}
	
	public static Number coalesceNumeric(Number num1,Number num2,Number num3){
		if(num1.doubleValue()>0){
			return num1;
		}else if(num2.doubleValue()>0){
			return num2;
		}else if(num3.doubleValue()>0){
			return num3;
		}else{
			return 0;
		}			
	}
	public static Number coalesceNumeric(Number num1,Number num2){
		return coalesceNumeric(num1,num2,0);
	}
	
	//用于将Map里的数进行相加
	public static void mapCalc(Map<String,Object> map,String key,BigDecimal num){
		BigDecimal numTag=(BigDecimal)map.get(key);
		if(numTag==null){
			numTag=new BigDecimal(0);
		}
		if(num==null){
			num=new BigDecimal(0);
		}
		numTag=numTag.add(num);
		map.put(key, numTag);
	}
	
	/*
	 * 将BigDecimal 四舍五入方式摸0
	 * 10.0->10,10.0100->10.01
	 * maxScale 最大精度，initValue 初始值
	 * Author：Mr.Shen
	 * ModifyDate：2017-08-25
	 * */
	public static String decimal2StrFormat(BigDecimal num,String initValue,int maxScale){		
		if(num==null){
			return initValue;
		}
		int i=0;
		BigDecimal tmp=new BigDecimal(num.toString());
		for(;i<maxScale;i++){
			if(tmp.compareTo(new BigDecimal(tmp.intValue()))==0){
				break;
			}
			tmp=tmp.multiply(new BigDecimal(10));
		}
		return num.setScale(i, RoundingMode.HALF_UP).toString();	//HALF_UP四舍五入
	}
	
	public static String decimal2StrFormat(BigDecimal num){	
		return decimal2StrFormat(num,"0",GI.decimalScale);
	}
	
}
