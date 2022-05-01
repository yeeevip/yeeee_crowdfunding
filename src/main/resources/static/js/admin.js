/**
 * 
 */
/*****菜单切换**********///
var f_display = function(divId){
	var c_displays =  document.querySelectorAll(".c_display");
	for(var i=0;i<c_displays.length;i++){
		//console.log(c_displays);
		c_displays[i].style.display="none";
	}
	document.querySelector("#"+divId).style.display="";
}


$(document).ready(function(){
	
	// 登录
	$("#adminLoginBtn").click(function () {
		$.ajax({
			type: 'POST',
			async: false,
			url: '/sys-user/login' ,
			//contentType: "application/json",
			data:  {
				'username': $("#username").val(),
				'password': $("#password").val()
			},
			dataType: 'json',
			success: function (res) {
				if (res.code != 200) {
					layer.alert(res.message)
				} else {
					// 将token存储到本地
					localStorage.setItem('sys-token', JSON.stringify(res.data))
					// 请求成功后跳转到首页
					location.href = '/pages/admin/main.html'
				}
			}
		});
	})
	
	/**
	 * 用户管理
	 */
	$("#user_menu").click(function(){
		let token = localStorage.getItem("sys-token");
		$.ajax({	
			url:"/user/page/list",
			async: false,   //是否为异步请求
			type: "POST", //请求方式为POST);
			contentType: "application/json;charset=utf-8",
			data: JSON.stringify({
				'pageSize': 1000
			}),
			headers: {
				"Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
			},
			dataType: "json",   //服务器返回的数据是什么类型
			success: function(res){
				var user = res.data.result
				var a = 0;
				$("#user_tbody tr").remove()
				$("#user_tbody").append('<tr class="trfirst"><td colspan="9"></td></tr>')
				for(var i=0;i<user.length;i++){

				$("#user_tbody").append(
						"<tr class='u_tbg_tr'>"+
						"<td><input name='user' user_index="+user[i].id+" class='table_checkbox' type='checkbox'></td>"+
						"<td>"+handleNull(user[i].id)+"</td>"+
						"<td>"+handleNull(user[i].username)+"</td>"+
						"<td>"+handleNull(user[i].email)+"</td>"+
						"<td>"+handleNull(user[i].sex)+"</td>"+
						"<td>"+handleNull(user[i].realName)+"</td>"+
						"<td>"+handleNull(user[i].idNumber)+"</td>"+
						"<td>"+handleNull(user[i].age)+"</td>"+
						"<td>"+handleNull(user[i].mobile)+"</td>"+
						"<td>"+handleNull(user[i].dateOfRegistration)+"</td>"+
					"</tr>");
	//				$("#table_checkboxBOx").append("<div class='table_checkbox_box'>"+
	//			    						"<input user_index="+user[i].id+" class='table_checkbox' type='checkbox' >"+
	//			"</div>");
	//
	//				$(".table_checkbox_box").height($(".u_tbg_tr:eq(i)").height());

				}
			}
		});

		
	})
			
	/**
	 * 用户全选取消
	 */
		$("#u_allCheck").click(function(){

//			alert($("#u_allCheck").prop("checked"));
		if($("#u_allCheck").prop("checked"))
				$(".u_tbg_tr input[name='user']").attr("checked","checked");
		else
			$(".u_tbg_tr input[name='user']").removeAttr("checked","checked");
			

		})
	
	/**
	 * 项目管理
	 */
	$("#project_menu").click(function(){
		

		$.ajax({	
		url:"adminProject.jhtml",
		async: true,   //是否为异步请求
	    cache: false,  //是否缓存结果
	    type: "POST", //请求方式为POST);
	    dataType: "json",   //服务器返回的数据是什么类型 
		success: function(project){
			console.log(project[1].projectcategory);
			if($(".p_tbg_tr").length == project.length) return;
			for(var i=0;i<project.length;i++)
			$("#project_tbody").append(
					"<tr class='p_tbg_tr'>"+
					"<td><input user_index="+project[i].project_id+" class='table_checkbox' type='checkbox'></td>"+
            		"<td>"+project[i].project_id+"</td>"+
            		"<td>"+project[i].user_id+"</td>"+
            		"<td>"+project[i].projectcategory.category_name+"</td>"+
            		"<td>"+project[i].title+"</td>"+
            		"<td>"+project[i].total_fund_raising+"</td>"+
            		"<td>"+project[i].days_raising+"</td>"+
            		"<td>"+project[i].has_fund_raising+"</td>"+
            		"<td></td>"+
            		"<td></td>"+
            		"<td>"+project[i].is_audits+"</td>"+
            		"<td>查看</td>"+
            	"</tr>");
		}
	});
	})
	
		/**
	 * 项目全选取消
	 */
		$("#p_allCheck").click(function(){

//			alert($("#u_allCheck").prop("checked"));
		if($("#p_allCheck").prop("checked"))
				$(".p_tbg_tr input").attr("checked","checked");
		else
			$(".p_tbg_tr input").removeAttr("checked","checked");
			

		})
		
	
		
	
	
})

