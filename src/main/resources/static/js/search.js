$(document).ready(function(){

	let pageNm = getQueryVariable("pageNum");
	let projectType = getQueryVariable("projectType")

	$("#projectTypeDiv a").each(function () {
		if ($(this).attr('href').indexOf('projectType=' + projectType) !== -1) {
			console.log(projectType)
			$(this).addClass('cur')
		}
	})

	var params = {
		"pageNum": pageNm ? pageNm : 1,
		"projectVO": {
		"projectType": projectType,
		"keyword": getQueryVariable("keyword"),
	}}
	$.ajax({
		type: 'POST',
		url: API_BASE_URL + '/project/front/list' ,
		contentType: "application/json;charset=utf-8",
		data:  JSON.stringify(params),
		dataType: 'json',
		success: function (res) {
			console.log(res)
			var data = res.data

			var itemHtml = ``
			data.result.forEach(item => {
				var progress = (item.hasFundRaising / item.totalFundRaising*100).toFixed(2)
				itemHtml +=
					`
	<div class="searchCard">
         <a href="/pages/front/public/project.html?id=${item.id}" class="siteCardItemImgA">
         	<img src="${item.coverPath}" />
         </a>
         <div class="searchCtext">
             <div class="searchCT_p">
                 <h3><a href="/pages/front/public/project.html?id=${item.id}" class="siteCTH3">${item.title}</a></h3>
                  <p class="siteCardIC_p">${item.blurb}</p>
             </div>
             <div class="searchCFooter">
                <div class="searchCF_bq">
                     <!-- <a href="javascript:;">浙江</a>
                        <a href="javascript:;">温州</a>
                        <a href="javascript:;">健康</a>
                        <a href="javascript:;">生态</a> -->
                </div>
                <div class="searchCF_jd">
                     <div class="jd_now" style="width:${progress}%;"></div>
                </div>
                    <div class="searchCF_je">
                     <div class="ftDiv">
                         <p class="ftP">￥${item.hasFundRaising}</p>
                            <p class="scP">已筹款</p>
                        </div>
                        <div class="scDiv">
                         <!-- <p class="ftP">12</p>
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
			$("#searchIMainBox").append(itemHtml)

			var pageFooterHtml = ``
			for (var i = 0; i < data.pages; i++) {
				pageFooterHtml +=
					`<a href = "/pages/front/public/search.html?projectType=${getQueryVariable('projectType')}&&pageNum=${i+1}" class = "normalPage cu" >${i+1}</a>`
			}
			$("#searchPMainBox").prepend(pageFooterHtml)

		}
	});
})



