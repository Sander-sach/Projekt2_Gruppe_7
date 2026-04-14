package com.example.projekt2_gruppe7.repository;

import com.example.projekt2_gruppe7.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.*;
import javax.sql.DataSource;

@Repository
public class UserRepository {
    @Autowired
     private DataSource dataSource;

    public void createUser(User user){
        String sql = "INSERT INTO user (name, email, password) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findUserByEmailAndPassword(String email, String password){
        User user = null;
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    Long id = (resultSet.getLong("id"));
                    String name = (resultSet.getString("name"));

                    user = new User(id, name, email, password);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User findByUserId(Long id){
        User user = null;
        String sql = "SELECT * FROM user WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {


                   String name = (resultSet.getString("name"));
                   String email = (resultSet.getString("email"));
                   String password = (resultSet.getString("password"));
                    user = new User(id, name, email, password);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User findUserByEmail(String email) {
        User user = null;
        String sql = "SELECT email FROM user WHERE email = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    Long id = (resultSet.getLong("id"));
                    String name = (resultSet.getString("name"));
                    String password = (resultSet.getString("password"));

                    user = new User(id, name, email, password);
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
