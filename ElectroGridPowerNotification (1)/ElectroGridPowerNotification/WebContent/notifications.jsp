<%@ page import="com.Notification"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PowerNotification</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.6.0.js"></script> 
<script src="Components/notifications.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-5"> 
			<h1>ElectroGrid Notification Management</h1>
				<form id="formNotification" name="formNotification" method="post" action="notifications.jsp">  
					   Description:  
 	 				<input id="description" name="description" type="text"  class="form-control form-control-sm">
					<br>Admin Name:   
  					<input id="adminName" name="adminName" type="text" class="form-control form-control-sm">   
  					<br>Date:   
  					<input id="nDate" name="nDate" type="text"  class="form-control form-control-sm">
  					<br>Zone:   
  					<input id="zone" name="zone" type="text"  class="form-control form-control-sm">
  					<br>
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidNotificationIDSave" name="hidNotificationIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divNotificationGrid">
					<%
					    
					    Notification NotificationObj = new Notification(); 
						out.print(NotificationObj.readNotification());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>