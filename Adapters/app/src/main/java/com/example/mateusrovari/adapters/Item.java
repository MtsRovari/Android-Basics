package com.example.mateusrovari.adapters;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private String nome;
    private double price;

    public Item(String nome, double price) {
        this.nome = nome;
        this.price = price;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static List<Item> getProducts(){
        List<Item> products = new ArrayList<>();
        products.add(new Item("Item 1", 1));
        products.add(new Item("Item 2", 2));
        products.add(new Item("Item 3", 3));
        products.add(new Item("Item 4", 4));
        products.add(new Item("Item 5", 5));
        products.add(new Item("Item 6", 6));
        products.add(new Item("Item 7", 7));
        products.add(new Item("Item 8", 8));
        products.add(new Item("Item 9", 9));
        products.add(new Item("Item 10", 10));

        return products;
    }
}
