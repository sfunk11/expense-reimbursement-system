$(document).ready(function(){
	
})

function getStatusList(){
	$.ajax({
		url:"/project1-ers/getAllItems.api?",
		method: "GET"
	}).then(function(res){
		console.log(res)
		for (item of res ){
		newRow = $("<tr>")	
		itemNumEl = $("<td>").text(item.reimbId);
		itemStatusEl = $("<td").text(item.status);
		itemDescEl = $("td").text(item.description);
		
		$("#statusArea").append(newRow);
		$(newRow).append(itemNumEl,itemStatusEl,ItemDescEl);
		}
	})
	
}