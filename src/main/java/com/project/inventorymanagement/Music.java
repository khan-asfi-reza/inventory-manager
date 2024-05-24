package com.project.inventorymanagement;


import java.util.ArrayList;

public class Music extends StockableProduct<Music> {
    // Name of the artist
    private String artistName;
    // Repository contains the Music instances as an ArrayList, and it reads from data/musics directory json files
    public static final  Repository<Music> repository = new Repository<>(Music.class);

    public Music() {
        super();
    }


    Music(String name, int productId, double price, String genre, int yearPublished, double discount, int numberOfItemsStocked, String artistName) {
        super(name, productId, price, genre, yearPublished, discount, numberOfItemsStocked);
        this.artistName = artistName;
    }


    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", Performed by " + artistName;
    }

    /**
     * Return music produced by artist
     * @param artistName Name of the artist
     * @return ArrayList<Games>
     */
    public static ArrayList<Music> filterByArtist(String artistName) {
        ArrayList<Music> musics = new ArrayList<>();
        for (Music music : repository.getAll()) {
            if (music.getArtistName().equals(artistName)) {
                musics.add(music);
            }
        }
        return musics;
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