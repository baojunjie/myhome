package com.myhome.entity.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myhome.entity.Artist;
import com.myhome.entity.Works;
import com.myhome.entity.WorksTagItem;

public class PassArtistInfo{
    static ObjectMapper objectMapper;
    private Integer total; 
    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<Map<String, Object>> getList() {
		return list;
	}
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
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
