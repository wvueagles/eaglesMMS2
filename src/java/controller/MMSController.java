package controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import java.util.Arrays;

/**
 *
 *
 */
public class MMSController extends HttpServlet {

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        MMSController.url = url;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        MMSController.user = user;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        MMSController.password = password;
    }

    public static String getMmsrelease() {
        return mmsrelease;
    }

    public static void setMmsrelease(String mmsrelease) {
        MMSController.mmsrelease = mmsrelease;
    }

    public static String getRealPath() {
        return realPath;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static void setRealPath(String realPath) {
        MMSController.realPath = realPath;
    }

    private static String url;
    private static String user;
    private static String password;
    private static Boolean isViewOnly;
    static String mmsrelease = "3.00";  // if time permits move this to servlet init to pull from DB 
    //if db connection and issue Home page should display Warning Message

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // Read RDS connection information from the environment
        if (System.getProperty("RDS_HOSTNAME") == null) {
            url = "jdbc:postgresql://localhost:5432/eagles_mms";
            user = "postgres";
            password = "admin";
        } else {
            String hostname = System.getProperty("RDS_HOSTNAME");
            String dbName = System.getProperty("RDS_DB_NAME");
            user = System.getProperty("RDS_USERNAME");
            password = System.getProperty("RDS_PASSWORD");
            String port = System.getProperty("RDS_PORT");
            url = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName;
        }
    }

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
                try {
                    String responseMessage = "";
                    String searchkey = "";
                    int intSearchkey = 0;
                    Potholeallreport pot_set = new Potholeallreport();
                    Potholeallreport pot = new Potholeallreport();
                    pot_set.setWorkid(request.getParameter("pot.workid").toLowerCase());
                    pot_set.setPotholelocation(request.getParameter("pot.potholelocation"));
                    pot_set.setSeverity(request.getParameter("pot.severity"));
                    pot_set.setPotholestatus(request.getParameter("pot.potholestatus"));
                    pot_set.setReportingpersonkey(request.getParameter("pot.reportingpersonkey"));
                    pot_set.setPotholecomments(request.getParameter("pot.potholecomments"));
                    pot_set.setCreateddate(request.getParameter("pot.createdate"));
                    pot_set.setPotholeupdateddate(request.getParameter("pot.potholeupdateddate"));
                    pot_set.setPotholeclosedtime(request.getParameter("pot.potholeclosedtime"));
                    //POTHOLE INSERT
                    if (null != request.getParameter("INSERT")) {
                        if (null != pot_set.getWorkid() && pot_set.getWorkid().trim().length() > 1 ) {
                            request.setAttribute("responseMessage", "This Pothole Work ID is a duplicate. Please press CLEAR and re-enter pothole data.");
                            request.setAttribute("pot", pot_set);  //return record for corrections
                        } else if (null != pot_set.getPotholelocation() && pot_set.getPotholelocation().length() <4) {
                            request.setAttribute("responseMessage", "Pothole Location is required and must contain a street address.");
                            request.setAttribute("pot", pot_set);  //return record for corrections
                        } else {
                            if (null != pot_set.getSeverity() && pot_set.getSeverity().length() < 1) {
                                pot_set.setSeverity("1");
                            }
                            intSearchkey = Dbquery.insertPothole(pot_set.getPotholelocation(), pot_set.getSeverity(), pot_set.getReportingpersonkey(), pot_set.getPotholecomments());
                            if (intSearchkey > 0) {   //person record successfully update in db
                                //try to retrtrieve for viewing
                                pot = Dbquery.selectPotholeallreport("SELECT * FROM Potholeallreport WHERE workid = '" + intSearchkey + "'");
                                request.setAttribute("responseMessage", "Pothole record inserted");
                                request.setAttribute("pot", pot);   //return retrieved person   
                            } else {
                                request.setAttribute("responseMessage", "Pothole record not inserted. Verify that repair data is entered correctly.");
                                request.setAttribute("pot", pot_set);  //return record for corrections
                            }
                        }
                        request.getRequestDispatcher("/potholes.jsp").forward(request, response);
                        break;
                    }//POTHOLE SEARCH
                    else if (null != request.getParameter("SEARCH")) {
                        isViewOnly = false;
                        searchkey = request.getParameter("searchkey");
                        if (null !=searchkey && (searchkey.trim()).length() < 1) {
                            request.setAttribute("responseMessage", "A valid search key must be entered.");
                            request.setAttribute("searchkey", searchkey);  //return searchkey for corrections
                            request.getRequestDispatcher("/potholes.jsp").forward(request, response);
                            break;
                        } else searchkey=searchkey.trim();
                        pot = Dbquery.selectPotholeallreport("SELECT * FROM Potholeallreport WHERE workid = '" + searchkey + "'");
                        if (pot == null) {
                            request.setAttribute("responseMessage", "Pothole record not found. Verify Search Work ID.");
                            request.setAttribute("searchkey", searchkey);  //return searchkey for corrections
                        } else if (pot.getPotholestatus().equals("CLOSE")) {
                            isViewOnly = true;
                            request.setAttribute("responseMessage", "Pothole record  is closed. User may view only.");
                            request.setAttribute("pot", pot);   //return retrieved person 
                        } else {
                            request.setAttribute("responseMessage", "Pothole record retrieved for update.");
                            request.setAttribute("pot", pot);   //return retrieved person 
                        }
                        request.getRequestDispatcher("/potholes.jsp").forward(request, response);
                        break;
                    } //POTHOLE UPDATE
                    else if (null != request.getParameter("UPDATE")) {
                        if (pot_set.getWorkid().length() < 1) {
                            request.setAttribute("responseMessage", "You must retrieve a Pothole record using SEARCH before pressing UPDATE.");
                        } else if (isViewOnly) {
                            request.setAttribute("responseMessage", "Pothole is closed, no changes can be made.");
                            request.setAttribute("pot", pot_set);  //return record to still view                        
                        } else if (null != pot_set.getSeverity() && pot_set.getSeverity().length() < 1) {
                            request.setAttribute("responseMessage", "Severity status must be an integer between 1 and 5");
                            request.setAttribute("pot", pot_set);  //return record for corrections
                        } else {
                            searchkey = pot_set.getWorkid();
                            if (Dbquery.updatePothole(pot_set.getWorkid(), pot_set.getReportingpersonkey(), pot_set.getPotholelocation(), pot_set.getSeverity(), pot_set.getPotholecomments())) {
                                //person record successfully update in db
                                //try to retrtrieve for viewing
                                pot = Dbquery.selectPotholeallreport("SELECT * FROM Potholeallreport WHERE workid = '" + searchkey + "'");
                                request.setAttribute("responseMessage", "Pothole record updated");
                                request.setAttribute("pot", pot);   //return retrieved person   
                            } else {
                                request.setAttribute("responseMessage", "Pothole record not Updated. Verify that repair data is entered correctly.");
                                request.setAttribute("pot", pot_set);  //return record for corrections
                            }
                        }
                        request.getRequestDispatcher("/potholes.jsp").forward(request, response);
                        break;
                    } //POTHOLE CLEAR
                    else if (null != request.getParameter("CLEAR")) {
                        request.setAttribute("responseMessage", " ");
                        request.setAttribute("searchkey", " ");
                        request.getRequestDispatcher("/potholes.jsp").forward(request, response);
                        break;
                    }
                } catch (SQLException ex) {
                    request.setAttribute("responseMessage", ex.getMessage());
                    request.getRequestDispatcher("/potholes.jsp").forward(request, response);
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(MMSController.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                            searchkey = per_set.getFirstname().substring(0, 1).toLowerCase();
                            per_set.setPersonkey(searchkey + per_set.getLastname().toLowerCase());
                            if (Dbquery.isPersonKeyDup(per_set.getPersonkey())) {
                                isValued = false;
                                responseMessage = "User Name is duplicate key. Use Update mode not Add mode.";
                            }
                        }
                        if (!isValued) {   //validation errors return to person page for corrections
                            request.setAttribute("responseMessage", responseMessage);
                            request.setAttribute("per", per_set);//return inserted record data
                            request.getRequestDispatcher("/person.jsp").forward(request, response);
                            break;
                        }  //person record pass validation insert into table
                        searchkey = per_set.getPersonkey();
                        if (Dbquery.insertPerson(per_set.getPersonkey(), per_set.getFirstname(), per_set.getLastname(), per_set.getAddress(),
                                per_set.getPhone(), per_set.getAlternatephone(), per_set.getEmailaddress())) {
                            //person record successfully inserted into the db
                            //try to retrtrieve for viewing
                            per = Dbquery.selectPersonreport("SELECT * FROM Personreport WHERE personkey = '" + searchkey + "'");
                            request.setAttribute("responseMessage", "Person record added.");
                            request.setAttribute("per", per);//return inserted record data    
                        } else {  //db record insert issue 
                            request.setAttribute("responseMessage", "Person record not Added. Verify User Name is not a duplicate and all data is entered correctly.");
                            request.setAttribute("per", per_set);  //return data for corrections
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
                            request.setAttribute("responseMessage", "Person record not found. Verify Search User Name.");
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
                            searchkey = per_set.getPersonkey();
                            if (Dbquery.updatePerson(per_set.getPersonkey(), per_set.getFirstname(), per_set.getLastname(), per_set.getAddress(),
                                    per_set.getPhone(), per_set.getAlternatephone(), per_set.getEmailaddress())) {
                                //person record successfully update in db
                                //try to retrtrieve for viewing
                                per = Dbquery.selectPersonreport("SELECT * FROM Personreport WHERE personkey = '" + searchkey + "'");
                                request.setAttribute("responseMessage", "Person record updated");
                                request.setAttribute("per", per);   //return retrieved person   
                            } else {
                                request.setAttribute("responseMessage", "Person record not Updated. Verify that person data is entered correctly.");
                                request.setAttribute("per", per_set);  //return record for corrections
                            }
                        }
                        request.getRequestDispatcher("/person.jsp").forward(request, response);
                        break;
                    } //PERSON CLEAR
                    else if (null != request.getParameter("CLEAR")) {
                        request.setAttribute("responseMessage", " ");
                        request.setAttribute("searchkey", " ");
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
                try {
                    String responseMessage = "";
                    String searchkey = "";
                    Potholeallreport pot_set = new Potholeallreport();
                    Potholeallreport pot = new Potholeallreport();
                    String validStatus = "REPAIRED FAIL PASS WIP";
                    pot_set.setWorkid(request.getParameter("pot.workid"));
                    pot_set.setRepairstatus(request.getParameter("pot.repairstatus"));
                    pot_set.setRepairordertype(request.getParameter("pot.repairordertype"));
                    pot_set.setRepaircomments(request.getParameter("pot.repaircomments"));
                    pot_set.setCreateddate(request.getParameter("pot.createdate"));
                    pot_set.setRepairupdateddate(request.getParameter("pot.repairupdateddate"));
                    pot_set.setPotholeclosedtime(request.getParameter("pot.potholeclosedtime"));
                    //REPAIR SEARCH
                    if (null != request.getParameter("SEARCH")) {
                        isViewOnly = false;
                        searchkey = request.getParameter("searchkey");
                        if ((searchkey).length() < 1) {
                            request.setAttribute("responseMessage", "A valid search key must be entered.");
                            request.setAttribute("searchkey", searchkey);  //return searchkey for corrections
                            request.getRequestDispatcher("/repair.jsp").forward(request, response);
                            break;
                        }
                        pot = Dbquery.selectPotholeallreport("SELECT * FROM Potholeallreport WHERE workid = '" + searchkey + "'");
                        if (pot == null) {
                            request.setAttribute("responseMessage", "Repair record not found. Verify Search Work ID.");
                            request.setAttribute("searchkey", searchkey);  //return searchkey for corrections
                        } else {
                            if (pot.getPotholestatus().equals("CLOSE")) {
                                isViewOnly = true;
                                request.setAttribute("responseMessage", "Repair record  is a closed. User may view only.");
                            } else if (pot.getWorkorderstatus().equals("HOLD")) {
                                isViewOnly = true;
                                request.setAttribute("responseMessage", "Repair work is on HOLD. User may view only.");
                            } else if (!pot.getWorkorderstatus().equals("APPROVE")) {
                                isViewOnly = true;
                                request.setAttribute("responseMessage", "Repair work order is still waiting approval. User may view only.");
                            } else {
                                isViewOnly = false;
                                request.setAttribute("responseMessage", "Repair record retrieved for update.");
                            }
                        }
                        request.setAttribute("pot", pot);   //return retrieved person 
                        request.getRequestDispatcher("/repair.jsp").forward(request, response);
                        break;
                    } //REPAIR UPDATE
                    else if (null != request.getParameter("UPDATE")) {
                        if (pot_set.getWorkid().length() < 1) {
                            request.setAttribute("responseMessage", "You must retrieve a Repair record using SEARCH before pressing UPDATE.");
                        } else if (isViewOnly) {
                            request.setAttribute("responseMessage", "This Repair record is view only.");
                            request.setAttribute("pot", pot_set);  //return record to still view   

                        } else if (null !=pot_set.getRepairstatus() && !validStatus.contains(pot_set.getRepairstatus().toUpperCase().trim())) {
                            request.setAttribute("responseMessage", "Repair status must be REPAIRED, FAIL, PASS, or WIP.");
                            request.setAttribute("pot", pot_set);  //return record for corrections                       
                        } else {
                            searchkey = pot_set.getWorkid().trim();
                            pot_set.setRepairstatus(pot_set.getRepairstatus().toUpperCase().trim());
                            // to prevent looping when updating system auto status
                            if (pot_set.getRepairstatus().equals("REPAIRED")) pot_set.setRepairstatus("SYSREPAIRED");
                            if (Dbquery.updateRepair(pot_set.getWorkid(), pot_set.getRepairstatus(), pot_set.getRepaircomments())) {
                                //person record successfully update in db
                                //try to retrtrieve for viewing
                                pot = Dbquery.selectPotholeallreport("SELECT * FROM Potholeallreport WHERE workid = '" + searchkey + "'");
                                request.setAttribute("responseMessage", "Repair record updated");
                                request.setAttribute("pot", pot);   //return retrieved person   
                            } else {
                                request.setAttribute("responseMessage", "Repair record not Updated. Verify that repair data is entered correctly.");
                                request.setAttribute("pot", pot_set);  //return record for corrections
                            }
                        }
                        request.getRequestDispatcher("/repair.jsp").forward(request, response);
                        break;
                    } //REPAIR CLEAR
                    else if (null != request.getParameter("CLEAR")) {
                        request.setAttribute("responseMessage", " ");
                        request.setAttribute("searchkey", " ");
                        request.getRequestDispatcher("/repair.jsp").forward(request, response);
                        break;
                    }
                } catch (SQLException ex) {
                    request.setAttribute("responseMessage", ex.getMessage());
                    request.getRequestDispatcher("/repair.jsp").forward(request, response);
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(MMSController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case ("workorder"):  //go to the repair entry page   
                try {
                    String responseMessage = "";
                    String searchkey = "";
                    Potholeallreport pot_set = new Potholeallreport();
                    Potholeallreport pot = new Potholeallreport();
                    String validStatus = "DENY APPROVE HOLD CLOSE";
                    pot_set.setWorkid(request.getParameter("pot.workid"));
                    pot_set.setWorkorderstatus(request.getParameter("pot.workorderstatus"));
                    pot_set.setWorkordertype(request.getParameter("pot.workordertype"));
                    pot_set.setWorkordercomments(request.getParameter("pot.workordercomments"));
                    pot_set.setCreateddate(request.getParameter("pot.createdate"));
                    pot_set.setWorkorderupdateddae(request.getParameter("pot.workorderupdateddae"));
                    pot_set.setPotholeclosedtime(request.getParameter("pot.potholeclosedtime"));
                    //WORKORDER SEARCH
                    if (null != request.getParameter("SEARCH")) {
                        searchkey = request.getParameter("searchkey");                        
                        if (null != searchkey && (searchkey.trim().length() < 1)) {
                            request.setAttribute("responseMessage", "A valid search key must be entered.");
                            request.setAttribute("searchkey", searchkey);  //return searchkey for corrections
                            request.getRequestDispatcher("/workorder.jsp").forward(request, response);
                            break;
                        }
                        searchkey=searchkey.trim();
                        pot = Dbquery.selectPotholeallreport("SELECT * FROM Potholeallreport WHERE workid = '" + searchkey + "'");
                        if (pot == null) {
                            request.setAttribute("responseMessage", "Work order record not found. Verify Search Work ID.");
                            request.setAttribute("searchkey", searchkey);  //return searchkey for corrections
                        } else if (null !=pot.getPotholestatus() &&  pot.getPotholestatus().equals("CLOSE")) {
                            isViewOnly = true;
                            request.setAttribute("responseMessage", "Work Order record is a closed. User may view only.");
                        request.setAttribute("pot", pot);   //return retrieved person 
                        } else {
                            isViewOnly = false;
                            request.setAttribute("responseMessage", "Work order record retrieved for update.");
                            request.setAttribute("pot", pot);   //return retrieved person 
                        }
                        request.getRequestDispatcher("/workorder.jsp").forward(request, response);
                        break;
                    } //WORKORDER UPDATE
                    else if (null != request.getParameter("UPDATE")) {
                        if (null != pot_set.getWorkid() && pot_set.getWorkid().length() < 1) {
                            request.setAttribute("responseMessage", "You must retrieve a Work Order record using SEARCH before pressing UPDATE.");
                        } else if (isViewOnly) {
                            request.setAttribute("responseMessage", "This Work Order record is view only.");
                            request.setAttribute("pot", pot_set);  //return record to still view      

                        } else if (!validStatus.contains(pot_set.getWorkorderstatus().toUpperCase().trim())) {
                            request.setAttribute("responseMessage", "Work order status must be DENY, APPROVE, HOLD or CLOSE.");
                            request.setAttribute("pot", pot_set);  //return record for corrections
                        } else {
                            searchkey = pot_set.getWorkid();
                            if (Dbquery.updateWorkorder(pot_set.getWorkid(), pot_set.getWorkorderstatus().toUpperCase().trim(), pot_set.getWorkordercomments())) {
                                //person record successfully update in db
                                //try to retrtrieve for viewing
                                pot = Dbquery.selectPotholeallreport("SELECT * FROM Potholeallreport WHERE workid = '" + searchkey + "'");
                                request.setAttribute("responseMessage", "Work order record updated");
                                request.setAttribute("pot", pot);   //return retrieved person   
                            } else {
                                request.setAttribute("responseMessage", "Work order record not Updated. Verify that repair data is entered correctly.");
                                request.setAttribute("pot", pot_set);  //return record for corrections
                            }
                        }
                        request.getRequestDispatcher("/workorder.jsp").forward(request, response);
                        break;
                    } //WORKORDER CLEAR
                    else if (null != request.getParameter("CLEAR")) {
                        request.setAttribute("responseMessage", " ");
                        request.setAttribute("searchkey", " ");
                        request.getRequestDispatcher("/workorder.jsp").forward(request, response);
                        break;
                    }
                } catch (SQLException ex) {
                    request.setAttribute("responseMessage", ex.getMessage());
                    request.getRequestDispatcher("/workorder.jsp").forward(request, response);
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(MMSController.class.getName()).log(Level.SEVERE, null, ex);
                }
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



    public static boolean contains(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
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
