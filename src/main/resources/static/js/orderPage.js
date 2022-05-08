$(document).ready(function(){

    var projectId = getQueryVariable('projectId');
    let token = localStorage.getItem("crowdfunding-token");
    $.ajax({
        type: 'GET',
        async: false,
        url: API_BASE_URL + '/front/project/orderPage',
        //contentType: "application/json",
        data: {
            'id': projectId
        },
        headers: {
            "Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
        },
        dataType: 'json',
        success: function (res) {

            var order = res.data

            if (res.code == 401) {
                layer.alert("登录失效，请重新登录！！！")
                return
            }

            order.repayVOList.forEach(item => {
                $(".tjdd_list").append(
                    `
  \t\t\t\t\t\t\t<div class="tjdd_item">
  \t\t\t\t\t\t\t<input type="hidden" name="repay_id" value="${item.id}">
  \t\t\t\t\t\t\t<input type="hidden" name="repay_money" value="${item.money}">
  \t\t\t\t\t\t\t\t<div class="supportABox">
  \t\t\t\t\t\t\t\t\t<a href="javascript:;" class="supportVal_A">支持￥${item.money}</a>
  \t\t\t\t\t\t\t\t</div>
  \t\t\t\t\t\t\t\t<h3 class="support_h3">¥${item.money}
  \t\t\t\t\t\t\t\t\t<b><span class="ng-binding">125</span>ren支持</b>
  \t\t\t\t\t\t\t\t</h3>
  \t\t\t\t\t\t\t\t<div class="support_title">${item.payTitle}</div>
  \t\t\t\t\t\t\t\t<p class="support_inforP ">${item.payContent}</p>
  \t\t\t\t\t\t\t\t<div class="supportFooter">
\t\t\t\t\t\t\t\t\t<div class="supportFLeft">
\t\t\t\t\t\t\t\t\t\t<p>回报方式：<b>${item.type}回报</b></p>
\t\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t\t\t
\t\t\t\t\t\t\t\t\t<div class="supportFLeft">
\t\t\t\t\t\t\t\t\t\t<p>&nbsp;&nbsp;&nbsp;&nbsp;回报时间：<b>项目成功结束后${item.time}天内</b></p>
\t\t\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t\t\t\t
\t\t\t\t\t\t\t\t\t<div class="supportFRight"></div>
\t\t\t\t\t\t\t\t\t
\t\t\t\t\t\t\t\t</div>
  \t\t\t\t\t\t\t</div>
                `
                )
            })

            if (order.receiveInfoVO && order.receiveInfoVO.id) {
                $("#defultReceiveBOX").append(
                    `
                <input type="hidden" value="${order.receiveInfoVO.id}" id="receiveInfoVOID" />
  \t\t\t\t\t\t<div id="defultReceive" class="shdzForm_swBox">
  \t\t\t\t\t\t\t<div class="tjdd_formItem">
  \t\t\t\t\t\t\t\t<div class="tjddQHFGBox left">
  \t\t\t\t\t\t\t\t\t<span>收货人：${order.receiveInfoVO.receiver}</span>
  \t\t\t\t\t\t\t\t</div>
  \t\t\t\t\t\t\t\t<div class="tjddQHFGBox left" style="margin-left:20px">
  \t\t\t\t\t\t\t\t\t<span>手机号：${order.receiveInfoVO.phone}</span>
  \t\t\t\t\t\t\t\t</div>
  \t\t\t\t\t\t\t</div>
  \t\t\t\t\t\t\t<div class="tjdd_formItem">
  \t\t\t\t\t\t\t\t<div class="tjddQHFGBox">
  \t\t\t\t\t\t\t\t\t<span>地址：${order.receiveInfoVO.address}</span>
  \t\t\t\t\t\t\t\t</div>
  \t\t\t\t\t\t\t</div>
  \t\t\t\t\t\t\t
  \t\t\t\t\t\t</div>
                `
                )
            } else {
                $("#shdzForm_swBoxBOX").append(
                    `
<div id="newReceive" class="shdzForm_swBox">
  \t\t\t\t\t\t\t<input name="is_defaultReceive" type="hidden" value="0" >
  \t\t\t\t\t\t\t<div class="tjdd_formItem">
  \t\t\t\t\t\t\t\t<div class="tjddQHFGBox left">
  \t\t\t\t\t\t\t\t\t<span><input name="receiver" type="text" class="tjdd_QHInput w200" placeholder="姓名"></span>
  \t\t\t\t\t\t\t\t</div>
  \t\t\t\t\t\t\t\t<div class="tjddQHFGBox right">
  \t\t\t\t\t\t\t\t\t<span><input name="phone" class="tjdd_QHInput" style="width:200px;" type="text" placeholder="手机号"></span>
  \t\t\t\t\t\t\t\t</div>
  \t\t\t\t\t\t\t</div>
  \t\t\t\t\t\t\t<div class="tjdd_formItem">
  \t\t\t\t\t\t\t\t<div class="tjddSelectBox left">
  \t\t\t\t\t\t\t\t\t<select name="province">
  \t\t\t\t\t\t\t\t\t\t<option>请选择</option>
  \t\t\t\t\t\t\t\t\t</select>
  \t\t\t\t\t\t\t\t</div>
  \t\t\t\t\t\t\t\t<div class="tjddSelectBox right">
  \t\t\t\t\t\t\t\t\t<select name="city">
  \t\t\t\t\t\t\t\t\t\t<option>请选择</option>
  \t\t\t\t\t\t\t\t\t</select>
  \t\t\t\t\t\t\t\t</div>
  \t\t\t\t\t\t\t\t<div class="tjddSelectBox right" style="margin-top:10px">
  \t\t\t\t\t\t\t\t\t<select name="district">
  \t\t\t\t\t\t\t\t\t\t<option>请选择</option>
  \t\t\t\t\t\t\t\t\t</select>
  \t\t\t\t\t\t\t\t</div>
  \t\t\t\t\t\t\t</div>
  \t\t\t\t\t\t\t<div class="tjdd_formItem">
  \t\t\t\t\t\t\t\t<div class="tjddQHFGBox">
  \t\t\t\t\t\t\t\t\t<span><input name="address" class="tjdd_QHInput w400" style="width:440px;" type="text" placeholder="详细地址"></span>
  \t\t\t\t\t\t\t\t</div>
  \t\t\t\t\t\t\t</div>
  \t\t\t\t\t\t\t
  \t\t\t\t\t\t</div>
                    `
                )
            }

        }
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
        repayId = $(this).parent().parent().find("input[name='repay_id']").val();

    })
    $(".tjddCont a.tjdd_h3").click(function(){
        $(".tjdd_item").slideDown();
        $(".tjdd_item").removeClass("cur");
        $(".supportVal_A").show();
        $(".tjddCont:last").slideUp();
    })

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

    bindSubmitOrder()

})

