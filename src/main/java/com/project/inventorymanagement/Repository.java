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
        File folder = new File("data/" + type.getSimpleName().toLowerCase() + "s" + "/");
        File[] files = folder.listFiles((d, name) -> name.endsWith(".json"));
        if (files != null) {
            for (File file : files) {
                try {
                    T item = JsonUtil.readFromJson(file.getAbsolutePath(), type);
                    cached.add(item);
                } catch (IOException e) {
                    System.out.println(e.getMessage());;
                }
            }
        }
    }

    public ArrayList<T> getAll() {
        if(cached.isEmpty()){
            cached  = new ArrayList <>();
            fetchAll();
            return cached;
        }
        return cached;
    }



    public void save(Model<?> item) {
        String dirPath = "data/" + item.getClass().getSimpleName().toLowerCase() + "s/";
        String filePath = dirPath + item.getPrimaryKey() + ".json";
        try {
            File directory = new File(dirPath);
            if (!directory.exists()) {
                boolean _ = directory.mkdirs();
            }
            JsonUtil.writeToJson(item, filePath);
            fetchAll();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
