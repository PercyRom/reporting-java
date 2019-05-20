var Roles = function() {
var HTML ="";
	return {
		init : function(uri) {
			
			$(window).on("load", function (e) {
				var numPagina = $('#numPagina').val();
				var cantidadFilasPagina = $('#cantidadFilasPagina').val();
				getRoles(numPagina, cantidadFilasPagina);
				getOpciones();
				onChangeCantidadFilas();
				onClickSave();
				onHiddenModal();
				onChangeVigencia();
			})
			
			function getRoles(vNumPagina, vFilasPagina){
				$('#loading').modal('show');
				$.ajax({
					url : uri+'seguridad/listarRoles',
					data : obtenerDatosFiltro(vNumPagina,vFilasPagina),
					type : "POST",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					beforeSend : function() {
						//$("#btnBuscar").attr("disabled","disabled");
						$('#tbody-roles').html('');
						$('#ul-paginado').html('');
						$(".no-records-found").show();
					},
					error : function(ts) {
						$('#loading').modal('hide');
						//$("#btnBuscar").removeAttr("disabled");
					},
					success : function(data) {
						//console.log(data);
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
							$.each(data.listObjetos, function(i, e) {
								HTML="<tr>";
								HTML+="<td>"+e.idRol+"</td>";
								HTML+="<td>"+e.nombreRol+"</td>";
								HTML+="<td>"+e.descripcionRol+"</td>";
								HTML+="<td style='text-align: center;'><div data-toggle='tooltip' title='"+(e.vigente=="S"?'Vigente':'No Vigente')+"'><i class='fas "+(e.vigente=="S"?'fa-check-circle':'fa-minus-circle')+"'></i></div></td>";
								HTML+="<td style='padding: 0px !important; margin: 0px; text-align: center; vertical-align: middle;'>";
								HTML+="<a href='#' id='btnEdit-"+e.idRol+"' data-toggle='modal' data-target='#modalForm' class='btnEdit btn btn-light mb-2' style='margin: 0px !important;'>"; 
								HTML+="<span class='vm-morado' data-toggle='tooltip' title='Editar'><i class='fas fa-pen fa-sm'></i></span></a></td></tr>";
			
								$('#tbody-roles').append(HTML);
								HTML ="";
							});
							Util.tooltip();
							onClikcBtnEditUser();
						}
					}
				}).done(function( data ) {
					onClickPaginator();
					$('#loading').modal('hide');
				});
				
			}
			function getOpciones(){
				$('#loading').modal('show');
				$.ajax({
					url : uri+'seguridad/listarOpciones',
					type : "GET",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					error : function(ts) {
						$('#loading').modal('hide');
					},
					success : function(data) {
						////console.log(data);
						if(data.length >0){
							$.each(data, function(i, e) {
								HTML ="<tr class='"+ (e.esMenu=="N"?"table-primary":"") +"'><td><div class='custom-control custom-checkbox mr-sm-2' style='text-align:center;'>";
								if(e.esMenu=="S"){
								HTML+="<input type='checkbox' value='"+e.idOpcion+"' class='custom-control-input' data-validation='check' data-check-group='chkGroupOpciones' data-validation-text='Debe seleccionar al menos una Opcion' data-parent='table-opciones' id='chkOpcion-"+e.idOpcion+"'>";
								HTML+="<label class='custom-control-label' for='chkOpcion-"+e.idOpcion+"' style='width: 100%; text-align: center;'>"+e.nombreOpcion+"</label>";
								}else{
									HTML+="<span>"+e.nombreOpcion+"</span>";
								}	
								HTML+="</div></td></tr>";
								$('#tbody-opciones').append(HTML);
								HTML="";
								
							});
						}
						Form.init();
					}
				}).done(function( data ) {
					onClickPaginator();
					$('#loading').modal('hide');
				});
				
			}
			function getRol(vId){
				$('#loading').modal('show');
				$.ajax({
					url : uri+'seguridad/loadRol',
					data : vId,
					dataType: "text",
					type : "POST",
					contentType : "application/json; charset=utf-8",
					beforeSend : function() {
						$(".btnEdit").each(function() {$(this).attr("disabled","disabled");});
					},
					error : function(ts) {
						//console.log("error al obtener usuario");
						$('#loading').modal('hide');
						$(".btnEdit").each(function() {$(this).removeAttr("disabled","disabled");});
					},
					success : function(data) {
						//console.log("obtener exitoso");
						data = jQuery.parseJSON(data);
						//console.log(data);
						
						$.each(data.listOpciones, function(i, e) {
							$("input[type='checkbox'][data-check-group='chkGroupOpciones'][value='"+e.idOpcion+"']").prop('checked', true);
						});
						
						$("#inputFormId").val(data.rol.idRol);
						$("#txtFormNombre").val(data.rol.nombreRol);
						$("#txtFormDescripcion").val(data.rol.descripcionRol);
						$("#opcion-vigencia").show();
						$("input:radio[name=radioVigente][value='"+data.rol.vigente+"']").prop('checked', data.rol.vigente==null?false:(data.rol.vigente=='S'?true:false));
					    
//						$('#modalUsuario').modal('hide');
						
					}
				}).done(function() {
					$('#loading').modal('hide');
					$(".btnEdit").each(function() {$(this).removeAttr("disabled","disabled");});
				});
			}
			function saveRol(){
				$('#loading').modal('show');
				var validate = Form.validate("formModal");
				if(validate){
					$.ajax({
						url : uri+'seguridad/saveRol',
						data : obtenerDatos(),
						dataType: "text",
						type : "PUT",
						contentType : "application/json; charset=utf-8",
						beforeSend : function() {
							$("#btnSave").attr("disabled","disabled");
						},
						error : function(ts) {
							//console.log("error al registrar");
							$('#loading').modal('hide');
							$("#btnSave").removeAttr("disabled");
						},
						success : function(data) {
							//console.log("registro exitoso");
							
							var numPagina = $('#numPagina').val();
							var cantidadFilasPagina = $('#cantidadFilasPagina').val();
							getRoles(numPagina, cantidadFilasPagina);
							$('#modalForm').modal('hide');
							
						}
					}).done(function() {
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
			function obtenerDatosFiltro(vNumPagina,vFilasPagina){
				
				var nombreRol = $("#nombreRol").val();
				var vigente = $("#vigente").val();
				
		        var obj = {
		        		numPagina : vNumPagina,
		        		cantidadFilasPagina : vFilasPagina,
		        		nombreRol : null,
		        		vigente : null,
				   };

				return JSON.stringify(obj);
			}
			function obtenerDatos(){
				
				$("input:radio[name=radioVigente][value='1']").prop('checked', true);
				
				var idRol = $("#inputFormId").val();
				var nombreRol = $("#txtFormNombre").val();
				var descripcionRol = $("#txtFormDescripcion").val();
				var vigente = $('input:radio[name=radioVigente]:checked').val();
				var opciones ="";
				$("input[data-check-group='chkGroupOpciones']").each(function(){
					if($(this).is(':checked')){
						opciones += $(this).val()+",";
					}
				});
				opciones = opciones.substring(0, opciones.length-1);
				
		        var obj = {
		        		idRol: idRol,
		        		nombreRol: nombreRol,
		        		descripcionRol: descripcionRol,
		        		vigente: vigente,
		        		opciones: opciones,
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
						
						getRoles(numPage,filasPorPagina);
						$('#numPagina').val(numPage);
						
					});
				});
			}
			function onClikcBtnEditUser(){
				$(".btnEdit").each(function() {
					$(this).click(function(){
						var $id = $(this).attr("id").replace("btnEdit-","");
						getRol($id);
					});
					
				});
			}
			function onChangeCantidadFilas(){
				$("#cantidadFilasPagina").change(function() {
					var filasPorPagina = $(this).val();
					getRoles(1,filasPorPagina);
					$('#cantidadFilasPagina').val(filasPorPagina);
					$('#numPagina').val("1");
				});
			}
			function onHiddenModal(){
				$('#modalForm').on('hidden.bs.modal', function (e) {
					$("#inputFormId").val("0");
					$("#txtFormNombre").val("");
					$("#txtFormDescripcion").val("");
					$("#opcion-vigencia").hide();
					$("input:radio[name=radioVigente][value='1']").prop('checked', true);
					Form.clean();
				})
			}
			function onClickSave(){
				$('#btnSave').click(function(){
					saveRol();
				});
			}
			function onChangeVigencia(){
				$iconVigencia = $("#icon-vigencia");
				$("input:radio[name=radioVigente]").each(function() {
					$(this).change(function(){
						if($(this).is(':checked')){
							$iconVigencia.removeClass("fas");
							$iconVigencia.removeClass("far");
							if($(this).val()==1){
								$iconVigencia.addClass("fas");
							}else{
								$iconVigencia.addClass("far");
							}
						}
					});
					
				});
			}
			
		}
	};

}();