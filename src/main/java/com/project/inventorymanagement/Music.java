package com.project.inventorymanagement;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Music extends StockableProduct {
    private String artistName;

    public Music() {
        super();
    }


    Music(String name, int productId, double price, String genre, int yearPublished, double discount, int numberOfItemsStocked, String artistName) {
        super(name, productId, price, genre, yearPublished, discount, numberOfItemsStocked);
        this.artistName = artistName;
    }


    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", Performed by " + artistName;
    }

    public static ArrayList<Music> readFromFile(String dataDir){
        File folder = new File(dataDir);
        ArrayList<Music> products = new ArrayList<>();
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
                        products.add((Music)object);
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