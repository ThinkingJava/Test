<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/head.jsp"%>
<div id="box">
<div id="mid">
    <div id="zhuce">
	<s:form action="order_confirm" namespace="/product" method="post">


	<table width="80%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="2">
			<s:fielderror></s:fielderror>
			</td>
		</tr>
 		<tr>
			<td width="70" height="35" align="right">收货人姓名：</td>
			<td>
				<%-- <s:textfield name="name" cssClass="bian"></s:textfield> --%>
				<input name="name" type="text" value="<s:property value="name"/>">
			</td>
		</tr> 
		<tr>
			<td width="70" height="35" align="right">详  细  住　址：</td>
			<td>
				<s:textfield name="address" cssClass="bian"></s:textfield>
			</td>
		</tr>
 		<tr>
			<td width="70" height="35" align="right">收  货 人   电    话：</td>
			<td>
				<%-- <s:textfield name="mobile" cssClass="bian"></s:textfield> --%>
				<input name="mobile" type="text" value="<s:property value="mobile"/>">
			</td>
		</tr>
		<tr>
			<td width="70" height="35" align="right">支付方式：</td>
			<td>
				<%-- <s:textfield name="paymentWay" cssClass="bian"></s:textfield> --%>
				<input name="paymentWay" type="text" value="<s:property value="paymentWay"/>">
			</td>
		</tr> 
		<tr>
			<td colspan="2" height="80" valign="middle" align="center">
<%-- 		<s:property value="name"/>
		<s:property value="address"/>
		<s:property value="mobile"/>
		<s:property value="paymentWay"/> --%>
			<s:submit value="更 改" type="submit" ></s:submit>
			</td>
		</tr>
	</table>
	</s:form>
</div>
  </div>
  <!-- <div id="foot"></div> -->
</div>
</body>
</html>