package com.myhome.entity.vo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SearchArtist {
    static ObjectMapper objectMapper;
    private SearchArtistInfo result;
    private boolean success=true;
 
   
	public SearchArtistInfo getResult() {
		return result;
	}
	public void setResult(SearchArtistInfo result) {
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
