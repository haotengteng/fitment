package com.zuoan.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zuoan.ApiProvider.ApiProvider;
import com.zuoan.Utils.UtilTools;
import com.zuoan.module.ProductInfo;
import com.zuoan.module.ProductInfoDTO;
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
@Path("product/info")
@Produces(MediaType.APPLICATION_JSON)
public class ProductInfoResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postProductInfo(ProductInfo productInfo) {
        JSONObject jsonObject = new JSONObject();
        productInfo.setProductId(UtilTools.UUIDUtil());
        if (ApiProvider.productInfoService.addProductInfo(productInfo)) {
            jsonObject.put("productId", productInfo.getProductId());
            return Response.status(Response.Status.OK).entity(jsonObject.toJSONString()).type(MediaType.APPLICATION_JSON).build();
        }
        jsonObject.put("error", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }

    @Path("{productId}")
    @DELETE
    public Response delProductInfo(@NotBlank(message = "productId 不能为空") @PathParam("productId") final String productId) {
        JSONObject jsonObject = new JSONObject();
        if (ApiProvider.productInfoService.delProductInfo(productId)) {
            return Response.status(Response.Status.OK).build();
        }
        jsonObject.put("error", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }

    @Path("{productId}")
    @PUT
    public Response putProductInfo(ProductInfo productInfo, @PathParam("productId") final String productId) {
        JSONObject jsonObject = new JSONObject();
        productInfo.setProductId(productId);
        if (ApiProvider.productInfoService.updateProductInfo(productInfo)) {
            return Response.status(Response.Status.OK).build();
        }
        jsonObject.put("error", "失败");
        return Response.status(Response.Status.OK).entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{productId}")
    public Response getProductInfo(@PathParam("productId") String productId) {
        ProductInfoDTO productInfo = ApiProvider.productInfoService.queryProductInfoDTOById(productId);
        JSONObject json;
        if (productInfo != null) {
            json = (JSONObject) JSON.toJSON(productInfo);
        } else {
            json = new JSONObject();
        }
        return Response.status(Response.Status.OK).entity(json.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }

    @Path("page/{pageIndex}")
    @GET
    public Response queryProductInfo(@PathParam("pageIndex") @DefaultValue("1") final String pageIndexStr,
                                     @QueryParam("pageSize") @DefaultValue("10") final String pageSizeStr,
                                     @QueryParam("productType") String productType,
                                     @QueryParam("productName") String productName,
                                     @QueryParam("newProduct") Integer newProduct,
                                     @QueryParam("salableProduct") Integer salableProduct,
                                     @QueryParam("specialPriceProduct") Integer specialPriceProduct) {
        Integer pageNum = Integer.parseInt(pageIndexStr);
        Integer pageSize = Integer.parseInt(pageSizeStr);
        ProductInfoDTO query = new ProductInfoDTO();
        query.setProductType(productType);
        query.setProductName(productName);
        query.setNewProduct(newProduct);
        query.setSalableProduct(salableProduct);
        query.setSpecialPriceProduct(specialPriceProduct);
        Page page = new Page(pageNum,pageSize);
        Page<ProductInfoDTO> productInfoPage = ApiProvider.productInfoService.queryProductInfoDTOByPage(query,page);
        List<ProductInfoDTO> lists = productInfoPage.getResult();
        JSONObject json = new JSONObject();
        if (lists != null) {
            JSONArray jsonArray = new JSONArray();
            for (ProductInfoDTO productInfo : lists) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(productInfo);
                jsonArray.add(jsonObject);
            }
            json.put("totalSize", productInfoPage.getTotal());
            json.put("page",productInfoPage.getPageNum());
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
    public Response queryProductInfoList(@QueryParam("productType") String productType,
                                         @QueryParam("productName") String productName,
                                         @QueryParam("newProduct") Integer newProduct,
                                         @QueryParam("salableProduct") Integer salableProduct,
                                         @QueryParam("specialPriceProduct") Integer specialPriceProduct,
                                         @QueryParam("flag") String flag) {
        ProductInfo query = new ProductInfo();
        query.setProductType(productType);
        query.setProductName(productName);
        query.setNewProduct(newProduct);
        query.setSalableProduct(salableProduct);
        query.setSpecialPriceProduct(specialPriceProduct);
        List<ProductInfo> lists = ApiProvider.productInfoService.queryProductInfo(query);
        JSONObject json = new JSONObject();
        if (lists != null) {
            JSONArray jsonArray = new JSONArray();
            for (ProductInfo productInfo : lists) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(productInfo);
                jsonArray.add(jsonObject);
            }
            json.put("totalSize", lists.size());
            json.put("list", jsonArray);
            json.put("flag", flag);
        } else {
            json.put("totalSize", 0);
            json.put("list", new JSONArray());
            json.put("flag", flag);
        }
        return Response.status(Response.Status.OK).entity(json.toJSONString()).type(MediaType.APPLICATION_JSON).build();
    }
}
