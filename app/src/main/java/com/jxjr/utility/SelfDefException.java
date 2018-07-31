package com.jxjr.utility;

import android.util.SparseArray;

public class SelfDefException extends Exception {

	/**
	 * author Mr.Eric
	 */
	private static final long serialVersionUID = 1L;
	public final static int noReponse=0x0001;
	public final static int networkClose=0x0002;
	public final static int zeroList=0x0003;
	public final static int emptyDecoder=0x0004;
	public final static int emptyData=0x0005;
	public final static int customError=0x9999;

	
	
	public final static SparseArray<String> excepMap=new SparseArray<String>();
	static{
		excepMap.put(noReponse, "错误！请求无响应。");
		excepMap.put(networkClose, "移动数据或WLAN关闭，请打开后尝试。");
		excepMap.put(zeroList, "警告！请求返回了一个空结果列表。");
		excepMap.put(emptyDecoder, "该二维码无解析结果。");
		excepMap.put(emptyData, "错误！请求返回了一个空结果。");
		excepMap.put(customError, "custom");
	}
	
    public SelfDefException(){
        super();
    }
    
    public SelfDefException(int excepCode){
    	super(excepMap.get(excepCode));
    	
    }
    public SelfDefException(String selfDefmsg){
    	super(selfDefmsg);
    }
}
