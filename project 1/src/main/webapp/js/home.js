$(document).ready(function(){
	getStatusList();
	
})

 liTemplate = "<td class = 'list-group-item>'> %val </td>";

function getStatusList(){
	$.ajax({
		url:"/project1-ers/getAllItems.api",
		method: "GET"
	}).then(function(res){
		res = JSON.parse(res);
		console.log(res)
		console.log(res.length);
		for (i = 0; i<res.length; i++){
		newRow = $("<tr>")	
		itemNumEl = liTemplate.replace("%val",res[i].reimbId);
		itemStatusEl = liTemplate.replace("%val",res[i].reimbStatus);
		itemDescEl = liTemplate.replace("%val",res[i].description);
		
		$("#statusArea").append(newRow);
		$(newRow).append(itemNumEl,itemStatusEl,itemDescEl);
		}
	})
	
}
