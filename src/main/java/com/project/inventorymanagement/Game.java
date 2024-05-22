package com.project.inventorymanagement;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Game extends StockableProduct {
    private String developer;

    public Game() {
        super();
    }

    Game(String name, int productId, double price, String genre, int yearPublished, double discount, int numberOfItemsStocked, String developer) {
        super(name, productId, price, genre, yearPublished, discount, numberOfItemsStocked);
        this.developer = developer;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", Developed by " + developer;
    }

    public static ArrayList<Game> readFromFile(String dataDir){
        File folder = new File(dataDir);
        ArrayList<Game> products = new ArrayList<>();
        if(!folder.exists()){
            boolean _ = folder.mkdirs();
        }
        File[] files = folder.listFiles();
        if(files != null){
            for (final File fileEntry : files) {
                if (fileEntry.isFile() && fileEntry.getName().endsWith(".dat")){
                    try {
                        FileInputStream fileIn = new FileInputStream(dataDir + "/" + fileEntry.getName());
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        Object object = in.readObject();
                        products.add((Game)object);
                        in.close();
                        fileIn.close();
                    }
                    catch (IOException | ClassNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        return products;
    }

}