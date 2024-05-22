package com.project.inventorymanagement;

import java.io.*;
import java.util.*;

class Inventory implements Iterable<StockableProduct> {
    private final ArrayList<StockableProduct> items;

    public Inventory() {
        items = new ArrayList<>();
        loadItems("games", Game.class);
        loadItems("movies", Movie.class);
        loadItems("musics", Music.class);
    }



    private <T extends StockableProduct> void loadItems(String dir, Class<T> type) {
        File folder = new File("data/" + dir + "/");
        File[] files = folder.listFiles((d, name) -> name.endsWith(".json"));
        if (files != null) {
            for (File file : files) {
                try {
                    T item = JsonUtil.deserializeFromJson(file.getAbsolutePath(), type);
                    System.out.println(item.toString());
                    items.add(item);
                } catch (IOException e) {
                    System.out.println(e.getMessage());;
                }
            }
        }
    }

    public void addItem(StockableProduct product) {
        items.add(product);
        product.saveToJson();
    }


    ArrayList<Game> getGames(){
        ArrayList<Game> games = new ArrayList<>();
        for (StockableProduct product : items) {
            if(product instanceof Game){
                games.add((Game) product);
            }
        }
        return games;
    }

    ArrayList<Movie> getMovies(){
        ArrayList<Movie> movies = new ArrayList<>();
        for (StockableProduct product : items) {
            if(product instanceof Movie){
                movies.add((Movie) product);
            }
        }
        return movies;
    }

    ArrayList<Music> getMusics(){
        ArrayList<Music> musics = new ArrayList<>();
        for (StockableProduct product : items) {
            if(product instanceof Music){
                musics.add((Music) product);
            }
        }
        return musics;
    }


    private void deleteJsonFile(StockableProduct item) {
        String dirPath = "data/" + item.getClass().getSimpleName().toLowerCase() + "s/";
        String filePath = dirPath + item.getProductId() + ".json";
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Deleted successfully" );
            } else {
                System.out.println("Failed to delete file" );
            }
        } else {
            System.out.println("No file found to delete");
        }
    }


    void removeItem(int productId) {
        for (StockableProduct item : items) {
            if(item.getProductId() == productId){
                deleteJsonFile(item);
                this.items.remove(item);
            }
        }
    }

    Product getItem(int productId) {
        for (StockableProduct product : items) {
            if (product.getProductId() == productId) {
                product.removeStock(1);
                return product;
            }
        }
        return null;
    }

    void addProductStock(int productId, int numberOfNewStock) {
        for (StockableProduct product : items) {
            if (product.getProductId() == productId) {
                product.addStock(numberOfNewStock);
            }
        }
    }

    void sortByPrice() {
        items.sort(Comparator.comparingDouble(StockableProduct::getPrice));
    }

    void sortByAvailableStock() {
        items.sort(Comparator.comparingInt(StockableProduct::getNumberOfItemsStocked));
    }

    @Override
    public Iterator<StockableProduct> iterator() {
        return items.iterator();
    }
}
