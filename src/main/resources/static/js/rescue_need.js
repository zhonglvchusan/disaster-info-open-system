$(document).ready(function (){

    changeSpanAndOption("/Rescue/selectTotalNum", 1);
    changeTable("/Rescue/selectByPage", 1);

    //当页数的select中option发生变化时，调用留言板初始化
    $(".currPage").change(function (){
        changeTable("/Rescue/selectByPage")
        changeUpAndDown();
    })

    $("#title-info").click(function (){
        changeSpanAndOption("/Rescue/selectTotalNum", 1);
        changeTable("/Rescue/selectByPage", 1);
    })

    $("#title-my").click(function (){
        changeSpanAndOption("/Rescue/selectTotalNum", 2);
        changeTable("/Rescue/selectByPage", 2, true)
    })

})

//分页计算最大页数
function changeSpanAndOption(url, status){
    $.ajax({
        url : url,
        async : false,
        data : {status : status},
        success : function (res){
            var realPageNum = Math.ceil(res / 10)
            var html = ""

            for(var i = 0; i < realPageNum; i++){
                html += "<option value='"+ (i+1) +"'>"+ (i+1) +"</option>"
            }
            $(".currPage").html(html)
            $(".realPageNum").text("/ " + realPageNum).attr("value", realPageNum)

        }
    })
}

//分页获取数据
function changeTable(url, status, flag){
    $.ajax({
        url : url,
        data : {
            currPage : $(".currPage").val(),
            status : status
        },
        success : function (res) {

            var html = "<table class='table table-hover'>" +
                "<thead>" +
                "<tr>" +
                "<th>姓名</th>" +
                "<th>年龄</th>" +
                "<th>联系电话</th>" +
                "<th>详细地址</th>" +
                "<th>描述</th>" +
                "<th>操作</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>"

            for (var i = 0; i < res.length; i++) {
                html += "<tr><td>"+ res[i].name +"</td><td>"+ res[i].age +"</td><td>"+ res[i].telNum +"</td><td title='"+ res[i].place +"' style='max-width: 241px'>"+ res[i].place +"</td><td title='"+ res[i].msg +"'>"+ res[i].msg +"</td><td>"
                if(flag){
                    html +=  "<button style='background: #f67272'  class='btn btn-primary' onclick='commitRescueProgress("+ res[i].id +",\""+ res[i].place +"\",\""+ res[i].name +"\")'>完成救援</button></td></tr>"
                }
                else {
                    html += "<button class='btn btn-primary' onclick='commitRescueInfo("+ res[i].id +")'>前往救援</button></td></tr>"
                }
            }

            html += "</tbody></table>"
            $("#table-info").html(html)

        }
    })
}

function commitRescueInfo(id){
    $.ajax({
        url : "/Rescue/go",
        data : {id : id},
        success : function (res){
            if(res === "success"){
                alert("操作成功！感谢您奉献爱心，请尽快前往救援！")
            }
            else {
                alert("失败啦！稍后重试")
            }
        }
    })
}

function commitRescueProgress(id, Place, name){
    $.ajax({
        url : "/Article/rescueCommit",
        method : "post",
        data : {
            Title : "关于"+ Place + name +"救援的进度",
            Msg : "经过不懈努力，救援已经完成，情况待公开！"
        },
        success : function (res){
            if(res === "success"){
                $.ajax({
                    url : "/Rescue/end",
                    data : {
                        id : id
                    },
                    success : function (res){
                        console.log(res)
                    }
                })
                alert("操作成功！感谢您奉献爱心!")
                $("#title-my").trigger("click")
            }
            else {
                alert("失败啦！稍后重试")
            }
        }
    })
}

