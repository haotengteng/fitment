package com.zuoan.dao;

import com.zuoan.module.ProductInfo;
import com.zuoan.module.ProductInfoDTO;

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

    List<ProductInfoDTO> selectProductInfoDTO(ProductInfoDTO productInfo);

    ProductInfo selectProductInfoById(String productId);

    ProductInfoDTO selectProductInfoDTOById(String productId);
}
