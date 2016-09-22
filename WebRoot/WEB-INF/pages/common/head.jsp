<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="context_path"
	value="#request.get('javax.servlet.forward.context_path')"></s:set>
<link href="${context_path}/css/style_index.css" rel="stylesheet"type="text/css" />
<link href="${context_path}/css/css/lrtk.css" rel="stylesheet"type="text/css" />
<style>
	td {
		font-size: 12px;
	}
	.menus { 
			height: 45px;
			display: block;
		}

		.menus ul {
			list-style: none;
			padding: 0;
			margin: 0;
		}

		.menus ul li {
			/* width and height of the menu items */  
			float: left;
			overflow: hidden;
			position: relative;
			text-align: center;
			line-height: 45px;
		}

		.menus ul li a {
			/* 必须使用相对位置，relative  */ 
			position: relative;
			display: block;
			width: 110px;
			height: 45px;
			font-family: 微软雅黑;
			font-size: 16px;
			font-weight: bold;
			letter-spacing: 1px;
			text-transform: uppercase;
			text-decoration: none;
			cursor: pointer;
		}

		.menus ul li a span {
			/* all layers will be absolute positioned */
			position: absolute;
			left: 0;
			width: 110px;
		}

		.menus ul li a span.out {
			top: 0px;
		}

		.menus ul li a span.over,
		.menus ul li a span.bg {
			/* hide */  
			top: -45px;
		}

		/** 1st example **/

		#menus {
			background: #EEE;
		}

		#menus ul li a {
			color: #000;
		}

		#menus ul li a span.over {
			color: #FFF;
		}

		#menus ul li span.bg {
			/* 背景层使用背景图bg_over.gif，高45px */  
			height: 45px;
			background: url('${context_path}/css/images/bg_over.gif') center center no-repeat;
		}

/* .actGotop{position:fixed; _position:absolute; bottom:100px; right:50px; width:150px; height:195px; display:none;}
.actGotop a,.actGotop a:link{width:150px;height:195px;display:inline-block; background:url(${context_path}/css/images/gotop.png) no-repeat; _background:url(${context_path}/css/images/gotop.gif) no-repeat; outline:none;}
.actGotop a:hover{width:150px; height:195px; background:url(${context_path}/css/images/gotopd.gif) no-repeat; outline:none;} */


</style>


    <script type="text/javascript" src="${context_path}/js/jquery.js"></script>
     <script type="text/javascript" src="${context_path}/js/jquery.min.js"></script>
    <script language="javascript">
		$(document).ready(function() {
			/*第1个菜单*/
			/// wrap inner content of each anchor with first layer and append background layer
			$("#menus li a").wrapInner( '<span class="out"></span>' ).append( '<span class="bg"></span>' );

			// 每个a元素后追加class="over"的span元素
			$("#menus li a").each(function() {
				$( '<span class="over">' +  $(this).text() + '</span>' ).appendTo( this );
			});

			$("#menus li a").hover(function() {
				// 当鼠标移入时调用此函数
				$(".out",	this).stop().animate({'top':	'45px'},	250); //out层动画下移至45x（隐藏）
				$(".over",	this).stop().animate({'top':	'0px'},		250); // over层动画下移至0x（显示）
				$(".bg",	this).stop().animate({'top':	'0px'},		120); // 背景层动画下移至0x（显示）

			}, function() {
				// 当鼠标移出时调用此函数
				$(".out",	this).stop().animate({'top':	'0px'},		250); // out层动画上移至0x（显示）
				$(".over",	this).stop().animate({'top':	'-45px'},	250); //over层动画上移至-45x（隐藏）
				$(".bg",	this).stop().animate({'top':	'-45px'},	120); //背景层动画上移至-45x（隐藏）
			});
					
		});

	</script>


<div id="box">
<div id="dark"><s:a action="cart_list" namespace="/product">
	<img src="${context_path}/css/images/index_03.gif" width="28"
		height="14" /> 我的购物车</s:a> | <s:a action="order_findByCustomer"
	namespace="/product">我的订单</s:a>　
