package com.nvk.chi.model;

public class Product {
    private int id;
    private String name;
    private int quatity;
    private double price;
    private double sum;
    private int cat_id;
    private Boolean status;

    public Product() {
    }

    public Product(int id,String name, int quatity, double price, double sum, int cat_id, Boolean status) {
        this.id = id;
        this.name = name;
        this.quatity = quatity;
        this.price = price;
        this.sum = sum;
        this.cat_id = cat_id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
