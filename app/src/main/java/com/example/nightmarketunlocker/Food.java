package com.example.nightmarketunlocker;

public class Food {
    String foodDesc;
    String foodImage;
    String foodName;
    long foodPrice;

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

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public long getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(long foodPrice) {
        this.foodPrice = foodPrice;
    }

    public long getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(long foodRate) {
        this.foodRate = foodRate;
    }

    public long getFoodStore() {
        return foodStore;
    }

    public void setFoodStore(long foodStore) {
        this.foodStore = foodStore;
    }

    long foodRate;
    long foodStore;
}