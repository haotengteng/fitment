package com.zuoan.module;

/**
 * Created by htt on 2016/3/30.
 */
public class CarouselInfo extends Base{
    private static final long serialVersionUID = 188027372937961735L;
    private String carouselId;
    private String carouselTitle;
    private String carouselImageUrl;
    private String carouselLocation;
    private String remark;

    public String getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(String carouselId) {
        this.carouselId = carouselId;
    }

    public String getCarouselTitle() {
        return carouselTitle;
    }

    public void setCarouselTitle(String carouselTitle) {
        this.carouselTitle = carouselTitle;
    }

    public String getCarouselImageUrl() {
        return carouselImageUrl;
    }

    public void setCarouselImageUrl(String carouselImageUrl) {
        this.carouselImageUrl = carouselImageUrl;
    }

    public String getCarouselLocation() {
        return carouselLocation;
    }

    public void setCarouselLocation(String carouselLocation) {
        this.carouselLocation = carouselLocation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
