$(document).ready(function () {

    $("#dropdownItem1-one-load").click(function (){
        var formData = new FormData($('#uploadForm')[0]);
        $.ajax({
            url : "/admin/uploadImg",
            type : "post",
            data : formData,
            cache : false,
            processData : false,
            contentType : false,
            success : function (res) {
                if (res === "success"){
                    alert("上传成功！")
                }
            }
        })
    })

    // 下拉菜单切换页面
    $('.navbar-nav #dropdownMenu1 ul li').click(function () {
        var num = $(this).index();
        $('.show').removeClass('show');
        $('.content .area #dropdownItem1>div').eq(num).addClass('show');
        switch (num) {
            case 1:
                AllPageNum("a_release", "num")
                changePageNum("two")
                changeUpDown("two")
                selectData("selectAuthData", "two")
                break;
            case 2:
                AllPageNum("material_need", "num1")
                changePageNum("material")
                changeUpDown("material")
                selectData("selectMaterialNeedData", "material")
                break;
            case 3:
                AllPageNum("rescue_need", "num1")
                changePageNum("rescue")
                changeUpDown("rescue")
                selectData("selectRescueNeedData", "rescue")
                break;
            case 4:
                AllPageNum("piyaopage", "num")
                changePageNum("piyao")
                changeUpDown("piyao")
                selectData("selectPiyaoData", "piyao")
                break;
        }

    });

    $('.navbar-nav #dropdownMenu2 ul li').click(function () {
        var num = $(this).index();
        $('.show').removeClass('show');
        $('.content .area #dropdownItem2>div').eq(num).addClass('show');
        AllPageNum("User");
        selectUserData();
        changeUpAndDown();
    });


    // 上传灾区照片
    var progress = document.getElementById('dropdownItem1-one-progress');
    var events = {
        load: function () {
            console.log('loaded');
        },
        progress: function (percent) {
            progress.value = percent;
        },
        success: function () {
            console.log('success');
        }
    };
    var loader;
    // 选择好要上传的文件后触发onchange事件
    document.getElementById('dropdownItem1-one-file').onchange = function () {
        var file = this.files[0];
        console.log(this.files)
        console.log(file);
        //loadFile.js
        loader = new FileLoader(file, events);
    };
    document.getElementById('dropdownItem1-one-abort').onclick = function () {
        loader.abort();
    }

    // 提交权威发布文章
    $('.dropdownItem1-two01>button').click(function () {
        $('.dropdownItem1-two02').removeClass('hidden').addClass('show');
        $('.dropdownItem1-two01').removeClass('show').addClass('hidden');
    });
    $('.dropdownItem1-two02 button').click(function () {
        commitAuthPage();
        $('.dropdownItem1-two02').removeClass('show').addClass('hidden');
        $('.dropdownItem1-two01').removeClass('hidden').addClass('show');
    });

    // 澄清谣言
    $('.dropdownItem1-five01>button').click(function () {
        $('.dropdownItem1-five02').removeClass('hidden').addClass('show');
        $('.dropdownItem1-five01').removeClass('show').addClass('hidden');
    });
    $('.dropdownItem1-five02 button').click(function () {
        commitPiyaoPage();
        $('.dropdownItem1-five02').removeClass('show').addClass('hidden');
        $('.dropdownItem1-five01').removeClass('hidden').addClass('show');
    });

    //下一页
    $("#dropdownItem2-one .page-next").click(function () {
        $("#dropdownItem2-one .page-now").text(Number($("#dropdownItem2-one .page-now").text()) + 1)
        changeUpAndDown();
        selectUserData();
    })

    //上一页
    $("#dropdownItem2-one .page-previous").click(function () {
        $("#dropdownItem2-one .page-now").text(Number($("#dropdownItem2-one .page-now").text()) - 1)
        changeUpAndDown();
        selectUserData();
    })

})

function changeUpAndDown() {

    if ($("#dropdownItem2-one .page-now").text() !== "1") {
        $("#dropdownItem2-one .page-previous").attr("class", "btn page-previous").css("pointer-events", "")
    } else {
        $("#dropdownItem2-one .page-previous").attr("class", "btn page-previous disabled").css("pointer-events", "none")
    }
    if ($("#dropdownItem2-one .page-now").text() === $("#dropdownItem2-one .page-total").attr("value")) {
        $("#dropdownItem2-one .page-next").attr("class", "btn page-next disabled").css("pointer-events", "none")
    } else {
        $("#dropdownItem2-one .page-next").attr("class", "btn page-next").css("pointer-events", "")
    }
}


