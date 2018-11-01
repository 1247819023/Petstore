package com.lgj.entity;

import java.util.Date;

public class Order {
    private Integer oid;

    private Integer per_id;

    private Integer qiantity;

    private Date ship_date;

    private String ostatus;

    private Boolean complete;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getPer_id() {
        return per_id;
    }

    public void setPer_id(Integer per_id) {
        this.per_id = per_id;
    }

    public Integer getQiantity() {
        return qiantity;
    }

    public void setQiantity(Integer qiantity) {
        this.qiantity = qiantity;
    }

    public Date getShip_date() {
        return ship_date;
    }

    public void setShip_date(Date ship_date) {
        this.ship_date = ship_date;
    }

    public String getOstatus() {
        return ostatus;
    }

    public void setOstatus(String ostatus) {
        this.ostatus = ostatus == null ? null : ostatus.trim();
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}