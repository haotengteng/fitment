package com.zuoan.service;

import com.zuoan.module.ProductInfo;
import com.zuoan.module.ProductInfoDTO;
import com.zuoan.utils.mybatis.Page;

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

    List<ProductInfoDTO> queryProductInfoDTO(ProductInfoDTO productInfoDTO);

    Page<ProductInfo> queryProductInfoByPage(ProductInfo productInfo,Page page);

    Page<ProductInfoDTO> queryProductInfoDTOByPage(ProductInfoDTO productInfoDTO,Page page);

    ProductInfo queryProductInfoById(String productId);

    ProductInfoDTO queryProductInfoDTOById(String productId);
}
