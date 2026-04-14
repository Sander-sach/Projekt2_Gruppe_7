package com.example.projekt2_gruppe7.model;

import java.util.List;

public class WishList {
    private Long id;
    private Long userId;
    private String name;
    private String description;
    private List<Wish> wishes;

    public WishList(Long id, Long userId, String name, String description) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
    }

    public WishList() {
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Wish> getWishes() {
        return wishes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWishes(List<Wish> wishes) {
        this.wishes = wishes;
    }
}
