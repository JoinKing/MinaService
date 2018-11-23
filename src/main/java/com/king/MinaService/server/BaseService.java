package com.king.MinaService.server;

public interface BaseService {
    int LOGIN_SUCCESS =200; //登陆成功 或者 请求成功
    int LOGIN_FAIl =201; //密码错误
    int LOGIN_NO_REGISTER =202;//账号未注册
    int LOGIN_YES_REGISTER =203;//账号已注册
    int SEND_MISTAKE =204; //发送失败
    int SEND_CODETAKE =205; //验证码已过期
    int PARMS_MIS =300; //参数错误
    int EXCEPTION =-1; //异常

    String SUCCESS = "成功";
    String LOGINSUCCESS = "登录成功";
    String LOGINFAIl ="密码错误";
    String LOGINNOREGISTER ="账号未注册";
    String LOGINYESREGISTER ="账号已注册";
    String PARMSMIS ="参数错误";
    String SENDMISTAKE ="发送失败";
    String SENDCODETAKE ="验证码已过期";
    String EXCEP_TION ="后台数据库操作异常";
}
