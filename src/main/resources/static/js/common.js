/**
 * 
 */

// const STATIC_BASE_URL = '/'

var API_BASE_URL = ''

function getProvince(pid,obj){
	$.get("/general/region?pid="+pid,function(res){
		data = res.data
		obj.find("option").remove();
		obj.append('<option value="-1">请选择</option>');
		for(var i in data){
			obj.append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
		}
	});
}
function getQueryVariable(variable) {

	var query = window.location.search.substring(1);

	var vars = query.split("&");

	for (var i=0;i<vars.length;i++) {

		var pair = vars[i].split("=");

		if(pair[0] == variable){return pair[1];}

	}

	return('');

}