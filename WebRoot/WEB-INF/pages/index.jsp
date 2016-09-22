<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<STYLE type="text/css">


#container{
				margin: 0 auto;
				padding:0;
				width: 960px;
				border: 10px solid #303030;
				border-radius: 5px 5px 5px 5px;
				background: #000;
				box-shadow: 0px 0px 30px #2B99E6;
			}
			
			#imgwrapper{
				width: 460px;
				float: left;
				text-align: center;
				padding:0;
				margin:0;
			}
			
			#knobwrapper{
				width: 300px;
				float: right;
				text-align: center;
			}
			
			#img{
				margin: 0 auto;
				width: 500px;
				border-radius: 5px 5px 5px 5px;
			}
			
			.clear{
				clear:both;
			}
			
			
			
			

</STYLE>
<SCRIPT type="text/javascript">
	if (self != top) {
		top.location = self.location;
	}
</SCRIPT>
</head>
<body>
<%@include file="/WEB-INF/pages/common/head.jsp"%>

<div id="box">
<div id="left">
<%-- <div id="left_s01">
<s:a action="customer_login" namespace="/customer"><img src="${context_path}/css/images/index_23.gif" class="imgx5" /></s:a>
<s:a action="customer_reg" namespace="/customer"><img src="${context_path}/css/images/index_26.gif" class="imgx5" /></s:a>
<img src="${context_path}/css/images/index_27.gif" />
</div> --%>
<div id="left_s02">
<img src="${context_path}/css/images/index_25.gif" width="680" height="267" class="imgz5" />
</div>
<!-- 类别 -->
<s:iterator value="categories">
<div id="left_x">
<div id="left122">
<table style="float: left;height: auto;width: 678px; vertical-align: middle; " class="box">
	<tr>
		<td class="word14" style="width: 30px; padding-left: 10px;">
           <s:property value="name"/>
       </td>
	</tr>
    <tr>       
        <td style="padding-bottom: 6px;"> 
			<div id="left122_y" class="box">
				<!-- 二级 -->
				<s:if test="!children.isEmpty" >
					<s:iterator value="children">
						<div style="white-space:nowrap; width: 28%;float: left; margin-top: 5px; margin-bottom: 5px; margin-left: 26px;">
							<b style="color: #999000;"><s:property value="name" escape="false"/></b>　
							<!-- 三级 -->
							<s:if test="!children.isEmpty">
								<span>
								<s:iterator value="children">
									<s:a action="product_getByCategoryId" namespace="/product">
										<s:param name="category.id" value="id"></s:param>
										<s:property value="name" escape="false"/>
									</s:a>
								</s:iterator>
								</span>
							</s:if>
						</div>
					</s:iterator>
				</s:if>
			</div>
       </td>
    </tr>
</table>
</div>
</div>
</s:iterator>
</div>
<div id="right">
<!-- 商品排行 -->
<div id="rqpgb">

<s:a action="customer_login" namespace="/customer"><img src="${context_path}/css/images/index_23.gif" class="imgx5" /></s:a>
<s:a action="customer_reg" namespace="/customer"><img src="${context_path}/css/images/index_26.gif" class="imgx5" /></s:a>
<img src="${context_path}/css/images/index_27.gif" />

<%-- <table width="195" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="195" height="31"><img
			src="${context_path}/css/images/index_28.gif" width="195" height="29" /></td>
	</tr>
	<tr>
		<td height="5"></td>
	</tr>
	<tr>
		<td valign="top">
			<s:action name="product_findByClick" namespace="/product" executeResult="true"></s:action>
		</td>
	</tr>
</table>
</div>
<!-- 推荐商品 -->
<div id="xpss">
<table width="195" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="195" height="31"><img
			src="${context_path}/css/images/08.gif" width="195" height="29" /></td>
	</tr>
	<tr>
		<td height="5"></td>
	</tr>
	<tr>
		<td valign="top">
			<s:action name="product_findByCommend" namespace="/product" executeResult="true"></s:action>
		</td>
	</tr>
</table>
</div>
<!-- 热销商品 -->
<div id="rxsp">
<table width="195" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="195" height="31"><img
			src="${context_path}/css/images/index_47.gif" width="195" height="29" /></td>
	</tr>
	<tr>
		<td height="5"></td>
	</tr>
	<tr>
		<td valign="top">
		<s:action name="product_findBySellCount" namespace="/product" executeResult="true"></s:action>
		</td>
	</tr>
</table> --%>
</div>
<div id="sckf"></div>
</div>
<!-- <div id="foot"></div>
</div> -->

</body>
</html>