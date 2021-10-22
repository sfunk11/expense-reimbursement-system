
function logIn(){
	event.preventDefault();
	
	$.ajax({
		url: "/project1-ers/login.view",
		method: "POST",
		cache: false
	}).then((res) => {
		 
	  localStorage.setItem("loggedIn",true);

	})
}