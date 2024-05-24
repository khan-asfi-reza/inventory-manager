package com.project.inventorymanagement;


import java.util.ArrayList;

public class Movie extends StockableProduct<Movie> {
    // Movie director
    private String director;
    // Repository contains the Movie instances as an ArrayList, and it reads from data/movies directory json files
    public static final  Repository<Movie> repository = new Repository<>(Movie.class);


    public Movie() {
        super();
    }

    Movie(String name, int productId, double price, String genre, int yearPublished, double discount, int numberOfItemsStocked, String director) {
        super(name, productId, price, genre, yearPublished, discount, numberOfItemsStocked);
        this.director = director;
    }


    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", Directed by " + director;
    }

    /**
     * Return movies directed by director
     * @param director Name of the director
     * @return ArrayList<Games>
     */
    public static ArrayList<Movie> filterByDirector(String director) {
        ArrayList<Movie> movies = new ArrayList<>();
        for (Movie movie : repository.getAll()) {
            if (movie.getDirector().equals(director)) {
                movies.add(movie);
            }
        }
        return movies;
    }

}