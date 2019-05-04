package com.example.nightmarketunlocker;

public class Categories {
    private Integer categoryId;
    private String categoryName;
    private String categoryNameJPN;

    public Categories() {
    }

    public Categories(Integer categoryId, String categoryName, String categoryNameJPN) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryNameJPN = categoryNameJPN;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryNameJPN() {
        return categoryNameJPN;
    }

    public void setCategoryNameJPN(String categoryNameJPN) {
        this.categoryNameJPN = categoryNameJPN;
    }
}

