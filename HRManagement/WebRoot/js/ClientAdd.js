var fid = 1;

var ifn=false;



function backtolist() {
	location.href = "list.html?fid=" + fid;
}// 返回end


function checkName(){
	debugger;
	if($("#sname").val()==""||$("#sname").val()==undefined){
		$("#add").attr("disabled", true);
		$("#sname_err").text("不容许为空");
	}else{
		$("#add").attr("disabled", false);
		$("#sname_err").html("");
		$.ajax({
			url:'/HRManagement/hrm/cwg_client/ifname',
			type:'get',
			data:{userName:$("#sname").val(),fid:fid},
			success:function(data){
				if(data!=0){
					$("#add").attr("disabled", true);
					$("#sname_err").text("客户名重复");
				}else{
					$("#add").attr("disabled", false);
					$("#sname_err").html("");
					ifn =true;
				}
			}
		})
	}
	
}


function save(){
	debugger;
//	alert($("#Max1").val())
	var mas = "提示";
	var info = "修改成功！";
	if(ifn==true){
		$.ajax({
			url:'/HRManagement/hrm/cwg_client/addClient',
			type:'post',
			data:{userName:$("#sname").val(),detail:$("#detail").val(),max:$("#Max1").val(),fid:fid},
			success : function(data){
				debugger;
				if (data > 0) {
					alert("添加成功");
					window.history.back(-1);
				} else {
					alert("超出管理员设置，添加失败！！");
				}
			}
		})
	}else{
		alert("请输入信息")
	}
	
}