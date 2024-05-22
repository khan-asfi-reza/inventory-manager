package com.project.inventorymanagement;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        try {
            User user = User.createUser("user1", "password");
            User user1 = User.createUser("user2", "password");
            User user2 = User.createUser("user3", "password");
        }
        catch (UserAlreadyExistsException e){
            System.out.println(e.getMessage());
        }

        System.out.println(User.authenticate("user1", "password"));

        inventory.addItem(new Music("Submarine", 101, 20.0, "Rock", 1968, 5, 100, "The Beatles"));
        inventory.addItem(new Music("AM", 102, 10.0, "Indie Rock", 2013, 10, 10, "Arctic Monkeys"));
        inventory.addItem(new Movie("Good Will Hunting", 201, 12.0, "Drama", 1997, 2, 125, "Gus Van Sant"));
        inventory.addItem(new Movie("Life Is Beautiful", 202, 20.0, "Comedy", 1997, 0, 125, "Roberto Benigni"));
        inventory.addItem(new Game("Red Dead Redemption 2", 301, 29.0, "Action-Adventure", 2018, 15, 560, "Rockstar Games"));
        inventory.addItem(new Game("God of War", 302, 12.0, "Action", 2005, 5, 570, "Santa Monica Studio"));
        inventory.addItem(new Game("Grand Theft Auto V", 303, 30.0, "Action-Adventure", 2013, 20, 600, "Rockstar Games"));

        for (StockableProduct product : inventory) {
            System.out.println(product.getInfo());
        }



    }
}
