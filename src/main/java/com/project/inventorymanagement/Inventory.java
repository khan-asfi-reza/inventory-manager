package com.project.inventorymanagement;

import java.util.*;

public class Inventory implements Iterable<StockableProduct<?>> {
    private final ArrayList<StockableProduct<?>> items;


    public Inventory() {
        // Add items from all the repositories.
        // Repositories read data from the json files
        items = new ArrayList<>();
        items.addAll(Game.repository.getAll());
        items.addAll(Movie.repository.getAll());
        items.addAll(Music.repository.getAll());
    }


    /**
     * Adds item in the items array list and save the product in file
     * @param product Instance of StockableProduct SubClass
     */
    public void addItem(StockableProduct<?> product) {
        items.add(product);
        product.save();
    }

    /**
     * Removes item in the items array list and delete the product in file
     * @param productId Integer of the product id
     */
    void removeItem(int productId) {
        for (StockableProduct<?> item : items) {
            if(item.getProductId() == productId){
                item.delete();
                this.items.remove(item);
            }
        }
    }

    /**
     * Get item from items array list and remove one stock
     *  While getting the item using product id, one stock is removed
     * @param productId Integer of the product id
     */
    StockableProduct<?> getItem(int productId) {
        for (StockableProduct<?> product : items) {
            if (product.getProductId() == productId) {
                product.removeStock(1);
                product.save();
                return product;
            }
        }
        return null;
    }

    /**
     * Get item from items array list and add numberOfNewStock stock
     * While getting the item using product id, new item is saved
     * @param productId Integer product id
     * @param numberOfNewStock Integer number of new stock
     */
    void addProductStock(int productId, int numberOfNewStock) {
        for (StockableProduct<?> product : items) {
            if (product.getProductId() == productId) {
                product.addStock(numberOfNewStock);
                product.save();
            }
        }
    }

    /**
     * Return ArrayList of products based on
     * @param type Class type subclass of StockableProduct
     */
    public <T extends StockableProduct<?>> ArrayList<T> getProductByClass(Class<T> type){
        ArrayList<T> products = new ArrayList<>();
        for (StockableProduct<?> product : items) {
            if(type.isInstance(product)){
                products.add(type.cast(product));
            }
        }
        return products;
    }

    // Sorts item by price
    void sortByPrice() {
        items.sort(Comparator.comparingDouble(StockableProduct::getPrice));
    }

    // Sorts item by available stock
    void sortByAvailableStock() {
        items.sort(Comparator.comparingInt(StockableProduct::getNumberOfItemsStocked));
    }

    @Override
    public Iterator<StockableProduct<?>> iterator() {
        return items.iterator();
    }
}
