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

    private int num = SEND_CODETAKE;

    public Login() {
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(String name, String psd, String email, String code) {
        UserInfoImpl userInfo = new UserInfoImpl();
        try {
            UserInfo user = userInfo.onFindById(name);
            if (null != user) {
                return ResuleUtils.getToJson(LOGIN_YES_REGISTER, LOGINYESREGISTER, user);
            } else {
                //注册流程 邮件-发送验证码-
                if (null == name || psd == null || email == null || code == null) {
                    if (null == name) {
                        return ResuleUtils.getToJson(PARMS_MIS, "用户名不能为空", null);
                    }
                    if (null == psd) {
                        return ResuleUtils.getToJson(PARMS_MIS, "密码不能为空", null);
                    }
                    if (null == email) {
                        return ResuleUtils.getToJson(PARMS_MIS, "eamil不能为空", null);
                    }
                    if (null == code) {
                        return ResuleUtils.getToJson(PARMS_MIS, "验证码不能为空", null);
                    }
                } else {
                    //验证验证码
                    if (num == SEND_CODETAKE) {
                        return ResuleUtils.getToJson(SEND_CODETAKE, SENDCODETAKE, null);
                    } else {//插入数据库
                        if (String.valueOf(num).equals(code)) {
                            UserInfo u = new UserInfo();
                            u.setUserName(name);
                            u.setPassWord(psd);
                            u.setEmail(email);
                            userInfo.onAdd(u);
                            return ResuleUtils.getToJson(LOGIN_SUCCESS, SUCCESS, null);
                        } else {
                            return ResuleUtils.getToJson(SEND_CODETAKE, SENDCODETAKE, null);
                        }

                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResuleUtils.getToJson(PARMS_MIS, e.getMessage(), null);
        }
        return ResuleUtils.getToJson(PARMS_MIS, PARMSMIS, null);
    }


    @RequestMapping(value = "/code", method = RequestMethod.POST)
    public String getCode(String email, String type) {
        num = (int) (Math.random() * 9000) + 1000;
        String title = "";
        if (type.equals("1")) {
            title = "KChat注册验证码";
        } else {
            title = "KChat修改验证码";
        }
        TimePicker timePicker = new TimePicker();
        timePicker.setCallback(this);
        String content = "您的验证码为:" + num;
        if (email.contains("@qq.com")) {
            boolean state = SendEmail.sendQQmail(email, title, content);
            if (state) {
                return ResuleUtils.getToJson(LOGIN_SUCCESS, LOGINSUCCESS, null);
            } else {
                timePicker.start();
                return ResuleUtils.getToJson(SEND_MISTAKE, SUCCESS, null);
            }
        } else if (email.contains("@163.com")) {
            boolean state = SendEmail.send163Email(email, title, content);
            if (state) {
                timePicker.start();
                return ResuleUtils.getToJson(LOGIN_SUCCESS, LOGINSUCCESS, null);
            } else {
                return ResuleUtils.getToJson(SEND_MISTAKE, SUCCESS, null);
            }
        }
        return ResuleUtils.getToJson(PARMS_MIS, PARMSMIS, null);
    }

    /**
     *
     * @param phone
     * @param psd
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String getLogion(String phone, String psd) {
        UserInfoImpl userInfo = new UserInfoImpl();

        if (null == phone || psd == null ) {
            if (null == phone) {
                return ResuleUtils.getToJson(PARMS_MIS, "用户名不能为空", null);
            }
            if (null == psd) {
                return ResuleUtils.getToJson(PARMS_MIS, "密码不能为空", null);
            }

        } else {
            try {
                UserInfo user = userInfo.onFindById(phone);
                if (null != user) {
                    //查询到数据源账号
                    if (phone.equals(user.getUserName())&&psd.equals(user.getPassWord())){
                        return ResuleUtils.getToJson(LOGIN_SUCCESS, LOGINSUCCESS, user);
                    }else {
                        return ResuleUtils.getToJson(LOGIN_FAIl, LOGINFAIl, null);
                    }
                } else {
                    //该账号未注册
                    return ResuleUtils.getToJson(LOGIN_NO_REGISTER, LOGINNOREGISTER, null);

                }
            } catch (SQLException e) {
                e.printStackTrace();
                return ResuleUtils.getToJson(PARMS_MIS, e.getMessage(), null);
            }
        }
        return ResuleUtils.getToJson(PARMS_MIS, PARMSMIS, null);
    }


    @Override
    public void over(boolean isOver) {
        if (isOver) {
            num = SEND_CODETAKE;
        }
    }
}
