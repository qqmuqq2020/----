var fid = 3;

	

	
	
	$("#uploadify").uploadify(
			{
				'uploader' : '../../servlet/Upload',
				'swf' : '../../js/uploadify.swf',
				'cancelImg' : '../../images/uploadify-cancel.png',
				'folder' : 'uploads',// 您想将文件保存到的路径
				'queueID' : 'fileQueue',// 与下面的id对应
				'queueSizeLimit' : 5,
				'fileDesc' : '*.png;*.jpg',
				'fileExt' : '*.png;*.jpg', // 控制可上传文件的扩展名，启用本项时需同时声明fileDesc
				'auto' : true, // 自动上传
				'multi' : true,
				'simUploadLimit' : 2,
				'buttonText' : '选择图片',
				'onUploadError' : function(file, errorCode, errorMsg,
						errorString, swfuploadifyQueue) {// 上传文件出错是触发（每个出错文件触发一次）
					$("#photo_err").text(errorMsg);
				},

				'onUploadSuccess' : function(file, data, response) {// 上传完成时触发（每个文件触发一次）
					if (response) {
						var json = eval("(" + data + ")");
						$("#photo img").attr('src',
								"../../uploads/" + json.fileName);
						$("#fileName").val(json.fileName);
					} else {
						$("#photo_err").text("文件上传有误，请查看上传文件类型与大小是否适合");
					}
				}
			});
	var str = "";// 拼接字符串变量
	var flag = true;
	// 获取职位数据
	$.ajax({
		url : '/HRManagement/hrm/positions/findPositions',// 请求
		type : 'post',
		data : {
			fid : fid
		},// 传权限
		success : function(data) {
			var json = eval("(" + data + ")");// json转
			$(json.list).each(
					function(i, item) {// 循环拼接字符串
						str = "<option value='" + item.positionid + "'>"
								+ item.positionname + "</option>";
						$("#pos").append(str);
					});

		}
	});
	// 获取部门数据
	$.ajax({
		url : '/HRManagement/hrm/sections/findSections',// 请求
		type : 'post',
		data : {
			fid : fid
		},
		success : function(data) {
			var json = eval("(" + data + ")");
			$(json.list).each(
					function(i, item) {
						str = "<option value='" + item.sectionid + "'>"
								+ item.sectionname + "</option>";
						$("#sec").append(str);
					});

		}
	});

	
	// 获取功能数据
//	$
//			.ajax({
//				url : '/HRManagement/hrm/functions/findFunctions',
//				type : 'post',
//				data : {
//					fid : fid
//				},
//				success : function(data) {
//					var json = eval("(" + data + ")");
//					$(json.list)
//							.each(
//									function(i, item) {
//										str = "<input style='width:15px;height:15px' class='qx' type='checkbox' name='functionid' value='"
//												+ item.functionid
//												+ "'/>"
//												+ item.functionname + " ";
//										$("#fun").append(str);
//									});
//
//				}
//			});



// 添加人员start
function register() {
	if (checkAll()) {
//		if (true) {
		var fileName = $("#fileName").val();// 图片
		var staffname = $("#sname").val();// 姓名
		var pos = $("#pos").val();// 职位
		var sec = $("#sec").val();// 部门
		var pwd = $("[name='staffpassword']").val();// 密码
		var contactway = $("[name='contactway']").val();// 联系方式
		var idcardno = $("[name='idcardno']").val();// 身份证
		var birthday = $("[name='birthday']").val();// 出生日期
		var nowaddress = $("[name='nowaddress']").val();// 住址
		var ready2 =  $("#read2").val();
		var checkIds = "";
//		$("[name='functionid']:checked").each(function() { // name 是 functionid
//			// 的多选框(已选状态)
//			checkIds += $(this).val() + ",";// 循环绑定并且加 "," 变成string 类型传回后台
//		});
//		checkIds = checkIds.substring(0, checkIds.length - 1);// 去掉最后一个逗号
		switch(pos){
		case "1":
			checkIds = "4,5,7,12,13";
			break;
		case "2":
			checkIds = "1,3,6,4,7,11,12,13";
			break;
		case "3":
			checkIds = "1,6,4,7,12,13";
			break;
		case "0":
			checkIds = "1,2,3,4,5,6,7,8,9,11,12,13";
			break;
		}
			
		
		var mas = "提示";
		var info = "修改成功！";
		$.ajax({
			url : '/HRManagement/hrm/staff/addStaff',
			type : 'POST',
			data : {
				"staffname" : staffname,
				"positionid" : pos,
				"sectionid" : sec,
				"staffpassword" : pwd,
				"contactway" : contactway,
				"idcardno" : idcardno,
				"birthday" : birthday,
				"nowaddress" : nowaddress,
				"functionid" : checkIds,
				"fileName" : fileName,
				"ready2":ready2,
				fid : 9
			},
			success : function(data) {
				debugger;
				if (data > 0) {
					alert("添加成功");
					window.history.back(-1);
				} else {
					alert( "添加失败");
				}

			}
		});

	}
}
// 添加人员 end

