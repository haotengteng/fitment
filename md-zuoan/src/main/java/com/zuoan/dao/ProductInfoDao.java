package com.zuoan.dao;

import com.zuoan.module.ProductInfo;

import java.util.List;

/**
 *
 * Created by xujy on 2016/3/26.
 */
public interface ProductInfoDao {
    int addProductInfo(ProductInfo productInfo);

    int delProductInfo(String productId);

    int updateProductInfo(ProductInfo productInfo);

    List<ProductInfo> selectProductInfo(ProductInfo productInfo);

    ProductInfo selectProductInfoById(String productId);
}
