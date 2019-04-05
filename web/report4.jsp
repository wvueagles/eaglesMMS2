<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <li><a href=".\person.jsp"><span>Persons</span></a></li>
                <li class='active'><a href=".\report.jsp"><span>Reports</span></a></li>
                <li class='last'><a href=".\exit.jsp"><span>Exit</span></a></li>
            </ul>
        </div>
        <div id='report'> 
            <form action="MMSController?action=report" method="POST">
                <strong> Reports Menu Item</strong>
                <br> <br>
                <p>Press POTHOLE to view pothole records.</p>
                <input type="submit" value="POTHOLE" />    
                ${responseMessage} 
                <br><br>
   <c:if test="${pals} != null}">

            
<div align="center">
        <table border="1" cellpadding="5">
        <caption><h2>List of Pothole Record(s)</h2></caption>
        <tr><th>Work ID</th><th>Location</th><th>Severity</th><th>Create Date</th><th>Pothole Status</th>
        <th>Repair Status</th><th>Work Order Status</th><th>Repair Type</th><th>Work Type</th><th>Reporting Person</th>
    <th>Repair POC</th><th>Work POC</th><th>Pothole Update</th><th>Repair Update</th><th>work Update</th>
    <th>Closed Date</th><th>Pothole Comments</th><th>Repair Comments</th><th>Work Comments</th></tr>						
</c:if> 		
<c:forEach items="${pals}" var="pal">
<tr><td><c:out value="${pal.workid}" /></td>
    <td><c:out value="${pal.potholelocation}" /></td>
    <td><c:out value="${pal.potholestatus}" /></td>
    <td><c:out value="${pal.repairstatus}" /></td>
    <td><c:out value="${pal.workorderstatus}" /></td>
    <td><c:out value="${pal.repairordertype}" /></td>
    <td><c:out value="${pal.workordertype}" /></td>
    <td><c:out value="${pal.reportingpersonkey}" /></td>
    <td><c:out value="${pal.repairpoc}" /></td>
    <td><c:out value="${pal.workorderpoc}" /></td>
    <td><c:out value="${pal.potholeupdateddate}" /></td>
    <td><c:out value="${pal.repairupdateddate}" /></td>
    <td><c:out value="${pal.workorderupdateddae}" /></td>
    <td><c:out value="${pal.potholeclosedtime}" /></td>
    <td><c:out value="${pal.potholecomments}" /></td>
    <td><c:out value="${pal.repaircomments}" /></td>
    <td><c:out value="${pal.workordercomments}" /></td></tr>                    
</c:forEach></table>
    </form>  
        </div>     
    </body>
</html>	
