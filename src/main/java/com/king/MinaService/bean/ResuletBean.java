package com.king.MinaService.bean;

import com.king.MinaService.bean.UserInfo;

/**
 * @author KING
 *
 */
public class ResuletBean {
	private int code = -1;
	private String msg = "";
	private UserInfo userInfo;

	public ResuletBean(int code, String msg, UserInfo userInfo) {
		this.code = code;
		this.msg = msg;
		this.userInfo = userInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
