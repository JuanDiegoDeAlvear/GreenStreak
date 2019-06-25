package com.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DbAdapter {

    /* 01 -------------------- database variables ------------------- */

    private static DbAdapter dbAdapter_instance = null;

    Connection conn = null;
    ResultSet rs = null;
    Statement stnt = null;

    /* 02 ------------------------ Variables ------------------------ */

    private final String jdburl = "jdbc:postgresql://localhost:5432/GreenStreak";
    private final String username = "postgres";



    /* 03 ------------------------ Constructor ---------------------- */

    /**
     * Constructor.
     */
    private DbAdapter() {

        //Connect to the database
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the database password: ");
        String password = input.nextLine();

        try {
            //Connect to the database
            conn = DriverManager.getConnection(jdburl, username, password);

            //Print success
            System.out.println("database connection stablished");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }




    /*-----------------------  Getters and Setters -------------------*/
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }


    /**
     * Getter.
     *
     * @return Returns an instance.
     */
    public  static DbAdapter getInstance() {
        if (dbAdapter_instance == null) {
            dbAdapter_instance = new DbAdapter();
        }
        return dbAdapter_instance;
    }

    public static void setDbAdapter_instance(DbAdapter dbAdapterinstance) {
        DbAdapter.dbAdapter_instance = dbAdapterinstance;
    }

    /**.
     * method to disconnect from the database com.server
     */
    public void disconnect() {

        try {

            if (stnt != null) {
                stnt.close();
            }

            if (rs != null) {
                rs.close();
            }

            if (conn != null) {
                conn.close();
            }

            System.out.println("database connection closed");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //All necessary basic sql commands


    /**.
     * The current method is a model of a querying method.
     *
     * @param query  as a String
     * @return resultset
     */
    public ResultSet query(String query) {

        try {

            PreparedStatement stnt = conn.prepareStatement(query);
            System.out.println("Connected to PostgreSQL database!");
            System.out.println("Reading records...");
            ResultSet resultSet = stnt.executeQuery();

            return resultSet;

        } catch (SQLException e) {

            e.printStackTrace();
            return null;

        }
    }

    /**
     * Method queries database.
     *
     * @param st as a prepared statement.
     * @return  resultSet.
     * @throws SQLException Throws an sql exception.
     */
    public ResultSet query(PreparedStatement st) throws SQLException {
        return st.executeQuery();
    }

    //    /**
    //     * change name of method.
    //     *
    //     * @param st as prepared statment.
    //     * @return  int.
    //     * @throws SQLException Throws an exception.
    //     */
    //    public int Update(PreparedStatement st) throws SQLException {
    //        return st.executeUpdate();
    //    }


    //
    //    public Profile getProfile(String username){
    //        //query for values
    //        return new Profile(result.username, result.age, result.hasCar)
    //    }
    //
    //    /**
    //     * Hacer un metodo query para datamanager class
    //     */

}
