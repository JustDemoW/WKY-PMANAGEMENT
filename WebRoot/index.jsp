<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>MyManage</title>
	<meta http-equiv="pragma" content="manage">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="library/resources/css/ext-all.css" />
	<link rel="stylesheet" href="css/main.css?v=2">
	<link rel="stylesheet" href="css/jbox/jbox.css">
	<link rel="stylesheet" href="css/login/loginStyle.css">
	<link rel="stylesheet" href="css/icon.css">
	<link rel="stylesheet" type="text/css" href="library/ux/css/LiveSearchGridPanel.css" />
	<link rel="stylesheet" type="text/css" href="library/ux/statusbar/css/statusbar.css" />
	<script>!window.jQuery && document.write(unescape('%3Cscript src="library/jquery-1.7.1.min.js"%3E%3C/script%3E'))</script>
	<script type="text/javascript" src="js/main/menu.js"></script>
	<script type="text/javascript" src="library/ux/jquery.jBox.src.js"></script>
	<script type="text/javascript" src="library/ext-all.js"></script>
	<script type="text/javascript" src="library/ext-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/common/system.js"></script>
	<script type="text/javascript" src="library/ux/statusbar/StatusBar.js"></script>
	<script type="text/javascript" src="library/ux/LiveSearchGridPanel.js"></script>
	<script type="text/javascript" src="library/ux/ProgressBarPager.js"></script>
  </head>
  
  <body>
  <div id="showLogin" ></div>
  <div id="showLogin2" ></div>
    <div id="menu-wrapper">
    <table width="100%" border="0"style="font: bold 13px Verdana;">
    	<tr>
    		<td>
    			<ul class="menu">
					<li> <a href="#">工具</a> </li>
					<li> <a href="#">求上进</a> </li>
					<li> <a href="#">图片/照片</a> </li>
					<li> <a href="#">工作日志</a> </li>
					<li> <a href="#">个人管理</a> </li>
				</ul>
    		</td>
    		 
    		<td align="center" width="370">
    		<div id='login-div' style='height:55px;'>
    			<a id='login-btn' href="javascript:void(0);" class='login-btn'>
    				登录
    			</a>
    			<a id='register-btn' href="javascript:void(0);" class='login-btn'>
    				注册
    			</a>
    		</div>
    		<div id='logout-div' style='height:55px;display:none;'>
    			<img id='user-img' src="images/user-man.jpg" class='user-image'>
    			<span id='user-span' class='user-span'></span>
    			<span class='user-span'>,欢迎回来！</span>
    			<a id='logout-btn' href="javascript:void(0);" class='logout-btn'>
    				退出
    			</a>
    		</div>
