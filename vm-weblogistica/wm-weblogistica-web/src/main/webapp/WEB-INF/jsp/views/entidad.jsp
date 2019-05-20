<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<c:set var="now" value="<%=Calendar.getInstance().getTime()%>" />
<fmt:formatDate value="${now}" pattern="ddMMyyyyHHmmss" var="fechaHoraActual" />

<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item">Tablas Maestras</li>
	<li class="breadcrumb-item active">Entidad</li>
</ol>

<input type="hidden" id="numPagina" value="1" />

<div class="card-body">

	<div class="form-row">
		
		
		<div style="width: 50%; text-align:  left; "> 
			<a href="${pageContext.request.contextPath}/maestras/entidad/nuevo" class="btn btn-light mb-2" > 
				<span class="vendemas-lila" title="Nueva Entidad"> 
				<i class="fas fa-plus fa-sm"></i>
			</span> <span class="vendemas-color-1">Nuevo</span>
			</a>
		</div>
		
		<div style="width: 50%; text-align:  right; margin-top: 14px;"> 
			<label for="filasPorPagina" >&nbsp;&nbsp;Filas por P&aacute;gina</label>
			<select id="cantidadFilasPagina">
				<option>1</option>
				<option selected>10</option>
				<option>20</option>
				<option>30</option>
				<option>50</option>
			</select>
			<label class="total-registros"></label>
		</div>
	</div>
	
	<table data-toggle="table" data-click-to-select="true"
		class="table table-sm table-hover dataTable" id="datatable_roles">
		<thead class="thead-dark">
			<tr>
				<th style="" align="center">
					<div class="th-inner">R.U.C.</div>
					<div class="fht-cell"></div>
				</th>
				<th style="">
					<div class="th-inner">RAZ&Oacute;N SOCIAL</div>
					<div class="fht-cell"></div>
				</th>
				<th style="">
					<div class="th-inner">TIPO</div>
					<div class="fht-cell"></div>
				</th>
				<th style="text-align: center;">
					<div class="th-inner">PA&Iacute;S</div>
					<div class="fht-cell"></div>
				</th>
				<th style="text-align: center;">
						<div class="th-inner">VIGENTE</div>
						<div class="fht-cell"></div>
					</th>
				<th style="padding: 0; text-align: center; vertical-align: middle;">
					<span class="vendemas-mostaza" data-toggle="tooltip" title="Editar">
						<i class="fas fa-pen fa-sm"></i>
				</span>
				</th>
			</tr>
		</thead>
		<tbody id="tbody-entidades">
		</tbody>
	</table>
</div>

<div class="alert alert-warning no-records-found" role="alert" style="display: none;">No hay datos para mostrar</div>

<section id="section-paginador"></section>


<div class="modal fade" id="modalForm" tabindex="-1" role="dialog"
	aria-labelledby="modalUsuarioLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<span class="vendemas-morado"> <i
					class="fas fa-user-circle fa-sm"></i>
				</span> <span class="modal-title vendemas-morado">&nbsp;&nbsp;Entidad</span>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span class="vendemas-lila"><i class="fas fa-times"></i></span>
				</button>
			</div>
			<div class="modal-body">
			
<!-- INI -->
<form id="formModal">
	<input type="hidden" id="inputFormId" value="0">
	
<div class="form-row">
    <div class="col-md-4 mb-3">
     <label for="txtFormRUC">R.U.C.</label>
     <div class="input-group">
		<div class="input-group-prepend" data-toggle="tooltip" title="RUC">
			<div class="input-group-text">
				<i class="fas fa-portrait fa-sm"></i>
			</div>
		</div>
		<input type="text" class="form-control" id="txtFormRUC" 
		data-validation="only-numeric" placeholder="R.U.C." maxlength="11">
	</div>
    </div>
    <div class="col-md-4 mb-3">
      	<label for="txtFormNombre">Raz&oacute;n Social</label>
		<div class="input-group">
			<div class="input-group-prepend" data-toggle="tooltip" title="Nombre">
				<div class="input-group-text">
					<i class="fas fa-portrait fa-sm"></i>
				</div>
			</div>
			<input type="text" class="form-control" id="txtFormNombre" 
			data-validation="alphanumeric" placeholder="Raz&oacute;n Social" >
		</div>
    </div>
    <div class="col-md-4 mb-3">
		<label for="txtFormNombreComercial">Nombre Comercial</label>
		<div class="input-group">
			<div class="input-group-prepend" data-toggle="tooltip" title="Nombre Comercial">
				<div class="input-group-text">
					<i class="fas fa-portrait fa-sm"></i>
				</div>
			</div>
			<input type="text" class="form-control" id="txtFormNombreComercial" 
			data-validation="alphanumeric" placeholder="Nombre Comercial">
		</div>
    </div>
  </div>
  <div class="form-row">
    <div class="col-md-4 mb-3">
	    <label for="cboFormTipoEntidad">Tipo Entidad</label>
		<div class="input-group">
			<div class="input-group-prepend" data-toggle="tooltip" title="Tipo de Identidad">
				<div class="input-group-text">
					<i class="fas fa-portrait fa-sm"></i>
				</div>
			</div>
			
			<select class="custom-select" id="cboFormTipoEntidad" required>
				<option selected >SELECCIONE</option>
			</select>
		</div>
    </div>
    <div class="col-md-4 mb-3">
      	<label for="txtFormPais">PA&Iacute;S</label>
		<div class="input-group">
			<div class="input-group-prepend" data-toggle="tooltip" title="Pais">
				<div class="input-group-text">
					<i class="fas fa-portrait fa-sm"></i>
				</div>
			</div>
			<input type="text" class="form-control" id="txtFormPais" 
			placeholder="Pais" required data-validation="alphanumeric" >
		</div>
    </div>
    <div class="col-md-4 mb-3">
      	<label for="txtFormPais">VIGENTE</label>
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
						name="radioVigente" id="radioVigente_S" value="S"> <label
						class="form-check-label" for="radioVigente_S">Vigente</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio"
						name="radioVigente" id="radioVigente_N" value="N"> <label
						class="form-check-label" for="radioVigente_N">No Vigente</label>
				</div>
			</div>
	
		</div>
      
      
    </div>
  </div>

