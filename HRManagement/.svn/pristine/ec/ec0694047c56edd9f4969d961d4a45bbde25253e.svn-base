var fid = 2;// 权限id
var total;// 数据总数量
var pageindex;// 当前页码
var Sectionname;
var bomb_info;
// 页面一加载调用
$(function() {
	if (pageindex == null) {
		pageindex = 1;
	}
	PageClick(pageindex, total, 4);
});
// 模糊查询
function check() {
	Sectionname = $("#sectionname").val();
	PageClick(pageindex, total, 4, Sectionname);
}
// 部门分页 start
function PageClick(pageindex, total, spanInterval, Sectionname) {

	$
			.ajax({
				url : '/HRManagement/hrm/sections/getAll',
				type : 'post',
				data : {
					pageIndex : pageindex,
					sectionname : Sectionname,
					fid : fid
				},
				// dataType : 'JSON',
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
										str += "<tr id="
												+ tr
												+ "><td><span>"
												+ item.sectionid
												+ "</span></td><td><span>"
												+ item.sectionname
												+ "</span></td><td"
												+ "><span><a href='view.html?sectionid="
												+ item.sectionid
												+ "&sid="
												+ item.sid
												+ "&fid="
												+ fid
												+ "' class='viewBill'><img  src='../../images/read.png' alt='查看' title='查看'></a></span><span><a href='edit.html?sectionid="
												+ item.sectionid
												+ "&fid="
												+ fid
												+ "' class='modifyBill'><img src='../../images/xiugai.png' alt='修改' title='修改'/></a></span>"
												+ "<span><a onclick='delsection("
												+ item.sectionid
												+ ")' class='deleteBill'><img src='../../images/schu.png' alt='删除' title='删除'/></a></span></td>";

									});
					$("#table").append(str);// 拼接table表单
					var intPageIndex = parseInt(pageindex);
					GetData(intPageIndex, total, spanInterval, Sectionname);// 调用分页
				}
			});
};
// 部门分页 end

// 页面跳转
function toPage(inputPage) {
	pageindex = inputPage;
	PageClick(pageindex, total, 4, Sectionname);
}

// 部门删除
function delsection(sectionid) { // 删除 del(sectionid) 的sectionid 是
	// item.sectionid
			var mas="提示";
			var info="删除成功，已将该部门下所有员工转移！";
		if(confirm("确定删除该部门?")){
			$.ajax({
				url : '/HRManagement/hrm/sections/del',
				type : 'post',
				data : {
					id : sectionid,
					fid : fid
				// id 为 控制层的id sectionid是 del(sectionid)
				},
				success : function(data) {
					if (data > 0) {
						show(mas,info);
					}
						
				}
			});
		}

}