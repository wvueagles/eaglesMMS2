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
                <li class='active'><a href=".\potholes.jsp"><span>Potholes</span></a></li>
                <li><a href=".\repair.jsp"><span>Repairs</span></a></li>
                <li><a href=".\workorder.jsp"><span>Work Orders</span></a></li>
                <li><a href=".\person.jsp"><span>Persons</span></a></li>
                <li><a href=".\report.jsp"><span>Reports</span></a></li>
                <li class='last'><a href=".\exit.jsp"><span>Exit</span></a></li>
            </ul>
        </div>
        <div id='potholes'> 
            <form action="MMSController?action=potholes" method="POST">
                <strong> Pothole Menu Item</strong>
                <br><br>
                <p>Press INSERT to enter a pothole record. <input type="submit" name="INSERT" value="INSERT"  /></p>
                <p>Press SEARCH to display a pothole record by Work ID:  <input type="text"  pattern="[0-9]+" name="searchkey" />  <input type="submit" name="SEARCH" value="SEARCH" /></p>   
                <p>Press UPDATE to update displayed pothole record. <input type="submit" name="UPDATE" value="UPDATE"  /></p>
                <p>Press CLEAR to reset the report form.  <input type="submit" name="CLEAR" value="CLEAR" /></p>           
                <div style="color: #FF0000;">${responseMessage}</div>  
                <br><br>
                <table>
                    <p><label>Work ID:     </label><input type="text" name="pot.workid"  value="${pot.workid}"  style="background: #D3D3D3;" readonly /></p>
                    <p><label>*Location:     </label><input type="text" name="pot.potholelocation"   pattern="[0-9a-zA-Z ,-]{0,59}" title="Pothole Location format is letter, numbers, spaces, commas, and dash. No special characters."  style="width: 300px;" minlength="0"  maxlenth="59" value="${pot.potholelocation}"  /></p>
                    <p><label>Severity:     </label><input type="text" name="pot.severity" pattern="[1-5]{0,1}"  title="Valid severity is a range from 1-5." minlength="0"  maxlenth="29"  value="${pot.severity}"  /></p> 
                    <p><label>Status:     </label><input type="text" name="pot.potholestatus" pattern="[a-zA-Z]+" minlength="0"  maxlenth="30"  value="${pot.potholestatus}" style="background: #D3D3D3;" readonly /></p> 
                    <p><label>Reporting Person Key:     </label><input type="text" name="pot.reportingpersonkey" pattern="[a-zA-Z]{0,25}" title="User name for person reporting pothole is first name initial followed by their last name." minlength="0"  maxlenth="29"  value="${pot.reportingpersonkey}"  /></p> 
                    <p><label>Comments:        </label><input type="text" name="pot.potholecomments"  pattern="[0-9a-zA-Z ,-]{0,59}" title="Pothole Comment format is letter, numbers, spaces, commas, and dash. No special characters."  style="width: 300px;" maxlenth="59"   value="${pot.potholecomments}" /></p> 
                    <p><label>Create Date:    </label><input type="text" name="pot.createddate"   maxlenth="59"   value="${pot.createddate}"  style="background: #D3D3D3;" readonly /></p>  
                    <p><label>Last Updated:    </label><input type="text" name="pot.potholeupdateddate"   maxlenth="59"   value="${pot.potholeupdateddate}"  style="background: #D3D3D3;" readonly /></p>  
                    <p><label>Closed Date:     </label><input type="text" name="pot.potholeclosedtime"   maxlenth="59"   value="${pot.potholeclosedtime}"  style="background: #D3D3D3;" readonly /></p> 
                    <p>*Required Fields on Add</p>
                    <p>Location must contain a street address and no special characters.</p>
                    <p>Pothole Severity is ranked between 1-5, with default value of 1</p>
                    <p>Shaded input boxes are read only fields.</p>
                </table>   
            </form> 
        </div>
    </body>
</html>