$(document).ready(function(){
    $(".login_btn").on('click', function () {
        $("#resMsgID").html('')
        $.ajax({
            type: 'POST',
            async: false,
            url: API_BASE_URL + '/front/user/login' ,
            //contentType: "application/json",
            data:  {
                'username': $("#Account").val(),
                'password': $("#Password").val(),
                'code': $("#ImgCode").val()
            },
            dataType: 'json',
            success: function (res) {
                if (res.code != 200) {
                    $("#resMsgID").html(res.message)
                } else {
                    // 将token存储到本地
                    localStorage.setItem('crowdfunding-token', JSON.stringify(res.data))
                    // 请求成功后跳转到首页
                    location.href = '/'
                }
            }
        });
    })
})