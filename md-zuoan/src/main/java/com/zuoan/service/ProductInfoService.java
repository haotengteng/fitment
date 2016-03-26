package com.zuoan.service;

import com.zuoan.module.ProductInfo;

import java.util.List;

/**
 *
 * Created by xujy on 2016/3/26.
 */
public interface ProductInfoService {
    boolean addProductInfo(ProductInfo productInfo);

    boolean delProductInfo(String productId);

    boolean updateProductInfo(ProductInfo productInfo);

    List<ProductInfo> queryProductInfo(ProductInfo productInfo);

    ProductInfo queryProductInfoById(String productId);
}
