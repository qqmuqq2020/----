/*var fid = 3;*/
var str;// 拼接字符串
var total;// 分页用的总数 查询出数量
var pageindex;// 分页用的页码 获取当前页
var bookname;// 书名
var PositionID;// 所属职位
//权限id
var fid=6;
var xfid=11;
function changeSelBtn(index){
    if(index==1){
        $("#sel_btn1").addClass('ch_cls');
        $("#sel_btn2").removeClass('ch_cls');
      
    }else if(index==2){
        $("#sel_btn1").removeClass('ch_cls');
        $("#sel_btn2").addClass('ch_cls');
    }
    
 }

//导出
function out1(){
	/*like:,clike:$("#pos").val()*/
	

	var book=$("#StaffName").val();
	var status=$("#pos").val();
	var psid=$("#psid").val();
	var client=$("#client").val()
	
	if(psid==0){
		if((book==null||book=="")&&(status==""||status==null)&&(client==""||client==null)){
			alert("请选择条件查询后,再进行导出")
		}else{  
			
			//
			var url2 = "/HRManagement/hrm/CwgonRecord/outs1?fid="+fid+"&book="+book+"&status="+status+"&client="+client;
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
					a.download = '备案管理.xls';
					a.href = window.URL.createObjectURL(blob);
					$("body").append(a); //  ޸ firefox   ޷     click
					a.click();
					$(a).remove();

				}
			};
			//     ajax    
			xhr.send()
			
			
			
		
		}
	}else{
		

		//
		var url2 = "/HRManagement/hrm/CwgonRecord/outs1?fid="+fid+"&book="+book+"&status="+status+"&client="+client;
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
				a.download = '备案管理.xls';
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


// load 
$(function(){

	$.ajax({
	    url:"/HRManagement/hrm/CwgonRecord/selepsid",
	    type:"post",
	    data: {fid:fid},
	    success:function(data){
		    	if(data!=2){
		    		$(".tds1").remove()
		    	}
		    	if(data==0){
		    		$("#sln").remove()
		    	}
		    	
	    	}
	    })
	
	
	
	
	
	//全选start,开始导出备案
$('input[name="selectAll"]').on("click",function(){
if($(this).is(':checked')){
    $('input[name="chooseInfo"]').each(function(){
        $(this).prop("checked",true);
    });
}else{
    $('input[name="chooseInfo"]').each(function(){
        $(this).prop("checked",false);
    }); 
}
//
});

	
	
	 
})
//导出
	
	
//导出备案end

// load end

// 查询按钮 start
/*function checkfind() {
	bookname = $("#StaffName").val();
	PositionID = $("#pos").val();

	PageClick(pageindex, total, 6, Staffname, PositionID);
}*/
	// 查询按钮 end


//pageMe.js 使用方法
var app = angular.module('myApp', []);
	
 	var index=1;
		app.controller('siteCtrl', function($scope, $http) {
			//发行员，我的备案
			
			//删除方法
			$scope.del=function(id){
				if(confirm("确认删除吗?")){
					$.ajax({
					    url:"/HRManagement//hrm/CwgonRecord/del",
					    type:"post",
					    data: {id:id,fid:fid},
					    //根据servlet传回的数据对结果进行判断
					    success:function(data){
					    		if(data>0){
					    			alert("删除成功")
					    			 $http({
					    			        method: 'POST',
					    			        url: '/HRManagement//hrm/CwgonRecord///onRecordList',
					    			        data:$.param({index:index,like:$("#StaffName").val(),clike:$("#pos").val(),pub:$("#client").val(),snnumber:$("#ppos").val(),fid:fid}),
					    			        headers: {'Content-Type': 'application/x-www-form-urlencoded'}	
					    			    }).then(function successCallback(response) {
					    			            $scope.names = response.data.getlist;
					    			          
					    			            // 加载list页面
					    					  $scope.Page(response.data);
					    			            
					    			        }, function errorCallback(response) {
					    			            // 请求失败执行代码
					    			    });
					    			  
					    		}else{
					    			alert("该备案已审核通过不能删除");
					    		}
					    }
			
				 });
				}
				 
			}
			//同意
			$scope.consent=function(id){
				if(confirm("确认通过审核吗？")){
				$.ajax({
				    url:"/HRManagement//hrm/CwgonRecord//consent",
				    type:"post",
				    data: {id:id,fid:xfid},
				    //根据servlet传回的数据对结果进行判断
				    success:function(data){
				    		if(data>0){
				    			alert("审核通过")
				    			 $http({
				    			        method: 'POST',
				    			        url: '/HRManagement//hrm/CwgonRecord///onRecordList',
				    			        data:$.param({index:index,like:$("#StaffName").val(),clike:$("#pos").val(),pub:$("#client").val(),snnumber:$("#ppos").val(),fid:fid}),
					    			        headers: {'Content-Type': 'application/x-www-form-urlencoded'}	
				    			    }).then(function successCallback(response) {
				    			            $scope.names = response.data.getlist;
				    			            
				    			            // 加载list页面
				    					  $scope.Page(response.data);
				    			            
				    			        }, function errorCallback(response) {
				    			            // 请求失败执行代码
				    			    });
				    			  
				    		}
				    }
		
			 });
				}
				
			}
			//拒绝
			$scope.refust=function(id){
				if(confirm("确认拒绝审核吗？")){
					$.ajax({
					    url:"/HRManagement//hrm/CwgonRecord//refust",
					    type:"post",
					    data: {id:id,fid:xfid},
					    //根据servlet传回的数据对结果进行判断
					    success:function(data){
					    		if(data>0){
					    			alert("已拒绝")
					    			 $http({
					    			        method: 'POST',
					    			        url: '/HRManagement//hrm/CwgonRecord///onRecordList',
					    			        data:$.param({index:index,like:$("#StaffName").val(),clike:$("#pos").val(),pub:$("#client").val(),snnumber:$("#ppos").val(),fid:fid}),
						    			        headers: {'Content-Type': 'application/x-www-form-urlencoded'}	
					    			    }).then(function successCallback(response) {
					    			            $scope.names = response.data.getlist;
					    			           
					    			            // 加载list页面
					    					  $scope.Page(response.data);
					    			            
					    			        }, function errorCallback(response) {
					    			            // 请求失败执行代码
					    			    });
					    			  
					    		}
					    }
			
				 });
				}
			}
			//手动中止方法
			$scope.out=function(id){
				if(confirm("确认中止吗？")){
				 $.ajax({
					    url:"/HRManagement//hrm/CwgonRecord/out",
					    type:"post",
					    data: {id:id,fid:fid},
					    //根据servlet传回的数据对结果进行判断
					    success:function(data){
					    		if(data>0){
					    			alert("终止成功")
					    			 $http({
					    			        method: 'POST',
					    			        url: '/HRManagement/hrm/CwgonRecord/onRecordList',
					    			        data:$.param({index:index,like:$("#StaffName").val(),clike:$("#pos").val(),pub:$("#client").val(),snnumber:$("#ppos").val(),fid:fid}),
					    			        headers: {'Content-Type': 'application/x-www-form-urlencoded'}	
					    			    }).then(function successCallback(response) {
					    			            $scope.names = response.data.getlist;
					    			           
					    			            // 加载list页面
					    					  $scope.Page(response.data);
					    			            
					    			        }, function errorCallback(response) {
					    			            // 请求失败执行代码
					    			    });
					    			  
					    		}
					    }
			
				 });
					}
				}
			//延期
			$scope.delay=function(id,residueNumber){
				if(confirm("确认延期吗？")){
				$.ajax({
				    url:"/HRManagement//hrm/CwgonRecord///residue",
				    type:"post",
				    data: {
				    	id:id,
				    	residueNumber:residueNumber,
				    	fid:fid
				    	},
				    //根据servlet传回的数据对结果进行判断
				    success:function(data){
				    		if(data>0){
				    			alert("延期成功")
				    			 $http({
				    			        method: 'POST',
				    			        url: '/HRManagement//hrm/CwgonRecord///onRecordList',
				    			        data:$.param({index:index,like:$("#StaffName").val(),clike:$("#pos").val(),pub:$("#client").val(),snnumber:$("#ppos").val(),fid:fid}),
				    			        headers: {'Content-Type': 'application/x-www-form-urlencoded'}	
				    			    }).then(function successCallback(response) {
				    			            $scope.names = response.data.getlist;
				    			           
				    			            // 加载list页面
				    					  $scope.Page(response.data);
				    			            
				    			        }, function errorCallback(response) {
				    			            // 请求失败执行代码
				    			    });
				    			  
				    		}
				    }
		
			 });
				}
			}
			//查询
			$scope.checkfind=function(){
				var like=$("#StaffName").val();
				var clike=$("#pos").val();
				index=1;
				$http({
			        method: 'POST',
			        url: '/HRManagement//hrm/CwgonRecord///onRecordList',
			        data:$.param({index:index,like:$("#StaffName").val(),clike:$("#pos").val(),pub:$("#client").val(),snnumber:$("#ppos").val(),fid:fid}),
			        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			    }).then(function successCallback(response) {
			            $scope.names = response.data.getlist;
			           
			            // 加载list页面
					  $scope.Page(response.data);
			            
			        }, function errorCallback(response) {
			            // 请求失败执行代码
			    });
			
			}
			
		
		
		
		
			$scope.Page=function(obj){
				
				//如果总数量等于100那么就注销添加按钮
			    
			
				 $("#page").paging({
				        pageNum: index, // 当前页面
				        totalNum: obj.pageCount, // 总页码
				        totalList: obj.count, // 记录总数量
				        like:obj.like,
				        clike:obj.clike,
				        pub:obj.pub,
				        snnumber:obj.snnumber,
				        callback: function (num) { //回调函数
				          index=num
				          $http({
								        method: 'POST',
								        url: '/HRManagement/hrm/CwgonRecord/onRecordList',
								        data:$.param({index:index,like:$("#StaffName").val(),clike:$("#pos").val(),pub:$("#client").val(),snnumber:$("#ppos").val(),fid:fid}),
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
		        method: 'POST',
		        url: '/HRManagement/hrm/CwgonRecord/onRecordList',
		        data:$.param({index:index,like:$("#StaffName").val(),clike:$("#pos").val(),pub:$("#client").val(),snnumber:$("#ppos").val(),fid:fid}),
		        headers: {'Content-Type': 'application/x-www-form-urlencoded'}	
		    }).then(function successCallback(response) {
		            $scope.names = response.data.getlist;
		          
		            // 加载list页面
				  $scope.Page(response.data);
		            
		        }, function errorCallback(response) {
		            // 请求失败执行代码
		    });
		  
});
		
		
		
