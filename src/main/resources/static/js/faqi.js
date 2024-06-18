/**
 * 发起项目 提交信息到后台
 */


$(document).ready(function(){

	var coverUploader = new WebUploader.Uploader({
		auto: true,
		fileVal: 'file',
		swf: 'js/webuploader-0.1.5/Uploader.swf',
		server: '/general/upload?path=project/cover',
		pick: '#set_cover',
		accept: {
			title: 'Images',
			extensions: 'gif,jpg,jpeg,bmp,png',
			mimeTypes: 'image/jpg,image/jpeg,image/png'
		}
	});

	coverUploader.on("uploadSuccess", function (file, res){
		console.log(res);
		$("#cover_show").remove();
		$("#show_cover").prepend('<img id="cover_show" width="400px" height="300px" src=' + res.data + ' />');
	})
	
    /**
     * 详情图片
     */
	$(".uploadPic").each(function(){

		 var div = $(this).parent().parent().parent().parent().parent().parent().find(".upload_detital_pic");
		 var $this = $(this);

		 var detailUploader = new WebUploader.Uploader({
			 auto: true,
			 fileVal: 'file',
			 swf: 'js/webuploader-0.1.5/Uploader.swf',
			 server: '/general/upload?path=project/item',
			 pick: $this,
			 accept: {
				 title: 'Images',
				 extensions: 'gif,jpg,jpeg,bmp,png',
				 mimeTypes: 'image/jpg,image/jpeg,image/png'
			 }
		 });

		 detailUploader.on("uploadSuccess", function (file, res){
			 console.log(res);
			 div.append('<img src=' + res.data + ' />');
		 })

	 });

	/**
	 * 身份证 公司 图片
	 */
	$(".uplodify_in_repeat_0person").each(function(){

			 var div = $(this).parent().parent().find(".material-list-pic");

			 var $this = $(this);

			 var personIdentityUploader = new WebUploader.Uploader({
				 auto: true,
				 fileVal: 'file',
				 swf: 'js/webuploader-0.1.5/Uploader.swf',
				 server: '/general/upload?path=project/IDphoto',
				 pick: $this,
				 accept: {
					 title: 'Images',
					 extensions: 'gif,jpg,jpeg,bmp,png',
					 mimeTypes: 'image/jpg,image/jpeg,image/png'
				 }
			 });

			 personIdentityUploader.on("uploadSuccess", function (file, res){
				 console.log(res);
				 div.empty();
				 div.append('<img id="shenfenxinxi" src=' + res.data+ ' />');
			 })
		 });
	
	//项目类型选项
	$(".fq_lxItem a").click(function(){
		$(".fq_lxItem a").removeClass("cur");
		$(this).addClass("cur");
	})
	//表单填写切换
	$(".fq_topBox li").click(function(){
		$(".fq_topBox li").removeClass("curr")
		$(this).addClass("curr");
	});

	/**
	 * 提交
	 */
    
	$(".submitSH").click(function(){
		
		
		layer.confirm('确定要提交项目吗？', {
			  btn: ['确定','取消'], //按钮
			 // closeBtn:0
			}, function(){
			  //layer.msg('的确很重要', {icon: 1});

					addTextArray();//保存开始的文本

					textToHtml($("#detail_info textarea")); //textare的value转htnl


			    	var basicInfo = $("#basis_info").serializeJson();//数据序列化
					basicInfo.projectType = $(".fq_lxItem a.cur").attr("index");//项目类别
					basicInfo.shenfen = $(".identity-type-hd a.cur").html();//项目类别
					basicInfo.coverPath = $("#cover_show").attr("src");//项目封面路径

			    	var detailInfo = $("#detail_info").serializeJson();//数据序列化

			    	var repayInfo = $("#repay_info").serializeJson();//数据序列化
					repayInfo.repay_type1 = $("#repay_info input[name=repay_type1]:checked").val();
			    	
			    	var identityInfo = $("#identityInfo").serializeJson();//数据序列化
					identityInfo.idPicFace = $("img#shenfenxinxi:eq(0)").attr("src");
					identityInfo.idPicInverse = $("img#shenfenxinxi:eq(1)").attr("src");
					identityInfo.licensePic = $("img#shenfenxinxi:eq(2)").attr("src");
					identityInfo.registeredNumPic = $("img#shenfenxinxi:eq(3)").attr("src");
					identityInfo.taxPig = $("img#shenfenxinxi:eq(4)").attr("src");
					identityInfo.address = $("select[name='province']").find("option:selected").text() + '/' + $("select[name='city']").find("option:selected").text()

			    	var param = basicInfo;
			    	//param = $.extend(proRuestl_1,proRuestl_2,proRuestl_3,proRuestl_4);

					var detailInfoArr = []
					for (var i = 1; i <= detailInfo.detailCount; i++) {
						detailInfoArr.push({
							'itemTitle': detailInfo['detail_title' + i],
							'itemContent': detailInfo['detail_content' + i],
						})
					}
					param.itemVOList = detailInfoArr

					var repayInfoArr = []
					for (var i = 1; i <= repayInfo.repayCount; i++) {
						repayInfoArr.push({
							'payTitle': repayInfo['repay_title' + i],
							'payContent': repayInfo['repay_content' + i],
							'type': repayInfo['repay_type' + i],
							'time': repayInfo['repay_time' + i],
							'money': repayInfo['repay_money' + i],
						})
					}
					param.repayVOList = repayInfoArr

					param.initiatorPersonInfoVO = identityInfo
					param.initiatorCompanyInfoVO = identityInfo

					console.log(param);

			let token = localStorage.getItem("crowdfunding-token");
				$.ajax({
					url: API_BASE_URL + "/front/project/lunch",
					data: JSON.stringify(param),
					headers: {
						"Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
					},
					async: false,
					contentType: 'application/json;charset=utf-8',
					type: "POST", //请求方式为POST
					dataType: "json",
					success: function(res){

						if (res.code==401) {
							layer.alert("登录过期，请重新登录！！！")
						} else if(res.code==200){
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
								  //layer.msg('的确很重要', {icon: 1});
//									var index = parent.layer.getFrameIndex(window.name); 
//							        parent.layer.close(index);
								//	window.location.href='showPerson.jhtml';
									layer.close(index);
								});
						}
						renewText();
					}
				});
				
			});
		
	})

})

			/**
 * //序列化多个表单
 */
