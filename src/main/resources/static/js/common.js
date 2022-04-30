/**
 * 
 */

function getProvince(pid,obj){
	$.get("getProvince?pid="+pid,function(data){
		date =  eval('(' + data + ')');
		obj.find("option").remove();
		obj.append('<option value="-1">请选择</option>');
		for(var i in date){
			obj.append('<option value="'+date[i].id+'">'+date[i].name+'</option>');
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