package com.zuoan.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zuoan.ApiProvider.ApiProvider;
import com.zuoan.module.UserInfo;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by htt on 2016/3/22.
 */
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserInfoResource {

    /**
     * 删除账户
     */
    @Path("delete")
    @DELETE
    public Response delUserInfo(@NotBlank(message = "userId 不能为空") String userId) {
        JSONObject jsonObject = new JSONObject();
        if (ApiProvider.userInfoService.delUserInfo(userId)) {
            jsonObject.put("result", "成功");
            return Response.status(Response.Status.OK).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
        }
        jsonObject.put("result", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }

    /**
     * 修改用户信息
     */
    @Path("update")
    @PUT
    public Response updateUserInfo(@Validated({UserInfo.Update.class}) UserInfo userInfo) {
        JSONObject jsonObject = new JSONObject();
        if (ApiProvider.userInfoService.updateUserInfo(userInfo)) {
            jsonObject.put("result", "成功");
            return Response.status(Response.Status.OK).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
        }
        jsonObject.put("result", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }

    /**
     * 用户信息模糊查询
     */
    @Path("query")
    @GET
    public Response queryUserInfo(@QueryParam("userName") String userName,
                                  @QueryParam("userId") String userId,
                                  @QueryParam("phone") String phone) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPhone(phone);
        userInfo.setUserId(userId);
        List<UserInfo> userInfos = ApiProvider.userInfoService.queryUserInfo(userInfo);
        JSONObject json = new JSONObject();
        if (userInfos != null) {
            JSONArray jsonArray = new JSONArray();
            for (UserInfo user : userInfos) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userId", user.getUserId());
                jsonObject.put("userName", user.getUserName());
                jsonObject.put("phone", user.getPhone());
                jsonObject.put("password", user.getAvatar());
                jsonArray.add(jsonObject);
            }
            json.put("size", jsonArray.size());
            json.put("infos", jsonArray);
        } else {
            json.put("size", 0);
            json.put("infos", new JSONArray());
        }
        return Response.status(Response.Status.OK).entity(json.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{userId}")
    public Response getUserInfo(@PathParam("userId") String userId) {
        UserInfo userInfo = ApiProvider.userInfoService.queryUserInfoById(userId);
        JSONObject json;
        if (userInfo != null) {
            json = (JSONObject) JSON.toJSON(userInfo);
        } else {
            json = new JSONObject();
        }
        return Response.status(Response.Status.OK).entity(json.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }
}
