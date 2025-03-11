
$(document).ready(function (){
    //下一页
    $("#down").click(function (){
        var pageNum = Number($(".currPage").val())
        var newNum = pageNum + 1
        $(".currPage").val(newNum).change()
    })

    //上一页
    $("#up").click(function (){
        var pageNum = Number($(".currPage").val())
        var newNum = pageNum - 1
        if(newNum !== 0)
            $(".currPage").val(newNum).change()
    })

})



function changeUpAndDown(){

    if($(".currPage").val() !== "1"){
        $("#up").attr("class", "pageSpan").css("pointer-events", "")
    }
    else {
        $("#up").attr("class", "disabled").css("pointer-events", "none")
    }
    if($(".currPage").val() === $(".realPageNum").attr("value")){
        $("#down").attr("class", "disabled").css("pointer-events", "none")
    }
    else {
        $("#down").attr("class", "pageSpan").css("pointer-events", "")
    }
}

//分页计算最大页数
function ChangeSpanAndOption(url){
    $.ajax({
        url : url,
        async : false,
        success : function (res){
            var pageSize = $(".pageSize").val()
            var realPageNum = Math.ceil(res / pageSize)
            var html = ""

            for(var i = 0; i < realPageNum; i++){
                html += "<option value='"+ (i+1) +"'>"+ (i+1) +"</option>"
            }
            $(".currPage").html(html)
            $(".realPageNum").text("/ " + realPageNum).attr("value", realPageNum)

        }
    })
}
