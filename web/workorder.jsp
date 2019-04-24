<!DOCTYPE html>
<html>
    <head>
        <title>MMS Eagles Pothole System </title>
        <meta charset='utf-8'/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link rel="stylesheet" href="styles.css"/>
        <script src="jquery-latest.min.js" type="text/javascript"></script>
        <script src="script.js"></script>
    </head>   
    <body>
        <div id='cssmenu'>
            <ul>
                <li><a href='.\index.xhtml'><span>Home</span></a></li>
                <li><a href=".\potholes.jsp"><span>Potholes</span></a></li>
                <li><a href=".\repair.jsp"><span>Repairs</span></a></li>
                <li class='active'><a href=".\workorder.jsp"><span>Work Orders</span></a></li>
                <li><a href=".\person.jsp"><span>Persons</span></a></li>
                <li><a href=".\report.jsp"><span>Reports</span></a></li>
                <li class='last'><a href=".\exit.jsp"><span>Exit</span></a></li>
            </ul>
        </div>
        <div id='workorder'> 
            <form action="MMSController?action=workorder" method="POST">
                <strong> Work Order Menu Item</strong>
                <br><br>
                <p>Press SEARCH to display a work order record by  Work ID:  <input type="text" pattern="[0-9]+" name="searchkey" />  <input type="submit" name="SEARCH" value="SEARCH" /></p>   
                <p>Press UPDATE to update displayed work order record. <input type="submit" name="UPDATE" value="UPDATE"  /></p>
                <p>Press CLEAR to reset the report form.  <input type="submit" name="CLEAR" value="CLEAR" /></p>           
                <div style="color: #FF0000;">${responseMessage}</div>  
                <br><br>
                <table>
                    <p><label>Work ID:     </label><input type="text" name="pot.workid"  value="${pot.workid}"  style="background: #D3D3D3;" readonly /></p>
                    <p><label>Status:     </label><input type="text" name="pot.workorderstatus"   pattern="[a-zA-Z]+"  title="Valid User Work Order status values are: APPROVE, DENY, HOLD or CLOSE" minlength="0"  maxlenth="20" value="${pot.workorderstatus}"  /></p>
                    <p><label>Type:     </label><input type="text" name="pot.workordertype"  value="${pot.workordertype}" style="background: #D3D3D3;" readonly /></p> 
                    <p><label>Comments:        </label><input type="text" name="pot.workordercomments"  pattern="[0-9a-zA-Z ,-]{0,59}" title="Work Order Comment format is letter, numbers, spaces, commas, and dash. No special characters."  style="width: 300px;" maxlenth="60"   value="${pot.workordercomments}" /></p> 
                     <p><label>Create Date:        </label><input type="text" name="pot.createddate"   maxlenth="60"   value="${pot.createddate}"  style="background: #D3D3D3;" readonly /></p>  
                     <p><label>Last Updated:        </label><input type="text" name="pot.workorderupdateddae"   maxlenth="60"   value="${pot.workorderupdateddae}"  style="background: #D3D3D3;" readonly /></p>  
                     <p><label>Closed Date:     </label><input type="text" name="pot.potholeclosedtime"   maxlenth="60"   value="${pot.potholeclosedtime}"  style="background: #D3D3D3;" readonly /></p>            
                    <p>Valid User Work Order status values are: APPROVE, DENY, HOLD or CLOSE</p>
               <p>Shaded boxes are read only fields.</p>
                </table>  
            </form> 
        </div>
    </body>
</html>