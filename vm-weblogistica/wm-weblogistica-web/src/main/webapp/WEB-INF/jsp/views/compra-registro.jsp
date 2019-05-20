<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<c:set var="now" value="<%=Calendar.getInstance().getTime()%>" />
<fmt:formatDate value="${now}" pattern="ddMMyyyyHHmmss"
	var="fechaHoraActual" />

<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item">Almacen</li>
	<li class="breadcrumb-item">Compras</li>
	<li class="breadcrumb-item active">Registro de Compras</li>
</ol>

<div class="card-body">
	<h5>Datos de la Entidad</h5>

	<form id="form">
		<div class="form-row">

			<div class="form-group col-md-3">
				<small class="vm-morado">Tipo Entidad Origen</small> 
				<select class="custom-select mr-sm-2 custom-select-sm" id="cboTipoEntidad" required data-show-alert-myself>
					<option value='' selected class='vm-lila'>Seleccione</option>
					<c:forEach items="${listTiposEntidad}" var="e">
						<option value="${e.idTipoEntidad}">${e.nombreTipoEntidad}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group col-md-3">
				<small class="vm-morado">Entidad Origen</small> 
				<select class="custom-select mr-sm-2 custom-select-sm" id="cboEntidadOrigen" required data-show-alert-myself>
				</select>
			</div>
			<div class="form-group col-md-auto col-sm-4 datos-entidad" style="display: none;">
				<small class="vm-morado">RUC</small><br/>
				<label id="txtRuc" class="vm-lila"></label>
			</div>
			<div class="form-group col-md-auto col-sm-4 datos-entidad" style="display: none;">
				<small class="vm-morado">Razon Social</small><br/>
				<label id="txtRazonSocial" class="vm-lila"></label>
			</div>
			<div id="content-nbo" class="form-group col-md-auto col-sm-4 datos-entidad" style="display: none;">
				<small class="vm-morado">Codigo NBO</small><br/>
				<label id="txtCodigoNBO" class="vm-lila"></label>
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-3">
				<small class="vm-morado">Producto</small>
				<select class="custom-select mr-sm-2 custom-select-sm" id="cboProducto" required data-show-alert-myself>
					<option value='' selected class='vm-lila'>Seleccione</option>
					<c:forEach items="${listTipoProducto}" var="e">
						<option value="${e.idTipoProducto}">${e.nombreproducto}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group col-md-2">
				<small class="vm-morado">Cantidad</small>
				<input type="text" class="form-control form-control-sm" id="txtCantidad" 
					required data-show-alert-myself data-validation="only-numeric">
			</div>
			<div class="form-group col-md-3">
				<small class="vm-morado">Nro. Orden de Compra</small>
				<input type="text" class="form-control form-control-sm" id="txtOrdenCompra" 
					required data-show-alert-myself data-validation="alphanumeric">
			</div>
		</div>
		<div class="form-row">
			<h5 style="margin-right: 10px;">Entradas</h5>
			
			<a href="#" class="btn btn-light mb-2" data-toggle="modal" data-target="#modalForm">
		    	<span class="vm-lila" title="Agregar Entrada">
					<i class="fas fa-plus fa-sm"></i>
				</span>
				<span class="vm-lila">
		    		Agregar Entrada
		    	</span>
		    </a>
		    
			<div class="clear-both-5"></div>
			<table id="tableEntradas" class="table table-sm table-hover col-md-6">
				<thead class="thead-dark">
					<tr>
						<th scope="col">#</th>
						<th scope="col">Fecha Planificada</th>
						<th scope="col">Cantidad</th>
						<th scope="col">
							<span class="vm-mostaza" data-toggle="tooltip" title="Editar">
								<i class="fas fa-pencil-alt fa-sm"></i>
							</span>
						</th>
						<th scope="col">
							<span class="vm-mostaza" data-toggle="tooltip" title="Eliminar">
								<i class="fas fa-times fa-sm"></i>
							</span>
						</th>
					</tr>
				</thead>
				<tbody id="tBodyEntradas">
					
				</tbody>
			</table>
		</div>
		<a href="#" class="btn btn-light mb-2" id="btnSave"> <span
			class="vm-morado"><i class="fas fa-save fa-sm"></i> </span> <span
			class="vm-morado">Guardar</span>
		</a>
		<a href="${pageContext.request.contextPath}/almacen/compras" class="btn btn-light mb-2"> 
			<span class="vm-lila" title="Cancelar"> 
				<i class="fas fa-times fa-sm"></i>
			</span> 
			<span class="vm-lila">Cancelar</span>
		</a>
	</form>

