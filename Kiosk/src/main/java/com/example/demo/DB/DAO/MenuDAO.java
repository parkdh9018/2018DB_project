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
    private  DBconnect dBconnect = null;

    public MenuDAO(){


    }

    public void insertFood(String category, String foodname, int price){
        dBconnect = new DBconnect();
        con = dBconnect.getConnection();
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
        dBconnect = new DBconnect();
        con = dBconnect.getConnection();
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
        List<Food> list = null;
        dBconnect = new DBconnect();
        con = dBconnect.getConnection();
        try{
            sql = "SELECT * FROM FOOD WHERE CATEGORY = RPAD(?,50)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,category);

            rs = pstmt.executeQuery();

            if(!rs.isBeforeFirst())
                System.out.println("no data");

            if(rs.next()){

                list = new ArrayList<Food>();

                do {
                    Food food = new Food();
                    food.setCategory(rs.getString("category"));
                    food.setFoodname(rs.getString("foodname"));
                    food.setPrice(rs.getInt("price"));
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
    public List<Drink> getDrink(){
        List<Drink> list = null;
        dBconnect = new DBconnect();
        con = dBconnect.getConnection();
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

        List<Setmenu> list = null;
        dBconnect = new DBconnect();
        con = dBconnect.getConnection();
        try{
            sql = "SELECT * FROM SETMENU WHERE CATEGORY = RPAD(?,50)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,category);
            rs = pstmt.executeQuery();

            if(rs.next()){
                list = new ArrayList<Setmenu>();
                do {
                    Setmenu setmenu = new Setmenu();
                    setmenu.setSetmenuid(rs.getString("setmenuid"));
                    setmenu.setCategory(rs.getString("category"));
                    setmenu.setTotalprice(rs.getInt("totalprice"));

                    sql = "SELECT * FROM SUBITEM WHERE SETMENUID  = RPAD(?,50) AND TYPE = 'food'";
                    pstmt2 = con.prepareStatement(sql);
                    pstmt2.setString(1,setmenu.getSetmenuid());
                    rs2 = pstmt2.executeQuery();

                    if(rs2.next()){
                        do{
                            setmenu.setFoodname(rs2.getString("foodname"));
                        }while(rs2.next());
                    }

                    sql = "SELECT * FROM SUBITEM WHERE SETMENUID  = RPAD(?,50) AND TYPE = 'drink'";
                    pstmt2 = con.prepareStatement(sql);
                    pstmt2.setString(1,setmenu.getSetmenuid());
                    rs2 = pstmt2.executeQuery();

                    if(rs2.next()){
                        do{
                            setmenu.setFoodname(rs2.getString("drinkname"));
                        }while(rs2.next());
                    }
                    list.add(setmenu);
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
