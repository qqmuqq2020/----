var fid = 6;// 权限id
$(function() {
	getCocoList();
	getBookList();
});

//获取客户list
function getCocoList() {
		$.ajax({
		url:'/HRManagement/hrm/test/getCocoList?fid='+fid,
		type:'get',
		data:{},
		success:function (data) {
	
			$(data).each(function(i,item) {
				var id = item.id;
				var name = item.userName;
				$("#R3").append("<input class='Cimf' name='B' type='checkbox' value='" + id + "-" + name + "'/>" + name + "</br>");
			});
		}
	})
};

//全选按钮
function sall()
{
	if($("#all").prop("checked"))
    {
		$("input[name='C']").each(function(i,j){
			$(j).prop("checked",true);
			
		})
    }
	else
    {
		$("input[name='C']").each(function(i,j){
			$(j).prop("checked",false);
			
		})
    }
}

//加载获取书名
function getBookList() {
		$('#L3').empty();
		$.ajax({
			url:'/HRManagement/hrm/test/getAllBookList?fid='+fid,
			type:'get',
			data:{},
			success:function (data){
				
				
				$('#L3').append("<input type='checkbox' id='all' onclick='sall();'>全选/反选<br/>")
				$(data).each(function(i,item) {
					var id = item.id;
					var name = item.name;
					$("#L3").append("<input class='Bimf'  name='C' type='checkbox' value='" + id + "-" + name + "'/>" + name + "</br>");
				});
			}
		})
}

//搜索获取书名
function slectBook() {
	var bookName=$('#BookName').val();
	if(bookName.length==0){
		alert("请输入要查询图书名字！")
	}else {
		$('#L3').empty();
		$.ajax({
			url:'/HRManagement/hrm/test/getBookList?fid='+fid,
			type:'get',
			data:{'bookName':bookName},
			success:function (data){
				$(data).each(function(i,item) {
					var id = item.id;
					var name = item.name;
					$("#L3").append("<input class='Bimf'  name='C' type='checkbox' value='" + id + "-" + name + "'/>" + name + "</br>");
				});
			}
		})
	}
}

//生成预览
	//共有的数组存储表中的数据
var barray=[];
function makepreview() {
	var Bimfs=$(".Bimf:checked")
	var Cimfs=$(".Cimf:checked")
	//选中图书信息
	var array=new Array();
	for (var i = 1; i <=Bimfs.length ; i++) {
		var Bid=Bimfs.eq(i-1).val().split("-")[0]
		var Bname=Bimfs.eq(i-1).val().split("-")[1]
		for (var j = 1; j <=Cimfs.length ; j++) {
			var Cid=Cimfs.eq(j-1).val().split("-")[0]
			var Cname=Cimfs.eq(j-1).val().split("-")[1]
			array.push(Bid+"-"+Bname+"-"+Cid+"-"+Cname)
		}
	}
	if (Bimfs.length==0||Cimfs.length==0){
		alert("请选择客户或者图书！！")
	}else {
		$.ajax({
			url:'/HRManagement/hrm/test/ifHave?fid='+fid,
			type:'get',
			data:{array:array.toString()},
			success:function (data){
				if(data!=null){
					confirm(data);
				}else {
					if (barray.length==0) {
						for (var c = 1; c <=array.length ; c++) {
							barray.push(array[c-1])
						}
					}else {
						for (var c = 1; c <=array.length ; c++) {
							for (var s = 1; s <=barray.length ; s++) {
								if (array[c - 1] == barray[s - 1]) {
									alert(array[c - 1] + "的订单存在，请重新选择！")
									return;
								}
							}
							barray.push(array[c - 1])
						}
					}
					var bookName=$('#BookName').val();
					$("#beifen").html("");
					for (var j = 1; j <=barray.length ; j++) {
						var imf= barray[j-1].split("-");
						var st="<tr id='"+imf[0]+"-"+imf[2]+"'><td>"+imf[0]+"-"+imf[2]+"</td><td id='"+imf[3]+"'>"+imf[3]+"</td><td id='"+imf[1]+"'>"+imf[1]+"</td><td onclick='removetr(this)'>删除</td></tr>"
						$("#beifen").append(st);
					}
				}
			}
		})
		var bookName=$('#BookName').val("");
		getBookList();
		$("#L3").html("")
		$(".Cimf").attr("checked",false);
	}
}

//删除预览
function removetr(obj) {
	if(confirm("确认删除？")){
	var id= $(obj).parent().attr("id");
	$(obj).parent().remove();
	var a=id.split("-")
	var Bv=$(obj).prev().attr("id");
	var Cv=$(obj).prev().prev().attr("id");
	var s=a[0]+"-"+Bv+"-"+a[1]+"-"+Cv
	for (var j = 1; j <=barray.length ; j++){
		if (s==barray[j-1]) {
			barray.splice(j-1,1)
		}
	}
	}
}
//添加备份
function addrecord() {
	$.ajax({
		url:'/HRManagement/hrm/test/addRecord?fid='+fid,
		type:'get',
		data:{barray:barray.toString(),max:100},
		success:function (data){
			if(data=="max")
			{
				alert("超出管理员限制，添加失败！")
			}
			else
			{
				alert("添加成功！");
				window.location.href="/HRManagement/manage/onRecord/onRecordlist.html?fid="+fid;
				
			}
			
		}
	})
	$("#bbb").attr('disabled', true);
}




