package com.example.projekt2_gruppe7.service;

import com.example.projekt2_gruppe7.model.WishList;
import com.example.projekt2_gruppe7.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;

    @Autowired
    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public void createWishList(WishList wishList) {
        wishListRepository.createWishList(wishList);
    }

    public List<WishList> getWishListsByUser(Long userId) {
        return wishListRepository.findWishListsByUser(userId);
    }

    public WishList getWishListById(Long id) {
        return wishListRepository.findWishListById(id);
    }
}