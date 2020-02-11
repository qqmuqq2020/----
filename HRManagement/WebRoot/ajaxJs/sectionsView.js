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
var Sid = GetQueryString("sid"); // sid
var fid = GetQueryString("fid"); // fid
$(function() {
	selectbyid();// 页面加载就调用
});

// 页面回显 显示部门信息
function selectbyid() {
	$.ajax({
		url : '/HRManagement/hrm/sections/byid',
		type : 'post',
		data : {
			sectionid : Id,
			sid : Sid,
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
				$("#sid").val(data.Sname);
			});
		}
	});
}

// 返回事件
function back() {
	window.location.href = "/HRManagement/manage/sections/list.html?fid=" + fid;
}