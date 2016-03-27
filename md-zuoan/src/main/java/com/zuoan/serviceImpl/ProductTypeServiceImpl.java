package com.zuoan.serviceImpl;

import com.zuoan.dao.ProductTypeDao;
import com.zuoan.module.ProductType;
import com.zuoan.service.ProductTypeService;
import com.zuoan.utils.mybatis.Page;
import com.zuoan.utils.mybatis.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * Created by xujy on 2016/3/26.
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Resource
    private ProductTypeDao productTypeDao;
    @Override
    public boolean addProductType(ProductType productType) {
        if (productType == null || StringUtils.isBlank(productType.getTypeCode())){
            return false;
        }
        return productTypeDao.addProductType(productType)>0;
    }
    @Override
    public boolean delProductType(String typeId) {
        if (StringUtils.isBlank(typeId)){
            return false;
        }
        if (productTypeDao.selectProductTypeById(typeId)==null){
            return false;
        }
        return  productTypeDao.delProductType(typeId)>0;
    }
    @Override
    public boolean delProductTypeByTypeCode(String typeCode) {
        if (StringUtils.isBlank(typeCode)){
            return false;
        }
        if (productTypeDao.selectProductTypeByTypeCode(typeCode)==null){
            return false;
        }
        return  productTypeDao.delProductTypeByTypeCode(typeCode)>0;
    }
    @Override
    public boolean updateProductType(ProductType productType) {
        if (productType == null ||StringUtils.isBlank(productType.getTypeCode())){
            return false;
        }
        if (productTypeDao.selectProductTypeById(productType.getTypeId())==null){
            return false;
        }
       return productTypeDao.updateProductType(productType)>0;
    }
    @Override
    public List<ProductType> queryProductType(ProductType productType) {
        return productTypeDao.selectProductType(productType);
    }

    @Override
    public Page<ProductType> queryProductTypeByPage(ProductType productType, Page page) {
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        productTypeDao.selectProductType(productType);
        return PageHelper.endPage();

    }

    @Override
    public ProductType queryProductTypeById(String typeId) {
        if (StringUtils.isBlank(typeId)){
            return null;
        }
        return productTypeDao.selectProductTypeById(typeId);
    }
    @Override
    public ProductType queryProductTypeByTypeCode(String typeCode) {
        if (StringUtils.isBlank(typeCode)){
            return null;
        }
        return productTypeDao.selectProductTypeByTypeCode(typeCode);
    }
}
