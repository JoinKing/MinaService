package com.king.MinaService.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.king.MinaService.bean.ResuletBean;
import com.king.MinaService.bean.UserInfo;

import java.util.List;

public class ResuleUtils {

    public static String getToJson(int code, String msg, UserInfo userInfo){
        return new Gson().toJson(new ResuletBean(code,msg,userInfo));
    }

    public static String getToJsons(int code, String msg,  List<UserInfo> userInfo){

        JsonObject object = new JsonObject();
        object.addProperty("code",code);
        object.addProperty("msg",msg);
        object.add("list",new Gson().toJsonTree(userInfo));
        return object.toString();
    }

}
