/**
 * 
 */
package com.jxjr.m.DTO;

import java.util.List;

/**
 * @author ych
 *登陆获取当前用户基本信息以及对应模块的权限信息
 */
public class LoginResult implements java.io.Serializable{
	private  int fUserID;
	private int fEmpID;
	private String fUserNumber;
	private String fUserName;  //用户名
	private int fGzID;
	private String fGzNumber;
	private String fGzName;  //工种
	private int fBmID;
	private String fBmNumber;
	private String fBmName;  //部门
	private String forgid;		//组织ID
//	private Object fRightObject; //权限对象
	private List<RightObjectDTO> rightObjectDTO;	
	private String fPassword;
	private String fAcctID;
		
	/**
 * @param fUserID
 * @param fEmpID
 * @param fUserNumber
 * @param fUserName
 * @param fGzID
 * @param fGzNumber
 * @param fGzName
 * @param fBmID
 * @param fBmNumber
 * @param fBmName
 * @param rightObjectDTO
 */
//public LoginResult(int fUserID, int fEmpID, String fUserNumber,
//		String fUserName, int fGzID, String fGzNumber, String fGzName,
//		int fBmID, String fBmNumber, String fBmName,
//		List<RightObjectDTO> rightObjectDTO) {
//	this.fUserID = fUserID;
//	this.fEmpID = fEmpID;
//	this.fUserNumber = fUserNumber;
//	this.fUserName = fUserName;
//	this.fGzID = fGzID;
//	this.fGzNumber = fGzNumber;
//	this.fGzName = fGzName;
//	this.fBmID = fBmID;
//	this.fBmNumber = fBmNumber;
//	this.fBmName = fBmName;
//	this.rightObjectDTO = rightObjectDTO;
//}
//
//	public LoginResult(int fUserID, String fUserNumber, String fUserName,
//			int fGzID, String fGzNumber, String fGzName, int fBmID,
//			String fBmNumber, String fBmName) {
//		this.fUserID = fUserID;
//		this.fUserNumber = fUserNumber;
//		this.fUserName = fUserName;
//		this.fGzID = fGzID;
//		this.fGzNumber = fGzNumber;
//		this.fGzName = fGzName;
//		this.fBmID = fBmID;
//		this.fBmNumber = fBmNumber;
//		this.fBmName = fBmName;
//		//this.fRightObject = fRightObject;
//	}
	
	/**
	 * 无参数构造函数
	 */
	public LoginResult() {
	}

	public LoginResult(int fUserID, int fEmpID, String fUserNumber,
			String fUserName, int fGzID, String fGzNumber, String fGzName,
			int fBmID, String fBmNumber, String fBmName, String forgid,
			List<RightObjectDTO> rightObjectDTO, String fPassword,
			String fAcctID) {
		super();
		this.fUserID = fUserID;
		this.fEmpID = fEmpID;
		this.fUserNumber = fUserNumber;
		this.fUserName = fUserName;
		this.fGzID = fGzID;
		this.fGzNumber = fGzNumber;
		this.fGzName = fGzName;
		this.fBmID = fBmID;
		this.fBmNumber = fBmNumber;
		this.fBmName = fBmName;
		this.forgid = forgid;
		this.rightObjectDTO = rightObjectDTO;
		this.fPassword = fPassword;
		this.fAcctID = fAcctID;
	}

	/**
	 * @return the fUserID
	 */
	public int getfUserID() {
		return fUserID;
	}
	/**
	 * @param fUserID the fUserID to set
	 */
	public void setfUserID(int fUserID) {
		this.fUserID = fUserID;
	}
	/**
	 * @return the fUserNumber
	 */
	public String getfUserNumber() {
		return fUserNumber;
	}
	/**
	 * @param fUserNumber the fUserNumber to set
	 */
	public void setfUserNumber(String fUserNumber) {
		this.fUserNumber = fUserNumber;
	}
	/**
	 * @return the fUserName
	 */
	public String getfUserName() {
		return fUserName;
	}
	/**
	 * @param fUserName the fUserName to set
	 */
	public void setfUserName(String fUserName) {
		this.fUserName = fUserName;
	}
	/**
	 * @return the fGzID
	 */
	public int getfGzID() {
		return fGzID;
	}
	/**
	 * @param fGzID the fGzID to set
	 */
	public void setfGzID(int fGzID) {
		this.fGzID = fGzID;
	}
	/**
	 * @return the fGzNumber
	 */
	public String getfGzNumber() {
		return fGzNumber;
	}
	/**
	 * @param fGzNumber the fGzNumber to set
	 */
	public void setfGzNumber(String fGzNumber) {
		this.fGzNumber = fGzNumber;
	}
	/**
	 * @return the fGzName
	 */
	public String getfGzName() {
		return fGzName;
	}
	/**
	 * @param fGzName the fGzName to set
	 */
	public void setfGzName(String fGzName) {
		this.fGzName = fGzName;
	}
	/**
	 * @return the fBmID
	 */
	public int getfBmID() {
		return fBmID;
	}
	/**
	 * @param fBmID the fBmID to set
	 */
	public void setfBmID(int fBmID) {
		this.fBmID = fBmID;
	}
	/**
	 * @return the fBmNumber
	 */
	public String getfBmNumber() {
		return fBmNumber;
	}
	/**
	 * @param fBmNumber the fBmNumber to set
	 */
	public void setfBmNumber(String fBmNumber) {
		this.fBmNumber = fBmNumber;
	}
	/**
	 * @return the fBmName
	 */
	public String getfBmName() {
		return fBmName;
	}
	/**
	 * @param fBmName the fBmName to set
	 */
	public void setfBmName(String fBmName) {
		this.fBmName = fBmName;
	}
//	/**
//	 * @return the fRightObject
//	 */
//	public Object getfRightObject() {
//		return fRightObject;
//	}
//	/**
//	 * @param fRightObject the fRightObject to set
//	 */
//	public void setfRightObject(Object fRightObject) {
//		this.fRightObject = fRightObject;
//	}

	/**
	 * @return the rightObjectDTO
	 */
	public List<RightObjectDTO> getRightObjectDTO() {
		return rightObjectDTO;
	}

	/**
	 * @param rightObjectDTO the rightObjectDTO to set
	 */
	public void setRightObjectDTO(List<RightObjectDTO> rightObjectDTO) {
		this.rightObjectDTO = rightObjectDTO;
	}

	/**
	 * @return the fEmpID
	 */
	public int getfEmpID() {
		return fEmpID;
	}

	/**
	 * @param fEmpID the fEmpID to set
	 */
	public void setfEmpID(int fEmpID) {
		this.fEmpID = fEmpID;
	}

	public String getForgid() {
		return forgid;
	}

	public void setForgid(String forgid) {
		this.forgid = forgid;
	}

	public String getfPassword() {
		return fPassword;
	}

	public void setfPassword(String fPassword) {
		this.fPassword = fPassword;
	}

	public String getfAcctID() {
		return fAcctID;
	}

	public void setfAcctID(String fAcctID) {
		this.fAcctID = fAcctID;
	}
	
	
}
