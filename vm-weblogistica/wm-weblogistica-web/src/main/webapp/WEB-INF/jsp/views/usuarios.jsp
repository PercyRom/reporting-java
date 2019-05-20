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
<li class="breadcrumb-item active">Usuarios</li>
</ol>

<input type="hidden" id="numPagina" value="1"/>

<div class="card-body">

		<div class="form-row">
			<div class="form-group col-md-3">
				<div class="input-group">
					<div class="input-group-prepend" data-toggle="tooltip" title="Codigo Usuario">
						<div class="input-group-text">
							<i class="fas fa-id-card fa-sm"></i>
						</div>
					</div>
					<input type="text" class="form-control"
						id="txtCodUsuario" placeholder="Codigo Usuario">
				</div>
			</div>
			<div class="form-group col-md-3">
				<div class="input-group">
					<div class="input-group-prepend" data-toggle="tooltip" title="Nombres">
						<div class="input-group-text">
							<i class="fas fa-portrait fa-sm"></i>
						</div>
					</div>
					<input type="text" class="form-control"
						id="txtNombres" placeholder="Nombres">
				</div>
			</div>
			<div class="form-group col-md-4">
				<div class="input-group">
					<div class="input-group-prepend" data-toggle="tooltip" title="Apellido Paterno">
						<div class="input-group-text">
							<i class="fas fa-align-justify fa-sm"></i>
						</div>
					</div>
					<input type="text" class="form-control"
						id="txtApellidoPaterno" placeholder="Apellido Paterno">
				</div>
			</div>
			<div class="form-group col-md-2">
				<a href="#" id="btnSearch" class="btn btn-light mb-2" data-toggle="tooltip" title="Buscar">
			    	<span class="vm-lila">
						<i class="fas fa-search fa-sm"></i>
					</span>
			    </a>
			    <a href="#" id="btnClean" class="btn btn-light mb-2" data-toggle="tooltip" title="Limpiar">
			    	<span class="vm-lila">
						<i class="fas fa-broom fa-sm"></i>
					</span>
			    </a>
			</div>
		</div>
	
	<div class="form-row">
			<label class="filas" for="filasPorPagina">Filas</label> 
			<div class="col-sm-2">
		      <select id="cantidadFilasPagina" class="form-control">
		        <option>1</option>
		        <option>3</option>
		        <option>5</option>
		        <option selected>10</option>
		        <option>20</option>
		        <option>30</option>
		        <option>50</option>
		      </select>
		    </div>
	    
	    <label class="total-registros" style="display: none; "></label>
	    
	    <a href="#" class="btn btn-light mb-2" data-toggle="modal" data-target="#modalForm">
	    	<span class="vm-lila" title="Nuevo Usuario">
				<i class="fas fa-plus fa-sm"></i>
			</span>
			<span class="vm-lila">
	    		Nuevo Usuario
	    	</span>
	    </a>
	    
	</div>
	<table
		class="table table-sm table-hover dataTable table-content-scrollable">
		<thead class="thead-dark">
			<tr>
				<th  class="col-auto" align="center">
					<div class="th-inner">ID</div>
					<div class="fht-cell"></div>
				</th>
				<th  class="col-auto">
					<div class="th-inner">Codigo Usuario</div>
					<div class="fht-cell"></div>
				</th>
				<th  class="col-auto">
					<div class="th-inner">Email</div>
					<div class="fht-cell"></div>
				</th>
				<th  class="col-auto">
					<div class="th-inner">Nombres</div>
					<div class="fht-cell"></div>
				</th>
				<th  class="col-auto">
					<div class="th-inner">Apellido Paterno</div>
					<div class="fht-cell"></div>
				</th>
				<th  class="col-auto">
					<div class="th-inner">Apellido Materno</div>
					<div class="fht-cell"></div>
				</th>
				<th  class="col-auto" style="padding: 0; text-align: center; vertical-align: middle;">
					<span class="vm-mostaza" data-toggle="tooltip" title="Editar">
						<i class="fas fa-pen fa-sm"></i>
					</span>
				</th>
			</tr>
		</thead>
		<tbody id="tbody-usuarios">
				
		</tbody>
	</table>
	<div class="alert alert-warning no-records-found" role="alert" style="display: none;">
	  No hay datos para mostrar
	</div>
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
				</span> <span class="modal-title vm-morado" style="font-weight: bold;" id="modalUsuarioLabel">Usuario</span>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span class="vm-lila"><i class="fas fa-times"></i></span>
				</button>
			</div>
			<div class="modal-body">
				<form id="formModalNewUser">
					<input type="hidden" id="inputFormId" value="0">

					<div class="form-row">
						<div class="col-7">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend" data-toggle="tooltip" title="Codigo Usuario">
										<div class="input-group-text">
											<i class="fas fa-id-card fa-sm"></i>
										</div>
									</div>
									<input type="text" class="form-control" id="txtFormCodUsu"
										required data-validation="alphanumeric"
										placeholder="Codigo Usuario" required>
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend" data-toggle="tooltip" title="Email">
										<div class="input-group-text">
											<i class="fas fa-at fa-sm"></i>
										</div>
									</div>
									<input type="text" required data-validation="email"
										data-validation-text="El campo email es obligatorio"
										data-validation-text-format="El formato de email no es correcto"
										class="form-control" id="txtFormEmail" placeholder="Email">

								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend" data-toggle="tooltip" title="Nombres">
										<div class="input-group-text">
											<i class="fas fa-portrait fa-sm"></i>
										</div>
									</div>
									<input type="text" class="form-control" id="txtFormNombres"
										placeholder="Nombres" required data-validation="text-spaces">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend" data-toggle="tooltip" title="Apellido Paterno">
										<div class="input-group-text">
											<i class="fas fa-align-justify fa-sm"></i>
										</div>
									</div>
									<input type="text" class="form-control" id="txtFormApePat"
										placeholder="Apellido Paterno" required
										data-validation="text-spaces">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend" data-toggle="tooltip" title="Apellido Materno">
										<div class="input-group-text">
											<i class="fas fa-align-justify fa-sm"></i>
										</div>
									</div>
									<input type="text" class="form-control" id="txtFormApeMat"
										placeholder="Apellido Materno" required
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
												name="radioVigente" id="radioVigente1" value="1"> <label
												class="form-check-label" for="inlineRadio1">Vigente</label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio"
												name="radioVigente" id="radioVigente2" value="0"> <label
												class="form-check-label" for="inlineRadio2">No
												Vigente</label>
										</div>
									</div>

								</div>
							</div>

							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend" data-toggle="tooltip" title="Clave">
										<div class="input-group-text">
											<i class="fas fa-key fa-sm"></i>
										</div>
									</div>
									<input type="password" class="form-control" id="txtFormClave"
										placeholder="Clave" required
										data-validation-text="Escribe la clave"
										data-validation="password">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend" data-toggle="tooltip" title="Repetir Clave">
										<div class="input-group-text">
											<i class="fas fa-key fa-sm"></i>
										</div>
									</div>
									<input type="password" class="form-control" id="txtFormReClave"
										placeholder="Repetir Clave" required
										data-validation-text="Escribe nuevamente la clave"
										data-validation="repassword">
								</div>
							</div>
						</div>
						<div class="col">
							<table id="table-roles" class="table table-sm table-bordered table-hover">
								<thead class="thead-dark">
									<tr>
										<th style="text-align: center;">Roles</th>
									</tr>
								</thead>
								<tbody id="tbody-roles">
								</tbody>
							</table>
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

<%-- <script src="${prop['aplicacion.rutaRecursos']}/js/sistema/usuarios.js"></script> --%>
<script src="${prop['aplicacion.rutaRecursos']}/js/sistema/usuarios.js?fecha=<c:out value='${fechaHoraActual}' />"></script>
<script type="text/javascript">
	Usuarios.init("${pageContext.request.contextPath}/");
</script>