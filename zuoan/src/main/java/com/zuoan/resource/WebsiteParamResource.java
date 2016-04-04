package com.zuoan.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zuoan.ApiProvider.ApiProvider;
import com.zuoan.Utils.UtilTools;
import com.zuoan.module.WebsiteParam;
import com.zuoan.utils.mybatis.Page;
import org.hibernate.validator.constraints.NotBlank;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * Created by xujy on 2016/3/26.
 */
@Path("param")
@Produces(MediaType.APPLICATION_JSON)
public class WebsiteParamResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postWebsiteParam(WebsiteParam websiteParam) {
        JSONObject jsonObject = new JSONObject();
        websiteParam.setParamId(UtilTools.UUIDUtil());
        if (ApiProvider.websiteParamService.addWebsiteParam(websiteParam)) {
            jsonObject.put("paramId", websiteParam.getParamId());
            return Response.status(Response.Status.OK).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
        }
        jsonObject.put("error", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }

    @Path("{paramId}")
    @DELETE
    public Response delWebsiteParam(@NotBlank(message = "paramId 不能为空") @PathParam("paramId") final String paramId) {
        JSONObject jsonObject = new JSONObject();
        if (ApiProvider.websiteParamService.delWebsiteParam(paramId)) {
            return Response.status(Response.Status.OK).build();
        }
        jsonObject.put("error", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }

    @Path("{paramId}")
    @PUT
    public Response putWebsiteParam(WebsiteParam websiteParam, @PathParam("paramId") final String paramId) {
        JSONObject jsonObject = new JSONObject();
        websiteParam.setParamId(paramId);
        if (ApiProvider.websiteParamService.updateWebsiteParam(websiteParam)) {
            return Response.status(Response.Status.OK).build();
        }
        jsonObject.put("error", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{paramId}")
    public Response getWebsiteParam(@PathParam("paramId") String paramId) {
        WebsiteParam websiteParam = ApiProvider.websiteParamService.queryWebsiteParamById(paramId);
        JSONObject json;
        if (websiteParam != null) {
            json = (JSONObject) JSON.toJSON(websiteParam);
        } else {
            json = new JSONObject();
        }
        return Response.status(Response.Status.OK).entity(json.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/code/{paramCode}")
    public Response getWebsiteParamByCode(@PathParam("paramCode") String paramCode) {
        WebsiteParam websiteParam = ApiProvider.websiteParamService.queryWebsiteParamByCode(paramCode);
        JSONObject json;
        if (websiteParam != null) {
            json = (JSONObject) JSON.toJSON(websiteParam);
        } else {
            json = new JSONObject();
        }
        return Response.status(Response.Status.OK).entity(json.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }

    @Path("page/{pageIndex}")
    @GET
    public Response queryWebsiteParam(@PathParam("pageIndex") @DefaultValue("1") final String pageIndexStr,
                                      @QueryParam("pageSize") @DefaultValue("10") final String pageSizeStr,
                                      @QueryParam("paramCode") String paramCode,
                                      @QueryParam("paramName") String paramName) {
        Integer pageNum = Integer.parseInt(pageIndexStr);
        Integer pageSize = Integer.parseInt(pageSizeStr);
        WebsiteParam query = new WebsiteParam();
        query.setParamCode(paramCode);
        query.setParamName(paramName);
        Page page = new Page(pageNum,pageSize);
        Page<WebsiteParam> websiteParamPage = ApiProvider.websiteParamService.queryWebsiteParamByPage(query,page);
        List<WebsiteParam> lists = websiteParamPage.getResult();
        JSONObject json = new JSONObject();
        if (lists != null) {
            JSONArray jsonArray = new JSONArray();
            for (WebsiteParam websiteParam : lists) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(websiteParam);
                jsonArray.add(jsonObject);
            }
            json.put("totalSize", websiteParamPage.getTotal());
            json.put("page",websiteParamPage.getPageNum());
            json.put("info", jsonArray);
        } else {
            json.put("totalSize", 0);
            json.put("page",0);
            json.put("info", new JSONArray());
        }
        return Response.status(Response.Status.OK).entity(json.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }

    @Path("list")
    @GET
    public Response queryFeedbackList(@QueryParam("paramCode") String paramCode,
                                      @QueryParam("paramName") String paramName) {
        WebsiteParam query = new WebsiteParam();
        query.setParamCode(paramCode);
        query.setParamName(paramName);
        List<WebsiteParam> lists = ApiProvider.websiteParamService.queryWebsiteParam(query);
        JSONObject json = new JSONObject();
        if (lists != null) {
            JSONArray jsonArray = new JSONArray();
            for (WebsiteParam websiteParam : lists) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(websiteParam);
                jsonArray.add(jsonObject);
            }
            json.put("totalSize", lists.size());
            json.put("list", jsonArray);
        } else {
            json.put("totalSize", 0);
            json.put("list", new JSONArray());
        }
        return Response.status(Response.Status.OK).entity(json.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }
}
