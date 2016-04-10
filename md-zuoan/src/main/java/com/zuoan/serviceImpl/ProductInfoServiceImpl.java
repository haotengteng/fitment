package com.zuoan.serviceImpl;

import com.zuoan.dao.ProductInfoDao;
import com.zuoan.module.ProductInfo;
import com.zuoan.module.ProductInfoDTO;
import com.zuoan.service.ProductInfoService;
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
    public List<ProductInfoDTO> queryProductInfoDTO(ProductInfoDTO productInfo) {
        return productInfoDao.selectProductInfoDTO(productInfo);
    }

    @Override
    public Page<ProductInfo> queryProductInfoByPage(ProductInfo productInfo, Page page) {
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        productInfoDao.selectProductInfo(productInfo);
        return PageHelper.endPage();
    }

    @Override
    public Page<ProductInfoDTO> queryProductInfoDTOByPage(ProductInfoDTO productInfo, Page page) {
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        productInfoDao.selectProductInfoDTO(productInfo);
        return PageHelper.endPage();
    }

    @Override
    public ProductInfo queryProductInfoById(String productId) {
        if (StringUtils.isBlank(productId)){
            return null;
        }
        return productInfoDao.selectProductInfoById(productId);
    }

    @Override
    public ProductInfoDTO queryProductInfoDTOById(String productId) {
        if (StringUtils.isBlank(productId)){
            return null;
        }
        return productInfoDao.selectProductInfoDTOById(productId);
    }
}
