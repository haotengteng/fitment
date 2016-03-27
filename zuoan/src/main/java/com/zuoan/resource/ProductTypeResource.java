package com.zuoan.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zuoan.ApiProvider.ApiProvider;
import com.zuoan.module.ProductType;
import org.hibernate.validator.constraints.NotBlank;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 *
 * Created by xujy on 2016/3/26.
 */
@Path("product/type")
@Produces(MediaType.APPLICATION_JSON)
public class ProductTypeResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postProductType(ProductType productType) {
        JSONObject jsonObject = new JSONObject();
        productType.setTypeId(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
        if (ApiProvider.productTypeService.addProductType(productType)) {
            jsonObject.put("typeId", productType.getTypeId());
            return Response.status(Response.Status.OK).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
        }
        jsonObject.put("error", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }
    @DELETE
    public Response delProductType(@NotBlank(message = "typeId 不能为空") String typeId) {
        JSONObject jsonObject = new JSONObject();
        if (ApiProvider.productTypeService.delProductType(typeId)) {
            return Response.status(Response.Status.OK).build();
        }
        jsonObject.put("error", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }
    @Path("{typeId}")
    @PUT
    public Response putProductType(ProductType productType, @PathParam("typeId") final String typeId) {
        JSONObject jsonObject = new JSONObject();
        productType.setTypeId(typeId);
        if (ApiProvider.productTypeService.updateProductType(productType)) {
            return Response.status(Response.Status.OK).build();
        }
        jsonObject.put("error", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }
    @GET
    @Path("{typeId}")
    public Response getProductType(@PathParam("typeId") String typeId) {
        ProductType productType = ApiProvider.productTypeService.queryProductTypeById(typeId);
        JSONObject json;
        if (productType != null) {
            json = (JSONObject) JSON.toJSON(productType);
        } else {
            json = new JSONObject();
        }
        return Response.status(Response.Status.OK).entity(json.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }
    @Path("page/{pageIndex}")
    @GET
    public Response queryProductType(@PathParam("pageIndex") final String pageIndex,
                                  @QueryParam("pageSize") final String pageSize,
                                  @QueryParam("typeCode") String typeCode,
                                  @QueryParam("typeName") String typeName) {
        ProductType query = new ProductType();
        query.setTypeCode(typeCode);
        query.setTypeName(typeName);

        List<ProductType> lists = ApiProvider.productTypeService.queryProductType(query);

        JSONObject json = new JSONObject();
        if (lists != null) {
            JSONArray jsonArray = new JSONArray();
            for (ProductType productType : lists) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(productType);
                jsonArray.add(jsonObject);
            }
            json.put("totalSize", jsonArray.size());
            json.put("info", jsonArray);
        } else {
            json.put("totalSize", 0);
            json.put("info", new JSONArray());
        }
        return Response.status(Response.Status.OK).entity(json.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }
}