$.fn.serializeJson = function() {
	var serializeObj = {};
	var array = this.serializeArray();
	var str = this.serialize();
	$(array).each(
			function() {
				if (serializeObj[this.name]) {
					if ($.isArray(serializeObj[this.name])) {
						serializeObj[this.name].push(this.value);
					} else {
						serializeObj[this.name] = [ serializeObj[this.name],
								this.value ];
					}
				} else {
					serializeObj[this.name] = this.value;
				}
			});
	return serializeObj;
}


var textArr = new Array();//一开始的textarea
/**
 * 保存开始textarea纯文本的val
 */
function addTextArray(){
	textArr = [];
	$("#detail_info textarea").each(function(){

		textArr.push($(this).val());	
		
	})
	
}
/**
 * 回复开始的textarea
 */
function renewText(){
	for(var i=0;i<$("#detail_info textarea").length;i++){
		$("#detail_info textarea:eq("+i+")").val(textArr[i]);
//		alert(textArr[i]);
	}
}
	


/**
 * 详情内容+图片转换成html
 */

function textToHtml(obj){
	var img ="";
	
	re = new RegExp("\n", "g");
	obj.each(function(){
		//alert($(this).parent().parent().find("img").length);
		
		$(this).parent().parent().find("img").each(function(){
			//var $this = $(this).parent().parent().find("img");

			img = img + "<img src='"+$(this).attr("src") +"' class='lazy' style='width:670px; height:100%' />";	
			
		})
		$(this).val("<p>"+$(this).val().replace(re,"</p><p>")+"</p>"+img);
		
		img ="";//清空
		
	})
}

/**
 * 预览页  数据
 */

