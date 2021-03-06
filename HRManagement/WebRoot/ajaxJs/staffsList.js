var fid = 3;
var str;// 拼接字符串
var total;// 分页用的总数 查询出数量
var pageindex;// 分页用的页码 获取当前页
var Staffname;// 员工姓名
var PositionID;// 所属职位
var SectionID;// 所属部门
// load start
$(function() {// 页面一加载就执行里面的方法
	if (pageindex == null) {// 判断页码是否为空
		pageindex = 1;// 如果为空让它为1
	}
	PageClick(pageindex, total, 30);
//		debugger;
});
// load end

// 查询按钮 start
function checkfind() {
	Staffname = $("#StaffName").val();
	PositionID = $("#pos").val();
	SectionID = $("#sec").val();
	pageindex=1;
	PageClick(pageindex, total, 30, Staffname, PositionID, SectionID);
}
// 查询按钮 end

// 基于分页的多条件查询 start
function PageClick(pageindex, total, spanInterval, Staffname, PositionID,
		SectionID) {// 传值 当前页 总数 每页显示的数量
	$.ajax({
				url : '/HRManagement/hrm/staffs/getpage',// 到后台controller
				type : 'post',
				data : {
					pageIndex : pageindex,
					staffname : Staffname,
					positionID : PositionID,
					sectionID : SectionID,
					fid : fid
				},
				success : function(data) {
					var str = "";
					$("#table #tr").remove();// 清除要拼接的tr
					var tr = "tr";
					var str;
					var json = eval("(" + data + ")");
					total = json.total;
					$(json.list)
							.each(
									function(i, item) {
										if (item.icID == null
												|| item.icID == "") {
											
											 if(item.ready2==undefined||null){
												 item.ready2="无";
												}
											
											str += "<tr id="
													+ tr
													+ "><td><span>"
													+ item.staffID
													+ "</span></td><td><span>"
													+ item.staffName
													+ "</span></td><td><span>"
													+ item.positionName
													+ "</span></td><td><span>"
													+item.ready2
													+ "</span></td><td name='delc' "
													+ "><span><a href='view.html?id="
													+ item.id
													+ "&fid="
													+ fid
													+ "' class='viewBill'><img  src='../../images/read.png' alt='查看' title='查看'></a></span><span><a href='edit.html?id="
													+ item.id
													+ "&fid="
													+ fid
													+ "' class='modifyBill'><img src='../../images/xiugai.png' alt='修改' onclick='edit("
													+ item.staffID
													+ ")' title='修改'/></a></span>"
													+ "<span><a onclick='delsection("
													+ item.id
													+ ")' class='deleteBill'><img src='../../images/schu.png' alt='删除' title='删除'/></a></span></td>";
										} else {
											str += "<tr id="
													+ tr
													+ "><td><span>"
													+ item.staffID
													+ "</span></td><td><span>"
													+ item.staffName
													+ "</span></td><td><span>"
													+ item.positionName
													+ "</span></td><td><span>"
													+ item.ready2
													+ "</span></td><td name='delc'  "
													+ "><span><a href='view.html?id="
													+ item.id
													+ "&fid="
													+ fid
													+ "' class='viewBill'><img  src='../../images/read.png' alt='查看' title='查看'></a></span><span><a href='edit.html?id="
													+ item.id
													+ "&fid="
													+ fid
													+ "' class='modifyBill'><img src='../../images/xiugai.png' alt='修改' onclick='edit("
													+ item.staffID
													+ ")' title='修改'/></a></span>"
													+ "<span><a onclick='delsection("
													+ item.id
													+ ")'class='deleteBill'><img src='../../images/schu.png' alt='删除' title='删除'/></a></span></td>";

										}
									});
					$("#table").append(str);// 拼接table表单
					var intPageIndex = parseInt(pageindex);// 当前页
					PositionID = $("#pos").val();
					SectionID = $("#sec").val();
					GetData(intPageIndex, total, spanInterval, Staffname,
							PositionID, SectionID);// 上一页下一页
					if($.cookie('keypid')==0){
						$("td[name='delc']").removeAttr('display');
						$("th[name='delc']").removeAttr('display');
						$("a[name='delc']").removeAttr('display');
					}else{
						$("td[name='delc']").css('display','none');
						$("th[name='delc']").css('display','none');
						$("a[name='delc']").css('display','none');
						$("#aaa").attr("style","display:none;");
					}
					
					
				}
			});

};
// 跳转页面 start
function toPage(inputPage) {
	pageindex = inputPage;
	PageClick(pageindex, total, 6, Staffname, PositionID, SectionID);
}
// 跳转页面 end

// 获取部门 start
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
// 获取部门 end

// 获取职位 start
$.ajax({
	url : '/HRManagement/hrm/positions/findPositions',
	type : 'post',
	data : {
		fid : fid
	},
	success : function(data) {

		var json = eval("(" + data + ")");
		$(json.list).each(
				function(i, item) {
					str = "<option value='" + item.positionid + "'>"
							+ item.positionname + "</option>";
					$("#pos").append(str);
				});

	}
});
// 获取职位 end

// 修改按钮方法 start
function edit(id) {
	window.location = "edit.html?id=" + id + "&fid=" + fid;
}
// 修改按钮方法 end

// 人员删除按钮 start
function delsection(id) {
	var mas = "提示";
	var info = "删除成功！";
	if(confirm("确定删除该人员?")){
		$.ajax({
			url : '/HRManagement/hrm/staffs/delstaffs',
			type : 'post',
			data : {
				id : id,
				fid : 9
			// id 为 控制层的id sectionid是 del(sectionid)
			},
			success : function(data) {
				if (data > 0) {
					alert("删除成功");
					location.reload();
				}else{
					alert("删除失败");
				}
			}
		});
	}
}
// 人员删除按钮 end

// 发卡 start
//function giveIC(id) {
//	$.ajax({
//		url : '/HRManagement/hrm/staffs/giveIC',
//		type : 'post',
//		data : {
//			id : id,
//			fid : fid
//		},
//		success : function(data) {
//			if (data > 0) {
//				show("提示", "发卡成功!");
//			} else {
//				show("提示", "发卡失败!");
//			}
//
//		}
//	});
//}
// 发卡 end
