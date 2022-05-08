/**
 * 
 */

/*********个人中心页面切换******/

var f_display = function(divId){
	var c_displays =  document.querySelectorAll(".c_display");
	for(var i=0;i<c_displays.length;i++){
		c_displays[i].style.display="none";
	}
	document.querySelector("#"+divId).style.display="block";

	let token = localStorage.getItem("crowdfunding-token");
	if ('myZiliao' == divId) {
		$.ajax({
			type: 'GET',
			async: false,
			url: API_BASE_URL + '/front/user/info' ,
			contentType: "application/json",
			headers: {
				"Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
			},
			data:  '',
			dataType: 'json',
			success: function (res) {
				if (res.code == 401) {
					layer.alert("登录过期，请重新登录！！！")
				} else if (res.code == 200) {
					var user = res.data
					$("#user_ziliaoBOX").remove()
					$("#ziliaoBjBOX").append(
						`
					<ul id="user_ziliaoBOX">\t
\t\t\t            \t<li>
\t\t\t               \t\t<label class="ziLiao_form_label"><span>*</span>昵称：</label>
\t\t\t                \t<input type="text" name="user_name" value="${user.nickName}" placeholder="请输入昵称"  />
\t\t\t                </li>
\t\t\t                <li>
\t\t\t                \t<label>性别：</label>
\t\t\t\t                <div id="sex_ch">
\t\t\t\t                \t<input type="radio" name="sex" value="1" style="float:left;" ${user.sex==1 ? 'checked' : ''}/><span style="float:left;margin-top:7px;">男</span>
\t\t\t\t                    <input type="radio" name="sex" value="2" style="float:left;" ${user.sex==2 ? 'checked' : ''} /><span style="float:left;margin-top:7px;">女</span>
\t\t\t\t                    <input type="radio" name="sex" value="0" style="float:left;" ${user.sex==0 ? 'checked' : ''} /><span style="float:left;margin-top:7px;">保密</span>
\t\t\t\t                </div>
\t\t\t                </li>
\t\t\t              \t<li>
\t\t\t                \t<label><span>*</span>手机号：</label>
\t\t\t                    <input type="text" value="${user.mobile}" name="phone" placeholder="请输入手机号" />
\t\t\t                 </li>
\t\t\t                 <li>
\t\t\t                 \t<label><span>*</span>邮箱：</label>
\t\t\t                    <input type="text" value="${user.email}" name="email" placeholder="请输入邮箱" />
\t\t\t                 </li>
\t\t\t                 <li>
\t\t\t                 \t<label><span>*</span>真实姓名：</label>
\t\t\t                    <input type="text" value="${user.realName}" name="real_name" placeholder="请输入真实姓名" />
\t\t\t                 </li>
\t\t\t                 <li>
\t\t\t                 \t<label><span>*</span>身份证：</label>
\t\t\t                    <input type="text" value="${user.idNumber}" name="id_number" placeholder="请输入身份证号：" />
\t\t\t                 </li>
\t\t\t                 <li>
\t\t\t                 \t<label>生日：</label>
\t\t\t                    <input id="calender" type="text" value="${user.dateOfBirth}" name="dateOfBirth"/>
\t\t\t                 </li>
\t\t\t                 <li class="province-selector">
\t\t\t                 \t<label>所在地区：</label>
\t                            <select name="province" class=""><option>请选择</option></select>
\t                            <select name="city" class=""><option>请选择</option></select>
\t\t\t                 </li>
\t\t\t                 <li>
\t\t\t                 \t<label></label>
\t\t\t                    <input type="button" value="保存" id="ziLiao_box_submit" class="ziLiao_box_submit"/>
\t\t\t                    
\t\t\t                 </li>
\t\t\t               </ul> 
					`
					)
					ziLiaoBoxSubmitClick()
					calenderInit()
					$.ajaxSettings.async = false;
					provinceInit()
					let city = user.city.split(',')
					$("#user_ziliaoBOX select[name='province']").find('option:contains('+ city[0] +')').prop('selected', true)
					$("#user_ziliaoBOX select[name='province']").trigger('change')
					$("#user_ziliaoBOX select[name='city']").find('option:contains('+ city[1] +')').prop('selected', true)
				} else {
					layer.alert(res.message)
				}
			}
		})
	}

}


