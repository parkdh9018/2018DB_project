package com.example.demo.DB.DAO;

import com.example.demo.DB.DBconnect;
import com.example.demo.DB.DTO.Food;
import com.example.demo.DB.DTO.Orderitem;
import com.example.demo.DB.DTO.Ordermenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderDAO {

    private Connection con = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private PreparedStatement pstmt2 = null;
    private ResultSet rs = null;
    private ResultSet rs2 = null;
    private String sql = null;
    private String ordercode = null;
    private DBconnect dBconnect = null;

    public OrderDAO(){
        dBconnect = new DBconnect();

    }

    public void insertOrder(String ordercode, String[] type, String[] name, int num, int totalprice){
        int cnt = 0;
        String typeAttribute = null;

        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());

        con = dBconnect.getConnection();
        try{
            sql="INSERT INTO ORDERMENU(ORDERCODE, ORDEREDDATE, TOTALPRICE) VALUES(?,?,?)";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, ordercode);
            pstmt.setDate(2, sqlDate);
            pstmt.setInt(3,totalprice);
            pstmt.executeUpdate();

            for(int i=0;i<num;i++) {

                if(type[i].equals("food"))
                    typeAttribute = "FOODNAME";
                else if((type[i].equals("drink")))
                    typeAttribute = "DRINKNAME";
                else if((type[i].equals("set")))
                    typeAttribute = "SETMENUID";
                else return;


                sql = "INSERT INTO SUBORDER(ORDERCODE, NUM, " + typeAttribute + ",TYPE,COUPON) VALUES(?,?,?,?,?)";
                pstmt2 = con.prepareStatement(sql);

                pstmt2.setString(1, ordercode);
                pstmt2.setInt(2, cnt++);
                if(name[i].contains("(쿠폰)")) {
                    name[i] = name[i].replace("(쿠폰)","");
                    pstmt2.setString(3, name[i]);
                    pstmt2.setString(5,"1");
                }else {
                    pstmt2.setString(3, name[i]);
                    pstmt2.setString(5,"0");
                }
                pstmt2.setString(4, type[i]);
                pstmt2.executeUpdate();
            }

        }catch(Exception e){
            e.printStackTrace();;
        }finally {
            DBconnect.close(con, pstmt);
        }
    }

    public List<Ordermenu> getAllOrder(){
        List<Ordermenu> list = null;
        dBconnect = new DBconnect();
        con = dBconnect.getConnection();
        try{


                sql = "SELECT * FROM ORDERMENU";
                pstmt = con.prepareStatement(sql);


            rs = pstmt.executeQuery();

            if(!rs.isBeforeFirst())
                System.out.println("no data");

            if(rs.next()){

                list = new ArrayList<Ordermenu>();

                do {
                    Ordermenu ordermenu = new Ordermenu();
                    ordermenu.setOrdercode(rs.getString("ordercode"));
                    ordermenu.setOrdereddate(rs.getDate("ordereddate"));
                    ordermenu.setTotalprice(rs.getString("totalprice"));
                    list.add(ordermenu);
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
