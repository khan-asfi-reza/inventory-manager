package com.project.inventorymanagement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Tasks {
    public static void mainMethod() {

        Inventory inventory = new Inventory();
        System.out.println("--------------");
        System.out.println("Trying to create user with username: Asfi, Saad, Sadman, Fida");
        // Creates user
        try {
            User user = User.createUser("Asfi", "password");
            User user1 = User.createUser("Saad", "password");
            User user2 = User.createUser("Sadman", "password");
            User user3 = User.createUser("Fida", "password");
        }
        catch (UserAlreadyExistsException e){
            System.out.println("User already exists");
        }

        System.out.println("--------------");

        System.out.println("Custom Feature");
        System.out.println("Trying to check if user can login");
        boolean val = User.authenticate("Asfi", "password");
        if(val){
            System.out.println("User logged in");
        }
        System.out.println("---------------");

        // Add item to inventory : TASK 1
        System.out.println("Task 1");
        Music m1 = new Music("Submarine", 101, 20.0, "Rock", 1968, 5, 100, "The Beatles");
        inventory.addItem(m1);
        Music m2 = new Music("AM", 102, 10.0, "Indie Rock", 2013, 10, 10, "Arctic Monkeys");
        inventory.addItem(m2);

        Movie v1 = new Movie("Good Will Hunting", 201, 12.0, "Drama", 1997, 2, 125, "Gus Van Sant");
        inventory.addItem(v1);
        Movie v2 = new Movie("Life Is Beautiful", 202, 20.0, "Comedy", 1997, 0, 125, "Roberto Benigni");
        inventory.addItem(v2);

        Game g1 = new Game("Red Dead Redemption 2", 301, 29.0, "Action-Adventure", 2018, 15, 560, "Rockstar Games");
        inventory.addItem(g1);
        Game g2 = new Game("God of War", 302, 12.0, "Action", 2005, 5, 570, "Santa Monica Studio");
        inventory.addItem(g2);

        // Print items
        for (StockableProduct<?> product : inventory) {
            System.out.println(product.getInfo());
        }
        System.out.println("-------------------------------");

        // Invoice : TASK 2
        System.out.println("Task 2");
        Invoice invoice = new Invoice();
        invoice.addProduct(m1);
        invoice.addProduct(m2);
        invoice.addProduct(v1);
        invoice.addProduct(v2);
        invoice.addProduct(g1);
        System.out.println(invoice.getInvoice());
        System.out.println("-------------------------------");

        // Invoice : TASK 3
        System.out.println("Task 3");
        invoice.addProduct(g2);
        System.out.println(invoice.getInvoice());
        System.out.println("-------------------------------");

        // Games Finding : TASK 4
        System.out.println("Task 4");
        ArrayList<Game> games = inventory.getProductByClass(Game.class);
        for (Game g : games) {
            System.out.println(g.getInfo());
        }
        System.out.println("-------------------------------");

        // Cheapest Music
        System.out.println("Task 5");
        Music music = Music.getCheapestMusic();
        System.out.println(music.getInfo());
        System.out.println("-------------------------------");

        // Director Task 6
        System.out.println("Task 6");
        Movie v3 = new Movie("The Rainmaker", 205, 12.0, "Drama", 1997, 2, 125, "Francis Ford Coppola");
        inventory.addItem(v3);
        Movie v4 = new Movie("The Godfather", 206, 20.0, "Drama", 1997, 0, 125, "Francis Ford Coppola");
        inventory.addItem(v4);
        Movie v5 = new Movie("Apocalypse Now", 206, 20.0, "Drama", 1997, 0, 125, "Francis Ford Coppola");
        inventory.addItem(v5);
        ArrayList<Movie> movies = Movie.filterByDirector("Francis Ford Coppola");
        for (Movie m : movies) {
            System.out.println(m.getInfo());
        }
        System.out.println("-------------------------------");

        // Task 7
        System.out.println("Task 7");
        System.out.println("Available Stocks of Sold Products in Inventory:");
        Set<StockableProduct<?>> set = new HashSet<StockableProduct<?>>(invoice.getItems());
        for (StockableProduct<?> product : set) {
            System.out.println(product.getInfo());
        }


    }
}
