package com.example.mk.bean;

public class OneS {
    //key的值list的集合
    private Integer id;

    private String nameS;

    private Integer valueS;

    private Integer longS;

    public Integer getLongS() {
        return longS;
    }

    public void setLongS(Integer longS) {
        this.longS = longS;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameS() {
        return nameS;
    }

    public void setNameS(String nameS) {
        this.nameS = nameS == null ? null : nameS.trim();
    }

    public Integer getValueS() {
        return valueS;
    }

    public void setValueS(Integer valueS) {
        this.valueS = valueS;
    }
}