<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 <%@ include file="/WEB-INF/pages/common/head.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>学生成绩管理系统</title>
    
  </head>

  <body bgcolor="#d9DFAA">
    <table border="1" cellpadding="1" cellspacing="8" width="800">
      <tr align="center" bgcolor="silver">
        <th>学号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>专业</th>
        <th>图片</th>
        
        <th>操作</th>
        <th>操作</th>
      </tr>
      <s:if test="pageModel != null && pageModel.list.size() > 0">
      <s:iterator  value="pageModel.list">
      <tr align="center" bgcolor="silver">
        <td><s:property value="studentId"/>
        <td><s:property value="name"/></td>
        <td><s:if test="sex==0">男</s:if><s:else>女</s:else></td>
        <td><s:property value="department"/></td>
        <td><s:property value="studentImage"/></td>
<%--         <td><s:date name="#xs.cssj" format="yyyy-MM-dd"/></td>
        <td><s:property value="#xs.zxf"/></td> --%>
        <%-- <td><a href="findXs.action?student.studentId=<s:property value="studentId"/>">详细信息</a></td> --%>
        <td><a href="deleteXs.action?xs.xh=<s:property value="studentId"/>" onclick="if(!confirm('确定删除该信息吗？'))return false;else return true">删除</a></td>
        <td><a href="updateXsView.action?xs.xh=<s:property value="studentId"/>">修改</a></td>
      </tr>
      
      </s:iterator>
       </s:if>
      <tr>
       <s:include value="/WEB-INF/pages/common/page.jsp"></s:include>
      </tr>
  
    </table>  
  </body>
</html>
