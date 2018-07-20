package com.jxjr.utility;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

@SuppressLint("ShowToast")
public abstract class RequestLoad implements Runnable{
	
	private Context mContext;
	private String title;
	private String messString="等待中...";
	//等待
	private ProgressDialog mDownloadDialog;

	@SuppressLint("HandlerLeak")
	public Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
//			EnumErrorDef def=EnumErrorDef.values()[msg.what];
			
//			Toast.makeText(mContext, def.getContext(), Toast.LENGTH_LONG);
		
			if (msg.getData().getString("text").toString().equals("登陆成功")){
				messString="基础资料下载中...";				
			}
			
			if (msg.getData().getString("text").toString().equals("成功获取基础资料！")){
				messString="数据解析中...";				
			}

			mDownloadDialog.setMessage(messString);
			mDownloadDialog.show();
			
			Toast.makeText(mContext, msg.getData().getString("text").toString(), Toast.LENGTH_LONG).show();
//			if(msg.getData().containsKey("error")){
//				msgBox(msg.getData().getString("error").toString());
//			}else if(msg.getData().containsKey("xx")){
////				Toast.makeText(mContext, msg.getData().getString("text").toString(), Toast.LENGTH_LONG).show();
//				msgBox(msg.getData().getString("xx").toString());
//			}else {
//				Toast.makeText(mContext, msg.getData().getString("text").toString(), Toast.LENGTH_LONG).show();
//			}
		};
	};
	
	public RequestLoad(Context context) {
		this.mContext = context;
		initDialog();
	}
	
	public RequestLoad(Context context,String title) {
		this.mContext = context;
		this.title=title;
		initDialog();
	}
	
	public RequestLoad(Context context,String title,String messagesString) {
		this.mContext = context;
		this.title=title;
		this.messString=messagesString;
		initDialog();
	}
	
	
	public void run() {
		// TODO Auto-generated method stub
//		mHandler.sendEmptyMessage(doPost());
//		mHandler.sendMessage(doPost());
		doPost();
		mDownloadDialog.dismiss();
		
		
	}

	void initDialog(){
		mDownloadDialog = new ProgressDialog(mContext);
		mDownloadDialog.setTitle(title);
		mDownloadDialog.setMessage(messString);
		mDownloadDialog.setIndeterminate(true);
		mDownloadDialog.setCancelable(false);
		mDownloadDialog.show();
	}
	
//	public abstract int doPost();
	public abstract void doPost();
	
	private void msgBox(String mString){
		new AlertDialog.Builder(mContext)
		.setTitle("")
	    .setMessage(mString)
	    .setPositiveButton("确定", null)
	    .show();
	}
	
}
