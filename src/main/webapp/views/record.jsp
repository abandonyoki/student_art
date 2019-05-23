<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>学生作品管理系统</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<%@include file="common.jsp"%>


<style type="text/css">
			
body {
	
	background-color:#e6e8ea;
	padding-right:0px !important;
	    background-color: white;
	    height:100%;
}
.modal{
	overflow:auto !important;
	padding-right:0px !important;
}


</style>

</head>
<body >
<!--cantainer-fluid  开始 -->
<div class = "cantainer">
	<!-- row-fluid  第一个开始 -->
	<div class="row">
		<!-- col-md-12  第一个开始 -->
		<div class = "col-md-12">
			<!-- panel panel-default  第一个开始 -->
			<div class="panel panel-default">
				<div class="panel-body">
				<!-- 查询条件开始 -->
				<form id="searchForm" class="form-horizontal">
					<div class="form-group">
						<%--<label class="col-md-1 control-label" >操作类型</label>
						<div class="col-md-2">
							<input class="form-control" type="text" name="operateType">
						</div>--%>
						<label class="col-md-1 control-label" >操作人</label>
						<div class="col-md-2">
							<input class="form-control" type="text" name="username">
						</div>
						<div class="col-sm-6" align="left">
							<button type="button" class="btn btn-md btn-success" onclick = "operation.searchForm()">
								<span class="glyphicon glyphicon-search"></span>查询
							</button>
							<!-- <button type="button" class="btn btn-md btn-info" onclick="">
								<span class="glyphicon glyphicon-download"></span>导出
							</button> -->
						</div>
					</div>
				
				</form>
				<!-- 查询条件结束 -->
				</div>
			</div>
			<!-- panel panel-default  第一个开始 -->
		</div>
		<!-- col-md-12  第一个结束 -->	
	</div>
	<!-- row-fluid  第一个结束 -->
	
	<!-- row-fluid  第二个开始-->
	<div class="row-fluid">
		<div class="panel panel-default">
			<div class="panel-body">
				<!-- toolbar开始 -->
				<div id="toolbar" class="btn-toolbar" role="toolbar">
					<button id="delete_btn" type="button" class="btn  btn-danger btn-md" onclick="operation.deleteFrom()">
						<span class="glyphicon glyphicon-minus"></span> 删除
					</button>
				</div>
				<!-- toolbar结束 -->
				<table id="dg"></table>
			</div>
		</div>
	</div>
<!-- row-fluid  第二个结束-->


</div>
<!--cantainer-fluid  结束 -->

<!-- 第一个model 开始 -->
<!-- 第一个model 结束 -->

<script type="text/javascript">

//用户操作
var operation={
	searchForm:function(){
		$('#dg').bootstrapTable("refresh");
	},
	//弹出添加页面
	//添加表单
	//弹出修改页面
	//修改表单
	//删除表单
	deleteFrom:function(){
		var rows = $("#dg").bootstrapTable("getSelections");
		if(rows.length == 1  ){
			bootbox.confirm({
				size:"small",
				title:"提示",
				message:"确认删除这条数据吗？",
				callback:function(result){
					if(result){
						$.post("record/deleteRecord", {id:rows[0].id} ,function(data) {
							var obj = eval('('+data+')');
							$("#dg").bootstrapTable("refresh");
							message.alert(data.showInfo);
							
						});
						
					}
				}
			});
			
		}else{
			message.alert("请选择一条要删除的数据！");
			return;
		}
		
	}
}




$(function(){
	$('#dg').bootstrapTable({
		url:"record/query",
		sidePagination:"server",
		 method: "post",
         contentType:"application/x-www-form-urlencoded",
		queryParams:fmt.queryParams,
		pagination:true,
		pageNumber:1,
		pageSize:10,
		pageList:[10,25,50,100],
		//search:true,
		clickToSelect:true,
		singleSelect:true,
		toolbar:'#toolbar',
		columns:columns,
	})
	
});

//表格格式化
var fmt = {
	queryParams:function(params){
		var temp = getFormJson("searchForm");
		temp.rows = params.limit;
		temp.currentPage = (params.offset/params.limit)+1;
		return temp;
		
	},	
	setTime:function(value,row,index){
		if(value!=null && value!=""){
			return formatTimeYMDhms(value);  //转成年月日时分秒
		}
		
	}	
		
}
var columns=[[
 	 {
		field:'ck',
		width:'2%',
		checkbox:true,
		align:'center',
		
	},  
	{
		field:'id',
		title:'id',
		width:10,
		align:'center',
		visible:false
	},
	{
		field:'username',
		title:'操作人',
		width:'25%',
		align:'center',
		visible:true,

	},
	{
		field:'designName',
		title:'作品名称',
		width:'25%',
		align:'center',
		visible:true,

	},

	{
		field:'operateType',
		title:'操作类型',
		width:'25%',
		align:'center',
		visible:true
	},
	{
		field:'operateTime',
		title:'操作时间',
		width:'23%',
		align:'center',
		visible:true,
		formatter:fmt.setTime
	}

]];


$(function(){
	$("#setForm").bootstrapValidator({
		live:'disabled',
		excluded:[':disabled',':hidden',':not(:visible)'],
		fields:{
			noticeName:{
				validators:{
					message:"请输入学期名称!",
				}

			},


		}

	});



})





</script>



</body>
</html>

