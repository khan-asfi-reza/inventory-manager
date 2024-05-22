package com.project.inventorymanagement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Repository<T> {

    Class<T> type;

    public Repository(Class<T> type){
        this.type = type;
    }

    public ArrayList<T> getAll() {
        ArrayList<T> data = new ArrayList<T>();
        File folder = new File("data/" + type.getSimpleName().toLowerCase() + "s" + "/");
        File[] files = folder.listFiles((d, name) -> name.endsWith(".json"));
        if (files != null) {
            for (File file : files) {
                try {
                    T item = JsonUtil.deserializeFromJson(file.getAbsolutePath(), type);
                    data.add(item);
                } catch (IOException e) {
                    System.out.println(e.getMessage());;
                }
            }
        }
        return data;
    }

}
