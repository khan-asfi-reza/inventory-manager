package com.project.inventorymanagement;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super("User already exists");
    }
}
