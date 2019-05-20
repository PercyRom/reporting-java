var HTML = "";
var TR_ENTRADA = "<tr><th scope='row'>ID_ENTRADA</th><td>FEC_ENTRADA</td><td>CANT_ENTRADA</td>";
	TR_ENTRADA+="<td><a href='#' class='btn btn-light mb-2 btnEditEntrada' id='btnEditEntrada-ID_ENTRADA' data-toggle='tooltip' title='Editar'><span class='vm-morado'><i class='fas fa-pen fa-sm'></i> </span></a></td>";
	TR_ENTRADA+="<td><a href='#' class='btn btn-light mb-2 btnRemoveEntrada' id='btnRemoveEntrada-ID_ENTRADA' data-toggle='tooltip' title='Eliminar'><span class='vm-morado'><i class='fas fa-times fa-sm'></i> </span></a></td></tr>";
	
var OPTION_SELECCIONE ="<option value='' selected class='vm-lila'>Seleccione</option>";
var OPTION_NINGUNO ="<option value='' selected class='vm-lila'>Ninguno</option>";
var LIST_ENTRADAS = [];
var CompraRegistro = function() {
	return {
		init : function(uri) {
			
			$(window).on("load", function (e) {
				onChangeTipoEntidad();
				onChangeEntidad();
				onHiddenModal();
				initDatePicker();
				loadListEntradas();
				onClickSaveEntrada();
				onClickConfirmRemoveEntrada();
				onClickSaveCompra();
				Form.init();
			})
			function ajaxGetEntidadByTipo(vIdTipoEntidad){
				$.ajax({
					url : uri+'almacen/listarEntidadByTipo',
					type : "POST",
					data : vIdTipoEntidad,
					dataType: "json",
					contentType : "application/json; charset=utf-8",
					success : function(data) {
						if(data.length >0){
							$('#cboEntidadOrigen').append(OPTION_SELECCIONE);
							$.each(data, function(i, e) {
								HTML="<option value='"+e.idEntidad+"'>"+e.nombreEntidad+"</option>";
								$('#cboEntidadOrigen').append(HTML);
								HTML = "";
							});
						}else{
							$('#cboEntidadOrigen').append(OPTION_NINGUNO);
						}
					}
				});
			}
			function ajaxGetDatosEntidad(vIdEntidad){
				$.ajax({
					url : uri+'almacen/obtenerDatosEntidad',
					type : "POST",
					data : vIdEntidad,
					dataType: "json",
					contentType : "application/json; charset=utf-8",
					success : function(data) {
						if(data!=null){
							$(".datos-entidad").show();
							$("#txtRuc").text(data.identificadorFacturacion);
							$("#txtRazonSocial").text(data.nombreEntidad);
							
							$("#content-nbo").hide();
							if(data.codigoNbo != null){
								$("#txtCodigoNBO").text(dat.codigoNbo);
								$("#content-nbo").show();
							}
						}
					}
				});
			}
			function ajaxRedirectCompras(){
				$.ajax({
					url : uri+'almacen/compras',
					type : "PUT",
					contentType : "application/json; charset=utf-8",
				});
			}
			function ajaxSaveCompra(){
				$.ajax({
					url : uri+'almacen/saveCompra',
					data : obtenerDatos(),
					dataType: "text",
					type : "PUT",
					contentType : "application/json; charset=utf-8",
					beforeSend : function() {
						$("#btnSave").attr("disabled","disabled");
					},
					error : function(ts) {
						$("#btnSave").removeAttr("disabled");
					},
					success : function() {
						ajaxRedirectCompras();
					}
				}).done(function() {
					$("#btnSave").removeAttr("disabled");
				});
			}
			function obtenerDatos(){
//				idCompra;
				idTipoEntidadOrigen = $("#cboTipoEntidad").val();
				idEntidadOrigen = $("#cboEntidadOrigen").val();
				idTipoProducto = $("#cboProducto").val();
				nroOrdenCompra = $("#cboProducto").val();
				cantidad = $("#txtCantidad").val();
				
		        var obj = {
		        		idTipoEntidadOrigen: idTipoEntidadOrigen,
		        		idEntidadOrigen: idEntidadOrigen,
		        		idTipoProducto: idTipoProducto,
		        		nroOrdenCompra: nroOrdenCompra,
		        		cantidad: cantidad,
		        		entradas: LIST_ENTRADAS,
				   };
		        var result = JSON.stringify(obj);
		        console.log(result);
				return result;
			}
			function onChangeTipoEntidad(){
				$("#cboTipoEntidad").change(function(){
					$("#cboEntidadOrigen").html("");
					$(".datos-entidad").hide();
					Form.removeClassValidation("cboEntidadOrigen");
					if( $(this).val() != "" ){
						ajaxGetEntidadByTipo( $(this).val() );
					}
				});
			}
			function onChangeEntidad(){
				$("#cboEntidadOrigen").change(function(){
					if( $(this).val() != "" ){
						ajaxGetDatosEntidad( $(this).val() );
					}
				});
			}
			function loadListEntradas(){
				$('#tBodyEntradas').html("");
				if(LIST_ENTRADAS.length>0){
					console.log(LIST_ENTRADAS);
					$.each(LIST_ENTRADAS, function(i, e) {
						$('#tBodyEntradas').append(TR_ENTRADA
								.replace(/ID_ENTRADA/g,e.id+"")
								.replace("FEC_ENTRADA",e.fechaPlanificado)
								.replace("CANT_ENTRADA",e.cantidad)
						);
					});
					onClickRemoveEntrada();
					onClickEditEntrada();
				}
			}
			function onClickSaveEntrada(){
				$("#btnSaveEntrada").click(function(){
					var validate = Form.validate("modalForm");
					if(validate){
						
						var fechaPlanificado = $("#txtFechaPlanificada").val();
						var cantidad = $("#txtCantidadEntrada").val();
						
						LIST_ENTRADAS.push({
							id: LIST_ENTRADAS.length+1,
			        		fechaPlanificado : fechaPlanificado,
			        		cantidad : cantidad,
					    });
						loadListEntradas();
						$('#modalForm').modal('hide');
					}
				});
			}
			function onClickRemoveEntrada(){
				$(".btnRemoveEntrada").each(function(){
					$(this).click(function(){
						$("#hidenEntradaToUtil").val( $(this).attr("id").replace("btnRemoveEntrada-","") );
						$('#modalConfirm').modal('show');
					});
				});
			}
			function onClickEditEntrada(){
				$(".btnEditEntrada").each(function(){
					$(this).click(function(){
						var id = $(this).attr("id").replace("btnEditEntrada-","");
						
						var obj;
						for(var i=0; i < LIST_ENTRADAS.length; i++) {
						   if(LIST_ENTRADAS[i].id == id) {
							   obj = LIST_ENTRADAS[i];
						   }
						}
						$("#inputFormId").val(obj.id);
						$("#txtFechaPlanificada").val(obj.fechaPlanificado);
						$("#txtCantidadEntrada").val(obj.cantidad);
						
						$('#modalForm').modal('show');
					});
				});
			}
			function onClickConfirmRemoveEntrada(){
				$("#btnConfirmRemove").click(function(){
					var id =$("#hidenEntradaToUtil").val();
					
					for(var i=0; i < LIST_ENTRADAS.length; i++) {
					   if(LIST_ENTRADAS[i].id == id) {
						   LIST_ENTRADAS.splice(i,1);
					   }
					}
					
					loadListEntradas();
					$('#modalConfirm').modal('hide');
				});
			}
			function onClickSaveCompra(){
				$("#btnSave").click(function() {
					if(LIST_ENTRADAS.length==0){
						Form.showMsgOwnValidation("#tableEntradas","Debe agregar al menos una entrada");
					}
					var validate = Form.validate("form");
					if(validate && LIST_ENTRADAS.length>0){
						ajaxSaveCompra();
					}
				});
			}
			function onHiddenModal(){
				$('#modalForm').on('hidden.bs.modal', function (e) {
					$("#txtFechaPlanificada").val("");
					$("#txtCantidadEntrada").val("");
					Form.clean();
				})
			}
			function initDatePicker(){
				$('.datetimepicker').datetimepicker({
                	locale: 'es',
                	format: 'DD/MM/YYYY',
                	allowInputToggle: true,
            		minDate: moment(),
            		stepping: 15,
            		ignoreReadonly: true,
            		showClose: true,
            		icons: {
                		time: 'fas fa-clock', 
                        date: 'fa fa-calendar',
                        up: 'fa fa-arrow-up',
                        down: 'fa fa-arrow-down',
                        previous: 'fa fa-chevron-left',
                        next: 'fa fa-chevron-right',
                        today: 'fa fa-calendar-check-o',
                        clear: 'fa fa-delete',
                        close: 'fa fa-times'
                    }
                });
			}
			
			
		}
	};

}();
