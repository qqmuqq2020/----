var fid = 3;
$(function() {
	selectbyid();// 页面加载就调用
});
function selectbyid() {
	var id = GetQueryString("id"); // 获取另一个网址用url传来的参数
	$.ajax({
		url : '/HRManagement/hrm/staffs/selectStaffs',
		type : 'post',
		data : {
			id : id,
			fid : fid,
			rid : fid
		},
		dataType : 'JSON',
		success : function(data) {
			var staffs = data.selectStaffs;
			for ( var i = 0; i < staffs.length; i++) {
				$(".zhaopian img").attr('src',
						"../../uploads/" + staffs[i].picture);
				$("#staffID").html(staffs[i].staffID);
				$("#staffName").html(staffs[i].staffName);
				$("#positionName").html(staffs[i].positionName);
				$("#birthday").html(staffs[i].birthday);
				$("#staffPassword").html(staffs[i].staffPassword);
				$("#nowAddress").html(staffs[i].nowAddress);
				$("#contactWay").html(staffs[i].contactWay);
				$("#sectionName").html(staffs[i].sectionName);
				$("#icID").html(staffs[i].icID);
				$("#idCardNo").html(staffs[i].idCardNo);
			}
		}
	});

}
function GetQueryString(name) // 此方法为获取另外一个页面url传来的参数 下面有两个网址可以查看了解
{ // http://www.jb51.net/article/48942.htm
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // http://blog.csdn.net/zxstone/article/details/12173015
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
