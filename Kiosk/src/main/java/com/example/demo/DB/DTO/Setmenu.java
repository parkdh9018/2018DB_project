package com.example.demo.DB.DTO;

import java.util.ArrayList;
import java.util.List;

public class Setmenu {
    private String setmenuid;
    private String category;
    private int totalprice;
    private List<String> foodname;
    private List<String> drinkname;

    public Setmenu(){
        foodname = new ArrayList<String>();
        drinkname = new ArrayList<String>();
    }

    public String getSetmenuid() {
        return setmenuid;
    }

    public void setSetmenuid(String setmenuid) {
        this.setmenuid = setmenuid;
    }

    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }

    public void setFoodname(String foodname) { this.foodname.add(foodname); }


    public void setDrinkname(String drinkname) {
        this.drinkname.add(drinkname);
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public String toStringCompoenet(){
        String result = foodname.get(0);
        for(int i=1;i<foodname.size();i++)
            result += "+"+foodname.get(i);

        for(int i=0;i<drinkname.size();i++)
            result += "+"+drinkname.get(i);

        return result;
    }

}
