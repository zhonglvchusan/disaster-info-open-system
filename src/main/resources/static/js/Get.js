
function CleanSession() {
    sessionStorage.removeItem("UID")
    sessionStorage.removeItem("right")
    sessionStorage.removeItem("displayName")
    $.ajax({
        url: "/User/logout"
    })
}

$(document).ready()

$(document).ready(function () {

    MaterialInit()//
    BoardList();//调用方法对留言板进行初始化
    ChangeSpanAndOption("/Board/listNum");//调用方法对页数进行初始化
    setInterval("BoardList()", 3600000)//每一小时自动更新留言板，展示新添加评论
    lunbo();

    //当每页评论数的select中option变化时，再次调用初始化
    $(".pageSize").change(function (){
        $(".currPage").val("1")
        ChangeSpanAndOption("/Board/listNum");
        changeUpAndDown();
        BoardList();
    })

    //当页数的select中option发生变化时，调用留言板初始化
    $(".currPage").change(function (){
        BoardList();
        changeUpAndDown();
    })

});


//权威发布与救援情况列表获取
$(document).ready(function () {
    $.ajax({
        url: "/Article/selectRescue",
        dataType: "json",
        success: function (res) {
            add(res, "#rescue", "rescue")
        }
    })

    $.ajax({
        url: "/Article/selectRelease",
        dataType: "json",
        success: function (res) {
            add(res, "#authoritative", "auth")
        }
    })

    setTextarea()

})

//添加获取到的权威发布与救援情况数据
function add(res, id, string) {
    var html = ""
    for (var i in res) {
        html += "<div class='item1'>" +
            "<a href='/Article/detail/" + string + "/" + res[i].id + "'>" +
            "<div class='item_title'>" + res[i].title + "</div>" +
            "</a>" +
            "<div class='item_time'>" + res[i].date.split(" ")[0] + "</div>" +
            "</div>"
    }
    $(id).html(html)
}

//提交方法
function commit() {
    var message = $(".textMsg").val()

    if(message === null || message === ""){
        alert("内容不能为空！")
        return
    }

    $.ajax({
        url: "/Board/commit",
        data: {"message": message},
        dataType: "json",
        success: function (res) {

            if (res > 0) {
                //插入成功刷新下方留言板
                BoardList()
            }
            $(".textMsg").val("")
        }
    })
}

//留言板分页获取数据
function BoardList(){
    var currPage = $(".currPage").val()
    var pageSize = $(".pageSize").val()

    var html = ""
    $.ajax({
        url: "/Board/listByPage",
        data: {
            "currPage" : currPage,
            "pageSize" : pageSize
        },
        dataType: "json",
        success: function (res) {

            for(var i = 0; i < res.length; i++){
                html += "<div class='media well'>" +
                    "<div class='media-body mediaDiv'>" +
                    "<h3 class='media-heading displayName'>"+ res[i].displayName +"</h3>" +
                    "<div class='time'>"+ res[i].msgDate +"</div>" +
                    "</div>" +
                    "<div class='msg'>"+ res[i].msg.replace(/(\r\n|\n|\r)/gm, "<br />") +"</div>" +
                    "</div>"
            }
            $(".mediaBox").html(html)
        }
    })
}


//急需物资初始化
function MaterialInit(){
    $.ajax({
        url : "/Material/IndexMaterial",
        method : "get",
        dataType : "json",
        success : function (res){
            console.log(res)
            var html = "<tr><td>物资名称</td><td>数量</td><td>所需地区</td></tr>";

            for (var i in res){
                html += "<tr><td>"+ res[i].material_name +"</td><td>"+ res[i].number +"</td><td>"+ res[i].area +"</td></tr>"
            }

            $(".table").html(html)

        }
    })
}


function lunbo(){
    $.ajax({
        url : "/Board/LunBo",
        success : function (res) {
            console.log(res)
            for (var i = 0; i < res.length; i++) {
                $(".carousel-inner img").eq(i).attr("alt", res[i].title).attr("src", res[i].url)
            }
        }
    })
}

function setTextarea() {
    if (flag) {
        $("#textarea").html("<textarea rows='5' disabled='disabled' placeholder='登录后才能发表评论' class='textMsg'></textarea>")
    } else {
        $("#textarea").html("<textarea rows='5' placeholder='请输入留言内容' class='textMsg'></textarea>")
        $(".textLogin").attr("href", "javascript:void(0)").text("发表").attr("onclick", "commit()")
    }
}