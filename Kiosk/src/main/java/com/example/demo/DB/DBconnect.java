package com.example.demo.DB;


import java.sql.*;

public class DBconnect {
    private static String url = "jdbc:oracle:thin:@210.94.199.20:1521:DBLAB";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String username = "ST2013113082";
    private static String password = "ST2013113082";
    private static Connection con;
    private static String urlstring;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(urlstring, username, password);
            } catch (SQLException ex) {
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found.");
        }
        return con;
    }
    public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        try {
            try {
                if(rs!=null) { rs.close(); rs=null;}
            }catch(Exception e) {}

            try {
                if(pstmt!=null) { pstmt.close(); pstmt=null; }
            }catch(Exception e) {}

            try {
                if(con!=null) { con.close(); con=null; }
            }catch(Exception e) {}
        }catch(Exception e) {}
    }
    public static void close(Connection con, PreparedStatement pstmt) {
        try {
            try {
                if(pstmt!=null) { pstmt.close(); pstmt=null; }
            }catch(Exception e) {}

            try {
                if(con!=null) { con.close(); con=null; }
            }catch(Exception e) {}
        }catch(Exception e) {}
    }
}
