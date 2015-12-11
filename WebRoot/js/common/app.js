/*
 * WKY-PMANAGEMENT V 0.1
 * 
 * wankangyu@hotmail.com
 * 
 * http://blog.sina.com.cn/u/2896904785
 */
function MyDesktop() {
	var windows = new Ext.WindowGroup();
	var deskTopEl = Ext.get('mainView');
	var me = this;
	this.createWindow = function(config) {
		var maxable = true;
		if (config.maxmizable == false) {
			maxable = false;
		}
		var window = Ext.create('Ext.window.Window', Ext.applyIf(config || {},
						{
							manager : windows,
							minimizable : true,
							constrain : true,
							maximizable : maxable
						}));
		window.render(deskTopEl);
		window.on({
					'maximize' : {
						fn : function(win) {
							win.setPagePosition(deskTopEl.dom.offsetTop,
									deskTopEl.dom.offsetLeft);
						}
					}
				});
		return window;
	};
	this.showLoginWindow = function() {
		var me = this;
		var isLogin = false;
		var html = ' <div id="loginDiv" tabindex =-1><form id="loginForm"class="login">'
				+ '<h1>PM</h1>'
				+ '<input id="userName" type="text" name="userName" class="login-input" placeholder="用户名" autofocus>'
				+ '<input id="passWord"type="password" name="passWord" class="login-input" placeholder="密码">'
				+ '<input id="submitBtnForLogin" type="button" value="登录" class="login-submit" >'
				+ '</form></div>';
		var showDivEl = Ext.get('showLogin2');
		var winWidth;
		var winHeight;
		if (document.body && document.body.clientHeight
				&& document.body.clientWidth) {
			winHeight = document.body.clientHeight;
			winWidth = document.body.clientWidth;
		}
		document.getElementById('showLogin').style.display = "block";
		document.getElementById('showLogin').style.width = winWidth + "px";
		document.getElementById('showLogin').style.height = winHeight + "px";
		document.getElementById('showLogin2').style.display = "block";
		document.getElementById('showLogin2').style.width = winWidth + "px";
		document.getElementById('showLogin2').style.height = winHeight + "px";
		var login = document.getElementById('loginDiv');
		if (!login) {
			Ext.DomHelper.append(showDivEl, html);
			login = document.getElementById('loginDiv');
		}
		login.focus();// 获取焦点
		function loginSubmit() {
			isLogin = true;
			var userName = $('#userName').val();
			var passWord = $('#passWord').val();
			if (!userName) {
				jBox.tip('帐号不能为空！', 'failure');
				return;
			}
			if (!passWord) {
				jBox.tip('密码不能为空！', 'failure');
				return;
			}
			Ext.Ajax.request({
				url : 'user/login_user',
				method : 'POST',
				params : {
					userName : userName,
					passWord : passWord
				},
				success : function(response, options) {
					var result = Ext.JSON.decode(response.responseText);
					if (result.errno == '0') {
						document.getElementById('showLogin').style.display = "none";
						document.getElementById('showLogin2').style.display = "none";
						document.getElementById('login-div').style.display = "none";
						document.getElementById('logout-div').style.display = "block";
						document.getElementById('user-span').innerHTML = result.userName;
						// 登录成功后清空
						$('#userName').val('');
						$('#passWord').val('');
						if (result.userSex == "2") {
							document.getElementById('user-img').src = "images/user-women.jpg"
						}else{
							document.getElementById('user-img').src = "images/user-man.jpg"
						}
						jBox.messager(
								"<span style='font-size:12px;color:blue;'>"
										+ "登录成功！</span>",
								"<span style='font-size:12px;'>提示</span>",
								3000, {
									width : 200,
									showType : 'show'
								});
					} else {
						// isLogin = false;
						jBox.messager(
								"<span style='font-size:12px;color:red;'>"
										+ "登录失败！</span>",
								"<span style='font-size:12px;'>提示</span>",
								3000, {
									width : 200,
									showType : 'show'
								});
					}
				},
				failure : function(response, options) {
					// isLogin = false;
					Ext.Msg.show({
								icon : Ext.MessageBox.ERROR,
								buttons : Ext.Msg.OK,
								title : '错误',
								msg : '网络有误!'
							});
				}
			});
		}
		document.getElementById('submitBtnForLogin').onclick = loginSubmit;
		// $('#userName').keydown(function(e) {
		// if (e.keyCode == 13) {
		// loginSubmit();
		// }
		// });
		$('#passWord').keydown(function(e) {
					if (e.keyCode == 13) {
						if (!isLogin)
							loginSubmit();
					}
				});
		// 隐藏div
		document.getElementById('showLogin2').onclick = function(e) {
			var ele = e ? e.target : window.event.srcElement;
			if (ele.id !== 'loginDiv' && ele.id !== 'loginForm'
					&& ele.id !== 'userName' && ele.id !== 'passWord'
					&& ele.id !== 'submitBtnForLogin' && ele.id !== 'register'
					&& ele.id !== 'register-a') {
				document.getElementById('showLogin').style.display = "none";
				document.getElementById('showLogin2').style.display = "none";
			}
		}
	}
	this.registerWindow = function() {
		// Ext.apply(Ext.form.VTypes, {
		// verifyAccount:function(v){
		// console.debug(v);
		// if(v=='no'){
		// return false;
		// }
		// }
		// });
		var isVali = false;
		var addFormPanel = Ext.extend(Ext.form.FormPanel, {
			initComponent : function() {
				var me = this;
				me.id = 'registerFromPanel';
				me.width = '30%';
				me.hidden = false;
				me.bodyStyle = 'background:#D4E1F1';
				me.border = false;
				me.defaultType = 'textfield';
				me.defaults = {
					labelAlign : 'right',
					labelWidth : 60
				};
				// me.bodyPadding = '20 0 0 0';
				me.items = [{
					xtype : 'panel',
					bodyStyle : 'background:#D4E1F1',
					border : false,
					html : '<span id="warningSpanForRegister" style="color:red;line-height:20px;display:block;"></span>',
					height : 20
						// hidden:true
				}, {
					fieldLabel : '帐号',
					name : 'userName',
					width : 300,
					allowBlank : false,
					blankText : '帐号不能为空！',
					// validateOnChange : false,
					// validator : function(value) {},
					regex : /^[A-Za-z0-9_]+$/,
					regexText : '只允许输入英文字母、数字和下划线等字符！',
					maxLength:64,
					maxLengthText:'密码最大长度64位！',
					listeners : {
						blur : function(f) {
							var value = f.getValue();
							var warningSpan = document
									.getElementById('warningSpanForRegister');
							Ext.Ajax.request({
										url : 'user/verify_user',
										method : 'POST',
										params : {
											userName : value
										},
										success : function(response, options) {
											var result = Ext.JSON
													.decode(response.responseText);
											if (result.errno == '0') {
												warningSpan.innerHTML = '用户名可用。';
												warningSpan.style.color = 'blue';
												isVali = true;
											} else {
												warningSpan.innerHTML = '用户名已被占用!';
												warningSpan.style.color = 'red';
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

						}
					}
				}, {
					fieldLabel : '密码',
					name : 'passWord',
					width : 300,
					allowBlank : false,
					blankText : '密码不能为空！',
					maxLength:64,
					maxLengthText:'密码最大长度64位！'
				}, {
					fieldLabel : '呢称',
					name : 'userNickname',
					width : 300
				}, {
					xtype:'radiogroup',
					fieldLabel:'性别',
					items:[{
						boxLabel :'男',
						name:'userSex',
						checked:true,
						inputValue:'1'
					},{
						boxLabel :'女',
						name:'userSex',
						checked:false,
						inputValue:'2'
					}]
				},{
					xtype : 'panel',
					border : false,
					bodyStyle : 'background:#D4E1F1;',
					bodyPadding : '15 0 0 0',
					items : [{
						xtype : 'button',
						cls : 'x-grid-myBtn',
						overCls : 'x-grid-myBtn-over',
						text : '<span style="color:#fff;line-height:25px;">提交</span>',
						handler : function() {
							var form = me.getForm();
							if (form.isValid()&&isVali) {
								form.submit({
									url : 'user/register_user',
									method : 'post',
									success : function(form, action) {
										var result = action.result;
										if (result.errno == '0') {
											me.ownerCt.close();
											jBox
													.messager(
															"<span style='font-size:12px;color:blue;'>"
																	+ "注册成功</span>",
															"<span style='font-size:12px;'>提示</span>",
															3000, {
																width : 200,
																showType : 'show'
															});
										} else {
											jBox
													.messager(
															"<span style='font-size:12px;color:red;'>"
																	+ "注册失败，请联系管理员！</span>",
															"<span style='font-size:12px;'>提示</span>",
															3000, {
																width : 200,
																showType : 'show'
															});
										}
									},
									failure : function(form, action) {
										jBox
												.messager(
														"<span style='font-size:12px;color:red;'>"
																+ "网络错误！</span>",
														"<span style='font-size:12px;'>提示</span>",
														3000, {
															width : 200,
															showType : 'show'
														});
									}

								});
							}else{
								jBox.tip('注册有误！', 'failure');
							}
						}
					}, {
						xtype : 'button',
						cls : 'x-grid-myBtn',
						overCls : 'x-grid-myBtn-over',
						text : '<span style="color:#fff;line-height:25px;">重置</span>',
						handler : function() {
							me.getForm().reset();
						}
					}, {
						xtype : 'button',
						cls : 'x-grid-myBtn',
						overCls : 'x-grid-myBtn-over',
						text : '<span style="color:#fff;line-height:25px;">取消</span>',
						handler : function() {
							me.ownerCt.close();
						}
					}]
				}];
				me.callParent(arguments);
			}
		});
		var addFormPanel = new addFormPanel();
		var window = Ext.getCmp('registerWin');
		if(!window){
			window = Ext.create('Ext.Window', {
					id : 'registerWin',
					title : '帐号注册',
					renderTo : 'mainView',
					width : 350,
					height : 220,
					layout : 'fit',
					items : addFormPanel
				});
		}
		window.show();
	}
	/**
	 * 用户退出
	 */
	this.quitThisUser = function() {
		Ext.Ajax.request({
			url : 'user/quit_user',
			method : 'POST',
			success : function(response, options) {
				var result = Ext.JSON.decode(response.responseText);
				if (result.success) {
					document.getElementById('login-div').style.display = "block";
					document.getElementById('logout-div').style.display = "none";
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
	}
}

/**
 * ȫ�全局对象
 */
function AppWky() {

}
AppWky.prototype = {
	eval : function(className) {
		var str = className + ".init()";
		eval(str.toString());
	},
	getDesktop : function() {
		return new MyDesktop();
	}
};
var AppWky = new AppWky();

/**
 * 
 * @param moduleId
 */
function loadJs(moduleId) {
	Ext.Ajax.request({
				url : 'system/getModule',
				method : 'POST',
				params : {
					moduleId : moduleId
				},
				success : function(response, options) {
					var result = Ext.JSON.decode(response.responseText);
					if (result.errno == '0') {
						getModule(result.module, result.moduleFile);
					} else if (result.errno == '-6') {
						var desktop = AppWky.getDesktop();
						desktop.showLoginWindow();
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
}
/**
 * 
 * @param {}
 *            data
 */
function getModule(module, moduleFile) {
	if (!moduleFile.id.fileName || !module.className) {
		jBox.messager("<span style='font-size:12px;color:red;'>加载失败</span>",
				"<span style='font-size:12px;'>提示</span>", 3000, {
					id : 'main',
					width : 200,
					showType : 'slide',
					icon : 'info'
				});
		return;
	}
	var fileName = moduleFile.id.fileName.split('.')[0];
	Ext.require('js/pm/' + moduleFile.directory + fileName);
	setTimeout("AppWky.eval('" + module.className + "')", 200);
	jBox.messager("<span style='font-size:12px;'>成功加载" + module.launcherText
					+ "</span>", "<span style='font-size:12px;'>提示</span>",
			3000, {
				id : 'main',
				width : 200,
				showType : 'slide',
				icon : 'info'
			});
}
document.getElementById('login-btn').onclick = function() {
	AppWky.getDesktop().showLoginWindow();
}
document.getElementById('register-btn').onclick = function() {
	AppWky.getDesktop().registerWindow();
}
document.getElementById('logout-btn').onclick = function() {
	jBox.warning("确定退出？", "提示", function(v, h, f) {
				if (v == 'yes') {
					AppWky.getDesktop().quitThisUser();
				}
			}, {
				width : 300,
				top : '30%'
			});
}