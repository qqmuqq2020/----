var fid=4;
		$(document).ready(function() {
			$('.mormingtimetop').clockTimePicker({});
			$('.mormingtimebelow').clockTimePicker({});
			$('.afternoontimetop').clockTimePicker({});
			$('.afternoontimebelow').clockTimePicker({});
		});
		$(function(){
		select();
		});
		function select() {
			$.ajax({
				url : '/HRManagement/hrm/time/list',
				type : 'post',
				data : {fid:fid},
				dataType:'JSON',
				success : function(data) {
				$(data.time).each(function(i,item){
				 $(".mormingtimetop").val(item.ontime);
				 $(".mormingtimebelow").val(item.undertime);
				 $(".afternoontimetop").val(item.afternoontimetop);
				 $(".afternoontimebelow").val(item.afternoontimebelow);
				});
				}
			});
		}
		function update() {
			var onTime = $(".mormingtimetop").val();
			var underTime = $(".mormingtimebelow").val();
			var afternoontimetop = $(".afternoontimetop").val();
			var afternoontimebelow = $(".afternoontimebelow").val();
			var mas="提示";
			var info="时间设置成功！";
			$.ajax({
				url : '/HRManagement/hrm/time/update',
				type : 'post',
				data : {
					onTime : onTime,
					underTime : underTime,
					afternoontimetop : afternoontimetop,
					afternoontimebelow : afternoontimebelow,
					fid:fid
				},
				success : function(data) {
					if(data>0){
						show(mas,info);
					}
				}
			});
		}