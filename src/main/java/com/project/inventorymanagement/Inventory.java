package com.project.inventorymanagement;

import java.util.*;

public class Inventory implements Iterable<StockableProduct<?>> {
    private final ArrayList<StockableProduct<?>> items;



    public Inventory() {
        items = new ArrayList<>();
        items.addAll(Game.repository.getAll());
        items.addAll(Movie.repository.getAll());
        items.addAll(Music.repository.getAll());
    }



    public void addItem(StockableProduct<?> product) {
        items.add(product);
        product.save();
    }

    void removeItem(int productId) {
        for (StockableProduct<?> item : items) {
            if(item.getProductId() == productId){
                item.delete();
                this.items.remove(item);
            }
        }
    }

    StockableProduct<?> getItem(int productId) {
        for (StockableProduct<?> product : items) {
            if (product.getProductId() == productId) {
                product.removeStock(1);
                return product;
            }
        }
        return null;
    }

    void addProductStock(int productId, int numberOfNewStock) {
        for (StockableProduct<?> product : items) {
            if (product.getProductId() == productId) {
                product.addStock(numberOfNewStock);
                product.save();
            }
        }
    }

    public <T extends StockableProduct<?>> ArrayList<T> getProductByClass(Class<T> type){
        ArrayList<T> products = new ArrayList<>();
        for (StockableProduct<?> product : items) {
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
    public Iterator<StockableProduct<?>> iterator() {
        return items.iterator();
    }
}
