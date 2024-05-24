package com.project.inventorymanagement;


public class Music extends StockableProduct<Music> {

    private String artistName;
    public static final  Repository<Music> repository = new Repository<>(Music.class);

    public Music() {
        super();
    }


    Music(String name, int productId, double price, String genre, int yearPublished, double discount, int numberOfItemsStocked, String artistName) {
        super(name, productId, price, genre, yearPublished, discount, numberOfItemsStocked);
        this.artistName = artistName;
        // Auto assign ID
        setProductId(IdStore.getIndex());
    }


    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", Performed by " + artistName;
    }

    public static Music filterByArtist(String artistName) {
        for (Music music : repository.getAll()) {
            if (music.getArtistName().equals(artistName)) {
                return music;
            }
        }
        return null;
    }

    public static Music getCheapestMusic(){
        Music m = null;
        double cheap = Double.MAX_VALUE;
        for (Music music : repository.getAll()) {
            if (cheap >= music.getPrice()) {
                cheap = music.getPrice();
                m = music;
            }
        }
        return m;
    }

}