function handleNull(val) {
	return val ? val : ""
}

/**
	 * 项目管理tab切换
	 */
function projectAll(){
	$("#all_projects").show();
	$("#no_audits").hide();
}
function projectNo(){
$("#all_projects").hide();
$("#no_audits").show();

$.ajax({
	url		:		"auditsProject.jhtml",
	async	:		true,
	cache	:		false,
	type	:		"get",
	dataType:		"json",
	success	:		function(project){

		if($(".p_tbg_tr.shenhe").length == project.length) return;
		for(var i=0;i<project.length;i++)

		$("#project_tbody_audits").append(
				"<tr class='p_tbg_tr shenhe'>"+
				"<td><input user_index="+project[i].project_id+" class='table_checkbox' type='checkbox'></td>"+
				"<td>"+project[i].project_id+"</td>"+
				"<td>"+project[i].user_id+"</td>"+
				"<td>"+project[i].projectcategory.category_name+"</td>"+
				"<td>"+project[i].title+"</td>"+
				"<td>"+project[i].total_fund_raising+"</td>"+
				"<td>"+project[i].days_raising+"</td>"+
				"<td>"+project[i].has_fund_raising+"</td>"+
				"<td></td>"+
				"<td></td>"+
				"<td>"+project[i].is_audits+"</td>"+
				"<td><a target='_blank' href='shenhe.jhtml?id="+project[i].project_id+"'>去审核</a></td>"+
			"</tr>");

	}
});
}

/**
 * 用户管理tab切换
 */
function frontUser(){
	$("#frontUser").show();
	$("#systemUser").hide();
	$("#user_menu").trigger('click')
}
function systemUser(){
	$("#frontUser").hide();
	$("#systemUser").show();
	let token = localStorage.getItem("sys-token");
	$.ajax({
		url:"/sys-user/page/list",
		async: false,   //是否为异步请求
		type: "POST", //请求方式为POST);
		contentType: "application/json;charset=utf-8",
		data: JSON.stringify({
			'pageSize': 1000
		}),
		headers: {
			"Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
		},
		dataType: "json",   //服务器返回的数据是什么类型
		success: function(res){
			var user = res.data.result
			var a = 0;
			$("#user_tbody_sysuser tr").remove()
			for(var i=0;i<user.length;i++){
				$("#user_tbody_sysuser").append(
					"<tr class='u_tbg_tr'>"+
					"<td><input name='user' user_index="+user[i].id+" class='table_checkbox' type='checkbox'></td>"+
					"<td>"+handleNull(user[i].id)+"</td>"+
					"<td>"+handleNull(user[i].username)+"</td>"+
					"<td>"+handleNull(user[i].email)+"</td>"+
					"<td>"+handleNull(user[i].sex)+"</td>"+
					"<td>"+handleNull(user[i].realName)+"</td>"+
					"<td>"+handleNull(user[i].idNumber)+"</td>"+
					"<td>"+handleNull(user[i].age)+"</td>"+
					"<td>"+handleNull(user[i].mobile)+"</td>"+
					"<td>"+handleNull(user[i].dateOfRegistration)+"</td>"+
					"</tr>");

			}
		}
	});
}
		