package com.example.demo.DB.DAO;

import com.example.demo.DB.DBconnect;
import com.example.demo.DB.DTO.Drink;
import com.example.demo.DB.DTO.Food;
import com.example.demo.DB.DTO.Setmenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MenuDAO {
    private Connection con = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private PreparedStatement pstmt2 = null;
    private ResultSet rs = null;
    private ResultSet rs2 = null;
    private  String sql = null;

    public MenuDAO(){}

    public void insertFood(String category, String foodname, int price){
        con = DBconnect.getConnection();


        try{
            sql="INSERT INTO FOOD(CATEGORY, FOODNAME, PRICE) VALUES(?,?,?)";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, category);
            pstmt.setString(2, foodname);
            pstmt.setInt(3,price);
            pstmt.executeUpdate();

        }catch (Exception e ){
            e.printStackTrace();
        }finally {
            DBconnect.close(con, pstmt);
        }
    }
    public void insertDrink(String category, String drinksize, String drinkname, int price, String companyname ){
        con = DBconnect.getConnection();

        try{
            sql="INSERT INTO DRINK(CATEGORY,DRINKSIZE ,DRINKNAME, PRICE, COMPANYNAME) VALUES(?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, category);
            pstmt.setString(2, drinksize);
            pstmt.setString(3,drinkname);
            pstmt.setInt(4, price);
            pstmt.setString(5,companyname);
            pstmt.executeUpdate();

        }catch (Exception e ){
            e.printStackTrace();
        }finally {
            DBconnect.close(con, pstmt);
        }
    }
    public List<Food> getFood(String category){
        con = DBconnect.getConnection();
        List<Food> list = null;

        try{
            sql = "SELECT * FROM FOOD WHERE category = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,category);

            rs = pstmt.executeQuery();

            if(rs.next()){

                list = new ArrayList<Food>();

                do {
                    Food food = new Food();
                    food.setCategory(rs.getString("category"));
                    food.setFoodname(rs.getString("foodname"));
                    food.setPrice(rs.getString("price"));
                    food.setScoreavg(rs.getString("scoreavg"));
                    list.add(food);
                }while(rs.next());
            }else{
                list = Collections.EMPTY_LIST;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBconnect.close(con, pstmt,rs);
        }

        return list;
    }
    public List<Drink> getDrink(String category){
        con = DBconnect.getConnection();
        List<Drink> list = null;

        try{
            sql = "SELECT * FROM DRINK ";
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();

            if(rs.next()){

                list = new ArrayList<Drink>();

                do {
                    Drink drink = new Drink();
                    drink.setCategory(rs.getString("category"));
                    drink.setDrinkname(rs.getString("drinkname"));
                    drink.setDrinksize(rs.getString("drinksize"));
                    drink.setPrice(rs.getString("price"));
                    list.add(drink);
                }while(rs.next());
            }else{
                list = Collections.EMPTY_LIST;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBconnect.close(con, pstmt,rs);
        }

        return list;
    }
    public List<Setmenu> getSetmenu(String category){

        con = DBconnect.getConnection();
        List<Setmenu> list = null;

        try{
            sql = " SELECT * FROM SETMENU WHERE CATEGORY = ?";
            pstmt.setString(1,category);
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if(rs.next()){
                list = new ArrayList<Setmenu>();
                do {
                    Setmenu setmenu = new Setmenu();
                    setmenu.setSetmenuid(rs.getString("setmenuid"));
                    setmenu.setSettype(rs.getString("settype"));
                    setmenu.setTotalprice(rs.getString("totalprice"));

                    sql = "SELECT * FROM SUBITEM WHERE SETMENUID  = " + setmenu.getSetmenuid()
                            +" AND TYPE = food";
                    pstmt2 = con.prepareStatement(sql);
                    rs2 = pstmt2.executeQuery();

                    if(rs2.next()){
                        do{
                            setmenu.setFoodname(rs2.getString("foodname"));
                        }while(rs2.next());
                    }

                    sql = "SELECT * FROM SUBITEM WHERE SETMENUID  = " + setmenu.getSetmenuid()
                            +" AND TYPE = drink";
                    pstmt2 = con.prepareStatement(sql);
                    rs2 = pstmt2.executeQuery();

                    if(rs2.next()){
                        do{
                            setmenu.setFoodname(rs2.getString("drinkname"));
                        }while(rs2.next());
                    }

                }while(rs.next());
            }else{
                list = Collections.EMPTY_LIST;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBconnect.close(con, pstmt,rs);
        }

        return list;

    }



}
