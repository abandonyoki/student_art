<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>学生作品管理系统</title>
	<%@ page isELIgnored="false" %>
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
						<label class="col-md-1 control-label" for = "designName">作品名称</label>
						<div class="col-md-2">
							<input class="form-control" type="text" name="designName">
						</div>
						<label class="col-sm-1 control-label" for="status">审核</label>
						<div class="col-sm-2">
							<select class="selectpicker" name="status" >
								<option value="null">--请选择--</option>
								<option value="0">等待审核</option>
								<option value="1">审核通过</option>
								<option value="2">审核拒绝</option>
							</select>
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
					<!--<button id="add_btn" type="button" class="btn  btn-success btn-md" onclick = "operation.addWindow()">
						<span class="glyphicon glyphicon-plus"></span> 新增
					</button> -->
					<button id="edit_btn" type="button" class="btn  btn-info  btn_md" onclick="operation.down()">
						<span class="glyphicon glyphicon-edit"></span> 下载
					</button>
					<!--<button id="delete_btn" type="button" class="btn  btn-danger btn-md" onclick="operation.deleteFrom()">
						<span class="glyphicon glyphicon-minus"></span> 删除
					</button>-->
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
				审核
			</div>
			<!-- modal-body 开始 -->
			<div class="modal-body">
				<form id="setForm" class="form-horizontal">
					<input type="hidden" name="id">
					<div class="form-group">
						<label class="col-sm-4 control-label" for="status">审核</label>
						<div class="col-sm-6">
							<select class="selectpicker" name="status" >
								<option value="">--请选择--</option>
							    <option value="1">审核通过</option>
							    <option value="2">审核拒绝</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-4 control-label" for="advice">审核意见</label>
						<div class="col-sm-6">
							<input class="form-control" type="text" name = "advice" placeholder="请输入审核意见">
						</div>
					</div>

				</form>
			</div>
			<!-- modal-body 结束 -->
			<div class="modal-footer">
				<button type="button" class="btn btn_success" onclick = "operation.addForm()">
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
	addForm:function(){
		var temp = getFormJson("setForm");
		$.post("design/audit", temp ,function(data) {
			var obj = eval('('+data+')');
			$("#dg").bootstrapTable("refresh");
			message.alert(obj.showInfo);
			$('#setForm').data('bootstrapValidator').resetForm();
			$('#setForm')[0].reset();
			$('#setModal').modal('hide');
		});
	},
	down:function(){
		var rows = $("#dg").bootstrapTable("getSelections");
		if(rows.length == 1  ){
			if(rows[0].status == "0" || rows[0].status == "2"){
				message.alert("请选择审核通过的作品下载！");
				return;
			}
			location.href="design/down?id="+rows[0].id;
		}else{
			message.alert("请选择一条要下载的作品！");
			return;
		}

	},

	//关闭页面
	closeMoal:function(){
		$('#setForm').bootstrapValidator('resetForm',true);
		//$('#setForm').reset();
		$('#setModal').modal('hide');
	},
	//审核
	audit:function(index){
		var row = $('#dg').bootstrapTable('getData')[index];
		setForm("#setForm",row);
		$('#setModal').modal('show');
	}
}




$(function(){
	$('#dg').bootstrapTable({
		url:"design/query",
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
	setStatus:function(value,row,index){
		var val="";
		if(value==0){
			val="等待审核";
		}
		if(value==1){
			val="审核通过";
		}
		if(value==2){
			val="审核拒绝";
		}
		return val;  //转成年月日时分秒
	},
	setOpt:function(value,row,index){
		var html ="";
		if(row.status==0){
			html +='<button type="button" class="btn btn-md btn-info" onclick = "operation.audit('+index+')"><span class="glyphicon glyphicon-add"></span>审核</button>'
		}
		if(row.status==1){
			html +='可下载'
		}
		if(row.status==2){
			html +='不可下载'
		}
		return html;
	},
	setImage:function(value,row,index){
		var html ="";
		var matchStr = '.*\.psd$';
		if(value.match(matchStr) != null){
			html ='<img src="upload/ps.png" style="width:100px;height:100px;"> </img>'
		}else {
			html = '<img src="' + value + '" style="width:100px;height:100px;"> </img>';
		}
		return html;
		
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
	{field:'stuId',title:'作者',width:'8%',align:'center',visible:false},
	{field:'stuName',title:'作者',width:'8%',align:'center',visible:true},
	{field:'designName',title:'作品名称',width:'12%',align:'center',visible:true},
	{field:'designIntroduce',title:'作品介绍',width:'12%',align:'center',visible:true},
	{field:'imgUrl',title:'作品',width:10,align:'center',visible:true,formatter:fmt.setImage},
	{field:'courseId',title:'所属课程',width:'12%',align:'center',visible:false},
	{field:'courseName',title:'所属课程',width:'12%',align:'center',visible:true},
	{field:'createTime',title:'创建时间',width:'12%',align:'center',visible:true},
	{field:'advice',title:'审核意见',width:'12%',align:'center',visible:true},
	{field:'status',title:'状态',width:'8%',align:'center',visible:true,formatter:fmt.setStatus},
	{field:'opt',title:'操作',width:'8%',align:'center',visible:true,formatter:fmt.setOpt},
	
]];


$(function(){
	
	 $("#file-0c").fileinput({
	        language : 'zh'
	    });

	//班级
	$.post("course/findCourse" ,function(data) {
		var obj = eval('('+data+')');
		$('#courseId').empty();
		var html="";
		html +="<option value=''>--请选择--</option>";
		$.each(obj,function(i,val){ 
			html+="<option value='"+val.id+"'>"+val.courseName+"</option>";
		});
		$('#courseId').append(html);
		$("#courseId").selectpicker("refresh");
		$('#courseId2').append(html);
		$("#classId2").selectpicker("refresh");
	
	});
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

