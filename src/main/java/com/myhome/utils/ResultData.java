package com.myhome.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myhome.entity.Artist;
import com.myhome.entity.Works;
import com.myhome.entity.vo.ArtistModel;

public class ResultData {
    static ObjectMapper objectMapper;
	private Boolean success = true;
	private Boolean result = true;
	private String msg="" ;
	private Long picId;
	private Artist artist;
	private Integer total;
	private String path;
	private String orginpath;
	private Integer code;
	private List<String> msgs = new ArrayList<String>();
	private List<ArtistModel> artistlist = new ArrayList<ArtistModel>();
	private List<Works> workslist = new ArrayList<Works>();
	
	public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public Boolean getResult() {
        return result;
    }
    public void setResult(Boolean result) {
        this.result = result;
    }
    public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public List<ArtistModel> getArtistlist() {
        return artistlist;
    }
    public void setArtistlist(List<ArtistModel> artistlist) {
        this.artistlist = artistlist;
    }
    
    public List<Works> getWorkslist() {
        return workslist;
    }
    public void setWorkslist(List<Works> workslist) {
        this.workslist = workslist;
    }
    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    
    public String getOrginpath() {
        return orginpath;
    }
    public void setOrginpath(String orginpath) {
        this.orginpath = orginpath;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public Long getPicId() {
		return picId;
	}
	public void setPicId(Long picId) {
		this.picId = picId;
	}
	public List<String> getMsgs() {
		return msgs;
	}
	public void setMsgs(List<String> msgs) {
		this.msgs = msgs;
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
