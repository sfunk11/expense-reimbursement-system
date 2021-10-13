$(document).ready(function(){
	getEmployees();
	
})

tdTemplate = "<td class = 'list-group-item>'> %val </td>";
buttonTemplate = "<button"
 
 function getEmployees(){
	
	$.ajax({
		url:"/project1-ers/getEmployees.api",
		method: "GET"
	}).then(res => {
		res = JSON.parse(res);
		console.log(res);
		optionTemplate = "<option value = %id>%fname %lname</option>";
		for(i=0; i<res.length; i++){
			optionEl = optionTemplate.replace("%id",res[i].userId).replace("%fname", res[i].firstName).replace("%lname", res[i].lastName);
			$("#employeeId").append(optionEl);
		}
	})
}

function getStatusList(){
	
	
	$.ajax({
		url: "/project1-ers/getALLItems.api",
		method: "GET"
		}).then(function(res){
			res = JSON.parse(res);
	
	
			for (i = 0; i<res.length; i++){
			newRow = $("<tr>")	
			itemNumEl = tdTemplate.replace("%val",res[i].reimbId);
			employeeEl = tdTemplate.replace("%val",res[i].authorUsername);
			AmountEl = tdTemplate.replace("%val", "$" + res[i].amount);
			itemStatusEl = tdTemplate.replace("%val",res[i].status);
			itemTypeEl = tdTemplate.replace("%val",res[i].reimbCategory);
			itemDescEl = tdTemplate.replace("%val",res[i].description);
			
			$("#statusArea").append(newRow);
			$(newRow).append(itemNumEl,employeeEl,AmountEl,itemStatusEl,itemTypeEl,itemDescEl);
		}
	})
	
}
 