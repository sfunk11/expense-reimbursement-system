$(document).ready(function(){
	getStatusList();
	
})
	


 tdTemplate = "<td class = 'list-group-item>'> %val </td>";

function getStatusList(){
	event.preventDefault();
	
	$.ajax({
		url: "/project1-ers/getAllItems.api",
		method: "GET"
		}).then(function(res){
			$("#statusArea").empty()
			for (i = 0; i<res.length; i++){
			newRow = $("<tr>")	
			itemNumEl = tdTemplate.replace("%val",res[i].reimbId);
			AmountEl = tdTemplate.replace("%val", "$" + res[i].amount);
			itemStatusEl = tdTemplate.replace("%val",res[i].status);
			itemTypeEl = tdTemplate.replace("%val",res[i].reimbCategory);
			itemDescEl = tdTemplate.replace("%val",res[i].description);
			if($("#status").val() == res[i].reimbStatus || $("#status").val() == 0){
			$("#statusArea").append(newRow);

			$(newRow).append(itemNumEl,AmountEl,itemStatusEl,itemTypeEl,itemDescEl);
		}}
	
	})
	
}

function newItemFormSubmit(){
	
	itemData = {
		amount: $("#amount").val(),
		description: $("#description").val(),
		reimbType: $("#reimbType").find("option:selected").val()
	}
	
	$.ajax({
	url:  "/project1-ers/newItem.api",
	method: "POST",
		data: itemData
	})
		.then((res) =>{
			
		alert("Item added Successfully");
			$("#statusArea").empty();
		getStatusList();
		
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
