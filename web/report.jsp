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
                <strong>Reports Menu Item</strong>
                <br> <br>
                <p>Press POTHOLE to view all Pothole data. <input type="submit" name="POTHOLE" value="POTHOLE"  /></p>
                <p>Press SEARCH to view a pothole record by entered Work ID:  <input type="text" name="searchpot" />  <input type="submit" name="SEARCHPID" value="SEARCH by ID" /></p>   		
                <p>Press PERSON to view all Person data. <input type="submit" name="PERSON" value="PERSON"  /></p>
                <p>Press SEARCH to view a person record by User Name:  <input type="text" name="searchkey" />  <input type="submit" name="SEARCHUNAME" value="SEARCH by User Name" /></p>   
		<p>Press CLEAR to reset the report form.  <input type="submit" name="CLEAR" value="CLEAR" /></p>           
                ${responseMessage} 
                <br><br>
<c:if test="${pals.size() > 0}"> 
  <div align="center">
        <table border="1" cellpadding="5">
        <caption><h2>List of Pothole Record(s)</h2></caption>
        <tr><th>Work ID</th><th>Location</th><th>Severity</th><th>Create Date</th><th>Pothole Status</th><th>Repair Status</th>
            <th>Work Order Status</th><th>Repair Type</th><th>Work Type</th>
            <th>Reporting Person</th><th>Repair POC</th><th>Work POC</th><th>Pothole Update</th>
            <th>Repair Update</th><th>Work Update</th><th>Closed Date</th><th>Pothole Comments</th><th>Repair Comments</th><th>Work Comments</th></tr>						
		
<c:forEach items="${pals}" var="pal">
<tr><td><c:out value="${pal.workid}" /></td>
    <td><c:out value="${pal.potholelocation}" /></td>
    <td><c:out value="${pal.severity}" /></td>
    <td><c:out value="${pal.createddate}" /></td>
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
</c:if>
      
<c:if test="${pers.size() > 0}"> 
  <div align="center">
        <table border="1" cellpadding="5">
        <caption><h2>List of Person Record(s)</h2></caption>
        <tr><th>User Name</th><th>First Name</th><th>Last Name</th><th>Address</th><th>Phone Number</th><th>Alternate Phone</th><th>Email Address</th><th>Create Date</th><th>Update Date</th></tr>						
		
<c:forEach items="${pers}" var="per">
<tr><td><c:out value="${per.personkey}" /></td>
    <td><c:out value="${per.firstname}" /></td>
    <td><c:out value="${per.lastname}" /></td>
    <td><c:out value="${per.address}" /></td>
    <td><c:out value="${per.phone}" /></td>
    <td><c:out value="${per.alternatephone}" /></td>
    <td><c:out value="${per.emailaddress}" /></td>
    <td><c:out value="${per.createddate}" /></td>  
    <td><c:out value="${per.updateddate}" /></td>                
</c:forEach></table>
</c:if>
    </form>  
        </div>     
    </body>
</html>	
