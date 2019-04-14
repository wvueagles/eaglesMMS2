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
                <li><a href=".\workorder.jsp"><span>Work Orders</span></a></li>
                <li class='active'><a href=".\person.jsp"><span>Persons</span></a></li>
                <li><a href=".\report.jsp"><span>Reports</span></a></li>
                <li class='last'><a href=".\exit.jsp"><span>Exit</span></a></li>
            </ul>
        </div>
        <div id='person'> 
            <form action="MMSController?action=person" method="POST">
                <strong> Person Menu Item</strong>
                <br><br>
                <p>Press ADD to enter a person record. <input type="submit" name="ADD" value="ADD"  /></p>
                <p>Press SEARCH to display a person record by User Name:  <input type="text" name="searchkey"  value="${searchkey}"  />  <input type="submit" name="SEARCH" value="SEARCH" /></p>   
                <p>Press UPDATE to update displayed person record. <input type="submit" name="UPDATE" value="UPDATE"  /></p>
                <p>Press CLEAR to reset the report form.  <input type="submit" name="CLEAR" value="CLEAR" /></p>           
                <div style="color: #FF0000;">${responseMessage}</div>  
                <br><br>
                <table>
                    <p><label>User  Name:     </label><input type="text" name="per.personkey"  value="${per.personkey}"  style="background: #D3D3D3;" readonly /></p>
                    <p><label>*First Name:     </label><input type="text" name="per.firstname"   pattern="[a-zA-Z]+" minlength="0"  maxlenth="20" value="${per.firstname}"  /></p>
                    <p><label>*Last  Name:     </label><input type="text" name="per.lastname" pattern="[a-zA-Z]+" minlength="0"  maxlenth="30"  value="${per.lastname}"  /></p> 
                    <p><label>Address:        </label><input type="text" name="per.address"   maxlenth="60"   value="${per.address}" /></p> 
                    <p><label>Phone:          </label><input type="text" name="per.phone"  maxlenth="20"  value="${per.phone}" /></p> 
                    <p><label>Alternate Phone:</label><input type="text"  name="per.alternatephone" maxlenth="20"   value="${per.alternatephone}" /></p> 
                    <p><label>Email Address:  </label><input type="text" name="per.emailaddress" maxlenth="30"  value="${per.emailaddress}" /></p>
                    <p><label>Created Date:   </label><input type="text" name="per.createddate"  value="${per.createddate}" style="background: #D3D3D3;" readonly /></p> 
                    <p><label>Updated Date:   </label><input type="text" name="per.updateddate"  value="${per.updateddate}" style="background:#D3D3D3;" readonly/></p> 
                    <p>*Required Fields on Add</p>
                    <p>Shaded boxes are read only fields.</p>
                    <p>The system creates the user name by adding the first name initial in front of their last name.</p>
                </table>  
            </form> 
        </div>
    </body>
</html>