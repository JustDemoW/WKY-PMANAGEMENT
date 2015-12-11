MyConsumptionRecord = {
	init : function() {
		var desktop = AppWky.getDesktop();
		var tool = new ConsumptionTool();
		tool.setDesktop(desktop);
		tool.init('myConsumptionRecord');
		this.setTool(tool);
		this.createWindow();
	},
	createWindow : function() {
		var me = this;
		var tool = me.getTool();
		var desktop = tool.getDesktop();
		/**
		 * 行高与居中显示
		 */
		function addClsForColumn(value, meta, record) {
			meta.tdCls = 'x-grid-columnCls';
			return value;
		}
		var store = Ext.create('Ext.data.ArrayStore', {
					autoLoad : true,
					pageSize : 5,
					proxy : {
						type : 'ajax',
						url : 'consumption/getConsumptionInfo',
						actionMethods : {
							read : 'POST'
						},
						reader : {
							type : 'json',
							root : 'result',
							totalProperty : 'total'
						}
					},
					fields : ['recordId', 'recordDeposittype', 'recordType',
							'recordConsumptiontype', 'recordConsumptioncredit',
							'recordRemarks', 'recordTime'],
					listeners : {
						beforeload : function(s) {
							var new_params = tool.getParams();
							Ext.apply(s.proxy.extraParams, new_params);
						}
					}
				});
		var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
					clicksToEdit : 2
				});
		function getComboDate() {
			var date = [];
			var nowY = new Date().getFullYear();
			for (var index = 0; index < 5; index++) {
				var value = nowY - index;
				date.push({
							'displayValue' : value + '年',
							'value' : value
						});
			}
			return date;
		}
		var comboDate = getComboDate();
		var comboStore = Ext.create('Ext.data.Store', {
					fields : ['displayValue', 'value'],
					data : comboDate
				});
		var defaultTbar = Ext.create('Ext.toolbar.Toolbar', {
			id:'defaultTbar'+tool.id,
			items : [{
						text : '记账',
						iconCls : 'icon-add',
						handler : function(b) {
							b.setDisabled(true);
							var panel = Ext.getCmp('rightPanel' + tool.id);
							panel.items.get(0).hide();
							panel.items.get(1).show();
						}
					}, '-', {
						text : '删除',
						iconCls : 'icon-delete',
						handler : function(b) {
							var gridPanel = Ext.getCmp('gridPanel' + tool.id);
							var selectModel = gridPanel.getSelectionModel();
							var count = selectModel.getCount();
							if (count > 0) {
								var modelArr = selectModel.getSelection();
								var id = '';
								for (var index = 0; index < modelArr.length; index++) {
									if (index != 0)
										id += ',';
									id += modelArr[index].data.recordId;
								}
								jBox.warning("删除" + count + "条记录？", "提示",
										function(v, h, f) {
											if (v == 'yes') {
												Ext.Ajax.request({
													url : 'consumption/consumptionInfoAction',
													method : 'POST',
													params : {
														type : 'delete',
														recordId : id
													},
													success : function(
															response, options) {
														var result = Ext.JSON
																.decode(response.responseText);
														if (result.errno == '0') {
															jBox.tip('已删除。');
															store.reload();
															categoryStore
																	.reload();
															yearStore.reload();
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
													failure : function(form,
															action) {
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
						}
					}, '->', {
						xtype : 'radiogroup',
						id : 'checkedRadiogroup' + tool.id,
						width : 300,
						items : [{
									boxLabel : '今天',
									name : 'checkDate',
									checked : true,
									inputValue : '1'
								}, {
									boxLabel : '昨天',
									name : 'checkDate',
									checked : false,
									inputValue : '2'
								}, {
									boxLabel : '本周',
									name : 'checkDate',
									checked : false,
									inputValue : '3'
								}, {
									boxLabel : '本月',
									name : 'checkDate',
									checked : false,
									inputValue : '4'
								}, {
									boxLabel : '自定义',
									name : 'checkDate',
									checked : false,
									inputValue : '5'
								}],
						listeners : {
							change : function(r) {
								var searchDateType = r.getValue().checkDate;
								var params = tool.getParams();
								params.searchDateType = searchDateType;
								tool.setParams(params);
								if (searchDateType == "5") {
									Ext.getCmp('northPanel' + tool.id).show();
								} else {
									Ext.getCmp('northPanel' + tool.id).hide();
									store.load();
									categoryStore.load();
								}
							}
						}
					}, {
						xtype : 'combo',
						id : 'checkYearCombo' + tool.id,
						width : 80,
						height : 30,
						displayField : 'displayValue',
						valueField : 'value',
						hidden : true,
						store : comboStore,
						value : new Date().getFullYear(),
						listeners : {
							select : function(c) {
								var tablePanel = Ext.getCmp('tablePanel'
										+ tool.id);
								var params = tool.getParams();
								var value = c.getValue();
								tablePanel.items.get(1).setTitle(value
										+ '年度消费统计');
								params.searchYear = value;
								tool.setParams(params);
								yearStore.load();
							}
						}
					}]
		});

		var northPanel = Ext.create('Ext.Panel', {
					id : 'northPanel' + tool.id,
					region : 'north',
					border : 0,
					hidden : true,
					height : 30,
					bodyStyle : 'background:#D3E1F1;',
					defaults : {
						margins : '0 5 0 0'
					},
					layout : {
						type : 'hbox',
						padding : '3',
						pack : 'end',
						align : 'middle'
					},
					// bodyPadding:'0 10 0 0',
					items : [{
								xtype : 'panel',
								bodyStyle : 'background:#D3E1F1;',
								border : 0,
								html : '从'
							}, {
								xtype : 'datefield',
								itemId : 'startDate',
								format : 'Y-m-d',
								altFormats : 'Ymd|Y.m.d'
							}, {
								xtype : 'panel',
								bodyStyle : 'background:#D3E1F1;',
								border : 0,
								html : '到'
							}, {
								xtype : 'datefield',
								itemId : 'endDate',
								format : 'Y-m-d',
								altFormats : 'Ymd|Y.m.d'
							}, {
								xtype : 'button',
								cls : 'x-mybt-100b',
								text : 'ok',
								handler : function(b) {
									var startDate = Ext.util.Format.date(
											b.ownerCt.items.get(1).getValue(),
											'Ymd');
									var endDate = Ext.util.Format.date(
											b.ownerCt.items.get(3).getValue(),
											'Ymd');
									if (!startDate) {
										jBox.tip('起始日期不能为空！');
										return;
									}
									if (!endDate) {
										jBox.tip('结束日期不能为空！');
										return;
									}
									if (startDate > endDate) {
										jBox.tip('起始日期不能大于结束日期！');
										return;
									}
									var params = tool.getParams();
									params.searchStartTime = startDate;
									params.searchEndTime = endDate;
									tool.setParams(params);
									store.load();
								}
							}]
				});
		var gridPanel = Ext.create('Ext.grid.Panel', {
					id : 'gridPanel' + tool.id,
					multiSelect : true,
					region : 'center',
					columnLines : true,
					border : 0,
					store : store,
					columns : [{
								text : '消费时间',
								width : 150,
								align : 'center',
								sortable : false,
								dataIndex : 'recordTime',
								renderer : addClsForColumn,
								editor : {
									allowBlank : false,
									height : 30,
									maxLength : 64
								}

							}, {
								text : '收入/支出',
								width : 80,
								align : 'center',
								sortable : false,
								dataIndex : 'recordType',
								renderer : addClsForColumn,
								editor : {
									allowBlank : false,
									height : 30,
									maxLength : 64
								}
							}, {
								text : '储蓄类型',
								width : 80,
								align : 'center',
								sortable : false,
								dataIndex : 'recordDeposittype',
								renderer : addClsForColumn,
								editor : {
									allowBlank : false,
									height : 30,
									maxLength : 64
								}
							}, {
								text : '消费项目',
								width : 80,
								align : 'center',
								sortable : false,
								dataIndex : 'recordConsumptiontype',
								renderer : addClsForColumn,
								editor : {
									allowBlank : false,
									height : 30,
									maxLength : 64
								}
							}, {
								text : '消费金额',
								width : 80,
								align : 'center',
								sortable : false,
								dataIndex : 'recordConsumptioncredit',
								renderer : addClsForColumn,
								editor : {
									allowBlank : false,
									height : 30,
									maxLength : 64
								}
							}, {
								text : '备注',
								align : 'left',
								flex : 1,
								sortable : false,
								dataIndex : 'recordRemarks',
								renderer : addClsForColumn,
								editor : {
									allowBlank : false,
									height : 30,
									maxLength : 64
								}
							}],
					viewConfig : {
						stripeRows : true
					},
					plugins : [cellEditing]
				});
		var categoryStore = Ext.create('Ext.data.JsonStore', {
					autoLoad : true,
					proxy : {
						type : 'ajax',
						url : 'consumption/getConsumptionCharts',
						actionMethods : {
							read : 'POST'
						},
						reader : {
							type : 'json',
							root : 'result'
						}
					},
					fields : ['name', 'data'],
					listeners : {
						beforeload : function(s) {
							var new_params = tool.getParams();
							Ext.apply(s.proxy.extraParams, new_params);
						}
					}
				});
		// 柱状图
		var centerChartPanel = Ext.create('Ext.Panel', {
					id : 'centerChartPanel' + tool.id,
					region : 'center',
					border : 0,
					layout : 'fit',
					items : [{
						xtype : 'chart',
						id : 'chartCmp',
						xtype : 'chart',
						style : 'background:#fff',
						animate : true,
						shadow : true,
						store : categoryStore,
						axes : [{
							type : 'Numeric',
							position : 'left',
							fields : ['data'],
							label : {
								renderer : Ext.util.Format
										.numberRenderer('0,0')
							},
							// title : '金额',
							grid : true,
							minimum : 0
						}, {
							type : 'Category',
							position : 'bottom',
							fields : ['name']
								// title : '消费类型'
							}],
						series : [{
							type : 'column',
							axis : 'left',
							highlight : true,
							tips : {
								trackMouse : true,
								width : 140,
								height : 28,
								renderer : function(storeItem, item) {
									this.setTitle(storeItem.get('name') + ': '
											+ storeItem.get('data') + ' 元');
								}
							},
							label : {
								display : 'insideEnd',
								'text-anchor' : 'middle',
								field : 'data',
								// renderer :
								// Ext.util.Format.numberRenderer('0'),
								orientation : 'vertical',
								color : '#333'
							},
							xField : 'name',
							yField : 'data'
						}]
					}]
				});
		// 饼状图
		var rightChartPanel = Ext.create('Ext.Panel', {
					id : 'rightChartPanel' + tool.id,
					region : 'east',
					border : 0,
					width : '30%',
					layout : 'fit',
					items : [{
						xtype : 'chart',
						id : 'chartCmp2',
						animate : true,
						store : categoryStore,
						shadow : true,
						legend : {
							position : 'right'
						},
						// insetPadding : 60,
						theme : 'Base:gradients',
						series : [{
							type : 'pie',
							field : 'data',
							showInLegend : false,
							donut : false,
							tips : {
								trackMouse : true,
								width : 140,
								height : 28,
								renderer : function(storeItem, item) {
									// calculate percentage.
									var total = 0;
									categoryStore.each(function(rec) {
												total += rec.get('data');
											});
									this.setTitle(storeItem.get('name')
											+ ': '
											+ Math.round(storeItem.get('data')
													/ total * 100) + '%');
								}
							},
							highlight : {
								segment : {
									margin : 20
								}
							}
								// ,label : {
								// field : 'name',
								// display : 'rotate',
								// contrast : true,
								// font : '18px Arial'
								// }
						}]
					}]
				});
		var yearStore = Ext.create('Ext.data.JsonStore', {
					// autoLoad : true,
					proxy : {
						type : 'ajax',
						url : 'consumption/getConsumptionChartsForYear',
						actionMethods : {
							read : 'POST'
						},
						reader : {
							type : 'json',
							root : 'result'
						}
					},
					fields : ['name', 'data'],
					listeners : {
						beforeload : function(s) {
							var new_params = tool.getParams();
							Ext.apply(s.proxy.extraParams, new_params);
						}
					}
				});
		// 年度线性
		var yearChart = Ext.create('Ext.chart.Chart', {
					xtype : 'chart',
					animate : true,
					store : yearStore,
					// insetPadding : 30,
					axes : [{
								type : 'Numeric',
								minimum : 0,
								position : 'left',
								fields : ['data'],
								title : false,
								grid : true,
								label : {
									renderer : Ext.util.Format
											.numberRenderer('0,0'),
									font : '10px Arial'
								}
							}, {
								type : 'Category',
								position : 'bottom',
								fields : ['name'],
								title : false,
								label : {
									font : '11px Arial'
									// renderer : function(name) {
									// return name.substr(0, 3);
									// }
								}
							}],
					series : [{
								type : 'line',
								axis : 'left',
								xField : 'name',
								yField : 'data',
								gutter : 80,
								listeners : {},
								tips : {
									trackMouse : true,
									width : 80,
									height : 40,
									renderer : function(storeItem, item) {
										this.setTitle(storeItem.get('name'));
										this.update(storeItem.get('data'));
									}
								},
								style : {
									fill : '#38B8BF',
									stroke : '#38B8BF',
									'stroke-width' : 3
								},
								markerConfig : {
									type : 'circle',
									size : 4,
									radius : 4,
									'stroke-width' : 0,
									fill : '#38B8BF',
									stroke : '#38B8BF'
								}
							}]
				});
		var tablePanel = Ext.create('Ext.tab.Panel', {
					id : 'tablePanel' + tool.id,
					region : 'south',
					height : '46%',
					border : 0,
					activeTab : 0,
					plain : false,
					items : [{
								title : '消费类别统计',
								layout : 'border',
								border : 0,
								items : [centerChartPanel, rightChartPanel]
							}, {
								title : '年度消费统计',
								border : 0,
								layout : {
									type : 'fit'
								},
								items : yearChart
							}],
					listeners : {
						beforerender : function(tb) {
							var comboValue = Ext.getCmp('checkYearCombo'
									+ tool.id).getValue();
							tb.items.get(1).setTitle(comboValue + "年度消费统计")
						},
						tabchange : function(tb, p) {
							var comboValue = Ext.getCmp('checkYearCombo'
									+ tool.id).getValue();
							var centerPanel = tb.ownerCt;
							var pTitle = comboValue + '年度消费统计';
							if (p.title == pTitle) {
								yearStore.load();
								Ext.getCmp('checkedRadiogroup' + tool.id)
										.hide();
								centerPanel.items.get(0).hide();
								Ext.getCmp('checkYearCombo' + tool.id).show();
							} else {
								var radioGroup = Ext.getCmp('checkedRadiogroup'
										+ tool.id);
								radioGroup.show();
								var radioValue = radioGroup.getValue().checkDate;
								if (radioValue == '5') {
									centerPanel.items.get(0).show();
								}
								Ext.getCmp('checkYearCombo' + tool.id).hide();
							}
						}
					}
				});
		var centerPanel = Ext.create('Ext.panel.Panel', {
					id : 'centerPanel' + tool.id,
					region : 'center',
					border : 1,
					layout : 'border',
					tbar : defaultTbar,
					items : [northPanel, gridPanel, tablePanel],
					bbar : Ext.create('Ext.PagingToolbar', {
								pageSize : 5,
								store : store,
								displayInfo : true,
								displayMsg : '显示 {0} - {1} 条,共 {2} 条',
								emptyMsg : '没有数据',
								plugins : Ext.create('Ext.ux.ProgressBarPager',
										{})
							})
				});
		var tpl = '<table class="x-crpanel-tbale">'
				+ '<tr>'
				+ '<td style="width:120px;height:30px;text-align:right;">工资(￥)：'
				+ '</td>'
				+ '<td id="salarytd" style="width:150px;height:30px;text-align:center;color:blue;">+0.00'
				+ '</td>'
				+ '</tr>'
				+ '<tr>'
				+ '<td style="width:120px;height:30px;text-align:right;">信用卡支出(￥)：'
				+ '</td>'
				+ '<td id="xykzctd" style="width:150px;height:30px;text-align:center;color:red;">-0.00'
				+ '</td>'
				+ '</tr>'
				+ '<tr>'
				+ '<td style="width:120px;height:30px;text-align:right;">他人借出(￥)：'
				+ '</td>'
				+ '<td id="trjctd" style="width:150px;height:30px;text-align:center;color:blue;">-0.00'
				+ '</td>'
				+ '</tr>'
				+ '<tr>'
				+ '<td style="width:120px;height:30px;text-align:right;">他人借入(￥)：'
				+ '</td>'
				+ '<td id="trjrtd" style="width:150px;height:30px;text-align:center;color:red;">+0.00'
				+ '</td>'
				+ '</tr>'
				+ '<tr>'
				+ '<td style="width:120px;height:30px;text-align:right;">总收入(￥)：'
				+ '</td>'
				+ '<td id="zsrtd" style="width:150px;height:30px;text-align:center;color:blue;">+0.00'
				+ '</td>'
				+ '</tr>'
				+ '<tr>'
				+ '<td style="width:120px;height:30px;text-align:right;">总支出(￥)：'
				+ '</td>'
				+ '<td id="zzctd" style="width:150px;height:30px;text-align:center;color:red;">-0.00'
				+ '</td>'
				+ '</tr>'
				+ '<tr>'
				+ '<td style="width:120px;height:30px;text-align:right;font-weight:bold;">结余(￥)：'
				+ '</td>'
				+ '<td id="jytd" style="width:150px;height:30px;text-align:center;font-weight:bold;">0.00'
				+ '</td>' + '</tr>' + '</table>';
		var addFormpanel = Ext.create('Ext.form.Panel', {
			id : 'addFormpanel' + tool.id,
			// region : 'east',
			// width : '30%',
			hidden : true,
			border : false,
			bodyStyle : 'background:#D4E1F1;',
//			bodyPadding : 6,
//			defaults : {
//				labelAlign : 'right',
//				labelWidth : 60
//			},
			items : [{
						xtype : 'fieldset',
						title : '新增支出',
						items : [{
									xtype : 'combo',
									fieldLabel : '储蓄类型',
									name:'cxlx_zc',
									width : 285,
									labelAlign : 'right',
									labelWidth : 60,
									displayField : 'ddValue',
									valueField : 'ddCodeValue',
									store : tool.getDdStore('储蓄类型'),
									listeners : {}
								}, {
									xtype : 'combo',
									fieldLabel : '储蓄帐号',
									name:'cxzh_zc',
									width : 285,
									labelAlign : 'right',
									labelWidth : 60,
									displayField : 'ddValue',
									valueField : 'ddCodeValue',
									store : tool.getDdStore('储蓄帐号'),
									listeners : {}
								}, {
									xtype : 'combo',
									fieldLabel : '支出类型',
									name:'zclx',
									width : 285,
									labelAlign : 'right',
									labelWidth : 60,
									displayField : 'ddValue',
									valueField : 'ddCodeValue',
									listConfig:{
										align:'left'
									},
									store : tool.getDdStore('消费类型'),
									listeners : {}
								}, {
									xtype : 'textfield',
									name : 'zcje',
									fieldLabel : '支出金额',
									labelAlign : 'right',
									labelWidth : 60,
									width : 285
								}, {
									xtype : 'textareafield',
									name : 'zcbz',
									fieldLabel : '支出备注',
									labelAlign : 'right',
									labelWidth : 60,
									width : 285,
									height:40
								}]
					}, {
						xtype : 'fieldset',
						title : '新增收入',
						items : [{
									xtype : 'combo',
									fieldLabel : '储蓄类型',
									name:'cxlx_sr',
									width : 285,
									labelAlign : 'right',
									labelWidth : 60,
									displayField : 'ddValue',
									valueField : 'ddCodeValue',
									store : tool.getDdStore('储蓄类型'),
									listeners : {}
								}, {
									xtype : 'combo',
									fieldLabel : '储蓄帐号',
									name:'cxzh_sr',
									width : 285,
									labelAlign : 'right',
									labelWidth : 60,
									displayField : 'ddValue',
									valueField : 'ddCodeValue',
									store : tool.getDdStore('储蓄帐号'),
									listeners : {}
								}, {
									xtype : 'combo',
									fieldLabel : '收入类型',
									name:'srlx',
									width : 285,
									labelAlign : 'right',
									labelWidth : 60,
									displayField : 'ddValue',
									valueField : 'ddCodeValue',
									store : tool.getDdStore('收入类型'),
									listeners : {}
								}, {
									xtype : 'textfield',
									name : 'srje',
									fieldLabel : '收入金额',
									labelAlign : 'right',
									labelWidth : 60,
									width : 285
								}, {
									xtype : 'textareafield',
									name : 'srbz',
									fieldLabel : '收入备注',
									labelAlign : 'right',
									labelWidth : 60,
									width : 285,
									height:40
								}]
					}, {
						xtype : 'panel',
						border : false,
						bodyStyle : 'background:#D4E1F1;',
						bodyPadding : '10 0 0 0',
						items : [{
							xtype : 'button',
							cls : 'x-grid-myBtn',
							overCls : 'x-grid-myBtn-over',
							text : '<span style="color:#fff;line-height:25px;">保存</span>',
							handler : function(b) {
								
							}
						}, {
							xtype : 'button',
							cls : 'x-grid-myBtn',
							overCls : 'x-grid-myBtn-over',
							text : '<span style="color:#fff;line-height:25px;">保存并新建</span>',
							handler : function(b) {
							}
						}, {
							xtype : 'button',
							cls : 'x-grid-myBtn',
							overCls : 'x-grid-myBtn-over',
							text : '<span style="color:#fff;line-height:25px;">重置</span>',
							handler : function(b) {
								b.ownerCt.ownerCt.getForm().reset();
							}
						}, {
							xtype : 'button',
							cls : 'x-grid-myBtn',
							overCls : 'x-grid-myBtn-over',
							text : '<span style="color:#fff;line-height:25px;">取消</span>',
							handler : function(b) {
							Ext.getCmp('defaultTbar'+tool.id).items.get(0).setDisabled(false);
							var panel = Ext.getCmp('rightPanel' + tool.id);
							panel.items.get(0).show();
							panel.items.get(1).hide();
							b.ownerCt.ownerCt.getForm().reset();
						}
						}]
					}]
		});
		var rightPanel = Ext.create('Ext.panel.Panel', {
			id : 'rightPanel' + tool.id,
			region : 'east',
			width : '30%',
			border : false,
			bodyStyle : 'background:#D4E1F1;',
			bodyPadding : 6,
			layout : 'fit',
			items : [{
						xtype : 'fieldset',
						title : '统计结果',
						html : tpl
					}, addFormpanel],
			listeners : {
				afterrender : function(p) {
					Ext.Ajax.request({
						url : 'consumption/getStatisticsData',
						method : 'POST',
						success : function(response, options) {
							var result = Ext.JSON.decode(response.responseText);
							if (result.errno == '0') {
								document.getElementById('salarytd').innerHTML = '+'
										+ result.salary;
								document.getElementById('xykzctd').innerHTML = '-'
										+ result.xykxf;
								document.getElementById('trjctd').innerHTML = '-'
										+ result.trjc;
								document.getElementById('trjrtd').innerHTML = '+'
										+ result.trjr;
								document.getElementById('zsrtd').innerHTML = '+'
										+ result.zsr;
								document.getElementById('zzctd').innerHTML = '-'
										+ result.zzc;
								document.getElementById('jytd').innerHTML = result.jy;
							} else if (result.errno == '-6') {
								var desktop = AppWky.getDesktop();
								desktop.showLoginWindow();
							} else {

							}
						}
					});
				}
			}
		});
		var win = Ext.getCmp('mainWin' + tool.id);
		if (!win) {
			win = desktop.createWindow({
						id : 'mainWin' + tool.id,
						title : '记账管理',
						width : mainDivWidth * 0.8,
						height : mainDivHeight * 0.8,
						layout : 'border',
						items : [centerPanel, rightPanel]
					});
		}
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
function ConsumptionTool() {
	this.params = {
		searchDateType : 1,
		searchYear : new Date().getFullYear()
	};
}
ConsumptionTool.prototype = {
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
	getParams : function() {
		return this.params;
	},
	setParams : function(params) {
		this.params = params;
	},
	getDdStore:function(className){
		var ddKeyStore = Ext.create('Ext.data.ArrayStore', {
					proxy : {
						type : 'ajax',
						url : 'common/getDDLike',
						actionMethods : {
							read : 'POST'
						},
						reader : {
							type : 'json',
							root : 'result',
							totalProperty : 'total'
						}
					},
					fields : ['ddValue', 'ddCodeValue'],
					listeners : {
						beforeload : function(s) {
							var new_params = {className:className||''}
							Ext.apply(s.proxy.extraParams, new_params);
						}
					}
				});
		return ddKeyStore;
	}
};