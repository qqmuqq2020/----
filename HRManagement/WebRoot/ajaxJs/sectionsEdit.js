function GetQueryString(name) // 此方法为获取另外一个页面url传来的参数 下面有两个网址可以查看了解
// Ctrl+左键单机网址
{ // http://www.jb51.net/article/48942.htm
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // http://blog.csdn.net/zxstone/article/details/12173015
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

var Id = GetQueryString("sectionid"); // 获取另一个网址用url传来的参数 sectionid
var fid = GetQueryString("fid");
$(function() {
	selectbyid();// 页面加载就调用
	getallsections();
	$("#sectionid").attr("disabled", true);
	$("#sid").attr("disabled", true);
});
var Sectionid;
var Sectionname;
// 修改section
function editsection() {
	var mas="提示";
	var info="修改成功！";
	$.ajax({
		url : '/HRManagement/hrm/sections/update',
		type : 'post',
		data : {
			sectionid : $("#sectionid").val(),
			sectionname : $("#sectionname").val(),
			fid : fid
		// id 为 控制层的id sectionid是 del(sectionid)
		},
		dataType : 'JSON',
		success : function(data) { // 注意到这一步有问题的话可以看看你的controller层
			// pWriter.print(js.toString());
			// 不是js.tojsonString
			if (data > 0) {
				show(mas,info);
			} else{
				$("#sectionname").val("");
				$("#sectionname").attr('placeholder', "请重新修改部门");
			} 
		}
	});
	// 返回到section 的 list.html

}

// 获取部门下拉数据
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

// 页面回显 根据id查询
function selectbyid() {
	$.ajax({
		url : '/HRManagement/hrm/sections/byid',
		type : 'post',
		data : {
			sectionid : Id,
			fid : fid
		// id 为 控制层的id sectionid是 del(sectionid)
		},
		dataType : 'JSON',
		success : function(data) { // 注意到这一步有问题的话可以看看你的controller层
			// pWriter.print(js.toString());
			// 不是js.tojsonString
			$(data.list).each(function(i, item) {
				// $("#id").val(item.nationID); //为id为这个的标签循环绑值
				$("#sectionid").val(item.sectionid);
				$("#sectionname").val(item.sectionname);
			});
		}
	});
}
// 返回
function fanhui() {
	window.location = "/HRManagement/manage/sections/list.html?fid=" + fid;
}
