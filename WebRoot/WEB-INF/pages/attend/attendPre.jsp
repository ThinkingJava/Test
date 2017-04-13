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
  

    <link rel="stylesheet" type="text/css" href="/Test/bootstrap/css/bootstrap-select.css">
    <link rel="stylesheet" type="text/css" href="/Test/css/theme.css">
    <link rel="stylesheet" href="/Test/font-awesome/css/font-awesome.css">
    
    <script src="/Test/bootstrap/js/bootstrap.js"></script>
    <script src="/Test/bootstrap/js/bootstrap-select.js"></script>
    <script src="/Test/bootstrap/js/bootstrap-file-input.js"></script>

   <link rel="stylesheet" href="/Test/bootstrap/css/bootstrap-switch.css" />
   <script src="/Test/bootstrap/js/bootstrap-switch.js"></script>
    


  
  <body>
  <div class="content">
        
        <div class="header">
            <h1 class="page-title">考勤</h1>
        </div>
        
                <ul class="breadcrumb">
            <li><a href="/Test/personal.html">主页</a> <span class="divider">/</span></li>
            <li class="active">添加</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
			    	<s:if test="map.status==5">
				    <div class="alert alert-error">
				        <button type="button" class="close" data-dismiss="alert">×</button>
				        <strong>小提示：</strong> <s:property value="map.message" />
				    </div>
			      </s:if>
            <div class="page-header" ></div>
         
            
            <input id="status" type="hidden"  name="attend.status" value="1">
            <input id="courseid" type="hidden"  name="attend.course.courseid" value="">
              
            <div class="row-fluid">
               <div class="span7">  	
					<div class="control-group">
							<label class="control-label" for="name">学号</label>
							<div class="controls">
							<!-- <input name="attend.student.studentid" class="input-xlarge focused" id="name" type="text" value=""> -->
							<span class="label label-success"><s:property value="attend.student.studentid" /></span>
							</div>
					</div>

					<div class="control-group">
					<label class="control-label" >课程</label>
					<div class="controls">
<%-- 					<select id="mySelect" name="menutypes"  class="selectpicker show-tick"  data-size="4" >
						<s:iterator value="#session.teacher.courses">
  						<option  value="<s:property value="courseid" />" ><s:property value="coursename" /></option>
  						</s:iterator>
  						
					</select> --%>
					<span class="label label-success"><s:property value="attend.course.coursename" /></span>
					</div>
					</div>
					
					
					<div class="control-group">
					<table>
					<tr>
					<td><label class="control-label" >状态</label>	
							<div class="controls">
<!-- 									<div id="status" class="make-switch" data-on-label="已签到" data-off-label="待签到">
										<input type="checkbox" >
									</div> -->
				<s:if test="attend.status==1">
            	<span class="label label-success">已签到</span>
          		 </div>
             	</s:if>
             	<s:if test="attend.status==0">
            	<span class="label label-success">未签到</span>
           		</div>
            	 </s:if>
							</div>
					    	</td>
					</tr>	
						</table> 		   
					</div>
				
					
					
					
               </div>
				<div class="span5">
					<div class="fileupload fileupload-new" data-provides="fileupload">
					
 					  <div class="fileupload-preview  thumbnail" style="width: 220px; height: 170px;">
 					  <span>学生头像</span>
 					  <img src="/Test/<s:property value="attend.student.imagepath" />"/>
 					  </div>
 					  
<!-- 					  <div>
					  <span class="btn btn-file"><span class="fileupload-new">上传图片</span><span class="fileupload-exists">更改</span>
					  <input type="file" name="photo" />
					  </span>
					  <a href="#" class="btn fileupload-exists" data-dismiss="fileupload">删除</a>
					  </div> -->
					  
					    <div class="fileupload-preview  thumbnail" style="width: 220px; height: 170px;">
					    <span>考勤头像</span>
                       <img src="/Test/<s:property value="attend.imagepath" />"/>
                       </div>
					</div>
					

               </div>
               				
               </div>
               <div class="form-actions">
							<!-- 	<button type="submit" class="btn btn-primary">添 加</button> -->
								<a href="/Test/attendMessage.html?attend.course.courseid=<s:property value="attend.course.courseid" />">
								<button  class="btn">返 回</button>
							  </a>
				</div>
            
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
			
			 $('.make-switch').on('switch-change', function (e, data){
			        alert("状态更改");
	             if($("#status").val()==1){
	            	 $("#status").val("0");
	             }else{
	            	 $("#status").val("1");	
	             }
	        	})
			  
			$(document).ready(function(){ 
			$('#mySelect').change(function(){ 
			//alert($(this).children('option:selected').val()); 
			var p1=$(this).children('option:selected').val();//这就是selected的值 
			//改变隐藏域中的值
			$('#courseid').val(p1);
			//var p2=$('#menutype').val();
			//alert(p2)
			}) 
			}) 	
			$('input[type=file]').bootstrapFileInput();
		$('.file-inputs').bootstrapFileInput();		
			</script>
  </body>
</html>
  
  </body>
</html>
