$(document).ready(function(){
	getStatusList();
	
})

 tdTemplate = "<td class = 'list-group-item>'> %val </td>";

function getStatusList(){
	
	
	$.ajax({
		url: "/project1-ers/getAllItems.api",
		method: "GET"
		}).then(function(res){
			res = JSON.parse(res);
			console.log(res);
	
			for (i = 0; i<res.length; i++){
			newRow = $("<tr>")	
			itemNumEl = tdTemplate.replace("%val",res[i].reimbId);
			AmountEl = tdTemplate.replace("%val", "$" + res[i].amount);
			itemStatusEl = tdTemplate.replace("%val",res[i].status);
			itemTypeEl = tdTemplate.replace("%val",res[i].reimbCategory);
			itemDescEl = tdTemplate.replace("%val",res[i].description);
			
			$("#statusArea").append(newRow);
			$(newRow).append(itemNumEl,AmountEl,itemStatusEl,itemTypeEl,itemDescEl);
		}
	})
	
}

function newItemFormSubmit(){
	
	itemData = {
		amount: $("#amount").val(),
		description: $("#description").val(),
		reimbType: $("#reimbType").find("option:selected").val()
	}
	console.log(itemData);
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
