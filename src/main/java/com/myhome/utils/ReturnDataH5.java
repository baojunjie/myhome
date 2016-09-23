package com.myhome.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ReturnDataH5 {
	static ObjectMapper objectMapper;
	private String returnCode = "0000";
	private String msg = "";
	private Object result;
	private boolean success = true;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
	public String toString() {
		return JSONObject.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
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
			/*objectMapper.setSerializationInclusion(Include.NON_EMPTY);
			objectMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
			objectMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
			objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);*/
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}

