/**
 * 
 */

$(document).ready(function(){
	
	showScroll();//显示悬浮导航

	initProjectDetail();

	$("#fabiao_pl").click(function(){

		var $content = $("#pl_content").val();//评论内容
		let projectId = getQueryVariable('id')
		
		if($content == null || $content ==""){
			layer.alert("请填写评论内容！");
			return;
		}
		let token = localStorage.getItem("token");
    	$.ajax({
			type: 'POST',
			async: false,
			url: '/comment/front/add' ,
			contentType: "application/json",
			headers: {
				"Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
			},
			data:  JSON.stringify({
				'content': $content,
				'projectId': projectId
			}),
			dataType: 'json',
			success: function (res) {
				if (res.code == 401) {
					layer.alert("登录过期，请重新登录！！！")
				} else if (res.code == 200) {
					$("#plcontentBox .pl_contentBox").remove()
					$.ajax({
						type: 'POST',
						async: false,
						url: '/comment/front/list',
						contentType: "application/json",
						data: JSON.stringify({
							'pageSize': 100,
							'commentVO': {
								'projectId': projectId
							}
						}),
						dataType: 'json',
						success: function (res) {
							res.data.result.forEach(item => {
								$("#plcontentBox").append(
									`
\t\t\t\t\t\t\t<div class="pl_contentBox">
\t\t\t\t\t\t\t\t\t<div class="content_tx">
\t\t\t\t\t\t\t\t\t\t<a href="javascript:;"><img src="" /></a>
\t\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t\t\t<a href="javascript:;" class="content_nc">用户：${item.username}</a>
\t\t\t\t\t\t\t\t\t<p class="content_text">${item.content}</p>
\t\t\t\t\t\t\t\t\t<!--<div class="content_img">
\t\t\t\t\t\t\t\t\t\t<div class="contentIBox">
\t\t\t\t\t\t\t\t\t\t\t<img src="" />
\t\t\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t\t\t</div>-->
\t\t\t\t\t\t\t\t\t<div class="pl_days">
\t\t\t\t\t\t\t\t\t\t<span class="timeSpan">${item.time}</span>
\t\t\t\t\t\t\t\t\t\t<a href="javascript:;" class="to_pl"><!--评论(0)--></a>
\t\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t</div>
					`
								)
							})
						}
					})
				} else {
					layer.alert(res.message)
				}
            }
          });
    	
    	
		
	});
	
	
	
	var vv ;
	var dd = $(".NumInner input");
	/*	$("a.det_btn1").click(function(){
		var url = window.location.href;
// 		window.open(url);
		$(".xqPageBox").hide();
		$(".zhifuInnerBox").show();
	})
    */
	$(".NumInner a:eq(0)").click(function(){
		
		
		dd.val(parseInt($(".NumInner input").val()) - 1) ;
		$(".shdzForm_xnBox .ng-binding").text(dd.val()*vv);
	})
	$(".NumInner a:eq(1)").click(function(){
		
		$(".NumInner input").val(parseInt($(".NumInner input").val()) + 1) ;
		$(".shdzForm_xnBox .ng-binding").text(dd.val()*vv);
	})
	
	//提交订单效果
	
	$(".supportVal_A").click(function(){
		$(this).hide();
		$(".tjdd_item").slideUp();
		$(this).parent().parent().addClass("cur");
		$(this).parent().parent().show();
		$(".tjddCont h3.tjdd_h3").hide();
		$(".tjddCont a.tjdd_h3").show();
		$(".tjddCont").slideDown();
		vv = $(this).parent().parent().find("input[name='repay_money']").val();
		
	})
	$(".tjddCont a.tjdd_h3").click(function(){
		$(".tjdd_item").slideDown();
		$(".tjdd_item").removeClass("cur");
		$(".supportVal_A").show();
		$(".tjddCont:last").slideUp();
	})
	
	
	//提交订单 ajax
	$(".tjdd_submitBtn").click(function(){
		if(userSession){
			alert("请登录!!!");
			return;
		}
		var address = $("select[name='province'] option:selected").text()+"|"+$("select[name='city'] option:selected").text()+"|"
						+$("select[name='district'] option:selected").text()+"|"+$("input[name='address']").val();
		
		layer.confirm('确定要提交订单吗？', {
			  btn: ['确定','取消'], //按钮
			 // closeBtn:0
			}, function(){
			$.ajax({
				url			:		"saveOrder.jhtml",
				data		:		{
										projectRepay_id	: $("input[name='repay_id']").val(),
										pay_count		: $("input[name='pay_count']").val(),
										receiver		: $("input[name='receiver']").val(),
										address			: address,
										phone			: $("input[name='phone']").val(),
										is_defaultReceive:$("input[name='is_defaultReceive']").val()
					
									},
				async		:		true,
				cache		:		false,
				type		:		"POST",
				dataType	:		"json",
				success		:		function(data){
					//data = eval('('+data+')');
					
					if(data.code=="0"){
						layer.confirm(data.msg, {
							  btn: ['确定'], //按钮
							  closeBtn:0
							}, function(){
							  //layer.msg('的确很重要', {icon: 1});
								//var index = layer.getFrameIndex(window.name); 
								//var index = layer.alert();
								//layer.close(index);
								
						        //parent.layer.close(index);
								window.location.href='showPerson.jhtml';
							});
					}else{
						var index =layer.confirm(data.msg, {
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
	});
	
	
})

function initProjectDetail() {
	let projectId = getQueryVariable('id');
	$.ajax({
		type: 'GET',
		async: false,
		url: '/project/front/detail' ,
		//contentType: "application/json",
		data:  {
			'id': projectId
		},
		dataType: 'json',
		success: function (res) {
			if (res.code != 200) {
				layer.alert(res.message)
			} else {
				let data = res.data
				$(".xqTitle").append(`
					<div class="xqTitText">
						<div class="text_h3_box">
							<p class="text_h3">${data.title}<p>
							</div>
							<div class="text_span">
							<span class="txt1">发起人</span>
								<span class="txt2">用户id_${data.sellerVO.username}</span>
								<!-- <span class=txt3>联系我</span> -->
							</div>
					</div>
				`)

				let percent = (data.hasFundRaising/data.totalFundRaising).toFixed(2)
				$(".xqDetailBox").append(
					`
            \t<span></span>
            \t<div class="det_left">
                \t<img style="height: 400px;width: 534px;" src="${data.coverPath}" />
                </div>
                <div class="det_right">
                \t<div class="det_rightBox">
                    \t<div class="detR_p">
                        \t<p>
                            \t<!-- <span class="ftP">1111</span>
                                <span class="scP">支持数</span> -->
                            </p>
                        </div>
                        <div class="detR_p">
                        \t<p>
                            \t<span class="ftP">￥${data.hasFundRaising}</span>
                                <span class="scP">已筹款</span>
                            </p>
                        </div>
                    </div>
                    <div class="det_jinduBox">
                    \t<div class="jindu_p">
                        \t<p class="ftP">${percent}%</p>
                        </div>
                        <div class="xqRatio">
                        \t<div class="xqRatioInner " style="width:${percent}%;"></div>
                        </div>
                        <div class="jindu_s">
                        \t<div class="s_s">
                            \t<span>剩余</span>
                                <b>${data.leftDays}</b>
                            </div>
                            <div class="s_m">
                            \t<span>目标筹资</span>
                                <b>¥${data.totalFundRaising}</b>
                            </div>
                        </div>
                    </div>
                    <div class="det_Btn_box">
                    \t<a href="/pages/front/public/orderPage?project=${data.id}" class="det_btn1" >立即支持</a>
                        <div class="det_btn2Box">
                        \t<a href="javascript:;" class="det_btn2">分享</a>
                        </div>
                    </div>
                </div>					
					`
				)

				$(".xqTab .tab_box").append(
					`
				<ul class="tab_ul">
                \t<li><a href="#xqMain_left" class="tab_li">项目详情</a></li>
                    <li><a href="#xq_plBox" class="tab_li">评论（${data.commentVOList.length}）</a></li>
                    <li><a href="#xq_zcBox" class="tab_li">支持记录（999）</a></li>
                </ul>
					`
				)

				data.progressVOList.forEach(item => {
					$("#progressList").append(
						`
\t\t\t\t\t\t\t\t\t\t<div class="zxjz_navItemInner">
\t\t\t\t\t\t\t\t\t\t\t<h3 class="zxjz_navItem_h3">
\t\t\t\t\t\t\t\t\t\t\t\t<a href="javascript:void(0);" onclick="siXin(368862,'鬼影人间');sitePop.showSixin(368862);" class="colorALink">
\t\t\t\t\t\t\t\t\t\t\t\t\t${item.pubUser}
\t\t\t\t\t\t\t\t\t\t\t\t</a>发起人
\t\t\t\t\t\t\t\t\t\t\t</h3>
\t\t\t\t\t\t\t\t\t\t\t<p class="textP">
\t\t\t\t\t\t\t\t\t\t\t\t${item.content}
\t\t\t\t\t\t\t\t\t\t\t</p>
\t\t\t\t\t\t\t\t\t\t\t<div class="lastInner"></div>
\t\t\t\t\t\t\t\t\t\t\t<div class="zxjzILBox">
\t\t\t\t\t\t\t\t\t\t\t\t<p class="timeP">${item.publishDateStr}</p>
\t\t\t\t\t\t\t\t\t\t\t\t<i class="statusIcon gr"></i>
\t\t\t\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t\t\t\t</div>
					`
					)
				})

				data.itemVOList.forEach(item => {
					$("#xqTextTitleContentId").append(
						`
                    \t<div class="xqTextTitle">
                            <p class="xqTextTitle_p"></p>
                            <div class="xqLeftTitleInner">
                                <h2>${item.itemTitle}</h2>
                            </div>
                        </div>
                        ${item.itemContent}
						`
					)
				})

				$("#orderRecordDataPreview").append(
					`
		\t\t\t\t\t<div class="xq_footerBox">
                    \t<div class="foot_date">
                        \t<p class="ftP">¥${data.hasFundRaising}</p>
                            <p class="fcP">已筹款</p>
                        </div>
                        <div class="foot_date">
                        \t<p class="ftP">9999</p>
                            <p class="fcP">支持数</p>
                        </div>
                        <div class="foot_date">
                        \t<p class="ftP">${data.leftDays}</p>
                            <p class="fcP">剩余时间</p>
                        </div>
                        <a href="javascript:;">立即支持</a>
                    </div>
					`
				)

				data.commentVOList.forEach(item => {
					$("#plcontentBox").append(
						`
\t\t\t\t\t\t\t<div class="pl_contentBox">
\t\t\t\t\t\t\t\t\t<div class="content_tx">
\t\t\t\t\t\t\t\t\t\t<a href="javascript:;"><img src="" /></a>
\t\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t\t\t<a href="javascript:;" class="content_nc">用户：${item.username}</a>
\t\t\t\t\t\t\t\t\t<p class="content_text">${item.content}</p>
\t\t\t\t\t\t\t\t\t<!--<div class="content_img">
\t\t\t\t\t\t\t\t\t\t<div class="contentIBox">
\t\t\t\t\t\t\t\t\t\t\t<img src="" />
\t\t\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t\t\t</div>-->
\t\t\t\t\t\t\t\t\t<div class="pl_days">
\t\t\t\t\t\t\t\t\t\t<span class="timeSpan">${item.time}</span>
\t\t\t\t\t\t\t\t\t\t<a href="javascript:;" class="to_pl"><!--评论(0)--></a>
\t\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t</div>
					`
					)
				})

				data.repayVOList.forEach(item => {
					$("#zcje_ItemBox").append(
						`
\t                    <div class="zcje_ItemBox">
\t                    \t<h3 class="zcje_h3"><b>¥${item.money}</b>99人已支持</h3>
\t                        <div class="zcje_title">${item.payTitle}</div>
\t                        <p class="zcje_textP">${item.payContent}</p>
\t                        <div class="zcjeFooter">
\t                        \t<p class="">回报方式：<b>${item.type}</b></p>
\t                            <p class="">预计回报发送时间：<b>项目成功结束后${item.time}内</b></p>
\t                        </div>
\t                        <div class="">
\t                        \t<span class=""></span>
\t                            <a href="javascript:;" class=""></a>
\t                        </div>
\t                    </div>
						`
					)
				})

			}
		}
	});
}

function showScroll() {
        $(window).scroll(function () {
            var scrollValue = $(window).scrollTop();
            scrollValue > 800 ? $('.xqTab').css("position","fixed").css("top","0") : $('.xqTab').css("position","").css("top","");
        });
        
}

//使用新地址

function newAddressForm(){
	var htmlStr = '<div id="newReceive" class="shdzForm_swBox">'
			+'<div class="tjdd_formItem">'
			+'<div class="tjddQHFGBox left">'
			+'<span><input name="receiver" type="text" class="tjdd_QHInput w200" placeholder="姓名"></span>'
			+'</div>'
			+'<div class="tjddQHFGBox right">'
			+'<span><input name="phone" class="tjdd_QHInput" style="width:200px;" type="text" placeholder="手机号"></span>'
			+'</div>'
			+'</div>'
			+'<div class="tjdd_formItem">'
			+'<div class="tjddSelectBox left">'
			+'<select name="province">'
			+'<option>请选择</option>'
			+'</select>'
			+'</div>'
			+'<div class="tjddSelectBox right">'
			+'<select name="city">'
			+'<option>请选择</option>'
			+'</select>'
			+'</div>'
			+'<div class="tjddSelectBox right" style="margin-top:10px">'
			+'<select name="district">'
			+'<option>请选择</option>'
			+'</select>'
			+'</div>'
			+'</div>'
			+'<div class="tjdd_formItem">'
			+'<div class="tjddQHFGBox">'
			+'<span><input name="address" class="tjdd_QHInput w400" style="width:440px;" type="text" placeholder="详细地址"></span>'
			+'</div>'
			+'</div>'
	
			+'</div>';
	
	$("#defultReceive").remove();
	$("#newReceive").remove();
	$(".shdzListBox").after(htmlStr);
	getProvince(0,$("select[name='province']"));//所有省份
	$("#newReceive").on("change", "select[name='province']", function () {        
		getProvince($(this).val(),$("select[name='city']"));
		});
	$("#newReceive").on("change", "select[name='city']", function () {        
		getProvince($(this).val(),$("select[name='district']"));
		})

	
}

