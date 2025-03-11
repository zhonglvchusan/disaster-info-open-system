$(document).ready(function (){
    selectUserInfo(sessionStorage.getItem("UID"))
    selectChecked()
})
var chk_value_all = []

function selectUserInfo(UID){
    $.ajax({
        url: "/User/getInfo",
        data: {Id: UID},
        dataType: "json",
        success: function (res) {

            if(res.sex === null){
                res.sex = "未设置"
            }

            if(res.email === null){
                res.email = "未填写"
            }

            if(res.area === null){
                res.area = "未设置"
            }

            $("#username").text(res.displayName)
            $("#sex").text(res.sex)
            $("#tel").text(res.username)
            $("#email").text(res.email)
            $("#area").text(res.area)
        }
    })
}


//复选框选择后显示表单
function displayUpdateFrom(a){
    switch (a) {
        case "username":
            $("#username-form").removeAttr("hidden")
            break;
        case "sex":
            $("#sex-form").removeAttr("hidden")
            break;
        case "tel":
            $("#tel-form").removeAttr("hidden")
            break;
        case "email":
            $("#email-form").removeAttr("hidden")
            break;
        case "area":
            $("#area-form").removeAttr("hidden")
            break;
    }
}

//获取选中的复选框的name属性
function selectChecked(){
    $("#selectChecked input[type='checkbox']").click(function (){

        $(".modal-body .form-group").attr("hidden","hidden")

        var chk_value = [];
        $("#selectChecked input[type='checkbox']:checked").each(function (){
            chk_value.push($(this).prop("name"));
        });
        chk_value = chk_value.join(",");
        chk_value = chk_value.split(",")
        chk_value_all = chk_value
        console.log(chk_value_all)

        chk_value.forEach(displayUpdateFrom)//遍历列表，对选中的表单进行显示
    })
}

function updateUserInfo(){

    var username = "";
    var displayName = "";
    var email = "";
    var sex = "";
    var area = "";

    for (var i in chk_value_all) {
        if(chk_value_all.indexOf("username") > -1){
            displayName = $("#username-form #user_name").val();
            console.log(displayName)
            chk_value_all.shift()
        }

        if(chk_value_all.indexOf("sex") > -1){
            sex = $("#sex-form input[type='radio']:checked").val();
            console.log(sex)
            chk_value_all.shift()
        }

        if(chk_value_all.indexOf("tel") > -1){
            username = $("#tel-form #user_tel").val();
            console.log(username)
            chk_value_all.shift()
        }

        if(chk_value_all.indexOf("email") > -1){
            email = $("#email-form #user_email").val();
            console.log(email)
            chk_value_all.shift()
        }

        if(chk_value_all.indexOf("area") > -1){
            area = $("#area-form #user_area option:checked").text();
            console.log(area)
            chk_value_all.shift()
        }
    }

    if(username === ""){
        username = $("#tel").text()
    }

    if(displayName === ""){
        displayName = $("#username").text()
    }

    if(email === ""){
        email = $("#email").text()
    }

    if(sex === ""){
        sex = $("#sex").text()
    }

    if(area === ""){
        area = $("#area").text()
    }


    $.ajax({
        url : "/User/update",
        method : "post",
        data : {
            id : sessionStorage.getItem("UID"),
            username : username,
            displayName : displayName,
            email : email,
            sex : sex,
            area : area
        },
        success : function (res) {
            if(res === "修改成功"){
                sessionStorage.setItem("displayName", displayName)
                alert("信息修改成功")
                location.reload()
            }
        }
    })
}