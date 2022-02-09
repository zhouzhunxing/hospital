package com.yjm.hospital.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "返回结果")
public class Result<T> {

    @ApiModelProperty(value = "标识")
    private boolean flag;
    @ApiModelProperty(value = "返回代码")
    private Integer code;
    @ApiModelProperty(value = "返回信息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private T data;
    @ApiModelProperty(value = "返回数据2")
    private Object objectData;
    @ApiModelProperty(value = "返回状态")
    private Integer status;

    public Result() {
    }

    public Result(boolean flag, Integer code, String message, T data) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, Integer code, String message, T data, Object objectData) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
        this.objectData = objectData;
    }

    public Result(boolean flag, Integer code, String message, T data, Integer status) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public Result(boolean flag, Integer code, String message, T data, Object objectData, Integer status) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
        this.objectData = objectData;
        this.status = status;
    }

    public Result(boolean flag, Integer code, String message) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getObjectData() {
        return objectData;
    }

    public void setObjectData(Object objectData) {
        this.objectData = objectData;
    }
}
