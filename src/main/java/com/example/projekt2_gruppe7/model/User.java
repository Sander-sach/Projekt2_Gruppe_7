package com.example.projekt2_gruppe7.model;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;


    // skal opdateres med List<WishList> wishLists
    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    // Tom Constructer til SQL
    public User() {
    }
    // til oprettelse
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
