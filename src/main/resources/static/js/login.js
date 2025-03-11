const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});

function Login(){
    var tel = $("#login-tel").val()
    var password = $("#login-password").val()

    if(tel.length === 11){
        $.ajax({
            url : "/User/login",
            type : "post",
            data : {
                "username" : tel,
                "password" : password
            },
            success : function (res){

                //用户名不存在时，后台返回空
                if(res.toString() === ""){
                    alert("用户名不存在！")
                    $("#login-tel").val("")
                    $("#login-password").val("")
                }
                else {
                    res =  JSON.parse(res)
                    if(res[0] !== '密码错误'){
                        if(res[1] === "admin"){
                            location.href = "admin"
                            sessionStorage.setItem("UID", res[0])
                            sessionStorage.setItem("right", res[1])
                            sessionStorage.setItem("displayName", res[2])
                        }
                        else{
                            location.href = "index"
                            sessionStorage.setItem("UID", res[0])
                            sessionStorage.setItem("right", res[1])
                            sessionStorage.setItem("displayName", res[2])
                        }
                    }
                    else {
                        alert("密码输入错误")
                    }
                }
            }
        })
    }
    else {
        alert("手机号格式不正确！")
    }
}