package com.example.projekt2_gruppe7.model;

import java.util.List;

public class WishList {
    private Long id;
    private Long userId;
    private String listName;
    private String description;
    private List<Wish> wishes;

    public WishList(Long userId, String listName, String description, List<Wish> wishes) {
        this.userId = userId;
        this.listName = listName;
        this.description = description;
        this.wishes = wishes;
    }

    public WishList() {
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getListName() {
        return listName;
    }

    public String getDescription() {
        return description;
    }

    public List<Wish> getWishes() {
        return wishes;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWishes(List<Wish> wishes) {
        this.wishes = wishes;
    }
}
