var Entidad = function() {
var HTML ="";
	return {
		init : function(uri) {
			$(window).on("load", function (e) {
				
				onChangeCantidadFilas();
				onClickSave();
				onHiddenModal();
				//fillCboTipoEntidad();
				
				var numPagina = $('#numPagina').val();
				var cantidadFilasPagina = $('#cantidadFilasPagina').val();
				getEntidades(numPagina, cantidadFilasPagina);
				
			})
			
			function getEntidades(vNumPagina, vFilasPagina){
				$('#loading').modal('show');
				$.ajax({
					url : uri+'maestras/listarEntidades',
					data : obtenerDatosFiltro(vNumPagina,vFilasPagina),
					type : "POST",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					beforeSend : function() {
						$('#tbody-entidades').html('');
						$('#ul-paginado').html('');
						$(".no-records-found").show();
					},
					error : function(ts) {
						$('#loading').modal('hide');
					},
					success : function(data) {
						$(".total-registros").hide();
						if(data.listObjetos.length >0){
							if(data.totalObjetosBD == data.listObjetos.length){
								$(".total-registros").text("Mostrando "+data.totalObjetosBD+" registros.");
							}else{
								$(".total-registros").text("Mostrando "+data.listObjetos.length+" de "+data.totalObjetosBD+" registros.");
							}
							
							$(".total-registros").show();
							
							$(".no-records-found").hide();
							loadPaginator(vNumPagina,data.totalPaginas);
							
							let red = "";
							
							$.each(data.listObjetos, function(i, e) {
								
								let url = uri + "maestras/entidad/" ;
								url =  url + e.idEntidad;
								
								red = (e.vigente === 'N') ? "color: red;" : "color: black;";
								
								
								HTML="<tr style='" + red + "'>";
								HTML+="<td>"+e.identificadorFacturacion+"</td>";
								HTML+="<td>"+e.nombreEntidad+"</td>";
								HTML+="<td>"+e.nombreTipoEntidad+"</td>";
								HTML+="<td style='text-align: center;'>"+e.codigoPais+"</td>";
								HTML+="<td style='text-align: center;'><div data-toggle='tooltip' title='"+(e.vigente=="S"?'Vigente':'No Vigente')+"'><i class='fas "+(e.vigente=="S"?'fa-check-circle':'fa-minus-circle')+"'></i></div></td>";
								HTML+="<td style='padding: 0px !important; margin: 0px; text-align: center; vertical-align: middle;'>";
								HTML+="<a href='" + url + "' target='_self' class='btnEdit btn btn-light mb-2' style='margin: 0px !important; padding: 0px 10px 0px 10px;'>"; 
								HTML+="<span class='vendemas-morado' data-toggle='tooltip' title='Editar Entidad'><i class='fas fa-pen fa-sm'></i></span></a>";
								HTML+="</td>";
								HTML+="</tr>";
								$('#tbody-entidades').append(HTML);
								HTML ="";
							});
							$(function () {
							  $('[data-toggle="tooltip"]').tooltip()
							})
							//onClikcBtnEditUser();
						}
						Form.init();
					}
				}).done(function( data ) {
					onClickPaginator();
					$('#loading').modal('hide');
				});
				
			}
			function getEntidad(vId){
				$('#loading').modal('show');
				$.ajax({
					url : uri+'maestras/entidad/byId',
					data : vId,
					dataType: "text",
					type : "POST",
					contentType : "application/json; charset=utf-8",
					beforeSend : function() {
						$(".btnEdit").each(function() {$(this).attr("disabled","disabled");});
					},
					error : function(ts) {
						$(".btnEdit").each(function() {$(this).removeAttr("disabled","disabled");});
					},
					success : function(data) {
						$('#loading').modal('hide');
						
						data = jQuery.parseJSON(data);
						
						console.log(data);

						$("#inputFormId").val(data.entidad.idEntidad);
						$("#txtFormNombre").val(data.entidad.nombreEntidad);
						$("#txtFormNombreComercial").val(data.entidad.nombreComercial);
						$('#cboFormTipoEntidad').val(data.entidad.idTipoEntidad);
						$("#txtFormRUC").val(data.entidad.identificadorFacturacion);
						$("#txtFormPais").val(data.entidad.codigoPais);
						$("#txtNbo").val(data.entidad.codigoNbo);
						
						$("input:radio[name=radioVigente][value='"+data.entidad.vigente+"']").prop('checked', true);
						
						$('#modalForm').modal('show');
					}
				}).done(function() {
					$(".btnEdit").each(function() {$(this).removeAttr("disabled","disabled");});
				});
			}
			function save(){
				$('#loading').modal('show');
				var validate = Form.validate("formModal");
				if(validate){
					$.ajax({
						url : uri+'maestras/entidad/save',
						data : obtenerDatos(),
						dataType: "text",
						type : "PUT",
						contentType : "application/json; charset=utf-8",
						beforeSend : function() {
							$("#btnSave").attr("disabled","disabled");
						},
						error : function(ts) {
							$('#loading').modal('hide');
							$("#btnSave").removeAttr("disabled");
						},
						success : function(data) {
							$('#modalForm').modal('hide');
						}

					}).done(function() {
						var numPagina = $('#numPagina').val();
						var cantidadFilasPagina = $('#cantidadFilasPagina').val();
						getEntidades(numPagina, cantidadFilasPagina);
						
						$('#loading').modal('hide');
						$("#btnSave").removeAttr("disabled");
					});
				}else{
					$('#loading').modal('hide');
					if ($('#modalForm:visible').length) { 
			            $('body').addClass('modal-open');
			        }
				}
			}
			
			function fillCboTipoEntidad(){
				$.ajax({
					url : uri+'maestras/tipos/entidad',
					dataType: "text",
					type : "POST",
					contentType : "application/json; charset=utf-8",
					error : function(ts) {
						alert('error.. al cargar tipos de entidad.');
					},
					success : function(data) {
						let jsonData = jQuery.parseJSON(data);
						
						$.each(jsonData.tipoEntidad,function(key, value){
							
							let v = value.idTipoEntidad;
							let t = value.nombreTipoEntidad;
							let opt = '<option value=' + v + '>' + t + '</option>';
							
							$('#cboFormTipoEntidad').append(opt);
						});
					}
				}).done(function() {
					$('#loading').modal('hide');
				});
				
			}
			
			function obtenerDatosFiltro(vNumPagina,vFilasPagina){
				var obj = {
		        		numPagina : vNumPagina,
		        		cantidadFilasPagina : vFilasPagina,
		        		nombreRol : null,
		        		vigente : null,
				   };

				return JSON.stringify(obj);
			}
			function obtenerDatos(){
				
				var id			 	= $("#inputFormId").val();
				var nombre 			= $("#txtFormNombre").val();
				var nombreComercial = $("#txtFormNombreComercial").val();
				var tipoEntidad 	= $("#cboFormTipoEntidad").val();
				var tipoComprobante = $("#txtFormRUC").val();
				var pais	 		= $("#txtFormPais").val();
				var nbo 			= $("#txtNbo").val();
				var vigente 		= $('input:radio[name=radioVigente]:checked').val();
				
		        var obj = {
		        		idEntidad					: id,
		        		nombreEntidad				: nombre,
		        		nombreComercial				: nombreComercial,
		        		idTipoEntidad				: tipoEntidad,
		        		identificadorFacturacion	: tipoComprobante,
		        		codigoPais					: pais,
		        		codigoNbo					: nbo,
		        		vigente						: vigente
				   };

				return JSON.stringify(obj);
			}
			function loadPaginator(vNumPagina,totalPaginas){
				Form.paginator('section-paginador',vNumPagina,totalPaginas);
			}
			function onClickPaginator(){
				var list = $("a.page-link");
				list.each(function(i,e) {
					var obj = $(this);
					obj.click(function() {
						
						var numPage = parseInt($('#numPagina').val());
						var filasPorPagina = parseInt($('#cantidadFilasPagina').val());
						
						if(obj.text()=="Anterior"){
							numPage--;
						}else if(obj.text()=="Siguiente"){
							numPage++;
						}else{
							numPage = parseInt(obj.text());
						}
						
						getEntidades(numPage,filasPorPagina);
						$('#numPagina').val(numPage);
						
					});
				});
			}
			function onClikcBtnEditUser(){
				$(".btnEdit").each(function() {
					$(this).click(function(){
						var $id = $(this).attr("id").replace("btnEdit-","");
						getEntidad($id);
					});
					
				});
			}
			function onChangeCantidadFilas(){
				$("#cantidadFilasPagina").change(function() {
					var filasPorPagina = $(this).val();
					getEntidades(1,filasPorPagina);
					$('#cantidadFilasPagina').val(filasPorPagina);
					$('#numPagina').val("1");
				});
			}
			function onHiddenModal(){
				
				$('#modalForm').on('hidden.bs.modal', function (e) {
					/*
					$("#inputFormId").val("0");
					$("#txtFormNombre").val("");
					$("#txtFormDescripcion").val("");
					$("#opcion-vigencia").hide();
					$("input:radio[name=radioVigente][value='1']").prop('checked', true);
					*/
					Form.clean();
				})
			}
			function onClickSave(){
				$('#btnSave').click(function(){
					save();
				});
			}
			
		}
	};

}();