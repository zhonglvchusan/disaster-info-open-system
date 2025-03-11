var btn = $(".buttonchecking")
var returnCheck = null
var tel = null
var flag1 = true

//按钮点击事件
btn.click(function () {
    // alert("60s后可重发！")
    tel = $("#tel").val()
    $.ajax({
        async: false,
        url: "/User/username",
        type: "get",
        success: function (res){
            for (let i in res){
                if (tel == res[i]){
                    alert("账户已存在！")
                    flag1 = false
                    location.reload()
                }
            }
            if(flag1){
                if (tel.length === 11) {
                    //获取验证码
                    getRandomCode(tel)
                    if(returnCheck.length === 6){
                        //60s倒计时方法
                        getCountDown()
                    }
                    // console.log(returnCheck)
                } else {
                    alert("手机号格式不正确！")
                }
            }
        }
    })
})


var time = 60;


//倒计时
function getCountDown() {
    if (time === 0) {
        time = 60;
        btn.text("重新获取")
        btn.css("background-color", "#FF4B2B")
        btn.css("color", "#fff")
        btn.css("border-color", "#FF4B2B")
        btn.attr("disabled", false)
        return;
    } else {
        time--;
        btn.text(time + "s后可重发!")
        btn.css("background-color", "#eee")
        btn.css("color", "#999999")
        btn.css("border-color", "#eee")
        btn.attr("disabled", true)
    }
    setTimeout(function () {
        getCountDown();
    }, 1000);
}

//请求后台获取验证码
function getRandomCode(tel) {
    $.ajax({
        async: false,
        type: "post",
        url: "/User/check",
        data: {"tel": tel},
        success: function (res) {
            console.log(res)
            if(res.length !== 6){
                alert(res)
                returnCheck = res
            }
            else {
                returnCheck = res
            }
        }
    })
}

//注册方法
function LegalCheck() {
    //获取用户输入值
    var inputCheck = $("#check").val()
    var password = $("#password").val()

    if (inputCheck === returnCheck) {
        if (password != null) {
            $.ajax({
                url: "/User/register",
                type: "post",
                data: {
                    "tel": tel,
                    "password": password,
                    "right": "user"
                },
                success: function (res) {
                    if(res == 1){
                        alert("注册成功！")
                        location.reload()
                    }
                }
            })
        } else {
            alert("密码不能为空!")
        }
    } else {
        alert("验证码输入不正确！")
    }
}


