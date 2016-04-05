package com.zuoan.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zuoan.ApiProvider.ApiProvider;
import com.zuoan.Utils.UtilTools;
import com.zuoan.module.ArticleInfo;
import com.zuoan.utils.mybatis.Page;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Administrator on 2016/4/1.
 */
@Path("article/info")
@Produces(MediaType.APPLICATION_JSON)
public class ArticleResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addArticle(ArticleInfo articleInfo,
                               @CookieParam("token") String token) {
        articleInfo.setArticleId(UtilTools.UUIDUtil());
        articleInfo.setUploader(token);
        JSONObject jsonObject = new JSONObject();
        if (ApiProvider.articleService.AddArticleInfo(articleInfo)) {
            jsonObject.put("result", "成功");
            return Response.status(Response.Status.OK).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
        }
        jsonObject.put("result", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    public Response updateArticle(ArticleInfo articleInfo) {
        JSONObject jsonObject = new JSONObject();
        if (ApiProvider.articleService.updateArticleInfo(articleInfo)) {
            jsonObject.put("result", "成功");
            return Response.status(Response.Status.OK).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
        }
        jsonObject.put("result", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{articleId}")
    public Response getArticleById(@PathParam("articleId") String articleId) {
        ArticleInfo articleInfo = ApiProvider.articleService.queryArticleInfoById(articleId);
        String jsonObject = "";
        if (articleInfo != null) {
            jsonObject = JSON.toJSONString(articleInfo);
        }
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("{articleId}")
    public Response delArticle(@PathParam("articleId") String articleId) {
        JSONObject jsonObject = new JSONObject();
        if (ApiProvider.articleService.deleteArticleInfo(articleId)) {
            jsonObject.put("result", "success");
        } else {
            jsonObject.put("result", "fail");
        }
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("page/{pageIndex}")
    public Response queryArticle(@PathParam("pageIndex") @DefaultValue("1") final String pageIndexStr,
                                 @QueryParam("pageSize") @DefaultValue("10") final String pageSizeStr,
                                 @QueryParam("articleId") String articleId,
                                 @QueryParam("articleTitle") String articleTitle,
                                 @QueryParam("articleState") String articleState){
        Integer pageNum = Integer.parseInt(pageIndexStr);
        Integer pageSize = Integer.parseInt(pageSizeStr);
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setArticleId(articleId);
        articleInfo.setArticleTitle(articleTitle);
        articleInfo.setArticleState(articleState);
        JSONArray array = new JSONArray();
        Page page = new Page(pageNum, pageSize);
        Page<ArticleInfo> articleInfoPage = ApiProvider.articleService.queryArticleInfoByPage(articleInfo,page);
        List<ArticleInfo> articleInfos = articleInfoPage.getResult();
        JSONObject json = new JSONObject();
        if (articleInfoPage != null){
            for (ArticleInfo articleInfo1 :articleInfos){
                array.add(JSON.toJSON(articleInfo1));
            }
            json.put("totalSize", articleInfoPage.getTotal());
            json.put("page",articleInfoPage.getPageNum());
            json.put("info", array);
        }else {
            json.put("totalSize", "0");
            json.put("page","0");
            json.put("info", new JSONArray());
        }
        return Response.status(Response.Status.OK).entity(json).type(MediaType.APPLICATION_JSON).build();
    }
}
