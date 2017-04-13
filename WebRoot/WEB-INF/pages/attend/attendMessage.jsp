<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="context_path"
	value="#request.get('javax.servlet.forward.context_path')"></s:set> --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
  <head>
  
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
  
    <%@include file="/WEB-INF/pages/common/head.jsp"%>
    
   <script src="/Test/bootstrap/js/bootstrap-select.js"></script>
    <link rel="stylesheet" href="/Test/bootstrap/css/bootstrap-switch.css" />
    <script src="/Test/bootstrap/js/bootstrap-switch.js"></script>
    
    <!-- 内容 开始 -->
 <div class="content">
        
        <!-- 提示框1 -->
					 	<div id="myModal" class="modal hide fade">
					  <div class="modal-header">
					    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					    <h3>警告</h3>
					  </div>
					  <div  class="modal-body">
					    <p>你确定要删除吗？</p>
					  </div>
					  <div class="modal-footer">
								<a href="#" class="btn"  data-dismiss="modal" aria-hidden="true">关闭</a>
    							<a id="delbtn2"  href="#" class="btn btn-primary"> 确  认</a>
					  </div>
					</div>


          
        <div class="header">
            
            <h1 class="page-title">订单</h1>
        </div>
        
                <ul class="breadcrumb">
            <li><a href="/Test/personal.html">主页</a> <span class="divider">/</span></li>
            <li class="active">班级</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    
        <div class="row-fluid">
                <div class="btn-toolbar">
                 
                   <a href="/Test/addAttendCourse.html?course.courseid=<s:property value="map.courseid" />"  class="btn btn-primary" >
                   <i class="icon-plus"></i> 添 加</a>
                <div class="btn-group">
                </div>
                </div>
				<s:if test="map.status==1">

					<div class="alert alert-info">
						<button type="button" class="close" data-dismiss="alert">×</button>
						<strong>小提示：</strong> 添加成功！！
					</div>
				</s:if>
				
				<s:if test="map.status==2">

					<div class="alert alert-info">
						<button type="button" class="close" data-dismiss="alert">×</button>
						<strong>小提示：</strong> 更改考勤成功！！
					</div>
				</s:if>
				
			</div>
<div class="well">
    <table class="table table-striped table-bordered bootstrap-datatable datatable">
      <thead>
        <tr>
          <th>学生号</th>
          <th>学生姓名</th>
          <th>考勤时间</th>
          <th>考勤状态</th>
          <th>操作</th>
          <th style="width: 26px;"></th>
        </tr>
      </thead>
      <tbody>
      <s:if test="pageModel != null && pageModel.list.size() > 0">
      <s:iterator value="pageModel.list" >  
        <tr>
          <td><s:property value="student.studentid" /></td>
          <td><s:property value="student.studentname" /></td>
          <td><s:property value="datatime" /></td>
          <td>
          		<s:if test="status==1">
            	<div id="<s:property value="attendid" />"  class="make-switch" data-on-label="已签到" data-off-label="待签到" >
            	 <input  type="checkbox" checked ">
          		 </div>
             	</s:if>
             	<s:if test="status==0">
            	<div id="<s:property value="attendid" />"  class="make-switch" data-on-label="已签到" data-off-label="待签到" >
             	<input type="checkbox" >
           		</div>
            	 </s:if>
          </td>
          <td>
           <a href="/Test/attendPre.html?attendid=<s:property value="attendid" />" class="btn btn-primary"> 预 览</a>
              <button  onclick="del(this)"  courseid="<s:property value="map.courseid" />" delid="<s:property value='attendid' />"  pNow="${pNow}" class="btn btn-danger"><i class="icon-trash icon-white"></i> 删除</button>
       </td>
        </tr>
       </s:iterator>
       	<s:url var="first">
		<s:param name="pageNo" value="1"></s:param>
		<s:param name="attend.course.courseid" value="map.courseid"></s:param>
	</s:url>
	<s:url var="previous">
		<s:param name="pageNo" value="pageModel.pageNo-1"></s:param>
		<s:param name="attend.course.courseid" value="map.courseid"></s:param>
	</s:url>
	<s:url var="last">
		<s:param name="pageNo" value="pageModel.bottomPageNo"></s:param>
		<s:param name="attend.course.courseid" value="map.courseid"></s:param>
	</s:url>
	<s:url var="next">
		<s:param name="pageNo" value="pageModel.pageNo+1"></s:param>
		<s:param name="attend.course.courseid" value="map.courseid"></s:param>
	</s:url>
	<s:include value="/WEB-INF/pages/common/page.jsp"></s:include>
	</s:if>
      </tbody>
    </table>
</div>
<div class="pagination">
    <ul>
    <%-- 	<s:if test="${pre=='yes'}">
        <li><a href="/WirelessOrder/order.do?flag=order&pageNow=${pNow-1}">前一页</a></li>
    	</s:if>
        <s:forEach var="i" begin="1" end="${pageCount}">
        <li>
        <a href="/WirelessOrder/order.do?flag=order&pageNow=${i }">${i }</a>
        </li>
        </s:forEach>
        <s:if test="${next=='yes'}">
        <li><a href="/WirelessOrder/order.do?flag=order&pageNow=${pNow+1}">后一页</a></li>
        </s:if> --%>
    </ul>
</div>             
<!--                   <footer>
                        <hr>
                        <p class="pull-right">&copy; 2013.8 <a href="#" target="_blank"> shun_fzll</a></p>
                    </footer> -->
            </div>
        </div>
    </div>
                    
                    

    <script type="text/javascript">

/*
$(document).ready(function(){
 $("#delbtn").click(function(){
    var delid=$("#delbtn").attr("delid");
    $("#myModal").modal("show").on("shown", function () {
    	var delurl="/WirelessOrder/TableServlet?delid="+delid;
    		$("#delbtn2").attr('href',delurl);
    	});
  });
  
});
  */
  
      var 	xmlhttp;
        $('.make-switch').on('switch-change', function (e, data){
	
            if($("#sex").val()==1){
           	 $("#sex").val("0");
            }else{
           	 $("#sex").val("1");	
            }
          //创建xmlHttp  
	createXMLHttpRequest();
	var url="/Test/updateAttendStatus.html?attend.attendid="+$(this).attr('id');
	xmlhttp.onreadystatechange=state_Change;  
	xmlhttp.open("POST",url,true);  
	xmlhttp.send(null);  
	})


function createXMLHttpRequest() {
	if (window.XMLHttpRequest)
	  {// code for IE7, Firefox, Opera, etc.
	  xmlhttp=new XMLHttpRequest();
	  }
	else if (window.ActiveXObject)
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
}

//处理返回结果
function state_Change()  
{  
 if(xmlhttp.readyState==4)  
 {  
  if(xmlhttp.status==200)  
  {  
	  alert("考勤更改成功");
  }  
 }  
}
 

  
  
function del(obj){
	var delid=$(obj).attr("delid");
	var courseid=$(obj).attr("courseid");
	var pNow=$(obj).attr("pNow");
	var delurl="/Test/deleteAttend.html?attend.attendid="+delid+"&attend.course.courseid="+courseid+"&pageNow="+pNow;
	$("#myModal").modal("show").on("shown", function () {
		$("#delbtn2").attr('href',delurl);
	});
}


    </script>
  </body>
</html>
  