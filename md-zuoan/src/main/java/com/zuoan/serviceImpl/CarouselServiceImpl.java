package com.zuoan.serviceImpl;

import com.zuoan.dao.CarouselInfoDao;
import com.zuoan.module.CarouselInfo;
import com.zuoan.service.CarouselService;
import com.zuoan.utils.mybatis.Page;
import com.zuoan.utils.mybatis.PageHelper;
import com.zuoan.utils.redis.RedisCacheable;
import com.zuoan.utils.redis.RedisCacheableForDelete;
import com.zuoan.utils.redis.RedisCacheableForUpdate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * Created by Administrator on 2016/4/1.
 */
@Service
public class CarouselServiceImpl implements CarouselService {
    @Resource
    CarouselInfoDao carouselInfoDao;
    @Override
    public boolean addCarouselInfo(CarouselInfo carouselInfo) {
        if (carouselInfo ==null|| StringUtils.isBlank(carouselInfo.getCarouselId())){
            return false;
        }
        return carouselInfoDao.addCarouselInfo(carouselInfo) > 0;
    }

    @Override
    @RedisCacheableForUpdate
    public boolean updateCarouselInfo(CarouselInfo carouselInfo) {
        if (carouselInfo ==null|| StringUtils.isBlank(carouselInfo.getCarouselId())){
            return false;
        }
        if (carouselInfoDao.selectCarouselInfoById(carouselInfo.getCarouselId()) == null){
            return false;
        }
        return carouselInfoDao.updateCarouselInfo(carouselInfo)>0;
    }

    @Override
    @RedisCacheableForDelete
    public boolean deleteCarouselInfo(String carouselId) {
        if ( StringUtils.isBlank(carouselId)){
            return false;
        }
        if (carouselInfoDao.selectCarouselInfoById(carouselId) == null){
            return false;
        }
        return carouselInfoDao.deleteCarouselInfo(carouselId) >0;
    }

    @Override
    @RedisCacheable
    public CarouselInfo queryCarouselInfoById(String carouselId) {
        if ( StringUtils.isBlank(carouselId)){
            return null;
        }
        return carouselInfoDao.selectCarouselInfoById(carouselId);
    }

    @Override
    public List<CarouselInfo> queryCarouselInfo(CarouselInfo carouselInfo) {
        return carouselInfoDao.selectCarouselInfo(carouselInfo);
    }

    @Override
    public Page<CarouselInfo> queryCarouselInfoByPage(CarouselInfo carouselInfo, Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        carouselInfoDao.selectCarouselInfo(carouselInfo);
        return PageHelper.endPage();
    }
}
