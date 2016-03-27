package com.zuoan.service;

import com.zuoan.module.ProductType;
import com.zuoan.utils.mybatis.Page;

import java.util.List;

/**
 *
 * Created by xujy on 2016/3/26.
 */
public interface ProductTypeService {
    boolean addProductType(ProductType productType);

    boolean delProductType(String typeId);

    boolean delProductTypeByTypeCode(String typeCode);

    boolean updateProductType(ProductType productType);

    List<ProductType> queryProductType(ProductType productType);

    Page<ProductType> queryProductTypeByPage(ProductType productType, Page page);

    ProductType queryProductTypeById(String typeId);

    ProductType queryProductTypeByTypeCode(String typeCode);
}
