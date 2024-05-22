package com.project.inventorymanagement;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;


public class User extends Model<User>{


    private int id;
    private String username;
    private String password;

    public static final Repository<User> repository = new Repository<>(User.class);


    public User(){

    }

    public User(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;

    }


    @Override
    protected Repository<User> getRepository() {
        return repository;
    }

    @Override
    @JsonIgnore
    public int getPrimaryKey() {
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean authenticate(String username, String password) {
        for (User user : repository.getAll()) {
            if (user.getUsername().equals(username)) {
                String encryptedPassword = Crypto.encrypt(password);
                return user.password.equals(encryptedPassword);
            }
        }
        return false;
    }

    public static User createUser(String username, String password) throws UserAlreadyExistsException {
        for (User user : repository.getAll()) {
            if (user.getUsername().equals(username)) {
                throw new UserAlreadyExistsException();
            }
        }
        User user = new User(repository.getAll().size(), username, Crypto.encrypt(password));
        user.save();
        return user;
    }



}
