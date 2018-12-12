package com.example.demo.DB.DAO;

import com.example.demo.DB.DBconnect;
import com.example.demo.DB.DTO.Store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class StoreDAO {

    private Connection con = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private PreparedStatement pstmt2 = null;
    private ResultSet rs = null;
    private ResultSet rs2 = null;
    private String sql = null;
    private String ordercode = null;
    private DBconnect dBconnect = null;

    public StoreDAO(){
        dBconnect = new DBconnect();

    }

    public Store getRandomStore(){

        Store store = new Store();

        con = dBconnect.getConnection();
        try{
            sql="SELECT ADDRESS, PHONE, CRN, NAME\n" +
                    "FROM STORE, CLERK\n" +
                    "WHERE STORE.STOREID = RPAD(?,50) AND STORE.STOREID = CLERK.STOREID AND POSITION='mgr'";
            pstmt = con.prepareStatement(sql);

            Random generator = new Random();

            int num = (generator.nextInt(3) + 1) * 10;

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,String.valueOf(num));

            rs = pstmt.executeQuery();


            if(rs.next()){
                do{
                    store.setAddress(rs.getString("ADDRESS"));
                    store.setManager(rs.getString("NAME"));
                    store.setPhone(rs.getString("PHONE"));
                    store.setCrn(rs.getString("CRN"));
                }while(rs.next());
            }

        }catch(Exception e){
            e.printStackTrace();;
        }finally {
            DBconnect.close(con, pstmt);
        }

        return store;


    }
}
