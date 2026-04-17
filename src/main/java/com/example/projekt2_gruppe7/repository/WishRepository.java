package com.example.projekt2_gruppe7.repository;

import com.example.projekt2_gruppe7.model.Wish;
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
public class WishRepository {
    @Autowired
    private DataSource dataSource;

    public void createWish(Wish wish){
        String sql = "INSERT INTO wish (wishlist_id, item_name, description, price, item_url) VALUES(?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, wish.getWishListId());
            statement.setString(2, wish.getItemName());
            statement.setString(3, wish.getDescription());
            statement.setDouble(4, wish.getPrice());
            statement.setString(5, wish.getItemURL());
            statement.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Wish> findWishesByListId(Long wishlist_id){
        List<Wish> wishes = new ArrayList<>();

        String sql = "SELECT * FROM wish WHERE wishlist_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, wishlist_id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    Long id = (resultSet.getLong("id"));
                    Long wishListId = (resultSet.getLong("wishlist_id"));
                    String itemName = (resultSet.getString("item_name"));
                    String description = (resultSet.getString("description"));
                    Double price = (resultSet.getDouble("price"));
                    String itemurl = (resultSet.getString("item_url"));

                    Wish wish = new Wish(id, wishListId, itemName, description, price, itemurl);
                    wishes.add(wish);

                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return wishes;
    }
    public Wish findWishById(Long id){
        Wish wish = null;
        String sql = "SELECT * FROM wish WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    wish = new Wish();

                    Long wishListId = (resultSet.getLong("wishlist_id"));
                    String itemName = (resultSet.getString("item_name"));
                    String description = (resultSet.getString("description"));
                    Double price = (resultSet.getDouble("price"));
                    String itemurl = (resultSet.getString("item_url"));

                    wish = new Wish(id, wishListId, itemName, description, price, itemurl);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return wish;
    }

    //NYT TIL REDIGERING AF ØNSKE
    public void updateWishName(Long wishId, String itemName) {
        String sql = "UPDATE wish SET item_name = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, itemName);
            statement.setLong(2, wishId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
