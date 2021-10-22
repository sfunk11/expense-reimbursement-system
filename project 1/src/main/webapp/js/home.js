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
				date = new Date(res[i].submittedAt);
				formattedSubmitDate = (date.getMonth()+1)+ "/"+date.getDate()+"/"+date.getFullYear()+ " "+date.getHours()+
         		":"+date.getMinutes()+":"+date.getSeconds();
          date = new Date(res[i].resolvedAt);
				formattedResolveDate = (date.getMonth()+1)+
          "/"+date.getDate()+
          "/"+date.getFullYear()+
          " "+date.getHours()+
          ":"+(date.getMinutes()<10? '0':'') + date.getMinutes()+
          ":"+date.getSeconds();
			newRow = $("<tr>")	
			itemNumEl = tdTemplate.replace("%val",res[i].reimbId);
			submittedEl = tdTemplate.replace("%val", formattedSubmitDate);
			AmountEl = tdTemplate.replace("%val", "$" + res[i].amount);
			itemStatusEl = tdTemplate.replace("%val",res[i].status);
			if(res[i].reimbStatus != 1){
				resolvedEl = tdTemplate.replace("%val", formattedResolveDate);
			}else{
				resolvedEl = tdTemplate.replace("%val", "  ");
			}
			itemTypeEl = tdTemplate.replace("%val",res[i].reimbCategory);
			itemDescEl = tdTemplate.replace("%val",res[i].description);
			if($("#status").val() == res[i].reimbStatus || $("#status").val() == 0){
			$("#statusArea").append(newRow);

			$(newRow).append(itemNumEl,submittedEl, AmountEl,itemStatusEl, resolvedEl,itemTypeEl,itemDescEl);
		}}
	
	})
	
}

function newItemFormSubmit(){
	
	let myForm = document.getElementById('newItem');
	console.log(myForm);
	
	itemData = new FormData(myForm);
	
for (var pair of itemData.entries()) {
    console.log(pair[0]+ ', ' + pair[1]); 
}
	
	$.ajax({
	url:  "/project1-ers/newItem.api",
	method: "POST",
		data: itemData,
		processData: false,
    	contentType: false
	})
		.then((res) =>{
			
		alert("Item added Successfully");
		$("#newItem")[0].reset();
		$("#statusArea").empty();
		getStatusList();
		
	})
	
}

function logOut(){
	$.ajax({
		url: "/project1-ers/logout.view",
		method: "GET",
		cache: false
	}).then((res) => {
		
	    history.pushState(null, null, null);
	    window.addEventListener('popstate', function () {
	        history.pushState(null, null, null);
	    });

	})
}
