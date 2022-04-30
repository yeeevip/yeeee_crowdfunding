/**
 * 首页轮播
 */
function lunbo()
{
	var oDiv=document.getElementById('indexBanner');
	var btnDiv=document.getElementById('indexBPage');
	var preBtn=btnDiv.getElementsByTagName('a')[0];
	var nextBtn=btnDiv.getElementsByTagName('a')[1];
	var oUl=oDiv.getElementsByTagName('ul')[0];
	
	function move(){
		
		oUl.style.left=oUl.offsetLeft-1600+'px';
		if(oUl.offsetLeft<-oUl.offsetWidth*(5/6))
		{
			oUl.style.left='0';	
		}	
		}
	var timer=setInterval(move,1500);

	oDiv.onmousemove=function()
	{
		clearInterval(timer);
		
		}
	oDiv.onmouseout=function()
	{
		timer=setInterval(move,1500);
		}
	
	/*****向左向右滚动按钮*****/
	var a=0;
	nextBtn.onclick=function(){
		var timer2=setInterval(function(){
			
			oUl.style.left=oUl.offsetLeft-50+'px';
			a+=50;
			
			if(a==1600){clearInterval(timer2);a=0;}
			if(oUl.style.left==-9600+'px'){
				oUl.style.left=0+'px';
			}
		},10);
	
	}
	preBtn.onclick=function(){
		var timer2=setInterval(function(){
			
			oUl.style.left=oUl.offsetLeft+50+'px';
			a+=50;
			
			if(a==1600){clearInterval(timer2);a=0;}
			if(oUl.style.left==1600+'px'){
				oUl.style.left=-8000+'px';
			}
		},10);
	
	}
}

