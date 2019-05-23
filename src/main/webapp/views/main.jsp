<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page isELIgnored="false" %>
    <meta charset="utf-8" />
    <title>学生作品管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="common.jsp"%>
    <!-- basic styles -->
    <link href="/student_art/plugins/ace/css/font-awesome.min.css" rel="stylesheet" />
    <!-- ace styles -->
    <link rel="stylesheet" href="/student_art/plugins/ace/css/ace.min.css" />
    <!-- 自定义js -->
    <script src="/student_art/plugins/ace/js/sidebar-menu.js"></script>
    <!-- ace scripts -->
    <script src="/student_art/plugins/ace/js/ace-elements.min.js"></script>
    <script src="/student_art/plugins/ace/js/ace.min.js"></script>

    <style type="text/css">

        body {
            overflow: hidden;
        }
        .tab-content {
            border: none;
            padding: 10px 0px;
        }

        #menu_li_0 {
            display: none;
        }
        .ace-nav>li {
            height: 50px;

        }
    </style>
</head>

<body>
<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try{ace.settings.check('navbar' , 'fixed')}catch(e){}
    </script>

    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="fa fa-envira"></i>
                    学生作品管理系统
                </small>
            </a><!-- /.brand -->
        </div><!-- /.navbar-header -->

        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">

                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <!-- <img class="nav-user-photo" src="images/user.jpg" alt="Jason's Photo" /> -->
                        <span class="user-info">
									<small>欢迎光临,</small>
									${user.username }
								</span>
                        <i class="icon-caret-down"></i>
                    </a>

                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close" style="min-width: 97px;">
                        <li>
                            <a href="javascript:void(0)" onclick="editPwd()">
                                修改密码
                            </a>
                        </li>
                        <li class="divider"></li>

                        <li>
                            <a href="javascript:void(0)" onclick="logout()">
                                退出
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

        <div class="sidebar" id="sidebar">
            <script type="text/javascript">
                try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
            </script>

            <ul class="nav nav-list" id="menu">

            </ul>

            <div class="sidebar-collapse" id="sidebar-collapse">
                <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
            </div>

            <script type="text/javascript">
                try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
            </script>
        </div>

        <div class="main-content">

            <div class="page-content">

                <div class="row">
                    <div class="col-xs-12">
                        <ul class="nav nav-tabs" role="tablist">
                        </ul>
                        <div class="tab-content">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 第一个model 开始 -->
<div id="setModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keybord="false">
    <div class="modal-dialog" style="width:700px">
        <div class="modal-content">
            <div class="modal-header">
                修改密码
            </div>
            <div class="modal-body">
                <form id="setForm" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="form-group">
                            <label class="col-sm-4 control-label">新密码</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password" id="password" name = "password" placeholder="请输入新密码">
                        </div>
                    </div>
                </form>
            </div>
            <!-- modal-body 结束 -->
            <div class="modal-footer">
                <button id="edit_save_btn" type="button" class="btn btn_info" onclick = "editPassword()" style="background-color:#438EB9!important;border-color:#438EB9;">
                    <span class="glyphicon glyphicon-ok"></span>保存
                </button>
                <button id="delete_save_btn" type="button" class="btn btn_warning" onclick = "closeMoal()"  style="background-color:#438EB9!important;border-color:#438EB9;">
                    <span class="glyphicon glyphicon-remove"></span>关闭
                </button>
            </div>

        </div>
    </div>

</div>
<!-- 第一个model 结束 -->

<script type="text/javascript">
    $(window).resize(function() {
        $("#sidebar").height($(window).height()-$("#navbar").height());
        $("div .tab-pane iframe").height($("#sidebar").height()-60);
    });

    $(function () {
        var data ;
        if(parseInt("${user.role}")==1){
            data=[{
                id: '000',
                text: '首页',
                icon: 'fa fa-home fa-lg',
                url: 'login/home',
            },
                {id: '01',text: '我的作品', icon: 'fa fa-gg',url: 'design/stu_design'},
                {id: '02',text: '通知管理', icon: 'fa fa-file-text-o',url: 'notice/notice'}

            ]
        }
        if(parseInt("${user.role}")==2){
            data=[{
                id: '000',
                text: '首页',
                icon: 'fa fa-home fa-lg',
                url: 'login/home',
            },
                {id: '01',text: '学生信息', icon: 'fa fa-group',url: 'student/tea_student'},
                {id: '02',text: '学生作品', icon: 'fa fa-gg',url: 'design/tea_design'},
                {id: '03',text: '通知管理', icon: 'fa fa-file-text-o',url: 'notice/notice'}

            ]
        }
        if(parseInt("${user.role}")==3){
            data=[{
                id: '000',
                text: '首页',
                icon: 'fa fa-home fa-lg',
                url: 'login/home',
            }, {
                id: '01',text: '基础数据设置', icon: 'fa fa-cog',url: '',
                menus: [
                    {id: '11',text: '学院',icon: 'icon-glass',url: 'serie/serie'},
                    {id: '12',text: '专业',icon: 'icon-glass',url: 'profession/profession'},
                    {id: '13',text: '班级',icon: 'icon-glass',url: 'class/class'},
                    {id: '14',text: '科目',icon: 'icon-glass',url: 'course/course'}
                ]
            },
                {id: '02',text: '学生信息', icon: 'fa fa-group',url: 'student/student'},
                {id: '03',text: '老师信息', icon: 'fa fa-user-o',url: 'teacher/teacher'},
                {id: '04',text: '作品信息', icon: 'fa fa-file-text-o',url: 'design/design'},
                {id: '05',text: '日志信息', icon: 'fa fa-file-text-o',url: 'record/record'},
                {id: '06',text: '通知管理', icon: 'fa fa-file-text-o',url: 'notice/notice'}
            ]
        }

        $("#sidebar").height($(window).height()-$("#navbar").height()-40);
        $('#menu').sidebarMenu({
            data:data
        });
        eval($("#menu_li_000 a").attr("href"));
    });

    function logout(){
        bootbox.confirm({
            size:"small",
            title:"提示",
            message:"确认退出吗？",
            callback:function(result){
                if(result){
                    window.location.href="login/logout"
                }
            }
        });
    }

    function editPwd(){
        $('#setModal').modal('show');
    }
    function closeMoal(){
        $('#setModal').modal('hide');
    }
    function editPassword(){
        var password = $('#password').val();
        if(password == ""){
            message.alert("请输入新密码");
            return;
        }
        $.post("login/editPassword", {password:password} ,function(data) {
            var obj = eval('('+data+')');
            alert(obj.showInfo);
            window.location.href="login/logout"
        });
    }

</script>
</body>
</html>

