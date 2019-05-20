<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<c:set var="now" value="<%=Calendar.getInstance().getTime()%>" />
<fmt:formatDate value="${now}" pattern="ddMMyyyyHHmmss" var="fechaHoraActual" />

<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item">Tablas Maestras</li>
	<li class="breadcrumb-item">Entidad</li>
	<li class="breadcrumb-item active">Nuevo</li>
</ol>

<div style="width: 50%; text-align: left;">
	<a href="#" class="btn btn-light mb-2" id="btnSave"> <span
		class="vendemas-morado"><i class="fas fa-save fa-sm"></i> </span> <span
		class="vendemas-morado">Guardar</span>
	</a> <a href="#" class="btn btn-light mb-2" data-toggle='modal' id="btnContacto"> <span
		class="vendemas-lila"> <i class="fas fa-plus fa-sm"></i>
	</span> <span class="vendemas-lila">Contacto</span>
	</a>
</div>

<div class="card-body">
	<form id="formEntidad">
	<input type="hidden" id="txhIdEntidad" value="${entidad.idEntidad}">
		
	<div class="form-row">
	    <div class="col-md-4 mb-3">
	     <div class="input-group">
			<div class="input-group-prepend" data-toggle="tooltip" title="RUC">
				<div class="input-group-text">
					<i class="fas fa-portrait fa-sm"></i>
				</div>
			</div>
			<input type="text" class="form-control" id="txtFormRUC" 
			data-validation="only-numeric" placeholder="R.U.C." maxlength="11" required value="${entidad.identificadorFacturacion}">
		</div>
	    </div>
	    <div class="col-md-4 mb-3">
			<div class="input-group">
				<div class="input-group-prepend" data-toggle="tooltip" title="Nombre">
					<div class="input-group-text">
						<i class="fas fa-portrait fa-sm"></i>
					</div>
				</div>
				<input type="text" class="form-control" id="txtFormNombre" 
				data-validation="alphanumeric" placeholder="Raz&oacute;n Social" required value="${entidad.nombreEntidad}">
			</div>
	    </div>
	    <div class="col-md-4 mb-3">
			<div class="input-group">
				<div class="input-group-prepend" data-toggle="tooltip" title="Nombre Comercial" >
					<div class="input-group-text">
						<i class="fas fa-portrait fa-sm"></i>
					</div>
				</div>
				<input type="text" class="form-control" id="txtFormNombreComercial" 
				data-validation="alphanumeric" placeholder="Nombre Comercial" required value="${entidad.nombreComercial}">
			</div>
	    </div>
	  </div>
	  <div class="form-row">
	    <div class="col-md-4 mb-3">
			<div class="input-group">
				<div class="input-group-prepend" data-toggle="tooltip" title="Tipo de Identidad">
					<div class="input-group-text">
						<i class="fas fa-portrait fa-sm"></i>
					</div>
				</div>
				
				<select class="custom-select" id="cboFormTipoEntidad" required>
					
					<c:if test="${!empty lstTipoEntidad}">
					<option selected value="0" >SELECCIONE</option>
					<c:forEach var="tipoEntidad" items="${lstTipoEntidad}">
						<option value="${tipoEntidad.idTipoEntidad}" ${tipoEntidad.idTipoEntidad == entidad.idTipoEntidad ? 'selected' : ''} >${tipoEntidad.nombreTipoEntidad}</option>
					</c:forEach>
					</c:if>
				</select>
			</div>
	    </div>
	    <div class="col-md-4 mb-3">
			<div class="input-group">
				<div class="input-group-prepend" data-toggle="tooltip" title="Pais">
					<div class="input-group-text">
						<i class="fas fa-portrait fa-sm"></i>
					</div>
				</div>
				<input type="text" class="form-control" id="txtFormPais" 
				placeholder="Pais" data-validation="alphanumeric" required value="${entidad.codigoPais}">
			</div>
	    </div>
	    <div class="col-md-4 mb-3">
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
							name="radioVigente" id="radioVigente_S" value="S" ${entidad.vigente=='S'?'checked':''}> 
							<label class="form-check-label" for="radioVigente_S">Vigente</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio"
							name="radioVigente" id="radioVigente_N" value="N" ${entidad.vigente=='N'?'checked':''} > 
							<label class="form-check-label" for="radioVigente_N">No Vigente</label>
					</div>
				</div>
		
			</div>
	      
	      
	    </div>
	  </div>
	
	</form>
	
	<table data-toggle="table" data-click-to-select="true"
		class="table table-sm table-hover dataTable" id="tbContactos">
		<thead class="thead-dark">
			<tr>
				<th style="" align="center">
					<div class="th-inner">#</div>
					<div class="fht-cell"></div>
				</th>
				<th style="display: none;" align="center" data-override="editado">
					<div class="th-inner">editado</div>
					<div class="fht-cell"></div>
				</th>
				<th style="display: none;" align="center" data-override="idEntidadContacto">
					<div class="th-inner">idEntidadContacto</div>
					<div class="fht-cell"></div>
				</th>
				<th style="display: none;" align="center" data-override="idTipoDocumentoIdentidad">
					<div class="th-inner">idTipoDocumentoIdentidad</div>
					<div class="fht-cell"></div>
				</th>
				<th style="" align="center" data-override="documentoIdentidad">
					<div class="th-inner">DOCUMENTO</div>
					<div class="fht-cell"></div>
				</th>
				<th style="" data-override=apellidoPaterno>
					<div class="th-inner">A. PATERNO</div>
					<div class="fht-cell"></div>
				</th>
				<th style="" data-override="apellidoMaterno">
					<div class="th-inner">A. MATERNO</div>
					<div class="fht-cell"></div>
				</th>
				<th style="" data-override="nombres">
					<div class="th-inner">NOMBRES</div>
					<div class="fht-cell"></div>
				</th>
				<th style="" data-override="telefonoContacto">
					<div class="th-inner">TEL&Eacute;FONO</div>
					<div class="fht-cell"></div>
				</th>
				<th style="text-align: center;" data-override="vigente">
						<div class="th-inner">VIGENTE</div>
						<div class="fht-cell"></div>
				</th>
				<th style="padding: 0 ; text-align: center; vertical-align: middle;">
						<i class="fas fa-pen fa-sm th-inner"></i>
				</th>
				
			</tr>
		</thead>
		<tbody id="tbody-contactos">
			<c:forEach  var="contacto"  items="${entidad.contactos}" varStatus="row"> 
				<tr>
			    	<td>${row.index +1}</td>
			    	<td style='display: none; '>false</td>
			    	<td style='display: none; '><c:out value="${contacto.idEntidadContacto}"></c:out></td>
			    	<td style='display: none; '><c:out value="${contacto.idTipoDocumentoIdentidad}"></c:out></td>
			    	<td><c:out value="${contacto.documentoIdentidad}"></c:out></td>
			    	<td><c:out value="${contacto.apellidoPaterno}"></c:out></td>
			    	<td><c:out value="${contacto.apellidoMaterno}"></c:out></td>
			    	<td><c:out value="${contacto.nombres}"></c:out></td>
			    	<td><c:out value="${contacto.telefonoContacto}"></c:out></td>
			    	<td style='padding: 0px !important; margin: 0px; text-align: center; vertical-align: middle;'><c:out value="${contacto.vigente}"></c:out></td>
			    	<td style='padding: 0px !important; margin: 0px; text-align: center; vertical-align: middle;'>
			    		<a href='#' class='btnEdit btn btn-light mb-2' style='margin: 0px !important; padding: 0px 10px 0px 10px;'>
			    			<i class="fas fa-pen fa-sm th-inner"></i>
			    		</a>
			    	</td>
			    </tr>
			</c:forEach>
		</tbody>
	</table>
	
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
			  <input type="text" name="idEntidadContacto" id="idEntidadContacto" value="0"/>
			  <div class="form-row">
			    <div class="col-md-4 mb-3">
					<div class="input-group">
						<div class="input-group-prepend" data-toggle="tooltip" title="Tipo de Documento">
							<div class="input-group-text">
								<i class="fas fa-portrait fa-sm"></i>
							</div>
						</div>
						<select class="custom-select" id="cboFormTipoDocumento" required>
							<c:if test="${!empty lstTipoDocumentoIdentidad}">
							<option >SELECCIONE</option>
							<c:forEach var="tipoDocumento" items="${lstTipoDocumentoIdentidad}" varStatus="status">
								<option value="${tipoDocumento.idtipodocident}">${tipoDocumento.nombretipodocident}</option>
							</c:forEach>
							</c:if>
						</select>
					</div>
			    </div>
			    
			    <div class="col-md-8 mb-3">
					<div class="input-group">
						<div class="input-group-prepend" data-toggle="tooltip" title="Tipo de Documento">
							<div class="input-group-text">
								<i class="fas fa-portrait fa-sm"></i>
							</div>
						</div>
						<input type="text" class="form-control" id="txtFormNroDocumento" placeholder="Nro Documento" required>
					</div>
			    </div>
			    </div>
			    
			    <div class="form-row">
			    
			    <div class="col-md-6 mb-3">
			      <div class="input-group">
			        <div class="input-group-prepend">
			          <span class="input-group-text" id="inputGroupPrepend"><i class="fas fa-portrait fa-sm"></i></span>
			        </div>
			        <input type="text" class="form-control" id="txtFormApePaterno" placeholder="Apellido Paterno" required>
			      </div>
			    </div>
			    <div class="col-md-6 mb-3">
			      <div class="input-group">
			        <div class="input-group-prepend">
			          <span class="input-group-text" id="inputGroupPrepend"><i class="fas fa-portrait fa-sm"></i></span>
			        </div>
			        <input type="text" class="form-control" id="txtFormApeMaterno" placeholder="Apellido Materno" required>
			      </div>
			    </div>
			  </div>
			  
			  <div class="form-row">
			    <div class="col-md-12 mb-3">
			      <div class="input-group">
			        <div class="input-group-prepend">
			          <span class="input-group-text" id="inputGroupPrepend"><i class="fas fa-portrait fa-sm"></i></span>
			        </div>
			        <input type="text" class="form-control" id="txtFormNombres" placeholder="Nombres" required>
			      </div>
			    </div>
			  </div>
			  
			  
			  <div class="form-row">
			    <div class="col-md-6 mb-3">
			      <div class="input-group">
			        <div class="input-group-prepend">
			          <span class="input-group-text" id="inputGroupPrepend"><i class="fas fa-portrait fa-sm"></i></span>
			        </div>
			        <input type="text" class="form-control" id="txtFormTelefono" placeholder="Telefono" required>
			      </div>
			    </div>
			    
			    <div class="col-md-6 mb-3">
			    <div class="input-group">
					<div class="input-group-prepend" data-toggle="tooltip" title="Vigente">
						<div class="input-group-text">
							<i id="icon-vigencia" class="fas fa-check-square fa-sm"></i>
						</div>
					</div>
					<div class="form-control">
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="radioVigente" id="radioContactoVigente_S" value="S"> 
							<label class="form-check-label" for="radioContactoVigente_S">Vigente</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="radioVigente" id="radioContactoVigente_N" value="N"> 
							<label class="form-check-label" for="radioContactoVigente_N">No Vigente</label>
						</div>
					</div>
				</div>
			    </div>
	  		</div>
		</form>
			
		<div class="modal-footer">
			<a href="#" class="btn btn-light mb-2" id="btnAddContacto"> <span
				class="vendemas-morado"><i class="fas fa-plus fa-sm"></i> </span> <span
				class="vendemas-morado">Agregar</span>
			</a> <a href="#" class="btn btn-light mb-2" data-dismiss="modal"> <span
				class="vendemas-lila"> <i class="fas fa-times fa-sm"></i>
			</span> <span class="vendemas-lila">Cerrar</span>
			</a>
		</div>
			
		</div>
	</div>
	</div>
</div>

<script src="${prop['aplicacion.rutaRecursos']}/js/sistema/mntoEntidad.js?fecha=<c:out value='${fechaHoraActual}' />"></script>
<script src="${prop['aplicacion.rutaRecursos']}/js/librerias/jquery-form-dls/jquery-table-json.js?fecha=<c:out value='${fechaHoraActual}' />"></script>
<script type="text/javascript"> MntoEntidad.init("${pageContext.request.contextPath}/"); </script>
