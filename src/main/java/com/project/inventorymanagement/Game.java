package com.project.inventorymanagement;


public class Game extends StockableProduct<Game> {
    private String developer;
    public static final Repository<Game> repository = new Repository<>(Game.class);


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

    public static Game filterByDeveloper(String developer) {
        for (Game game : repository.getAll()) {
            if (game.getDeveloper().equals(developer)) {
                return game;
            }
        }
        return null;
    }


}