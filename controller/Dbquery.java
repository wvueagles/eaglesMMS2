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

  /*  private static String jdbcUrl = "jdbc:postgresql://localhost:5432/eagles_mms";
    private static String user = "postgres";
    private static String password = "admin";
 */
 
  // Read RDS connection information from the environment
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private static ResultSetMetaData rsmd = null; 
	
    public Dbquery() {
    }

    //open connections
    public static void DbOpen() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(MMSController.getUrl(), MMSController.getUser(), MMSController.getPassword());
        stmt = conn.createStatement();
    }

    //close connections
    public static void DbClose() throws SQLException {
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

// Updates a record into person table, returns the successful  updated record in personreport structure to person.jsp    
    public static boolean updatePerson(String personkey, String firstname, String lastname,
            String address, String phone, String altphone, String email) throws ClassNotFoundException, SQLException, Exception {
        String querystmt;
        boolean isUpdated = true;
        try {
            DbOpen();
            querystmt
                    = "UPDATE Person SET firstname='" + firstname
                    + "', lastname='" + lastname
                    + "', address ='" + address
                    + "', phone ='" + phone
                    + "', alternatephone ='" + altphone
                    + "', emailaddress ='" + email
                    + "' where personkey='" + personkey + "'";
            if (stmt.executeUpdate(querystmt) ==0) isUpdated=false;  //update failed
            else {
                conn.commit();
            }
        } catch (SQLException | ClassNotFoundException e) {
        } finally {
            DbClose();
        }
        DbClose();
        return isUpdated;
    }

    /* check if person primary key is duplicate */
    public static boolean isPersonKeyDup(String personkey) throws ClassNotFoundException, SQLException, Exception {
        String querystmt;
        try {
            DbOpen();
            boolean isDup = true;
            querystmt = "select personkey from person where personkey='" + personkey + "'";
            rs = stmt.executeQuery(querystmt);     // get table view results
            if (rs.next() == false) {
                isDup = false; //no result found return null
            }
            DbClose();  //close connections
            return isDup;// duplicate primary person key insert fails
        } catch (SQLException | ClassNotFoundException e) {
        } finally {
            DbClose();
        }
        return false;
    }

    /* inserts a record into person table, returns the successful inserted record in personreport sructure 
    to person.jsp. If there is an exception a null structure is returned and the db and stmt connections are closed.
     */ 
    public static boolean insertPerson(String personkey, String firstname, String lastname,
            String address, String phone, String altphone, String email) throws ClassNotFoundException, SQLException, Exception {
        String querystmt;
        boolean isInserted=true;
        try {
            DbOpen();
            querystmt = "INSERT into Person VALUES ('" + personkey + "','" + firstname + "','" + lastname + "','" + address + "','" + phone + "','" + altphone + "','" + email + "')";
            if (stmt.executeUpdate(querystmt)==0) isInserted=false;  //Insert fail
            else  conn.commit(); 
        } catch (SQLException | ClassNotFoundException e) {
        } finally {
            DbClose();
        }
        DbClose();  //close connections
        return isInserted; // if per is null select personreport errored out.
    }

//returns a personreport row in class structure for person.jsp activity
// return a null if no person retrieved  
    public static Personreport selectPersonreport(String querystmt)
            throws SQLException, Exception {

        Personreport personreport = null;
        int tableColCount;
        try {
            DbOpen();
            rs = stmt.executeQuery(querystmt);     // get table view results
             if (rs.next() != false) {
                personreport = new Personreport();
                rsmd = rs.getMetaData();  //get result meta data
                tableColCount = rsmd.getColumnCount();
                do {           //should be only one row since searching on primary key
                    for (int i = 1; i <= tableColCount; i++) {     //loop for each column
                        // copy result set into table structure
                        personreport.setStringField(rsmd.getColumnName(i), rs.getString(i));
                    }
                }  while (rs.next());
            }
        } catch (SQLException e) {
        } finally {
            DbClose();
        }
        DbClose();
        return personreport;
    }

    //returns a list of personreport rows in class structure to view in report.jsp
    public static List<Personreport> selectPersonreports(String querystmt)
            throws SQLException, Exception {
        List<Personreport> personreports = new ArrayList<>();
        int tableColCount;
        try {
            DbOpen();
            rs = stmt.executeQuery(querystmt);
            rsmd = rs.getMetaData();   //get result meta data
            tableColCount = rsmd.getColumnCount();
            while (rs.next()) {           //loop for each row          
                Personreport personreport = new Personreport();
                for (int i = 1; i <= tableColCount; i++) {     //loop for each column
                    // copy result set into table structure
                    personreport.setStringField(rsmd.getColumnName(i), rs.getString(i));
                }
                personreports.add(personreport); //add table row to table list
            }
        } catch (SQLException | ClassNotFoundException e) {
        } finally {
            DbClose();  //close connections
        }
        DbClose();  //close connections
        return personreports;
    }
    //returns a list of pothole all tables & rows in class structure for report.jsp
    // This method can also be used to display data on pothole.jsp, repair.jsp and workorder.jsp

    public static List<Potholeallreport> selectPotholeallreports(String querystmt)
            throws SQLException, Exception {

        List<Potholeallreport> pals = new ArrayList<>();
        int tableColCount;
        try {
            DbOpen();
            rs = stmt.executeQuery(querystmt);
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
            DbClose();  //close connections
        }
        DbClose();  //close connections
        return pals;
    }


    /* inserts a record into pothole table, returns the successful inserted record in potholeallreport sructure 
    to pothole.jsp. If there is an exception a null structure is returned and the db and stmt connections are closed.
     */ 
    public static boolean insertPothole( String status,  String location, String severity, String reportingpersonkey, String comments )throws ClassNotFoundException, SQLException, Exception {
        String querystmt;
        boolean isInserted=true;
        try {
            DbOpen();
           querystmt = "insert into PotHoles (LOCATION, SEVERITY, REPORTINGPERSONKEY, COMMENTS) VALUES ('"+location+ "'," + severity + ",'" + reportingpersonkey + "','" + comments  + "')";
            if (stmt.executeUpdate(querystmt)==0) isInserted=false;  //Insert fail
            else  conn.commit(); 
        } catch (SQLException | ClassNotFoundException e) {
        } finally {
            DbClose();
        }
        DbClose();  //close connections
        return isInserted; // if per is null select personreport errored out.
    }

//returns a potholeallreport row in class structure for pothole.jsp repair.jsp workorder.jsp activity
// return a null if no potholeallreport retrieved  
    public static Potholeallreport selectPotholeallreport(String querystmt)
            throws SQLException, Exception {

        Potholeallreport potholeallreport = null;
        int tableColCount;
        try {
            DbOpen();
            rs = stmt.executeQuery(querystmt);     // get table view results
             if (rs.next() != false) {
                potholeallreport = new Potholeallreport();
                rsmd = rs.getMetaData();  //get result meta data
                tableColCount = rsmd.getColumnCount();
                do {           //should be only one row since searching on primary key
                    for (int i = 1; i <= tableColCount; i++) {     //loop for each column
                        // copy result set into table structure
                        potholeallreport.setStringField(rsmd.getColumnName(i), rs.getString(i));
                    }
                }  while (rs.next());
            }
        } catch (SQLException e) {
        } finally {
            DbClose();
        }
        DbClose();
        return potholeallreport;
    }
}
