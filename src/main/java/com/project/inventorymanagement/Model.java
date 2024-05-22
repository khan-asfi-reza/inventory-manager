package com.project.inventorymanagement;

import java.io.File;
import java.io.IOException;



public abstract class Model {

    abstract public int getPrimaryKey();

    protected void save() {
        String dirPath = "data/" + this.getClass().getSimpleName().toLowerCase() + "s/";
        String filePath = dirPath + getPrimaryKey() + ".json";
        try {
            File directory = new File(dirPath);
            if (!directory.exists()) {
                boolean _ = directory.mkdirs();
            }
            JsonUtil.serializeToJson(this, filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
