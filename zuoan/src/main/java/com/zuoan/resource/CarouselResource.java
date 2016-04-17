package com.zuoan.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zuoan.ApiProvider.ApiProvider;
import com.zuoan.Utils.UtilTools;
import com.zuoan.module.CarouselInfo;
import com.zuoan.utils.mybatis.Page;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * 
 * Created by Administrator on 2016/4/1.
 */
@Path("carousel")
@Produces(MediaType.APPLICATION_JSON)
public class CarouselResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCarousel(CarouselInfo carouselInfo,
                               @CookieParam("token") String token) {
        carouselInfo.setCarouselId(UtilTools.UUIDUtil());
        JSONObject jsonObject = new JSONObject();
        if (ApiProvider.CAROUSEL_SERVICE.addCarouselInfo(carouselInfo)) {
            jsonObject.put("result", "成功");
            return Response.status(Response.Status.OK).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
        }
        jsonObject.put("result", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("{carouselId}")
    public Response updateCarousel(CarouselInfo carouselInfo, @PathParam("carouselId") String carouselId) {
        JSONObject jsonObject = new JSONObject();
        carouselInfo.setCarouselId(carouselId);
        if (ApiProvider.CAROUSEL_SERVICE.updateCarouselInfo(carouselInfo)) {
            jsonObject.put("result", "成功");
            return Response.status(Response.Status.OK).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
        }
        jsonObject.put("result", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{carouselId}")
    public Response getCarouselById(@PathParam("carouselId") String carouselId) {
        CarouselInfo carouselInfo = ApiProvider.CAROUSEL_SERVICE.queryCarouselInfoById(carouselId);
        String jsonObject = "";
        if (carouselInfo != null) {
            jsonObject = JSON.toJSONString(carouselInfo);
        }
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("{carouselId}")
    public Response delCarousel(@PathParam("carouselId") String carouselId) {
        JSONObject jsonObject = new JSONObject();
        if (ApiProvider.CAROUSEL_SERVICE.deleteCarouselInfo(carouselId)) {
            jsonObject.put("result", "success");
        } else {
            jsonObject.put("result", "fail");
        }
        return Response.status(Response.Status.OK).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("page/{pageIndex}")
    public Response queryCarousel(@PathParam("pageIndex") @DefaultValue("1") final String pageIndexStr,
                                 @QueryParam("pageSize") @DefaultValue("10") final String pageSizeStr,
                                 @QueryParam("carouselId") String carouselId,
                                 @QueryParam("carouselTitle") String carouselTitle,
                                 @QueryParam("carouselLocation") String carouselLocation){
        Integer pageNum = Integer.parseInt(pageIndexStr);
        Integer pageSize = Integer.parseInt(pageSizeStr);
        CarouselInfo query = new CarouselInfo();
        query.setCarouselId(carouselId);
        query.setCarouselTitle(carouselTitle);
        query.setCarouselLocation(carouselLocation);
        JSONArray array = new JSONArray();
        Page page = new Page(pageNum, pageSize);
        Page<CarouselInfo> carouselInfoPage = ApiProvider.CAROUSEL_SERVICE.queryCarouselInfoByPage(query,page);
        List<CarouselInfo> lists = carouselInfoPage.getResult();
        JSONObject json = new JSONObject();
        if (lists != null){
            for (CarouselInfo carouselInfo :lists){
                array.add(JSON.toJSON(carouselInfo));
            }
            json.put("totalSize", carouselInfoPage.getTotal());
            json.put("page",carouselInfoPage.getPageNum());
            json.put("info", array);
        }else {
            json.put("totalSize", "0");
            json.put("page","0");
            json.put("info", new JSONArray());
        }
        return Response.status(Response.Status.OK).entity(json.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("list")
    public Response queryCarouselList(@QueryParam("carouselTitle") String carouselTitle,
                                  @QueryParam("carouselLocation") String carouselLocation){
        CarouselInfo query = new CarouselInfo();
        query.setCarouselTitle(carouselTitle);
        query.setCarouselLocation(carouselLocation);
        List<CarouselInfo> lists = ApiProvider.CAROUSEL_SERVICE.queryCarouselInfo(query);
        JSONObject json = new JSONObject();

        if (lists != null) {
            JSONArray jsonArray = new JSONArray();
            for (CarouselInfo carouselInfo : lists) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(carouselInfo);
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
