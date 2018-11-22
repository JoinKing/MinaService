package com.king.MinaService.server;

public interface BaseService {
    int LOGIN_SUCCESS =200; //登陆成功 或者 请求成功
    int LOGIN_FAIl =201; //密码错误
    int LOGIN_NO_REGISTER =202;//账号未注册
    int LOGIN_YES_REGISTER =203;//账号已注册
    int PARMS_MIS =300; //参数错误

    String LOGINSUCCESS = "登陆成功";
    String LOGINFAIl ="密码错误";
    String LOGINNOREGISTER ="账号未注册";
    String LOGINYESREGISTER ="账号已注册";
    String PARMSMIS ="参数错误";
}
