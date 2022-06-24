package com.example.common.entity;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Valid
public class Test {

    @NotNull(message = "不能为空name")
    private String name;


}
