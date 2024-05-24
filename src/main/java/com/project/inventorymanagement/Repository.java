package com.project.inventorymanagement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Repository<T> {

    Class<T> type;


    public Repository(Class<T> type){
        this.type = type;
    }

    /**
     * Read data from data json files and return array list
     * @return ArrayList<T>
     */
    public ArrayList<T> getAll() {
        ArrayList<T> data = new ArrayList<>();
        // Build the folder using the class name
        File folder = new File("data/" + type.getSimpleName().toLowerCase() + "s" + "/");
        // Get list of files from the folder
        File[] files = folder.listFiles((d, name) -> name.endsWith(".json"));
        if (files != null) {
            // Open each file and read from json and build object
            for (File file : files) {
                try {
                    T item = JsonUtil.readFromJson(file.getAbsolutePath(), type);
                    data.add(item);
                } catch (IOException e) {
                    System.out.println(e.getMessage());;
                }
            }
        }
        return data;
    }







}
