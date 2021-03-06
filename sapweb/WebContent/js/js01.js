//直接调用action，页面加载时就读取rfc.action

Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.panel.*',
             ]);
Ext.onReady(function(){
	Ext.define('ImageModel',{
		extend:'Ext.data.Model',
		fields:['FUNCNAME','GROUPNAME','APPL','HOST','SETEXT']
	});
	var store = Ext.create('Ext.data.JsonStore',{
		model:'ImageModel',
		proxy:{
			type:'ajax',
			url:'http://localhost:8080/sapweb/example/Rfc.action',
			reader:{
				type:'json',
				//root:'images'
			}
		}
	});
	store.load();
	
	var listView = Ext.create('Ext.grid.Panel',{
		width:800,
		height:250,
		collapsible:true,
		title:'Simple ListView<i>(0 items selected)</i>',
		renderTo:Ext.getBody(),
		store:store,
		multiSelect:true,
		viewConfig:{
			emptyText:'No items to display'
		},
		columns:[{
			text:'Function',
			flex:50,
			dataIndex:'FUNCNAME'
		},{
			text:'Group Name',
			dataIndex:'GROUPNAME'
			
		},{
			text:'APPL',
			dataIndex:'APPL',
			
		},{
			text:'HOST',
			dataIndex:'HOST'
		},{
			text:'STEXT',
			dataIndex:'STEXT'
		}]
	});
	
	listView.on('selectionchange',function(view,nodes){
		var l = nodes.length;
		var s = l!=1?'s':'';
		listView.setTitle('');
	});
	
});


