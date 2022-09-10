package com.example.firebaseproject;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Product {
    private String name;
    private String price;
    private String sale;
    private String photo;
    private String state;
    private String type;
    private String key;

    public Product() {
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getSale() {
        return sale;
    }

    public String getPhoto() {
        return photo;
    }

    public String getState() {
        return state;
    }

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public Product(String name, String price, String sale, String photo, String state, String type) {
        this.name = name;
        this.price = price;
        this.sale = sale;
        this.photo = photo;
        this.state = state;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setType(String type) {
        this.type = type;
    }
}
