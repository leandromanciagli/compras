package org.example.compras.model;

public class Product {
    private String name;
    private double price;
    private int amount;
    private double totalPrice;

    // Constructor
    public Product(String name, double price, int amount, double totalPrice) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
