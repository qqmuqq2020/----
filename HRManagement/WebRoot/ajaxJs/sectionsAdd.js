var fid = 2;// 权限id
$(function() {
	getallsections();
	$("#secname").attr("disabled", true);
});
var sectionname;
// 添加部门事件
function addsections() {
		var mas="提示";
		var info="添加成功！";
		sectionname=$("#sectionname").val();
		if (sectionname != "") {
			$.ajax({
				url : '/HRManagement/hrm/sections/add', // 请求地址
				type : 'post', // 请求方式
				data : {
					sectionname : $("#sectionname").val(),
					fid : fid
				}, // 传参
				success : function(data) { // 得参
					if (data > 0) {
						alert("添加成功");
						window.history.back(-1);
					} else {
						$("#sectionname").val("");
						$("#sectionname").attr('placeholder', "该部门已存在,请重新修改");
					}
				}
			});
		} else {
			$("#sectionname").attr('placeholder', "请编写部门名称");
		}
}

// 获取部门树状图
function getallsections() {
	var str = "";
	$
			.ajax({
				url : '/HRManagement/hrm/sections/findSections',
				type : 'post',
				data : {
					fid : fid
				},
				success : function(data) {
					var json = eval("(" + data + ")");
					$(json.list)
							.each(
									function(i, item) {
										str = "<li onclick='sec("
												+ item.sectionid
												+ ")' id='li'><label for='subfolder6' id='"
												+ item.sectionid
												+ "'><img src='../../images/home.png'  style='height:25px;width:25px;'/>"
												+ item.sectionname
												+ "</label></li>";
										$("#sec").append(str);
									});

				}
			});
}
// 返回事件
function fanhui() {
	window.location = "/HRManagement/manage/sections/list.html?fid=" + fid;
}