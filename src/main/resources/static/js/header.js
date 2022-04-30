/**
 * 
 */

/**
 * header 搜索框弹出
 */


function sToBig(){
	var sdiv=document.getElementById('search-box');
	var adiv=document.getElementById('searchBth');
	var indiv=document.getElementById('search-input');
	indiv.onfocus=function(){
		adiv.style.backgroundPosition="8px -534px";
		adiv.style.backgroundColor="#fff";	
		sdiv.className+=" itemSearch-focus";		
	}
	indiv.onblur=function(){
		adiv.style.backgroundPosition="";
		adiv.style.backgroundColor="";
        sdiv.className = sdiv.className.replace(' itemSearch-focus', '');//用空白替换掉' itemSearch-focus'
	}
}

/**
 * 回到顶部
 */



window.onscroll = function () {
	var toTop= document.getElementById('SiteGoTopBtn');
	var aa = document.body.scrollTop+document.documentElement.scrollTop;
	var a=true;
    if (aa > 300) {
        toTop.style.display = "block";
    }
    else {
        toTop.style.display = "none";
    } 
   

    toTop.onclick = function(){
    	//alert();
    	    
        var timer=setInterval(function(){
        	document.documentElement.scrollTop=document.documentElement.scrollTop-30;
        	if(document.documentElement.scrollTop==0)  clearInterval(timer);
        }
        ,1);		 
    }
   
    //console.log(document.body.scrollTop);


}


$(document).ready(function(){
	var token = localStorage.getItem('token')
	if (token) {
		$("#headerRightShow").append(
			`
			<div class="siteHLoginBox clearfix">
                <a href="/pages/showPerson.html" class="sitehH_login Js_showlogin">个人中心</a>
                <span class="line"></span>
                <a href="javascript:;" id="logoutBtn" class="siteH_register Js_showRegister">注销</a>
            </div>
			`
		)
	} else {
		$("#headerRightShow").append(
			`
			<div class="siteHLoginBox clearfix">
                <a href="/pages/front/login.html" class="sitehH_login Js_showlogin">登录</a>
                <span class="line"></span>
                <a href="/pages/front/register.html" class="siteH_register Js_showRegister">注册</a>
            </div>
			`
		)
	}

	$("#logoutBtn").on('click', function () {
		let token = localStorage.getItem("token");
		$.ajax({
			type: 'GET',
			async: false,
			url: '/user/logout' ,
			//contentType: "application/json",
			data:  {},
			headers: {
				"Authorization": token ? ('Bearer ' + JSON.parse(token).token) : ''
			},
			dataType: 'json',
			success: function (res) {
				if (res.code == 200 || res.code == 401) {
					// 将token存储到本地
					localStorage.removeItem('token')
					// 请求成功后跳转到首页
					if (res.code == 200) {
						location.href = '/'
					} else {
						location.href = '/pages/front/login.html'
					}
				} else {
					layer.alert(res.message)
				}
			}
		});
	})

})








