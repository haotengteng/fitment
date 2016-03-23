package com.zuoan.resource;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.util.Auth;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * Created by Xujy on 2016/3/24.
 */
@Path("qiniu")
@Produces(MediaType.APPLICATION_JSON)
public class QiniuResource {
    private static final String ACCESS_KEY = "JyZtCiplDLN68PozIMwCQ3TkCzQMUCeOvYqpobYM";
    private static final String SECRET_KEY = "x7Va3NeTZ5LMqhTcMY7iPFMkMYF9slOTRWT1C_FD";
    private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    @GET
    @Path("yuejili")
    public Response getTestToken(@CookieParam("token") String token) {
        String qiniuUpToken = auth.uploadToken("yuejili");

        JSONObject json = new JSONObject();
        json.put("uptoken", qiniuUpToken);
        json.put("prefix", "http://7xs3kj.com1.z0.glb.clouddn.com");
        // 操作成功
        return Response.status(Response.Status.OK).entity(json.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }
}
