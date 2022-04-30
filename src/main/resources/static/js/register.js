$(document).ready(function(){
    $(".login_btn").on('click', function () {
        $("#resMsgID").html('')
        var password = $("#Password").val()
        var confirmPassword = $("#ConfirmPassword").val()
        if (password != confirmPassword) {
            $("#resMsgID").html("前后密码不一致")
            return
        }
        $.ajax({
            type: 'POST',
            async: false,
            url: '/user/register' ,
            //contentType: "application/json",
            data:  {
                'username': $("#Account").val(),
                'password': password,
                'code': $("#ImgCode").val()
            },
            dataType: 'json',
            success: function (res) {
                if (res.code != 200) {
                    $("#resMsgID").html(res.message)
                } else {
                    layer.confirm('注册成功，去登录？', {
                        btn: ['确定'], //按钮
                        closeBtn: 0
                    }, function(){
                        location.href = '/pages/front/login.html'
                    });
                }
            }
        });
    })
})