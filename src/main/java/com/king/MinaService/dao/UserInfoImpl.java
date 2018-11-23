package com.king.MinaService.dao;

import com.king.MinaService.bean.UserInfo;
import com.king.MinaService.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UserInfoImpl implements UserInfoDao{

	@Override
	public void onAdd(UserInfo user) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into userinfor(userName,passWord,nickName,headImage,age,sex,accountType,signature,userState,email)values(?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassWord());
			ps.setString(3, user.getNickName());
			ps.setString(4, user.getHeadImage());
			ps.setInt(5, user.getAge());
			ps.setString(6, user.getSex());
			ps.setInt(7, user.getAccountType());
			ps.setString(8, user.getSignature());
			ps.setInt(9, user.getUserState());
			ps.setString(10, user.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("添加数据失败");
		}
		finally {
			DBUtils.close(null, ps, conn);
		}



	}

	@Override
	public void onUpdate(UserInfo user) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update userinfor set userName=? passWord=?,nickName=?,headImage=?,age=?,sex=?,accountType=?,signature=?,userState=?,email=? where userName=?";
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassWord());
			ps.setString(3, user.getNickName());
			ps.setString(4, user.getHeadImage());
			ps.setInt(5, user.getAge());
			ps.setString(6, user.getSex());
			ps.setInt(7, user.getAccountType());
			ps.setString(8, user.getSignature());
			ps.setInt(9, user.getUserState());
			ps.setString(10, user.getEmail());
			ps.setString(11, user.getUserName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("更新数据失败");
		}
		finally {
			DBUtils.close(null, ps, conn);
		}

	}

	@Override
	public void onDelete(UserInfo user) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "delete from userinfor where userName=?";
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("更新数据失败");
		}
		finally {
			DBUtils.close(null, ps, conn);
		}


	}

	@Override
	public UserInfo onFindById(String userName) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		UserInfo user = null;
		ResultSet rs = null;
		String sql = "select userName,passWord,nickName,headImage,age,sex,accountType,signature,userState,email from userinfor where userName=?";
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			if(rs.next()) {
				user = new UserInfo();
				user.setUserName(rs.getString(1));
				user.setPassWord(rs.getString(2));
				user.setNickName(rs.getString(3));
				user.setHeadImage(rs.getString(4));
				user.setAge(rs.getInt(5));
				user.setSex(rs.getString(6));
				user.setAccountType(rs.getInt(7));
				user.setSignature(rs.getString(8));
				user.setUserState(rs.getInt(9));
				user.setEmail(rs.getString(10));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("根据ID查询数据失败");
		}
		finally {
			DBUtils.close(null, ps, conn);
		}
		return user;
	}

	@Override
	public List<UserInfo> onFindAll() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		UserInfo user = null;
		ResultSet rs = null;
		List<UserInfo> users = new ArrayList<UserInfo>();
		String sql = "select userName,passWord,nickName,headImage,age,sex,accountType,signature,userState,email from userinfor";
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				user = new UserInfo();
				user.setUserName(rs.getString(1));
				user.setPassWord(rs.getString(2));
				user.setNickName(rs.getString(3));
				user.setHeadImage(rs.getString(4));
				user.setAge(rs.getInt(5));
				user.setSex(rs.getString(6));
				user.setAccountType(rs.getInt(7));
				user.setSignature(rs.getString(8));
				user.setUserState(rs.getInt(9));
				user.setEmail(rs.getString(10));
				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("查询全部数据失败");
		}
		finally {
			DBUtils.close(null, ps, conn);
		}
		return users;
	}
}
