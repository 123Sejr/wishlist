package com.example.wishlist.model;

public class Wish {
    private int id;
    private String name;
    private String desc;
    private int listId;
    private Float price;
    private String link;
    private String image;
    private boolean isPriority;

    // Tom constructor til Spring
    public Wish(){}

    // Constructor med alt
    public Wish(int id, String name, String desc, int listId, Float price, String link, String image, boolean isPriority) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.listId = listId;
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

    public int getListId(){
        return listId;
    }

    public void setListId(int listId){
        this.listId = listId;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
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

    public boolean getIsPriority() {
        return isPriority;
    }

    public void setIsPriority(boolean priority) {
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
