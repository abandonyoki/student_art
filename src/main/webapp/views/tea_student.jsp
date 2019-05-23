<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<%@ page isELIgnored="false" %>
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
.bootstrap-select:not([class*=col-]):not([class*=form-control]):not(.input-group-btn) {
    width: 100%;
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
						<label class="col-md-1 control-label" for = "stuName">姓名</label>
						<div class="col-md-2">
							<input class="form-control" type="text" name="stuName">
						</div>
						<label class="col-md-1 control-label" for = "classId">班级</label>
						<div class="col-md-2">
							<select class="selectpicker" id="classId2" name="classId" ></select>
						</div>
						<div class="col-sm-2" align="left">
							<button type="button" class="btn btn-md btn-success" onclick = "operation.searchForm()">
								<span class="glyphicon glyphicon-search"></span>查询
							</button>
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

	<div class="row-fluid">
		<div class="panel panel-default">
			<div class="panel-body">
				<table id="dg"></table>
			</div>
		</div>
	</div>
<!-- row-fluid  第二个结束-->


</div>
<!--cantainer-fluid  结束 -->


<script type="text/javascript">

//用户操作
var operation={
	searchForm:function(){
		$('#dg').bootstrapTable("refresh");
	},
	
	
	//导出
	exportFile:function(){
		
		
	},
	//审核
	audit:function(){
		
		
	}
}




$(function(){
	$('#dg').bootstrapTable({
		url:"student/query",
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
		temp.professionId="${user.professionId}";
		temp.rows = params.limit;
		temp.currentPage = (params.offset/params.limit)+1;
		return temp;
		
	},	
	setTime:function(value,row,index){
		if(value !=null && value!=""){
			return formatTimeYMD(value);  //转成年月日时分秒
		}
		
	},
	setSex:function(value,row,index){
		var sex="女";
		if(value==1){
			sex="男";
		}
		return sex;
	}
		
}
var columns=[[
 	{field:'ck',width:'2%',checkbox:true,align:'center'},  
	{field:'id',title:'id',width:"10%",align:'center',visible:false},
	{field:'stuNo',title:'学号',width:"10%",align:'center',visible:true},
	{field:'stuName',title:'姓名',width:"10%",align:'center',visible:true},
	{field:'stuAge',title:'年龄',width:"10%",align:'center',visible:true},
	{field:'stuSex',title:'性别',width:"10%",align:'center',visible:true,formatter:fmt.setSex},
	{field:'phone',title:'电话',width:"10%",align:'center',visible:true},
	{field:'mailBox',title:'邮箱',width:"10%",align:'center',visible:true},
	{field:'classId',title:'班级',width:10,align:'center',visible:false},
	{field:'className',title:'班级',width:"10%",align:'center',visible:true},
	{field:'professionId',title:'专业',width:10,align:'center',visible:false},
	{field:'professionName',title:'专业',width:"10%",align:'center',visible:true},
	{field:'serieId',title:'学院',width:10,align:'center',visible:false},
	{field:'serieName',title:'学院',width:"10%",align:'center',visible:true},
	
]];


$(function(){
	//班级
	$.post("class/findClass" ,function(data) {
		var obj = eval('('+data+')');
		$('#classId').empty();
		var html="";
		html +="<option value=''>--请选择--</option>";
		$.each(obj,function(i,val){ 
			html+="<option value='"+val.id+"'>"+val.className+"</option>";
		});
		$('#classId').append(html);  
		$("#classId").selectpicker("refresh");
		$('#classId2').append(html);  
		$("#classId2").selectpicker("refresh");
	});
	
	//专业

	//系
	$.post("serie/findSerie" ,function(data) {
		var obj = eval('('+data+')');
		$('#serieId').empty();
		var html="";
		html +="<option value=''>--请选择--</option>";
		$.each(obj,function(i,val){ 
			html+="<option value='"+val.id+"'>"+val.serieName+"</option>";
		});
		$('#serieId').append(html);  
		$("#serieId").selectpicker("refresh");
		$('#serieId2').append(html);  
		$("#serieId2").selectpicker("refresh");
	});

})





</script>



</body>
</html>

