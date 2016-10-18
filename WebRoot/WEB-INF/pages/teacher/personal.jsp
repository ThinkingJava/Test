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
		<li class="active">个人中心</li>
	</ul>

	<div class="container-fluid">
            <div class="row-fluid">
               	<s:if test="map.status==1">
   			 <div class="alert alert-info">
       			 <button type="button" class="close" data-dismiss="alert">×</button>
      			  <strong>小提示：</strong> 密码更改成功！
  			  </div>
			      </s:if>
			 <div class="page-header" ></div>
        <form id="tabfrom" class="form-signin" action="/Test/updateTeacherMessage.html" enctype="multipart/form-data" method="post">         
			<div class="btn-toolbar">
    			<button id="submit" class="btn btn-primary"><i class="icon-save"></i> 保存</button>
			</div>
		<div class="well">
    <ul class="nav nav-tabs">
      <li class="active"><a href="#home" data-toggle="tab">基本资料</a></li>
      <li><a href="#profile" data-toggle="tab">密码更改</a></li>
    </ul>
    <div id="myTabContent" class="tab-content">
      <div class="tab-pane active in" id="home">
      <input type="hidden" id="sex" name="teacher.sex" value="<s:property value="#session.teacher.sex"/>">
      <input type="hidden"  name="teacher.imagepath" value="<s:property value="#session.teacher.imagepath"/>">      
        <table>
         <tr> 
        <td><label>用户id</label></td>
       <td> <input name="teacher.teacherid" type="text" value="<s:property value="#session.teacher.teacherid"/>" class="input-xlarge uneditable-input"></td>
        </tr>
        <tr>
       <td> <label>用户名</label></td>
       <td> <input name="teacher.teachername"  type="text" value="<s:property value="#session.teacher.teachername"/>" class="input-xlarge"> </td>
         </tr>
         <tr>
       <td> <label>性别</label></td>
         <td>    <s:if test="#session.teacher.sex==1">
            	<div id="teacher.sex"  class="make-switch" data-on-label="男" data-off-label="女" >
            	 <input  type="checkbox" checked >
          		 </div>
             	</s:if>
             	<s:if test="#session.teacher.sex==0">
            	<div id="teacher.sex"  class="make-switch" data-on-label="男" data-off-label="女" >
             	<input  type="checkbox" >
           		</div>
            	 </s:if>
           </td>
         </tr>
         <tr>
       <td> <label>部门</label></td>
        <td><input name="teacher.department"  type="text" value="<s:property value="#session.teacher.department"/>" class="input-xlarge"></td>
        </tr>
        <tr>
        <td><label>备注</label></td>
        <td><input name="remark"  type="text" value="<s:property value="#session.teacher.imagepath"/>" class="input-xlarge"></td>
        </tr>
     </table>
     				<div class="span5">
					<div class="fileupload fileupload-new" data-provides="fileupload">
					  <div class="fileupload-new thumbnail" style="width: 220px; height: 170px;"><img src="/Test<s:property value="#session.teacher.imagepath"/>" /></div>
					  <div class="fileupload-preview fileupload-exists thumbnail" style="width: 220px; height: 170px;"></div>
					  <div>
					  <span class="btn btn-file"><span class="fileupload-new">修改图片</span><span class="fileupload-exists">更改</span>
					  <input type="file"  name="photo" value="" />
					 <%--  <s:file id="file" name="photo"></s:file> --%>
					  </span>
					  <a href="#" class="btn fileupload-exists" data-dismiss="fileupload">删除</a>
					  </div>
					</div>
					
               </div>
    </form>
      </div>
      <div class="tab-pane fade" id="profile">
    <form id="tab2" action="/Test/updatePassword.html" method="post">
    	<input type="hidden" name="login.teacherid" value="<s:property value="#session.teacher.teacherid"/>">
        <label>新密码</label>
        <input name="login.password" type="password" class="input-xlarge">
        <div>
            <button class="btn btn-primary">更改</button>
        </div>
    </form>
      </div>
  </div>

</div>
                    
                  <footer>
                        <hr>
                        <p class="pull-right">&copy; 2013.8 <a href="#" target="_blank"> shun_fzll</a></p>
                    </footer>
                    
            </div>
        </div>
    </div>
    <script type="text/javascript">

        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
        $("#submit").click(function(){
          $("#tabfrom").submit();
        });
        
        $('.make-switch').on('switch-change', function (e, data){
        
             if($("#sex").val()==1){
            	 $("#sex").val("0");
             }else{
            	 $("#sex").val("1");	
             }
        	})
        
        	
    </script>
  </body>
</html>