var repayId
//提交订单 ajax
function bindSubmitOrder() {
    $(".tjdd_submitBtn").click(function(){
        let token = localStorage.getItem("crowdfunding-token");
        var address = $("select[name='province'] option:selected").text()+"|"+$("select[name='city'] option:selected").text()+"|"
            +$("select[name='district'] option:selected").text()+"|"+$("input[name='address']").val();

        layer.confirm('确定要提交订单吗？', {
            btn: ['确定','取消'], //按钮
            // closeBtn:0
        }, function(){
            $.ajax({
                url			:		API_BASE_URL +"/front/order/create",
                data		:		JSON.stringify(
                    {
                        'repayId'	: repayId,
                        'payCount'		: $("input[name='pay_count']").val(),
                        'receiveInfoVO': {
                            'receiver'		: $("input[name='receiver']").val(),
                            'address'			: address,
                            'phone'			: $("input[name='phone']").val(),
                            'id': $("#receiveInfoVOID").val()
                        }

                    }),
                headers: {
                    "Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
                },
                async		:		false,
                type		:		"POST",
                dataType	:		"json",
                contentType: "application/json;charset=utf-8",
                success		:		function(res){

                    if(res.code==200){
                        layer.confirm(res.message, {
                            btn: ['确定'], //按钮
                            closeBtn:0
                        }, function(){
                            window.location.href='/pages/front/private/personal_info.html';
                        });
                    } else if (res.code==401) {
                        layer.alert("登录失效，请重新登录！！！")
                    } else{
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