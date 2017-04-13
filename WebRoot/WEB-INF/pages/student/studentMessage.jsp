<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="context_path"
	value="#request.get('javax.servlet.forward.context_path')"></s:set> --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
  <head>
  <%@include file="/WEB-INF/pages/common/head.jsp"%>
    
    <link rel="stylesheet" type="text/css" href="/Test/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/Test/bootstrap/css/bootstrap-select.css">
    <link rel="stylesheet" type="text/css" href="/Test/css/theme.css">
    <link rel="stylesheet" href="/Test/font-awesome/css/font-awesome.css">
    
    <script src="/Test/bootstrap/js/bootstrap.js"></script>
    <script src="/Test/bootstrap/js/bootstrap-select.js"></script>
    <script src="/Test/bootstrap/js/bootstrap-file-input.js"></script>
   
    <link rel="stylesheet" href="/Test/bootstrap/css/bootstrap-switch.css" />
    <script src="/Test/bootstrap/js/bootstrap-switch.js"></script>

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
  
  
  
    <!-- 内容 开始 -->
<div class="content">
        
        <div class="header">
            <h1 class="page-title">学生</h1>
        </div>
        
                <ul class="breadcrumb">
            <li><a href="/Test/personal.html">主页</a> <span class="divider">/</span></li>
            <li class="active">学生</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
            	<s:if test="map.status==1">
				    <div class="alert alert-info">
				        <button type="button" class="close" data-dismiss="alert">×</button>
				        <strong>小提示：</strong> 保存成功！！
				    </div>
			      </s:if>
			      <s:if test="map.status==3">
				    <div class="alert alert-error">
				        <button type="button" class="close" data-dismiss="alert">×</button>
				        <strong>小提示：</strong>  <s:property value="map.message"/>！！
				    </div>
			      </s:if>
            <div class="page-header" >
            	<a href="/Test/teacherCourse.html?course.courseid=<s:property value="map.courseid"/>">
				<input type="button" class="btn" value="返  回">
				  </a>
            </div>
            <form action="/Test/updateStudent.html?courseid=<s:property value="map.courseid"/>" method="post" class="form-horizontal"  enctype="multipart/form-data" >    
            <div class="row-fluid">
               <div class="span7">  	
               <input name="student.studentid"  id="student.studentid" type="hidden" value="<s:property value="student.studentid"/>">
               <input type="hidden" id="sex" name="student.sex" value="<s:property value="student.sex"/>">
               <input type="hidden" id="major" name="student.major.majorid" value="<s:property value="student.major.majorid"/>">
               <input type="hidden" id="datatime" name="student.datatime" value="<s:property value="student.datatime"/>">
               <input type="hidden" id="imagepath" name="student.imagepath" value="<s:property value="student.imagepath"/>">
					<div class="control-group">
							<label class="control-label" for="names">名字</label>
							<div class="controls">
							<input name="student.studentname" class="input-xlarge focused" id="names" type="text" value="<s:property value="student.studentname"/>">
							</div>
					</div>
					<div class="control-group">
							<label class="control-label" for="price">学分</label>
							<div class="controls">
							<input name="student.score" class="input-xlarge focused" id="price" type="text" value="<s:property value="student.score"/>">
							</div>
					</div>
					<div class="control-group">
							<label class="control-label" for="price">性别</label>
							<div class="controls">
								<table>
									<tr>
									<td>
										<s:if test="student.sex==1">
											<div id="student.sex" class="make-switch" data-on-label="男"
												data-off-label="女">
												<input type="checkbox" checked>
											</div>
										</s:if>
										<s:if test="student.sex==0">
											<div id="student.sex" class="make-switch" data-on-label="男"
												data-off-label="女">
												<input type="checkbox">
											</div>
										</s:if>
										</td>
									</tr>
								</table>
							</div>
					</div>
					<div class="control-group">
					<label class="control-label" >专业</label>
					<div class="controls">
					<select id="mySelect" name="menutypes"  class="selectpicker show-tick" >
						 <s:iterator value="map.major" > 
						<s:if test="majorid==student.major.majorid">
						<option  value="<s:property value="majorid"/>"  selected="selected"><s:property value="majorname"/></option>
						</s:if>
  						<s:if test="majorid!=student.major.majorid">
						<option  value="<s:property value="majorid"/>" ><s:property value="majorname"/></option>
						</s:if>
				
  						</s:iterator>
					</select>

					</div>
					</div>

					<div class="control-group">
							<label class="control-label" for="remark">备 注</label>
							<div class="controls">
							<textarea rows="4" cols="20" name="student.remark" id="remark"><s:property value="student.remark"/></textarea>
							</div>
					</div>
					
               </div>
				<div class="span5">
					<div class="fileupload fileupload-new" data-provides="fileupload">
					  <div class="fileupload-new thumbnail" style="width: 220px; height: 170px;"><img src="/Test<s:property value="student.imagepath"/>" /></div>
					  <div class="fileupload-preview fileupload-exists thumbnail" style="width: 220px; height: 170px;"></div>
					  <div>
					  <span class="btn btn-file"><span class="fileupload-new">修改图片</span><span class="fileupload-exists">更改</span>
					  <input type="file" name="photo" value="/Test<s:property value="student.imagepath"/>" />
					  </span>
					  <a href="#" class="btn fileupload-exists" data-dismiss="fileupload">删除</a>
					  </div>
					</div>
					
               </div>
               </div>
               <div class="form-actions">
								<button type="submit" class="btn btn-primary">保  存</button>
<!-- 								<a href="/WirelessOrder/menu.do?flag=menu">
								<input type="button" class="btn" value="返  回">
							  </a> -->
				</div>
               </form>
			<!-- <footer>
			<hr>
			<p class="pull-right">
				&copy; 2013.8 <a href="#" target="_blank"> shun_fzll</a>
			</p>
			</footer> -->
            </div>
        </div>
    </div>




			<script type="text/javascript">
/* 			$('.selectpicker').selectpicker({
			      style: 'btn-info',
			      size: 4
			  }); */
			  
			  $('.make-switch').on('switch-change', function (e, data){
			        
		             if($("#sex").val()==1){
		            	 $("#sex").val("0");
		             }else{
		            	 $("#sex").val("1");	
		             }
		        	})
			  
			$(document).ready(function(){ 
			$('#mySelect').change(function(){ 
			//alert($(this).children('option:selected').val()); 
			var p1=$(this).children('option:selected').val();//这就是selected的值 
			//改变隐藏域中的值
			$("#major").val(p1);
			//var p2=$('#menutype').val();
			//alert(p2)
			}) 
			}) 	
			$('input[type=file]').bootstrapFileInput();
		$('.file-inputs').bootstrapFileInput();	
		
	   
			</script>
  </body>
</html>