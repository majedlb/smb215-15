Ext.Loader.setConfig({
	enabled : true
});

Ext.onReady(function(){

	Ext.onReady(function() {
		  setTimeout(function(){
		    Ext.get('loading').remove();
		  }, 250);
		});

	Ext.tip.QuickTipManager.init();  // enable tooltips
	
	var richWidgetPanel = Ext.create('Ext.form.Panel', {
	    width: 1000,
	    height: 600,
	    bodyPadding: 30,
	    id: 'richWidgetPanel',
	    title: 'Rich UI Widgets'
	});
	
	richWidgetPanel.render('richWidgetPanel');
	
	var formWidgetPanel = Ext.create('Ext.form.Panel', {
	    width: 1000,
	    height: 700,
	    bodyPadding: 30,
	    id: 'formWidgetPanel',
	    title: 'Form Fields'
	});
	
	formWidgetPanel.render('formWidgetPanel');
	
	var buttonWidgetPanel = Ext.create('Ext.form.Panel', {
	    width: 1000,
	    height: 150,
	    bodyPadding: 30,
	    id: 'buttonWidgetPanel',
	    title: 'Buttons'
	});
	
	buttonWidgetPanel.render('buttonWidgetPanel');
	
	var advancedFormFieldsPanel = Ext.create('Ext.form.Panel', {
	    width: 1000,
	    height: 350,
	    bodyPadding: 30,
	    id: 'advancedFormFieldsPanel',
	    title: 'Advanced Form Fields'
	});
	
	advancedFormFieldsPanel.render('advancedFormFieldsPanel');
	
	new Ext.LoadMask(Ext.getCmp('richWidgetPanel'), {msg:"Loading, please wait..."}).show();
	new Ext.LoadMask(Ext.getCmp('formWidgetPanel'), {msg:"Loading, please wait..."}).show();
	new Ext.LoadMask(Ext.getCmp('buttonWidgetPanel'), {msg:"Loading, please wait..."}).show();
	new Ext.LoadMask(Ext.getCmp('advancedFormFieldsPanel'), {msg:"Loading, please wait..."}).show();

//	myMask.show();
	
	Ext.Ajax.request({
		  url: 'ext-js-rich-ui-widgets.html',
		  success: function(response){
			var htmlText= response.responseText;
			var richWidgets = Ext.getCmp('richWidgetPanel');
			richWidgets.update(htmlText, true);
		  }
	});
	
	Ext.Ajax.request({
		  url: 'ext-js-advanced-form-widgets.html',
		  success: function(response){
			var htmlText= response.responseText;
			var advancedFormFields = Ext.getCmp('advancedFormFieldsPanel');
			advancedFormFields.update(htmlText, true);
		  }
	});
	
	Ext.Ajax.request({
		  url: 'ext-js-form-widgets.html',
		  success: function(response){
			var htmlText= response.responseText;
			var formWidgets = Ext.getCmp('formWidgetPanel');
			formWidgets.update(htmlText, true);
		  }
	});
	
	Ext.Ajax.request({
		  url: 'ext-js-button-widgets.html',
		  success: function(response){
			var htmlText= response.responseText;
			var buttonWidgets = Ext.getCmp('buttonWidgetPanel');
			buttonWidgets.update(htmlText, true);
		  }
	});
	
});