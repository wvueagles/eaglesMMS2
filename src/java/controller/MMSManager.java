/*
 * 
 */
package controller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * this contains the mmsproperty settings
 *
 * @author pammt
 */
public class MMSManager {

    private static final long serialVersionUID = 1L;
    private static long work_ID;
  //  private static final String ?
    private static final String POTHOLE_REPORT = "pothole.rpt";
 //   private static int pTotal;
    private static MMSContent mc = new MMSContent();
    private static String potResponseMessage, repairResponseMessage, wrkResponseMessage, perResponseMessage, mmsResponseMessage;
    private static ArrayList<MMSContent> mms= new ArrayList<MMSContent>();
   private static List<Integer> potHoleOrder = new ArrayList<>();
    MMSManager() {
        this.work_ID =0;
        this.mmsResponseMessage = " ";
        this.potResponseMessage = " ";
        this.repairResponseMessage = " ";
        this.wrkResponseMessage = " ";
        this.perResponseMessage = " ";
    }
//method to validate page input (xxxx.jsp)
// returns true when succesfully set false if no succesfully set

    public static boolean isValidInput(String test) {
      
/*        mc.clearContent();
       catch (NumberFormatException e) {
             potResponseMessage = potResponseMessage + "whatever error. ";
             return false;
           }
  */        
        return true;
    } // end 

 
    
    
    public String getMMSResponseMessage() {
        return mmsResponseMessage;
    }

    public String getPotResponseMessage() {
        return potResponseMessage;
    }

    public static boolean isPropSet() {
//  example test
if (true) return true;
         else {
            potResponseMessage = "Please fix what ever is not valid.";
        }
        return false;
    }

    public void catPotHoleResponseMessage(String message) {
        potResponseMessage = potResponseMessage + message;
    }

    void clearMMS() {
        this.work_ID =0;
        this.mmsResponseMessage = " ";
        this.potResponseMessage = " ";
        this.repairResponseMessage = " ";
        this.wrkResponseMessage = " ";
        this.perResponseMessage = " ";
    }

    static void setMMSStart() {
   ;
    }

   //get pothole data
    public static String getMMSData()
            {
         return (null);
            }
            
    static String getMMSComplete() {
        String displayMessage = "";
        displayMessage = displayMessage + "Whatever just an example";
        return displayMessage;
    }  //end mmsCompleted 
  
private static boolean writeToFile() {
     try {
            FileOutputStream fs = null;
            String strLine;
            fs = new FileOutputStream(POTHOLE_REPORT, true);
            OutputStreamWriter osw = new OutputStreamWriter(fs);
            try {
                //  osw.write(ql.toDelString());
               strLine = "just an example";
                osw.write(strLine);
            } catch (IOException ex) {
                Logger.getLogger(MMSManager.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            try {
                osw.close();
            } catch (IOException ex) {
                Logger.getLogger(MMSManager.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MMSManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;    
}
    

    public static String displaySomething() {
        String displayMessage;
        displayMessage = "This is just an example. ";
        displayMessage = displayMessage.concat("<br>What up " + 5 + " testing....");
        return displayMessage;
    }
    
    void setPotResponseMessage(String s) {
        this.potResponseMessage = " ";
    }
   
} //end of MMSManager Class
