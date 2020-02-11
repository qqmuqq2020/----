
		function loadDiv() {
			var str=""+
				"<div class='loadDiv'>" +
				"<div class='load-container load4'>" +
				"<div class='loader'>Loading.....</div>" +
				"<div class='title'>正在加载.</div>" +
				"</div>" +
				"</div>";
				$("body").append(str);
			var height = $(window).height();
			$(".loadDiv").css('height', height + "px");
			$(window).bind("resize", function() {
				var height = $(window).height();
				$(".loadDiv").css('height', height + "px");
			});
		}
		
		function loadExit(){
			$(".loadDiv").remove();
		}