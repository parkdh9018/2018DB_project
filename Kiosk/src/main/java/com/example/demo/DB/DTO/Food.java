package com.example.demo.DB.DTO;


public class Food {

  private String price;
  private String foodname;
  private String scoreavg;
  private String category;


  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
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

}
