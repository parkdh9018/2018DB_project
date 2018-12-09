package com.example.demo.DB.DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {
    private String ordernum;
    private String ordercode;
    private String orderdate;
    private String totalprice;
    private List<String> foodname;
    private List<String> drinkname;
    private List<String> setmenuid;

    public Order(){
        foodname = new ArrayList<String>();
        drinkname = new ArrayList<String>();
        setmenuid = new ArrayList<String>();
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public void setFoodname(String foodname) { this.foodname.add(foodname);}

    public void setDrinkname(String drinkname) { this.drinkname.add(drinkname);}

    public void setSetmenuid(String setmenuid) { this.foodname.add(setmenuid);}
}
