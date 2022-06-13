package com.example.loginout.service;


import com.example.common.entity.LoginInfo;
import com.example.common.utils.resultbean.ResultMAX;

public interface LoginService {
    ResultMAX<LoginInfo> login(LoginInfo loginInfo);
}
