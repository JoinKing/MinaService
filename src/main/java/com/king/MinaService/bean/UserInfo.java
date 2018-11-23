package com.king.MinaService.bean;

public class UserInfo {
	
	private String userName = "";
	private String passWord = "";
	private String nickName = "";
	private String headImage = "";
	
	private int age;
	private String sex = "";
	private int accountType = 0;
	private String signature = "";
	private int userState = 0;
	private String email = "";

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public int getUserState() {
		return userState;
	}
	public void setUserState(int userState) {
		this.userState = userState;
	}
	@Override
	public String toString() {
		return "UserInfo [userName=" + userName + ", passWord=" + passWord + ", nickName=" + nickName + ", headImage="
				+ headImage + ", age=" + age + ", sex=" + sex + ", accountType=" + accountType + ", signature="
				+ signature + ", userState=" + userState + "]";
	}
	
	
	
	
	

}
