package com.myhome.entity.vo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReturnComment {

    static ObjectMapper objectMapper;
    private ReturnCommentInfo result;
    private String Msg;
    private Integer code;
    private boolean success=true;
    
    public ReturnCommentInfo getResult() {
        return result;
    }
    public void setResult(ReturnCommentInfo result) {
        this.result = result;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getMsg() {
        return Msg;
    }
    public void setMsg(String msg) {
        Msg = msg;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    @Override
    public String toString() {
        return JSONObject.toJSON(this).toString();
    }
    /**
     * jackson         
     * 解决fastjson将对象转换成json时出现的递归错误
     * toJSon
     * @author gwb
     * @param object
     * @return
     * 2015年9月18日 下午5:45:56
     */
    public static String toJSon(Object object) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
