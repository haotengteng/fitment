package com.zuoan.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zuoan.ApiProvider.ApiProvider;
import com.zuoan.Utils.UtilTools;
import com.zuoan.module.FeedbackInfo;
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
@Path("feedback")
@Produces(MediaType.APPLICATION_JSON)
public class FeedbackInfoResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postFeedbackInfo(FeedbackInfo feedbackInfo) {
        JSONObject jsonObject = new JSONObject();
        feedbackInfo.setFeedbackId(UtilTools.UUIDUtil());
        if (ApiProvider.feedbackInfoService.addFeedbackInfo(feedbackInfo)) {
            jsonObject.put("feedbackId", feedbackInfo.getFeedbackId());
            return Response.status(Response.Status.OK).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
        }
        jsonObject.put("error", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }

    @Path("{feedbackId}")
    @DELETE
    public Response delFeedbackInfo(@NotBlank(message = "feedbackId 不能为空") @PathParam("feedbackId") final String feedbackId) {
        JSONObject jsonObject = new JSONObject();
        if (ApiProvider.feedbackInfoService.delFeedbackInfo(feedbackId)) {
            return Response.status(Response.Status.OK).build();
        }
        jsonObject.put("error", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }

    @Path("{feedbackId}")
    @PUT
    public Response putFeedbackInfo(FeedbackInfo feedbackInfo, @PathParam("feedbackId") final String feedbackId) {
        JSONObject jsonObject = new JSONObject();
        feedbackInfo.setFeedbackId(feedbackId);
        if (ApiProvider.feedbackInfoService.updateFeedbackInfo(feedbackInfo)) {
            return Response.status(Response.Status.OK).build();
        }
        jsonObject.put("error", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{feedbackId}")
    public Response getFeedbackInfo(@PathParam("feedbackId") String feedbackId) {
        FeedbackInfo feedbackInfo = ApiProvider.feedbackInfoService.queryFeedbackInfoById(feedbackId);
        JSONObject json;
        if (feedbackInfo != null) {
            json = (JSONObject) JSON.toJSON(feedbackInfo);
        } else {
            json = new JSONObject();
        }
        return Response.status(Response.Status.OK).entity(json.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }

    @Path("page/{pageIndex}")
    @GET
    public Response queryFeedbackInfo(@PathParam("pageIndex") @DefaultValue("1") final String pageIndexStr,
                                      @QueryParam("pageSize") @DefaultValue("10") final String pageSizeStr,
                                      @QueryParam("feedbackUserName") String feedbackUserName,
                                      @QueryParam("feedbackEmail") String feedbackEmail,
                                      @QueryParam("feedbackPhone") String feedbackPhone) {
        Integer pageNum = Integer.parseInt(pageIndexStr);
        Integer pageSize = Integer.parseInt(pageSizeStr);
        FeedbackInfo query = new FeedbackInfo();
        query.setFeedbackUserName(feedbackUserName);
        query.setFeedbackEmail(feedbackEmail);
        query.setFeedbackPhone(feedbackPhone);
        Page page = new Page(pageNum,pageSize);
        Page<FeedbackInfo> feedbackInfoPage = ApiProvider.feedbackInfoService.queryFeedbackInfoByPage(query,page);
        List<FeedbackInfo> lists = feedbackInfoPage.getResult();
        JSONObject json = new JSONObject();
        if (lists != null) {
            JSONArray jsonArray = new JSONArray();
            for (FeedbackInfo feedbackInfo : lists) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(feedbackInfo);
                jsonArray.add(jsonObject);
            }
            json.put("totalSize", feedbackInfoPage.getTotal());
            json.put("page",feedbackInfoPage.getPageNum());
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
    public Response queryFeedbackList(@QueryParam("feedbackUserName") String feedbackUserName,
                                      @QueryParam("feedbackEmail") String feedbackEmail,
                                      @QueryParam("feedbackPhone") String feedbackPhone) {
        FeedbackInfo query = new FeedbackInfo();
        query.setFeedbackUserName(feedbackUserName);
        query.setFeedbackEmail(feedbackEmail);
        query.setFeedbackPhone(feedbackPhone);
        List<FeedbackInfo> lists = ApiProvider.feedbackInfoService.queryFeedbackInfo(query);
        JSONObject json = new JSONObject();
        if (lists != null) {
            JSONArray jsonArray = new JSONArray();
            for (FeedbackInfo feedbackInfo : lists) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(feedbackInfo);
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
