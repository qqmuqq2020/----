var fid = 1;// 权限id
var startTime;// 开始时间
var endTime;// 结束时间
var error;// 没有数据时的错误
// 图形报表查询方法 start
function manage(PercentageCD, PercentageZT, PercentageZC) {
	var mas="提示";
	var info="没有找到该阶段的数据!";
	if(checkTime()){
		$.ajax({
			url : '/HRManagement/hrm/onclockrecords/getphoto',
			type : 'post',
			data : {
				sectionid : $("#sec").val(),
				startTime : $("#startTime").val(),
				endTime : $("#endTime").val(),
				fid : fid

			},
			dataType : 'JSON',
			success : function(data) {
				startTime = data.startTime;
				endTime = data.endTime;
				error = data.error;
				if (error>0) {
					show(mas,info);
				}
				// 接收后台各个类型的百分比
				PercentageZT = data.ztd;
				PercentageCD = data.cdd;
				PercentageZC = data.zcd;
				// 调用图形插件方法
				tuxing(PercentageZC, PercentageCD, PercentageZT);
			}
		});
	}
}
// 图形报表查询方法 start

// 图形显示方法 start
function tuxing(a, b, c) {
	var starttime = startTime;
	var endtime = endTime;
	var data = [ {
		name : '正常',
		value : a,
		color : '#4572a7'
	}, {
		name : '迟到',
		value : b,
		color : '#aa4643'
	}, {
		name : '早退',
		value : c,
		color : '#89a54e'
	} ];

	var chart = new iChart.Pie3D({
		render : 'canvasDiv',
		data : data,
		title : {
			text : '考勤状态百分比',
			height : 40,
			fontsize : 30,
			color : '#282828'
		},
		footnote : {
			text : '项目部签到系统',
			color : '#486c8f',
			fontsize : 12,
			padding : '0 38'
		},
		sub_option : {
			mini_label_threshold_angle : 40,// 迷你label的阀值,单位:角度
			mini_label : {// 迷你label配置项
				fontsize : 20,
				fontweight : 600,
				color : '#ffffff'
			},
			label : {
				background_color : null,
				sign : false,// 设置禁用label的小图标
				padding : '0 4',
				border : {
					enable : false,
					color : '#666666'
				},
				fontsize : 11,
				fontweight : 600,
				color : '#4572a7'
			},
			border : {
				width : 2,
				color : '#ffffff'
			},
			listeners : {
				parseText : function(d, t) {
					return d.get('value') + "%";// 自定义label文本
				}
			}
		},
		legend : {
			enable : true,
			padding : 0,
			offsetx : 120,
			offsety : 50,
			color : '#3e576f',
			fontsize : 20,// 文本大小
			sign_size : 20,// 小图标大小
			line_height : 28,// 设置行高
			sign_space : 10,// 小图标与文本间距
			border : false,
			align : 'left',
			background_color : null
		// 透明背景
		},
		animation : true,// 开启过渡动画
		animation_duration : 800,// 800ms完成动画
		shadow : true,
		shadow_blur : 6,
		shadow_color : '#aaaaaa',
		shadow_offsetx : 0,
		shadow_offsety : 0,
		background_color : '#f1f1f1',
		align : 'right',// 右对齐
		offsetx : -100,// 设置向x轴负方向偏移位置60px
		offset_angle : -90,// 逆时针偏移120度
		width : 1149,
		height : 400,
		radius : 150
	});
	// 利用自定义组件构造右侧说明文本
	chart.plugin(new iChart.Custom({
		drawFn : function() {
			// 在右侧的位置，渲染说明文字
			chart.target.textAlign('start').textBaseline('top').textFont(
					'600 25px Verdana').fillText(
					starttime + '至' + endtime + '出勤情况占比', 120, 80, false,
					'#be5985', false, 24).textFont('600 20px Verdana')
					.fillText('默认显示公司当月的出勤率百分比', 120, 160, false, '#999999');
		}
	}));

	chart.draw();
}
// 图形显示方法 end

// 页面一加载
$(function() {
	tuxing();// 饼状图
	manage();// 图像报表查询方法
});

// 获取部门数据 start
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
// 获取部门数据 end

// 检查时间是否正确 start
function checkTime() {
	var mas="时间选择错误";
	var info="月初时间不能大于月末时间!";
	var StatTime = $("#StatTime").val();
	var endTime = $("#endTime").val();
	if (StatTime > endTime) {
		show(mas,info);
		flag = false;
	} else {
		flag = true;
	}
	return flag;
}
// 检查时间是否正确 end
