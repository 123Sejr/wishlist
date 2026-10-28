package com.example.wishlist.model;

public class Wish {
    int id;
    String name;
    String desc;
    float price;
    String link;
    String image;
    boolean isPriority;

    // Tom constructor til Spring
    public Wish(){}

    // Constructor med alt
    public Wish(int id, String name, String desc, float price, String link, String image, boolean isPriority) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.link = link;
        this.image = image;
        this.isPriority = isPriority;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isPriority() {
        return isPriority;
    }

    public void setPriority(boolean priority) {
        isPriority = priority;
    }

    @Override
    public String toString() {
        return "Wish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", link='" + link + '\'' +
                ", image='" + image + '\'' +
                ", isPriority=" + isPriority +
                '}';
    }
}
