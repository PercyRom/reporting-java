<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>

<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<c:set var="now" value="<%=Calendar.getInstance().getTime()%>" />
<fmt:formatDate value="${now}" pattern="ddMMyyyyHHmmss" var="fechaHoraActual" />
	
<!DOCTYPE html>
<html lang="en">
	<head>

	  <meta charset="utf-8" />
	  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
	  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	  <meta name="description" content="" />
	  <meta name="author" content="" />
	  <link rel="icon" href="${prop['aplicacion.rutaRecursos']}/img/favicon.ico?fecha=<c:out value='${fechaHoraActual}' />" type="image/x-icon" />

	  <title>Web Log&iacute;stica Vendem&aacute;s</title>
	
	  <!-- Custom fonts for this template-->
	  <link href="${prop['aplicacion.rutaRecursos']}/js/librerias/fontawesome/5.7.2/css/all.min.css" rel="stylesheet" type="text/css" />
	
	  <!-- Page level plugin CSS-->
	  <link href="${prop['aplicacion.rutaRecursos']}/css/librerias/datatables/1.10.18/css/dataTables.bootstrap4.css" rel="stylesheet" />
	  <link href="${prop['aplicacion.rutaRecursos']}/css/librerias/tempusdominus/5.1.3/tempusdominus-bootstrap-4.css" rel="stylesheet" />
	
	  <!-- Custom styles for this template-->
	  <link href="${prop['aplicacion.rutaRecursos']}/css/plantillas/sb-admin/sb-admin.css" rel="stylesheet" />
	  <link href="${prop['aplicacion.rutaRecursos']}/css/plantillas/sb-admin/reset.css" rel="stylesheet" />

	  <!-- Bootstrap core JavaScript-->
	  <script src="${prop['aplicacion.rutaRecursos']}/js/librerias/jquery/3.3.1/jquery.min.js"></script>
	  <script src="${prop['aplicacion.rutaRecursos']}/js/librerias/bootstrap/4.3.1/bootstrap.bundle.min.js"></script>
	  <script src="${prop['aplicacion.rutaRecursos']}/js/librerias/handlebars/1.0.rc.2/handlebars.js"></script>
	
	  <script src="${prop['aplicacion.rutaRecursos']}/js/librerias/moment.js/2.24.0/moment-with-locales.js"></script>
	  <script src="${prop['aplicacion.rutaRecursos']}/js/librerias/tempusdominus/5.1.3/tempusdominus-bootstrap-4.js"></script>
<%-- 	  <script src="${prop['aplicacion.rutaRecursos']}/js/librerias/tempusdominus/5.1.3/tempusdominus-bootstrap-4.min.js"></script> --%>
	
	  <!-- Core plugin JavaScript-->
	  <script src="${prop['aplicacion.rutaRecursos']}/js/librerias/jquery-easing/1.4.1/jquery.easing.min.js"></script>
	  
	  
	  <script src="${prop['aplicacion.rutaRecursos']}/js/librerias/jquery-form-dls/jquery-validate-form-dls.js?fecha=<c:out value='${fechaHoraActual}' />"></script>
<%-- 	  <script src="${prop['aplicacion.rutaRecursos']}/js/librerias/jquery-form-dls/jquery-validate-form-dls.js"></script> --%>

	  <script src="${prop['aplicacion.rutaRecursos']}/js/sistema/menu.js?fecha=<c:out value='${fechaHoraActual}' />"></script>
	  	
	  <script type="text/javascript">
		  $(window).on("load", function (e) {
			  fnInicializarMenu("${pageContext.request.contextPath}",${jsonMenu});
		  });
	  </script>
	</head>
	
	<body id="page-top">
		
		<section id="header">
			<tiles:insertAttribute name="header" />
		</section>
  		<div id="wrapper">

  			<!-- Bloque de menu -->
  			<tiles:insertAttribute name="menu" flush="true" />

			<div id="content-wrapper">

	      		<div class="container-fluid">
			        <tiles:insertAttribute name="content" />
	      		</div>
	
		      	
		      	<tiles:insertAttribute name="footer" />
	    	</div>
  		</div>
		<!-- Scroll to Top Button-->
		<a class="scroll-to-top rounded" href="#page-top">
			<i class="fas fa-angle-up"></i>
		</a>

		<!-- Dialogo Salida : Inicio -->
		<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		    <div class="modal-dialog" role="document">
		      <div class="modal-content">
		        <div class="modal-header">
		          <h5 class="modal-title" id="exampleModalLabel">Desea cerrar sesi&oacute;n?</h5>
		          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
		            	<span aria-hidden="true">x</span>
		          </button>		
		        </div>
		        <div class="modal-body">Seleccione "Salir" si desea terminar la sesi&oacute;n actual.</div>
		        <div class="modal-footer">
		          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
		          <a class="btn btn-primary" href="<c:url value="/logout" />">Salir</a>
		        </div>
		      </div>
		    </div>
	  	</div>
	  	<!-- Dialogo Salida : Fin -->
	  	
<div class="modal modal-loading" id="loading" data-backdrop="static" tabindex="-1" role="dialog"
	aria-labelledby="mySmallModalLabel" aria-hidden="true" 
	style="z-index: 2000; background: rgba(0,0,0,0.3);">
	<div class="modal-dialog modal-sm modal-dialog-centered">
		<div class="modal-content" style="background: transparent; border: 0px;">
			<div class="row mx-auto">
				<div class="spinner-grow text-danger " role="status">
				<span class="sr-only">Loading...</span>
			</div>
			<div class="spinner-grow text-warning " role="status">
				<span class="sr-only">Loading...</span>
			</div>
			<div class="spinner-grow text-info " role="status">
				<span class="sr-only">Loading...</span>
			</div>
			</div>
		</div>
	</div>
</div>
	  	
	  	<script src="${prop['aplicacion.rutaRecursos']}/js/plantillas/sb-admin/sb-admin.min.js"></script>
	  	<script src="${prop['aplicacion.rutaRecursos']}/js/librerias/jquery-form-dls/jquery-util.js?fecha=<c:out value='${fechaHoraActual}' />"></script>
	</body>
</html>