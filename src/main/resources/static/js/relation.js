$(document).ready(function (){
    //页面切换
    $("#info-btn").click(function (){
        $("#info-box").removeAttr("hidden")
        $("#material-box").attr("hidden", "hidden")
    })

    $("#material-btn").click(function (){
        $("#info-box").attr("hidden", "hidden")
        $("#material-box").removeAttr("hidden")
    })

    $("#rescueNeedBtn").click(function (){
        //获取输入内容
        var name = $("#name").val()
        var age = $("#age").val()
        var telNum = $("#tel").val()
        var place = $("#place").val()
        var msg = $("#msg").val()

        //发送请求至后台提交
        commitInfo("/User/commit", name, age, telNum, place, msg)
    })

    $("#materialNeedBtn").click(function (){
        //获取表单内容
        var material_name = $("#materialName").val()
        var number = $("#materialNum").val()
        var area = $("#materialPlace").val()

        commitInfo("/Material/commit", material_name, number, area)
    })

    $("#Btn").click(function (){
        commitRescue()
    })
})

function commitInfo(url, ...args){

    if(args.length === 5){
        $.ajax({
            url: url,
            method: "post",
            data: {
                name: args[0],
                age: args[1],
                telNum : args[2],
                place: args[3],
                msg: args[4]
            },
            success: function (res) {
                if (res === "success") {
                    alert("提交成功！")
                } else if (res === "fail") {
                    alert("请稍后再试！")
                } else {
                    alert("此信息已由 " + res + " 提交")
                }
                location.reload()
            }
        })
    }

    if(args.length === 3){
        $.ajax({
            url : url,
            method: "post",
            data : {
                material_name : args[0],
                number : args[1],
                Area : args[2]
            },
            success : function (res) {
                if (res === "success") {
                    alert("提交成功！")
                } else if (res === "fail") {
                    alert("请稍后再试！")
                } else if (res === "exist") {
                    if(confirm("此信息已被提交，我们正竭力调配资源，是否再次提交")){
                        $.ajax({
                            url : "/Material/again",
                            method : "post",
                            data : {
                                material_name : args[0],
                                number : args[1],
                                Area : args[2]
                            },
                            success : function (res){
                                if (res === "success") {
                                    alert("提交成功！")
                                } else {
                                    alert("请稍后再试！")
                                }
                            }
                        })
                    }
                }
                else {
                    alert("出错啦，请稍后再试！")
                }
                clean()
            }
        })
    }
}

function clean(){
    $("#materialName").val("")
    $("#materialNum").val("")
    $("#materialPlace").val("")
}

function commitRescue(){
    var title = $("#rescueTitle").val()
    var msg = $("#rescueMsg").val()

    $.ajax({
        url : "/Article/rescueCommit",
        method : "post",
        data : {
            Title : title,
            Msg : msg
        },
        success : function (res) {
            alert(res)
        }

    })


}