
var REGEX_EMAIL = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
var REGEX_EMAIL_2 = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
var REGEX_TEXT_SPACES = /[a-zA-Z]+(\s[a-zA-Z]+)*$/;
var REGEX_ALPHANUMERIC = "^[a-zA-Z0-9]+$";
var REGEX_ALPHANUMERIC_SPACES = /^[a-z\d\-_\s]+$/i;
var REGEX_ONLY_NUMERIC = /^\d*$/;
var BODY;
var VALIDATE = true;
var FIRST_ERROR = null;
//var ALERT = "<div class='alert alert-danger' role='alert'>$MENSAJE$</div>";
var ALERT = "<div class='invalid-feedback' style='display:inline'>$MENSAJE$</div>";
var ALERT_CLASS = "invalid-feedback";
var CONTENT_PAGINATOR;
var HTML ="";

var SELECTOR_INPUT_CHECK_SELECT = "select[required],input[required],input[data-validation]";

var TYPE_ALPHANUMERIC = "alphanumeric";
var TYPE_ALPHANUMERIC_SPACES = "alphanumeric-spaces";
var TYPE_ONLY_NUMERIC = "only-numeric";
var TYPE_EMAIL = "email";
var TYPE_ONLY_TEXT = "only-text";
var TYPE_TEXT_SPACES = "text-spaces";
//PARA CHECKBOX
var TYPE_CHECK = "check";
var TYPE_PASSWORD = "password";
var TYPE_REPASSWORD = "repassword";

var TYPE_ADD_LENGTH = "length";

var Form = function() {

	return {
		init: function() {
			init();
		},
		validate : function(vIdForm){
			return validate(vIdForm);
		},
		paginator:function(idContentPaginador,vNumPagina,totalPaginas){
			paginator(idContentPaginador,vNumPagina,totalPaginas);
		},
		clean: function(){
			clean();
		},
		cleanContent: function(vIdForm){
			cleanContent(vIdForm);
		},
		removeClassValidation: function(vIdCampo){
			removeClassValidation(vIdCampo);
		},
		showMsgOwnValidation: function(vId, message){
			show_msg_own_validation(vId, message)
		}
	};

}();

