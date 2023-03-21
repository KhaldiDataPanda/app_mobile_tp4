package com.example.myapplication;

public class Achat {
    private String name;
    private int quantity;

    public Achat(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    // create method setName to edit name of item
    public void setName(String name) {
        this.name = name;
    }

    // create method setQuantity to edit quantity of item
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}