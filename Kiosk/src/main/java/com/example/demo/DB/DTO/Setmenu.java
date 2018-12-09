package com.example.demo.DB.DTO;

import java.util.ArrayList;
import java.util.List;

public class Setmenu {
    private String setmenuid;
    private String settype;
    private String totalprice;
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

    public String getSettype() {
        return settype;
    }

    public void setSettype(String settype) {
        this.settype = settype;
    }

    public void setFoodname(String foodname) { this.foodname.add(foodname); }


    public void setDrinkname(String drinkname) {
        this.drinkname.add(drinkname);
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String toStringCompoenet(){
        String result = null;
        for(int i=0;i<foodname.size();i++)
            result += "+"+foodname.get(i);

        for(int i=0;i<drinkname.size();i++)
            result += "+"+drinkname.get(i);

        return result;
    }

}
