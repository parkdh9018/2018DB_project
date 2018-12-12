package com.example.demo.DB.DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Orderitem {
    private String name;
    private String count;
    private String Originprice;
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getOriginprice() {
        return Originprice;
    }

    public void setOriginprice(String originprice) {
        Originprice = originprice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
