$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateNotificationForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidNotificationIDSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
			url : "NotificationAPI",   
			type : type,  
			data : $("#formNotification").serialize(),  
			dataType : "text",  
			complete : function(response, status)  
			{   
				onNotificationSaveComplete(response.responseText, status);  
			} 
	}); 
}); 


function onNotificationSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divNotificationGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidNotificationIDSave").val("");  
	$("#formNotification")[0].reset(); 
} 

 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidNotificationIDSave").val($(this).closest("tr").find('#hidNotificationIDUpdate').val());     
	$("#description").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#adminName").val($(this).closest("tr").find('td:eq(1)').text()); 
	$("#nDate").val($(this).closest("tr").find('td:eq(2)').text());
	$("#zone").val($(this).closest("tr").find('td:eq(3)').text());   
	 
}); 




//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "NotificationAPI",   
		type : "DELETE",   
		data : "NotificationID=" + $(this).data("NotificationID"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onNotificationDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onNotificationDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divNotificationGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validateNotificationForm() 
{  
	// Description-----------------------
	if ($("#description").val().trim() == "")  
	{   
		return "Insert Description.";  
	} 

	
	// AdminName------------------------------
	if ($("#adminName").val().trim() == "")  
	{   
		return "Insert Admin Name.";  
	}
	
	// Date-------------------------------
	if ($("#nDate").val().trim() == "")  
	{   
		return "Insert Date.";  
	}
	
	// Zone---------------------------  
	if ($("#zone").val().trim() == "")  
	{   
		return "Insert Zone.";  
	}
		
	return true; 
}