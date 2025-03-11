$(document).ready(function () {
    getTotalNum();
    changeUpAndDown();
    getPageData();

    //下一页
    $(".page-next").click(function () {
        $(".page-now").text(Number($(".page-now").text()) + 1)
        changeUpAndDown();
        getPageData();
    })

    //上一页
    $(".page-previous").click(function () {
        $(".page-now").text(Number($(".page-now").text()) - 1)
        changeUpAndDown();
        getPageData();
    })
})

function changeUpAndDown(){

    if($(".page-now").text() !== "1"){
        $(".page-previous").attr("class", "btn page-previous").css("pointer-events", "")
    }
    else {
        $(".page-previous").attr("class", "btn page-previous disabled").css("pointer-events", "none")
    }
    if($(".page-now").text() === $(".page-total").attr("value")){
        $(".page-next").attr("class", "btn page-next disabled").css("pointer-events", "none")
    }
    else {
        $(".page-next").attr("class", "btn page-next").css("pointer-events", "")
    }
}


function getTotalNum(){
    $.ajax({
        url : "/Piyao/totalNum",
        async : false,
        success : function (res){
            $("#total-number").text(res)
            var pageTotal = Math.ceil(Number(res) / 10)
            $(".page .page-total").text(pageTotal).attr("value", pageTotal)
        }
    })

    $.ajax({
        url : "/Piyao/yesTotalNum",
        success : function (res){
            $("#yes-total-number").text(res)
        }
    })
}

function getPageData(){
    $.ajax({
        url : "/Piyao/pageData",
        data : {
            currPage : $(".page-now").text()
        },
        success : function (res) {
            var html = "";

            for (var i = 0; i < res.length; i++) {
                html += "<li><a href='/Article/detail/piyao/"+ res[i].id +"' class='area-li-titlelist'>" +
                    "<div class='rumor'><div class='icon_rumor'>谣言</div><div class='rumor-date'>"+ res[i].date +"</div>" +
                    "<div class='rumor-title'>"+ res[i].title +"</div></div>" +
                    "<div class='rumor-tips'>已辟谣</div></a></li>"
            }
            $(".area-ul-titlelist").html(html)
        }
    })
}