package com.example.projekt2_gruppe7.service;

import com.example.projekt2_gruppe7.model.Wish;
import com.example.projekt2_gruppe7.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {

    private final WishRepository wishRepository;

    @Autowired
    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public void createWish(Wish wish) {
        wishRepository.createWish(wish);
    }

    public List<Wish> getWishesByListId(Long wishListId) {
        return wishRepository.findWishesByListId(wishListId);
    }

    public Wish getWishById(Long id) {
        return wishRepository.findWishById(id);
    }
    //NYT TIL REDIGERING AF ØNSKE
    public void updateWishName(Long wishId, String itemName) {
        wishRepository.updateWishName(wishId, itemName);
    }

    //nyt til reserveWish
    public boolean reserveWish(Long wishId) {

        Wish wish = wishRepository.findWishById(wishId);

        // hvis ønsket ikke findes
        if (wish == null) {
            return false;
        }

        // hvis allerede reserveret (forhindrer dobbeltreservation)
        if (wish.isReserved()) {
            return false;
        }

        // reserver ønsket
        wish.setReserved(true);

        // gem i databasen (brug jeres metode)
        wishRepository.createWish(wish); // eller updateWish hvis I har den

        return true;
    }
    // Slet ønske
    public void deleteWish(long wishId){ wishRepository.deleteWishById(wishId);}
}