function selectUserData() {
    $.ajax({
        url: "/admin/selectUserData",
        data: {
            currPage: $("#dropdownItem2-one .page-now").text()
        },
        success: function (res) {
            console.log(res)
            var html = "<table class='table table-striped'><tr><th>手机号</th><th>邮箱</th><th>用户名</th><th>权限</th></tr>"

            for (var i = 0; i < res.length; i++) {
                html += "<tr><td>" + res[i].username + "</td><td>" + res[i].email + "</td><td>" + res[i].displayName + "</td>" +
                    "<td><div class='btn-group'><button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>" + res[i].right + "<span class='caret'></span></button>" +
                    "<ul class='dropdown-menu'><li><a onclick='updateRight(" + res[i].id + ",\"user\")'>普通用户</a></li><li><a onclick='updateRight(" + res[i].id + ",\"organization\")'>救援组织</a></li></ul></div></td></tr>"
            }
            html += "</table>"
            $("#dropdownItem2-one .dropdownItem1-five01").html(html)

        }
    })
}

function updateRight(id, right) {
    $.ajax({
        url: "/admin/adminUpdate",
        method: "post",
        data: {
            id: id,
            right: right
        },
        success: function (res) {
            if (res === "success") {
                alert("修改成功");
                selectUserData();
            }
        }
    })
}

function AllPageNum(name, url) {

    $.ajax({
        url: "/admin/" + url,
        async: false,
        data: {
            name: name
        },
        success: function (res) {
            var realNum = Math.ceil(Number(res) / 10)
            $(".page-total").text(realNum).attr("value", realNum)
        }
    })
}

function selectData(url, page) {
    var currPage
    switch (page) {
        case "piyao":
            currPage = $("#dropdownItem1-five .page-now").text()
            break;
        case "rescue":
            currPage = $("#dropdownItem1-four .page-now").text()
            break;
        case "material":
            currPage = $("#dropdownItem1-three .page-now").text()
            break;
        case "two":
            currPage = $("#dropdownItem1-two .page-now").text()
            break;
    }

    $.ajax({
        url: "/admin/" + url,
        data: {
            currPage: currPage
        },
        success: function (res) {

            switch (page) {
                case "piyao":
                    var piyaohtml = "<table class='table table-striped'><tr><th>标题</th><th>内容</th><th>时间</th><th>操作</th></tr>"

                    for (var i = 0; i < res.length; i++) {
                        piyaohtml += "<tr><td>" + res[i].title + "</td><td>" + res[i].msg + "</td><td>" + res[i].date + "</td><td><button type='button' class='btn btn-danger' onclick='deleteById(" + res[i].id + ",\"piyao\")'>删除</button></td></tr>"
                    }
                    piyaohtml += "</table>"
                    $("#dropdownItem1-five .dropdownItem1-five01 .table").empty()
                    $("#dropdownItem1-five .dropdownItem1-five01 .table").append(piyaohtml)
                    break;
                case "rescue":
                    var rescuehtml = "<table class='table table-striped'><tr><th>姓名</th><th>年龄</th><th>地点</th><th>提交用户</th><th>联系电话</th><th>描述</th><th>操作</th></tr>"

                    for (var i = 0; i < res.length; i++) {
                        rescuehtml += "<tr><td>" + res[i].name + "</td><td>" + res[i].age + "</td><td>" + res[i].place + "</td><td>" + res[i].username + "</td><td>" + res[i].telNum + "</td><td>" + res[i].msg + "</td><td><button type='button' class='btn btn-primary' onclick='updateStatus(" + res[i].id + ",\"rescue\")'>审核通过</button></td></tr>"
                    }
                    rescuehtml += "</table>"

                    $("#dropdownItem1-four .dropdownItem1-four01 .table").empty()
                    $("#dropdownItem1-four .dropdownItem1-four01 .table").append(rescuehtml)
                    break;
                case "material":
                    var materialhtml = "<table class='table table-striped'><tr><th>物资名称</th><th>所需数量</th><th>所需地区</th><th>操作</th></tr>"

                    for (var i = 0; i < res.length; i++) {
                        materialhtml += "<tr><td>" + res[i].material_name + "</td><td>" + res[i].number + "</td><td>" + res[i].area + "</td><td><button type='button' class='btn btn-primary' onclick='updateStatus(" + res[i].id + ",\"material\")'>审核通过</button></td></tr>"
                    }
                    materialhtml += "</table>"

                    $("#dropdownItem1-three .dropdownItem1-three01 .table").empty()
                    $("#dropdownItem1-three .dropdownItem1-three01 .table").append(materialhtml)
                    break;
                case "two":
                    var twohtml = "<table class='table table-striped'><tr><th>标题</th><th>内容</th><th>时间</th><th>操作</th></tr>"

                    for (var i = 0; i < res.length; i++) {
                        twohtml += "<tr><td>" + res[i].title + "</td><td>" + res[i].msg + "</td><td>" + res[i].date + "</td><td><button type='button' class='btn btn-danger' onclick='deleteById(" + res[i].id + ",\"auth\")'>删除</button></td></tr>"
                    }
                    twohtml += "</table>"
                    $("#dropdownItem1-two .dropdownItem1-two01 .table").empty()
                    $("#dropdownItem1-two .dropdownItem1-two01 .table").append(twohtml)
                    break;
            }

        }
    })
}

