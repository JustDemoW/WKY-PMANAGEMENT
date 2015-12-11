/**
 * Ext入口
 */
var mainDivWidth, mainDivHeight;

Ext.onReady(function() {
			Ext.Ajax.request({
				url : 'system/isLogin',
				method : 'POST',
				success : function(response, options) {
					var result = Ext.JSON.decode(response.responseText);
					if (result.success) {
						document.getElementById('login-div').style.display = "none";
						document.getElementById('logout-div').style.display = "block";
						document.getElementById('user-span').innerHTML = result.userName;
						if(result.userSex=="2"){
							document.getElementById('user-img').src="images/user-women.jpg"
						}
					} 
				},
				failure : function(response, options) {
					Ext.Msg.show({
								icon : Ext.MessageBox.ERROR,
								buttons : Ext.Msg.OK,
								title : '错误',
								msg : '网络有误!'
							});
				}
			});
			initJbox();
			initDiv();
			mainDivWidth = document.getElementById('mainView').offsetWidth;
			mainDivHeight = document.getElementById('mainView').offsetHeight;
			Ext.Loader.setConfig({
						enabled : true
					});
			Ext.require('js/common/app');
		});

/**
 * jBox 全局设置
 */
function initJbox() {
	var _jBoxConfig = {};
	_jBoxConfig.defaults = {
		id : null, /* 在页面中的唯一ID，如果为null则自动为随机ID,一个ID只会显示一个jBox */
		top : '15%', /* 窗口离顶部的距离,可以是百分比或像素(如 '100px') */
		border : 5, /* 窗口的外边框像素大小,必须是0以上的整数 */
		opacity : 0.2, /* 窗口隔离层的透明度,如果设置为0,则不显示隔离层 */
		timeout : 0, /* 窗口显示多少毫秒后自动关闭,如果设置为0,则不自动关闭 */
		showType : 'fade', /* 窗口显示的类型,可选值有:show、fade、slide */
		showSpeed : 'fast', /* 窗口显示的速度,可选值有:'slow'、'fast'、表示毫秒的整数 */
		showIcon : true, /* 是否显示窗口标题的图标，true显示，false不显示，或自定义的CSS样式类名（以为图标为背景） */
		showClose : true, /* 是否显示窗口右上角的关闭按钮 */
		draggable : true, /* 是否可以拖动窗口 */
		dragLimit : true, /* 在可以拖动窗口的情况下，是否限制在可视范围 */
		dragClone : false, /* 在可以拖动窗口的情况下，鼠标按下时窗口是否克隆窗口 */
		persistent : true, /* 在显示隔离层的情况下，点击隔离层时，是否坚持窗口不关闭 */
		showScrolling : true, /* 是否显示浏览的滚动条 */
		ajaxData : {}, /*
						 * 在窗口内容使用post:前缀标识的情况下，ajax post的数据，例如：{ id: 1 } 或
						 * "id=1"
						 */
		iframeScrolling : 'auto', /* 在窗口内容使用iframe:前缀标识的情况下，iframe的scrolling属性值，可选值有：'auto'、'yes'、'no' */

		title : 'jBox', /* 窗口的标题 */
		width : 350, /* 窗口的宽度，值为'auto'或表示像素的整数 */
		height : 'auto', /* 窗口的高度，值为'auto'或表示像素的整数 */
		bottomText : '', /* 窗口的按钮左边的内容，当没有按钮时此设置无效 */
		buttons : {
			'确定' : 'ok'
		}, /* 窗口的按钮 */
		buttonsFocus : 0, /* 表示第几个按钮为默认按钮，索引从0开始 */
		loaded : function(h) {
		}, /* 窗口加载完成后执行的函数，需要注意的是，如果是ajax或iframe也是要等加载完http请求才算窗口加载完成，参数h表示窗口内容的jQuery对象 */
		submit : function(v, h, f) {
			return true;
		}, /* 点击窗口按钮后的回调函数，返回true时表示关闭窗口，参数有三个，v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 */
		closed : function() {
		} /* 窗口关闭后执行的函数 */
	};
	_jBoxConfig.stateDefaults = {
		content : '', /* 状态的内容，不支持前缀标识 */
		buttons : {
			'确定' : 'ok'
		}, /* 状态的按钮 */
		buttonsFocus : 0, /* 表示第几个按钮为默认按钮，索引从0开始 */
		submit : function(v, h, f) {
			return true;
		} /* 点击状态按钮后的回调函数，返回true时表示关闭窗口，参数有三个，v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 */
	};
	_jBoxConfig.tipDefaults = {
		content : '', /* 提示的内容，不支持前缀标识 */
		icon : 'info', /* 提示的图标，可选值有'info'、'success'、'warning'、'error' */
		top : '40%', /* 提示离顶部的距离,可以是百分比或像素(如 '100px') */
		width : 'auto', /* 提示的高度，值为'auto'或表示像素的整数 */
		height : 'auto', /* 提示的高度，值为'auto'或表示像素的整数 */
		opacity : 0, /* 窗口隔离层的透明度,如果设置为0,则不显示隔离层 */
		timeout : 2000, /* 提示显示多少毫秒后自动关闭,必须是大于0的整数 */
		closed : function() {
		} /* 提示关闭后执行的函数 */
	};
	_jBoxConfig.messagerDefaults = {
		content : '', /* 信息的内容，不支持前缀标识 */
		title : 'jBox', /* 信息的标题 */
		icon : 'none', /* 信息图标，值为'none'时为不显示图标，可选值有'none'、'info'、'question'、'success'、'warning'、'error' */
		width : 350, /* 信息的高度，值为'auto'或表示像素的整数 */
		height : 'auto', /* 信息的高度，值为'auto'或表示像素的整数 */
		timeout : 3000, /* 信息显示多少毫秒后自动关闭,如果设置为0,则不自动关闭 */
		showType : 'slide', /* 信息显示的类型,可选值有:show、fade、slide */
		showSpeed : 600, /* 信息显示的速度,可选值有:'slow'、'fast'、表示毫秒的整数 */
		border : 0, /* 信息的外边框像素大小,必须是0以上的整数 */
		buttons : {}, /* 信息的按钮 */
		buttonsFocus : 0, /* 表示第几个按钮为默认按钮，索引从0开始 */
		loaded : function(h) {
		}, /* 窗口加载完成后执行的函数，参数h表示窗口内容的jQuery对象 */
		submit : function(v, h, f) {
			return true;
		}, /* 点击信息按钮后的回调函数，返回true时表示关闭窗口，参数有三个，v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 */
		closed : function() {
		} /* 信息关闭后执行的函数 */
	};
	_jBoxConfig.languageDefaults = {
		close : '关闭', /* 窗口右上角关闭按钮提示 */
		ok : '确定', /* $.jBox.prompt() 系列方法的“确定”按钮文字 */
		yes : '是', /* $.jBox.warning() 方法的“是”按钮文字 */
		no : '否', /* $.jBox.warning() 方法的“否”按钮文字 */
		cancel : '取消' /* $.jBox.confirm() 和 $.jBox.warning() 方法的“取消”按钮文字 */
	};
	$.jBox.setDefaults(_jBoxConfig);
}
/**
 * div控制
 */
