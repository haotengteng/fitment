package com.zuoan.dao;

import com.zuoan.module.ProductType;

import java.util.List;

/**
 *
 * Created by xujy on 2016/3/26.
 */
public interface ProductTypeDao {
    int addProductType(ProductType productType);

    int delProductType(String typeId);

    int delProductTypeByTypeCode(String typeCode);

    int updateProductType(ProductType productType);

    List<ProductType> selectProductType(ProductType productType);

    ProductType selectProductTypeById(String typeId);

    ProductType selectProductTypeByTypeCode(String typeCode);
}
