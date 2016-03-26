package com.zuoan.module;

import java.io.Serializable;

/**
 *
 * Created by Administrator on 2016/3/15.
 */
public class Base implements Serializable{
    private static final long serialVersionUID = -3041996176588455105L;
    private String isDelete;
    private String modifyTime;
    private String createTime;

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
