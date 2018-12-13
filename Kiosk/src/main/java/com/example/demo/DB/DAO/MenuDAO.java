package com.example.demo.DB.DAO;

import com.example.demo.DB.DBconnect;
import com.example.demo.DB.DTO.*;

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
            if(category.equals("전체")) {
                sql = "SELECT * FROM FOOD";
                pstmt = con.prepareStatement(sql);
            }
            else {
                sql = "SELECT * FROM FOOD WHERE CATEGORY = RPAD(?,50)";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1,category);
            }


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
                    food.setImageurl(rs.getString("foodimage"));
                    food.setSoldout(rs.getString("soldout"));
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
                    drink.setDrinkname(rs.getString("drinkname"));
                    drink.setPrice(rs.getInt("price"));
                    drink.setImageurl(rs.getString("drinkimage"));
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
    public List<Allergy> getAllergyList(){
        List<Allergy> list = null;
        dBconnect = new DBconnect();
        con = dBconnect.getConnection();
        try{
            sql = "SELECT FOOD.FOODNAME, listagg(INGREDIENT.INGREDIENTNAME, ', ') within group(order by INGREDIENT.INGREDIENTNAME ) AS INGREDIENT\n" +
                    "FROM FOOD ,FOOD_INGREDIENT,INGREDIENT\n" +
                    "WHERE FOOD.FOODNAME = FOOD_INGREDIENT.FOODNAME AND\n" +
                    "       INGREDIENT.INGREDIENTNAME = FOOD_INGREDIENT.INGREDIENTNAME AND\n" +
                    "       ALLERGY = 1\n" +
                    "group by FOOD.FOODNAME";
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();

            if(rs.next()){

                list = new ArrayList<Allergy>();
                do {
                    Allergy allergy = new Allergy();
                    allergy.setFood(rs.getString("foodname"));
                    allergy.setIngredient(rs.getString("ingredient"));
                    list.add(allergy);
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

            if(category.equals("전체")) {
                sql = "SELECT * FROM SETMENU";
                pstmt = con.prepareStatement(sql);
            }
            else{
                sql = "SELECT * FROM SETMENU WHERE CATEGORY = RPAD(?,50)";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, category);
            }
            rs = pstmt.executeQuery();

            if(rs.next()){
                list = new ArrayList<Setmenu>();
                do {
                    Setmenu setmenu = new Setmenu();
                    setmenu.setSetmenuid(rs.getString("setmenuid"));
                    setmenu.setCategory(rs.getString("category"));
                    setmenu.setTotalprice(rs.getInt("totalprice"));
                    setmenu.setImageurl(rs.getString("setimage"));
                    setmenu.setSoldout(rs.getString("soldout"));

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
    public Coupon getCoupon(String couponId){
        Coupon coupon = new Coupon();
        dBconnect = new DBconnect();
        con = dBconnect.getConnection();
        try{
            sql = "(SELECT SALESITEM.FOODNAME as name, SALERATE, FOOD.PRICE AS PRICE, TYPE\n" +
                    " FROM SALESITEM, FOOD\n" +
                    " WHERE COUPONID=RPAD(?,50) AND TYPE='food' AND USED='0' AND EXPIRATIONDATE >= TO_DATE(SYSDATE, 'YYYY-MM-DD HH24:MI:SS')\n" +
                    " AND SALESITEM.FOODNAME = FOOD.FOODNAME )\n" +
                    "UNION\n" +
                    "(SELECT SALESITEM.SETMENUID as name, SALERATE, SETMENU.TOTALPRICE AS PRICE, TYPE\n" +
                    " FROM SALESITEM, SETMENU\n" +
                    " WHERE COUPONID=RPAD(?,50) AND TYPE='set' AND USED='0'AND EXPIRATIONDATE >= TO_DATE (SYSDATE, 'YYYY-MM-DD HH24:MI:SS')\n" +
                    "AND SALESITEM.SETMENUID = SETMENU.SETMENUID)\n" +
                    "UNION\n" +
                    "(SELECT SALESITEM.DRINKNAME as name, SALERATE, DRINK.PRICE AS PRICE, TYPE\n" +
                    " FROM SALESITEM, DRINK\n" +
                    " WHERE COUPONID=RPAD(?,50) AND TYPE='drink' AND USED='0'AND EXPIRATIONDATE >= TO_DATE (SYSDATE, 'YYYY-MM-DD HH24:MI:SS')\n" +
                    "AND SALESITEM.DRINKNAME = DRINK.DRINKNAME)";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,couponId);
            pstmt.setString(2,couponId);
            pstmt.setString(3,couponId);

            rs = pstmt.executeQuery();

            if(rs.next()){
                do{
                    coupon.setName(rs.getString("name"));
                    coupon.setSalesRate(rs.getInt("SALERATE"));
                    coupon.setPrice(rs.getInt("price"));
                    coupon.setType(rs.getString("type"));
                }while(rs.next());
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBconnect.close(con, pstmt,rs);
        }

        return coupon;
    }

    public void modifySoldout(String name, String type){

        dBconnect = new DBconnect();
        con = dBconnect.getConnection();
        try{

            if("food".equals(type))
                sql = "update FOOD set SOLDOUT = CASE WHEN SOLDOUT = 1 THEN 0\n" +
                        "                                  WHEN SOLDOUT = 0 THEN 1 END\n" +
                        "where FOODNAME = RPAD(?,50)";
            else if("set".equals(type))
                sql = "update FOOD set SOLDOUT = CASE WHEN SOLDOUT = 1 THEN 0\n" +
                        "                                  WHEN SOLDOUT = 0 THEN 1 END\n" +
                        "where FOODNAME = RPAD(?,50)";
            else if("drink".equals(type))
                sql = "update FOOD set SOLDOUT = CASE WHEN SOLDOUT = 1 THEN 0\n" +
                        "                                  WHEN SOLDOUT = 0 THEN 1 END\n" +
                        "where FOODNAME = RPAD(?,50)";

            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.executeUpdate();


        }catch (Exception e ){
            e.printStackTrace();
        }finally {
            DBconnect.close(con, pstmt);
        }
    }

    public List<Clerk> getClerk(){
        List<Clerk> list = null;
        dBconnect = new DBconnect();
        con = dBconnect.getConnection();
        try{
            sql = "SELECT * FROM CLERK ";
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();

            if(rs.next()){

                list = new ArrayList<>();

                do {
                    Clerk clerk = new Clerk();
                    clerk.setPosition(rs.getString("position"));
                    clerk.setName(rs.getString("name"));
                    clerk.setEmpno(rs.getString("empno"));
                    list.add(clerk);
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
