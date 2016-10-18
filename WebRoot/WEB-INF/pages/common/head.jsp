<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="context_path"
	value="#request.get('javax.servlet.forward.context_path')"></s:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
  <head>
    <title>学生管理后台</title>
    

    <link rel="stylesheet" type="text/css" href="/Test/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/Test/css/theme.css">
    <link rel="stylesheet" href="/Test/font-awesome/css/font-awesome.css">

	<script src="/Test/js/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src="/Test/bootstrap/js/bootstrap.js"></script>
   

    <!-- Demo page code -->

    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

    
  </head>
  <body class=""> 
  <!--<![endif]-->
    
        <div class="navbar">
        <div class="navbar-inner">
                <ul class="nav pull-right">
                    
                    <li><a href="#" class="hidden-phone visible-tablet visible-desktop" role="button">设置</a></li>
                    <li id="fat-menu" class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i>${user.username}
                            <i class="icon-caret-down"></i>
                        </a>

                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="/Test/personal.html">个人中心</a></li>
                            <li class="divider"></li>
                            <li><a tabindex="-1" href="/Test/login.do?flag=logout">退出</a></li>
                        </ul>
                    </li>
                    
                </ul>
                <a class="brand" href="/Test/home.do?flag=home"><span class="first">学生管理</span> <span class="second">后台</span></a>
        </div>
    </div>
    


    
    <div class="sidebar-nav">
        <form class="search form-inline">
            <input type="text" placeholder="Search...">
        </form>

        <a href="#dashboard-menu" class="nav-header" data-toggle="collapse"><i class="icon-dashboard"></i>首页</a>
        <ul id="dashboard-menu" class="nav nav-list collapse in">
            <li><a href="/Test/home.do?flag=home">报表</a></li>
            <li><a href="/Test/personal.html">个人中心</a></li>
        </ul>

        <a href="#table-menu" class="nav-header" data-toggle="collapse"><i class="icon-table"></i>班级
        </a>
        <ul id="table-menu" class="nav nav-list collapse">
            <li ><a href="/Test/courseMessage.html">班级管理</a></li>
        </ul>

        <a href="#menu-menu" class="nav-header collapsed" data-toggle="collapse"><i class="icon-reorder"></i>考勤
         </a>
        <ul id="menu-menu" class="nav nav-list collapse">
            <li ><a href="/Test/attendCourse.html">考勤管理</a></li>
        </ul>

        <a href="#order-menu" class="nav-header" data-toggle="collapse"><i class="icon-th-large"></i>订单</a>
        <ul id="order-menu" class="nav nav-list collapse">
            <li ><a href="/Test/order.do?flag=order">订单管理</a></li>
        </ul>
    </div>
    </body>
    </html>
    