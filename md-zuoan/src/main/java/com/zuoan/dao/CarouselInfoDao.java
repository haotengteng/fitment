package com.zuoan.dao;

import com.zuoan.module.CarouselInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/4/1.
 */
public interface CarouselInfoDao {
    int addCarouselInfo(CarouselInfo carouselInfo);

    int updateCarouselInfo(CarouselInfo carouselInfo);

    int deleteCarouselInfo(String articleId);

    CarouselInfo selectCarouselInfoById(String articleId);

    List<CarouselInfo> selectCarouselInfo(CarouselInfo carouselInfo);
}
