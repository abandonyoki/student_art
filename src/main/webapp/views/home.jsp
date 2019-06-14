<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>学生作品管理系统</title>
	<%@ page isELIgnored="false" %>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<%@include file="common.jsp"%> 

<!-- <link rel="stylesheet" type="text/css" href="css/style-home.css"> -->
<link rel="stylesheet" type="text/css" href="/student_art/plugins/photo/photopile.css">


<style type="text/css">
			

body {
	background-color: lavender;
	height:100%;
}

.photopile-wrapper { width: 1080px; margin: 0px auto; padding: 0px;}
ul.photopile li a img { border: 0 none;}

</style>

</head>
<body >
				<button type="button" class="btn btn-primary pull-right" style="position:absolute; top:0px;right:0px;" onclick="down()">下载</button>	
<!-- Demo  -->
<div class="photopile-wrapper">

	<ul class="photopile"  >
		<c:forEach var="obj" items="${list}">
			<li>
				<a href="${obj.imgUrl }" onclick="getPic(${obj.id })" >
					<img src="${obj.imgUrl }" alt="${obj.stuName }-${obj.designName}-${obj.courseName}"
						 title="${obj.stuName }-${obj.designName}-${obj.courseName}" style="width:180px; height:223px;font-size:30px" />
				</a>

			</li>
		</c:forEach>
	</ul>
</div>


<!--cantainer-fluid  结束 -->

<script type="text/javascript" src="/student_art/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/student_art/plugins/photo/jquery-ui-1.10.4.min.js"></script>
<script type="text/javascript" src="/student_art/plugins/photo/photopile.js"></script>


<script type="text/javascript">
   
var id = 0;
function getPic(id2){
	id = id2;
}

function down(){
	if(id==0){
		message.alert("请选择作品！");
		return;
	}
	location.href="design/down?id="+id;
}

</script>

</body>
</html>