/*****头像资料切换********/
function changeTXBox(a){
	if(a==1){
		document.getElementById('ziliaoBj').style.display="";
		document.getElementById('touxiangBj').style.display="none";
	}
	else if(a==2){
		document.getElementById('ziliaoBj').style.display="none";
		document.getElementById('touxiangBj').style.display="";
	}
}

function addReceiveInfo(){
	//内容
	var receiver = $("input[name='receiver']").val();
	var phone =$("input[name='phone']").val();
	var zipcode =$("input[name='zipcode']").val();
	var address =$("select[name='province'] option:selected").text()+"|"+$("select[name='city'] option:selected").text()+"|"+$("input[name='address']").val();

	$.ajax({
		url:"addReceiveInfo",
		data:{receiver:receiver,phone:phone,zipcode:zipcode,address:address},

		async:true,
		cache:false,
		type: "POST", //请求方式为POST
		dataType:"",
		success:function(data){
			//alert(data);
			data = eval('('+data+')');

			if(data.code=="0"){
				layer.confirm(data.msg, {
					  btn: ['确定'], //按钮
					  closeBtn:0
					}, function(){
					  //layer.msg('的确很重要', {icon: 1});
						var index = parent.layer.getFrameIndex(window.name);
				        parent.layer.close(index);
						//window.location.href='showPerson.jhtml';
					});
			}else{
				var index =layer.confirm(data.msg, {
					  btn: ['确定'], //按钮
					  closeBtn:0
					}, function(){
					  //layer.msg('的确很重要', {icon: 1});
//						var index = parent.layer.getFrameIndex(window.name);
//				        parent.layer.close(index);
					//	window.location.href='showPerson.jhtml';
						layer.close(index);
					});
			}

		}
	});
}


function convertProjectState(hasAudits, hasFinish) {
	var projectState = "";
	if(hasAudits==1&&hasFinish==0){
		projectState ="正在集资";
	}
	if(hasAudits==1&&hasFinish==-1 ){
		projectState = "集资失败";
	}
	if(hasAudits==1&&hasFinish==1 ){
		projectState = "集资成功";
	}
	if(hasAudits==0 ){
		projectState = "未审核";
	}
	if(hasAudits==-1 ){
		projectState = "未通过审核";
	}
	return projectState
}

