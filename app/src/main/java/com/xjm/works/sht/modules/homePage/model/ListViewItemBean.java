package com.xjm.works.sht.modules.homePage.model;

import com.wolearn.mvpframelib.frame.MvpModel;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class ListViewItemBean extends MvpModel {

    private String id;
    private boolean isNewRecord;
    private String createDate;
    private String updateDate;
    private int userId;
    private String name;
    private String description;
    private int categoryId;
    private String category;
    private int amount;
    private double lowestPrice;
    private double fixedPrice;
    private double closingCost;
    private double nowPrice;
    private double upPrice;
    private String photo;
    private long startTime;
    private long endTime;
    private int status;
    private int auctionStatus;
    private int pages;

    public void setId(String id) {
        this.id = id;
    }

    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setLowestPrice(double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public void setFixedPrice(double fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public void setClosingCost(double closingCost) {
        this.closingCost = closingCost;
    }

    public void setNowPrice(double nowPrice) {
        this.nowPrice = nowPrice;
    }

    public void setUpPrice(double upPrice) {
        this.upPrice = upPrice;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setAuctionStatus(int auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getId() {
        return id;
    }

    public boolean getIsNewRecord() {
        return isNewRecord;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public double getFixedPrice() {
        return fixedPrice;
    }

    public double getClosingCost() {
        return closingCost;
    }

    public double getNowPrice() {
        return nowPrice;
    }

    public double getUpPrice() {
        return upPrice;
    }

    public String getPhoto() {
        return photo;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public int getStatus() {
        return status;
    }

    public int getAuctionStatus() {
        return auctionStatus;
    }

    public int getPages() {
        return pages;
    }
}
