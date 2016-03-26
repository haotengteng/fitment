package com.zuoan.module;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * Created by Administrator on 2016/3/22.
 */
public class UserInfo extends Base {
    private static final long serialVersionUID = -8400837878862818942L;
    @NotBlank(message = "{user.id.null}", groups = {Update.class})
    private String userId;
    @NotBlank(message = "{user.name.null}", groups = {Add.class})
    private String userName;
    @NotBlank(message = "{user.password.null}", groups = {Add.class})
    private String password;
    private String avatar;
    private String phone;

    public UserInfo() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public interface Add {
    }

    public interface Update {
    }
}