$(document).ready(function(){

	let token = localStorage.getItem('crowdfunding-token');
	let pageNum = 1
	$.ajax({
		type: 'POST',
		async: false,
		url: API_BASE_URL +'/front/project/myself' ,
		contentType: "application/json;charset=utf-8",
		headers: {
			"Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
		},
		data:  JSON.stringify({
			'pageNum': pageNum
		}),
		dataType: 'json',
		success: function (res) {
			if (res.code == 401) {
				layer.alert("登录过期，请重新登录！！！")
			} else if (res.code == 200) {
				var Ojson = res.data.result

				$("#myProject_tbody_ajax").find("tr").remove();
				for(var i=0;i<Ojson.length;i++){
					var projectState = convertProjectState(Ojson[i].hasAudits, Ojson[i].hasFinish)

					var percent=Ojson[i].hasFundRaising/Ojson[i].totalFundRaising*100;

					percent = percent.toFixed(2);



					$("#myProject_tbody_ajax").append('<tr class="trfirst"><td colspan="4"></td></tr>'
						+'<tr class="ftTr">'
						+'<td colspan="4">创建时间：'+Ojson[i].launchDateRaising+'</td>'
						+'<td><a href="javascript:;" class="ftTr_delA" title="删除"></a></td>'
						+'</tr>'
						+'<tr class="inforTr" project_id="">'
						+'<td>'
						+'<div class="ddImgBox"><a href="/pages/front/public/project.html?id=' + Ojson[i].id + '" target="_blank"><img style="width:80px;height:60px;" src="'+Ojson[i].coverPath+'"></a></div>'
						+'<div class="ddImgText"><a href="/pages/front/public/project.html?id=' + Ojson[i].id + '" target="_blank">'+Ojson[i].title+'</a></div>'
						+'</td>'
						+'<td>'
						+'<div>'
						+'<p class="inforText_p gray">'
						+projectState
						+'</p>'
						+'</div></td>'
						+'<td>'
						+'<p class="inforText_p ">目标：'+Ojson[i].totalFundRaising +'元</p>'

						+'<div class="inforRatioBox">'
						+'<div class="inforRatio"><div class="inner" style="width:'+percent+'%"></div></div>'
						+'<span>'+percent+'%</span> '
						+'</div>'
						+'</td>'
						+'<td><div><p class="inforText_p gray">不可结算</p></div></td>'
						+'<td class="btnTd">'
						+'<div class="operations">'
						+'<a href="javascript:goProjectProcess('+Ojson[i].id +');" class="ddLastbtn_A">更新最新进展</a>'

						+'</td>'
						+'</tr>');
				}
				for (var i = 0; i < res.data.pages; i++) {
					if (pageNum == (i + 1)) {
						$("#myprojectFlip").append(
							`
							<a href="javascript:;" class="fy_page cur">${i+1 }</a>
							`
						)
					} else {
						$("#myprojectFlip").append(
							`
							<a href="javascript:;" class="fy_page">${i+1 }</a>
							`
						)
					}

				}

				//registerChangePageMyProject();

			} else {
				layer.alert(res.message)
			}

		}
	});
	
	//收货地址
	
	$(".new_shdzBtn").click(function(){
		

		layer.open({
		      type: 2,
		      title: '增加收货信息',
		      shadeClose: true,
		      shade: false,
		      maxmin: false, //开启最大化最小化按钮
		      area: ['600px', '400px'],
		      content: ['ReceiveInfoPage','no']
		    });

		
	});
	
	/**
	 * 买家订单查询
	 */
	$("#myOrderShow").click(function(){
		let token = localStorage.getItem("crowdfunding-token");
		let pageNum = 1
		$.ajax({
			type: 'POST',
			async: false,
			url: API_BASE_URL + '/front/order/buyer' ,
			contentType: "application/json;charset=utf-8",
			headers: {
				"Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
			},
			data:  JSON.stringify({
				'pageNum': pageNum,
				'pageSize': 4
			}),
			dataType: 'json',
			success: function (res) {
				if (res.code == 401) {
					layer.alert("登录过期，请重新登录！！！")
				} else if (res.code == 200) {
					var Ojson = res.data.result
					$("#myOrderTbody").find("tr").remove();
					$("#myBuyOrderFlip .fy_page").remove()
					for(var i=0;i<Ojson.length;i++){

						var payState = "";
						if(Ojson[i].hasPay==0){
							payState="未支付"
						}
						if(Ojson[i].hasPay==1){
							payState="支付成功"
						}

						var projectState = convertProjectState(Ojson[i].projectVO.hasAudits, Ojson[i].projectVO.hasFinish)

						var html = '<tr class="trfirst"><td colspan="7"></td></tr>'+
							'<tr class="ftTr">'+
							'<td colspan="6">创建时间：'+Ojson[i].orderDate+
							'<span class="dingdan">订单号码：'+Ojson[i].code+'</span><span  class="dingdan">发起人：'+Ojson[i].sellerVO.username+'</span>'+
							'</td>'+
							'<td><a href="javascript:;" class="ftTr_delA" title="删除"></a></td>'+
							'</tr>'+
							'<tr class="inforTr">'+
							'<td>'+
							'<div class="ddImgBox"><a href="/pages/front/public/project.html?id=' + Ojson[i].projectVO.id + '" target="_blank"><img style="width:80px;height:60px;" src="'+Ojson[i].projectVO.coverPath+'"></a></div>'+
							'<div class="ddImgText"><a href="/pages/front/public/project.html?id=' + Ojson[i].projectVO.id + '" target="_blank">'+Ojson[i].projectVO.title+'</a></div>'+
							'</td>'+
							'<td><div><p class="inforText_p gray">'+projectState+'</p></div></td>'+
							'	<td>'+
							'<div class="ddImgText"><a href="/pages/front/public/project.html?id=' + Ojson[i].projectVO.id + '" target="_blank" title="'+Ojson[i].projectRepayVO.payTitle+'----'+Ojson[i].projectRepayVO.payContent+'">'+Ojson[i].projectRepayVO.payTitle+'----'+Ojson[i].projectRepayVO.payContent+'</a></div>'+
							'</td>'+
							'<td><div><p class="inforText_p gray">'+Ojson[i].payPrice+'元</p></div></td>'+
							'<td><div><p class="inforText_p gray">'+Ojson[i].count+'</p></div></td>'+
							'<td><div><p class="inforText_p gray">'+payState+'</p></div></td>'+
							'<td class="btnTd">'+
							'<div class="operations">';

						if(Ojson[i].hasPay==1){
							html = html+'<a href="javascript:;" class="ddLastbtn_A">确认收货</a><a href="javascript:;" class="ddLastbtn_A">我要投诉</a></td></tr>';
						}else{
							html = html+'<a href="javascript:toPay('+Ojson[i].id+');" class="ddLastbtn_A">去支付</a></td></tr>';
						}
						$("#myOrderTbody").append(html);
					}
					for (var i = 0; i < res.data.pages; i++) {
						if (pageNum == (i + 1)) {
							$("#myBuyOrderFlip").append(
								`
							<a href="javascript:;" class="fy_page cur">${i+1 }</a>
							`
							)
						} else {
							$("#myBuyOrderFlip").append(
								`
							<a href="javascript:;" class="fy_page">${i+1 }</a>
							`
							)
						}

					}

					$("#myBuyOrderFlip .fy_page").click(function(){

						var $this = $(this);
						let token = localStorage.getItem("crowdfunding-token");
						let pageNum = $this.html()
						$.ajax({
							type: 'POST',
							async: false,
							url: API_BASE_URL + '/front/order/buyer' ,
							contentType: "application/json;charset=utf-8",
							headers: {
								"Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
							},
							data:  JSON.stringify({
								'pageNum': pageNum,
								'pageSize': 4
							}),
							dataType: 'json',
							success: function (res) {
								if (res.code == 401) {
									layer.alert("登录过期，请重新登录！！！")
								} else if (res.code == 200) {

									$("#myBuyOrderFlip .fy_page").removeClass("cur");

									var Ojson = res.data.result

									$("#myOrderTbody").find("tr").remove();
									for(var i=0;i<Ojson.length;i++){

										var payState = "";
										if(Ojson[i].hasPay==0){
											payState="未支付"
										}
										if(Ojson[i].hasPay==1){
											payState="支付成功"
										}

										var projectState = convertProjectState(Ojson[i].projectVO.hasAudits, Ojson[i].projectVO.hasFinish)

										var html = '<tr class="trfirst"><td colspan="7"></td></tr>'+
											'<tr class="ftTr">'+
											'<td colspan="6">创建时间：'+Ojson[i].orderDate+
											'<span class="dingdan">订单号码：'+Ojson[i].code+'</span><span  class="dingdan">发起人：'+Ojson[i].sellerVO.username+'</span>'+
											'</td>'+
											'<td><a href="javascript:;" class="ftTr_delA" title="删除"></a></td>'+
											'</tr>'+
											'<tr class="inforTr">'+
											'<td>'+
											'<div class="ddImgBox"><a href="/pages/front/public/project.html?id=' + Ojson[i].projectVO.id + '" target="_blank"><img style="width:80px;height:60px;" src="'+Ojson[i].projectVO.coverPath+'"></a></div>'+
											'<div class="ddImgText"><a href="/pages/front/public/project.html?id=' + Ojson[i].projectVO.id + '" target="_blank">'+Ojson[i].projectVO.title+'</a></div>'+
											'</td>'+
											'<td><div><p class="inforText_p gray">'+projectState+'</p></div></td>'+
											'	<td>'+
											'<div class="ddImgText"><a href="/pages/front/public/project.html?id=' + Ojson[i].projectVO.id + '" target="_blank" title="'+Ojson[i].projectRepayVO.payTitle+'----'+Ojson[i].projectRepayVO.payContent+'">'+Ojson[i].projectRepayVO.payTitle+'----'+Ojson[i].projectRepayVO.payContent+'</a></div>'+
											'</td>'+
											'<td><div><p class="inforText_p gray">'+Ojson[i].payPrice+'元</p></div></td>'+
											'<td><div><p class="inforText_p gray">'+Ojson[i].count+'</p></div></td>'+
											'<td><div><p class="inforText_p gray">'+payState+'</p></div></td>'+
											'<td class="btnTd">'+
											'<div class="operations">';

										if(Ojson[i].is_pay==1){
											html = html+'<a href="javascript:;" class="ddLastbtn_A">确认收货</a><a href="javascript:;" class="ddLastbtn_A">我要投诉</a></td></tr>';
										}else{
											html = html+'<a href="javascript:toPay('+Ojson[i].id+');" class="ddLastbtn_A">去支付</a></td></tr>';
										}
										$("#myOrderTbody").append(html);
									}
									$this.addClass("cur");

								} else {
									layer.alert(res.message)
								}

							}
						});

					});

				} else {
					layer.alert(res.message)
				}
			}
		});
	});
	
	/**
	 * 卖家订单管理
	 */
	$("#myProjectShow").click(function(){
		let token = localStorage.getItem("crowdfunding-token");
		$.ajax({
			url			:		API_BASE_URL + "/front/order/seller",
			async		:		false,
			type: 'POST',
			contentType: "application/json;charset=utf-8",
			headers: {
				"Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
			},
			data:  JSON.stringify({
				'pageSize': 1000
			}),
			dataType: 'json',
			success: function (res) {
				Ojson = res.data.result
				if (res.code == 401) {
					layer.alert("登录过期，请重新登录！！！")
				} else if (res.code == 200) {
					$("#allOrderCount").html(res.data.total)
					$("#myProjectTbody").find("tr").remove();
					for(var i=0;i<Ojson.length;i++){

						var payState = "";
						if(Ojson[i].hasPay==0){
							payState="未支付"
						}
						if(Ojson[i].hasPay==1){
							payState="支付成功"
						}

						$("#myProjectTbody").append('<tr class="trfirst"><td colspan="4"></td></tr><tr class="u_tbg_tr" style="height:50px;margin-bottom:10px">'+
							'<td>'+Ojson[i].code+'</td>'+
							'<td>'+Ojson[i].projectVO.title+'</td>'+
							'<td>'+Ojson[i].repayVO.payTitle+'</td>'+
							'<td>'+Ojson[i].buyerVO.username+'</td>'+
							'<td>'+Ojson[i].orderDate+'</td>'+
							'<td>'+Ojson[i].count+'</td>'+
							'<td>'+Ojson[i].payPrice+'</td>'+
							'<td>'+Ojson[i].receiveInfoVO.receiver+'</td>'+
							'<td>'+Ojson[i].receiveInfoVO.address+'</td>'+
							'<td>'+Ojson[i].receiveInfoVO.phone+'</td>'+
							'<td>'+payState+'</td>'+
							'<td><a href="javascript:;" target="" class="search_btn">发货</a></td>'+

							'</tr>');
					}
				} else {
					layer.alert(res.message)
				}
				
			}
		});
	});
	
	
	
	/**
	 * 收货地址
	 */
	$("#showMyReceive").click(function(){
		let token = localStorage.getItem("crowdfunding-token");
		$.ajax({
			url			:		API_BASE_URL + "/front/receive/page",
			async		:		false,
			cache		:   	false,
			type		:		"POST",
			data		:		JSON.stringify({
				pageSize: 1000
			}),
			headers: {
				"Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
			},
			contentType	:		'application/json;charset=utf-8',
			dataType	:		"json",
			success		:		function(res){
				var Ojson = res.data.result
				$("#myReceive_tbody").find("tr").remove();
				for(var i=0;i<Ojson.length;i++){
				
					var html = '<tr class="trfirst"><td colspan="4"></td></tr>'
						+'<tr class="u_tbg_tr" style="height:50px;margin-bottom:10px">'
						+'<td>'+handleNull(Ojson[i].receiver)+'</td>'
						+'<td>'+handleNull(Ojson[i].address)+'</td>'
						+'<td>'+handleNull(Ojson[i].zippost)+'</td>'
						+'<td>'+handleNull(Ojson[i].phone)+'</td>';
					if(Ojson[i].setDefault=="0"){
						html =html +'<td><a style="color: #50abf2" onclick="setDefaultReceiveInfo('+Ojson[i].id+')">设为默认</a></td></tr>';
					}else if(Ojson[i].setDefault=="1"){
						html =html +'<td>默认</td></tr>';
					}else{
						html =html +'<td></td></tr>';
					}
					
					$("#myReceive_tbody").append(html);
							
				}
				
			}
		});
	});

	
	$("#myprojectFlip .fy_page").click(function(){

		var $this = $(this);
		let token = localStorage.getItem("crowdfunding-token");
		let pageNum = $this.html()
		$.ajax({
			type: 'POST',
			async: false,
			url: API_BASE_URL + '/front/project/myself' ,
			contentType: "application/json;charset=utf-8",
			headers: {
				"Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
			},
			data:  JSON.stringify({
				'pageNum': pageNum
			}),
			dataType: 'json',
			success: function (res) {
				if (res.code == 401) {
					layer.alert("登录过期，请重新登录！！！")
				} else if (res.code == 200) {

					$("#myprojectFlip .fy_page").removeClass("cur");

					$("#myProject_tbody_ajax").find("tr").remove();

					var Ojson = res.data.result

					$("#myProject_tbody_ajax").find("tr").remove();
					for(var i=0;i<Ojson.length;i++){
						var projectState = "";
						if(Ojson[i].hasAudits==1&&Ojson[i].hasFinish==0){
							projectState ="正在集资";
						}
						if(Ojson[i].hasAudits==1&&Ojson[i].hasFinish==-1 ){
							projectState = "集资失败";
						}
						if(Ojson[i].hasAudits==1&&Ojson[i].hasFinish==1 ){
							projectState = "集资成功";
						}
						if(Ojson[i].hasAudits==0 ){
							projectState = "未审核";
						}
						if(Ojson[i].hasAudits==-1 ){
							projectState = "未通过审核";
						}

						var percent=Ojson[i].hasFundRaising/Ojson[i].totalFundRaising*100;

						percent = percent.toFixed(2);



						$("#myProject_tbody_ajax").append('<tr class="trfirst"><td colspan="4"></td></tr>'
							+'<tr class="ftTr">'
							+'<td colspan="4">创建时间：'+Ojson[i].launchDateRaising+'</td>'
							+'<td><a href="javascript:;" class="ftTr_delA" title="删除"></a></td>'
							+'</tr>'
							+'<tr class="inforTr" project_id="">'
							+'<td>'
							+'<div class="ddImgBox"><a href="/pages/front/public/project.html?id=' + Ojson[i].id + '" target="_blank"><img style="width:80px;height:60px;" src="'+Ojson[i].coverPath+'"></a></div>'
							+'<div class="ddImgText"><a href="/pages/front/public/project.html?id=' + Ojson[i].id + '" target="_blank">'+Ojson[i].title+'</a></div>'
							+'</td>'
							+'<td>'
							+'<div>'
							+'<p class="inforText_p gray">'
							+projectState
							+'</p>'
							+'</div></td>'
							+'<td>'
							+'<p class="inforText_p ">目标：'+Ojson[i].totalFundRaising +'元</p>'

							+'<div class="inforRatioBox">'
							+'<div class="inforRatio"><div class="inner" style="width:'+percent+'%"></div></div>'
							+'<span>'+percent+'%</span> '
							+'</div>'
							+'</td>'
							+'<td><div><p class="inforText_p gray">不可结算</p></div></td>'
							+'<td class="btnTd">'
							+'<div class="operations">'
							+'<a href="javascript:goProjectProcess('+Ojson[i].id +');" class="ddLastbtn_A">更新最新进展</a>'

							+'</td>'
							+'</tr>');
					}
					$this.addClass("cur");

				} else {
					layer.alert(res.message)
				}

			}
		});

	});

	/**
	 * 修改密码
	 */
	$("#ziLiao_box_password_submit").click(function(){

		var oldPassword = $("#user_ziliao_passwordBOX input[name='oldPassword']").val();
		var newPassword = $("#user_ziliao_passwordBOX input[name='newPassword']").val();
		var newPassword2 = $("#user_ziliao_passwordBOX input[name='newPassword2']").val();

		if (newPassword!=newPassword2) {
			layer.alert('新密码两次不一致')
			return
		}

		let token = localStorage.getItem("crowdfunding-token");

		$.ajax({
			url: API_BASE_URL + "/front/user/updatePassword",
			data: JSON.stringify({
				oldPassword: oldPassword,
				newPassword: newPassword
			}),
			contentType: 'application/json;charset=utf-8',
			headers: {
				'Authorization': 'Bearer ' + token ? ('Bearer ' + JSON.parse(token).token) : ''
			},
			async: false,
			cache: false,
			type: "POST",
			dataType: "json",
			success: function(res){
				if (res.code == 401) {
					layer.alert("登录过期，请重新登录！！！")
				} else if (res.code == 200) {
					localStorage.removeItem("crowdfunding-token");
					layer.confirm('修改成功，请重新登录？', {
						btn: ['确定'], //按钮
						closeBtn: 0
					}, function(){
						location.href = '/pages/front/public/login.html'
					});
				} else {
					layer.alert(res.message)
				}
			}
		});

})

})

