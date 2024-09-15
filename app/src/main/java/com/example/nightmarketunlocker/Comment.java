package com.example.nightmarketunlocker;

public class Comment {

    private String reviewContent;
    private String reviewDate;
    private Long reviewRate;
    private long reviewStore;
    private String reviewStoreName;
    private String userEmail;
    private String userImage;
    private String userName;

    public Comment(){

    }


    public Comment(String reviewContent, String reviewDate, long reviewRate, long reviewStore, String reviewStoreName, String userEmail, String userImage, String userName) {
        this.reviewContent = reviewContent;
        this.reviewDate = reviewDate;
        this.reviewRate = reviewRate;
        this.reviewStore = reviewStore;
        this.reviewStoreName = reviewStoreName;
        this.userEmail = userEmail;
        this.userImage = userImage;
        this.userName = userName;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public long getReviewRate() {
        return reviewRate;
    }

    public void setReviewRate(long reviewRate) {
        this.reviewRate = reviewRate;
    }

    public long getReviewStore() {
        return reviewStore;
    }

    public void setReviewStore(long reviewStore) {
        this.reviewStore = reviewStore;
    }

    public String getReviewStoreName() {
        return reviewStoreName;
    }

    public void setReviewStoreName(String reviewStoreName) {
        this.reviewStoreName = reviewStoreName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
