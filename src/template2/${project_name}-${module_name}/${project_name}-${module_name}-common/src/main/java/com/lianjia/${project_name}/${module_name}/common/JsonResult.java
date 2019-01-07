package com.lianjia.${project_name}.${module_name}.common;

import com.alibaba.fastjson.JSONObject;
import com.qyp.config.utils.JsonUtils;



public class JsonResult<T> {

    private int resultCode;
    private String resultInfo;
    private String requestId;

    //兼容app的字段
    private int code;
    private String msg;
    private int errno;
    private String error;

    private T data;

    public JsonResult() {}

    public JsonResult(int resultCode) {

        this.resultCode = resultCode;
        this.code = resultCode;
        this.errno=resultCode;

    }

    public JsonResult(int resultCode, String resultInfo) {

        this.resultCode = resultCode;
        this.code = resultCode;
        this.resultInfo = resultInfo;
        this.msg = resultInfo;
        this.error = resultInfo;
        this.errno=resultCode;

    }

    public int getResultCode() {
        return resultCode;
    }

    public JsonResult<T> setResultCode(int resultCode) {
        this.resultCode = resultCode;
        this.code = resultCode;
        this.errno = resultCode;
        return this;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public JsonResult<T> setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
        this.msg = resultInfo;
        this.error = resultInfo;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public JsonResult<T> setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public T getData() {
        return data;
    }

    public JsonResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
        this.errno = code;
        this.resultCode = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static JsonResult failureResult() {
        return new JsonResult(-1).setData(new JSONObject());
    }
    public static JsonResult failureResult(String error) {
        return failureResult(-1, error).setData(new JSONObject());
    }
    public static JsonResult failureResult(int code, String error) {
        return new JsonResult(code, error).setData(new JSONObject());
    }

    public static JsonResult successResult() {
        return new JsonResult(0).setData(new JSONObject());
    }

    public static JsonResult successResult(Object t) {
        return new JsonResult(0).setData(t);
    }

    /**
     * 避免序列化时的多余标签
     */
    public boolean amSucc() {
        return resultCode == 0;
    }

    @Override
    public String toString() {
        return JsonUtils.read(this);
    }
}
