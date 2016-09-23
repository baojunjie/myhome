/**
 * Project Name:web_myhome
 * File Name:WorksVo.java
 * Package Name:com.myhome.entity.vo
 * Date:2015年9月12日上午10:04:10
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
 */

package com.myhome.entity.vo;


/**
 * @author 1
 * @version
 * @since JDK 1.6
 * @see
 */
public class ReturnQuestionnaire {
    private boolean haveToDecorate;
    private boolean wantBuyHouse;
    private boolean wantToDecorate;
    private boolean myBalcony;
    public boolean isHaveToDecorate() {
        return haveToDecorate;
    }
    public void setHaveToDecorate(boolean haveToDecorate) {
        this.haveToDecorate = haveToDecorate;
    }
    public boolean isWantBuyHouse() {
        return wantBuyHouse;
    }
    public void setWantBuyHouse(boolean wantBuyHouse) {
        this.wantBuyHouse = wantBuyHouse;
    }
    public boolean isWantToDecorate() {
        return wantToDecorate;
    }
    public void setWantToDecorate(boolean wantToDecorate) {
        this.wantToDecorate = wantToDecorate;
    }
    public boolean isMyBalcony() {
        return myBalcony;
    }
    public void setMyBalcony(boolean myBalcony) {
        this.myBalcony = myBalcony;
    }

    
}
