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
			url: API_BASE_URL + '/admin/sys-user/login' ,
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
					localStorage.setItem('sys-crowdfunding-token', JSON.stringify(res.data))
					// 请求成功后跳转到首页
					location.href = '/pages/admin/v1/main.html'
				}
			}
		});
	})
	
	/**
	 * 用户管理
	 */
	$("#user_menu").click(function(){
		let token = localStorage.getItem("sys-crowdfunding-token");
		$.ajax({	
			url: API_BASE_URL + "/admin/user/page",
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

				if (res.code == 401) {
					layer.alert("登录过期，请重新登录！！！")
					return
				}

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
	 * 订单管理
	 */
	$("#dingdan_menu").click(function(){
		let token = localStorage.getItem("sys-crowdfunding-token");
		$.ajax({
			url: API_BASE_URL + "/admin/order/page",
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

				if (res.code == 401) {
					layer.alert("登录过期，请重新登录！！！")
					return
				}

				var user = res.data.result
				var a = 0;
				$("#dingdan_tbody tr").remove()
				$("#dingdan_tbody").append('<tr class="trfirst"><td colspan="9"></td></tr>')
				for(var i=0;i<user.length;i++){

					$("#dingdan_tbody").append(
						"<tr class='u_tbg_tr'>"+
						"<td><input name='user' user_index="+user[i].id+" class='table_checkbox' type='checkbox'></td>"+
						"<td>"+handleNull(user[i].code)+"</td>"+
						"<td>"+handleNull(user[i].projectVO.title)+"</td>"+
						"<td>"+handleNull(user[i].payPrice)+"</td>"+
						"<td>"+handleNull(user[i].receiveInfoVO.receiver)+"</td>"+
						"<td>"+handleNull(user[i].receiveInfoVO.address)+"</td>"+
						"<td>"+handleNull(user[i].receiveInfoVO.phone)+"</td>"+
						"<td>"+handleNull(user[i].orderDate)+"</td>"+
						"<td>"+handleNull(user[i].hasPay)+"</td>"+
						"</tr>");

				}
			}
		});


	})


	/**
	 * 项目类别管理
	 */
	$("#category_menu").click(function(){
		let token = localStorage.getItem("sys-crowdfunding-token");
		$.ajax({
			url: API_BASE_URL + "/admin/category/page",
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

				if (res.code == 401) {
					layer.alert("登录过期，请重新登录！！！")
					return
				}

				var user = res.data.result
				var a = 0;
				$("#category_tbody tr").remove()
				$("#category_tbody").append('<tr class="trfirst"><td colspan="9"></td></tr>')
				for(var i=0;i<user.length;i++){

					$("#category_tbody").append(
						"<tr class='u_tbg_tr'>"+
						"<td><input name='user' user_index="+user[i].id+" class='table_checkbox' type='checkbox'></td>"+
						"<td>"+handleNull(user[i].id)+"</td>"+
						"<td>"+handleNull(user[i].categoryName)+"</td>"+
						"<td>"+handleNull(user[i].note)+"</td>"+
						"<td>"+handleNull(user[i].createDate)+"</td>"+
						"<td>"+handleNull(user[i].changeDate)+"</td>"+
						"<td></td>"+
						"<td>"+handleNull(user[i].changePerson)+"</td>"+
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
		let token = localStorage.getItem("sys-crowdfunding-token");
		$.ajax({
			url: API_BASE_URL + "/admin/project/page",
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
				var project = res.data.result
				$("#project_tbody tr").remove()
			for(var i=0;i<project.length;i++) {
				var hasAudit = (project[i].hasAudits == '1' ? '是' : '否')
				var hasFinish = (project[i].hasFinish == '1' ? '是' : '否')
				$("#project_tbody").append(
					"<tr class='p_tbg_tr'>"+
					"<td><input user_index="+handleNull(project[i].id)+" class='table_checkbox' type='checkbox'></td>"+
					"<td>"+handleNull(project[i].id)+"</td>"+
					"<td>"+handleNull(project[i].seller.username)+"</td>"+
					"<td>"+handleNull(project[i].categoryVO.categoryName)+"</td>"+
					"<td>"+handleNull(project[i].title)+"</td>"+
					"<td>"+handleNull(project[i].totalFundRaising)+"</td>"+
					"<td>"+handleNull(project[i].daysRaising)+"</td>"+
					"<td>"+handleNull(project[i].hasFundRaising)+"</td>"+
					"<td>"+handleNull(project[i].launchDateRaising)+"</td>"+
					"<td>"+hasFinish+"</td>"+
					"<td>"+hasAudit+"</td>"+
					"<td>查看</td>"+
					"</tr>");
			}
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
	$("#hasFinishProject").hide();
	$("#project_menu").trigger('click')
}
function projectNo(){
	$("#all_projects").hide();
	$("#hasFinishProject").hide();
	$("#no_audits").show();
	let token = localStorage.getItem("sys-crowdfunding-token");
	$.ajax({
		url: API_BASE_URL + "/admin/project/page",
		async: false,   //是否为异步请求
		type: "POST", //请求方式为POST);
		contentType: "application/json;charset=utf-8",
		data: JSON.stringify({
			'pageSize': 1000,
			'projectVO': {
				'hasAudits': 0
			}
		}),
		headers: {
			"Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
		},
		dataType: "json",   //服务器返回的数据是什么类型
		success: function(res){
			var project = res.data.result
			$("#project_tbody_audits tr").remove()
			for(var i=0;i<project.length;i++) {
				var hasAudit = (project[i].hasAudits == '1' ? '是' : '否')
				var hasFinish = (project[i].hasFinish == '1' ? '是' : '否')
				$("#project_tbody_audits").append(
					"<tr class='p_tbg_tr'>"+
					"<td><input user_index="+handleNull(project[i].id)+" class='table_checkbox' type='checkbox'></td>"+
					"<td>"+handleNull(project[i].id)+"</td>"+
					"<td>"+handleNull(project[i].seller.username)+"</td>"+
					"<td>"+handleNull(project[i].categoryVO.categoryName)+"</td>"+
					"<td>"+handleNull(project[i].title)+"</td>"+
					"<td>"+handleNull(project[i].totalFundRaising)+"</td>"+
					"<td>"+handleNull(project[i].daysRaising)+"</td>"+
					"<td>"+handleNull(project[i].hasFundRaising)+"</td>"+
					"<td>"+handleNull(project[i].launchDateRaising)+"</td>"+
					"<td>"+hasFinish+"</td>"+
					"<td>"+hasAudit+"</td>"+
					"<td><a target='_blank' style='color: gold' href='/pages/admin/v1/shenhe.html?id="+project[i].id+"'>去审核</a></td>"+
					"</tr>");
			}
		}
	});
}

function projectFinish(){
	$("#all_projects").hide();
	$("#no_audits").hide();
	$("#hasFinishProject").show();
	let token = localStorage.getItem("sys-crowdfunding-token");
	$.ajax({
		url: API_BASE_URL + "/admin/project/page",
		async: false,   //是否为异步请求
		type: "POST", //请求方式为POST);
		contentType: "application/json;charset=utf-8",
		data: JSON.stringify({
			'pageSize': 1000,
			'projectVO': {
				'hasFinish': 1
			}
		}),
		headers: {
			"Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
		},
		dataType: "json",   //服务器返回的数据是什么类型
		success: function(res){
			var project = res.data.result
			$("#project_tbody_finish tr").remove()
			for(var i=0;i<project.length;i++) {
				var hasAudit = (project[i].hasAudits == '1' ? '是' : '否')
				var hasFinish = (project[i].hasFinish == '1' ? '是' : '否')
				$("#project_tbody_finish").append(
					"<tr class='p_tbg_tr'>"+
					"<td><input user_index="+handleNull(project[i].id)+" class='table_checkbox' type='checkbox'></td>"+
					"<td>"+handleNull(project[i].id)+"</td>"+
					"<td>"+handleNull(project[i].seller.username)+"</td>"+
					"<td>"+handleNull(project[i].categoryVO.categoryName)+"</td>"+
					"<td>"+handleNull(project[i].title)+"</td>"+
					"<td>"+handleNull(project[i].totalFundRaising)+"</td>"+
					"<td>"+handleNull(project[i].daysRaising)+"</td>"+
					"<td>"+handleNull(project[i].hasFundRaising)+"</td>"+
					"<td>"+handleNull(project[i].launchDateRaising)+"</td>"+
					"<td>"+hasFinish+"</td>"+
					"<td>"+hasAudit+"</td>"+
					"<td>查看</td>"+
					"</tr>");
			}
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
	let token = localStorage.getItem("sys-crowdfunding-token");
	$.ajax({
		url: API_BASE_URL + "/admin/sys-user/page",
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
		