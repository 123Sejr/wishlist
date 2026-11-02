package com.example.wishlist.model;

import java.util.ArrayList;

public class Wishlist {
    private int id;
    private String name;
    private int user_id;
    private ArrayList<String> wishes;

    //Tom konstruktør til Spring
    public Wishlist() {

    }

    //Konstruktør med alt
    public Wishlist(int id, String name, int user_id/*, ArrayList<String> wishes*/) {
        this.id = id;
        this.name = name;
        this.user_id = user_id;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
                ", userId = " + user_id +
                ", wishes = " + wishes;
    }
}
