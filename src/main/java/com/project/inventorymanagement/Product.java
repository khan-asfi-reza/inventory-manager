package com.project.inventorymanagement;

public abstract class Product {
    private String name;
    private int productId;
    private double price;
    private String genre;
    private int yearPublished;
    private double discount;

    // Constructor
    Product(String name, int productId, double price, String genre, int yearPublished, double discount) {
        this.name = name;
        this.productId = productId;
        this.price = price;
        this.genre = genre;
        this.yearPublished = yearPublished;
        this.discount = discount;
    }

    // Getters
    public String getName() { return name; }
    public int getProductId() { return productId; }
    public double getPrice() { return price; }
    public String getGenre() { return genre; }
    public int getYearPublished() { return yearPublished; }
    public double getDiscount() { return discount; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setProductId(int productId) { this.productId = productId; }
    public void setPrice(double price) { this.price = price; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setYearPublished(int yearPublished) { this.yearPublished = yearPublished; }
    public void setDiscount(double discount) { this.discount = discount; }

    abstract String getInfo();
    abstract public String toString();
}
