package com.zuoan.service;

import com.zuoan.module.CarouselInfo;
import com.zuoan.utils.mybatis.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/4/1.
 */
public interface CarouselService {
    boolean addCarouselInfo(CarouselInfo carouselInfo);

    boolean updateCarouselInfo(CarouselInfo carouselInfo);

    boolean deleteCarouselInfo(String articleId);

    CarouselInfo queryCarouselInfoById(String articleId);

    List<CarouselInfo> queryCarouselInfo(CarouselInfo carouselInfo);

    Page<CarouselInfo> queryCarouselInfoByPage(CarouselInfo carouselInfo, Page page);

}
