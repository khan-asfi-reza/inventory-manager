package com.project.inventorymanagement;


import java.util.ArrayList;

public class Game extends StockableProduct<Game> {
    // Developer of the game
    private String developer;
    // Repository contains the Game instances as an ArrayList, and it reads from data/games directory json files
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

    /**
     * Return games developed by developer
     * @param developer Name of the developer
     * @return ArrayList<Games>
     */
    public static ArrayList<Game> filterByDeveloper(String developer) {
        ArrayList<Game> games = new ArrayList<>();
        for (Game game : repository.getAll()) {
            if (game.getDeveloper().equals(developer)) {
                games.add(game);
            }
        }
        return games;
    }


}