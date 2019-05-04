package com.example.nightmarketunlocker;

public class Food {

    public String foodname, foodimage, fooddesc;

    public Food(){

    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFoodimage() {
        return foodimage;
    }

    public void setFoodimage(String foodimage) {
        this.foodimage = foodimage;
    }

    public String getFooddesc() {
        return fooddesc;
    }

    public void setFooddesc(String fooddesc) {
        this.fooddesc = fooddesc;
    }

    public Food(String foodname, String foodimage, String fooddesc) {
        this.foodname = foodname;
        this.foodimage = foodimage;
        this.fooddesc = fooddesc;
    }
}
