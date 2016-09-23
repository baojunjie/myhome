package com.myhome.entity.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myhome.entity.Works;

public class WorksListInfo {
    static ObjectMapper objectMapper;
    private List<Works> boylist = new ArrayList<Works>();
    private List<Works> girllist = new ArrayList<Works>();
    
    public List<Works> getBoylist() {
		return boylist;
	}
	public void setBoylist(List<Works> boylist) {
		this.boylist = boylist;
	}
	
	public List<Works> getGirllist() {
		return girllist;
	}
	public void setGirllist(List<Works> girllist) {
		this.girllist = girllist;
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
