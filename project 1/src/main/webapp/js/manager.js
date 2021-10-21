$(document).ready(function(){
getEmployees();	
	
})


tdTemplate = "<td class = 'list-group-item>'>%val</td>";
buttonTemplate = "<button class='btn btn-sm btn-info me-3 text-capitalize' onClick='%click' id='%a%N'>%action</button>";
 
 function getEmployees(){
	
	$.ajax({
		url:"/project1-ers/getEmployees.api",
		method: "GET",
		cache: false
	}).then(res => {
		res = JSON.parse(res);
		optionTemplate = "<option value = %id>%fname %lname</option>";
		defaultEl ='<option value = "0"> All Employees </option>'
		$("#employeeId").append(defaultEl);
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
				date = new Date(res[i].submittedAt);
				formattedDate = (date.getMonth()+1)+
          "/"+date.getDate()+
          "/"+date.getFullYear()+
          " "+date.getHours()+
          ":"+date.getMinutes()+
          ":"+date.getSeconds();
				newRow = $("<tr>")	
				itemNumEl = tdTemplate.replace("%val",res[i].reimbId);
				submittedEl = tdTemplate.replace("%val", formattedDate);
				employeeEl = tdTemplate.replace("%val",res[i].authorUsername);
				AmountEl = tdTemplate.replace("%val", "$" + res[i].amount);
				itemStatusEl = tdTemplate.replace("%val",res[i].status);
				itemTypeEl = tdTemplate.replace("%val",res[i].reimbCategory);
				itemDescEl = tdTemplate.replace("%val",res[i].description);
				approveEl = buttonTemplate.replace("%click", "approveItem(this)").replace("%a", "apr").replace("%action","approve").replace("%N",res[i].reimbId);
				rejectEl = buttonTemplate.replace("%click", "rejectItem(this)").replace("%a", "deny").replace("%action","reject").replace("%N",res[i].reimbId);
				if($("#status").val() == res[i].reimbStatus || $("#status").val() == 0){
					$("#managerStatusArea").append(newRow);
					$(newRow).append(itemNumEl,submittedEl,employeeEl,AmountEl,itemStatusEl,itemTypeEl,itemDescEl);
				if (res[i].reimbStatus == 1){
					$(newRow).append(approveEl, rejectEl);
			}} 
		}
	})
	
}	
	
function logOut(){
	$.ajax({
		url: "/project1-ers/logout.view",
		method: "GET"
	}).then((res) => {
		 history.pushState(null, null, null);
	    window.addEventListener('popstate', function () {
	        history.pushState(null, null, null);
	    });

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
		},
		cache:false
	}).then(res => {
		getManagerStatusList();
	})
}

function rejectItem(elem){
	event.preventDefault();
	itemId = $(elem).closest("tr").find("td:first-child").text();
	
	
	$.ajax({
		url:"/project1-ers/changeItem.api",
		method: "POST",
		data: {
			reimbId: itemId,
			isApproved: false
		},
		cache:false
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

 