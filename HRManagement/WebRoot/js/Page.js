function GetData(intPageIndex, total, pageSize, title,PositionID,SectionID) {
	var pageS = total;
	if (pageS % pageSize == 0)
		pageS = pageS / pageSize;
	else
		pageS = parseInt(total / pageSize) + 1;
	var $pager = $("#pager");
	// 清楚分页div中的内容
	$("#pager span").remove();

	$("#pager label").remove();
	$("#pager a").remove();
	// 添加第一页
	if (intPageIndex == 1)
		$pager.append("<span class='disabled'>首页</span>");
	else {
		var first = $("<a href='javascript:void(0)' first='" + 1 + "'>首页</a>")
				.click(function() {
					PageClick($(this).attr('first'), total, pageSize, title,PositionID,SectionID);
					return false;
				});
		$pager.append(first);
	}
	// 添加上一页
	if (intPageIndex == 1)
		$pager.append("<span class='disabled'>上一页</span>");
	else {
		var pre = $(
				"<a href='javascript:void(0)' pre='" + (intPageIndex - 1)
						+ "'>上一页</a>").click(function() {
			PageClick($(this).attr('pre'), total, pageSize, title,PositionID,SectionID);
			return false;
		});
		$pager.append(pre);
	}
	// 设置分页的格式 这里可以根据需求完成自己想要的结果
	var interval = parseInt(pageSize); // 设置间隔
	var start = Math.max(1, intPageIndex - interval); // 设置起始页
	var end = Math.min(intPageIndex + interval, pageS);// 设置末页
	if (intPageIndex < interval + 1) {
		end = (2 * interval + 1) > pageS ? pageS : (2 * interval + 1);
	}
	if ((intPageIndex + interval) > pageS) {
		start = (pageS - 2 * interval) < 1 ? 1 : (pageS - 2 * interval);
	}
	// 生成页码
	for ( var j = start; j < end + 1; j++) {
		if (j == intPageIndex) {
			var spanSelectd = $("<label style='color: #000;'>" + j + "</label>");
			$pager.append(spanSelectd);
		} // if
		else {
			var a = $("<a href='javascript:void(0)'>" + j + "</a>").click(
					function() {
						PageClick($(this).text(), total, pageSize, title,PositionID,SectionID);
						return false;
					});
			$pager.append(a);
		} // else
	} // for
	// 上一页
	if (intPageIndex == pageS) {
		$pager.append("<span class='disabled'>下一页</span>");
	} else {
		var next = $(
				"<a href='javascript:void(0)'next='" + (intPageIndex + 1)
						+ "'>下一页</a>").click(function() {
			PageClick($(this).attr("next"), total, pageSize, title,PositionID,SectionID);
			return false;
		});
		$pager.append(next);
	}
	// 最后一页
	if (intPageIndex == pageS) {
		$pager.append("<span class='disabled'>尾页</span>");
	} else {
		var last = $("<a href='javascript:void(0)' last='" + pageS + "'>尾页</a>")
				.click(function() {
					PageClick($(this).attr("last"), total, pageSize, title,PositionID,SectionID);
					return false;
				});
		$pager.append(last);
	}
}