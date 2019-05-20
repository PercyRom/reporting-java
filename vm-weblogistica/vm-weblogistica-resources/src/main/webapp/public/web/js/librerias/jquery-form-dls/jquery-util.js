var Util = function() {
	return {
		tooltip : function(){
			tooltip();
		},
		windowHeight : function (){
			windowHeight();
		},
		tableScrollable : function(){
			tableScrollable();
		}
	};
}();

function tooltip(){
	$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})
}
function windowHeight(){
	var HEIGHT_WINDOW = $(window).height();
	var HEIGHT_HEADER = $("#header").outerHeight(true);
	var hMax = HEIGHT_WINDOW-HEIGHT_HEADER;
	
	$("#listadoMenu").css("min-height",hMax+"px");
	$("#content-wrapper").css("max-height",hMax+"px");
}
function tableScrollable(){
	
	if( $(".content-table").length == 0){
		var $table = $(".table-content-scrollable");
		var $table_clone = $(".table-content-scrollable").clone();
		
		$table.before("<div class='content-table'></div>");
		$table.detach().appendTo(".content-table");
		$(".content-table>.table-content-scrollable>tbody").remove();
		
		$content_table = $(".content-table");
		$table = $(".table-content-scrollable");
		$table.after("<div class='body-scrollable'></div>");
		$table_clone.appendTo(".body-scrollable");
		$(".body-scrollable>.table-content-scrollable>thead").remove();
		$table_clone = null;
	}
	
	//para que las columnas de las cabeceras se alinien a las del cuerpo
	$( window ).resize(function() {
//		console.log("resize");
		$(".body-scrollable>.table-content-scrollable tr").first().find("td").each(function(i,e){
			var width = $(this).css("width");
			var th = $(".content-table>.table-content-scrollable th:gt("+i+")");
			th.prop("width", width+"!important" );
		});
		Util.windowHeight();
	});
}

$(function () {
	$( document ).ajaxStart(function() {
		$('#loading').modal('show');
	});
	$( document ).ajaxComplete(function() {
		$('#loading').modal('hide');
	});
	$( document ).ajaxError(function() {
		$('#loading').modal('hide');
	});
	Util.tooltip();
	Util.windowHeight();
	Util.tableScrollable();
});