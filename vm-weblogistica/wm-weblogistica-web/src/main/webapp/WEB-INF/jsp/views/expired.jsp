<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<c:set var="now" value="<%=Calendar.getInstance().getTime()%>" />
<fmt:formatDate value="${now}" pattern="ddMMyyyyHHmmss" var="fechaHoraActual" />

<html lang="es">
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

	  <!-- Custom styles for this template-->
	  <link href="${prop['aplicacion.rutaRecursos']}/css/plantillas/sb-admin/sb-admin.css" rel="stylesheet" />
	  
	  <link href="${prop['aplicacion.rutaRecursos']}/css/sistema/login.css?fecha=<c:out value='${fechaHoraActual}' />" rel="stylesheet" />
	  
	  <!-- Bootstrap core JavaScript-->
	  <script src="${prop['aplicacion.rutaRecursos']}/js/librerias/jquery/3.3.1/jquery.min.js"></script>
	  <script src="${prop['aplicacion.rutaRecursos']}/js/librerias/bootstrap/4.3.1/bootstrap.bundle.min.js"></script>
	  
	  <!-- Core plugin JavaScript-->
	  <script src="${prop['aplicacion.rutaRecursos']}/js/librerias/jquery-easing/1.4.1/jquery.easing.min.js"></script>
		
	  <!-- Core plugin JavaScript-->
	  <script src="${prop['aplicacion.rutaRecursos']}/js/sistema/login.js?fecha=<c:out value='${fechaHoraActual}' />"></script>
	</head>

	<body class="bg-dark">
	  <div class="container">
	    <div class="card card-login mx-auto mt-5">
	      <div class="card-header">
	      		<img src="${prop['aplicacion.rutaRecursos']}/img/vendemas.svg?fecha=<c:out value='${fechaHoraActual}' />" width="100" height="50" />
	      		<label class="labelTitulo">Web Log&iacute;stica Vendem&aacute;s</label>
	      </div>
	      <div class="card-body">
	        <h1>Session Expired</h1>
	        <%-- 
	        <div class="text-center">
	          <a class="d-block small mt-3" href="<c:url value="/pages/sbadmin-01/register.jsp" />">Register an Account</a>
	          <a class="d-block small" href="<c:url value="/pages/sbadmin-01/forgot-password.jsp" />">Forgot Password?</a>
	        </div>
	        --%>
	      </div>
	    </div>
	  </div>
	</body>
</html>