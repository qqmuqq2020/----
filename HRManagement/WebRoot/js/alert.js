// JavaScript Document
$(function() {
	$("#alert").hide();
	$("#a").hide();
});

function show(mas, info) {
	$("#alert").show();
	$("#a").show();
	$("#mas").html(mas);
	$("#info").html(info);
};

function hide() {
	debugger;
	$("#alert").hide();
	$("#a").hide();
	window.history.back(-1);
};
function hide2() {
	$("#alert").hide();
	$("#a").hide();
	manage(PercentageCD, PercentageZT, PercentageZC);
};
//报表导出时使用
function hide3() {
	$("#alert").hide();
	$("#a").hide();
};
function hide4() { // 删除人员调用
	$("#alert").hide();
	$("#a").hide();
	if (pageindex == null) {// 判断页码是否为空
		pageindex = 1;// 如果为空让它为1
	}
	PageClick(pageindex, total, 6);
};
function hide5() { // 删除部门调用
	$("#alert").hide();
	$("#a").hide();
	if (pageindex == null) {// 判断页码是否为空
		pageindex = 1;// 如果为空让它为1
	}
	PageClick(pageindex, total, 4);
};