$(document).ready(function(){
	$.ajax({
		type: 'GET',
		url: '/project/front/index' ,
		data: {} ,
		dataType: 'json',
		success: function (res) {
			console.log(res)
			var data = res.data

			var hotHtml =
				`<!--热门推荐-->
					 <div class="indexZCWrap">
						 <div class="mainInnerBox">
							 <h3 class="indexZCH3">热门推荐</h3>
							 <ul class="indexZCLabel_ul">
								  <li class="siteIlB_item"><a href="javascript:;" class="site_ALink">最新上线</a></li>
								  <li class="geban siteIlB_item">/</li>
								  <li class="siteIlB_item"><a href="javascript:;" class="site_ALink">公益</a></li>
								  <li class="geban siteIlB_item">/</li>
								  <li class="siteIlB_item"><a href="javascript:;" class="site_ALink">农业</a></li>
								  <li class="geban siteIlB_item">/</li>
								  <li class="siteIlB_item"><a href="javascript:;" class="site_ALink">出版</a></li>
								  <li class="geban siteIlB_item">/</li>
								  <li class="siteIlB_item"><a href="javascript:;" class="site_ALink">娱乐</a></li>
								  <li class="geban siteIlB_item">/</li>
								  <li class="siteIlB_item"><a href="javascript:;" class="site_ALink">艺术</a></li>
								  <li class="moreA siteIlB_item"><a href="javascript:;">更多&gt;</a></li>      
							 </ul>
							 <!--卡片列begin表-->
        					 <div class="indCardListWrap">
				`
			data.hotList.forEach(item => {
				var progress = (item.hasFundRaising / item.totalFundRaising).toFixed(2)
				hotHtml +=
					`
							 <div class="indCardItem">
								<a href="/pages/front/project.html?id=${item.id}" class="siteCardItemImgA"><img src="${item.coverPath}" /></a>
								<div class="indCardICBox">
									<div class="indCardICText">
										<a href="/pages/front/project.html?id=1" class="siteCardICH3">${item.title}</a>
										<p class="siteCardIC_p ind">${item.blurb}</p>
									</div>
									<div class="siteCardFBox">
										<div class="siteCardFLabelBox">
											<!-- a href="javascript:;">北京</a>
											<a href="javascript:;">我行我素</a> -->
										</div>
										<div class="siteCardRatio">
											<div class="siteCardRatioInner" style="width:${progress}%"></div>
										</div>
										<div class="siteCardFData">
											<div class="ftDiv">
												<p class="ftP">￥${item.hasFundRaising}</p>
												<p class="scP">已筹款</p>
											</div>
											<div class="scDiv">
												<!-- <p class="ftP">149</p>
												<p class="scP">支持数</p> -->
											</div>
											<div class="thDiv">
												<p class="ftP">${progress}%</p>
												<p class="scP">筹款进度</p>
											</div>
										</div>
									</div>
								</div>
							</div>
					`
			})
			hotHtml +=
				`			 </div>
        					 <!--卡片列表end-->
							 <a href="/pages/front/search.html" class="indCardListMoreA btn_Alink">浏览更多项目</a>
						 </div>
					 </div>
					 <!--热门推荐end-->`
			$("#indexContentBox").append(hotHtml)

			var welfareHtml =
				`<!--公益众筹-->
					 <div class="indexZCWrap">
						 <div class="mainInnerBox">
							 <h3 class="indexZCH3">公益众筹</h3>
							 <ul class="indexZCLabel_ul"> 
							 </ul>
							 <!--卡片列begin表-->
        					 <div class="indCardListWrap">
				`
			data.welfareList.forEach(item => {
				var progress = (item.hasFundRaising / item.totalFundRaising).toFixed(2)
				welfareHtml +=
					`
							 <div class="indCardItem">
								<a href="/pages/front/project.html?id=${item.id}" class="siteCardItemImgA"><img src="${item.coverPath}" /></a>
								<div class="indCardICBox">
									<div class="indCardICText">
										<a href="/pages/front/project.html?id=1" class="siteCardICH3">${item.title}</a>
										<p class="siteCardIC_p ind">${item.blurb}</p>
									</div>
									<div class="siteCardFBox">
										<div class="siteCardFLabelBox">
											<!-- a href="javascript:;">北京</a>
											<a href="javascript:;">我行我素</a> -->
										</div>
										<div class="siteCardRatio">
											<div class="siteCardRatioInner" style="width:${progress}%"></div>
										</div>
										<div class="siteCardFData">
											<div class="ftDiv">
												<p class="ftP">￥${item.hasFundRaising}</p>
												<p class="scP">已筹款</p>
											</div>
											<div class="scDiv">
												<!-- <p class="ftP">149</p>
												<p class="scP">支持数</p> -->
											</div>
											<div class="thDiv">
												<p class="ftP">${progress}%</p>
												<p class="scP">筹款进度</p>
											</div>
										</div>
									</div>
								</div>
							</div>
					`
			})
			welfareHtml +=
				`			 </div>
        					 <!--卡片列表end-->
							 <a href="/pages/front/search.html" class="indCardListMoreA btn_Alink">浏览更多项目</a>
						 </div>
					 </div>
					 <!-- 公益众筹end-->`
			$("#indexContentBox").append(welfareHtml)

			var agHtml =
				`<!--农业众筹-->
					 <div class="indexZCWrap">
						 <div class="mainInnerBox">
							 <h3 class="indexZCH3">农业众筹</h3>
							 <ul class="indexZCLabel_ul">
							 </ul>
							 <!--卡片列begin表-->
        					 <div class="indCardListWrap">
				`
			data.agList.forEach(item => {
				var progress = (item.hasFundRaising / item.totalFundRaising).toFixed(2)
				agHtml +=
					`
							 <div class="indCardItem">
								<a href="/pages/front/project.html?id=${item.id}" class="siteCardItemImgA"><img src="${item.coverPath}" /></a>
								<div class="indCardICBox">
									<div class="indCardICText">
										<a href="/pages/front/project.html?id=1" class="siteCardICH3">${item.title}</a>
										<p class="siteCardIC_p ind">${item.blurb}</p>
									</div>
									<div class="siteCardFBox">
										<div class="siteCardFLabelBox">
											<!-- a href="javascript:;">北京</a>
											<a href="javascript:;">我行我素</a> -->
										</div>
										<div class="siteCardRatio">
											<div class="siteCardRatioInner" style="width:${progress}%"></div>
										</div>
										<div class="siteCardFData">
											<div class="ftDiv">
												<p class="ftP">￥${item.hasFundRaising}</p>
												<p class="scP">已筹款</p>
											</div>
											<div class="scDiv">
												<!-- <p class="ftP">149</p>
												<p class="scP">支持数</p> -->
											</div>
											<div class="thDiv">
												<p class="ftP">${progress}%</p>
												<p class="scP">筹款进度</p>
											</div>
										</div>
									</div>
								</div>
							</div>
					`
			})
			agHtml +=
				`			 </div>
        					 <!--卡片列表end-->
							 <a href="/pages/front/search.html" class="indCardListMoreA btn_Alink">浏览更多项目</a>
						 </div>
					 </div>
					 <!--农业众筹end-->`
			$("#indexContentBox").append(agHtml)

			var publishHtml =
				`<!--出版众筹-->
					 <div class="indexZCWrap">
						 <div class="mainInnerBox">
							 <h3 class="indexZCH3">出版众筹</h3>
							 <ul class="indexZCLabel_ul">      
							 </ul>
							 <!--卡片列begin表-->
        					 <div class="indCardListWrap">
				`
			data.publishList.forEach(item => {
				var progress = (item.hasFundRaising / item.totalFundRaising).toFixed(2)
				publishHtml +=
					`
							 <div class="indCardItem">
								<a href="/pages/front/project.html?id=${item.id}" class="siteCardItemImgA"><img src="${item.coverPath}" /></a>
								<div class="indCardICBox">
									<div class="indCardICText">
										<a href="/pages/front/project.html?id=1" class="siteCardICH3">${item.title}</a>
										<p class="siteCardIC_p ind">${item.blurb}</p>
									</div>
									<div class="siteCardFBox">
										<div class="siteCardFLabelBox">
											<!-- a href="javascript:;">北京</a>
											<a href="javascript:;">我行我素</a> -->
										</div>
										<div class="siteCardRatio">
											<div class="siteCardRatioInner" style="width:${progress}%"></div>
										</div>
										<div class="siteCardFData">
											<div class="ftDiv">
												<p class="ftP">￥${item.hasFundRaising}</p>
												<p class="scP">已筹款</p>
											</div>
											<div class="scDiv">
												<!-- <p class="ftP">149</p>
												<p class="scP">支持数</p> -->
											</div>
											<div class="thDiv">
												<p class="ftP">${progress}%</p>
												<p class="scP">筹款进度</p>
											</div>
										</div>
									</div>
								</div>
							</div>
					`
			})
			publishHtml +=
				`			 </div>
        					 <!--卡片列表end-->
							 <a href="/pages/front/search.html" class="indCardListMoreA btn_Alink">浏览更多项目</a>
						 </div>
					 </div>
					 <!--出版众筹end-->`
			$("#indexContentBox").append(publishHtml)

			var artHtml =
				`<!--艺术众筹-->
					 <div class="indexZCWrap">
						 <div class="mainInnerBox">
							 <h3 class="indexZCH3">艺术众筹</h3>
							 <ul class="indexZCLabel_ul">      
							 </ul>
							 <!--卡片列begin表-->
        					 <div class="indCardListWrap">
				`
			data.artList.forEach(item => {
				var progress = (item.hasFundRaising / item.totalFundRaising).toFixed(2)
				artHtml +=
					`
							 <div class="indCardItem">
								<a href="/pages/front/project.html?id=${item.id}" class="siteCardItemImgA"><img src="${item.coverPath}" /></a>
								<div class="indCardICBox">
									<div class="indCardICText">
										<a href="/pages/front/project.html?id=1" class="siteCardICH3">${item.title}</a>
										<p class="siteCardIC_p ind">${item.blurb}</p>
									</div>
									<div class="siteCardFBox">
										<div class="siteCardFLabelBox">
											<!-- a href="javascript:;">北京</a>
											<a href="javascript:;">我行我素</a> -->
										</div>
										<div class="siteCardRatio">
											<div class="siteCardRatioInner" style="width:${progress}%"></div>
										</div>
										<div class="siteCardFData">
											<div class="ftDiv">
												<p class="ftP">￥${item.hasFundRaising}</p>
												<p class="scP">已筹款</p>
											</div>
											<div class="scDiv">
												<!-- <p class="ftP">149</p>
												<p class="scP">支持数</p> -->
											</div>
											<div class="thDiv">
												<p class="ftP">${progress}%</p>
												<p class="scP">筹款进度</p>
											</div>
										</div>
									</div>
								</div>
							</div>
					`
			})
			artHtml +=
				`			 </div>
        					 <!--卡片列表end-->
							 <a href="/pages/front/search.html" class="indCardListMoreA btn_Alink">浏览更多项目</a>
						 </div>
					 </div>
					 <!--艺术众筹end-->`
			$("#indexContentBox").append(artHtml)

		}
	});
})



