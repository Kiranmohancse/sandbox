
function my_button_click_handler()
{

	var urlvalue = encodeURIComponent( $('#url').val());
	if(urlvalue.trim()!=''){
		//TO DO : handle the http and https with subpage/ URL 
		alert("You have entered : "+urlvalue);
	}else{
		alert("Please add your url value first");
		return;
	}
	
	$.ajax({
        type: "POST",
        url: "/serviceUrl/"+urlvalue,
        timeout: 600000,
        dataType : "json",
        success: function (data) {
           alert("Please use this URL for any long URL  : "+"http://localhost:8080/redirect/"+data.key);
        },
        error: function (e) {

           alert("error found :"+e);

        }
    });
}