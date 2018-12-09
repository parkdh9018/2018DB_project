package com.example.demo.DB.DAO;

import com.example.demo.DB.DBconnect;
import com.example.demo.DB.DTO.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public OrderDAO(){}

    public void insertOrder(String[] type, String[] name){
        con = DBconnect.getConnection();
        int cnt = 0;
        String typeAttribute = null;

        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyymmddhhmmss");
        ordercode = dayTime.format(new Date(time));

        SimpleDateFormat dayTime2 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String date = dayTime2.format(new Date(time));

        try{
            sql="INSERT INTO ORDERMENU(ORDERCODE, ORDEREDDATE) VALUES(?,?)";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, ordercode);
            pstmt.setString(2, date);
            pstmt.executeUpdate();

            for(int i=0;i<type.length;i++) {

                if(type[i].equals("food"))
                    typeAttribute = "FOODNAME";
                else if((type[i].equals("drink")))
                    typeAttribute = "DRINKNAME";
                else if((type[i].equals("setmenu")))
                    typeAttribute = "SETMENUID";


                sql = "INSERT INTO SUBORDER(ORDERCODE, NUM, " + typeAttribute + ",TYPE) VALUES(?,?,?,?)";
                pstmt2 = con.prepareStatement(sql);

                pstmt2.setString(1, ordercode);
                pstmt2.setInt(2, cnt++);
                pstmt2.setString(3, name[i]);
                pstmt2.setString(4, type[i]);
                pstmt2.executeUpdate();
            }

        }catch(Exception e){
            e.printStackTrace();;
        }finally {
            DBconnect.close(con, pstmt);
        }
    }

}
