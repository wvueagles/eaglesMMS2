package controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 */
public class MMSController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String url = "jdbc:postgresql://localhost:5432/eagles_mms";
    private String user = "postgres";
    private String password = "admin";
    static String mmsrelease = "2.00";  // if time permits move this to servlet init to pull from DB 
    //if db connection and issue Home page should display Warning Message

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ServletController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MMSController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Potholeallreport> pals = new ArrayList<Potholeallreport>();
        List<Personreport> pers = new ArrayList<Personreport>();
        String action = request.getParameter("action");
        String workid = "", location = "", severity = "", comments = "", status = "", type = "";
        int id = 0;
        Connection conn = null;
        request.setAttribute("mmsrelease", mmsrelease);
        switch (action) {
            //TODO we propably do need an exit to clear data not sure what data yet
            //Space holders for us to work with
            case ("exit"):
                //EXIT ACTION
                //clear clean up
                request.setAttribute("responseMessage", "Clears Properties.");
                request.getRequestDispatcher("/exit.jsp").forward(request, response);
                break;
            case ("potholes"):
                //POTHOLES ACTION
                //get and validate input
                location = request.getParameter("location");
                severity = request.getParameter("severity");
                comments = request.getParameter("comments");

                if (!validateString(location) || !validateString(comments)) {
                    request.setAttribute("responseMessage", "Text may not contain special characters");
                    request.getRequestDispatcher("/potholes.jsp").forward(request, response);
                    break;
                }
                if (location == "") {
                    request.setAttribute("responseMessage", "Location Required");
                    request.getRequestDispatcher("/potholes.jsp").forward(request, response);
                    break;
                }
                if (!validateInt(severity)) {
                    request.setAttribute("responseMessage", "Severity must be an integer");
                    request.getRequestDispatcher("/potholes.jsp").forward(request, response);
                    break;
                }
                if (Integer.parseInt(severity) < 1 || Integer.parseInt(severity) > 5) {
                    request.setAttribute("responseMessage", "Severity must be an integer between 1 and 5");
                    request.getRequestDispatcher("/potholes.jsp").forward(request, response);
                    break;
                }

                try {
                    //This line is necessary for java to understand how to use the postgres jar file
                    //Put the postgres jdbc driver in your apache lib folder and add it to your project
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection(url, user, password);
                    String SQL = "INSERT INTO potholes(location, severity, comments) " + "VALUES(?,?,?)";

                    PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

                    pstmt.setString(1, location);
                    pstmt.setInt(2, Integer.parseInt(severity));
                    pstmt.setString(3, comments);

                    int affectedRows = pstmt.executeUpdate();
                    // check the affected rows 
                    if (affectedRows > 0) {
                        // get the ID back
                        try (ResultSet rs = pstmt.getGeneratedKeys()) {
                            if (rs.next()) {
                                id = rs.getInt(1);
                            }
                        } catch (SQLException ex) {
                            request.setAttribute("responseMessage", ex.getMessage());
                            request.getRequestDispatcher("/potholes.jsp").forward(request, response);
                        }
                    }

                } catch (Exception e) {
                    request.setAttribute("responseMessage", e.getMessage());
                    request.getRequestDispatcher("/potholes.jsp").forward(request, response);
                }
                request.setAttribute("responseMessage", "Successfully entered record");
                request.getRequestDispatcher("/potholes.jsp").forward(request, response);
                break;
            case ("person"):  //go to the person entry page 
                //PERSON ACTION
                try {
                    String responseMessage = "";
                    String searchkey = "";
                    Personreport per_set = new Personreport();
                    Personreport per = new Personreport();
                    per_set.setPersonkey(request.getParameter("per.personkey").toLowerCase());
                    per_set.setFirstname(request.getParameter("per.firstname"));
                    per_set.setLastname(request.getParameter("per.lastname"));
                    per_set.setAddress(request.getParameter("per.address"));
                    per_set.setPhone(request.getParameter("per.phone"));
                    per_set.setAlternatephone(request.getParameter("per.alternatephone"));
                    per_set.setEmailaddress(request.getParameter("per.emailaddress"));
                    per_set.setCreateddate(request.getParameter("per.createdate"));
                    per_set.setUpdateddate(request.getParameter("per.updateddate"));
                    //PERSON INSERT
                    if (null != request.getParameter("INSERT")) {
                        boolean isValued = true;
                        if ((per_set.getFirstname().length() < 2) || (per_set.getLastname().length() < 2)) {
                            isValued = false;
                            responseMessage = "Please correct: First and Last names are required with lengths at least 2 characters long.";
                        } else {
                            searchkey = per_set.getFirstname().substring(0, 1);
                            per_set.setPersonkey(searchkey.toLowerCase() + per_set.getLastname().toLowerCase());
                            if (Dbquery.isPersonKeyDup(per_set.getPersonkey())) {
                                isValued = false;
                                responseMessage = "User Name is duplicate key. Use Update mode not Insert mode.";
                            }
                        }
                        if (!isValued) {   //validation errors return to person page for corrections
                            request.setAttribute("responseMessage", responseMessage);
                            request.setAttribute("per", per_set);//return inserted record data
                            request.getRequestDispatcher("/person.jsp").forward(request, response);
                            break;
                        }  //person record pass validation insert into table
                        per = Dbquery.insertPerson(per_set.getPersonkey(), per_set.getFirstname(), per_set.getLastname(), per_set.getAddress(),
                                per_set.getPhone(), per_set.getAlternatephone(), per_set.getEmailaddress());
                        if (per == null) {  //db record insert issue 
                            request.setAttribute("responseMessage", "Person record not Inserted. Verify that User Name is not a duplicate and all data is entered correctly.");
                            request.setAttribute("per", per);  //return data for corrections
                        } else {  //person record successfully inserted into the db
                            request.setAttribute("responseMessage", "Person record added.");
                            request.setAttribute("per", per);//return inserted record data
                        }
                        request.getRequestDispatcher("/person.jsp").forward(request, response);
                        break;
                    } //PERSON SEARCH
                    else if (null != request.getParameter("SEARCH")) {
                        searchkey = request.getParameter("searchkey");
                        if (searchkey.length() < 2) {
                            request.setAttribute("responseMessage", "A valid search key must be entered.");
                            request.setAttribute("searchkey", searchkey);  //return searchkey for corrections
                            request.getRequestDispatcher("/person.jsp").forward(request, response);
                            break;
                        }
                        per = Dbquery.selectPersonreport("SELECT * FROM Personreport WHERE personkey = '" + searchkey + "'");
                        if (per == null) {
                            request.setAttribute("responseMessage", "Person record not found. Verify that User Name.");
                            request.setAttribute("searchkey", searchkey);  //return searchkey for corrections
                        } else {
                            request.setAttribute("responseMessage", "Person record retrieved for update.");
                            request.setAttribute("per", per);   //return retrieved person 
                        }
                        request.getRequestDispatcher("/person.jsp").forward(request, response);
                        break;
                    } //PERSON UPDATE
                    else if (null != request.getParameter("UPDATE")) {
                        if ((per_set.getPersonkey().length() < 2)
                                || (per_set.getFirstname().length() < 2) || (per_set.getLastname().length() < 2)) {
                            request.setAttribute("responseMessage", "Person Name must be longer than 2 characters. Verify that person data is entered correctly.");
                            request.setAttribute("per", per_set);  //return record for corrections
                        } else {
                            per = Dbquery.updatePerson(per_set.getPersonkey(), per_set.getFirstname(), per_set.getLastname(), per_set.getAddress(),
                                    per_set.getPhone(), per_set.getAlternatephone(), per_set.getEmailaddress());
                            if (per == null) {
                                request.setAttribute("responseMessage", "Person record not Updated. Verify that person data is entered correctly.");
                                request.setAttribute("per", per_set);  //return record for corrections
                            } else {
                                request.setAttribute("responseMessage", "Person record updated.");
                                request.setAttribute("per", per);   //return retrieved person 
                            }
                        }
                        request.getRequestDispatcher("/person.jsp").forward(request, response);
                        break;
                    } else if (null != request.getParameter("CLEAR")) {
                        request.setAttribute("responseMessage", "");
                        request.getRequestDispatcher("/person.jsp").forward(request, response);
                        break;
                    }
                } catch (SQLException ex) {
                    request.setAttribute("responseMessage", ex.getMessage());
                    request.getRequestDispatcher("/person.jsp").forward(request, response);
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(MMSController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case ("repair"):  //go to the repair entry page   
                workid = request.getParameter("workid");
                try {
                    //This line is necessary for java to undstand how to use the postgres jar file
                    //Put the postgres jdbc driver in your apache lib folder and add it to your project
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection(url, user, password);
                    Statement pstmt;
                    pstmt = conn.createStatement();
                    try (ResultSet rs = pstmt.executeQuery("SELECT * FROM repair WHERE workid = " + workid)) {
                        if (rs.next()) {
                            id = rs.getInt("workid");
                            status = rs.getString("status");
                            type = rs.getString("type");
                            comments = rs.getString("comments");
                        }
                    } catch (SQLException ex) {
                        request.setAttribute("responseMessage", "Result error " + ex.getMessage());
                        request.getRequestDispatcher("/repair.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    request.setAttribute("responseMessage", "Other Error " + e.getMessage());
                    request.getRequestDispatcher("/repair.jsp").forward(request, response);
                }
                request.setAttribute("workid", id);
                request.setAttribute("status", status);
                request.setAttribute("type", type);
                request.setAttribute("comments", comments);
                request.setAttribute("responseMessage", "Result " + id + " " + status + " " + type + " " + comments + " End");
                request.getRequestDispatcher("/repairedit.jsp").forward(request, response);
                break;  // end repair switch
            case ("repairedit"):
                //get and validate input
                workid = request.getParameter("workid");
                status = request.getParameter("status");
                type = request.getParameter("type");
                comments = request.getParameter("comments");
                try {
                    //This line is necessary for java to understand how to use the postgres jar file
                    //Put the postgres jdbc driver in your apache lib folder and add it to your project
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection(url, user, password);
                    Statement pstmt;
                    pstmt = conn.createStatement();
                    try {
                        pstmt.executeUpdate("UPDATE repair SET status = '" + status + "', type = '" + type + "', comments = '" + comments + "' WHERE workid = " + workid);
                    } catch (SQLException ex) {
                        request.setAttribute("responseMessage", "Result error " + ex.getMessage());
                        request.getRequestDispatcher("/repair.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    request.setAttribute("responseMessage", e.getMessage());
                    request.getRequestDispatcher("/repair.jsp").forward(request, response);
                }
                request.setAttribute("responseMessage", "Successfully updated record");
                request.getRequestDispatcher("/repair.jsp").forward(request, response);
                break;
            case ("workorder"):  //go to the repair entry page   
                workid = request.getParameter("workid");
                try {
                    //This line is necessary for java to understand how to use the postgres jar file
                    //Put the postgres jdbc driver in your apache lib folder and add it to your project
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection(url, user, password);
                    Statement pstmt;
                    pstmt = conn.createStatement();

                    try (ResultSet rs = pstmt.executeQuery("SELECT * FROM workorder WHERE workid = " + workid)) {
                        if (rs.next()) {
                            id = rs.getInt("workid");
                            status = rs.getString("status");
                            type = rs.getString("type");
                            comments = rs.getString("comments");
                        }
                    } catch (SQLException ex) {
                        request.setAttribute("responseMessage", "Result error " + ex.getMessage());
                        request.getRequestDispatcher("/workorder.jsp").forward(request, response);
                    }
                } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                    request.setAttribute("responseMessage", "Other Error " + e.getMessage());
                    request.getRequestDispatcher("/workorder.jsp").forward(request, response);
                }
                request.setAttribute("workid", id);
                request.setAttribute("status", status);
                request.setAttribute("type", type);
                request.setAttribute("comments", comments);
                request.setAttribute("responseMessage", "Result " + id + " " + status + " " + type + " " + comments + " End");
                request.getRequestDispatcher("/workorderedit.jsp").forward(request, response);
                break;  // end repair switch
            case ("workorderedit"):
                //get and validate input
                workid = request.getParameter("workid");
                status = request.getParameter("status");
                type = request.getParameter("type");
                comments = request.getParameter("comments");
                try {
                    //This line is necessary for java to understand how to use the postgres jar file
                    //Put the postgres jdbc driver in your apache lib folder and add it to your project
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection(url, user, password);
                    Statement pstmt;
                    pstmt = conn.createStatement();
                    try {
                        pstmt.executeUpdate("UPDATE workorder SET status = '" + status + "', type = '" + type + "', comments = '" + comments + "' WHERE workid = " + workid);
                    } catch (SQLException ex) {
                        request.setAttribute("responseMessage", "Result error " + ex.getMessage());

                        request.getRequestDispatcher("/workorder.jsp").forward(request, response);
                    }
                } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                    request.setAttribute("responseMessage", e.getMessage());
                    request.getRequestDispatcher("/workorder.jsp").forward(request, response);
                }
                request.setAttribute("responseMessage", "Successfully updated record");
                request.getRequestDispatcher("/workorder.jsp").forward(request, response);
                break;
            case ("report"):  //go to the workorder entry page   
                String responseMessage = "";   //report page response message to query
                String searchpot = "",
                 searchkey = "";  //report search key value
                String reportquery = "";  //report sql query value
                boolean isPothole = false;
                try {
                    if (null != request.getParameter("POTHOLE")) {
                        isPothole = true;
                        reportquery = "SELECT * FROM Potholeallreport";
                        pals = Dbquery.selectPotholeallreports(reportquery);
                    } else if (null != request.getParameter("SEARCHPID")) {
                        isPothole = true;
                        searchpot = request.getParameter("searchpot");
                        reportquery = "SELECT * FROM Potholeallreport WHERE workid = '" + searchpot + "'";
                        pals = Dbquery.selectPotholeallreports(reportquery);
                    } else if (null != request.getParameter("PERSON")) {
                        isPothole = false;
                        reportquery = "SELECT * FROM Personreport";
                        pers = Dbquery.selectPersonreports(reportquery);
                    } else if (null != request.getParameter("SEARCHUNAME")) {
                        isPothole = false;
                        searchkey = request.getParameter("searchkey");
                        reportquery = "SELECT * FROM Personreport WHERE personkey = '" + searchkey + "'";
                        pers = Dbquery.selectPersonreports(reportquery);
                    } else if (null != request.getParameter("CLEAR")) {
                        responseMessage = "";
                        searchpot = "0";
                        searchkey = "egee";
                        request.setAttribute("responseMessage", "");
                        request.getRequestDispatcher("/report.jsp").forward(request, response);
                        break;
                    }
                } catch (SQLException ex) {
                    request.setAttribute("responseMessage", "Result error " + ex.getMessage());
                    request.getRequestDispatcher("/report.jsp").forward(request, response);
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(MMSController.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("responseMessage", "Result error " + ex.getMessage());
                    request.getRequestDispatcher("/report.jsp").forward(request, response);
                    break;
                }
                if (isPothole) {
                    if (pals != null && pals.size() < 1) {
                        responseMessage = "Your query did not return any pothole records.";
                    } else {
                        responseMessage = "Pothole query record count:" + pals.size();
                    }
                    request.setAttribute("pals", pals);
                } else {
                    if (pers != null && pers.size() < 1) {
                        responseMessage = "Your query did not return any person records.";
                    } else {
                        responseMessage = "Person query record count:" + pers.size();
                    }
                    request.setAttribute("pers", pers);
                }
                request.setAttribute("responseMessage", responseMessage);
                request.getRequestDispatcher("/report.jsp").forward(request, response);
                break;  // end report switch   
            default:
                request.getRequestDispatcher("/index.xhtml").forward(request, response);
        }
    }
    private static String realPath;

    void setRealPath() {
        realPath = getServletContext().getRealPath("");
    }

    private static Boolean validateString(String input) {
        Pattern p = Pattern.compile("[^a-z0-9,. ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input);
        return !(m.find());
    }

    private static Boolean validateInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String getFilePath() {
        return realPath;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
