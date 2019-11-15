package com.nvk.chi.model;

public class Category {
    private int id;
    private String catName;
    private boolean status;

    public Category() {
    }

    public Category(int id, String catName, boolean status) {
        this.id = id;
        this.catName = catName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
