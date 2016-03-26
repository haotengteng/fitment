package com.zuoan.serviceImpl;

import com.zuoan.dao.ProductInfoDao;
import com.zuoan.module.ProductInfo;
import com.zuoan.service.ProductInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * Created by xujy on 2016/3/26.
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Resource
    private ProductInfoDao productInfoDao;
    @Override
    public boolean addProductInfo(ProductInfo productInfo) {
        if (productInfo == null || StringUtils.isBlank(productInfo.getProductImageUrl())){
            return false;
        }
        return productInfoDao.addProductInfo(productInfo)>0;
    }
    @Override
    public boolean delProductInfo(String productId) {
        if (StringUtils.isBlank(productId)){
            return false;
        }
        if (productInfoDao.selectProductInfoById(productId)==null){
            return false;
        }
        return  productInfoDao.delProductInfo(productId)>0;
    }
    @Override
    public boolean updateProductInfo(ProductInfo productInfo) {
        if (productInfo == null ||StringUtils.isBlank(productInfo.getProductId())){
            return false;
        }
        if (productInfoDao.selectProductInfoById(productInfo.getProductId())==null){
            return false;
        }
       return productInfoDao.updateProductInfo(productInfo)>0;
    }
    @Override
    public List<ProductInfo> queryProductInfo(ProductInfo productInfo) {
        return productInfoDao.selectProductInfo(productInfo);
    }
    @Override
    public ProductInfo queryProductInfoById(String productId) {
        if (StringUtils.isBlank(productId)){
            return null;
        }
        return productInfoDao.selectProductInfoById(productId);
    }
}
