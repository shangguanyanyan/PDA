package com.jxjr.contract;

import java.util.List;
import java.util.Map;

import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.DTO.JrBcprkSrcBillDTO;
import com.jxjr.m.DTO.JrCpbzdjBillDTO;
import com.jxjr.m.DTO.JrGxpgbillDTO;
import com.jxjr.m.DTO.JrMaterialBaseInfoDTO;
import com.jxjr.m.DTO.JrXsckSrcBillDTO;
import com.jxjr.m.DTO.JrYlqdBillDTO;
import com.jxjr.m.entity.JrPdaGxpgbillentry;

public interface CallbackContract {
	public interface IfLoadDataCallbak{
		public void popFailure();
		public void popFailure(String error);
		public <T> void popSucess(T entity);

	}
//	public interface IfLoadForeignCallbak{
//		public void popFailure(String foreignType,String error);
//		public <T> void popSucess(String foreignType,T entity);
//
//	}
	public interface IfLoadDataCallbakMsg{
		public void popFailure(String error);
		public <T> void popSucess(String msg,T entity);

	}	
	public interface IfcheckModifiedCallbak{
		public void popModified();
		public void popNoModify();
	}
	
	public interface IfSrcDataCallbakMsg<T>{//查询源单的接口
		public void popFailure(String error);
		public void popSucess(String msg,T entity);
	}
	
	public interface IfSearchCallbak{
		public void popSucess(List<BaseInfoObjectDTO> list);
		public void popFailure(String error);
	}
	public interface IfLoadListCallbak{
		public <T> void popSucess(List<T> list);
		public void popFailure(String error);
		public <T> void popFallure(String error,List<T> list);
	}
	
	public interface IfSearchMatCallbak{
		public void popSucess(List<JrMaterialBaseInfoDTO> list);
		public void popFailure(String error);
	}
	
	public interface IfSearchTmCallbak{
		public void popSucess(List<JrPdaGxpgbillentry> list);
		public void popFailure(String error);
	}
	
	public interface IfdecodeTmCallbak{		
		public void popSucess(JrGxpgbillDTO entity);
		public void popFailure(String error);
	}
	
	public interface IfdecodeBarcodeCallbak{
		public void popSucess(BaseInfoObjectDTO entity);
		public void popFailure(String error);
	}
	public interface IfdecodeMatCallbak{
		public void popSucess(JrMaterialBaseInfoDTO entity);
		public void popFailure(String error);
	}

	public interface IfdecodeThCallbak{
		public void popSucess(JrCpbzdjBillDTO entity);
		public void popFailure(String error);
	}
	
	public interface IfdecodeBcprkCallbak{
		public void popSucess(JrBcprkSrcBillDTO entity);
		public void popFailure(String error);
	}
	
	public interface IfdecodeXsckSrcCallbak{
		public void popSucess(JrXsckSrcBillDTO entity);
		public void popFailure(String error);
	}
	
	public interface IfdecodeCprkSrcCallbak{
		public void popSucess(JrCpbzdjBillDTO entity);
		public void popFailure(String error);
	}
	
	public interface IfdecodeScllSrcCallbak{
		public void popSucess(JrYlqdBillDTO entity);
		public void popFailure(String error);
	}
	
	public interface IfSaveCallbak{
		public <T> void popSucess(T entity);
		public void popFailure(String error);
	}
	public interface IfSubmitCallbak{
		public <T> void popSucess(T entity);
		public void popFailure(String error);
	}
	public interface IfBaseInfoQueryCallbak{
		public void popSucess(List <Map<String,Object>> list);
		public void popFailure(String error);
	}
	public interface IfBaseInfoDecodeCallbak{
		public void popSucess(Map<String,Object> map);
		public void popFailure(String error);
	}
	public interface IfListCallbak{
		public <T> void popSucess(List<T> entity);
		public void popFailure(String error);
	}
	public interface IfQueryCallbak<T>{
		public void popSucess(List<T> dtoList);
		public void popFailure(String error);
	}
	public interface IfEntitycallBack<T>{
		public void popSucess(T entity);
		public void popFailure(String error);
	}
	public interface IfListcallBack<T>{
		public void popSucess(List<T> dtoList);
		public void popFailure(String error);
	}
}
