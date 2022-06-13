package com.example.loginout.service.imp;


import com.example.common.entity.LoginInfo;
import com.example.common.enums.Constants;
import com.example.common.enums.ResultStatus;
import com.example.common.utils.MD5Utils;
import com.example.common.utils.resultbean.ResultMAX;
import com.example.loginout.mapper.LoginInfoMapper;
import com.example.loginout.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginServiceImp implements LoginService {
    @Autowired
    LoginInfoMapper logininfoMapper;

    @Override
    public ResultMAX<LoginInfo> login(LoginInfo loginInfo) {
        ResultMAX<LoginInfo> result = ResultMAX.build();
        LoginInfo loginUser = logininfoMapper.getLoginInfoByNickname(loginInfo.getPhone(), Constants.USERTYPE_NORMAL);
        if (loginUser != null) {
            String pwd = MD5Utils.formPassToDBPass(loginInfo.getPassword(),loginInfo.getSalt());
        }
        result.withError(ResultStatus.LOGIN_FIAL);
        return result;
    }
}
