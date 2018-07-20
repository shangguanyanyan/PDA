package com.jxjr.utility;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import android.os.Environment;

public class FindFilePath {
	private List<String> pathList;
	public List<String> getList(){
		return pathList;
		
	}
	public FindFilePath(){
		
	}
	public void findFilePath() {
		pathList = new LinkedList<String>();
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// 判断手机是否有SD卡
			File path = Environment.getExternalStorageDirectory();// 获得SD卡路径
			// File path = new File("/mnt/sdcard/");
			final File[] files = path.listFiles();// 读取
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					getFileName(files);
				}

			});

		}
	}

	private void getFileName(File[] files) {
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					// Log.i("zeng", "若是文件目录。继续读1" + file.getName().toString()
					// + file.getPath().toString());

					getFileName(file.listFiles());
					// Log.i("zeng", "若是文件目录。继续读2" + file.getName().toString()
					// + file.getPath().toString());
				} else {
					String fileName = file.getName();

					if (fileName.endsWith("tst_loginresult.txt")) {
						pathList.add(file.getPath());
					}

				}
			}
		}

	}
}
