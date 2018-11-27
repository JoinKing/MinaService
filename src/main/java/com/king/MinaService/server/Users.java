package com.king.MinaService.server;

import com.king.MinaService.bean.UserInfo;
import com.king.MinaService.dao.UserInfoImpl;
import com.king.MinaService.utils.ResuleUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class Users implements BaseService {

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String users() {
        UserInfoImpl userInfo = new UserInfoImpl();
        try {
            List<UserInfo> userInfos = userInfo.onFindAll();

            if (userInfos.size() > 0) {
                return ResuleUtils.getToJsons(LOGIN_SUCCESS, SUCCESS, userInfos);
            } else {
                return ResuleUtils.getToJsons(EXCEPTION, NODATA, null);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return ResuleUtils.getToJsons(EXCEPTION, EXCEP_TION, null);
        }
    }
}