<!--    		<div>-->
<!--    			<audio autoplay='autoplay' controls='controls' loop='loop' src='https://i.alipayobjects.com/e/201312/YoungForYou.mp3'></audio>-->
<!--    		</div>-->
    		</td>
    		
    		<td align="center" width="110px">
				<span style="cursor:text;margin-right:20px;color: white;"><p id="nowTime"></p><p id="week">星期二</p></span><span>${moduleId }</span>
				<script type="text/javascript">
					setInterval("document.getElementById('nowTime').innerHTML=new Date().toLocaleString();document.getElementById('week').innerHTML='星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
				</script>
			</td>
    	</tr>
    </table>
	</div>
	<div id="submenu-wrapper">
		<ul class="submenu">
			<li>
				<a href="javascript:void(0);">
					<img src="images/9.jpg" />
					API
				</a>
			</li>
			<li>
				<a href="javascript:void(0);">
					<img src="images/10.jpg" />
					JAVA
				</a>
			</li>
			<li>
				<a href="javascript:void(0);">
					<img src="images/11.jpg" />
					JAVASCRiPT
				</a>
			</li>
			<li>
				<a href="javascript:void(0);">
					<img src="images/12.jpg" />
					EXTJS
				</a>
			</li>
			<li>
				<a href="javascript:void(0);">
					<img src="images/12.jpg" />
					EXTJS
				</a>
			</li>
		</ul>
		<ul class="submenu">
			<li>
				<a href="javascript:void(0);">
					<img src="images/13.jpg" />
					DEMO
				</a>
			</li>
			<li>
				<a href="javascript:void(0);">
					<img src="images/14.jpg" />
					书籍
				</a>
			</li>
			<li>
				<a href="javascript:void(0);">
					<img src="images/15.jpg" />
					视频
				</a>
			</li>
			<li>
				<a href="javascript:void(0);">
					<img src="images/16.jpg" />
					MORE...
				</a>
			</li>
		</ul>
		<ul class="submenu">
			<li>
				<a href="javascript:void(0);">
					<img src="images/5.jpg" />
					ME
				</a>
			</li>
			<li>
				<a href="javascript:void(0);">
					<img src="images/6.jpg" />
					LianQingqing
				</a>
			</li>
			<li>
				<a href="javascript:void(0);">
					<img src="images/7.jpg" />
					BOBY
				</a>
			</li>
			<li>
				<a href="javascript:void(0);">
					<img src="images/8.jpg" />
					MORE...
				</a>
			</li>
		</ul>
		<ul class="submenu">
			<li>
				<a href="javascript:void(0);">
					<img src="images/1.jpg" />
					工作日志
				</a>
			</li>
		</ul>
		<ul class="submenu">
			<li>
				<a href="javascript:void(0);onclick=loadJs('system-myAccount')">
					<img src="images/1.jpg" />
					帐号管理
				</a>
			</li>
			<li>
				<a href="javascript:void(0);onclick=loadJs('system-myConsumption')">
					<img src="images/1.jpg" />
					记账管理
				</a>
			</li>
			<li>
				<a href="javascript:void(0);">
					<img src="images/1.jpg" />
					卡号管理
				</a>
			</li>
			<li>
				<a href="javascript:void(0);">
					<img src="images/1.jpg" />
					提醒管理
				</a>
			</li>
		</ul>
	</div>
	<div id="leftDivForMenu"></div>
	<div id="topDivForDate">
		<marquee scrollAmount=3 align="middle"style="color:#ffffff;font-size:15px;">欢迎您的使用！</marquee>
	</div>
	<div id='mainView'>	
<!--	<s:if test="moduleId!=null">-->
		
<!--	<s:if>-->
	</div>
	<div id="rightDivForMenu"></div>
<!--	<script type="text/javascript">-->
<!--		var width = document.documentElement.clientWidth;-->
<!--		var height = document.documentElement.clientHeight;-->
<!--		document.getElementById('mainView').style.height=(height-document-->
<!--		.getElementById('menu-wrapper').offsetHeight-document-->
<!--		.getElementById('topDivForDate').offsetHeight-2)+'px';-->
<!--		document.getElementById('mainView').style.width=(width-(document-->
<!--		.getElementById('leftDivForMenu').offsetWidth-10)*2-20)+'px';-->
<!--		document.getElementById('topDivForDate').style.width=(width-(document-->
<!--		.getElementById('leftDivForMenu').offsetWidth-10)*2-20)+'px';-->
<!--		document.getElementById('leftDivForMenu').style.height=(height-document-->
<!--		.getElementById('menu-wrapper').offsetHeight-2)+'px';-->
<!--		document.getElementById('rightDivForMenu').style.height=(height-document-->
<!--		.getElementById('menu-wrapper').offsetHeight-2)+'px';-->
<!--		AppWky.eval("Ext.create('Ext.Window',{renderTo:'mainView',title:'hello',width:'100%',height:'100%',maximizable:true}).show()");-->
<!--		</script>-->
	<!-- GA 
	<script type="text/javascript">
		var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
		document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
	</script>
	<script type="text/javascript">
		try {
			var pageTracker = _gat._getTracker("UA-2260508-2");
			pageTracker._trackPageview();
		} catch(err) {}</script>
	 GA -->
  </body>
</html>