function changePageNum(page) {
    switch (page) {
        case "piyao":
            //下一页
            $("#dropdownItem1-five .page-next").click(function () {
                $("#dropdownItem1-five .page-now").text(Number($("#dropdownItem1-five .page-now").text()) + 1)
                changeUpDown(page);
                selectData("selectPiyaoData", page)
            })

            //上一页
            $("#dropdownItem1-five .page-previous").click(function () {
                $("#dropdownItem1-five .page-now").text(Number($("#dropdownItem1-five .page-now").text()) - 1)
                changeUpDown(page);
                selectData("selectPiyaoData", page)
            })
            break;
        case "rescue":
            //下一页
            $("#dropdownItem1-four .page-next").click(function () {
                $("#dropdownItem1-four .page-now").text(Number($("#dropdownItem1-four .page-now").text()) + 1)
                changeUpDown(page);
                selectData("selectRescueNeedData", page)
            })

            //上一页
            $("#dropdownItem1-four .page-previous").click(function () {
                $("#dropdownItem1-four .page-now").text(Number($("#dropdownItem1-four .page-now").text()) - 1)
                changeUpDown(page);
                selectData("selectRescueNeedData", page)
            })
            break;
        case "material":
            //下一页
            $("#dropdownItem1-three .page-next").click(function () {
                $("#dropdownItem1-three .page-now").text(Number($("#dropdownItem1-three .page-now").text()) + 1)
                changeUpDown(page);
                selectData("selectMaterialNeedData", page)
            })

            //上一页
            $("#dropdownItem1-three .page-previous").click(function () {
                $("#dropdownItem1-three .page-now").text(Number($("#dropdownItem1-three .page-now").text()) - 1)
                changeUpDown(page);
                selectData("selectMaterialNeedData", page)
            })
            break;
        case "two":
            //下一页
            $("#dropdownItem1-two .page-next").click(function () {
                $("#dropdownItem1-two .page-now").text(Number($("#dropdownItem1-two .page-now").text()) + 1)
                changeUpDown(page);
                selectData("selectAuthData", page)
            })

            //上一页
            $("#dropdownItem1-two .page-previous").click(function () {
                $("#dropdownItem1-two .page-now").text(Number($("#dropdownItem1-two .page-now").text()) - 1)
                changeUpDown(page);
                selectData("selectAuthData", page)
            })
            break;
    }
}

