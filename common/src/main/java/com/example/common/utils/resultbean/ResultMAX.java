package com.example.common.utils.resultbean;



import com.example.common.enums.ResultStatus;

import java.io.Serializable;

public class ResultMAX<T> extends AbstractResult implements Serializable {
    private static final long serialVersionUID = 867933019328199779L;
    private T data;
    private Integer count;

    protected ResultMAX() {
    }

    protected ResultMAX(ResultStatus status, String message) {
        super(status, message);
    }

    protected ResultMAX(ResultStatus status) {
        super(status);
    }

    public static <T> ResultMAX<T> build() {
        return new ResultMAX(ResultStatus.SUCCESS, (String) null);
    }

    public static <T> ResultMAX<T> build(String message) {
        return new ResultMAX(ResultStatus.SUCCESS, message);
    }

    public static <T> ResultMAX<T> error(ResultStatus status) {
        return new ResultMAX<T>(status);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void success(T value) {
        this.success();
        this.data = value;
        this.count = 0;
    }

}
