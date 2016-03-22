package com.zuoan.module;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/15.
 */
public class Base implements Serializable{
    private static final long serialVersionUID = -3041996176588455105L;
    public String isDelete;
    public String modifyDate;
    public String createrDate;

    public Base() {
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCreaterDate() {
        return createrDate;
    }

    public void setCreaterDate(String createrDate) {
        this.createrDate = createrDate;
    }
}
