package com.project.inventorymanagement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Repository<T> {

    Class<T> type;

    ArrayList <T> cached = new ArrayList <>();

    ArrayList<Repository<?>> repos;

    public Repository(Class<T> type){
        this.type = type;
    }

    public Repository(Repository<?>  ... repositories) {
        repos = new ArrayList <>();
        repos.addAll(List.of(repositories));
    }


    public void fetchAll(){

    }

    public ArrayList<T> getAll() {
        ArrayList<T> data = new ArrayList<>();
        File folder = new File("data/" + type.getSimpleName().toLowerCase() + "s" + "/");
        File[] files = folder.listFiles((d, name) -> name.endsWith(".json"));
        if (files != null) {
            for (File file : files) {
                try {
                    T item = JsonUtil.readFromJson(file.getAbsolutePath(), type);
                    data.add(item);
                    // Integer fileId = Integer.parseInt(file.getName().replace(".json", ""));
                } catch (IOException e) {
                    System.out.println(e.getMessage());;
                }
            }
        }
        return data;
    }







}
