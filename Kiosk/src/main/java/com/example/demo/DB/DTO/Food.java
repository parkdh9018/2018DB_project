package com.example.demo.DB.DTO;


public class Food {

  private int price;
  private String foodname;
  private String scoreavg;
  private String category;
  private String imageurl;
  private String soldout;


  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }


  public String getFoodname() {
    return foodname;
  }

  public void setFoodname(String foodname) {
    this.foodname = foodname;
  }


  public String getScoreavg() {
    return scoreavg;
  }

  public void setScoreavg(String scoreavg) {
    this.scoreavg = scoreavg;
  }


  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getImageurl() {
    return imageurl;
  }

  public void setImageurl(String imageurl) {
    this.imageurl = imageurl;
  }

  public String getSoldout() {
    return soldout;
  }

  public void setSoldout(String soldout) {
    this.soldout = soldout;
  }
}
