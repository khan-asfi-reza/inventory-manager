package com.project.inventorymanagement;

import java.io.*;
import java.util.*;

class Inventory implements Iterable<StockableProduct> {
    private final ArrayList<StockableProduct> items;
    Repository<Game> gameRepository = new Repository<>(Game.class);
    Repository<Movie> movieRepository = new Repository<>(Movie.class);
    Repository<Music> musicRepository = new Repository<>(Music.class);


    public Inventory() {
        items = new ArrayList<>();
        items.addAll(gameRepository.getAll());
        items.addAll(movieRepository.getAll());
        items.addAll(musicRepository.getAll());
    }



    public void addItem(StockableProduct product) {
        items.add(product);
        product.save();
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

    StockableProduct getItem(int productId) {
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
                product.save();
            }
        }
    }

    public <T extends StockableProduct> ArrayList<T> getProductByClass(Class<T> type){
        ArrayList<T> products = new ArrayList<>();
        for (StockableProduct product : items) {
            if(type.isInstance(product)){
                products.add(type.cast(product));
            }
        }
        return products;
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
