package com.zuoan.module;

/**
 * 定制产品信息
 * Created by xujy on 2016/3/26.
 */
public class ProductInfoDTO extends Base {
    private String productId;
    private String productType;
    private String productTypeName;
    private String productName;
    private String productUrl;
    private String productImageUrl;
    private double productPrice;
    private Integer newProduct; //0:表示不是新款商品，1：表示是新款商品
    private Integer salableProduct; //0:表示不是畅销商品，1：表示是畅销商品
    private Integer specialPriceProduct; //0:表示不是特价商品，1：表示是特价商品
    private String remark;

    public Integer getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(Integer newProduct) {
        this.newProduct = newProduct;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getSalableProduct() {
        return salableProduct;
    }

    public void setSalableProduct(Integer salableProduct) {
        this.salableProduct = salableProduct;
    }

    public Integer getSpecialPriceProduct() {
        return specialPriceProduct;
    }

    public void setSpecialPriceProduct(Integer specialPriceProduct) {
        this.specialPriceProduct = specialPriceProduct;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
