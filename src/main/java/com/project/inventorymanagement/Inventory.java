package com.project.inventorymanagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

class Inventory implements Iterable<StockableProduct> {
    private ArrayList<StockableProduct> items;

    Inventory(){
        this.items = new ArrayList<>();
    }

    void addItem(StockableProduct product) {
        items.add(product);
    }

    void removeItem(int productId) {
        items.removeIf(p -> p.getProductId() == productId);
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