//更新项目最新状态
function goProjectProcess(project_id){

	layer.open({
	      type: 2,
	      title: '发布项目最新动态',
	      shadeClose: true,
		scrollbar: true,
	      shade: false,
	      maxmin: true, //开启最大化最小化按钮
	      area: ['893px', '600px'],
	      content: ['/pages/front/private/project_process.html?project_id='+project_id,'yes']
	    });

	
}

// 设为默认收货地址
function setDefaultReceiveInfo(receiveId){
	let token = localStorage.getItem("crowdfunding-token");
	$.ajax({
		url			:		API_BASE_URL + "/front/receive/update",
		async		:		false,
		cache		:   	false,
		type		:		"POST",
		data		:		JSON.stringify({
			id: receiveId,
			setDefault: 1
		}),
		headers: {
			"Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
		},
		contentType	:		'application/json;charset=utf-8',
		dataType	:		"json",
		success		:		function(res){
			if (res.code == 200) {
				layer.confirm('设置成功', {
					btn: ['确定'], //按钮
					closeBtn: 0
				}, function(){
					var index = layer.alert();
					layer.close(index);
					$("#showMyReceive").trigger('click')
				});
			} else {
				layer.alert(res.message)
			}
		}
	});
}

//去支付
function toPay(order_id){
	
	layer.confirm('确定要付款吗？', {
		  btn: ['确定','取消'], //按钮
		 // closeBtn:0
		}, function(){
		let token = localStorage.getItem("crowdfunding-token");
		$.ajax({
			url			:		API_BASE_URL + "/front/order/pay",
			data		:		JSON.stringify({
				'subjectId': order_id
			}),
			headers: {
				"Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
			},
			async		:		false,
			type		:		"POST",
			dataType	:		"json",
			contentType: "application/json;charset=utf-8",
			success		:		function(res){
				//data = eval('('+data+')');
				//alert(data);
				if(res.code==200){
					layer.confirm(res.message, {
						  btn: ['确定'], //按钮
						  closeBtn:0
						}, function(){
						window.location.href='/pages/front/private/personal_info.html';
						});
				}else{
					var index =layer.confirm(res.message, {
						  btn: ['确定'], //按钮
						  closeBtn:0
						}, function(){
							var index = layer.alert();
							layer.close(index);
						});
				}
			}
		});
	
});

}

