package com.king.MinaService.utils;

import com.google.gson.Gson;
import com.king.MinaService.bean.ResuletBean;
import com.king.MinaService.bean.UserInfo;

public class ResuleUtils {

    public static String getToJson(int code, String msg, UserInfo userInfo){
        return new Gson().toJson(new ResuletBean(code,msg,userInfo));
    }
}
