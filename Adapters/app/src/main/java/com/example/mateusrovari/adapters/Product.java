package com.example.mateusrovari.adapters;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String nome;
    private double price;

    public Product(String nome, double price) {
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

    public static List<Product> getProducts(){
        List<Product> products = new ArrayList<>();
        products.add(new Product("Beans", 31.5));
        products.add(new Product("Pasta", 52.5));
        products.add(new Product("Rice", 33.5));
        products.add(new Product("Coffe", 87.5));
        products.add(new Product("Potato", 12.5));
        products.add(new Product("Beans", 31.5));
        products.add(new Product("Pasta", 52.5));
        products.add(new Product("Rice", 33.5));
        products.add(new Product("Coffe", 87.5));
        products.add(new Product("Potato", 12.5));
        products.add(new Product("Beans", 31.5));
        products.add(new Product("Pasta", 52.5));
        products.add(new Product("Rice", 33.5));
        products.add(new Product("Coffe", 87.5));
        products.add(new Product("Potato", 12.5));
        products.add(new Product("Beans", 31.5));
        products.add(new Product("Pasta", 52.5));
        products.add(new Product("Rice", 33.5));
        products.add(new Product("Coffe", 87.5));
        products.add(new Product("Potato", 12.5));
        products.add(new Product("Beans", 31.5));
        products.add(new Product("Pasta", 52.5));
        products.add(new Product("Rice", 33.5));
        products.add(new Product("Coffe", 87.5));
        products.add(new Product("Potato", 12.5));
        products.add(new Product("Beans", 31.5));
        products.add(new Product("Pasta", 52.5));
        products.add(new Product("Rice", 33.5));
        products.add(new Product("Coffe", 87.5));
        products.add(new Product("Potato", 12.5));
        products.add(new Product("Beans", 31.5));
        products.add(new Product("Pasta", 52.5));
        products.add(new Product("Rice", 33.5));
        products.add(new Product("Coffe", 87.5));
        products.add(new Product("Potato", 12.5));
        products.add(new Product("Beans", 31.5));
        products.add(new Product("Pasta", 52.5));
        products.add(new Product("Rice", 33.5));

        return products;
    }
}