</div>
<input id="hidenEntradaToUtil" type="hidden">

<div class="modal fade" id="modalForm" tabindex="-1" role="dialog"
	aria-labelledby="modalFormLabel" aria-hidden="true">
	<div class="modal-dialog modal-md" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<span class="vm-morado"> <i
					class="fas fa-sign-in-alt fa-lg" style="margin-right: 10px;"></i>
				</span> <span class="modal-title vm-morado">Entrada</span>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span class="vm-lila"><i class="fas fa-times"></i></span>
				</button>
			</div>
			<div class="modal-body">
				<form id="formModal">
					<input type="hidden" id="inputFormId" value="0">
					
					<div class="form-group">
						<div class="input-group date datetimepicker" id="dateFechaPlanificada" data-target-input="nearest">
							<div class="input-group-append" data-target="#dateFechaPlanificada" data-toggle="datetimepicker">
								<div class="input-group-text">
									<i class="fas fa-calendar-alt fa-sm"></i>
								</div>
							</div>
							<input type="text" class="form-control datetimepicker-input"  id="txtFechaPlanificada"
								data-target="#dateFechaPlanificada" placeholder="Fecha Planificada" readonly required data-show-alert-myself>
						</div>
					</div>
					<div class="form-group" style="display: none;">
						<div class="input-group">
							<div class="input-group-prepend" data-toggle="tooltip" title="Fecha Real">
								<div class="input-group-text">
									<i class="fas fa-calendar-check fa-sm"></i>
								</div>
							</div>
							<input type="text" class="form-control" id="txtFechaReal"
								placeholder="Fecha Real">
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-prepend" data-toggle="tooltip" title="Cantidad">
								<div class="input-group-text">
									<i class="fas fa-sort-numeric-up fa-sm"></i>
								</div>
							</div>
							<input type="text" class="form-control" id="txtCantidadEntrada"
								placeholder="Cantidad" required
								data-validation="only-numeric">
						</div>
					</div>
					<div class="form-group">
						
						<div class="custom-control custom-switch">
						  <input type="checkbox" class="custom-control-input" id="customSwitch1" checked disabled >
						  <label class="custom-control-label" for="customSwitch1">Vigente</label>
						</div>
					</div>
					
				</form>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn btn-light mb-2" id="btnSaveEntrada"> <span
					class="vm-morado"><i class="fas fa-save fa-sm"></i> </span> <span
					class="vm-morado">Agregar</span>
				</a> <a href="#" class="btn btn-light mb-2" data-dismiss="modal"> <span
					class="vm-lila"> <i class="fas fa-times fa-sm"></i>
				</span> <span class="vm-lila">Cerrar</span>
				</a>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="modalConfirm" tabindex="-1" role="dialog"
	aria-labelledby="modalConfirmLabel" aria-hidden="true">
	<div class="modal-dialog modal-md" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<span class="vm-morado"> <i
					class="fas fa-sign-in-alt fa-lg" style="margin-right: 10px;"></i>
				</span> <span class="modal-title vm-morado">Confirmacion</span>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span class="vm-lila"><i class="fas fa-times"></i></span>
				</button>
			</div>
			<div class="modal-body">
					¿Esta seguro que desea eliminar esta entrada?
			</div>
			<div class="modal-footer">
				<a href="#" class="btn btn-light mb-2" id="btnConfirmRemove"> <span
					class="vm-morado"><i class="fas fa-save fa-sm"></i> </span> <span
					class="vm-morado">Aceptar</span>
				</a> <a href="#" class="btn btn-light mb-2" data-dismiss="modal"> <span
					class="vm-lila"> <i class="fas fa-times fa-sm"></i>
				</span> <span class="vm-lila">Cancelar</span>
				</a>
			</div>
		</div>
	</div>
</div>

<section id="section-paginador"></section>

<%-- <script src="${prop['aplicacion.rutaRecursos']}/js/sistema/compra-registro.js"></script> --%>
<script src="${prop['aplicacion.rutaRecursos']}/js/sistema/compra-registro.js?fecha=<c:out value='${fechaHoraActual}' />"></script>
<script type="text/javascript">
 	CompraRegistro.init("${pageContext.request.contextPath}/");
</script>