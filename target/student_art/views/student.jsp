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
						<label class="col-md-1 control-label" for = "serieId">学院</label>
						<div class="col-md-2">
							<select class="selectpicker" id="serieId2" name="serieId" ></select>
						</div>
						<label class="col-md-1 control-label" for = "professionId">专业</label>
						<div class="col-md-2">
							<select class="selectpicker" id="professionId2" name="professionId" ></select>
						</div>
						<label class="col-md-1 control-label" for = "classId">班级</label>
						<div class="col-md-2">
							<select class="selectpicker" id="classId2" name="classId" ></select>
						</div>
					</div>
					<div class="form-group">	
						<label class="col-md-1 control-label" >姓名</label>
						<div class="col-md-2">
							<input class="form-control" type="text" name="stuName">
						</div>
						<div class="col-sm-2" align="left">
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
					<button id="add_btn" type="button" class="btn  btn-success btn-md" onclick = "operation.addWindow()">
						<span class="glyphicon glyphicon-plus"></span> 新增
					</button>
					<button id="edit_btn" type="button" class="btn  btn-info  btn_md" onclick="operation.editWindow()">
						<span class="glyphicon glyphicon-edit"></span> 编辑
					</button>
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
<div id="setModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keybord="false">
	<div class="modal-dialog" style="width:700px">
		<div class="modal-content">
			<div class="modal-header">
				编辑
			</div>
			<!-- modal-body 开始 -->
			<div class="modal-body">
				<form id="setForm" class="form-horizontal">
					<input type="hidden" name="id">
					<div class="form-group">
						<label class="col-sm-4 control-label" >学生学号</label>
						<div class="col-sm-6">
							<input class="form-control" type="number" name = "stuNo" placeholder="请输入学生学号">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" >学生姓名</label>
						<div class="col-sm-6">
							<input class="form-control" type="text" name = "stuName" placeholder="请输入学生姓名">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" >性别</label>
						<div class="col-sm-6">
							<select class="selectpicker" name="stuSex" style="width:100%">
								<option value="1">男</option>
								<option value="2">女</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" >年龄</label>
						<div class="col-sm-6">
							<input class="form-control" type="number" name = "stuAge" placeholder="请输入学生年龄">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">联系电话</label>
						<div class="col-sm-6">
							<input class="form-control" type="number" name = "phone" placeholder="请输入联系电话">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">邮箱</label>
						<div class="col-sm-6">
							<input class="form-control" type="email" name = "mailBox" placeholder="请输入邮箱">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="classId">班级</label>
						<div class="col-sm-6">
							<select class="selectpicker" id="classId" name="classId" ></select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="professionId">专业</label>
						<div class="col-sm-6">
							<select class="selectpicker" id="professionId" name="professionId" ></select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="serieId">学院</label>
						<div class="col-sm-6">
							<select class="selectpicker" id="serieId" name="serieId" ></select>
						</div>
					</div>
				</form>	
			</div>
			<!-- modal-body 结束 -->
			<div class="modal-footer">
				<button id="add_save_btn" type="button" class="btn btn_success" onclick = "operation.addForm()">
					<span class="glyphicon glyphicon-ok"></span>保存
				</button>
				<button id="edit_save_btn" type="button" class="btn btn_info" onclick = "operation.editForm()">
					<span class="glyphicon glyphicon-ok"></span>保存
				</button>
				<button id="delete_save_btn" type="button" class="btn btn_warning" onclick = "operation.closeMoal()">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
			</div>
			
		</div>
	</div>



</div>
<!-- 第一个model 结束 -->

<script type="text/javascript">

//用户操作
var operation={
	searchForm:function(){
		$('#dg').bootstrapTable("refresh");
	},
	//弹出添加页面
	addWindow:function(){
		$('#setModal').modal('show');
		formObj.setBtnIsShow("add_save_btn","edit_save_btn",1);

	},
	//添加表单
	addForm:function(){
		$("#setForm").bootstrapValidator('validate');
		if($("#setForm").data("bootstrapValidator").isValid()){
			var temp = getFormJson("setForm");
			$.post("student/insertStudent", temp ,function(data) {
				var obj = eval('('+data+')');
				$("#dg").bootstrapTable("refresh");
				message.alert(obj.showInfo);
				$('#setForm').data('bootstrapValidator').resetForm(true);
				$('#setForm')[0].reset();
				$('#setModal').modal('hide');
			});
		}
		
	},
	//弹出修改页面
	editWindow:function(){
		var rows = $('#dg').bootstrapTable('getSelections');
		if(rows.length == 1  ){
			setForm("#setForm",rows[0]);
			$('#classId').selectpicker('val', rows[0].classId);
			$('#professionId').selectpicker('val', rows[0].professionId);
			$('#serieId').selectpicker('val', rows[0].serieId);
			$('#setModal').modal('show');
			formObj.setBtnIsShow("add_save_btn","edit_save_btn",0);
			
		}else{
			message.alert("请选择一条要修改的数据！");
			return;
		}
		
	},
	//修改表单
	editForm:function(){
		var validate = $("#setForm").bootstrapValidator('validate');
		if($("#setForm").data("bootstrapValidator").isValid()){
			var temp = getFormJson("setForm");
			$.post("student/updateStudent", temp ,function(data) {
				var obj = eval('('+data+')');
				$("#dg").bootstrapTable("refresh");
				message.alert(obj.showInfo);
				$('#setForm').data('bootstrapValidator').resetForm(true);
				$('#setForm')[0].reset();
				$('#setModal').modal('hide');
			});
		}
		
	},
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
						$.post("student/deleteStudent", {id:rows[0].id} ,function(data) {
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
		
	},
	//关闭页面
	closeMoal:function(){
		$('#setForm').data('bootstrapValidator').resetForm(true);
		//$('#setForm').reset();
		$('#setModal').modal('hide');
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
		queryParams:fmt.queryParams,
		 method: "post",
         contentType:"application/x-www-form-urlencoded",
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
	$.post("profession/findProfession" ,function(data) {
		var obj = eval('('+data+')');
		$('#professionId').empty();
		var html="";
		html +="<option value=''>--请选择--</option>";
		$.each(obj,function(i,val){ 
			html+="<option value='"+val.id+"'>"+val.professionName+"</option>";
		});
		$('#professionId').append(html);  
		$("#professionId").selectpicker("refresh");
		$('#professionId2').append(html);  
		$("#professionId2").selectpicker("refresh");
	});
	
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
	

	
	$("#setForm").bootstrapValidator({
		live:'disabled',
		excluded:[':disabled',':hidden',':not(:visible)'],
		fields:{
			stuNo:{
				validators:{
					message:"请输入学生学号!",
				}
			},
			stuName:{
				validators:{
					message:"请输入学生姓名!",
				}
			},
			stuAge:{
				validators:{
					message:"请输入学生年龄!",
				}
			},
			phone:{
				validators:{
					message:"请输入电话！",
				}
			},
		/* 	serieId:{
				validators:{
					message:"请选择学院！",
				}
			},
			professionId:{
				validators:{
					message:"请选择专业！",
				}
			},
			classId:{
				validators:{
					message:"请选择班级！",
				}
			},
			termId:{
				validators:{
					message:"请选择学期！",
				}
			}, */
		}
		
	});
	
	
	
})





</script>



</body>
</html>

