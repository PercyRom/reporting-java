<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<c:set var="now" value="<%=Calendar.getInstance().getTime()%>" />
<fmt:formatDate value="${now}" pattern="ddMMyyyyHHmmss" var="fechaHoraActual" />

<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item">Almacen</li>
	<li class="breadcrumb-item active">Compras</li>
</ol>

<input type="hidden" id="numPagina" value="1"/>

<div class="card-body">
	
	<div class="form-row">
		<div class="form-group col-md-2">
			<small class="vm-morado">Id Compra</small>
			<input type="text" class="form-control form-control-sm" id="txtCantidad">
		</div>
		<div class="form-group col-md-2">
			<small class="vm-morado">Registrado Desde</small> 
			<input type="text" class="form-control form-control-sm" id="txtNroOrdenCompra">
		</div>
		<div class="form-group col-md-2">
			<small class="vm-morado">Registrado Hasta</small> 
			<input type="text" class="form-control form-control-sm" id="txtNroOrdenCompra">
		</div>
		<div class="form-group col-md-2">
			<small class="vm-morado">Tipo Entidad Origen</small>
			<input type="text" class="form-control form-control-sm" id="txtCantidad">
		</div>
		<div class="form-group col-md-2">
			<small class="vm-morado">RUC</small> 
			<input type="text" class="form-control form-control-sm" id="txtNroOrdenCompra">
		</div>
		<div class="form-group col-md-2">
			<small class="vm-morado">Razon Social</small> 
			<input type="text" class="form-control form-control-sm" id="txtNroOrdenCompra">
		</div>
	</div>
	<div class="form-row">
		<div class="form-group col-md-2">
			<small class="vm-morado">Orden de Compra</small> 
			<input type="text" class="form-control form-control-sm" id="txtNroOrdenCompra">
		</div>
		<div class="form-group col-md-2">
			<small class="vm-morado">Tipo Doc Contable</small> 
			<input type="text" class="form-control form-control-sm" id="txtNroOrdenCompra">
		</div>
		<div class="form-group col-md-2">
			<small class="vm-morado">Num Doc Contable</small> 
			<input type="text" class="form-control form-control-sm" id="txtNroOrdenCompra">
		</div>
		<div class="form-group col-md-2">
			<small class="vm-morado">Producto</small> 
			<input type="text" class="form-control form-control-sm" id="txtNroOrdenCompra">
		</div>
		<div class="form-group col-md-2">
			<small class="vm-morado">Usuario Registro</small> 
			<input type="text" class="form-control form-control-sm" id="txtNroOrdenCompra">
		</div>
		<div class="form-group col-md-2">
			<small class="vm-morado">Estado</small> 
			<input type="text" class="form-control form-control-sm" id="txtNroOrdenCompra">
		</div>
	</div>
	<div class="form-row">
		<p class="col-sm-2 text-left">
			<a href="${pageContext.request.contextPath}/almacen/compra-registro" class="btn btn-light mb-2"> 
				<span class="vm-lila" title="Nuevo Usuario"> 
					<i class="fas fa-plus fa-sm"></i>
				</span> 
				<span class="vm-lila">Nueva Compra </span>
			</a>
		</p>
		<p class="col-sm-8 text-center">
			<label class="total-registros">Mostrando 10 registros</label> 
		</p>
		
		<p class="col-sm-1 text-center">
			<label class="filas" for="filasPorPagina">Filas</label>
			<div>
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
		</p>
	</div>
	<table class="table table-bordered table-sm table-hover table-content-scrollable">
		<thead class="thead-dark">
			<tr>
				<th align="center">
					<div class="th-inner">ID Compra</div>
					<div class="fht-cell"></div>
				</th>
				<th>
					<div class="th-inner">Orden Compra</div>
					<div class="fht-cell"></div>
				</th>
				<th data-toggle="tooltip" title="Cantidad Requerida">
					<div class="th-inner">Cant. Req.</div>
					<div class="fht-cell"></div>
				</th>
				<th data-toggle="tooltip" title="Cantidad Abastecida">
					<div class="th-inner">Cant. Abast.</div>
					<div class="fht-cell"></div>
				</th>
				<th>
					<div class="th-inner">Estado</div>
					<div class="fht-cell"></div>
				</th>
				<th>
					<div class="th-inner">Producto</div>
					<div class="fht-cell"></div>
				</th>
				<th>
					<div class="th-inner">Registrado por</div>
					<div class="fht-cell"></div>
				</th>
				<th data-toggle="tooltip" title="Fecha Registro">
					<div class="th-inner">Fec. Registro</div>
					<div class="fht-cell"></div>
				</th>
				<th data-toggle="tooltip" title="Tipo Entidad Origen">
					<div class="th-inner">Tipo Ent. Orig.</div>
					<div class="fht-cell"></div>
				</th>
				<th>
					<div class="th-inner">Razon Social</div>
					<div class="fht-cell"></div>
				</th>
				<th>
					<div class="th-inner">RUC</div>
					<div class="fht-cell"></div>
				</th>
				<th data-toggle="tooltip" title="Representante Legal">
					<div class="th-inner">Rep. Legal</div>
					<div class="fht-cell"></div>
				</th>
				<th style="padding: 0; text-align: center; vertical-align: middle;">
					<span class="vm-mostaza" data-toggle="tooltip" title="Editar">
						<i class="fas fa-pen fa-sm"></i>
					</span>
				</th>
			</tr>
		</thead>
		<tbody id="tbody-usuarios">
			<tr>
				<td align="center">
					<div class="th-inner">ID Compra</div>
					<div class="fht-cell"></div>
				</td>
				<td>
					<div class="th-inner">Orden Compra</div>
					<div class="fht-cell"></div>
				</td>
				<td>
					<div class="th-inner">Cant. Req.</div>
					<div class="fht-cell"></div>
				</td>
				<td>
					<div class="th-inner">Cant. Abast.</div>
					<div class="fht-cell"></div>
				</td>
				<td>
					<div class="th-inner">Estado</div>
					<div class="fht-cell"></div>
				</td>
				<td>
					<div class="th-inner">Producto</div>
					<div class="fht-cell"></div>
				</td>
				<td>
					<div class="th-inner">Registrado por</div>
					<div class="fht-cell"></div>
				</td>
				<td>
					<div class="th-inner">Fec. Registro</div>
					<div class="fht-cell"></div>
				</td>
				<td>
					<div class="th-inner">Tipo Ent. Orig.</div>
					<div class="fht-cell"></div>
				</td>
				<td>
					<div class="th-inner">Razon Social</div>
					<div class="fht-cell"></div>
				</td>
				<td>
					<div class="th-inner">RUC</div>
					<div class="fht-cell"></div>
				</td>
				<td>
					<div class="th-inner">Rep. Legal</div>
					<div class="fht-cell"></div>
				</td>
				<td style="padding: 0; text-align: center; vertical-align: middle;">
					<span class="vm-mostaza" data-toggle="tooltip" title="Editar">
						<i class="fas fa-pen fa-sm"></i>
					</span>
				</td>
			</tr>
		</tbody>
	</table>

</div>

<section id="section-paginador">
</section>

<%-- <script src="${prop['aplicacion.rutaRecursos']}/js/sistema/compras.js"></script> --%>
<script src="${prop['aplicacion.rutaRecursos']}/js/sistema/compras.js?fecha=<c:out value='${fechaHoraActual}' />"></script>
<script type="text/javascript">
	Compras.init("${pageContext.request.contextPath}/");
</script>