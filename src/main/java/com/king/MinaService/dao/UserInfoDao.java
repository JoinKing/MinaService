package com.king.MinaService.dao;

import com.king.MinaService.bean.UserInfo;

import java.sql.SQLException;
import java.util.List;


public interface  UserInfoDao {
	//添加数据
	public void onAdd(UserInfo user)throws SQLException;
	//更新数据
	public void onUpdate(UserInfo user)throws SQLException;
	//删除数据
	public void onDelete(UserInfo user)throws SQLException;
	//查询数据
	public UserInfo onFindById(String userName)throws SQLException;
	//查询所有数据
	public List<UserInfo> onFindAll()throws SQLException;

}
