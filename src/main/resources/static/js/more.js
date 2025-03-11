
$(document).ready(function (){

    //当每页评论数的select中option变化时，再次调用初始化
    $(".pageSize").change(function (){
        $(".currPage").val("1")
        if(sessionStorage.getItem("page") === "auth"){
            DetailsList("/Article/selectReleaseByPage", "auth");
            ChangeSpanAndOption("/Article/selectAllNum");
        }

        if(sessionStorage.getItem("page") === "rescue"){
            DetailsList("/Article//selectRescueByPage", "rescue");
            ChangeSpanAndOption("/Article/selectRescueAllNum");
        }

        changeUpAndDown();

    })

    //当页数的select中option发生变化时，调用留言板初始化
    $(".currPage").change(function (){
        if(sessionStorage.getItem("page") === "auth"){
            DetailsList("/Article/selectReleaseByPage", "auth");
        }

        if(sessionStorage.getItem("page") === "rescue"){
            DetailsList("/Article//selectRescueByPage", "rescue");
        }
        changeUpAndDown();
    })
})

//权威发布分页获取数据
function DetailsList(url, string){
    var currPage = $(".currPage").val()
    var pageSize = $(".pageSize").val()

    var html = ""
    $.ajax({
        url: url,
        data: {
            "currPage" : currPage,
            "pageSize" : pageSize
        },
        dataType: "json",
        success: function (res) {
            for(var i = 0; i < res.length; i++){
                html += "<div><div class='articleBox'>" +
                    "<div class='newsTitle'>" +
                    "<a href='/Article/detail/" + string + "/" + res[i].id +"'>"+ res[i].title +"</a>" +
                    "<p>"+ res[i].name +"</p>" +
                    "</div>" +
                    "<div class='newsText'>"+ res[i].msg +"</div>" +
                    "<div class='newsInfo'>" +
                    "<p>"+ res[i].date +"</p>" +
                    "<a href='/Article/detail/" + string + "/" + res[i].id +"'>阅读全文</a>" +
                    "</div></div></div>"
            }
            $("#article").html(html)
        }
    })
}