function changeUpDown(page) {
    switch (page) {
        case "piyao":
            if ($("#dropdownItem1-five .page-now").text() !== "1") {
                $("#dropdownItem1-five .page-previous").attr("class", "btn page-previous").css("pointer-events", "")
            } else {
                $("#dropdownItem1-five .page-previous").attr("class", "btn page-previous disabled").css("pointer-events", "none")
            }
            if ($("#dropdownItem1-five .page-now").text() === $("#dropdownItem1-five .page-total").attr("value")) {
                $("#dropdownItem1-five .page-next").attr("class", "btn page-next disabled").css("pointer-events", "none")
            } else {
                $("#dropdownItem1-five .page-next").attr("class", "btn page-next").css("pointer-events", "")
            }
            break;
        case "rescue":
            if ($("#dropdownItem1-four .page-now").text() !== "1") {
                $("#dropdownItem1-four .page-previous").attr("class", "btn page-previous").css("pointer-events", "")
            } else {
                $("#dropdownItem1-four .page-previous").attr("class", "btn page-previous disabled").css("pointer-events", "none")
            }
            if ($("#dropdownItem1-four .page-now").text() === $("#dropdownItem1-four .page-total").attr("value")) {
                $("#dropdownItem1-four .page-next").attr("class", "btn page-next disabled").css("pointer-events", "none")
            } else {
                $("#dropdownItem1-four .page-next").attr("class", "btn page-next").css("pointer-events", "")
            }
            break;
        case "material":
            if ($("#dropdownItem1-three .page-now").text() !== "1") {
                $("#dropdownItem1-three .page-previous").attr("class", "btn page-previous").css("pointer-events", "")
            } else {
                $("#dropdownItem1-three .page-previous").attr("class", "btn page-previous disabled").css("pointer-events", "none")
            }
            if ($("#dropdownItem1-three .page-now").text() === $("#dropdownItem1-three .page-total").attr("value")) {
                $("#dropdownItem1-three .page-next").attr("class", "btn page-next disabled").css("pointer-events", "none")
            } else {
                $("#dropdownItem1-three .page-next").attr("class", "btn page-next").css("pointer-events", "")
            }
            break;
        case "two":
            if ($("#dropdownItem1-two .page-now").text() !== "1") {
                $("#dropdownItem1-two .page-previous").attr("class", "btn page-previous").css("pointer-events", "")
            } else {
                $("#dropdownItem1-two .page-previous").attr("class", "btn page-previous disabled").css("pointer-events", "none")
            }
            if ($("#dropdownItem1-two .page-now").text() === $("#dropdownItem1-two .page-total").attr("value")) {
                $("#dropdownItem1-two .page-next").attr("class", "btn page-next disabled").css("pointer-events", "none")
            } else {
                $("#dropdownItem1-two .page-next").attr("class", "btn page-next").css("pointer-events", "")
            }
            break;
    }
}

function commitPiyaoPage() {
    $.ajax({
        url: "/admin/piyao",
        method: "post",
        data: {
            title: $("#piyao-title").val(),
            Msg: $("#piyao-msg").val()
        },
        success: function (res) {
            if (res === "success") {
                alert("发布成功！")
                selectData("selectPiyaoData", "piyao")
            }
        }
    })
}

function commitAuthPage() {
    $.ajax({
        url: "/admin/release",
        method: "post",
        data: {
            title: $("#auth-title").val(),
            msg: $("#auth-msg").val()
        },
        success: function (res) {
            if (res === "success") {
                alert("发布成功！")
                selectData("selectAuthData", "two")
            }
        }
    })
}

function deleteById(id, flag) {
    switch (flag) {
        case "auth":
            $.ajax({
                url: "/admin/release/" + id,
                method: "delete",
                success: function (res) {
                    if (res === "success") {
                        alert("删除成功！")
                        selectData("selectAuthData", "two")
                    }
                }
            })
            break;
        case "piyao":
            $.ajax({
                url: "/admin/piyao/" + id,
                method: "delete",
                success: function (res) {
                    if (res === "success") {
                        alert("删除成功！")
                        selectData("selectPiyaoData", "piyao")
                    }
                }
            })
    }
}

function updateStatus(id, flag) {
    $.ajax({
        url: "/admin/apply/" + flag + "/" + id,
        success: function (res) {
            if (res === "success") {
                alert("成功！")
                if (flag === "rescue") {
                    selectData("selectRescueNeedData", flag)
                }
                if (flag === "material") {
                    selectData("selectMaterialNeedData", flag)
                }
            }
        }
    })
}
