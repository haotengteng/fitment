package com.zuoan.resource;

import com.alibaba.fastjson.JSONObject;
import com.zuoan.ApiProvider.ApiProvider;
import com.zuoan.Utils.UtilTools;
import com.zuoan.module.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 * Created by haotengteng on 2016/3/27.
 */
@Path("account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
    /**
     * 用户注册
     */
    @Path("register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(@Validated({UserInfo.Add.class}) UserInfo userInfo) {
        JSONObject jsonObject = new JSONObject();
        userInfo.setUserId(UtilTools.UUIDUtil());
        if (ApiProvider.userInfoService.addUserInfo(userInfo)) {

            jsonObject.put("result", "成功");
            return Response.status(Response.Status.OK).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
        }
        jsonObject.put("result", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }

    /**
     * 用户登录
     *
     * @param phone
     * @param userName
     * @param password
     * @return
     */
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("phone") String phone,
                          @FormParam("userName") String userName,
                          @NotBlank @FormParam("password") String password) {
        JSONObject jsonObject = new JSONObject();
        UserInfo userInfo;
        if (StringUtils.isNotBlank(phone)) {
            userInfo = ApiProvider.userInfoService.queryUserInfoByPhone(phone);
        } else if (StringUtils.isNotBlank(userName)) {
            userInfo = ApiProvider.userInfoService.queryUserInfoByUserName(userName);
        } else {
            jsonObject.put("msg", "请填写用户名或者手机号码");
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
        }
        if (userInfo != null) {
            if (password.equals(userInfo.getPassword())) {
                NewCookie newCookie = new NewCookie("token", userInfo.getUserId(),"/", null, NewCookie.DEFAULT_VERSION, null,
                        -1, null, false, true);
                //将userId存入缓存
                ApiProvider.redisCacheManage.addCache(userInfo.getUserId(), userInfo, 24*60*60);
                return Response.status(Response.Status.OK).cookie(newCookie).build();
            } else {
                jsonObject.put("msg", "密码错误");
                return Response.status(Response.Status.BAD_REQUEST).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
            }
        } else {
            jsonObject.put("msg", "用户名或手机号码错误");
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
        }
    }

}
