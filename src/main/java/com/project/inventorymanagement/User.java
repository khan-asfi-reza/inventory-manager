package com.project.inventorymanagement;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User extends Model{

    private int id;
    private String username;
    private String password;

    ArrayList<User> users = new ArrayList<>();

    Repository<User> userRepository;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = Crypto.encrypt(password);
        userRepository = new Repository<>(User.class);
        ArrayList<User> users = userRepository.getAll();
    }

    @Override
    public int getPrimaryKey() {
        return id;
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = Crypto.encrypt(password);
        return this;
    }

    public boolean authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                String encryptedPassword = Crypto.encrypt(password);
                return this.password.equals(encryptedPassword);
            }
        }
        return false;
    }

    public User createUser(String username, String password) {
        User user = new User(users.size(), username, password);
        user.save();
        users.add(user);
        return user;
    }



}
