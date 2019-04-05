/*
 * DB Connections class; central location to connect and close to data services
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Dbquery {

    private static String url = "jdbc:postgresql://localhost:5432/eagles_mms";
    private static String user = "postgres";
    private static String password = "admin";
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private static ResultSetMetaData rsmd = null;

    public Dbquery() {
    }

    //returns a list of personreport rows in class structure
    public static List<Personreport> selectPersonreports(String querystmt)
            throws SQLException, Exception {
       
        List<Personreport> personreports = new ArrayList<>();
        int tableColCount;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(querystmt);     // get table view results
            rsmd = rs.getMetaData();   //get result meta data
            tableColCount = rsmd.getColumnCount();
            while (rs.next()) {           //loop for each row          
                 Personreport personreport = new Personreport();
                 for (int i = 1; i < tableColCount; i++) {     //loop for each column
                    // copy result set into table structure
                    personreport.setStringField(rsmd.getColumnName(i), rs.getString(i));
                }
                personreports.add(personreport); //add table row to table list
            }
        } catch (SQLException | ClassNotFoundException e) {
        } finally {
            // Close connections
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return personreports;
    }
 //returns a list of pothole all tables & rows in class structure
    public static List<Potholeallreport> selectPotholeallreports(String querystmt)
            throws SQLException, Exception {
        
        List<Potholeallreport> pals = new ArrayList<>();
        int tableColCount;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(querystmt);     // get table view results
            rsmd = rs.getMetaData();   //get result meta data
            tableColCount = rsmd.getColumnCount();
            while (rs.next()) {           //loop for each row 
                Potholeallreport pal = new Potholeallreport();
                for (int i = 1; i < tableColCount; i++) {     //loop for each column
                    // copy result set into table structure
                    pal.setStringField(rsmd.getColumnName(i), rs.getString(i));
                }
                pals.add(pal); //add table row to table list
            }
        } catch (SQLException | ClassNotFoundException e) {
        } finally {
            // Close connections
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return pals;
    }
//returns a list of pothole all tables & rows in class structure
    public static Potholeallreport selectPotholeallreport(String querystmt)
            throws SQLException, Exception {
        Potholeallreport pal = new Potholeallreport();
        int tableColCount;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(querystmt);     // get table view results
            rsmd = rs.getMetaData();   //get result meta data
            tableColCount = rsmd.getColumnCount();
            while (rs.next()) {           //loop for each row          
                for (int i = 1; i < tableColCount; i++) {     //loop for each column
                    // copy result set into table structure
                    pal.setStringField(rsmd.getColumnName(i), rs.getString(i));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
        } finally {
            // Close connections
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return pal;
    }

}