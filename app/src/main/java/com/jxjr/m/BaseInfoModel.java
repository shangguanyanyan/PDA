

package com.jxjr.m;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jxjr.contract.BaseInfoContract.IfBaseInfoModel;
import com.jxjr.contract.CallbackContract.IfBaseInfoDecodeCallbak;
import com.jxjr.contract.CallbackContract.IfBaseInfoQueryCallbak;
import com.jxjr.m.dbcode.DaoMaster;
import com.jxjr.m.dbcode.DaoSession;
import com.jxjr.m.pojo.BaseInfoObjectPOJO;
import com.jxjr.m.pojo.BaseInfoObjectPOJODao;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;
import com.jxjr.utility.SelfDefException;
import com.jxjr.utility.StringUtils;
//import com.jxjr.v.activity.AppHelper;

import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;

public class BaseInfoModel implements IfBaseInfoModel{
	
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private String dbname;
    
	String condition;
	String fItemClass;
	String fParam;
	String forgid;
	
	List <Map<String,Object>> itemList=new ArrayList<Map<String,Object>>();
	Map<String,Object> itemMap=new HashMap<String,Object>();
	
	
	
//	public BaseInfoModel() {
//		super();
//		// TODO Auto-generated constructor stub
//		dbname=AppHelper.DB_NAME;
//	}