function yulan(){
	$(".xqText").children().remove();
	addTextArray();
	$(".det_left img").attr("src",$("#show_cover img").attr("src"));//封面
	$(".text_h3").text($("#basis_info input[name=p_title]").val());//标题
	$(".jindu_s .s_s b").text($("#basis_info input[name=days_raising]").val()+"天");//筹资天数
	$(".jindu_s .s_m b").text("￥"+$("#basis_info input[name=total_fund_raising]").val());//筹资总额
	textToHtml($("#detail_info textarea"));//textare的value转htnl
	
	for(var i=0;i<$(".fq_body_ul.detail").length;i++){
		var title = "";
		var content = "";
		//alert($("#detail_info textarea")[i].value);
		title = $(".text_title input")[i].value;
		content = $("#detail_info textarea")[i].value;

		$(".xqText").append('<div class="xqTextTitle">'+
		                            '<p class="xqTextTitle_p"></p>'+
		                            '<div class="xqLeftTitleInner">'+'<h2>'+title+'</h2>'+'</div>'+'</div>'+content);
	}
	
	
	$(".zcje_ItemBox").remove();
	for(var i=0;i<$("input[name='repayCount']").val();i++){
		var title = "";
		var content = "";
//		alert($("#detail_info textarea")[i].value);
		title = $("input[name='repay_title"+(i+1)+"']").val();
		content = $("textarea[name='repay_content"+(i+1)+"']").val();
		var money = $("input[name='repay_money"+(i+1)+"']").val();
		var time = $("input[name='repay_time"+(i+1)+"']").val();
		var htmlStr = '<div class="zcje_ItemBox">'
			+'<h3 class="zcje_h3"><b>¥'+money+'</b>0人已支持</h3>'
			+'<div class="zcje_title">'+title+'</div>'
			+'<p class="zcje_textP">'+content+'</p>'
			+'<div class="zcjeFooter">'
			+'<p class="">回报方式：<b></b></p>'
			+'<p class="">预计回报发送时间：<b>项目结束后立即'+time+'天内</b></p>'
			+'</div>'
			+'<div class="">'
			+'<span class=""></span>'
			+' <a href="javascript:;" class=""></a>'
			+'</div>'
			+'</div>';
		
		$(".zcjeBox").append(htmlStr);
	}
	renewText();
//	alert(textArr[0]);
}
    
//添加新的回报
var countRepay = 1 ;
function addRepay(){
	var htmlStr = '<div>'
		+'<div style="width:660px;margin:0 auto;"><h3 class="return-tit">回报'+(countRepay+1)+'</h3></div>'
		+'<div class="fq_body_ul">'
		+'<ul style="margin-top:20px">'
		+'<li >'
		+'<label>回报类型：</label>'
		+'<input  name="repay_type'+(countRepay+1)+'" value="实物" type="radio" style="width:17px;" checked /><span style="padding-left:10px;padding-right:20px;">实物回报(回报需要邮寄)</span>'
		+'<input  name="repay_type'+(countRepay+1)+'" value="虚拟" type="radio" style="width:17px;" /><span style="padding-left:10px;">虚拟回报(回报无要邮寄)</span>'
		+'</li>'
		+'<li>'
		+'<label>支持金额：</label>'
		+'<input  name="repay_money'+(countRepay+1)+'" type="text" value="0" placeholder="输入用户支持的金额(必填)"  /><em>元</em>'
		+'</li>'
		+'<li>'
		+'<label>回报标题：</label>'
		+'<input  name="repay_title'+(countRepay+1)+'" type="text" placeholder="输入回报标题（必填）" />'
		+'</li>'
		+'<li>'
		+'<label>回报内容：</label>'
		+'<textarea  name="repay_content'+(countRepay+1)+'" style="width:450px;height:80px;padding:5px 10px;" placeholder="回报内容（必填）"></textarea>'
		+'</li>'
		+'<li>'
		+'<label>回报时间：</label>'
		+'<input  name="repay_time'+(countRepay+1)+'" type="text" placeholder="0" value="0"  /><em>天</em>'
		+'</li>'
		+'</ul>'
		+'<div class="hb_save" style="border-bottom: 2px solid #e3e3e3;">'
		+'<a href="javascript:removeRepay();" style="" class=" removeRepay btn-blue small btn mr12">删除</a>'
		+'<a href="javascript:;" class="btn-grey small btn mr12">保存</a>'
		+'</div>'
		+'</div>'
		+'</div>';
	
	$("#repay_info").append(htmlStr);
	countRepay++;
	$("input[name='repayCount']").val(countRepay);
	
	$(".hb_save").off("click", ".removeRepay");
	$(".hb_save").on("click", ".removeRepay", function () {        
		$(this).parent().parent().parent().remove();
		console.log(countRepay);
		countRepay--;
		console.log(countRepay);
		$("input[name='repayCount']").val(countRepay);
		});

	
}


