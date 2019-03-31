package controller;

/**
 * SENG AGILE EAGLES PROJECT 
 * Pothole reporting, repairs and tracking
 */
import java.util.ArrayList;
import java.util.Iterator;

class MMSContent {

  
 //   private variables sample...
    private String potholeContent=" ";

    /*constructor */
    MMSContent() {
        this.potholeContent = "this is just a placeholder";
       
    }

    /*constructor */
    MMSContent(MMSContent mc) {
        this.potholeContent =mc.potholeContent;
    }

    void clearMSContent() {
         this.potholeContent =" ";
    }

// print report example maybe? 
    void printReport() {
        System.out.println("\n Pothole" + this.potholeContent + "\n");
    }
//add save report to file ; like print why write? just CTL+P

}
