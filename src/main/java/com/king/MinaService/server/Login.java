package com.king.MinaService.server;

import com.king.MinaService.bean.UserInfo;
import com.king.MinaService.dao.UserInfoImpl;
import com.king.MinaService.utils.ResuleUtils;
import com.king.MinaService.utils.SendEmail;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/user")
public class Login implements BaseService{
	@RequestMapping(value="/register",method =RequestMethod.POST )
	public String  register(String name ,Integer paswword ) {
		UserInfoImpl userInfo = new UserInfoImpl();
		try {
			UserInfo user = userInfo.onFindById(name);
			if (null!=user){
				SendEmail.send163Email("191160217@qq.com","你aa好zzz","你zz好zz");
				return  ResuleUtils.getToJson(LOGIN_YES_REGISTER,LOGINYESREGISTER,user);
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



	

}
