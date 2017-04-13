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
            <h1 class="page-title">学生</h1>
        </div>
        
                <ul class="breadcrumb">
            <li><a href="/Test/personal.html">主页</a> <span class="divider">/</span></li>
            <li class="active">学生</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
            <div class="row-fluid">
                <div class="btn-toolbar">
                    <a href="/Test/addCourseStudent.html?course.courseid=<s:property value="course.courseid"/>" role="button" class="btn btn-primary" >
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
                
            </div>
            <s:if test="pageModel != null && pageModel.list.size() > 0">
                <div class="well">
                    <ul class="thumbnails">
                     <div class="page-header" ></div>  
                     <div class="row-fluid">  
                   <!--  <c:forEach items="list" var="menu" begin="0"  end="3"> -->
                    
                        <s:iterator value="pageModel.list" >         
                        <li class="span3">
                            <div class="thumbnail">
                                <img src="/Test<s:property value="imagepath"/>" alt="" width="220" height="170">
                                <h3>学号:<s:property value="studentid"/> 姓名:<s:property value="studentname"/></h3>                         
                               <%--  <a href="/Test/menu.do?flag=menuPre&id=<s:property value="studentid"/>" class="btn btn-primary"> 预 览</a> --%>
                                <a  class="btn btn-info" href="/Test/studentMessage.html?student.studentid=<s:property value="studentid"/>&courseid=<s:property value="course.courseid"/>">
                                <i class="icon-edit icon-white"></i>修改
                                </a>
              					<button  onclick="del(this)" courseid="<s:property value="map.courseid"/>"  delid="<s:property value="studentid"/>" pNow="<s:property value="pageModel.pageNo"/>" class="btn btn-danger">
              					<i class="icon-trash icon-white"></i> 删除
              					</button>
                     
                            </div>
                        </li>
                        </s:iterator>
                      <!--   </c:forEach> -->
                        </div>
<%--                         <div class="page-header" ></div>   
                           <div class="row-fluid">  
                       <s:iterator value="list" begin="4"  end="6"> 
                                    
                        <li class="span3">
                            <div class="thumbnail">
                                 <img src="/Test<s:property value="imagepath"/>" alt="" width="220" height="170">
                                <h3><s:property value="studentname"/></h3>                             
                                <a href="/Test/menu.do?flag=menuPre&id=<s:property value="studentid"/>" class="btn btn-primary"> 预 览</a>
                                <a  class="btn btn-info" href="/WirelessOrder/menu.do?flag=menuEdit&id=${menu.id}">
                                <i class="icon-edit icon-white"></i>修改
                                </a>
              					<button  onclick="del(this)"  delid="<s:property value="studentid"/>" pNow="${pNow}" class="btn btn-danger">
              					<i class="icon-trash icon-white"></i> 删除
              					</button>
                     
                            </div>
                        </li>
                        </s:iterator>
                        </div> --%>
                    </ul>
                </div>
        <div class="pagination">
      <ul>
   
       	<s:url var="first">
		<s:param name="pageNo" value="1"></s:param>
		<s:param name="course.courseid" value="course.courseid"></s:param>
	</s:url>
	<s:url var="previous">
		<s:param name="pageNo" value="pageModel.pageNo-1"></s:param>
		<s:param name="course.courseid" value="course.courseid"></s:param>
	</s:url>
	<s:url var="last">
		<s:param name="pageNo" value="pageModel.bottomPageNo"></s:param>
		<s:param name="course.courseid" value="course.courseid"></s:param>
	</s:url>
	<s:url var="next">
		<s:param name="pageNo" value="pageModel.pageNo+1"></s:param>
		<s:param name="course.courseid" value="course.courseid"></s:param>
	</s:url>
	<s:include value="/WEB-INF/pages/common/page.jsp"></s:include>
    </ul>  
    </div>  
	</s:if>
<!-- 			<footer>
			<hr>
			<p class="pull-right">
				&copy; 2013.8 <a href="#" target="_blank"> shun_fzll</a>
			</p>
			</footer> -->
            </div>
        </div>
    </div>




			<script type="text/javascript">
				function del(obj) {
					var delid = $(obj).attr("delid");
					var pNow = $(obj).attr("pNow");
					var courseid=$(obj).attr("courseid");
					var delurl = "/Test/deleteCourseStudent.html?course.courseid="+courseid+"&studentid="
							+ delid + "&pageNo=" + pNow;
					$("#myModal").modal("show").on("shown", function() {
						$("#delbtn2").attr('href', delurl);
					});
				}
			</script>
  </body>
</html>

