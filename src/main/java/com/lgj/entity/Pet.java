package com.lgj.entity;

public class Pet {
    private Integer pid;

    private Integer cid;

    private String pname;

    private String photo_urls;

    private Integer tid;

    private String pstatus;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public String getPhoto_urls() {
        return photo_urls;
    }

    public void setPhoto_urls(String photo_urls) {
        this.photo_urls = photo_urls == null ? null : photo_urls.trim();
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getPstatus() {
        return pstatus;
    }

    public void setPstatus(String pstatus) {
        this.pstatus = pstatus == null ? null : pstatus.trim();
    }
}