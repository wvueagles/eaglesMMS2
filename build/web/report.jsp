<!DOCTYPE html>
<html>
    <head>
        <title>Facelet Title</title>
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
                <li><a href=".\pothole.jsp"><span>Potholes</span></a></li>
                <li><a href=".\repair.jsp"><span>Repairs</span></a></li>
                <li><a href=".\workorder.jsp"><span>WorkOrders</span></a></li>
                <li><a href=".\person.jsp"><span>Persons</span></a></li>
                <li class='active'><a href=".\report.jsp"><span>Reports</span></a></li>
                <li class='last'><a href=".\exit.jsp"><span>Exit</span></a></li>
            </ul>
        </div>
        <div id='report'> 
            <form action="MMSController?action=report" method="POST">
                <br><br> 
                <strong>Press Submit to display pothole records.</strong> 
                <input type="submit" value="Submit" />  
                <br><br>
                <div style="color: #FF0000;">${responseMessage}</div>  
            </form>  
        </div>
    </body>
</html>