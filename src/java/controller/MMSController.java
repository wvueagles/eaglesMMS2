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
        Dbquery dbq = new Dbquery();
        List<Potholeallreport> pals = new ArrayList<Potholeallreport>();
        String action = request.getParameter("action");

        String workid = "", location = "", severity = "", comments = "", status = "", type = "";

        int id = 0;

        Connection conn = null;

        switch (action) {
            //TODO we propably do need an exit to clear data not sure what data yet
            //Space holders for us to work with
            case ("exit"):
                //clear clean up
                request.setAttribute("responseMessage", "Clears Properties.");
                request.getRequestDispatcher("/exit.jsp").forward(request, response);
                break;
            case ("potholes"):
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
                request.setAttribute("responseMessage", "person page");
                request.getRequestDispatcher("/person.jsp").forward(request, response);
                break;  // end person switch
            case ("repair"):  //go to the repair entry page   
                workid = request.getParameter("workid");

                try {
                    //This line is necessary for java to understand how to use the postgres jar file
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
                    //}
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
                    //}
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
                String responseMessage="Success";
                Potholeallreport pal =new Potholeallreport();
                try {
                     pals = Dbquery.selectPotholeallreports("SELECT * FROM Potholeallreport");                
                 //   pal= Dbquery.selectPotholeallreport("SELECT * FROM Potholeallreport LIMIT 1");
                } catch (SQLException ex) {
                    request.setAttribute("responseMessage", "Result error " + ex.getMessage());
                   // request.getRequestDispatcher("/report.jsp").forward(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(MMSController.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("responseMessage", "Result error " + ex.getMessage());
                   // request.getRequestDispatcher("/report.jsp").forward(request, response);
                }
                    if (pal == null) {
                    responseMessage = "No pothole data entered.";
                } else {  
                   responseMessage = "Number of Pothole records:" + pals.size();
               }
              if (pals == null) {
                    responseMessage = "No pothole data entered.";
                } else {
                    responseMessage = "Number of Pothole records:" + pals.size();
                }
              request.setAttribute("pals", pals);
              //  request.setAttribute("pal", pal);
                request.setAttribute("responseMessage", responseMessage);
                request.getRequestDispatcher("/report.jsp").forward(request, response);
                break;  // end report switch   
            default:
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
