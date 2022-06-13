package com.example.common.exception;


import com.example.common.enums.ResultStatus;

public class GlobleException extends RuntimeException {


    private ResultStatus status;

    public GlobleException(ResultStatus status) {
        super();
        this.status = status;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }
}
