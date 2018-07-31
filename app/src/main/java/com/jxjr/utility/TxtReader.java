package com.jxjr.utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import android.os.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

public class TxtReader {

	String content;
	String filename;
	String filePath;
	String errorMSG;
	
	
	public TxtReader() {
		// TODO Auto-generated constructor stub
		
	}

	
	public TxtReader(String filePath,String filename) {
		super();
		this.filename = filename;
		this.filePath = filePath;
	}


	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}


	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}


	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}


	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}


	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}


	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	/**
	 * @return the errorMSG
	 */
	public String getErrorMSG() {
		return errorMSG;
	}


	/**
	 * @param errorMSG the errorMSG to set
	 */
	public void setErrorMSG(String errorMSG) {
		this.errorMSG = errorMSG;
	}

	public <T> List<T> json2ListClass(String jsonStr,Class<T> clazz) throws JSONException{
		return JSON.parseArray(jsonStr,clazz);
	}
	
	public <T> T json2Class(String jsonStr,Class<T> clazz) throws JSONException{
		return JSON.parseObject(jsonStr, clazz);
	}

	public void readTxt(){  
		String efg="";
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)){
			efg=Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		
//		File file1=new File(efg,"file1.txt");
//		try{
//			if(!file1.exists())
//				file1.createNewFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			String filePath=efg+this.filePath;


		String filename=this.filename;
        //byte Buffer[] = new byte[1024];  
        //得到文件输入流  
        File file = new File(filePath+File.separator+filename);
        FileInputStream in = null;  
        ByteArrayOutputStream outputStream = null;  
        try {  
        		if(file.exists())
        		{
        			in = new FileInputStream(file);  
    	            //读出来的数据首先放入缓冲区，满了之后再写到字符输出流中          			
        			int length = in.available(); 
        			byte [] buffer = new byte[length];
    	            int len = in.read(buffer);  
    	            //创建一个字节数组输出流  
    	            outputStream = new ByteArrayOutputStream();  
    	            outputStream.write(buffer,0,len);  
    	            //把字节输出流转String  
    	            this.content=new String(outputStream.toByteArray());
        		}	    		
               
        } catch (FileNotFoundException e) {  
//            e.printStackTrace();
        	this.errorMSG="读取文件异常";

        } catch (Exception e) {  
//            e.printStackTrace();
        	this.errorMSG="读取文件异常";

        }finally{  
            if(in!=null){  
                try {  
                    in.close();  
                } catch (Exception e) {  
//                    e.printStackTrace();  
                	this.errorMSG="文件读取异常";
                }  
            }  
            if(outputStream!=null){  
                try {  
                    outputStream.flush();  
                    outputStream.close();  
                } catch (Exception e) {  
//                    e.printStackTrace();
                	this.errorMSG="文件读取异常";
                }  
            }  
        }  
    } 
	
	
}
