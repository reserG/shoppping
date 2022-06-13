package com.example.common.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author qiurunze
 */
@Data
public class BaseDomain implements Serializable {

    @NotNull(message = "商品名称不允许为空")
    protected Long id;

    @Min(value = 0,message = "不小于0")
    private int price;
}
