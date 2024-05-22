package com.project.inventorymanagement;


public class Game extends StockableProduct {
    private String developer;
    public static final Repository<Game> repository = new Repository<>(Game.class);


    @Override
    protected Repository<Game> getRepository() {
        return repository;
    }

    public Game() {
        super();
    }

    Game(String name, int productId, double price, String genre, int yearPublished, double discount, int numberOfItemsStocked, String developer) {
        super(name, productId, price, genre, yearPublished, discount, numberOfItemsStocked);
        this.developer = developer;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", Developed by " + developer;
    }


}