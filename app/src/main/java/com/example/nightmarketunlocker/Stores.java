package com.example.nightmarketunlocker;

public class Stores {
    private Integer storeCategoryId;
    private String storeDesc;
    private Integer storeId;
    private String storeName;
    private String storeNameJPN;
    private Integer storeRate;
    private Integer storeReviewsCount;
    private String storeUrl;

    public Stores() {
    }

    public Stores(Integer storeCategoryId, String storeDesc, Integer storeId, String storeName, String storeNameJPN, Integer storeRate, Integer storeReviewsCoung, String storeUrl) {
        this.storeCategoryId = storeCategoryId;
        this.storeDesc = storeDesc;
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeNameJPN = storeNameJPN;
        this.storeRate = storeRate;
        this.storeReviewsCount = storeReviewsCount;
        this.storeUrl = storeUrl;
    }

    public Integer getStoreCategoryId() {
        return storeCategoryId;
    }

    public void setStoreCategoryId(Integer storeCategoryId) {
        this.storeCategoryId = storeCategoryId;
    }
    public String getStoreDesc() {
        return storeDesc;
    }

    public void setStoreDesc(String storeDesc) {
        this.storeDesc = storeDesc;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreNameJPN() {
        return storeNameJPN;
    }

    public void setStoreNameJPN(String storeNameJPN) {
        this.storeNameJPN = storeNameJPN;
    }

    public Integer getStoreRate() {
        return storeRate;
    }

    public void setStoreRate(Integer storeRate) {
        this.storeRate = storeRate;
    }

    public Integer getStoreReviewsCount() {
        return storeReviewsCount;
    }

    public void setStoreReviewsCount(Integer storeReviewsCoung) {
        this.storeReviewsCount = storeReviewsCoung;
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }
}


