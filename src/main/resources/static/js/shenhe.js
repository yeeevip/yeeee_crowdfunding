$(document).ready(function(){

    let projectId = getQueryVariable('id');
    let token = localStorage.getItem("sys-token");
    $.ajax({
        type: 'GET',
        async: false,
        url: '/project/admin/detail',
        //contentType: "application/json",
        data: {
            'id': projectId
        },
        headers: {
            "Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
        },
        dataType: 'json',
        success: function (res) {

            var project = res.data

            var identityHtml = ""
            if ('个人' == project.shenfen) {
                identityHtml +=
                    `
                <div class="shenfenBox">
\t\t\t    \t<div class="basicInfo">
\t\t\t\t\t\t<ul class="fq_body_ul" style="text-align:center">
\t                        <li style="height:25px;"><span>姓名</span><span>：</span>${project.initiatorPersonInfoVO.name}</li>
\t                        <li style="height:25px;"><span>身份证号</span><span>：</span>${project.initiatorPersonInfoVO.idNumber}</li>
\t                        <li style="height:25px;"><span>手机</span><span>：</span>${project.initiatorPersonInfoVO.phone}</li>
\t                        <li style="height:25px;"><span>地址</span><span>：</span>${project.initiatorPersonInfoVO.address}</li>
\t                    </ul>
\t\t\t    \t
\t\t\t    </div>
\t\t\t    
\t\t\t    <div class="imgInfo" >
\t\t\t    \t\t\t<div style="width:451px"><span>身份证正面：</span><div class="imgInfo_item"><img style="height:302px;width:450px" src="${project.initiatorPersonInfoVO.idPicFace}"/></div></div>
\t                    \t<div style="width:451px"><span>身份证反面：</span><div class="imgInfo_item"><img  style="height:302px;width:450px" src="${project.initiatorPersonInfoVO.idPicInverse}"/></div></div>
\t\t\t    \t</div>
\t\t\t   </div>
                    `
            } else {
                identityHtml +=
                    `
                    <div class="shenfenBox">
\t\t\t    \t<div class="basicInfo">
\t\t\t\t\t\t<ul class="fq_body_ul" style="text-align:center">
\t                        <li style="height:25px;"><span>企业名称</span><span>：</span>${project.initiatorCompanyInfoVO.firmName}</li>
\t                        <li style="height:25px;"><span>营业执照号</span><span>：</span>${project.initiatorCompanyInfoVO.businessNumber}</li>
\t                        <li style="height:25px;"><span>法定代表人</span><span>：</span>${project.initiatorCompanyInfoVO.slanderName}</li>
\t                        <li style="height:25px;"><span>公司注册地址</span><span>：</span>${project.initiatorCompanyInfoVO.address}</li>
\t                        <li style="height:25px;"><span>联系人姓名</span><span>：</span>${project.initiatorCompanyInfoVO.contactName}</li>
\t                        <li style="height:25px;"><span>联系人手机号</span><span>：</span>${project.initiatorCompanyInfoVO.contactPhone}</li>
\t                    </ul>
\t\t\t    \t
\t\t\t    </div>
\t\t\t    
\t\t\t    <div class="imgInfo">
\t\t\t    \t\t\t<div><span>营业执照：</span><div class="imgInfo_item"><img style="height:302px;width:450px" src="${project.initiatorCompanyInfoVO.licensePic}"/></div></div>
\t                    \t<div><span>组织机构代码证：</span><div class="imgInfo_item"><img  style="height:302px;width:450px" src="${project.initiatorCompanyInfoVO.registeredNumPic}"/></div></div>
\t                    \t<div><span>税务登记证：</span><div class="imgInfo_item"><img  style="height:302px;width:450px" src="${project.initiatorCompanyInfoVO.taxPig}"/></div></div>
\t\t\t    \t</div>
\t\t\t   </div>
                    `
            }
            $("#shenfenBox").append(identityHtml)

            $("#xqTitleBox").append(
                `
\t\t\t        \t<div class="xqTitText">
\t\t\t            \t<input type="hidden" name="project_id" value="${project.id}">
\t\t\t                \t<div class="text_h3_box">
\t\t\t                    \t<p class="text_h3">${project.title}</p>
\t\t\t                    </div>
\t\t\t                    <div class="text_span">
\t\t\t                    \t<span class="txt1">发起人</span>
\t\t\t                        <span class="txt2">用户xxxxxx</span>
\t\t\t                        <span class=txt3>联系我</span>
\t\t\t                    </div>
\t\t\t            </div>
                `
            )

            $("#xqDetailBoxBOX").append(
                `
<div class="xqDetailBox">
\t\t\t\t\t\t<div class="det_left">
\t\t\t\t\t\t\t<img src="${project.coverPath}" />
\t\t\t\t\t\t</div>
\t\t\t\t\t\t<div class="det_right">
\t\t\t\t\t\t\t<div class="det_rightBox">
\t\t\t\t\t\t\t\t<div class="detR_p">
\t\t\t\t\t\t\t\t\t<p>
\t\t\t\t\t\t\t\t\t\t<span class="ftP">0</span>
\t\t\t\t\t\t\t\t\t\t<span class="scP">支持数</span>
\t\t\t\t\t\t\t\t\t</p>
\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t\t<div class="detR_p">
\t\t\t\t\t\t\t\t\t<p>
\t\t\t\t\t\t\t\t\t\t<span class="ftP">￥0</span>
\t\t\t\t\t\t\t\t\t\t<span class="scP">已筹款</span>
\t\t\t\t\t\t\t\t\t</p>
\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t<div class="det_jinduBox">
\t\t\t\t\t\t\t\t<div class="jindu_p">
\t\t\t\t\t\t\t\t\t<p class="ftP">0%</p>
\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t\t<div class="xqRatio">
\t\t\t\t\t\t\t\t\t<div class="xqRatioInner " style="width:0;"></div>
\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t\t<div class="jindu_s">
\t\t\t\t\t\t\t\t\t<div class="s_s">
\t\t\t\t\t\t\t\t\t\t<span>剩余</span>
\t\t\t\t\t\t\t\t\t\t<b></b>
\t\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t\t\t<div class="s_m">
\t\t\t\t\t\t\t\t\t\t<span>目标筹资</span>
\t\t\t\t\t\t\t\t\t\t<b>${project.totalFundRaising}</b>
\t\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t<div class="det_Btn_box">
\t\t\t\t\t\t\t\t<a href="javascript:;" style="background-color:#999" class="det_btn1" >立即支持</a>
\t\t\t\t\t\t\t\t<div class="det_btn2Box">
\t\t\t\t\t\t\t\t\t<a href="javascript:;" style="background-color:#999"  class="det_btn2">分享</a>
\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t</div>
\t\t\t\t\t</div>
                `
            )

            project.itemVOList.forEach(item => {
                $("#xqTextBOX").append(
                    `
\t\t\t\t\t\t\t\t<div class="xqTextTitle">
\t\t\t\t\t\t\t\t\t<p class="xqTextTitle_p"></p>
\t\t\t\t\t\t\t\t\t<div class="xqLeftTitleInner">
\t\t\t\t\t\t\t\t\t\t<h2>${item.itemTitle}</h2>
\t\t\t\t\t\t\t\t\t</div>

\t\t\t\t\t\t\t\t </div>
\t\t\t\t\t\t\t   ${item.itemContent}
                `
                )
            })

            project.repayVOList.forEach(item => {
                $("#zcje_ItemBoxBOX").append(
                    `
\t\t\t\t\t\t\t\t<div class="zcje_ItemBox">
\t\t\t\t\t\t\t\t\t<h3 class="zcje_h3"><b>¥${item.money}</b>0人已支持</h3>
\t\t\t\t\t\t\t\t\t<div class="zcje_title">${item.payTitle}</div>
\t\t\t\t\t\t\t\t\t<p class="zcje_textP">${item.payContent}</p>
\t\t\t\t\t\t\t\t\t<div class="zcjeFooter">
\t\t\t\t\t\t\t\t\t\t<p class="">回报方式：<b>${item.type}</b></p>
\t\t\t\t\t\t\t\t\t\t<p class="">预计回报发送时间：<b>项目成功结束后${item.time}内</b></p>
\t\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t\t\t<div class="">
\t\t\t\t\t\t\t\t\t\t<span class=""></span>
\t\t\t\t\t\t\t\t\t\t<a href="javascript:;" class=""></a>
\t\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t\t</div>
                    `
                )
            })

        }
    })




    var project_id = getQueryVariable('id');

    $(".shenheBtn").click(function(){
        let token = localStorage.getItem("sys-token");
        $.ajax({
            url		:		'/project/admin/audits',
            async	:		false,
            data	:		JSON.stringify({
                'projectId': project_id,
                'hasAudits': 1
            }),
            contentType: 'application/json;charset=utf-8',
            headers: {
                "Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
            },
            type	:		"post",
            success	:		function(res){
                swal(res.message,"", "");
            }
        });
    });
    $(".shenheBtnreject").click(function(){
        let token = localStorage.getItem("sys-token");
        $.ajax({
            url		:		'/project/admin/audits',
            async	:		false,
            data	:		JSON.stringify({
                'projectId': project_id,
                'hasAudits': -1
            }),
            contentType: 'application/json;charset=utf-8',
            headers: {
                "Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
            },
            type	:		"post",
            success	:		function(res){
                swal(res.message,"", "");
            }
        });
    })



})