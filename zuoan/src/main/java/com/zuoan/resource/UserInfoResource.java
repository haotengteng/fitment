package com.zuoan.resource;

import com.alibaba.fastjson.JSONObject;
import com.zuoan.ApiProvider.ApiProvider;
import com.zuoan.module.UserInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * Created by htt on 2016/3/22.
 */
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserInfoResource {

    @Path("add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUserInfo(UserInfo userInfo){
        JSONObject jsonObject = new JSONObject();
      if (ApiProvider.userInfoService.addUserInfo(userInfo)){

          jsonObject.put("result","成功");
          return Response.status(Response.Status.OK).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
      }
        jsonObject.put("result","失败");
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }
}
