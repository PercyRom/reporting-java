package pe.com.vendemas.weblogistica.dao.util;

public class ConstantesDAO {

	public static String SCHEMA_NAME = "";
	
	public static final void iniciarlizarEsquema(String esquema) {
		ConstantesDAO.SCHEMA_NAME = esquema;
	}
	
	public static final String SP_USUARIO_LISTAR = "sp_usuario_listar";
	public static final String SP_USUARIO_REGISTRAR = "sp_usuario_registrar";
	public static final String SP_USUARIO_ACTUALIZAR = "sp_usuario_actualizar";
	public static final String SP_USUARIO_LISTAR_PAGINADO = "sp_usuario_listar_paginado";
	public static final String SP_USUARIO_TOTAL_USUARIOS = "sp_usuario_total_usuarios";
	public static final String SP_USUARIO_OBTENER_BY_CODIGO = "sp_usuario_obtener_bycodigo";
	public static final String SP_USUARIO_OBTENER_BY_ID = "sp_usuario_obtener_byid";
	
	public static final String SP_ROL_OBTENER_ROLES = "sp_rol_obtener_roles";
	public static final String SP_ROL_OBTENER_ROLES_BY_USUARIO = "sp_rol_obtener_roles_by_usuario";
	public static final String SP_ROL_LISTAR_PAGINADO = "sp_rol_listar_paginado";
	public static final String SP_ROL_REGISTRAR = "sp_rol_registrar";
	public static final String SP_ROL_ACTUALIZAR = "sp_rol_actualizar";
	public static final String SP_ROL_OBTENER_BY_ID = "sp_rol_obtener_byid";
	
	public static final String SP_ROL_USUARIO_MANT = "sp_rol_usuario_mant";
	public static final String SP_ROL_OPCION_MANT = "sp_rol_opcion_mant";
	
	public static final String SP_OPCION_OBTENER_BY_USUARIO = "sp_opcion_obtener_byusuario";
	public static final String SP_OPCION_OBTENER_OPCIONES_BY_ROL = "sp_opcion_obtener_opciones_by_rol";
	public static final String SP_OPCION_OBTENER_OPCIONES = "sp_opcion_obtener_opciones";
	
	public static final String SP_WL_SEGURIDAD_ROLES_LISTAR = "sp_wl_seguridad_roles_listar";
	public static final String SP_WL_SEGURIDAD_ROL_REGISTRAR = "sp_wl_seguridad_rol_registrar";
	public static final String SP_WL_SEGURIDAD_ROL_OPCIONES_LISTAR = "sp_wl_seguridad_rol_opciones_listar";
	public static final String SP_WL_SEGURIDAD_ROL_OPCION_REGISTRAR = "sp_wl_seguridad_rol_opcion_registrar";
	public static final String SP_WL_SEGURIDAD_USUARIO_OBTENER_OPCIONES = "sp_wl_seguridad_usuario_obtener_opciones";
	public static final String SP_WL_SEGURIDAD_USUARIOS_LISTAR = "sp_wl_seguridad_usuarios_listar";
	public static final String SP_WL_SEGURIDAD_USUARIO_REGISTRAR = "sp_wl_seguridad_usuario_registrar";
	public static final String SP_WL_SEGURIDAD_USUARIO_ROL_REGISTRAR = "sp_wl_seguridad_usuario_rol_registrar";
	public static final String SP_WL_SEGURIDAD_USUARIO_ROLES_LISTAR = "sp_wl_seguridad_usuario_roles_listar";
	public static final String SP_WL_OBTENER_USUARIO = "sp_wl_obtener_usuario";
	
	public static final String SP_ENTIDAD_LISTAR_PAGINADO = "sp_entidad_listar_paginado";
	public static final String SP_ENTIDAD_REGISTRAR = "sp_entidad_registrar";
	public static final String SP_ENTIDAD_OBTENER_BY_ID = "sp_entidad_obtener_byid";
	public static final String SP_ENTIDAD_OBTENER_BY_TIPO = "sp_entidad_obtener_by_tipo";
	public static final String SP_ENTIDAD_ACTUALIZAR = "sp_entidad_actualizar";
	
	public static final String SP_ENTIDAD_CONTACTOS_BY_ENTIDAD = "sp_entidad_contacto_by_entidad";
	public static final String SP_ENTIDAD_CONTACTOS_REGISTRAR = "sp_entidad_contactos_registrar";
	
	public static final String SP_TIPO_ENTIDAD_LISTAR = "sp_tipo_entidad_listar";
	
	public static final String SP_TIPO_DOC_IDENTIDAD_LISTAR = "sp_tipo_doc_ident_listar";
	
	public static final String SP_TIPO_PRODUCTO_LISTAR_VIGENTES = "sp_tipo_producto_obtener_vigentes";
	
	public static final String SP_COMPRAS_REGISTRAR = "sp_compra_registrar";
	
	public static final String SP_ENTRADAS_REGISTRAR = "sp_entradas_registrar";
	
	public static final String SP_ESTADO_CATALOGO_OBTENER_IDS = "sp_estado_catalogo_obtener_ids";

	
	public static final String CTLG_ESTADOS_COMPRA = "ESTADOS_COMPRA";
	public static final String CTLG_ESTADOS_ENTRADA = "ESTADOS_ENTRADA";
	
	public static final String ITEM_STAT_CMPR_REGISTRADO = "REGISTRADO";
	public static final String ITEM_STAT_CMPR_APROBADO = "APROBADO";
	public static final String ITEM_STAT_CMPR_EN_PROCESO = "EN PROCESO";
	public static final String ITEM_STAT_CMPR_COMPLETADO = "COMPLETADO";
	public static final String ITEM_STAT_CMPR_COMPLETADO_PARCIAL = "COMPLETADO PARCIAL";
	public static final String ITEM_STAT_CMPR_ANULADO = "ANULADO";
	
	public static final String ITEM_STAT_ENTRD_REGISTRADO = "REGISTRADO";
	public static final String ITEM_STAT_ENTRD_APROBADO = "APROBADO";
	public static final String ITEM_STAT_ENTRD_ANULADO = "ANULADO";
	
	public static final Integer TIPO_MOVIMIENTO_COMPRA = 1;
	
}
