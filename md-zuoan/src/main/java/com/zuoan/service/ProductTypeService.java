package com.zuoan.service;

import com.zuoan.module.ProductType;

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

    ProductType queryProductTypeById(String typeId);

    ProductType queryProductTypeByTypeCode(String typeCode);
}
