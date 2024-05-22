package com.project.inventorymanagement;

import com.fasterxml.jackson.annotation.JsonIgnore;



public abstract class Model<T extends Model<T> > {

    protected abstract Repository<T> getRepository();

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
    protected void save() {
        Repository<T> repo = getRepository() ;
        if (repo != null) {
            repo.save(this);
        }
    }


    public Model(){

    }

}
