package com.example.projekt2_gruppe7.repository;

import com.example.projekt2_gruppe7.model.Wish;
import com.example.projekt2_gruppe7.model.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishListRepository {

    @Autowired
    private DataSource dataSource;

    public void createWishList(WishList wishList){
        String sql = "INSERT INTO wishlist (id, user_id, name, description) VALUES(?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, wishList.getId());
            statement.setLong(2, wishList.getUserId());
            statement.setString(3, wishList.getName());
            statement.setString(4, wishList.getDescription());
            statement.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<WishList> findWishListsByUser(Long userId){
            List<WishList> wishLists = new ArrayList<>();

            String sql = "SELECT * FROM wishlists WHERE user_id = ?";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setLong(1, userId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {

                        Long id = (resultSet.getLong("id"));
                        String name = (resultSet.getString("name"));
                        String description = (resultSet.getString("description"));
                        WishList wishList = new WishList(id, userId, name, description);
                        wishLists.add(wishList);

                    }
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return wishLists;
    }

    public WishList findWishListById(Long id){
        WishList wishList = null;
        String sql = "SELECT * FROM wishlist WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    wishList = new WishList();

                    Long userId = (resultSet.getLong("user_id"));
                    String name = (resultSet.getString("name"));
                    String description = (resultSet.getString("description"));

                    wishList = new WishList(id, userId, name, description);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return wishList;
    }


}
