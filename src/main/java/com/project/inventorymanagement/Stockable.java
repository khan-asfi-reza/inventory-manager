package com.project.inventorymanagement;

public interface Stockable {
    void addStock(int num);
    void removeStock(int num);
    void editStock(int num);
}