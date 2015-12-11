Ext.Loader.setPath('Ext.ux', 'library/ux');
Ext.require(['Ext.ux.CheckColumn', 'Ext.ux.data.PagingMemoryProxy',
		'Ext.ux.ProgressBarPager']);
MyNetAccount = {
	init : function() {
		var desktop = AppWky.getDesktop();
		var tool = new AccountTool();
		tool.setDesktop(desktop);
		tool.init('myAccount');
		this.setTool(tool);
		this.createWindow();
	},
	createWindow : function() {
		var me = this;
		var tool = me.getTool();
		var desktop = tool.getDesktop();
		/**
		 * Custom function used for column renderer
		 * 
		 * @param {Object}
		 *            val
		 */
		function change(val) {
			if (val > 0) {
				return '<span style="color:green;">' + val + '</span>';
			} else if (val < 0) {
				return '<span style="color:red;">' + val + '</span>';
			}
			return val;
		}

		/**
		 * Custom function used for column renderer
		 * 
		 * @param {Object}
		 *            val
		 */
		function pctChange(val) {
			if (val > 0) {
				return '<span style="color:green;">' + val + '%</span>';
			} else if (val < 0) {
				return '<span style="color:red;">' + val + '%</span>';
			}
			return val;
		}
		/**
		 * 行高与居中显示
		 */
		function addClsForColumn(value, meta, record) {
			meta.tdCls = 'x-grid-columnCls';
			return value;
		}
		var store = Ext.create('Ext.data.ArrayStore', {
					autoLoad : true,
					proxy : {
						type : 'ajax',
						url : 'myManeg/getMyAccountInfo',
						actionMethods : {
							read : 'POST'
						},
						reader : {
							type : 'json',
							root : 'result',
							totalProperty : 'total'
						}
					},
					fields : ['accountId', 'accountName', 'accountPassword',
							'accountSecpassword', 'accountLrsj',
							'accountNeturl', 'accountBz']
				});
		var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
					clicksToEdit : 2
				});
		var gridPanel = Ext.extend(Ext.ux.LiveSearchGridPanel, {
			initComponent : function() {
				var me = this;
				me.id = 'centerGridPanel' + tool.id;
				me.store = store;
				me.multiSelect = true;
				me.region = 'center';
				me.columnLines = true;
				me.tbar = [{
							text : '新建',
							tooltip : '新建帐号',
							id : 'addBtn' + tool.id,
							iconCls : 'icon-add',
							handler : function(b) {
								var addForm = me.nextSibling();
								addForm.show();
								b.setDisabled(true);
							},
							scope : me
						}, '-', {
							text : '删除',
							tooltip : '删除帐号',
							iconCls : 'icon-delete',
							handler : function(b) {
								var selectModel = me.getSelectionModel();
								var count = selectModel.getCount();
								if (count > 0) {
									var modelArr = selectModel.getSelection();
									var id = '';
									for (var index = 0; index < modelArr.length; index++) {
										if (index != 0)
											id += ',';
										id += modelArr[index].data.accountId;
									}
									jBox.warning("删除" + count + "条记录？", "提示",
											function(v, h, f) {
												if (v == 'yes') {
													Ext.Ajax.request({
														url : 'myManeg/saveMyAccountInfo',
														method : 'POST',
														params : {
															type : 'delete',
															accountId : id
														},
														success : function(
																response,
																options) {
															var result = Ext.JSON
																	.decode(response.responseText);
															if (result.errno == '0') {
																jBox
																		.tip('已删除。');
																me
																		.getStore()
																		.reload();
															} else if (result.errno == '-6') {
																var desktop = AppWky
																		.getDesktop();
																desktop
																		.showLoginWindow();
															} else {
																jBox
																		.messager(
																				"<span style='font-size:12px;color:red;'>"
																						+ "删除失败！</span>",
																				"<span style='font-size:12px;'>提示</span>",
																				3000,
																				{
																					width : 200,
																					showType : 'show'
																				});
															}
														},
														failure : function(
																form, action) {
															jBox
																	.messager(
																			"<span style='font-size:12px;color:red;'>"
																					+ "网络错误！</span>",
																			"<span style='font-size:12px;'>提示</span>",
																			3000,
																			{
																				width : 200,
																				showType : 'show'
																			});
														}
													})

												}
											}, {
												width : 300,
												top : '30%'
											});

								} else {
									jBox
											.messager(
													"<span style='font-size:12px;color:red;'>"
															+ "没有选择要删除的帐号！</span>",
													"<span style='font-size:12px;'>提示</span>",
													3000, {
														width : 200,
														showType : 'fade'
													});
								}
							},
							scope : me
						}, '-', {
							text : '刷新',
							tooltip : '刷新列表',
							iconCls : 'icon-refresh',
							handler : function(b) {
								if (me.getStore()) {
									me.getStore().reload();
								}
							},
							scope : me
						}, '->', {
							xtype : 'textfield',
							name : 'searchField',
							hideLabel : true,
							emptyText : '输入查询的关键字',
							width : 200,
							listeners : {
								change : {
									fn : me.onTextFieldChange,
									scope : this,
									buffer : 100
								}
							}
						}, {
							xtype : 'button',
							text : '&lt;',
							tooltip : '上一个',
							handler : me.onPreviousClick,
							scope : me
						}, {
							xtype : 'button',
							text : '&gt;',
							tooltip : '下一个',
							handler : me.onNextClick,
							scope : me
						}, '-', {
							xtype : 'checkbox',
							hideLabel : true,
							margin : '0 0 0 4px',
							handler : me.caseSensitiveToggle,
							scope : me
						}, '大小写敏感'];
				me.columns = [{
							text : '帐号',
							// flex : 1,
							width : 170,
							sortable : false,
							align : 'center',
							dataIndex : 'accountName',
							renderer : addClsForColumn,
							editor : {
								allowBlank : false,
								height : 30,
								maxLength : 64
							}
						}, {
							text : '密码',
							width : 140,
							sortable : true,
							align : 'center',
							renderer : addClsForColumn,
							dataIndex : 'accountPassword',
							editor : {
								allowBlank : false,
								height : 30,
								maxLength : 64
							}
						}, {
							text : '二级密码',
							width : 140,
							sortable : true,
							align : 'center',
							renderer : addClsForColumn,
							dataIndex : 'accountSecpassword',
							editor : {
								height : 30,
								maxLength : 64
							}
						}, {
							text : '录入时间',
							width : 90,
							sortable : true,
							align : 'center',
							renderer : addClsForColumn,
							dataIndex : 'accountLrsj'
						}, {
							text : '网站URL',
							width : 100,
							sortable : true,
							align : 'center',
							dataIndex : 'accountNeturl',
							renderer : addClsForColumn,
							editor : {
								allowBlank : true,
								height : 30,
								maxLength : 2000
							}
						}, {
							text : '描述',
							flex : 1,
							sortable : true,
							dataIndex : 'accountBz',
							renderer : addClsForColumn,
							editor : {
								allowBlank : true,
								height : 30,
								maxLength : 2500
							}
						}, {
							text : '操作',
							width : 75,
							sortable : true,
							align : 'center',
							name : 'cz',
							dataIndex : 'accountNeturl',
							renderer : function(value, m, r) {
								m.tdCls = 'x-grid-myActionBtn';
								return '<span style="color:#fff;">Go</span>';
							}
						}];
				me.viewConfig = {
					stripeRows : true
				};
				// me.bbar = Ext.create('Ext.PagingToolbar', {
				// pageSize : 10,
				// store : store,
				// displayInfo : true,
				// plugins : Ext.create(
				// 'Ext.ux.ProgressBarPager', {
				// defaultText : '进度'
				// })
				// })
				me.bbar = Ext.create('Ext.ux.StatusBar', {
							defaultText : me.defaultStatusText,
							name : 'searchStatusBar'
						});
				// me.selModel = {
				// selType : 'cellmodel'
				// };
				me.plugins = [cellEditing];
				me.listeners = {
					cellclick : function(grid, td, columnIndex, re, tr,
							rowIndex) {
						if (me.columns[columnIndex].name == 'cz') {
							window.open('http://' + re.get('accountNeturl'));
						}
					},
					edit : function(editor, e, eOpts) {
						if (e.value != e.originalValue) {
							var formData = e.record.getData();
							jBox.warning("确定修改？", "提示", function(v, h, f) {
								if (v == 'yes') {
									Ext.Ajax.request({
										url : 'myManeg/saveMyAccountInfo',
										method : 'POST',
										params : {
											type : 'update',
											userName : formData.accountName,
											passWord : formData.accountPassword,
											secPassword : formData.accountSecpassword,
											accountId : formData.accountId,
											bz : formData.accountBz,
											netURL : formData.accountNeturl
										},
										success : function(response, options) {
											var result = Ext.JSON
													.decode(response.responseText);
											if (result.errno == '0') {
												// me.getStore().load();
												jBox.tip('已修改。');
											} else if (result.errno == '-6') {
												var desktop = AppWky
														.getDesktop();
												desktop.showLoginWindow();
												me.getStore().reload();
											} else {
												jBox
														.messager(
																"<span style='font-size:12px;color:red;'>"
																		+ "修改失败！</span>",
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
								}
								if (v == "no") {
									me.getStore().reload();
								}
								if (v == 'cancel') {
									me.getStore().reload();
								}
							}, {
								width : 300,
								top : '30%'
							});
						}
					}
				};
				me.callParent(arguments);
			}
		});
		var addFormPanel = Ext.extend(Ext.form.FormPanel, {
			initComponent : function() {
				var me = this;
				me.id = 'eastaddFromPanel' + tool.id;
				me.title = '添加帐号';
				me.region = 'east';
				me.width = '30%';
				me.hidden = true;
				me.bodyStyle = 'background:#D4E1F1;';
				me.border = 0;
				// me.layout = 'form';
				me.defaultType = 'textfield';
				me.defaults = {
					labelAlign : 'right',
					labelWidth : 60
				};
				me.bodyPadding = '20 0 0 0';
				me.items = [{
							fieldLabel : '帐号',
							name : 'userName',
							width : 300,
							allowBlank : false
						}, {
							fieldLabel : '密码',
							name : 'passWord',
							width : 300,
							allowBlank : false
						}, {
							fieldLabel : '二级密码',
							name : 'secPassword',
							width : 300
						}, {
							xtype : 'textareafield',
							name : 'netURL',
							fieldLabel : '网站',
							width : 300
						}, {
							xtype : 'textareafield',
							name : 'bz',
							fieldLabel : '描述',
							width : 300
						}, {
							xtype : 'panel',
							border : false,
							bodyStyle : 'background:#D4E1F1;',
							bodyPadding : '40 0 0 0',
							items : [{
								xtype : 'button',
								cls : 'x-grid-myBtn',
								overCls : 'x-grid-myBtn-over',
								text : '<span style="color:#fff;line-height:25px;">保存</span>',
								handler : function() {
									var form = me.getForm();
									if (form.isValid()) {
										form.submit({
											url : 'myManeg/saveMyAccountInfo',
											method : 'post',
											success : function(form, action) {
												var result = action.result;
												if (result.errno == '0') {
													me.getForm().reset();
													me.setVisible(false);
													me.previousSibling()
															.getStore().load();
													Ext.getCmp('addBtn'
															+ tool.id)
															.setDisabled(false);
													jBox
															.messager(
																	"<span style='font-size:12px;color:blue;'>"
																			+ "保存成功</span>",
																	"<span style='font-size:12px;'>提示</span>",
																	3000, {
																		width : 200,
																		showType : 'show'
																	});
												} else if (result.errno == '-6') {
													var desktop = AppWky
															.getDesktop();
													desktop.showLoginWindow();
												} else {
													jBox
															.messager(
																	"<span style='font-size:12px;color:red;'>"
																			+ "保存失败！</span>",
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
									} else {
										jBox
												.messager(
														"<span style='font-size:12px;color:red;'>"
																+ "内容有误！</span>",
														"<span style='font-size:12px;'>提示</span>",
														3000, {
															width : 200,
															showType : 'slide'
														});
									}
								}
							}, {
								xtype : 'button',
								cls : 'x-grid-myBtn',
								overCls : 'x-grid-myBtn-over',
								text : '<span style="color:#fff;line-height:25px;">保存并新建</span>',
								handler : function() {
									var form = me.getForm();
									if (form.isValid()) {
										form.submit({
											url : 'myManeg/saveMyAccountInfo',
											method : 'post',
											success : function(form, action) {
												var result = action.result;
												if (result.errno == '0') {
													me.getForm().reset();
													me.previousSibling()
															.getStore().load();
													jBox
															.messager(
																	"<span style='font-size:12px;color:blue;'>"
																			+ "保存成功</span>",
																	"<span style='font-size:12px;'>提示</span>",
																	3000, {
																		width : 200,
																		showType : 'slide'
																	});
												} else if (result.errno == '-6') {
													var desktop = AppWky
															.getDesktop();
													desktop.showLoginWindow();
												} else {
													jBox
															.messager(
																	"<span style='font-size:12px;color:red;'>"
																			+ "保存失败！</span>",
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
																	showType : 'slide'
																});
											}

										});
									} else {
										jBox
												.messager(
														"<span style='font-size:12px;color:red;'>"
																+ "内容有误！</span>",
														"<span style='font-size:12px;'>提示</span>",
														3000, {
															width : 200,
															showType : 'slide'
														});
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
									me.getForm().reset();
									me.setVisible(false);
									Ext.getCmp('addBtn' + tool.id)
											.setDisabled(false);
								}
							}]
						}];
				// me.buttonAlign='right';
				// me.buttons=[{
				// text:'保存'
				// },{
				// text:'保存并新建'
				// },{
				// text:'取消'
				// }];
				me.callParent(arguments);
			}
		});
		var win = Ext.getCmp('mainWin' + tool.id);
		if (!win)
			/**
			 * @param mainDivWidth
			 *            中间视图区的宽度 mainDivHeight 中间视图区的高度
			 */
			win = desktop.createWindow({
						id : 'mainWin' + tool.id,
						title : '网络帐号管理',
						width : mainDivWidth * 0.8,
						height : mainDivHeight * 0.8,
						layout : 'border',
						items : [new gridPanel(), new addFormPanel()]
					});
		win.show();
		win.maximize();
	},
	getTool : function() {
		return this.tool;
	},
	setTool : function(tool) {
		this.tool = tool;
	}
};

function AccountTool() {

}
AccountTool.prototype = {
	init : function(id) {
		this.id = id;
	},
	getText : function() {
		return this.text;
	},
	setText : function(text) {
		this.text = text;
	},
	getIndex : function() {
		return this.index;
	},
	setIndex : function(index) {
		this.index = index
	},
	setDesktop : function(desktop) {
		this.desktop = desktop;
	},
	getDesktop : function() {
		return this.desktop;
	},
	setFontSize : function(value, size) {
		size = size || 12;
		return '<span style="font-size:' + size + 'px;">' + value + '</span>';
	},
	dateFormat : function(value) {
		return value ? Ext.Date.dateFormat(value, 'Y-m-d') : '';
	}
};