// 返回start
function backtolist() {
	location.href = "list.html?fid=" + fid;
}// 返回end

// 验证姓名必填
function checkName() {

	var name = $("#sname").val();
	if (name == "") {
		$("#name_err").text("请填写员工姓名");
		flag = false;
	} else {
		$("#name_err").text("");
		flag = true;
	}
	return flag;
}

// 验证职位必选
function checkPos() {
	var pos = $("#pos").val();
	if (pos == "") {
		$("#position_err").text("请选择职位");
		flag = false;
	} else {
		$("#position_err").text("");
		flag = true;
	}

	return flag;
}
// 验证部门必选
function checkSec() {
	var sec = $("#sec").val();
	if (sec == "") {
		$("#section_err").text("请选择部门");
		flag = false;
	} else {
		$("#section_err").text("");
		flag = true;
	}

	return flag;
}
// 验证密码非空
function checkPwd() {
	var pwd = $("[name='staffpassword']").val();
	if (pwd == "") {
		$("#password_err").text("请填写密码");
		flag = false;
	} else {
		$("#password_err").text("");
		flag = true;
	}
	return flag;

}
// 验证手机号的有效性
//function checkContactway() {
//	var contactway = $("[name='contactway']").val();
//	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
//	if (contactway == "") {
//		$("#contactway_err").text("请填写联系方式");
//		flag = false;
//	} else if (!myreg.test(contactway)) {
//		$("#contactway_err").text("请填写正确的联系方式");
//		flag = false;
//	} else {
//		ifPhone();
//		flag = true;
//	}
//	return flag;
//
//}
// 验证身份证号码的有效性
//function checkIdcardno() {
//	var idcardno = $("[name='idcardno']").val();
//	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
//	if (idcardno == "") {
//		$("#idcardno_err").text("请填写身份证号码");
//		flag = false;
//	} else if (!reg.test(idcardno)) {
//		$("#idcardno_err").text("请填写正确的身份证号码");
//		flag = false;
//	} else {
//		ifCard();
//		flag = true;
//	}
//	return flag;
//}
// 验证所有数据的有效性
function checkAll() {
//	if (!checkIdcardno()) {
//		flag = false;
//	}
//	if (!checkContactway()) {
//		flag = false;
//	}
	if (!checkPwd()) {
		flag = false;
	}
	if (!checkSec()) {
		flag = false;
	}
	if (!checkPos()) {
		flag = false;
	}
	if (!checkName()) {
		flag = false;
	}
	if(!ifready2()){
		flag = false;
	}
	return flag;
}
// 身份证重复判断 start
//function ifCard() {
//	$.ajax({
//		url : '/HRManagement/hrm/staff/ifCard',
//		type : 'post',
//		data : {
//			idcardno : $("[name='idcardno']").val(),
//			fid : fid
//		},
//		success : function(data) {
//			var json = eval("(" + data + ")");
//
//			if (parseInt(json.card) == 0) {
//				$("#idcardno_err").text("验证通过");
//			} else {
//				$("#idcardno_err").text("此身份证已被注册过");
//			}
//
//		}
//	});
//
//}
// 身份证重复判断 end
// 电话号重复判断 start
//function ifPhone() {
//	$.ajax({
//		url : '/HRManagement/hrm/staff/ifPhone',
//		type : 'post',
//		data : {
//			contactway : $("[name='contactway']").val(),
//			fid : fid
//		},
//		success : function(data) {
//			var json = eval("(" + data + ")");
//			if (parseInt(json.phone) == 0) {
//				$("#contactway_err").text("验证通过");
//			} else {
//				$("#contactway_err").text("此电话号已被注册");
//			}
//
//		}
//	});
//
//	
//}
//选择配管商人员 查询
function ready(){
	var ry = $("#pos").val();
	if(ry==3){
		$.ajax({
			url :'/HRManagement//hrm/staff/getStaffsByid',// 请求
			type:'post',
			data:{sectionid:2,fid:fid},
			success:function(data){
				$("#dread2").removeAttr("hidden");
				$("#read2").html("");
				var s = "<option value=''>请选择</option>";
				$("#read2").append(s);
				$(data).each(function(i,obj){
					var str = "<option value='" + obj.staffID + "'>"
					+ obj.staffName + "</option>";
					$("#read2").append(str);
				})
			}
		})
		
	}else{
		$("#dread2").attr("hidden","");
		$("#read2").html("");
	}
	
}
function ifready2() {
	var r = $('#read2').val();
	if (r == "") {
		$("#ready2_err").text("请填人名");
		flag = false;
	} else {
		$("#ready2_err").text("");
		flag = true;
	}
	return flag;

}
// 电话号重复判断 end
