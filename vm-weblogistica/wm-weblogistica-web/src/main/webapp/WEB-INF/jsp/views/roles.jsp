<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<c:set var="now" value="<%=Calendar.getInstance().getTime()%>" />
<fmt:formatDate value="${now}" pattern="ddMMyyyyHHmmss" var="fechaHoraActual" />

<!-- Breadcrumbs-->
<ol class="breadcrumb">
<li class="breadcrumb-item">
	Administracion
</li>
<li class="breadcrumb-item active">Roles</li>
</ol>

<input type="hidden" id="numPagina" value="1"/>

<div class="card-body">
	
	
	<div class="form-row">
		<label class="filas" for="filasPorPagina">Filas</label> 
		<div class="col-sm-2">
	      <select id="cantidadFilasPagina" class="form-control">
	        <option>1</option>
	        <option selected>10</option>
	        <option>20</option>
	        <option>30</option>
	        <option>50</option>
	      </select>
	    </div>
	    
	    <label class="total-registros" style="display: none;"></label>
	    
	    <a href="#" class="btn btn-light mb-2" data-toggle="modal" data-target="#modalForm">
	    	<span class="vm-lila" title="Nuevo Rol">
				<i class="fas fa-plus fa-sm"></i>
			</span>
			<span class="vm-lila">
	    		Nuevo Rol
	    	</span>
	    </a>
	    
	</div>
	
		<table class="table table-sm table-hover dataTable table-content-scrollable" id="datatable_roles">
			<thead class="thead-dark">
				<tr>
					<th style="" align="center">
						<div class="th-inner">ID</div>
						<div class="fht-cell"></div>
					</th>
					<th style="">
						<div class="th-inner">Nombre</div>
						<div class="fht-cell"></div>
					</th>
					<th style="">
						<div class="th-inner">Descripcion</div>
						<div class="fht-cell"></div>
					</th>
					<th style="text-align: center;">
						<div class="th-inner">Vigente</div>
						<div class="fht-cell"></div>
					</th>
					<th style="padding: 0; text-align: center; vertical-align: middle;">
					<span class="vm-mostaza" data-toggle="tooltip" title="Editar">
						<i class="fas fa-pen fa-sm"></i>
					</span>
				</th>
				</tr>
			</thead>
			<tbody id="tbody-roles">
			</tbody>
		</table>
	</div>
	<div class="alert alert-warning no-records-found" role="alert" style="display: none;">
	  No hay datos para mostrar
	</div>
	
	<section id="section-paginador">
	</section>

<div class="modal fade" id="modalForm" tabindex="-1" role="dialog"
	aria-labelledby="modalUsuarioLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<span class="vm-morado"> <i
					class="fas fa-user-circle fa-lg" style="margin-right: 10px;"></i>
				</span> <span class="modal-title vm-morado">Rol</span>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span class="vm-lila"><i class="fas fa-times"></i></span>
				</button>
			</div>
			<div class="modal-body">
				<form id="formModal">
					<input type="hidden" id="inputFormId" value="0">

					<div class="form-row">
						<div class="col-7">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend" data-toggle="tooltip" title="Nombre">
										<div class="input-group-text">
											<i class="fas fa-portrait fa-sm"></i>
										</div>
									</div>
									<input type="text" class="form-control" id="txtFormNombre"
										placeholder="Nombre" required data-validation="text-spaces">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend" data-toggle="tooltip" title="Descripcion">
										<div class="input-group-text">
											<i class="fas fa-align-justify fa-sm"></i>
										</div>
									</div>
									<input type="text" class="form-control" id="txtFormDescripcion"
										placeholder="Descripcion" required
										data-validation="text-spaces">
								</div>
							</div>

							<div id="opcion-vigencia" class="form-group"
								style="display: none;">
								<div class="input-group">
									<div class="input-group-prepend" data-toggle="tooltip"
										title="Vigente">
										<div class="input-group-text">
											<i id="icon-vigencia" class="fas fa-check-square fa-sm"></i>
										</div>
									</div>
									<div class="form-control">
										<div class="form-check form-check-inline ">
											<input class="form-check-input" type="radio"
												name="radioVigente" id="radioVigente1" value="S"> <label
												class="form-check-label" for="inlineRadio1">Vigente</label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio"
												name="radioVigente" id="radioVigente2" value="N"> <label
												class="form-check-label" for="inlineRadio2">No
												Vigente</label>
										</div>
									</div>

								</div>
							</div>
						</div>
						<div class="col">
							<table class="table" style="margin-bottom: 0px;">
								<thead class="thead-dark">
									<tr>
										<th style="text-align: center;">Opciones</th>
									</tr>
								</thead>
							</table>
							<div id="table-opciones" style="position: relative; overflow: auto; max-height: 200px; width: 100%;">
							<table class="table table-bordered table-sm table-hover">
								<tbody id="tbody-opciones">
								</tbody>
							</table>
							</div>
						</div>
					</div>
					
				</form>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn btn-light mb-2" id="btnSave"> <span
					class="vm-morado"><i class="fas fa-save fa-sm"></i> </span> <span
					class="vm-morado">Guardar</span>
				</a> <a href="#" class="btn btn-light mb-2" data-dismiss="modal"> <span
					class="vm-lila"> <i class="fas fa-times fa-sm"></i>
				</span> <span class="vm-lila">Cerrar</span>
				</a>
			</div>
		</div>
	</div>
</div>

<script src="${prop['aplicacion.rutaRecursos']}/js/sistema/roles.js?fecha=<c:out value='${fechaHoraActual}' />"></script>
<%-- <script src="${prop['aplicacion.rutaRecursos']}/js/sistema/roles.js"></script> --%>
<script type="text/javascript">
	Roles.init("${pageContext.request.contextPath}/");
</script>