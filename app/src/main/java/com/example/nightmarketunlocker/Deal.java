package com.example.nightmarketunlocker;

public class Deal {
    private String foodName;
    private String foodDesc;
    private String foodImage;
    private String foodRate;

    public Deal() {
    }

    public Deal(String foodName, String foodDesc, String foodImage, String foodRate) {
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.foodImage = foodImage;
        this.foodRate = foodRate;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(String foodRate) {
        this.foodRate = foodRate;
    }


}
