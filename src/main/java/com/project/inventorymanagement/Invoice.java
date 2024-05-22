package com.project.inventorymanagement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Invoice {
    private ArrayList<Product> items;
    private LocalDateTime date;

    // Constructor
    Invoice() {
        this.items = new ArrayList<>();
        this.date = LocalDateTime.now();
    }

    // Get formatted date-time
    public String getLocalDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }

    // Add product to invoice
    public void addProduct(Product product) {
        if (((StockableProduct) product).getNumberOfItemsStocked() > 0) {
            items.add(product);
            ((StockableProduct) product).removeStock(1);
        } else {
            System.out.println("Product is out of stock!");
        }
    }

    // Remove product from invoice
    public void removeProduct(Product product) {
        if (items.remove(product)) {
            ((StockableProduct) product).addStock(1);
        } else {
            System.out.println("Product not found in invoice!");
        }
    }

    // Calculate total price without any discount
    private double calculatePriceWithoutDiscount() {
        double total = 0;
        for (Product product : items) {
            total += product.getPrice();
        }
        return total;
    }

    // Check if Full House discount is applicable
    private boolean isFullHouseDiscountAvailable() {
        Map<Class<?>, Integer> countMap = new HashMap<>();
        // Initialize count for each category
        countMap.put(Game.class, 0);
        countMap.put(Music.class, 0);
        countMap.put(Movie.class, 0);

        // Count instances of each category
        for (Product product : items) {
            countMap.computeIfPresent(product.getClass(), (k, v) -> v + 1);
        }

        // Check if all categories have at least two items
        return countMap.values().stream().allMatch(count -> count >= 2);
    }

    // Calculate the best discounted price considering all discounts
    public double calculateDiscountedPrice() {
        double totalPrice = calculatePriceWithoutDiscount();
        double priceAfterRegularDiscount = totalPrice;

        // Apply regular discounts
        for (Product product : items) {
            priceAfterRegularDiscount -= product.getPrice() * product.getDiscount() / 100;
        }

        // Apply full house discount if applicable
        double fullHouseDiscount = isFullHouseDiscountAvailable() ? totalPrice * 0.5 : Double.MAX_VALUE;

        // Return the lower value between regular discount and full house discount
        return Math.min(priceAfterRegularDiscount, fullHouseDiscount);
    }

    // Generate and return invoice string
    public String getInvoice() {
        StringBuilder sb = new StringBuilder();
        sb.append("Date - ").append(getLocalDateTime()).append("\n");
        for (Product product : items) {
            sb.append("Name: ").append(product.getName()).append(", Price: $").append(product.getPrice()).append("\n");
        }
        sb.append("Total Price: $").append(calculatePriceWithoutDiscount()).append("\n");
        sb.append("Price after discount: $").append(calculateDiscountedPrice()).append("\n");
        return sb.toString();
    }
}