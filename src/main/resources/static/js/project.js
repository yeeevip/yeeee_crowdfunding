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
		let token = localStorage.getItem("crowdfunding-token");
    	$.ajax({
			type: 'POST',
			async: false,
			url: API_BASE_URL + '/front/comment/add' ,
			contentType: "application/json;charset=utf-8",
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
						url: API_BASE_URL + '/front/comment/list',
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


	
	
})

function initProjectDetail() {
	let projectId = getQueryVariable('id');
	$.ajax({
		type: 'GET',
		async: false,
		url: API_BASE_URL + '/front/project/detail' ,
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

				let percent = (data.hasFundRaising/data.totalFundRaising*100).toFixed(2)
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
                    \t<a href="/pages/front/public/orderPage.html?projectId=${data.id}" class="det_btn1" >立即支持</a>
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


