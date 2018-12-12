package com.example.demo.DB.DTO;


public class Drink {

  private String category;
  private String drinksize;
  private String drinkname;
  private int price;
  private String companyname;
  private String imageurl;


  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }


  public String getDrinksize() {
    return drinksize;
  }

  public void setDrinksize(String drinksize) {
    this.drinksize = drinksize;
  }


  public String getDrinkname() {
    return drinkname;
  }

  public void setDrinkname(String drinkname) {
    this.drinkname = drinkname;
  }


  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }


  public String getCompanyname() {
    return companyname;
  }

  public void setCompanyname(String companyname) {
    this.companyname = companyname;
  }

  public String getImageurl() {
    return imageurl;
  }

  public void setImageurl(String imageurl) {
    this.imageurl = imageurl;
  }
}
