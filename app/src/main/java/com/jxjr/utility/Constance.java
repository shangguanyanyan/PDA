package com.jxjr.utility;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Constance {
	public final static String type_supplier="supplier";
	public final static String nullStr=null;
	
//	public final static int scan_delay=0;		废弃
	//用原生输入法的时候，防止条码过快产生Action
	
	//ManufactureRecRequest Web的基本路径
	public final static String webprefix="/jxjr/base";
	
	
	public final static String baseinfo_stock="stock";
	public final static String baseinfo_sp="sp";
	public final static String baseinfo_dept="allsccj";
	public final static String baseinfo_cust="cust";
	public final static String baseinfo_supplier="supplier";
	public final static String baseinfo_employee="employee";
	public final static String baseinfo_user="user";
	public final static String baseinfo_material="mat";
	public final static String baseinfo_wlgs="express";
	public final static String baseinfo_pd="pd";
	public final static String baseinfo_pdwarehouse="pdwarehouse";
	public final static String baseinfo_bz="allbz";
	public final static String baseinfo_gx="allgx";
	public final static String baseinfo_qxyy="allqxyy";
	public final static String baseinfo_yg="allyg";
	
	public final static Map<String,String> documentTypeItemClass=new HashMap<String,String>();
	static{
		documentTypeItemClass.put(baseinfo_stock, "warehouse");
		documentTypeItemClass.put(baseinfo_sp, "local");
		documentTypeItemClass.put(baseinfo_dept, "allsccj");
		documentTypeItemClass.put(baseinfo_cust, "cus");
		documentTypeItemClass.put(baseinfo_supplier, "sup");
		documentTypeItemClass.put(baseinfo_employee, "emp");
		documentTypeItemClass.put(baseinfo_user, "user");
		documentTypeItemClass.put(baseinfo_material, "mat");
		documentTypeItemClass.put(baseinfo_wlgs, "express");
		documentTypeItemClass.put(baseinfo_pd, "pd");
		documentTypeItemClass.put(baseinfo_pdwarehouse, "pdwarehouse");
		documentTypeItemClass.put(baseinfo_bz, "allbz");
		documentTypeItemClass.put(baseinfo_gx, "allgx");
		documentTypeItemClass.put(baseinfo_qxyy, "allqxyy");
		documentTypeItemClass.put(baseinfo_yg, "allyg");
	};

	public final static int requestCode_error=-1;
	public final static int requestCode_selectSupplier=0x0091;
	public final static int requestCode_baseinfoStock=0x0092;
	public final static int requestCode_baseinfoSp=0x0093;
	public final static int requestCode_baseinfoDept=0x0094;
	public final static int requestCode_baseinfoEmployee=0x0095;
	public final static int requestCode_baseinfoUser=0x0096;
	public final static int requestCode_baseinfoMaterial=0x0097;
	public final static int requestCode_baseinfoAuditer=0x0098;
	public final static int requestCode_baseinfoStocker=0x0099;
	public final static int requestCode_baseinfoDeliver=0x009A;
	public final static int requestCode_baseinfoInStock=0x009B;
	public final static int requestCode_baseinfoInSp=0x009C;
	public final static int requestCode_baseinfoOutStock=0x009D;
	public final static int requestCode_baseinfoOutSp=0x009E;
	public final static int requestCode_baseinfoWlgs=0x009F;
	public final static int requestCode_baseinfoPd=0x00A0;
	public final static int requestCode_baseinfoPdwarehouse=0x00A1;	
	public final static int requestCode_baseinfofHandler=0x00A2;	
	public final static int requestCode_baseinfobz=0x00A3;
	public final static int requestCode_baseinfogx=0x00A4;
	public final static int requestCode_baseinfoqxyy=0x00A5;
	public final static int requestCode_baseinfoYg=0x00A6;
	public final static int requestCode_baseinfoYgAlter=0x00A7;
	
	public final static int requestCode_sundryMatInv=0x0101;
	
	public final static Map<Integer,String> codeItemClass=new HashMap<Integer,String>();
	static{
		codeItemClass.put(requestCode_baseinfoStock, "warehouse");
		codeItemClass.put(requestCode_baseinfoInStock, "warehousein");		
		codeItemClass.put(requestCode_baseinfoOutStock, "warehouseout");	
		codeItemClass.put(requestCode_baseinfoSp, "local");
		codeItemClass.put(requestCode_baseinfoInSp, "local");
		codeItemClass.put(requestCode_baseinfoOutSp, "local");
		codeItemClass.put(requestCode_baseinfoDept, "dept");
		codeItemClass.put(requestCode_selectSupplier, "sup");
		codeItemClass.put(requestCode_baseinfoEmployee, "emp");
		codeItemClass.put(requestCode_baseinfoUser, "user");
		codeItemClass.put(requestCode_baseinfoMaterial, "mat");
		codeItemClass.put(requestCode_baseinfoWlgs, "express");
		codeItemClass.put(requestCode_baseinfoPd, "pd");
		codeItemClass.put(requestCode_baseinfoPdwarehouse, "pdwarehouse");
		codeItemClass.put(requestCode_baseinfoAuditer, "emp");
		codeItemClass.put(requestCode_baseinfoStocker, "emp");
		codeItemClass.put(requestCode_baseinfoDeliver, "emp");
		codeItemClass.put(requestCode_baseinfofHandler, "emp");
		codeItemClass.put(requestCode_baseinfobz, "bz");
		codeItemClass.put(requestCode_baseinfogx, "gx");
		codeItemClass.put(requestCode_baseinfoqxyy, "qxyy");
		codeItemClass.put(requestCode_baseinfoYg, "yg");
		codeItemClass.put(requestCode_baseinfoYgAlter, "ygalter");
	}	
	
	
	//判断是选择解码还是选择根据物料名称搜索。
	public final static String selDec_select="select";
	public final static String selDec_decode="decode";
	public final static String selDec_foreign="foreign";
	
	public final static String alert_ActivityExit="是否确认退出当前页面？";
	public final static String alert_SureReset="确认重置数据？";
	public final static String alert_警告="警告";
	public final static String alert_提示="提示";
	public final static String alert_scanInfoCheck="缺少有效信息不允许保存（物料、仓库等）。";
	public final static String check_ZeroEntry="无分录信息。";
	public final static String alert_ZeroQty="合格数量必须大于0。";
	public final static String alert_ZeroBaseQty="净重需要大于0。";
	public final static String alert_ZeroAssistQty="筒子数需要大于0。";
	public final static String alert_Zero辅助数量="辅助数量需要大于0。";
	
	public final static String checkerUndefinedError="无有效信息不允许保存。";
	public final static String alert_新增提醒="新增会覆盖当前分录数据，是否继续?";
	public final static String check_待发已发提示="该物料的待发数量为 %.3f,当前数量为%.3f。";
	public final static String check_MatID="物料ID错误。";
	public final static String check_SrcBillNoMatExist="源单上并无此物料。";
	public final static String check_zeroList="0条数据符合条件。";
	public final static String check_WareHouseNotMatch="仓位所在的仓库与已选的仓库不一致，是否覆盖已选仓库？";
	public final static String alert_RepeatedEntryCode="不能重复录入相同的物料二维码！";
	public final static String check_emptyLocation="仓位不能为空！";
	public final static String check_emptyWarehouse="请先扫描仓库再扫仓位！";
	public final static String check_emptySrcbillno="源单编号不能为空！";
	public final static String check_emptyLot="物料启用批号管理，批号不能为空！";
	public final static String message_submitTrue="提交成功！生成的单据编号为:%s。";
	public final static String message_ysdhNotMatch="包装单号不一致，不允许录入。原有单号：%s，当前单号：%s。";
	public final static String check_SrcBillError="扫描的类型错误。";
	public final static String alert_DataNull="无返回数据,请重新扫描。";
	public final static String alert_EntityNull="无明细数据,不能保存提交。";
	public final static int show_Dialog=11;
	public final static int show_Toast=12;
	
	
	public final static int forgin_src=2;
	
	
	public final static int srcflg_srcbill=1;
	
	public final static class BillClass{
		public final static String type_MoveLocation="MoveLocation";
		public final static String type_StockTransfer="StockTransfer";
	}
	
	public final static class ToastStr{
		public final static String error_matPur="解析物料条码失败。";
		public final static String error_OutNullMaterial="出库单内无此物料。";
	}
	public final static class ProcessStr{
		public final static String decoding="解析中...";
	}
	 
	public final static class PurInWarehsCon{
		
		//LinkedHashMap 带顺序的HashMap
		public final static Map<String,String> fbizMap=new LinkedHashMap<String,String>();
		static{
			fbizMap.put("0", "入库");
			fbizMap.put("1", "退库");
		}
		
		public final static String fbizTitle="选择业务类型";
	}
	
	public final static class SaleIssueCon{
		//LinkedHashMap 带顺序的HashMap
		public final static Map<String,String> fMap=new LinkedHashMap<String,String>();
		static{
			fMap.put("issue", "出库");
			fMap.put("return", "退库");
		}
		
		public final static String title="选择业务类型";
	}
	
	public final static class Mat{
		public final static String type_normal="normal";
		public final static String type_pur="pur";
		public final static String type_transfer="transfer";			//调拨单物料查询
		public final static String type_saleissue="saleissue";			//销售出库单物料查询
		public final static String type_manufactureInstock="manufacture";//生产入库单物料查询
		
		public final static String fparam_isNull="isNull";
	}
	
	public final static class ManufRecCon{		
		//LinkedHashMap 带顺序的HashMap
		public final static Map<String,String> fbizMap=new LinkedHashMap<String,String>();
		static{
			fbizMap.put("0", "生产入库");
			fbizMap.put("1", "简单生产入库");
			fbizMap.put("2", "其他入库");
			fbizMap.put("3", "委外入库");
			fbizMap.put("4", "采购入库");
			fbizMap.put("5", "销售退库");
		}		
		public final static String fbizTitle="选择生产入库类型";
	}
	
	public final static class TransferCon{
		public final static Map<String,String> bizTypMap=new LinkedHashMap<String,String>();
		static{
			bizTypMap.put("0","直接调拨");
			bizTypMap.put("1","分步式调出");
			bizTypMap.put("2","分布式调入");
		}
		public final static String bizTyptitle="选择调拨用途";
		
		
		public final static Map<String,String> srcTypMap=new LinkedHashMap<String,String>();
		static{
			srcTypMap.put("0","库位调整");
			srcTypMap.put("1","委外");
			srcTypMap.put("2","染色");
			srcTypMap.put("3","修色");
			srcTypMap.put("4","绞纱后处理");
			srcTypMap.put("5","退料退货");
			srcTypMap.put("6","其他");
		}
		public final static String srcTyptitle="选择调拨用途";
	}
	
	public final static String fbizTitle="选择业务类型";
	
	public final static class MaterialReqCon{
		//LinkedHashMap 带顺序的HashMap
		public final static Map<String,String> fbizMap=new LinkedHashMap<String,String>();
		static{
			fbizMap.put("0", "生产领料");
			fbizMap.put("1", "简单生产领料");
			fbizMap.put("2", "其他出库");
			fbizMap.put("3", "委外出库");
		}
	}
	
	public final static class MutiOrgCon{
		public final static Map<String,String> forgMap=new LinkedHashMap<String,String>();
		static{
			forgMap.put("1","恒誉");
//			forgMap.put("100010" ,"厚源");/
//			forgMap.put("327465", "新中和");
		}
		public final static String title="选择生产组织";
	}
	
	public final static class ServerCon{
		public final static Map<String,String> serverMap=new LinkedHashMap<String,String>();
		static{
			serverMap.put("192.168.0.9:8080", "测试账套");
			serverMap.put("192.168.0.49:8080", "金蝶测试账套");
			serverMap.put("192.168.10.100:8080", "内网测试账套");
			serverMap.put("192.168.10.210:8080", "内网正式账套");

		}
		public final static String title="选择一个账套";
	}
	
}
