package com.example.projekt2_gruppe7.service;

import com.example.projekt2_gruppe7.repository.WishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WishServiceTest {

    private WishService wishService;
    private WishRepository wishRepository;

    @BeforeEach
    void setUp(){
        wishRepository = Mockito.mock( WishRepository.class );
        wishService = new WishService( wishRepository );
    }
    @Test
    void deleteWish_ekisterendeId_sletterØnsket(){
        long wishId = 1L;
        wishService.deleteWish( wishId );
        verify(wishRepository, times(1)).deleteWishById(wishId);    

    }
    @Test
    void deleteWish_kaldesIkkeUdenId(){
        Long wishId = 99L;
        wishService.deleteWish( wishId );
        verify(wishRepository, never()).deleteWishById(0L);
    }

}