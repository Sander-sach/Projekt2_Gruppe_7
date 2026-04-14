package com.example.projekt2_gruppe7.model;

public class Wish {
    private Long id;
    private Long wishListId;
    private String itemName;
    private String description;
    private Double price;
    private String itemURL;

    public Wish(Long id,Long wishListId, String itemName, String description, Double price, String itemURL) {
        this.id = id;
        this.wishListId = wishListId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.itemURL = itemURL;
    }

    public Wish() {
    }

    public Long getId() {
        return id;
    }

    public Long getWishListId() {
        return wishListId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getItemURL() {
        return itemURL;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }
}
