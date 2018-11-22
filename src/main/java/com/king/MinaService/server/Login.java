package com.king.MinaService.server;

import com.king.MinaService.bean.UserInfo;
import com.king.MinaService.dao.UserInfoImpl;
import com.king.MinaService.utils.ResuleUtils;
import com.king.MinaService.utils.SendEmail;
import com.king.MinaService.utils.TimePicker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/user")
public class Login implements BaseService, TimePicker.OverCallback {
	private int num;
	Thread timePicker = new Thread(new TimePicker());
	@RequestMapping(value="/register",method =RequestMethod.POST )
	public String  register(String name ,String psd,String eamil,String code ) {
		UserInfoImpl userInfo = new UserInfoImpl();
		try {
			UserInfo user = userInfo.onFindById(name);
			if (null!=user){
				return  ResuleUtils.getToJson(LOGIN_YES_REGISTER,LOGINYESREGISTER,null);
			}else {
				//注册流程 邮件-发送验证码-
				return LOGINNOREGISTER;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  ResuleUtils.getToJson(PARMS_MIS,PARMSMIS,null);
	}

	@RequestMapping(value="/loginout",method =RequestMethod.GET )
	public String  loginout(UserInfo paswword ) {
		return  "2" ;
	}
	@RequestMapping(value="/code",method =RequestMethod.GET )
	public String  getCode(String email,String type ) {
		num=(int)(Math.random()*9000)+1000;
		String title = "";
		if (title.equals("1")){
			title = "KChat注册验证码";
		}else {
			title = "KChat修改验证码";
		}
		String content = "您的验证码为:"+num;
		if (email.contains("@qq.com")){
			boolean state = SendEmail.sendQQmail(email,title,content);
			if (state){
				return  ResuleUtils.getToJson(LOGIN_SUCCESS,LOGINSUCCESS,null);
			}else {
				timePicker.start();
				timePicker.run();
				return  ResuleUtils.getToJson(SEND_MISTAKE,SENDMISTAKE,null);
			}
		}else if (email.contains("@163.com")){
			boolean state = SendEmail.send163Email(email,title,content);
			if (state){
				timePicker.start();
				timePicker.run();
				return  ResuleUtils.getToJson(LOGIN_SUCCESS,LOGINSUCCESS,null);
			}else {
				return  ResuleUtils.getToJson(SEND_MISTAKE,SENDMISTAKE,null);
			}
		}
		return  "2" ;
	}


	@Override
	public void over(boolean isOver) {
		if (isOver){
			num =-1;
		}
	}
}
