package com.project.inventorymanagement;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.File;
import java.io.IOException;


public abstract class Model{

    /**
     * Primary key is needed to store the respective instance in its own json file.
     * For User the primary key is id, for Product the primary key is going to be productId.
     *
     */
    @JsonIgnore
    abstract public int getPrimaryKey();

    /**
     * Stores the instance's data in the respective json file.
     * After setting the instance attribute, call `.save()` to save it in the json file
     */
    public void save() {
        String dirPath = "data/" + this.getClass().getSimpleName().toLowerCase() + "s/";
        String filePath = dirPath + this.getPrimaryKey() + ".json";
        try {
            File directory = new File(dirPath);
            if (!directory.exists()) {
                boolean _ = directory.mkdirs();
            }
            JsonUtil.writeToJson(this, filePath);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete() {
        String dirPath = "data/" + this.getClass().getSimpleName().toLowerCase() + "s/";
        String filePath = dirPath + this.getPrimaryKey() + ".json";
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Deleted successfully" );
            } else {
                System.out.println("Failed to delete file" );
            }
        } else {
            System.out.println("No file found to delete");
        }
    }




    public Model(){

    }

}
