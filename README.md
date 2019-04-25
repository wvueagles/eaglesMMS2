# eaglesMMS
MMS Technology Web application

Server:  Apache Tomcat server 9.0.7
Development platform:   NetBeans IDE 8.2
Language/Design:  Java Controller Servlet using java persistence and HTTP messaging, web pages were JSP and JNDI
Data platform: Postgresql-42.2.5 for relation database  
•	The database schema Tables: 
o	Used a pothole table, repair table, work order table and person table to represent each MMS object/web page.  
o	Used two views that represented the data report types (person and pothole all data) on the report page.
o	Used trigger and functions to automatically cascade status updates based on User data entry.
o	Used trigger and functions to automatically create supporting records upon initial pothole record inserted.
o	For enforcement of pothole status flow, each table status had a custom status type created for each MMS object.
•	The data base scripts to create eaglesmms schema and functions are included in our teams Github repostitory
Github: Repository was used for tracking versions and code merging.   Professor Mundy, you were sent an invite to our repository.   https://github.com/wvueagles/eaglesMMS2
Host platform: AWS Elastic beanstalk configuration environment was the same as our local environment.

