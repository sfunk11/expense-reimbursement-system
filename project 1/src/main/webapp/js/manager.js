$(document).ready(function(){
getEmployees();	
	
})


tdTemplate = "<td class = 'list-group-item>'>%val</td>";
buttonTemplate = "<button class='btn btn-sm btn-outline-dark text-capitalize' onClick='%click' id='%a%N'>%action</button>";
 
 function getEmployees(){
	
	$.ajax({
		url:"/project1-ers/getEmployees.api",
		method: "GET"
	}).then(res => {
		res = JSON.parse(res);
		optionTemplate = "<option value = %id>%fname %lname</option>";
		for(i=0; i<res.length; i++){
			optionEl = optionTemplate.replace("%id",res[i].userId).replace("%fname", res[i].firstName).replace("%lname", res[i].lastName);
			$("#employeeId").append(optionEl);
		}
	})
}

function getManagerStatusList(){
	event.preventDefault();
	$.ajax({
		url: "/project1-ers/getManagerItems.api",
		method: "GET",
		data: {
			employeeId: $("#employeeId").val(),
		}
		}).then(res =>{
		console.log(res);

		$("#managerStatusArea").empty();
			for (i = 0; i<res.length; i++){
			newRow = $("<tr>")	
			itemNumEl = tdTemplate.replace("%val",res[i].reimbId).replace("%N", res[i].reimbId);
			employeeEl = tdTemplate.replace("%val",res[i].authorUsername);
			AmountEl = tdTemplate.replace("%val", "$" + res[i].amount);
			itemStatusEl = tdTemplate.replace("%val",res[i].status);
			itemTypeEl = tdTemplate.replace("%val",res[i].reimbCategory);
			itemDescEl = tdTemplate.replace("%val",res[i].description);
			approveEl = buttonTemplate.replace("%click", "approveItem(this)").replace("%a", "apr").replace("%action","approve").replace("%N",res[i].reimbId);
			rejectEl = buttonTemplate.replace("%click", "rejectItem(this)").replace("%a", "deny").replace("%action","reject").replace("%N",res[i].reimbId);
			if($("#status").val() == res[i].reimbStatus || $("#status").val() == 0){
			$("#managerStatusArea").append(newRow);
			$(newRow).append(itemNumEl,employeeEl,AmountEl,itemStatusEl,itemTypeEl,itemDescEl);
			if (res[i.reimbStatus == 1]){
				$("#managerStatusArea").append(approveEl, rejectEl);
			}} 
		}
	}, res => {
		console.log(res)
	})
	
}	
	
function logOut(){
	$.ajax({
		url: "/project1-ers/logout.view",
		method: "GET"
	}).then((res) => {
		console.log(res);
	})
}

function approveItem(elem){
	event.preventDefault();
	itemId = $(elem).closest("tr").find("td:first-child").text();
	
	$.ajax({
		url:"/project1-ers/changeItem.api",
		method: "POST",
		data: {
			reimbId: itemId,
			isApproved: true
		}
	}).then(res => {
		getManagerStatusList();
	})
}

function rejectItem(elem){
	event.preventDefault();
	itemId = $(elem).closest("tr").find("td:first-child").text();
	
	console.log(itemId);
	$.ajax({
		url:"/project1-ers/changeItem.api",
		method: "POST",
		data: {
			reimbId: itemId,
			isApproved: false
		}
	}).then(res => {
		getManagerStatusList();
	})
}

function newEmployeeFormSubmit(){
	employeeData = {
		name : $("#employeeName").val(),
		username : $("#newUsername").val(),
		password : $("#newPassword").val(),
		email : $("#email").val(),
		roleId : $("#roleType").find("option:selected").val()
	};
	
	$.ajax({
		url: "/project1-ers/newEmployee.api",
		method: "POST",
		data: employeeData
	})
	.then(res => {
		alert(employeeData.name +" was added successfully. Email sent with temporary password.")
		$("#employeeId").empty();
		getEmployees();
	})
	
}

 