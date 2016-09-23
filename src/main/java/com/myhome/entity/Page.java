package com.myhome.entity;




/**
 *
 * 静态页面
 *
 */
public class Page extends AbstractEntity {

    /**
     *
     * 页面的中文名称，比如“首页”
     *
     */
    private String name;
    
    /**
     *
     * 存储页面内容的模板的相对路径，以 "/" 开头
     *
     */
    private String filePath;
    
    /**
     *
     * 发布的相对路径，以 “/" 开头
     *
     */
    private String url;
    
    /**
     * 类型  1：action  2：轮播图
     */
    private int type;
    
    /**
     * 图片顺序
     */
    private int    sort;

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getFilePath() {
        return this.filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
    
    



    
}

