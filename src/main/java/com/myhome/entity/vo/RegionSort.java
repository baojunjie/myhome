package com.myhome.entity.vo;

public class RegionSort implements Comparable {
	
    private String cityName;
    private int    cityNum;
    private int    cityCode;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityNum() {
        return cityNum;
    }

    public void setCityNum(int cityNum) {
        this.cityNum = cityNum;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public RegionSort(int cityNum, String cityName, int cityCode) {
        this.cityNum = cityNum;
        this.cityName = cityName;
        this.cityCode = cityCode;
    }
    public RegionSort(String cityName,int cityNum){
    	  this.cityName = cityName;
    	  this.cityNum = cityNum;
    }

//    @Override
//    public String toString() {
//        return "\r\tnum:"+cityNum+" name:"+cityName+"\r";
//    }
    public int compareTo(Object o) {
        RegionSort tmp = (RegionSort) o;
        int result = tmp.cityNum > cityNum ? 1 : (tmp.cityNum == cityNum ? 0 : -1);
        if (result == 0) {
            result=tmp.cityName.indexOf(0)>cityName.indexOf(0)?1:-1;
        }
        return result;
    }
}
