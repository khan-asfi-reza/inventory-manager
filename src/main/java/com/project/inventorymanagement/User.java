package com.project.inventorymanagement;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class User extends Model{

    // id is auto generated
    private int id;
    // Username of the user
    private String username;
    // Password of the user, password will be encrypted using md5 hashing algorithm
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

    /**
     * Check if username exists in the arraylist of users
     * If exists check the hash of the password and signed-up users password hash
     * @param username User's login username
     * @param password User's login password
     * @return Is authenticated or not
     */
    public static boolean authenticate(String username, String password) {
        for (User user : repository.getAll()) {
            if (user.getUsername().equals(username)) {
                String encryptedPassword = Crypto.encrypt(password);
                return user.password.equals(encryptedPassword);
            }
        }
        return false;
    }

    /**
     * Check if username exists in the arraylist of users
     * If not exists create the user by setting the username and the password after encryption
     * @param username User's login username
     * @param password User's login password
     * @return Created User
     */
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