function ziLiaoBoxSubmitClick() {
	$("#ziLiao_box_submit").click(function(){

		var sex = $("#user_ziliaoBOX input[name='sex']:checked").val();//性别
		var user_name = $("#user_ziliaoBOX input[name='user_name']").val();//用户名
		var phone = $("#user_ziliaoBOX input[name='phone']").val();//手机
		var email = $("#user_ziliaoBOX input[name='email']").val();//邮箱
		var real_name = $("#user_ziliaoBOX input[name='real_name']").val();//真实姓名
		var id_number = $("#user_ziliaoBOX input[name='id_number']").val();//身份证号码
		var dateOfBirth = $("#user_ziliaoBOX input[name='dateOfBirth']").val();//身份证号码
		var city = $("#user_ziliaoBOX select[name='province'] option:selected").text() + ',' + $("#user_ziliaoBOX select[name='city'] option:selected").text();

		let token = localStorage.getItem("crowdfunding-token");

		$.ajax({
			url: API_BASE_URL + "/front/user/update",//要请求的服务器url
			//这是一个对象，表示请求的参数，两个参数：method=ajax&val=xxx，服务器可以通过request.getParameter()来获取
			//data:{method:"ajaxTest",val:value},
			data: JSON.stringify({
				sex: sex,
				nickName: user_name,
				mobile: phone,
				email: email,
				realName: real_name,
				idNumber: id_number,
				dateOfBirth: dateOfBirth,
				city: city

			}),
			contentType: 'application/json;charset=utf-8',
			headers: {
				'Authorization': 'Bearer ' + token ? ('Bearer ' + JSON.parse(token).token) : ''
			},
			async: false,   //是否为异步请求
			cache: false,  //是否缓存结果
			type: "POST", //请求方式为POST
			dataType: "json",   //服务器返回的数据是什么类型
			success: function(res){  //这个方法会在服务器执行成功是被调用 ，参数result就是服务器返回的值(现在是json类型)
				if (res.code == 401) {
					layer.alert("登录过期，请重新登录！！！")
				} else if (res.code == 200) {
					layer.alert('修改成功')
				} else {
					layer.alert(res.message)
				}
			}
		});

	})
}

function calenderInit() {
	//初始化日历
	calender('#calender').init({
		//date : [2015,12,12], //设置默认显示年月日，默认当前年月日
		format : 'yyyy-MM-dd', //设置显示格式
		button : false ,//是否显示按钮
		left : 0, //追加left，默认0
		top :0, //追加top，默认0
		onload : function(){  } //初始化完成执行，this为当前创建的日历对象
	},function(date){
		//回调函数
		this.value = date  ;
	});
}

function provinceInit() {
	getProvince(0,$("select[name='province']"));//所有省份
	$("select[name='province']").change(function(){
		getProvince($(this).val(),$("select[name='city']"));
	});

	$("select[name='city']").change(function(){
		getProvince($(this).val(),$("select[name='district']"));
	});
}