function initDiv(menuWidth) {
	menuWidth = menuWidth || 0;
	var width = document.documentElement.clientWidth;
	if (width < menuWidth) {
		width = menuWidth;
	}
	var height = document.documentElement.clientHeight;
	document.getElementById('mainView').style.width = (width
			- document.getElementById('leftDivForMenu').offsetWidth * 2 - 2)
			+ 'px';
	document.getElementById('mainView').style.height = (height
			- document.getElementById('menu-wrapper').offsetHeight
			- document.getElementById('topDivForDate').offsetHeight - 2)
			+ 'px';
	document.getElementById('topDivForDate').style.width = (width
			- document.getElementById('leftDivForMenu').offsetWidth * 2 - 2)
			+ 'px';
	document.getElementById('leftDivForMenu').style.height = (height
			- document.getElementById('menu-wrapper').offsetHeight - 2)
			+ 'px';
	document.getElementById('rightDivForMenu').style.height = (height
			- document.getElementById('menu-wrapper').offsetHeight - 2)
			+ 'px';
	$('#rightDivForMenu').css('left',width-80);
}

window.onresize = function() {
	var width = document.documentElement.clientWidth;
	var subWidth = document.getElementById('submenu-wrapper').offsetWidth;
	initDiv(subWidth);
	if (width < subWidth) {
		$('#menu-wrapper').css('width', subWidth);
		// document.getElementById('menu-wrapper').stlye.width = subWidth;
	} else {
		$('#menu-wrapper').css('width', width);
	}
	// var wrapperWidth = document.getElementById('menu-wrapper').offsetWidth;
	// var objs = document.getElementsByTagName('ul');
	// for (var index = 0; index < objs.length; index++) {
	// if (objs[index].className == 'menu') {
	// objs[index].style.width = wrapperWidth * 0.75;
	// break;
	// }
	// }
	// var submenu = document.getElementById('submenu-wrapper');
	// var startWidth = submenu.offsetWidth;
	// submenu.style.width = wrapperWidth * 0.86;
	// var endWidth = submenu.offsetWidth;
	// var scale = endWidth / startWidth;
	// submenu.style.height = submenu.offsetHeight * scale;
	// $('.submenu li').css('width', submenu.offsetWidth * 0.181 + 10);
	// $('.submenu li img').css('width', submenu.offsetWidth * 0.181);
}