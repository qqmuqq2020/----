//鏃堕棿
function fn() {
	var time = new Date();
	var str = "";
	var div = document.getElementById("time");
	// console.log(time);
	var year = time.getFullYear();
	var mon = time.getMonth() + 1;
	var day = time.getDate();
	var h = time.getHours();
	var m = time.getMinutes();
	var s = time.getSeconds();
	var week = time.getDay();
	switch (week) {
	case 0:
		week = "星期一";
		break;
	case 1:
		week = "星期二";
		break;
	case 2:
		week = "星期三";
		break;
	case 3:
		week = "星期四";
		break;
	case 4:
		week = "星期五";
		break;
	case 5:
		week = "星期六";
		break;
	case 6:
		week = "星期日";
		break;
	}
	str = year + "年" + mon + "月" + day + "月" + h + ":" + m + ":" + s + " "
			+ week;
	div.innerHTML = str;
}
fn();
setInterval(fn, 1000);
function totwo(n) {
	if (n <= 9) {
		return n = "0" + n;
	} else {
		return n = "" + n;
	}
}
