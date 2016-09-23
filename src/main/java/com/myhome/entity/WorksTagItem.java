package com.myhome.entity;




/**
 *
 * 暂时用作作品分类，比如：中国画、油画、素描……
 * 目前的需求可能是一对一就够了，但还是设计成多对多
 *
 */
public class WorksTagItem extends AbstractEntity {

    private Works works;
    
    private Tag tag;
    
    

    public Works getWorks() {
        return this.works;
    }
    
    public void setWorks(Works works) {
        this.works = works;
    }
    
    public Tag getTag() {
        return this.tag;
    }
    
    public void setTag(Tag tag) {
        this.tag = tag;
    }
    
    



    
}
