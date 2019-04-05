<!DOCTYPE html>
<html>
    <head>
        <title>MMS Eagles Pothole System</title>
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
                <p>Reporting pothole person contact record entry, coming soon.</p>
                <input type="submit" value="Submit" />  
                <div style="color: #FF0000;">${responseMessage}</div>   
            </form> 
        </div>
    </body>
</html>