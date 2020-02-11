var fid = 1;
// 动态获取部门下拉框
var total;// 接受数量
var pageindex;// 页码
var	startTime;
var	endTime;
var	sectionid;
var	staffName;
// 页面加载方法
$(function() {
	if (pageindex == null) {
		pageindex = 1;
	}
	PageClick(pageindex, total, 15);
});
// 员工表格模糊分页查询方法 start
function PageClick(pageindex, total, spanInterval) {
	$.ajax({
		url : '/HRManagement/hrm/onclockrecords/findtable',// 跳转后台
		type : 'post',// 跳转类型
		data : {
			pageIndex : pageindex,
			staffname : $("#staffname").val(),
			sectionid : $("#sec").val(),
			startTime : $("#startTime").val(),
			endTime : $("#endTime").val(),
			fid : fid
		},
		// dataType:'JSON',
		success : function(data) {
			var str = "";// 定义空字符串拼接tr
			$("#table #tr").remove();// 删除时清楚空的tr
			var tr = "tr";
			var str;
			var json = eval("(" + data + ")");// 强转为json数组
			total = json.total;// 获取数据的条数
			$(json.list).each(
					function(i, item) {
						var colcktype = "";
						if (item.colcktype == 1) {
							colcktype = "正常";
						} else if (item.colcktype == 2) {
							colcktype = "迟到";
						} else {
							colcktype = "早退";
						}
						str += "<tr id=" + tr + "><td><span>" + item.staffid
								+ "</span></td><td><span>" + item.staffname
								+ "</span></td><td><span>" + item.sectionname
								+ "</span></td><td><span>" + item.colcktime
								+ "</span></td><td><span>" + colcktype
								+ "</span></td></tr>";
					});
			$("#table").append(str);// 拼接table表单
			var intPageIndex = parseInt(pageindex);
			GetData(intPageIndex, total, spanInterval);// 调用分页
		}
	});
}
// 员工表格模糊分页查询方法 end

function toPage(inputPage) {
	pageindex = inputPage;
	PageClick(pageindex, total, 15);
}
// 搜索方法
function sousuo() {
	PageClick(pageindex, total, 15);
}
// 树状图ajax方法 start
$.ajax({
	url : '/HRManagement/hrm/sections/findSections',
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
// 树状图ajax方法 end

// 导出报表方法 start

function excel() {
	PageClick(pageindex, total, 15);
	startTime = $("#startTime").val();
	endTime = $("#endTime").val();
	sectionid = $("#sec").val();
	staffName = $("#staffname").val();
	window.open("/HRManagement/hrm/excel/excel.do?startTime="+startTime+"&endTime="+endTime+"&sectionid="+sectionid+"&staffName"+staffName+"&fid="+fid);
	var mas="提示";
	var info="导出报表成功,请在本地磁盘查看";
	show(mas,info);
}
function notnull(){
	var mas="提示";
	var info="请选择查询条件";
	startTime = $("#startTime").val();
	endTime = $("#endTime").val();
	sectionid = $("#sec").val();
	if(startTime=="本月月初"||endTime=="本月月末"||sectionid==0){
		show(mas,info);
	}else{
		PageClick(pageindex, total, 15);
		excel();
	}
}


