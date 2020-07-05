$(document).ready(function() {

	//$('#employee').DataTable();
	//alert('HI');

	/*$("div").click(function() {
		alert("You clicked me.");
	});//end click function
	 */	

});//end doc ready

function approveleave(id) {
	//alert(id);
	var comment = $('#comment').val();
	var session = $('#session').val();
	var testurl = "http://localhost:8080/manager/"+session+"/approveleave/"+id+"/"+comment;
	if(comment == ""){
		comment = null;
	}
	//alert(comment);
	//alert(testurl);
	//alert(session);
	$.ajax({
		cache: false,
		url: "http://localhost:8080/manager/"+session+"/approveleave/"+id+"/"+comment,
		beforeSend: function () {

		},
		success: function (result) {
			if (result != null) {
				alert("Successfully Approved")
				window.location.href = "/manager/"+session+"/viewappliedlist";
			}
			else {
				alert("Fail");
			}
		},
		complete: function () {

		}
	});
}

function cancelleave(id) {
	//alert(id);
	var session = $('#session').val();
	var comment = $('#comment').val();
	if( comment == "")
	{
		alert("Please fill in comment!");
	}
	else{
		$.ajax({
			cache: false,
			url: "http://localhost:8080/manager/"+session+"/cancelleave/"+id+"/"+comment,
			beforeSend: function () {

			},
			success: function (result) {
				if (result != null) {
					alert("Successfully Rejected!")
					window.location.href = "/manager/"+session+"/viewappliedlist";
				}
				else {
					alert("Fail");
				}
			},
			complete: function () {

			}
		});
	}

}

function searchEmployee() {
	//alert(id);
	var startdate = $('#startdate').val();
	var enddate = $('#enddate').val();

	$.ajax({
		cache: false,
		url: "http://localhost:8080/manager/{username}/leaveperiod/"+startdate+"/"+enddate,
		beforeSend: function () {

		},
		success: function (result) {
			if (result != null) {
				alert("Successfully Displayed")
				//window.location.href = "/manager/{username}/viewappliedlist";
				console.log("SUCCESS : ", result);
				$('#elist').empty().append(result);
			}
			else {
				alert("Fail");
			}
		},
		complete: function () {

		}
	});
}

function updateleave(id) {
//	alert(id);
	var session =$('#session').val();
	var userId=$('#userId').val();
	var leavetype = $('#leavetype').val();
	var leaveStartDate = $('#leaveStartDate').val();
	var leaveEndDate = $('#leaveEndDate').val();
	var leaveReason = $('#leaveReason').val();
//	var standInStaff = $('#standInStaff').val();
//	var contactDetails = $('#contactDetails').val();

	var leavetoUpdate = {
			"id" : id,
			"session" : session,
			"userId": userId,
			"leaveType" : leavetype,
			"leaveStartDate" : leaveStartDate,
			"leaveEndDate" : leaveEndDate,
			"leaveReason" : leaveReason
//			"standInStaff" : standInStaff
	}

//	var leave =  JSON.stringify(leavetoUpdate);
	alert("Updating "+id);
	$.ajax({
		type : "POST",
		accept : 'application/json',
		contentType : 'application/json',
		dataType : 'json',
		url :"http://localhost:8080/user/"+session+"/updateleave",
		data : JSON.stringify(leavetoUpdate),
		beforeSend : function() {

		},
		success : function(result) {
			console.log(result);
			if (result == "success") {
				alert("Successfully Updated!")
				window.location.href = "/user/"+session+"/leavelist";
			} else {
				alert("Fail");
			}
		},
		error : function(e) {
	        alert("Error!")
	        console.log("ERROR: ", e);
	      },
		complete : function() {

		}


	});
}
