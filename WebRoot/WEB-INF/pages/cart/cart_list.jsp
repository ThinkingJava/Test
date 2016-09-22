<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">

</style>

<title>我的购物车</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/head.jsp"%>
<link rel="stylesheet" type="text/css" href="${context_path}/css/shoppingCart.css">
<script type="text/javascript" src="${context_path}/js/shoppingCart.js"></script>
<s:set value="%{0}" var="sumall"></s:set>
<div id="box">
<div id="wdzh_left">
      <div id="left_list"><s:a action="cart_list" namespace="/product">我的购物车</s:a></div>
	  <div id="left_list"><s:a action="order_findByCustomer" namespace="/product">我的订单</s:a></div>
    </div>
    <div id="wdzh_right">
      <div id="right_tiao">　&gt;　我的购物车</div>
      <div id="biaodan">
       <table id="cartTable"  width="688" border="0" cellpadding="0" cellspacing="0">
  <thead>
 
   <tr class="order_content" >
    <th><input class="check_all check" type="checkbox"></input> 全选</th>
    <th>商品</th>
    <th>单价</th>
    <th>数量</th>
    <th>总价</th>
    <th>操作</th>
   </tr>
 
  </thead>
  <tbody >
    <s:form action="order_add" namespace="/product" method="post">

    <s:iterator value="#session.cart">
              <tr>
         <td colspan="7" class="pt10 pb10 f14">
          <input class = "check_business check" type="checkbox" name="select1" value="<s:property value="key"/>"> <span class="pl5">商家：</span><s:property value="key"/></input>     
            </tr>
    <s:iterator value="value" >
    <s:set value="%{#sumall +productPrice*amount}" var="sumall"/>
    
   <tr class="order_content" >   
    <td class="check"><input class = "check_one check" type="checkbox" name="select" value="<s:property value="productId"/>"></input></td>
    <td class="goods"><img src="<s:property value="%{context_path}"/>/upload/<s:property value="image"/>" height="50" width="50"> <span><s:property value="productName"/></span></td>
    <td class="price"><s:property value="productPrice"/></td>
    <td class="count">
     <span class="reduce"><s:a action="cart_reduce" namespace="/product" >
							<s:param name="productId" value="productId"></s:param>-</s:a></span>
     <input class="count_input" type="text" name="number" value="<s:property value="amount"/>"></input>
     <span class="add"><s:a action="cart_add" namespace="/product" ><s:param name="productId" value="productId"></s:param>+</s:a></span> 
    </td>
    <td class="subtotle"><s:property value="productPrice*amount"/></td>
    <td class="operation"><span class="delete"> <s:a action="cart_delete" namespace="/product">
					<s:param name="productId" value="productId"></s:param>
					删除
				</s:a></span></td>
   </tr>
   </s:iterator>
</s:iterator>

  </tbody>
 
 </table>

 <div id = "footer" class="footer">  
  <label class="fl select_all" ><input class="check_all check" type="checkbox"> 全选</input></label>
  <a class="fl delete_all" id="deleteAll" href="javascript:;"><s:a action="cart_clear" namespace="/product">删除</s:a></a>
  <div  class="fr closing"> <s:submit type="submit" name="" value="结算" ></s:submit></div>
</s:form>
 
  <div class="fr selected_totle">合计：￥ <span id="priceTotle">0.00</span> </div>
  <div class="fr selectAll" id="selected">已购商品
   <span id = "selectTotle">0</span>件
   <span class="arow up">+++</span>
   <span class="arow down">---</span>
  </div>
 
 
 </div>
 
</body>
</html>