package com.project.inventorymanagement;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Movie extends StockableProduct {
    private String director;

    public Movie() {
        super();
    }

    Movie(String name, int productId, double price, String genre, int yearPublished, double discount, int numberOfItemsStocked, String director) {
        super(name, productId, price, genre, yearPublished, discount, numberOfItemsStocked);
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", Directed by " + director;
    }

    public static ArrayList<Movie> readFromFile(String dataDir){
        File folder = new File(dataDir);
        ArrayList<Movie> products = new ArrayList<>();
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
                        products.add((Movie)object);
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