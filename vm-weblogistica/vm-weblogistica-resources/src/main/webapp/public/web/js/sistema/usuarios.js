var Usuarios = function() {
var HTML ="";
	return {
		init : function(uri) {
			
			$(window).on("load", function (e) {
				var numPagina = $('#numPagina').val();
				var cantidadFilasPagina = $('#cantidadFilasPagina').val();
				getUsuarios(numPagina, cantidadFilasPagina);
				onChangeCantidadFilas();
				onClickBtnSearch();
				onClickBtnClean();
				onHiddenModal();
				onClickSaveUsuario();
				getRoles();
				onChangeVigencia();
			})
			
			function getUsuarios(vNumPagina, vFilasPagina){
				$('#loading').modal('show');
				$.ajax({
					url : uri+'seguridad/listarUsuarios',
					data : obtenerDatosFiltro(vNumPagina,vFilasPagina),
					type : "POST",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					beforeSend : function() {
						//$("#btnBuscar").attr("disabled","disabled");
						$('#tbody-usuarios').html('');
						$(".no-records-found").show();
					},
					error : function(ts) {
						$('#loading').modal('hide');
						//$("#btnBuscar").removeAttr("disabled");
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
							
							$.each(data.listObjetos, function(i, e) {
								HTML="<tr>";
								HTML+="<td class='col-auto'>"+e.idUsuario+"</td>";
								HTML+="<td class='col-auto'>"+e.codigoUsuario+"</td>";
								HTML+="<td class='col-auto'>"+e.email+"</td>";
								HTML+="<td class='col-auto'>"+e.nombres+"</td>";
								HTML+="<td class='col-auto'>"+e.apellidoPaterno+"</td>";
								HTML+="<td class='col-auto'>"+e.apellidoMaterno+"</td>";
								HTML+="<td class='col-auto' style='padding: 0px !important; margin: 0px; text-align: center; vertical-align: middle;'>";
								HTML+="<a href='#' id='btnEdit-"+e.idUsuario+"' data-toggle='modal' data-target='#modalForm' class='btnEdit btn btn-light mb-2' style='margin: 0px !important;'>"; 
								HTML+="<span class='vm-morado' data-toggle='tooltip' title='Editar'><i class='fas fa-pen fa-sm'></i></span></a></td></tr>";
			
								$('#tbody-usuarios').append(HTML);
								HTML = "";
//								$('#tbody-usuarios').append(templateUsuarios(e));
							});
							onClikcBtnEditUser();
						}
					}
				}).done(function( data ) {
					onClickPaginator();
					$('#loading').modal('hide');
					Util.tooltip();
				});
				
			}
			
			function getRoles(){
				$('#loading').modal('show');
				$.ajax({
					url : uri+'seguridad/listarRoles',
					type : "GET",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					error : function(ts) {
						$('#loading').modal('hide');
						//$("#btnBuscar").removeAttr("disabled");
					},
					success : function(data) {
						//console.log(data);
						if(data.length >0){
							$.each(data, function(i, e) {
								HTML = "<tr><td><div class='custom-control custom-checkbox mr-sm-2'>";
								HTML+="<input type='checkbox' value='"+e.idRol+"' class='custom-control-input' data-validation='check' data-check-group='chkGroupRoles' data-validation-text='Debe seleccionar al menos un Rol' data-parent='table-roles' id='chkRol-"+e.idRol+"'>";
								HTML+="<label class='custom-control-label' for='chkRol-"+e.idRol+"' style='width: 100%; text-align: center;'>"+e.nombreRol+"</label>";
								HTML+="</div></td></tr>";
								
								$('#tbody-roles').append(HTML);
								HTML = "";
//								$('#tbody-roles').append(templateRoles(e));
							});
						}
						Form.init();
					}
				}).done(function( data ) {
					onClickPaginator();
					$('#loading').modal('hide');
				});
				
			}
			
			function getUsuario(vIdUsuario){
				$('#loading').modal('show');
				$.ajax({
					url : uri+'seguridad/loadUsuario',
					data : vIdUsuario,
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
						
						$.each(data.listRoles, function(i, e) {
							$("input[type='checkbox'][data-check-group='chkGroupRoles'][value='"+e.idRol+"']").prop('checked', true);
						});
						
						$("#inputFormId").val(data.usuario.idUsuario);
						$("#txtFormCodUsu").val(data.usuario.codigoUsuario);
						$("#txtFormEmail").val(data.usuario.email);
						$("#txtFormNombres").val(data.usuario.nombres);
						$("#txtFormApePat").val(data.usuario.apellidoPaterno);
						$("#txtFormApeMat").val(data.usuario.apellidoMaterno);
						$("#txtFormClave").val(data.usuario.clave);
						$("#txtFormReClave").val(data.usuario.clave);
						$("#opcion-vigencia").show();
						$("input:radio[name=radioVigente][value='"+data.usuario.idEstado+"']").prop('checked', data.usuario.idEstado==null?false:(data.usuario.idEstado==1?true:false));
					    
						//console.log(data);
						
//						$('#modalForm').modal('hide');
						
					}
				}).done(function() {
					$('#loading').modal('hide');
					$(".btnEdit").each(function() {$(this).removeAttr("disabled","disabled");});
				});
			}
			
			function saveUsuario(){
				$('#loading').modal('show');
				var validate = Form.validate("formModalNewUser");
				if(validate){
					$.ajax({
						url : uri+'seguridad/saveUsuario',
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
							getUsuarios(numPagina, cantidadFilasPagina);
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
			function loadPaginator(vNumPagina,totalPaginas){
				Form.paginator('section-paginador',vNumPagina,totalPaginas);
			}
			
			function obtenerDatosFiltro(vNumPagina,vFilasPagina){
				
				var codigoUsuario = $("#txtCodUsuario").val();
				var nombres = $("#txtNombres").val();
				var apellidoPaterno = $("#txtApellidoPaterno").val();
				
		        var obj = {
		        		numPagina : vNumPagina,
		        		cantidadFilasPagina : vFilasPagina,
		        		codigoUsuario: codigoUsuario,
		        		nombres: nombres,
		        		apellidoPaterno: apellidoPaterno,
				   };

				return JSON.stringify(obj);
			}
			function obtenerDatos(){
				
				var idUsuario = $("#inputFormId").val();
				var codigoUsuario = $("#txtFormCodUsu").val();
				var email = $("#txtFormEmail").val();
				var clave = $("#txtFormClave").val();
				var nombres = $("#txtFormNombres").val();
				var apellidoPaterno = $("#txtFormApePat").val();
				var apellidoMaterno = $("#txtFormApeMat").val();
				var idEstado = $('input:radio[name=radioVigente]:checked').val();
				var roles ="";
				$("input[data-check-group='chkGroupRoles']").each(function(){
					if($(this).is(':checked')){
						roles += $(this).val()+",";
					}
				});
				roles = roles.substring(0, roles.length-1);
				
		        var obj = {
		        		idUsuario: idUsuario,
		        		codigoUsuario: codigoUsuario,
		        		email: email,
		        		clave: clave,
		        		nombres: nombres,
		        		apellidoPaterno: apellidoPaterno,
		        		apellidoMaterno: apellidoMaterno,
		        		idEstado: idEstado,
		        		roles: roles,
				   };

				return JSON.stringify(obj);
			}
			function onClickSaveUsuario(){
				$('#btnSave').click(function(){
					saveUsuario();
				});
			}
			function onClickPaginator(){
				var list = $("a.page-link");
				list.each(function(i,e) {
					var obj = $(this);
					obj.click(function() {
						
						//console.log("[button]["+obj.text()+"]");
						var numPage = parseInt($('#numPagina').val());
						var filasPorPagina = parseInt($('#cantidadFilasPagina').val());
						
						if(obj.text()=="Anterior"){
							numPage--;
						}else if(obj.text()=="Siguiente"){
							numPage++;
						}else{
							numPage = parseInt(obj.text());
						}
						
						getUsuarios(numPage,filasPorPagina);
						$('#numPagina').val(numPage);
						
					});
				});
			}
			function onChangeCantidadFilas(){
				$("#cantidadFilasPagina").change(function() {
					var filasPorPagina = $(this).val();
					getUsuarios(1,filasPorPagina);
					$('#cantidadFilasPagina').val(filasPorPagina);
					$('#numPagina').val("1");
				});
			}
			function onClickBtnSearch(){
				$("#btnSearch").click(function() {
					var filasPorPagina = parseInt($('#cantidadFilasPagina').val());
					getUsuarios(1,filasPorPagina);
					$('#numPagina').val("1");
				});
			}
			function onClickBtnClean(){
				$("#btnClean").click(function() {
					$("#txtCodUsuario").val("");
					$("#txtNombres").val("");
					$("#txtApellidoPaterno").val("");
					$("#inputFormId").val("0");
					
					var numPage = parseInt($('#numPagina').val());
					var filasPorPagina = parseInt($('#cantidadFilasPagina').val());
					
					getUsuarios(1,filasPorPagina);
					$('#numPagina').val("1");
				});
			}
			function onHiddenModal(){
				$('#modalForm').on('hidden.bs.modal', function (e) {
					$("#inputFormId").val("0");
					$("#txtFormCodUsu").val("");
					$("#txtFormEmail").val("");
					$("#txtFormNombres").val("");
					$("#txtFormApePat").val("");
					$("#txtFormApeMat").val("");
					$("#txtFormClave").val("");
					$("#txtFormReClave").val("");
					$("#opcion-vigencia").hide();
					$("input:radio[name=radioVigente][value='1']").prop('checked', true);
					Form.clean();
				})
			}
			function onClikcBtnEditUser(){
				$(".btnEdit").each(function() {
					$(this).click(function(){
						var $id = $(this).attr("id").replace("btnEdit-","");
						getUsuario($id);
					});
					
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
