package com.project.inventorymanagement;

import java.io.*;

public abstract class StockableProduct extends Product implements Stockable, Serializable  {
    private int numberOfItemsStocked;

    public StockableProduct() {

    }

    StockableProduct(String name, int productId, double price, String genre, int yearPublished, double discount, int numberOfItemsStocked) {
        super(name, productId, price, genre, yearPublished, discount);
        this.numberOfItemsStocked = numberOfItemsStocked;
    }


    @Override
    public void setProductId(int productId) {
        super.setProductId(productId);

    }

    @Override
    public void setName(String name) {
        super.setName(name);

    }

    @Override
    public void setPrice(double price) {
        super.setPrice(price);

    }

    @Override
    public void setGenre(String genre) {
        super.setGenre(genre);

    }

    @Override
    public void setYearPublished(int yearPublished) {
        super.setYearPublished(yearPublished);
    }

    @Override
    public void setDiscount(double discount) {
        super.setDiscount(discount);
    }


    public void setNumberOfItemsStocked(int numberOfItemsStocked) {
        this.numberOfItemsStocked = numberOfItemsStocked;
    }

    public void addStock(int num) { numberOfItemsStocked += num; }
    public void removeStock(int num) { numberOfItemsStocked -= num; }
    public void editStock(int num) { numberOfItemsStocked = num; }

    public int getNumberOfItemsStocked() { return numberOfItemsStocked; }



    @Override
    public String toString() {
        return super.getName() + " - " + super.getGenre() + " - " + super.getYearPublished();
    }

    @Override
    public String getInfo() {
        return "Name: "+ this.getName() +
                ", Product ID: " + this.getProductId() +
                ", Price: " + this.getPrice() +
                ", Genre: " + this.getGenre() +
                ", Year Published: " + this.getYearPublished() +
                ", Stock Level: " + numberOfItemsStocked;
    }


}