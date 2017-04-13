 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%-- <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> --%>
<!-- 引入标签库 -->
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%-- <%@ taglib prefix="s" uri="/struts-tags"%> --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<%@include file="/WEB-INF/pages/common/head.jsp"%>

    
    <div class="content">
        
        <div class="header">
            <h1 class="page-title">首页</h1>
        </div>
        
          <ul class="breadcrumb">
            <li><a href="/Test/index.html">首页</a> <span class="divider">/</span></li>
            <li class="active">报表</li>
          </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    

<div class="row-fluid">

<!--     <div class="alert alert-info">
        <button type="button" class="close" data-dismiss="alert">×</button>
        <strong>小提示：</strong> 还有 x 桌客人没有结账哦！
    </div> -->
</div>

<div class="row-fluid">
    <div class="block span6">
        <a href="#tablewidget" class="block-heading" data-toggle="collapse">昨日考勤</a>
        <div id="tablewidget" class="block-body collapse in">
            <table class="table">
              <thead>
                <tr>
                  <th>昨天考勤人数</th>
                  <td><s:property value="#session.countYesterDay"/></td>
                </tr>
              </thead>
              <s:iterator value="#session.courseYesterDay" >
               <thead>
                <tr>
                  <th><s:property value="key" /></th>
                  <td><s:property value="value" /></td>
                </tr>
              </thead>
              </s:iterator>
            </table>
    <!--         <p><a href="users.html">更多...</a></p> -->
        </div>
    </div>
    <div class="block span6">
        <a href="#tablewidget" class="block-heading" data-toggle="collapse">今日考勤</a>
        <div id="tablewidget" class="block-body collapse in">
            <table class="table">
              <thead>
                <tr>
                  <th>当前考勤人数</th>
                  <td><s:property value="#session.countToDay"/></td>
                </tr>
              </thead>
                <s:iterator value="#session.courseToDay" >
              <thead>
                <tr>
                  <th><s:property value="key" /></th>
                  <td><s:property value="value" /></td>
                </tr>
              </thead>
               </s:iterator>
            </table>
<!--             <p><a href="users.html">更多...</a></p> -->
        </div>
    </div>
   
</div>
<!--                     <footer>
                        <hr>
                        <p class="pull-right">&copy; 2013.8 <a href="#" target="_blank"> shun_fzll</a></p>
                    </footer> -->
                    
            </div>
        </div>
    </div>
  </body>
</html>
