package com.myhome.entity;

import java.sql.Timestamp;




/**
 *
 * 活动
 *
 */
public class Activity extends AbstractEntity {

    /**
     *
     * 标题
     *
     */
    private String title;
    
    /**
    *
    * 描述
    *
    */
    private String    description;

    /**
     *
     * 图片
     *
     */
    private String path;
    
    /**
     *
     * 超链接
     *
     */
    private String url;
    
    /**
    *
    * 时间
    *
    */
    private Timestamp time;
    /**
    *
    * 月
    *
    */
    private String month;
    /**
    *
    * 日
    *
    */
    private String date;
    /**
    *
    * 时
    *
    */
    private String hour;
    
    /**
    *
    * 年
    *
    */
    private String year;

    /**
    *
    * last
    *
    */
    private String last;

    public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }


}

