$(document).ready(function() {

	//alert('HI');

	/*$("div").click(function() {
		alert("You clicked me.");
	});//end click function
	 */	

});//end doc ready

function approveleave(id) {
	//alert(id);
	var comment = $('#comment').val();
	if(comment == ""){
		comment = null;
	}
	alert(comment);
	$.ajax({
		cache: false,
		url: "http://localhost:8080/manager/{username}/approveleave/"+id+"/"+comment,
		beforeSend: function () {

		},
		success: function (result) {
			if (result != null) {
				alert("Successfully Approved")
				window.location.href = "/manager/{username}/viewappliedlist";
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

	var comment = $('#comment').val();
	if( comment == "")
	{
		alert("Please fill in comment!");
	}
	else{
		$.ajax({
			cache: false,
			url: "http://localhost:8080/manager/{username}/cancelleave/"+id+"/"+comment,
			beforeSend: function () {

			},
			success: function (result) {
				if (result != null) {
					alert("Successfully Rejected!")
					window.location.href = "/manager/{username}/viewappliedlist";
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
				//window.location.href = "/user/{username}/updateleave/(username = ${session.username})";
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
	alert(id);
	//var userId=$('#userId').val();
	var leavetype = $('#leavetype').val();
	var leaveStartDate = $('#leaveStartDate').val();
	var leaveEndDate = $('#leaveEndDate').val();
	var leaveReason = $('#leaveReason').val();
//	var standInStaff = $('#standInStaff').val();
//	var contactDetails = $('#contactDetails').val();

	var leavetoUpdate = {
			"id" : id,
			//"userId": userId,
			"leaveType" : leavetype,
			"leaveStartDate" : leaveStartDate,
			"leaveEndDate" : leaveEndDate,
			"leaveReason" : leaveReason
//			"standInStaff" : standInStaff
	}

//	var leave =  JSON.stringify(leavetoUpdate);

	$.ajax({
		type : "POST",
		accept : 'application/json',
		contentType : 'application/json',
		dataType : 'json',
		url :"http://localhost:8080/user/{username}/updateleave",
		data : JSON.stringify(leavetoUpdate),
		beforeSend : function() {

		},
		success : function(result) {
			if (result != null) {
				alert("Successfully Updated!")
				//window.location.href = "/manager/{username}/viewappliedlist";
			} else {
				alert("Fail");
			}
		},
		complete : function() {

		}

	});
}
