var right = sessionStorage.getItem("right");
var displayName = sessionStorage.getItem("displayName")
var flag = true

function CleanSession() {
    sessionStorage.removeItem("right")
    sessionStorage.removeItem("displayName")
    $.ajax({
        url: "/User/logout"
    })
}

$(document).ready(function (){
    // $(document).off('click.bs.dropdown.data-api');
    // dropdownOpen();//调用调用方法对物资列表初始化


    //当域中right不为空时请求后台获取对应权限列表，并放开评论框
    if (right != null) {
        $.ajax({
            type: "post",
            url: "/User/model",
            data: {right: right},
            dataType: "json",
            success: function (res) {
                console.log(res)
                flag = false
                if (res.toString() === null) {
                    return
                }

                if (res.toString() === "非法请求") {
                    return;
                }

                var modelhtml = "";
                for (var key in res) {
                    $(".login").css("visibility", "hidden")
                    $(".modelList").css("visibility", "visible")

                    modelhtml += "<li><a href='" + res[key] + "'>" + key + "</a></li>"

                }
                modelhtml += "<li><a href='/User/logout' onclick='CleanSession()'>注销</a></li>"
                $(".dropdown-menu").append(modelhtml)
                $("#displayName").html(displayName + "<strong class='caret'></strong>")
                setTextarea()
            }
        })
    }
})

// /**
//  * 鼠标划过就展开子菜单，免得需要点击才能展开
//  */
// function dropdownOpen() {
//
//     var $dropdownLi = $('li .dropdown');
//
//     $dropdownLi.mouseover(function () {
//         $(this).addClass('open');
//     }).mouseout(function () {
//         $(this).removeClass('open');
//     });
// }





