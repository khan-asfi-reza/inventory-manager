package com.project.inventorymanagement;


public class Movie extends StockableProduct<Movie> {
    private String director;
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

    public static Movie filterByDirector(String director) {
        for (Movie movie : repository.getAll()) {
            if (movie.getDirector().equals(director)) {
                return movie;
            }
        }
        return null;
    }

}