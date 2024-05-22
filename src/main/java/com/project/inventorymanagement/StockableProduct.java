package com.project.inventorymanagement;

public class StockableProduct extends Product implements Stockable {
    private int numberOfItemsStocked;

    StockableProduct(String name, int productId, double price, String genre, int yearPublished, double discount, int numberOfItemsStocked) {
        super(name, productId, price, genre, yearPublished, discount);
        this.numberOfItemsStocked = numberOfItemsStocked;
    }

    public void addStock(int num) { numberOfItemsStocked += num; }
    public void removeStock(int num) { numberOfItemsStocked -= num; }
    public void editStock(int num) { numberOfItemsStocked = num; }

    public int getNumberOfItemsStocked() { return numberOfItemsStocked; }
    public void setNumberOfItemsStocked(int numberOfItemsStocked) { this.numberOfItemsStocked = numberOfItemsStocked; }

    @Override
    public String getInfo() {
        return "Stock Level: " + numberOfItemsStocked;
    }

    @Override
    public String toString() {
        return super.getName() + " - " + super.getGenre() + " - " + super.getYearPublished();
    }
}