<s:if test="#session.customer != null">
	欢迎 　<s:property value="#session.customer.username"/>　

	  <img src="${context_path}/css/images/vip_icon<s:property value="#session.customer.state"/>.png"  width="15px" height="15px"/>

	<s:a action="customer_logout" namespace="/customer">退出</s:a>
</s:if>
</div>
<div id="logo">
<s:form action="product_findByName" method="post" namespace="/product">
<div id="sou">
	<s:textfield cssStyle="border-color:blue" name="name"></s:textfield><br>
	<!-- <input class="input-medium search-query" type="text" /> --> 
	<div style="margin-top: 5px;">
		<b>热搜商品：</b>
		<s:a action="product_getByCategoryId" namespace="/product">
			<s:param name="category.id" value="'330'"></s:param>
			软件
		</s:a>
	</div>
</div>
<div id="sou_zi">
	<s:submit type="image" src="%{context_path}/css/images/search.gif" width="75px" value="搜索"></s:submit>
</div>
</s:form>
<div id="sou_zi01">高级搜索<br />

使用帮助</div>
</div>
<div id="menu">
	<div id="menus" class="menus">
	<ul>
		<li><s:a  action="index" namespace="/">首页</s:a></li>
		<li><s:a  action="product_findNewProduct" namespace="/product">新品上市</s:a></li>
		<li><s:a  action="product_findSellProduct" namespace="/product">热销商品</s:a></li>
		<li><s:a  action="product_findCommendProduct" namespace="/product">推荐商品</s:a></li>
		<li><s:a  action="product_findEnjoyProduct" namespace="/product">人气商品</s:a></li>
		<%-- <img src="${context_path}/css/images/index_19.gif" width="144" height="33" id="z300" /> --%>
	</ul>
	</div> 
</div>
</div>

    <p style="margin:20px 0"></p>
</div>
    <div class="left_nav" id="left_nav">
    	<a  target="_blank" style="height:117px"></a>
        <a  target="_blank" style="height:116px"></a>
        <a  target="_blank" style="height:68px"></a>
        <a  target="_blank" style="height:68px"></a>
        <a  target="_blank" style="height:68px"></a>
        <a  target="_blank" style="height:68px"></a>
    </div>
    
    <div class="float" id="float">	
        <a  class="an_1">导航1</a>
        <a  class="an_2">导航2</a>
        <a  class="an_3">导航3</a>
        <a  class="an_4">导航4</a>
        <a  class="an_5">导航5</a>
        <a  class="an_6">导航6</a>
        <a  class="an_7">导航7</a>
        <a  class="an_8">导航8</a>
        <a  class="an_9">导航9</a>
        <a  class="an_10">导航10</a>
        <a  class="an_11">导航11</a>
        <a href="#top" class="an_12">导航12</a>
    </div>

<script type="text/javascript">
//浮动导航
function float_nav(dom){
	var right_nav=$(dom);
	var nav_height=right_nav.height();
	function right_nav_position(bool){
		var window_height=$(window).height();
		var nav_top=(window_height-nav_height)/2;
		if(bool){
			right_nav.stop(true,false).animate({top:nav_top+$(window).scrollTop()},400);
		}else{
			right_nav.stop(true,false).animate({top:nav_top},300);
		}	
		right_nav.show();
	}
	
	if(!+'\v1' && !window.XMLHttpRequest ){
		$(window).bind('scroll resize',function(){
			if($(window).scrollTop()>300){
				right_nav_position(true);			
			}else{
				right_nav.hide();	
			}
		})
	}else{
		$(window).bind('scroll resize',function(){
			if($(window).scrollTop()>300){
				right_nav_position();
			}else{
				right_nav.hide();
			}
		})
	}	
}
float_nav('#float');
float_nav('#left_nav');
</script>
<!-- 代码 结束 -->


