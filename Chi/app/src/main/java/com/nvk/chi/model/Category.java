package com.nvk.chi.model;

public class Category {
    private String catName;
    private boolean status;

    public Category() {
    }

    public Category(String catName, boolean status) {
        this.catName = catName;
        this.status = status;
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
