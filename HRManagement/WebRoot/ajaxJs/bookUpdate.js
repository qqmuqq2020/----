var fid = 4;
var index = 1;
var xfid=8;
//修改
function GetQueryString(id) // 此方法为获取另外一个页面url传来的参数 下面有两个网址可以查看了解
// Ctrl+左键单机网址
{ // http://www.jb51.net/article/48942.htm
    var reg = new RegExp("(^|&)" + id + "=([^&]*)(&|$)"); // http://blog.csdn.net/zxstone/article/details/12173015
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

$(function () {
    var id = GetQueryString("id");
    $.ajax({
        url:"/HRManagement/hrm/book/findById",
        data:{fid:fid,id:id},
        type:"get",
        success:function (data) {
            $("#name").val(data.name);
            $("#serialNumber").val(data.serialNumber);
            $("#publisher").val(data.publisher);
            $("#author").val(data.author);
            $("#total").val(data.total);
            $("#price").val(data.price);
            $("#detail").val(data.detail);
        }
    })
});


function updateById() {
    var id = GetQueryString("id");
    $.ajax({
        url:"/HRManagement/hrm/book/updateBook",
        type:"post",
        data:{
            name:$("#name").val(),
            serialNumber:$("#serialNumber").val(),
            publisher:$("#publisher").val(),
            author:$("#author").val(),
            total:$("#total").val(),
            price:$("#price").val(),
            detail:$("#detail").val(),
            inedx:index,
            fid:xfid,
            id:id
        },
        success:function (data) {
            Number(data);
            if (data > 0) {
            	alert("修改成功");
            	 window.location.href = "/HRManagement/manage/book/book.html?fid="+fid;
            }else {
            	alert("修改失败");
            	 window.location.href = "/HRManagement/manage/book/edit.html?fid="+xfid;
            	
            }
        }
    })
}

//返回事件
function update_list() {
    $("#alert").hide();
    $("#a").hide();
    window.location = "book.html?fid=" + fid;
};


//效验书名
function checkName() {
    var name = $("#name").val();
    if (name == "") {
        $("#b").text("书名必填");
        flag = false;
    } else {
        $("#b").text("正确");
        flag = true;
    }
    return flag;
}
//效验书名
function checkserialNumber() {
    var serialNumber = $("#serialNumber").val();
    if (serialNumber == "") {
        $("#c").text("编号必填");
        flag = false;
    } else {
        $("#c").text("正确");
        flag = true;
    }
    return flag;
}
//效验书名
function checkpublisher() {
    var publisher = $("#publisher").val();
    if (publisher == "") {
        $("#d").text("出版社必填");
        flag = false;
    } else {
        $("#d").text("正确");
        flag = true;
    }
    return flag;
}

function checkauthor() {
    var author = $("#author").val();
    if (author == "") {
        $("#e").text("作者必填");
        flag = false;
    } else {
        $("#e").text("正确");
        flag = true;
    }
    return flag;
}

function checkdetail() {
    var author = $("#detail").val();
    if (detail == "") {
        $("#f").text("备注必填");
        flag = false;
    } else {
        $("#f").text("正确");
        flag = true;
    }
    return flag;
}
//判断编号
function fanhuiBook() {
    window.location="/HRManagement/manage/book/book.html?fid="+fid;
}