package com.example.copyofhy;

import com.jxjr.m.dbcode.DaoMaster;
import com.jxjr.m.dbcode.DaoSession;
import com.jxjr.m.dbcode.DaoMaster.DevOpenHelper;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

public class MyApplication extends Application {
	private DaoMaster.DevOpenHelper helper;
	private SQLiteDatabase db;
	private DaoMaster daoMaster;
	private DaoSession daoSession;
	public static MyApplication instances;

	@Override
	public void onCreate() {
		super.onCreate();
		instances = this;
		helper = new DaoMaster.DevOpenHelper(this, "News_info-db", null);
		db = helper.getWritableDatabase();
		daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();
	}

	public static MyApplication getInstances() {
		return instances;
	}

	public DaoSession getDaoSession() {
		return daoSession;
	}

	public SQLiteDatabase getDb() {
		return db;
	}
}
