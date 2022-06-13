package com.example.common.entity;

import com.example.common.enums.Constants;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginInfo implements Serializable {

    private Long id;
    private String nickname;

    @NotNull(message = "商品名称不允许为空")
    private String phone;
    private String password;
    private String salt;
    private Date registerDate;
    private Date lastLoginDate;
    private int state = Constants.STATE_NORMAL;
    private int userType;//用户类型
    private boolean admin = false;
}
