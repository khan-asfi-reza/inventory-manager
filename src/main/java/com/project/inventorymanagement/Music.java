package com.project.inventorymanagement;


public class Music extends StockableProduct {

    private String artistName;
    public static final  Repository<Music> repository = new Repository<>(Music.class);

    public Music() {
        super();
    }


    Music(String name, int productId, double price, String genre, int yearPublished, double discount, int numberOfItemsStocked, String artistName) {
        super(name, productId, price, genre, yearPublished, discount, numberOfItemsStocked);
        this.artistName = artistName;
    }

    @Override
    protected Repository<Music> getRepository() {
        return repository;
    }

    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", Performed by " + artistName;
    }


}