	@Override
	public void igetOutData(Context context,String dbname,final IfBaseInfoQueryCallbak callback) {
		// TODO Auto-generated method stub
//		new Thread(){
//			@Override
//			public void run(){
		SQLiteOpenHelper helper = new DaoMaster.DevOpenHelper(context,dbname,null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        QueryBuilder<BaseInfoObjectPOJO> q=daoSession
						.getBaseInfoObjectPOJODao()
						.queryBuilder();
        						
        List<BaseInfoObjectPOJO> tmplist=new ArrayList<BaseInfoObjectPOJO>();

        if(Constance.codeItemClass.get(Constance.requestCode_baseinfoStock).equals(fItemClass)){
    		//查询仓库
        	if(condition==null||"".equals(condition)){
    			if(StringUtils.isEmpty(forgid)){
          			tmplist=q.where(BaseInfoObjectPOJODao.Properties.FItemClass.eq(fItemClass))
        			.list();
    			}else{
        			tmplist=q.where(BaseInfoObjectPOJODao.Properties.FItemClass.eq(fItemClass)
        					,BaseInfoObjectPOJODao.Properties.Forgid.eq(forgid))
        			.list();
    			}

    		}else{
    			if(StringUtils.isEmpty(forgid)){
        			tmplist=q.where(BaseInfoObjectPOJODao.Properties.FItemClass.eq(fItemClass)
        					,BaseInfoObjectPOJODao.Properties.FName.like("%"+condition+"%"))
        					.list();
    			}else{
        			tmplist=q.where(BaseInfoObjectPOJODao.Properties.FItemClass.eq(fItemClass)
        					,BaseInfoObjectPOJODao.Properties.Forgid.eq(forgid)
        					,BaseInfoObjectPOJODao.Properties.FName.like("%"+condition+"%"))
        					.list();
    			}

    		}
        }else{
    		if(condition==null||"".equals(condition)){
    			tmplist=q.where(BaseInfoObjectPOJODao.Properties.FItemClass.eq(fItemClass))
    			.list();
    		}else{
    			tmplist=q.where(BaseInfoObjectPOJODao.Properties.FItemClass.eq(fItemClass)
    					,BaseInfoObjectPOJODao.Properties.FName.like("%"+condition+"%"))
    					.list();
    		}
        }		
		if(tmplist==null||tmplist.size()<=0){
			callback.popFailure(SelfDefException.excepMap.get(SelfDefException.zeroList));
			return;
		}
		if(itemList!=null){
			itemList.clear();
		}else{
			itemList=new ArrayList<Map<String,Object>>();
		}
		for(BaseInfoObjectPOJO tmppojo:tmplist){
			Map tmpMap=new HashMap();
			tmpMap.put("fid", tmppojo.getFItemID());
			tmpMap.put("fnumber", tmppojo.getFNumber());
			tmpMap.put("fname", tmppojo.getFName());
			tmpMap.put("fparam", tmppojo.getFParam());
			itemList.add(tmpMap);
		}
		callback.popSucess(itemList);


	}

	@Override
	public void igetWarehouseList(Context context,String dbname,final IfBaseInfoQueryCallbak callback) {

		SQLiteOpenHelper helper = new DaoMaster.DevOpenHelper(context,dbname,null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        QueryBuilder<BaseInfoObjectPOJO> q=daoSession
						.getBaseInfoObjectPOJODao()
						.queryBuilder();
        						
        List<BaseInfoObjectPOJO> tmplist=new ArrayList<BaseInfoObjectPOJO>();
        
		if(condition==null||"".equals(condition)){
			if(StringUtils.isEmpty(forgid)){
				tmplist=q.where(BaseInfoObjectPOJODao.Properties.FItemClass.eq(fItemClass))
				.list();
			}else{
				tmplist=q.where(BaseInfoObjectPOJODao.Properties.FItemClass.eq(fItemClass)
						,BaseInfoObjectPOJODao.Properties.Forgid.eq(forgid))
				.list();
			}

		}else{
			if(StringUtils.isEmpty(forgid)){
				tmplist=q.where(BaseInfoObjectPOJODao.Properties.FItemClass.eq(fItemClass)
						,BaseInfoObjectPOJODao.Properties.FName.like("%"+condition+"%"))
						.list();
			}else{
				tmplist=q.where(BaseInfoObjectPOJODao.Properties.FItemClass.eq(fItemClass)
						,BaseInfoObjectPOJODao.Properties.Forgid.eq(forgid)
						,BaseInfoObjectPOJODao.Properties.FName.like("%"+condition+"%"))
						.list();
			}

		}
		if(tmplist==null||tmplist.size()<=0){
			callback.popFailure(SelfDefException.excepMap.get(SelfDefException.zeroList));
			return;
		}
		itemList.clear();
		for(BaseInfoObjectPOJO tmppojo:tmplist){
			Map tmpMap=new HashMap();
			tmpMap.put("fid", tmppojo.getFItemID());
			tmpMap.put("fnumber", tmppojo.getFNumber());
			tmpMap.put("fname", tmppojo.getFName());
			tmpMap.put("fparam", tmppojo.getFParam());
			itemList.add(tmpMap);
		}
		callback.popSucess(itemList);


	}

//	//查找仓库信息Without组织
//	@Override
//	public void igetWarehouseListWithoutOrg(Context context,String dbname,final IfBaseInfoQueryCallbak callback) {
//
//		SQLiteOpenHelper helper = new DaoMaster.DevOpenHelper(context,dbname,null);
//        db = helper.getWritableDatabase();
//        daoMaster = new DaoMaster(db);
//        daoSession = daoMaster.newSession();
//        QueryBuilder<BaseInfoObjectPOJO> q=daoSession
//						.getBaseInfoObjectPOJODao()
//						.queryBuilder();
//        						
//        List<BaseInfoObjectPOJO> tmplist=new ArrayList<BaseInfoObjectPOJO>();
//
//		if(condition==null||"".equals(condition)){
//			tmplist=q.where(BaseInfoObjectPOJODao.Properties.FItemClass.eq(fItemClass))
//			.list();
//		}else{
//			tmplist=q.where(BaseInfoObjectPOJODao.Properties.FItemClass.eq(fItemClass)
//					,BaseInfoObjectPOJODao.Properties.FName.like("%"+condition+"%"))
//					.list();
//		}
//		if(tmplist==null||tmplist.size()<=0){
//			callback.popFailure(SelfDefException.excepMap.get(SelfDefException.zeroList));
//			return;
//		}
//		itemList.clear();
//		for(BaseInfoObjectPOJO tmppojo:tmplist){
//			Map tmpMap=new HashMap();
//			tmpMap.put("fid", tmppojo.getFItemID());
//			tmpMap.put("fnumber", tmppojo.getFNumber());
//			tmpMap.put("fname", tmppojo.getFName());
//			tmpMap.put("fparam", tmppojo.getFParam());
//			itemList.add(tmpMap);
//		}
//		callback.popSucess(itemList);
//
//
//	}
	
	@Override
	public void pushPara(String condition, String fItemClass) {
		// TODO Auto-generated method stub
		pushPara(condition,fItemClass,"");
	}
	
	@Override
	public void pushPara(String condition, String fItemClass, String fParam) {
		// TODO Auto-generated method stub
		this.condition=condition;
		if("warehousein".equals(fItemClass)){
			this.fItemClass="warehouse";
			this.fParam="in";
		}else if("warehouseout".equals(fItemClass)){
			this.fItemClass="warehouse";
			this.fParam="out";
		}else{
			this.fItemClass=fItemClass;		
			this.fParam=fParam;
		}
	}
	
	@Override
	public void pushForgid(String _forgid){
		this.forgid=_forgid;
	}

	public void decodeBarcode(Context context,final IfBaseInfoDecodeCallbak callback){
		SQLiteOpenHelper helper = new DaoMaster.DevOpenHelper(context,dbname,null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        
        List<BaseInfoObjectPOJO> tmplist=new ArrayList<BaseInfoObjectPOJO>();
        QueryBuilder<BaseInfoObjectPOJO> q=daoSession
						.getBaseInfoObjectPOJODao()
						.queryBuilder();
		if(condition==null||"".equals(condition)){
			tmplist=q.where(BaseInfoObjectPOJODao.Properties
			.FItemClass.eq(fItemClass))
			.build().list();
//		}else if(!StringUtils.isEmpty(fParam)){
//			tmplist=q.where(BaseInfoObjectPOJODao.Properties.FItemClass.eq(fItemClass)
//					,BaseInfoObjectPOJODao.Properties.FNumber.eq(condition)
//					,BaseInfoObjectPOJODao.Properties.FParam.eq(fParam))
//					.build().list();
		}else{
			tmplist=q.where(BaseInfoObjectPOJODao.Properties.FItemClass.eq(fItemClass)
				,BaseInfoObjectPOJODao.Properties.FNumber.eq(condition))
				.build().list();
//			tmplist=q.where(BaseInfoObjectPOJODao.Properties.FParam.eq(condition))
//					.build().list();
		}
		if(tmplist==null||tmplist.size()<=0){
			callback.popFailure(SelfDefException.excepMap.get(SelfDefException.zeroList));
			return;
		}
		BaseInfoObjectPOJO pojo=tmplist.get(0);		
		itemMap.clear();
		itemMap.put("fid", pojo.getFItemID()+"");
		itemMap.put("fnumber", pojo.getFNumber());
		itemMap.put("fname", pojo.getFName());
		itemMap.put("fparam", pojo.getFParam());
		callback.popSucess(itemMap);
	}

	//根据仓库id获取仓库的属性，如是否启用仓位管理。
	
	@Override 
	public Map<String,String> warehouseid2Paras(Context context,String warehouseid){
		SQLiteOpenHelper helper = new DaoMaster.DevOpenHelper(context,dbname,null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        List<BaseInfoObjectPOJO> tmplist=new ArrayList<BaseInfoObjectPOJO>();
        QueryBuilder<BaseInfoObjectPOJO> q=daoSession
						.getBaseInfoObjectPOJODao()
						.queryBuilder();
        q=q.where(BaseInfoObjectPOJODao.Properties.FItemClass.eq("warehouse")
        		, BaseInfoObjectPOJODao.Properties.FItemID.eq(warehouseid));
        tmplist=q.build().list();
		if(tmplist==null||tmplist.size()<=0){
//			callback.popFailure(SelfDefException.excepMap.get(SelfDefException.zeroList));
			return null;
		}
		BaseInfoObjectPOJO pojo=tmplist.get(0);		
		Map<String,String> map=new HashMap<String,String>();
		map.put("fparam", pojo.getFParam());
		return map;
	}
	
}
