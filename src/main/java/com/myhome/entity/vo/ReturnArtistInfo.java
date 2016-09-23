package com.myhome.entity.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReturnArtistInfo {
    static ObjectMapper objectMapper;
    private Integer total;
    private List<ArtistModel> list = new ArrayList<ArtistModel>();
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
   
    public List<ArtistModel> getList() {
        return list;
    }
    public void setList(List<ArtistModel> list) {
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
