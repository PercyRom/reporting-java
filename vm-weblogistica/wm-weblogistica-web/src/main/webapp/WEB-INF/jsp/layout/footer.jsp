<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!-- Sticky Footer -->
<footer class="sticky-footer">
 	<div class="container my-auto">
   		<div class="copyright text-center my-auto">
     		<span>Copyright &copy; <a href="${prop['aplicacion.urlEmpresa']}" target="_blank">${prop['aplicacion.nombreEmpresa']}</a>&nbsp;${prop['aplicacion.anioSistema']}</span>
   		</div>
 	</div>
</footer>