var MntoEntidad = function() {
var HTML ="";
	return {
		init : function(uri) {
			
			$(window).on("load", function (e) {
				onShowContacto();
				onClickSave();
				onClickAddContacto();
			});
			
			function save(){
				let _data = obtenerDatos();
			
				let validate = Form.validate("formEntidad");
				
				if(validate){
					$.ajax({
						url : uri+'maestras/entidad/save',
						data : _data,
						dataType: "json",
						type : "POST",
						contentType : "application/json; charset=utf-8",
						beforeSend : function() {

						},
						error : function(ts) {
							
						},
						success : function(data) {
							
							let contactos = data.entidad.contactos;
							let row = 1;
							$.each(contactos, function (i,item){
								let colDNI = $('#tbContactos tr:eq('+row+') td:eq(4)');
								if(colDNI.text() === item.documentoIdentidad){
									cell.html("hallo");
								}
								row++;
							});
							
						}

					}).done(function() {
						/*
						let fcelltext = $('#tbContactos').parent().parent().find("td:first-child").text();
						$('#tbContactos').html(fcelltext);
						
						var row = 0;
						var col = 0;
						var cell = $('#tbContactos tr:eq(' + row + ') td:eq(' + col + ')');
						*/
						
					});
				}else{
					if ($('#modalForm:visible').length) { 
			            $('body').addClass('modal-open');
			        }
				}
				
			}
			
			function addContacto(){
				let item = $('#tbContactos tbody tr').length +1;
				
				let idEntidadContacto 	= $("#idEntidadContacto").val();
				let tipoDocumento 		= $("#cboFormTipoDocumento").val();
				let nroDocumento 		= $("#txtFormNroDocumento").val();
				let apePaterno 			= $("#txtFormApePaterno").val();
				let apMaterno 			= $('#txtFormApeMaterno').val();
				let nombres 			= $("#txtFormNombres").val();
				let telefono 			= $("#txtFormTelefono").val();
				let vigente				= $("#radioContactoVigente_S").val();
				
				let HTML;
				HTML="<tr>";
				HTML+="<td style='display: ;'>"+ item 						+ "</td>";
				HTML+="<td style='display: none; '>"+ true 					+ "</td>";
				HTML+="<td style='display: none; '>"+ idEntidadContacto		+ "</td>";
				HTML+="<td style='display: none; '>"+ tipoDocumento			+ "</td>";
				HTML+="<td style='display: ;'>"+ nroDocumento.toUpperCase() + "</td>";
				HTML+="<td style='display: ;'>"+ apePaterno.toUpperCase() 	+ "</td>";
				HTML+="<td style='display: ;'>"+ apMaterno.toUpperCase() 	+ "</td>";
				HTML+="<td style='display: ;'>"+ nombres.toUpperCase() 		+ "</td>";
				HTML+="<td style='display: ;'>"+ telefono.toUpperCase() 	+ "</td>";
				HTML+="<td style='padding: 0px !important; margin: 0px; text-align: center; vertical-align: middle;'>"+ vigente.toUpperCase() + "</td>";
				HTML+="<td style='padding: 0px !important; margin: 0px; text-align: center; vertical-align: middle;'>";
				HTML+="<a href='#' id='btnEdit-"+item+"' data-toggle='modal' class='btnEdit btn btn-light mb-2' style='margin: 0px !important; padding: 0px 10px 0px 10px;'>"; 
				HTML+="<span class='vendemas-morado' data-toggle='tooltip' title='Editar Entidad'><i class='fas fa-pen fa-sm'></i></span></a>";
				HTML+="</td>";
				HTML+="</tr>";
				
				$('#tbody-contactos').append(HTML);
				
				$('#modalFormContacto').modal('hide');
			}
			
			function obtenerDatos(){
				
				let idEntidad	 	= $("#txhIdEntidad").val();
				let nombre 			= $("#txtFormNombre").val();
				let nombreComercial = $("#txtFormNombreComercial").val();
				let tipoEntidad 	= $("#cboFormTipoEntidad").val();
				let tipoComprobante = $("#txtFormRUC").val();
				let pais	 		= $("#txtFormPais").val();
				let nbo 			= $("#txtNbo").val();
				let vigente 		= $('input:radio[name=radioVigente]:checked').val();
				
				let jsonEntidad = {
		        		idEntidad					: idEntidad,
		        		nombreEntidad				: nombre,
		        		nombreComercial				: nombreComercial,
		        		idTipoEntidad				: tipoEntidad,
		        		identificadorFacturacion	: tipoComprobante,
		        		codigoPais					: pais,
		        		codigoNbo					: nbo,
		        		vigente						: vigente
				   };
		        
		        let jsonContactos = $('#tbContactos').tableToJSON({ignoreColumns:[0,10]});
		        
//		        $.each(jsonContactos, function(k, v) {
//		        	jsonContactos[k].idEntidadContacto = '0';
//		        }); 
		        
		        jsonEntidad.contactos = jsonContactos;

		        return JSON.stringify(jsonEntidad);
			}
			
			function onClickSave(){
				$('#btnSave').click(function(){
					save();
				});
			}
			
			function showContacto(){
				$('#modalFormContacto').modal('show');
			}
			
			function onShowContacto(){
				$('#btnContacto').click(function(){
					showContacto();
				});
			}
			
			function onClickAddContacto(){
				$('#btnAddContacto').click(function(){
					addContacto();
				});
			}
			
		}
	};

}();