function init(){
	BODY = $("body");
	BODY.find(SELECTOR_INPUT_CHECK_SELECT).each(function(){
	VALIDATE = true;
	FIRST_ERROR = null;
		var input = $(this);
		var data_validation = input.attr("data-validation");
		var data_required = input.attr("required");
		
		var data_length_min 			=input.attr("data-length-min");
		var data_length_max 			=input.attr("data-length-max");
		
		if(!(typeof data_required === "undefined")){
			input.on("keypress keyup blur change",function (event) {
				validate_input(input, "required");
		    });
		}
		if(data_validation == TYPE_ALPHANUMERIC){
			input.keypress(function (event) {
				var regex = new RegExp(REGEX_ALPHANUMERIC);
		        if (!regex.test(String.fromCharCode(!event.charCode ? event.which : event.charCode))) {
		            event.preventDefault();
		            return false;
		        }
		    });
			input.keyup(function () {
				validate_input(input, data_validation);
			});
		}
		if(data_validation == TYPE_ALPHANUMERIC_SPACES){
			input.on("keypress keyup blur",function (event) {
				var $this = $(this);
				  $(this).val($this.val().replace(/(\s{2,})|[^a-zA-Z0-9']/g, ' ').replace(/^\s*/, ''));
		    });
			input.keyup(function () {
				validate_input(input, data_validation);
		    });
			input.change(function () {
				input.val( input.val().trim() );
				validate_input(input, data_validation);
		    });
		}
		if(data_validation == TYPE_ONLY_NUMERIC){
			input.on("keypress keyup blur",function (event) {
				var regex = new RegExp(REGEX_ONLY_NUMERIC);
				if (!regex.test(String.fromCharCode(!event.charCode ? event.which : event.charCode))) {
		            event.preventDefault();
		            return false;
		        }
			})
			.keyup(function(){
				$(this).val($(this).val().replace(/[^\d].+/, ""));
			});
		}
		if(data_validation == TYPE_EMAIL){
			input.keyup(function () {
				validate_input(input, data_validation);
			});
		}
		if(data_validation == TYPE_ONLY_TEXT){
			input.on("keyup blur",function (event) {
				var node = $(this);
			    node.val(node.val().replace(/[^a-z]/g,'') );
		    });
		}
		if(data_validation == TYPE_TEXT_SPACES){
			input.on("keypress keyup blur",function (event) {
				var $this = $(this);
				  $(this).val($this.val().replace(/(\s{2,})|[^a-zA-Z']/g, ' ').replace(/^\s*/, ''));
		    });
			input.keyup(function () {
				validate_input(input, data_validation);
		    });
			input.change(function () {
				input.val( input.val().trim() );
				validate_input(input, data_validation);
		    });
		}
		if(data_validation == TYPE_CHECK){
			input.change(function () {
				validate_input(input, data_validation);
			});
		}
		if(data_validation == TYPE_PASSWORD || data_validation == TYPE_REPASSWORD){
			input.keypress(function (event) {
				var regex = new RegExp(REGEX_ALPHANUMERIC);
		        if (!regex.test(String.fromCharCode(!event.charCode ? event.which : event.charCode))) {
		            event.preventDefault();
		            return false;
		        }
		    });
			if(data_validation == TYPE_PASSWORD){
				input.first().keyup(function () {
					validate_input(input, data_validation);
				});
			}
		}
		if(data_validation == TYPE_REPASSWORD){
			input.change(function () {
				validate_input(input, data_validation);
			});
		}
		
		if(!(typeof data_length_min === "undefined") || !(typeof data_length_max === "undefined")){
			input.on("keypress keyup change",function (event) {
				validate_input(input, TYPE_ADD_LENGTH);
			});
		}
		
	});
	
}

function validate_input(input, type_validation){
	//que los mensajes se muestren del mismo input
	var data_show_alert_myself		=input.attr("data-show-alert-myself");
	var data_required				=input.attr("required");
	var data_validation_text 		=input.attr("data-validation-text");
	var data_validation_text_format =input.attr("data-validation-text-format");
	
	var data_length_min 			=input.attr("data-length-min");
	var data_length_max 			=input.attr("data-length-max");
	var data_length_msg 			=input.attr("data-length-msg");
	var data_parent 				=input.attr("data-parent");
	
	var valid = true;
	var parent = input.parent();
	if(!(typeof data_show_alert_myself === "undefined")){
		parent = input;
	}
	var alert = parent.parent().find("."+ALERT_CLASS);
	if(alert.length > 0){
		alert.remove();
	}
	$("#"+data_parent).parent().find("."+ALERT_CLASS).remove();
	
	input.removeClass("is-invalid");
	input.removeClass("is-valid");
	
	if( !(typeof data_required === "undefined") && (input.val() ==null ||input.val().length==0) ){
		parent.after( ALERT.replace("$MENSAJE$",(typeof data_validation_text === "undefined")?
									"Dato es requerido":
									data_validation_text) );
		input.addClass("is-invalid");
		valid = false;
		
	}else if(type_validation==TYPE_EMAIL && 
			!REGEX_EMAIL_2.test(input.val()) ){
		parent.after( ALERT.replace("$MENSAJE$",(typeof data_validation_text_format === "undefined")?
									"Formato de correo no es correcto":
									data_validation_text_format) );
		input.addClass("is-invalid");
		valid = false;
		
//	}else if(type_validation==TYPE_TEXT_SPACES && 
//			!REGEX_TEXT_SPACES.test(input.val()) ){
//		parent.after( ALERT.replace("$MENSAJE$","El campo no debe contener espacios en blanco al comienzo ni al final") );
//		input.addClass("is-invalid");
//		valid = false;
	}else if(type_validation=="repassword"){
		if(input.val()!=$("input[data-validation][data-validation='password']").val()){
			parent.after( ALERT.replace("$MENSAJE$","El campo no coincide con la clave") );
			input.addClass("is-invalid");
			valid = false;
		}
	}else if(type_validation==TYPE_CHECK){
		
		var data_check_group = input.attr("data-check-group");
		var checked = false;
		
		BODY.find("input[data-validation='check'][data-check-group='"+data_check_group+"']").each(function(){
			$(this).removeClass("is-invalid");
			if($(this).is(':checked')){
				checked = true
			}
		});
		if(!checked){
			$("#"+data_parent).after( ALERT.replace("$MENSAJE$",(typeof data_validation_text === "undefined")?
					"Dato es requerido":
						data_validation_text) );
			//input.addClass("is-invalid");
			valid = false;
		}
		
	}else if(type_validation==TYPE_ADD_LENGTH){
		if( input.val().length>0 && (data_length_min!=0 || data_length_max!=0) ){
			if(input.val().length<data_length_min){
				parent.after( ALERT.replace("$MENSAJE$",(typeof data_length_msg === "undefined")?
						"El campo debe tener como minimo "+data_length_min+" valores":
							data_length_msg) );
				input.addClass("is-invalid");
				valid = false;
			}
			if(input.val().length>data_length_max){
				console.log("max");
				parent.after( ALERT.replace("$MENSAJE$",(typeof data_length_msg === "undefined")?
						"El campo debe tener como maximo "+data_length_max+" valores":
							data_length_msg) );
				input.addClass("is-invalid");
				valid = false;
			}
		}
	}
	
	if(valid){
		if(type_validation!="check"){
				input.addClass("is-valid");
			}
		}else{
			VALIDATE = valid;
			if(FIRST_ERROR==null){
				FIRST_ERROR = input;
			}
	}
}

function validate(vIdForm){
	VALIDATE = true;
	FIRST_ERROR = null;
	$("#"+vIdForm).find(SELECTOR_INPUT_CHECK_SELECT).each(function(){
		var input = $(this);
		var data_validation = input.attr("data-validation");
		
		validate_input(input, data_validation);
//		//console.log("[VALIDATE]["+VALIDATE+"]");
	});
	if(FIRST_ERROR!=null){
		FIRST_ERROR.focus();
	}
	return VALIDATE;
}

function paginator(idContentPaginador,vNumPagina,totalPaginas){
	CONTENT_PAGINATOR = "#"+idContentPaginador;
	var vMaxPaginas = 5;
	var vPagCentral = Math.round(parseFloat(vMaxPaginas)/2);
	var vPagIni = 1;
	var vPagFin = totalPaginas;
	$(CONTENT_PAGINATOR).html('');
	$(CONTENT_PAGINATOR).append("<nav><ul class='pagination justify-content-center'></ul></nav>");
	
	if(totalPaginas>vMaxPaginas){
		vPagIni = vNumPagina-vPagCentral+1;
		if(vPagIni<1){
			vPagIni = 1;
		}
		vPagFin = vPagIni+vMaxPaginas-1;
		if(vPagFin>totalPaginas){
			vPagFin = totalPaginas;
			vPagIni = vPagFin-vMaxPaginas+1;
		}
	}
	loadPage(vNumPagina==1?"N":"S","Anterior");
	for (var i = vPagIni; i <= vPagFin; i++) {
		loadPage(i==vNumPagina?"N":"S",i);
	}
	if(totalPaginas>vMaxPaginas && vPagFin != totalPaginas){
		loadPage("N","...");
	}
	loadPage(vNumPagina==totalPaginas?"N":"S","Siguiente");
}
function loadPage(state,page){
	HTML = "<li class='page-item "+(state=="S"?"":"disabled")+"'>";
	HTML+="<a class='page-link' href='#' "+(state=="S"?"":"tabindex='-1'aria-disabled='true'")+">"+page+"</a>";
	HTML+="</li>";
	$(CONTENT_PAGINATOR+'>nav>ul').append(HTML);
	HTML = "";
}
function removeClassValidation(vIdCampo){
	
	var input = BODY.find("#"+vIdCampo);
	var data_show_alert_myself		=input.attr("data-show-alert-myself");
	var data_parent 				=input.attr("data-parent");
	
	input.removeClass("is-invalid");
	input.removeClass("is-valid");
	
	var parent = input.parent();
	if(!(typeof data_show_alert_myself === "undefined")){
		parent = input;
	}
	var alert = parent.parent().find("."+ALERT_CLASS);
	if(alert.length > 0){
		alert.remove();
	}
	$("#"+data_parent).parent().find("."+ALERT_CLASS).remove();
}

function clean(){
	BODY.find("."+ALERT_CLASS).each(function(){
		$(this).remove();
	});
	BODY.find(SELECTOR_INPUT_CHECK_SELECT).each(function(){
		$(this).removeClass("is-invalid");
		$(this).removeClass("is-valid");
		if($(this).is(':checked')){
			$(this).prop('checked', false);
		}
		
	});
}
function cleanContent(vIdForm){
	$("#"+vIdForm).find("."+ALERT_CLASS).each(function(){
		$(this).remove();
	});
	$("#"+vIdForm).find(SELECTOR_INPUT_CHECK_SELECT).each(function(){
		$(this).removeClass("is-invalid");
		$(this).removeClass("is-valid");
		if($(this).is(':checked')){
			$(this).prop('checked', false);
		}
		
	});
}
function valid_id(vId){
	result = true;
	if(vId==null || vId=="" || !vId.startsWith("#")){
		result = false;
		alert("ID: "+vId+" HTML NO ES VALIDO");
	}
	return result;
}
function show_msg_own_validation(vId, message){
	if(valid_id(vId)){
		var input = $(vId);
		
		input.parent().find("."+ALERT_CLASS).remove();
		
		input.addClass("is-invalid");
		$( ALERT.replace("$MENSAJE$",(message == null || message =="")?
				"Campo Requerido":
					message) )
		.insertAfter(input);
	}
}