package com.zuoan.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zuoan.ApiProvider.ApiProvider;
import com.zuoan.module.UserInfo;
import com.zuoan.utils.mybatis.Page;
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
    @Path("{userId}")
    @DELETE
    public Response delUserInfo(@NotBlank(message = "userId 不能为空") @PathParam("userId") String userId) {
        JSONObject jsonObject = new JSONObject();
        if (ApiProvider.userInfoService.delUserInfo(userId)) {
            return Response.status(Response.Status.OK).build();
        }
        jsonObject.put("error", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }

    /**
     * 修改用户信息
     */
    @Path("{userId}")
    @PUT
    public Response updateUserInfo(@Validated({UserInfo.Update.class}) UserInfo userInfo, @PathParam("userId") String userId) {
        JSONObject jsonObject = new JSONObject();
        userInfo.setUserId(userId);
        if (ApiProvider.userInfoService.updateUserInfo(userInfo)) {
            return Response.status(Response.Status.OK).build();
        }
        jsonObject.put("error", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }

    @Path("{userId}")
    @GET
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

    /**
     * 用户信息模糊查询
     */
    @Path("page/{pageIndex}")
    @GET
    public Response queryUserInfo(@PathParam("pageIndex") @DefaultValue("1") final String pageIndexStr,
                                  @QueryParam("pageSize") @DefaultValue("10") final String pageSizeStr,
                                  @QueryParam("userName") String userName,
                                  @QueryParam("phone") String phone) {
        Integer pageNum = Integer.parseInt(pageIndexStr);
        Integer pageSize = Integer.parseInt(pageSizeStr);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPhone(phone);
        Page page = new Page(pageNum, pageSize);
        Page<UserInfo> userInfoPage = ApiProvider.userInfoService.queryUserInfoByPage(userInfo,page);
        List<UserInfo> userInfos = userInfoPage.getResult();
        JSONObject json = new JSONObject();
        if (userInfos != null) {
            JSONArray jsonArray = new JSONArray();
            for (UserInfo user : userInfos) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(user);
                jsonArray.add(jsonObject);
            }
            json.put("totalSize", userInfoPage.getTotal());
            json.put("page",userInfoPage.getPageNum());
            json.put("info", jsonArray);
        } else {
            json.put("totalSize", 0);
            json.put("page",0);
            json.put("info", new JSONArray());
        }
        return Response.status(Response.Status.OK).entity(json.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }
}