</form>
<!-- END -->
				
				
			</div>
			<div class="modal-footer">
				<a href="#" class="btn btn-light mb-2" id="btnSave"> <span
					class="vendemas-morado"><i class="fas fa-save fa-sm"></i> </span> <span
					class="vendemas-morado">Guardar</span>
				</a> <a href="#" class="btn btn-light mb-2" data-dismiss="modal"> <span
					class="vendemas-lila"> <i class="fas fa-times fa-sm"></i>
				</span> <span class="vendemas-lila">Cerrar</span>
				</a>
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="modalFormContacto" tabindex="-1" role="dialog" aria-labelledby="modalUsuarioLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<span class="vendemas-morado"> <i
					class="fas fa-user-circle fa-sm"></i>
				</span> <span class="modal-title vendemas-morado">&nbsp;&nbsp;Contacto</span>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span class="vendemas-lila"><i class="fas fa-times"></i></span>
				</button>
			</div>
			<div class="modal-body">
				<form id="formModal">
					<input type="hidden" id="inputFormId" value="0">

  <div class="form-row">
    <div class="col-md-4 mb-3">
      <label for="validationCustom01">First name</label>
      <input type="text" class="form-control" id="validationCustom01" placeholder="First name" value="Mark" required>
      <div class="valid-feedback">
        Looks good!
      </div>
    </div>
    <div class="col-md-4 mb-3">
      <label for="validationCustom02">Last name</label>
      <input type="text" class="form-control" id="validationCustom02" placeholder="Last name" value="Otto" required>
      <div class="valid-feedback">
        Looks good!
      </div>
    </div>
    <div class="col-md-4 mb-3">
      <label for="validationCustomUsername">Username</label>
      <div class="input-group">
        <div class="input-group-prepend">
          <span class="input-group-text" id="inputGroupPrepend">@</span>
        </div>
        <input type="text" class="form-control" id="validationCustomUsername" placeholder="Username" aria-describedby="inputGroupPrepend" required>
        <div class="invalid-feedback">
          Please choose a username.
        </div>
      </div>
    </div>
  </div>
  <div class="form-row">
    <div class="col-md-6 mb-3">
      <label for="validationCustom03">City</label>
      <input type="text" class="form-control" id="validationCustom03" placeholder="City" required>
      <div class="invalid-feedback">
        Please provide a valid city.
      </div>
    </div>
    <div class="col-md-3 mb-3">
      <label for="validationCustom04">State</label>
      <input type="text" class="form-control" id="validationCustom04" placeholder="State" required>
      <div class="invalid-feedback">
        Please provide a valid state.
      </div>
    </div>
    <div class="col-md-3 mb-3">
      <label for="validationCustom05">Zip</label>
      <input type="text" class="form-control" id="validationCustom05" placeholder="Zip" required>
      <div class="invalid-feedback">
        Please provide a valid zip.
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="form-check">
      <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
      <label class="form-check-label" for="invalidCheck">
        Agree to terms and conditions
      </label>
      <div class="invalid-feedback">
        You must agree before submitting.
      </div>
    </div>
  </div>
  
  <table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">First</th>
      <th scope="col">Last</th>
      <th scope="col">Handle</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>Larry</td>
      <td>the Bird</td>
      <td>@twitter</td>
    </tr>
  </tbody>
</table>

</form>

					
			</div>
			<div class="modal-footer">
				<a href="#" class="btn btn-light mb-2" id="btnSave"> <span
					class="vendemas-morado"><i class="fas fa-save fa-sm"></i> </span> <span
					class="vendemas-morado">Guardar</span>
				</a> <a href="#" class="btn btn-light mb-2" data-dismiss="modal"> <span
					class="vendemas-lila"> <i class="fas fa-times fa-sm"></i>
				</span> <span class="vendemas-lila">Cerrar</span>
				</a>
			</div>
		</div>
	</div>
</div>


<script src="${prop['aplicacion.rutaRecursos']}/js/sistema/entidad.js?fecha=<c:out value='${fechaHoraActual}' />"></script>
<script type="text/javascript"> Entidad.init("${pageContext.request.contextPath}/"); </script>
