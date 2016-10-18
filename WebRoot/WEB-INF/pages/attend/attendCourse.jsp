<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="context_path"
	value="#request.get('javax.servlet.forward.context_path')"></s:set> --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
  <head>
  <%@include file="/WEB-INF/pages/common/head.jsp"%>
    
    <link rel="stylesheet" href="/Test/bootstrap/css/bootstrap-switch.css" />
    <script src="/Test/bootstrap/js/bootstrap-switch.js"></script>
    
    <!-- 内容 开始 -->
   <div class="content">
        
        <div class="header">
            
            <h1 class="page-title">个人中心</h1>
        </div>

	<ul class="breadcrumb">
		<li><a href="/Test/home.do?flag=home">主页</a> <span
			class="divider">/</span></li>
		<li class="active">学生考勤</li>
	</ul>
	   <ul class="nav nav-tabs">
	  <s:iterator value="#session.teacher.courses">
	  <%--  <li><a href="teacherCourse.html?course.courseid=<s:property value="courseid" />" data-toggle="tab"><s:property value="coursename" /></a></li> --%>
      <li><a href="attendMessage.html?attend.course.courseid=<s:property value="courseid" />" ><s:property value="coursename" /></a></li>
      
      </s:iterator>
    </ul>
        </div>
         
                  <footer>
                        <hr>
                        <p class="pull-right">&copy; 2013.8 <a href="#" target="_blank"> shun_fzll</a></p>
                    </footer>
                    
            </div>
        </div>
    </div>
    <script type="text/javascript">
        
        	
    </script>
  </body>
</html>
