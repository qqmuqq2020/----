var fid = 5;
var str;// 拼接字符串
var total;// 分页用的总数 查询出数量
var pageindex;// 分页用的页码 获取当前页
var Staffname;// 员工姓名
var PositionID;// 所属职位
var SectionID;// 所属部门
var like;

// pageMe.js 使用方法
function out(){
		if(($("#lk").val()==null||$("#lk").val()=="")&&($("#clike").val()==null||$("#clike").val()=="")){
			alert("导出必须有一个条件")
		}else{
		var url2 = "/HRManagement/hrm/financial/outEX?like="+$("#lk").val()+"&&clike="+$("#clike").val()+"&&fid="+fid;
		//window.location = url2;
		var url = url2;
		var xhr = new XMLHttpRequest();
		loadDiv();
		xhr.open('GET', url, true); // Ҳ    ʹ  POST  ʽ    ݽӿ 
		xhr.responseType = "blob"; //         blob
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 3) {

			}
			if(xhr.readyState == 4) {

			}
		};
		
		xhr.onload = function() {

			//        
			if(this.status === 200) {
				loadExit();

				//     200
				var blob = this.response;
				// ת    ɣ     һ  a  ǩ        
				var a = document.createElement('a');
				a.download = 'FinancialInfo.xls';
				a.href = window.URL.createObjectURL(blob);
				$("body").append(a); //  ޸ firefox   ޷     click
				a.click();
				$(a).remove();

			}
		};
		//     ajax    
		xhr.send()
		}
	}

	


var app = angular.module('myApp', []);
var index=1;

	
	app.controller('siteCtrl', function($scope, $http) {
		
		
		$scope.complete=function(id){
			if(confirm("确认成交吗？")){
				$.ajax({
					url:'/HRManagement/hrm/financial/Complete',
					type:'post',
					data:{id:id,fid:fid},
					success:function(data){
						if (data > 0) {
							alert("操作成功")
						} else {
							alert("操作失败:"+data)
						}
						  $http({
						        method: 'post',
						        url: '/HRManagement/hrm/financial/financialList',
						        data: $.param({index:index,like:$("#lk").val(),clike:$("#clike").val(),fid:fid}),
						        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
						    }).then(function successCallback(response) {
						            $scope.names = response.data.getlist;
						            // 加载list页面
								  $scope.Page(response.data);
						            
						        }, function errorCallback(response) {
						            // 请求失败执行代码
						    });
						
					}
					
				})
				
				
			}
			
		}
		
		
		$scope.likk = function(){
			index =1;
			 $http({
			        method: 'post',
			        url: '/HRManagement/hrm/financial/financialList',
			        data: $.param({index:index,like:$("#lk").val(),clike:$("#clike").val(),fid:fid}),
			        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			    }).then(function successCallback(response) {
			            $scope.names = response.data.getlist;
			            // 加载list页面
					  $scope.Page(response.data);
			            
			        }, function errorCallback(response) {
			            // 请求失败执行代码
			    });
		}
		
	/*	$scope.add=function (obj){
			alert(obj)
		}*/
		$scope.Page=function(obj){
			 $("#page").paging({
			        pageNum: index, // 当前页面
			        totalNum: obj.pageCount, // 总页码
			        totalList: obj.count,
			        like:obj.like,
			        clike:obj.clike,// 记录总数量
			        callback: function (num) { //回调函数
			          index=num
			          $http({
							        method: 'post',
							        url: '/HRManagement/hrm/financial/financialList',
							        data: $.param({index:index,like:$("#lk").val(),clike:$("#clike").val(),fid:fid}),
							        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			          		}).then(function successCallback(response) {
							            $scope.names = response.data.getlist;
							            // 加载list页面
									  $scope.Page(response.data);
							        }, function errorCallback(response) {
							            // 请求失败执行代码
							  });
			            }
	            })
		
		}
		
	    $http({
	        method: 'post',
	        url: '/HRManagement/hrm/financial/financialList',
	        data: $.param({index:index,like:$("#lk").val(),clike:$("#clike").val(),fid:fid}),
	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	    }).then(function successCallback(response) {
	            $scope.names = response.data.getlist;
	            // 加载list页面
	            $scope.like=response.data.getlike;
			  $scope.Page(response.data);
	            
	        }, function errorCallback(response) {
	            // 请求失败执行代码
	    });
	  
	});


/*function op(){
	var number=new Array();
	$('input:checkbox[name=ck]:checked').each(function(k){
		number[k] = $(this).val();
	})
	alert(number)
}

$(function(){
	$("#allck").click(function(){
		if($("#allck").prop("checked")){
		$(":checkbox").prop("checked",true);
		}else{
		$(":checkbox").removeAttr("checked");
		}
	})
	
}*/
	
	



