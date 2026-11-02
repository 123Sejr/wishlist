package com.example.wishlist.model;

import java.util.ArrayList;

public class Wishlist {
    private int id;
    private String name;
    private int userId;
    private ArrayList<String> wishes;

    //Tom konstruktør til Spring
    public Wishlist() {

    }

    //Konstruktør med alt
    public Wishlist(int id, String name, int userId/*, ArrayList<String> wishes*/) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        //this.wishes = wishes;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<String> getWishes() {
        return wishes;
    }

    public void setWishes(ArrayList<String> wishes) {
        this.wishes = wishes;
    }

    @Override
    public String toString() {
        return "Wishlist: " +
                "id = " + id +
                ", name = " + name +
                ", userId = " + userId +
                ", wishes = " + wishes;
    }
}
