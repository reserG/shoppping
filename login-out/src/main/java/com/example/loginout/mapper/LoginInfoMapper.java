package com.example.loginout.mapper;

import com.example.common.entity.LoginInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LoginInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(LoginInfo record);

    LoginInfo selectByPrimaryKey(Long id);

    List<LoginInfo> selectAll();

    int updateByPrimaryKey(LoginInfo record);

    int getCountByNickname(@Param("nickname") String nickname,
                           @Param("userType") int userType);

    LoginInfo getLoginInfoByNickname(@Param("phone") String phone,
                                     @Param("userType") int userType);

    LoginInfo login(@Param("name") String name,
                    @Param("password") String password, @Param("userType") int userType);

    List<Map<String, Object>> autoComplate(@Param("word") String word, @Param("userType") int userType);
}