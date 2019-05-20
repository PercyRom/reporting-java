/*
 Navicat Premium Data Transfer

 Source Server         : postgresql@localhost
 Source Server Type    : PostgreSQL
 Source Server Version : 90610
 Source Host           : localhost:5432
 Source Catalog        : vm_weblogistica
 Source Schema         : sch_weblogistica

 Target Server Type    : PostgreSQL
 Target Server Version : 90610
 File Encoding         : 65001

 Date: 25/04/2019 11:39:57
*/


-- ----------------------------
-- Sequence structure for tb_rol_idrol_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "sch_weblogistica"."tb_rol_idrol_seq";
CREATE SEQUENCE "sch_weblogistica"."tb_rol_idrol_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for tb_usuario_idusuario_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "sch_weblogistica"."tb_usuario_idusuario_seq";
CREATE SEQUENCE "sch_weblogistica"."tb_usuario_idusuario_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for tb_opciones
-- ----------------------------
DROP TABLE IF EXISTS "sch_weblogistica"."tb_opciones";
CREATE TABLE "sch_weblogistica"."tb_opciones" (
  "idopcion" int4 NOT NULL,
  "idpadre" int4 NOT NULL,
  "nombreopcion" varchar COLLATE "pg_catalog"."default",
  "linkopcion" varchar COLLATE "pg_catalog"."default",
  "vigente" varchar COLLATE "pg_catalog"."default",
  "esmenu" varchar COLLATE "pg_catalog"."default",
  "orden" varchar COLLATE "pg_catalog"."default",
  "icono" varchar COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of tb_opciones
-- ----------------------------
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (4, 1, 'Pedidos Preparados', 'pedidos/pedidosPreparados', 'S', 'S', '005.015', NULL);
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (5, 1, 'Asignaciones', 'asignacion/consultarAsignaciones', 'S', 'S', '005.020', NULL);
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (6, 1, 'Pedidos Asignados', 'pedidos/pedidosAsignados', 'S', 'S', '005.025', NULL);
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (7, 1, 'Actualizar Pedido', 'pedidos/busquedaPedido', 'S', 'S', '005.030', NULL);
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (9, 8, 'Estado de Pedidos', 'pedidos/estadoPedidos', 'S', 'S', '010.005', NULL);
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (10, 8, 'Reprogramación', 'pedidos/reprogramacion', 'S', 'S', '010.010', NULL);
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (3, 1, 'Pedidos Recibidos', 'pedidos/pedidosRecibidos', 'S', 'S', '005.010', NULL);
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (12, 11, 'Usuarios', 'seguridad/usuarios', 'S', 'S', '015.005', NULL);
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (13, 11, 'Roles', 'seguridad/roles', 'S', 'S', '015.010', NULL);
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (15, 14, 'Registro prospectos', 'registro', 'S', 'S', '020.005', NULL);
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (16, 14, 'Listado de prospectos', 'pedidos/prospectos', 'S', 'S', '020.010', NULL);
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (17, 1, 'Actualizar Pedido Masivo', 'pedidos/cargaArchivoActualizacion', 'S', 'S', '005.031', NULL);
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (18, 8, 'Edición de Pedidos', 'pedidos/edicionPedidos', 'S', 'S', '010.004', NULL);
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (19, 8, 'Reprocesar Facturación', 'pedidos/reprocesarFacturacion', 'S', 'S', '010.011', NULL);
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (2, 0, 'Dashboard', 'dashboard', 'S', 'S', '004', 'fa-tachometer-alt');
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (1, 0, 'Pedidos', '#', 'S', 'N', '005', NULL);
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (8, 0, 'Call Center', '#', 'S', 'N', '010', NULL);
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (11, 0, 'Administración', '#', 'S', 'N', '015', NULL);
INSERT INTO "sch_weblogistica"."tb_opciones" VALUES (14, 0, 'Prospectos', '#', 'S', 'N', '020', NULL);

-- ----------------------------
-- Table structure for tb_opciones_menu
-- ----------------------------
DROP TABLE IF EXISTS "sch_weblogistica"."tb_opciones_menu";
CREATE TABLE "sch_weblogistica"."tb_opciones_menu" (
  "idopcionmenu" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "idopcion" int4 NOT NULL
)
;

-- ----------------------------
-- Records of tb_opciones_menu
-- ----------------------------
INSERT INTO "sch_weblogistica"."tb_opciones_menu" VALUES ('ID_OPCION_DASHBOARD', 2);
INSERT INTO "sch_weblogistica"."tb_opciones_menu" VALUES ('ID_OPCION_PEDIDOS_PREPARADOS', 4);
INSERT INTO "sch_weblogistica"."tb_opciones_menu" VALUES ('ID_OPCION_ASIGNACIONES', 5);
INSERT INTO "sch_weblogistica"."tb_opciones_menu" VALUES ('ID_OPCION_PEDIDOS_ASIGNADOS', 6);
INSERT INTO "sch_weblogistica"."tb_opciones_menu" VALUES ('ID_OPCION_ACTUALIZAR_PEDIDO', 7);
INSERT INTO "sch_weblogistica"."tb_opciones_menu" VALUES ('ID_OPCION_REPROGRAMACION', 10);
INSERT INTO "sch_weblogistica"."tb_opciones_menu" VALUES ('ID_OPCION_PEDIDOS_RECIBIDOS', 3);
INSERT INTO "sch_weblogistica"."tb_opciones_menu" VALUES ('ID_OPCION_USUARIOS', 12);
INSERT INTO "sch_weblogistica"."tb_opciones_menu" VALUES ('ID_OPCION_ROLES', 13);
INSERT INTO "sch_weblogistica"."tb_opciones_menu" VALUES ('ID_OPCION_REGISTRAR', 15);
INSERT INTO "sch_weblogistica"."tb_opciones_menu" VALUES ('ID_OPCION_PROSPECTOS', 16);
INSERT INTO "sch_weblogistica"."tb_opciones_menu" VALUES ('ID_OPCION_ACTUALIZAR_PEDIDO_MASIVO', 17);
INSERT INTO "sch_weblogistica"."tb_opciones_menu" VALUES ('ID_OPCION_EDICION_PEDIDOS', 18);
INSERT INTO "sch_weblogistica"."tb_opciones_menu" VALUES ('ID_OPCION_REPROCESAR_FACTURACION', 19);
INSERT INTO "sch_weblogistica"."tb_opciones_menu" VALUES ('ID_OPCION_REGISTRO_PROSPECTO', 15);
INSERT INTO "sch_weblogistica"."tb_opciones_menu" VALUES ('ID_OPCION_ESTADO_PEDIDOS', 9);

-- ----------------------------
-- Table structure for tb_rol
-- ----------------------------
DROP TABLE IF EXISTS "sch_weblogistica"."tb_rol";
CREATE TABLE "sch_weblogistica"."tb_rol" (
  "idrol" int4 NOT NULL DEFAULT nextval('"sch_weblogistica".tb_rol_idrol_seq'::regclass),
  "nombrerol" varchar COLLATE "pg_catalog"."default",
  "descripcionrol" varchar COLLATE "pg_catalog"."default",
  "vigente" varchar COLLATE "pg_catalog"."default",
  "idusuariocreacion" varchar COLLATE "pg_catalog"."default",
  "fechacreacion" timestamp(6),
  "idusuariomodifica" varchar COLLATE "pg_catalog"."default",
  "fechamodifica" timestamp(6)
)
;

-- ----------------------------
-- Records of tb_rol
-- ----------------------------
INSERT INTO "sch_weblogistica"."tb_rol" VALUES (1, 'ADMINISTRADOR', 'Administrador', 'S', 'mcarbajal', '2019-04-10 17:45:02.877746', NULL, NULL);

-- ----------------------------
-- Table structure for tb_rol_opciones
-- ----------------------------
DROP TABLE IF EXISTS "sch_weblogistica"."tb_rol_opciones";
CREATE TABLE "sch_weblogistica"."tb_rol_opciones" (
  "idrol" int4 NOT NULL,
  "idopcion" int4 NOT NULL,
  "vigente" varchar COLLATE "pg_catalog"."default",
  "idusuariocreacion" varchar COLLATE "pg_catalog"."default",
  "fechacreacion" timestamp(6),
  "idusuariomodifica" varchar COLLATE "pg_catalog"."default",
  "fechamodifica" timestamp(6)
)
;

-- ----------------------------
-- Records of tb_rol_opciones
-- ----------------------------
INSERT INTO "sch_weblogistica"."tb_rol_opciones" VALUES (1, 2, 'S', 'amendoza', '2019-04-10 18:12:24.104321', NULL, NULL);
INSERT INTO "sch_weblogistica"."tb_rol_opciones" VALUES (1, 3, 'S', 'amendoza', '2019-04-10 18:12:24.12889', NULL, NULL);
INSERT INTO "sch_weblogistica"."tb_rol_opciones" VALUES (1, 4, 'S', 'amendoza', '2019-04-10 18:12:24.148802', NULL, NULL);
INSERT INTO "sch_weblogistica"."tb_rol_opciones" VALUES (1, 5, 'S', 'amendoza', '2019-04-10 18:12:24.170882', NULL, NULL);
INSERT INTO "sch_weblogistica"."tb_rol_opciones" VALUES (1, 6, 'S', 'amendoza', '2019-04-10 18:12:24.193817', NULL, NULL);
INSERT INTO "sch_weblogistica"."tb_rol_opciones" VALUES (1, 7, 'S', 'amendoza', '2019-04-10 18:12:24.215774', NULL, NULL);
INSERT INTO "sch_weblogistica"."tb_rol_opciones" VALUES (1, 9, 'S', 'amendoza', '2019-04-10 18:12:24.237085', NULL, NULL);
INSERT INTO "sch_weblogistica"."tb_rol_opciones" VALUES (1, 10, 'S', 'amendoza', '2019-04-10 18:12:24.258763', NULL, NULL);
INSERT INTO "sch_weblogistica"."tb_rol_opciones" VALUES (1, 12, 'S', 'amendoza', '2019-04-10 18:12:24.28072', NULL, NULL);
INSERT INTO "sch_weblogistica"."tb_rol_opciones" VALUES (1, 13, 'S', 'amendoza', '2019-04-10 18:12:24.304663', NULL, NULL);
INSERT INTO "sch_weblogistica"."tb_rol_opciones" VALUES (1, 15, 'S', 'mcarbajal', '2019-04-10 18:12:24.327964', NULL, NULL);
INSERT INTO "sch_weblogistica"."tb_rol_opciones" VALUES (1, 16, 'S', 'mcarbajal', '2019-04-10 18:12:24.350108', NULL, NULL);
INSERT INTO "sch_weblogistica"."tb_rol_opciones" VALUES (1, 17, 'S', 'mcarbajal', '2019-04-10 18:12:24.372177', NULL, NULL);
INSERT INTO "sch_weblogistica"."tb_rol_opciones" VALUES (1, 18, 'S', 'amendoza', '2019-04-10 18:12:24.39709', NULL, NULL);
INSERT INTO "sch_weblogistica"."tb_rol_opciones" VALUES (1, 19, 'S', 'mcarbajal', '2019-04-10 18:12:24.421446', NULL, NULL);

-- ----------------------------
-- Table structure for tb_rol_usuario
-- ----------------------------
DROP TABLE IF EXISTS "sch_weblogistica"."tb_rol_usuario";
CREATE TABLE "sch_weblogistica"."tb_rol_usuario" (
  "idrol" int4 NOT NULL,
  "idusuario" int4 NOT NULL,
  "vigente" varchar COLLATE "pg_catalog"."default",
  "idusuariocreacion" varchar COLLATE "pg_catalog"."default",
  "fechacreacion" timestamp(6),
  "idusuariomodifica" varchar COLLATE "pg_catalog"."default",
  "fechamodifica" timestamp(6)
)
;

-- ----------------------------
-- Records of tb_rol_usuario
-- ----------------------------
INSERT INTO "sch_weblogistica"."tb_rol_usuario" VALUES (1, 1, 'S', 'jcamacho', '2019-04-10 17:46:11.812317', NULL, NULL);

-- ----------------------------
-- Table structure for tb_usuario
-- ----------------------------
DROP TABLE IF EXISTS "sch_weblogistica"."tb_usuario";
CREATE TABLE "sch_weblogistica"."tb_usuario" (
  "idusuario" int4 NOT NULL DEFAULT nextval('"sch_weblogistica".tb_usuario_idusuario_seq'::regclass),
  "codigousuario" varchar COLLATE "pg_catalog"."default",
  "clave" varchar COLLATE "pg_catalog"."default",
  "email" varchar COLLATE "pg_catalog"."default",
  "nombres" varchar COLLATE "pg_catalog"."default",
  "apellidopaterno" varchar COLLATE "pg_catalog"."default",
  "apellidomaterno" varchar COLLATE "pg_catalog"."default",
  "idestado" int4,
  "idusuariocreacion" varchar COLLATE "pg_catalog"."default",
  "fechacreacion" timestamp(6),
  "idusuariomodifica" varchar COLLATE "pg_catalog"."default",
  "fechamodifica" timestamp(6)
)
;

-- ----------------------------
-- Records of tb_usuario
-- ----------------------------
INSERT INTO "sch_weblogistica"."tb_usuario" VALUES (1, 'jcamacho', '123456', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Function structure for sp_wl_obtener_usuario
-- ----------------------------
DROP FUNCTION IF EXISTS "sch_weblogistica"."sp_wl_obtener_usuario"("var_cuenta" varchar);
CREATE OR REPLACE FUNCTION "sch_weblogistica"."sp_wl_obtener_usuario"("var_cuenta" varchar)
  RETURNS "pg_catalog"."refcursor" AS $BODY$
DECLARE resultado REFCURSOR;
BEGIN

	OPEN resultado for
	SELECT  u.*
	from sch_weblogistica.tb_usuario u
	where 	LTRIM(RTRIM(LOWER(u.codigoUsuario))) = LTRIM(RTRIM(LOWER(var_cuenta))) limit 1 ;

RETURN resultado;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for sp_wl_seguridad_rol_opcion_registrar
-- ----------------------------
DROP FUNCTION IF EXISTS "sch_weblogistica"."sp_wl_seguridad_rol_opcion_registrar"(IN "var_id_rol" int4, IN "var_id_opcion" int4, IN "var_vigente" varchar, IN "usuario_auditoria" varchar, IN "fecha_auditoria" varchar, IN "var_operacion" varchar, OUT "resultado" int4);
CREATE OR REPLACE FUNCTION "sch_weblogistica"."sp_wl_seguridad_rol_opcion_registrar"(IN "var_id_rol" int4, IN "var_id_opcion" int4, IN "var_vigente" varchar, IN "usuario_auditoria" varchar, IN "fecha_auditoria" varchar, IN "var_operacion" varchar, OUT "resultado" int4)
  RETURNS "pg_catalog"."int4" AS $BODY$
DECLARE dt_fecha_auditoria timestamp;
DECLARE var_id_seq int;
BEGIN

	IF COALESCE(fecha_auditoria,'') <> '' THEN
		SELECT to_timestamp(fecha_auditoria,'DD/MM/YYYY HH24:MI') INTO dt_fecha_auditoria;
	ELSE 
		SELECT CURRENT_TIMESTAMP INTO dt_fecha_auditoria;
	END IF;

	IF var_operacion = 'I' THEN
	
		insert into sch_weblogistica.tb_rol_opciones
		(idRol,idOpcion,vigente,idUsuarioCreacion,fechaCreacion)
		values
		(var_id_rol,var_id_opcion,var_vigente,usuario_auditoria,dt_fecha_auditoria);
	ELSE 
		UPDATE sch_weblogistica.tb_rol_opciones
		set vigente = var_vigente,
		idUsuarioModifica = usuario_auditoria,
		fechaModifica = dt_fecha_auditoria where idRol = var_id_rol and idOpcion = var_id_opcion;
		
	END IF;

	resultado := 1;	
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for sp_wl_seguridad_rol_opciones_listar
-- ----------------------------
DROP FUNCTION IF EXISTS "sch_weblogistica"."sp_wl_seguridad_rol_opciones_listar"("var_id_rol" int4);
CREATE OR REPLACE FUNCTION "sch_weblogistica"."sp_wl_seguridad_rol_opciones_listar"("var_id_rol" int4)
  RETURNS "pg_catalog"."refcursor" AS $BODY$
DECLARE resultado REFCURSOR;
BEGIN

	OPEN resultado for
	select X.* from (
	select 
	coalesce(trp.idRol,0) as idRolAsignadoInicial,
	coalesce(trp.idROL,0) as idRolAsignado,
	op.idOpcion as idOpcion,
	op.idPadre as idPadre,
	op.nombreOpcion as nombreOpcion,
	op.esMenu as esMenu,
	coalesce(trp.vigente,'N') as vigente,
	coalesce(trp.vigente,'N') as vigenteInicial,
	op.orden
	from sch_trackingbox.tb_opciones op 
	left join sch_trackingbox.tb_rol_opciones trp on (trp.idOpcion = op.idOpcion and trp.idRol = var_id_rol)
	where op.esMenu = 'S' 
	UNION ALL
	select 
	0 as idRolAsignadoInicial,
	0 as idrolasignado,
	op.idOpcion,
	op.idPadre,
	op.nombreOpcion,
	op.esMenu,
	'N' as vigente,
	'N' as vigenteInicial,
	op.orden from sch_weblogistica.tb_opciones op where op.esMenu = 'N')X
	order by X.orden asc;

RETURN resultado;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for sp_wl_seguridad_rol_registrar
-- ----------------------------
DROP FUNCTION IF EXISTS "sch_weblogistica"."sp_wl_seguridad_rol_registrar"(IN "var_id" int4, IN "var_nombre_rol" varchar, IN "var_descripcion_rol" varchar, IN "var_vigente" varchar, IN "usuario_auditoria" varchar, IN "fecha_auditoria" varchar, OUT "resultado" int4);
CREATE OR REPLACE FUNCTION "sch_weblogistica"."sp_wl_seguridad_rol_registrar"(IN "var_id" int4, IN "var_nombre_rol" varchar, IN "var_descripcion_rol" varchar, IN "var_vigente" varchar, IN "usuario_auditoria" varchar, IN "fecha_auditoria" varchar, OUT "resultado" int4)
  RETURNS "pg_catalog"."int4" AS $BODY$
DECLARE dt_fecha_auditoria timestamp;
DECLARE var_id_seq int;
BEGIN
		
	
	IF COALESCE(fecha_auditoria,'') <> '' THEN
		SELECT to_timestamp(fecha_auditoria,'DD/MM/YYYY HH24:MI') INTO dt_fecha_auditoria;
	ELSE 
		SELECT CURRENT_TIMESTAMP INTO dt_fecha_auditoria;
	END IF;
	
	IF var_id = 0 then

		select nextval('sch_weblogistica.tb_seguridad_idrol_seq'::regclass) into var_id_seq;
		
		insert into sch_weblogistica.tb_rol
		(idRol,nombreRol,descripcionRol,vigente,idUsuarioCreacion,fechaCreacion)
		values
		(var_id_seq,var_nombre_rol,var_descripcion_rol,var_vigente,usuario_auditoria,dt_fecha_auditoria);

		resultado := var_id_seq;
	else 
		update sch_weblogistica.tb_rol
		set 
		nombreRol = var_nombre_rol,
		descripcionRol = var_descripcion_rol,
		vigente = var_vigente,
		idUsuarioModifica = usuario_auditoria,
		fechaModifica = dt_fecha_auditoria where idRol = var_id;

		resultado := var_id;
	end if;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for sp_wl_seguridad_roles_listar
-- ----------------------------
DROP FUNCTION IF EXISTS "sch_weblogistica"."sp_wl_seguridad_roles_listar"("var_nombre_rol" varchar, "var_vigente" varchar);
CREATE OR REPLACE FUNCTION "sch_weblogistica"."sp_wl_seguridad_roles_listar"("var_nombre_rol" varchar, "var_vigente" varchar)
  RETURNS "pg_catalog"."refcursor" AS $BODY$
DECLARE resultado REFCURSOR;
BEGIN

	OPEN resultado for
	SELECT X.* from(
	SELECT tr.idRol as idRol,
	coalesce(tr.nombreRol,'') as nombreRol,
	coalesce(tr.descripcionRol,'') as descripcionRol,
	coalesce(tr.vigente,'N') as vigente,
	COALESCE(tr.idUsuarioCreacion,'') as idUsuarioCreacion,
	COALESCE(to_char(tr.fechaCreacion,'DD/MM/YYYY HH24:MI'),'') as fechaCreacion,
	COALESCE(tr.idUsuarioModifica,'') as idUsuarioModifica,
	COALESCE(to_char(tr.fechaModifica,'DD/MM/YYYY HH24:MI'),'') as fechaModifica
	from sch_trackingbox.tb_rol tr 
	where 
	upper(coalesce(tr.nombreRol,'')) like '%'||upper(COALESCE(var_nombre_rol,'')::text)||'%' and
	upper(coalesce(tr.vigente,'')) like '%'||upper(COALESCE(var_vigente,'')::text)||'%'
	order by tr.nombreRol asc) X;

RETURN resultado;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for sp_wl_seguridad_usuario_obtener_opciones
-- ----------------------------
DROP FUNCTION IF EXISTS "sch_weblogistica"."sp_wl_seguridad_usuario_obtener_opciones"("var_id_usuario" int4);
CREATE OR REPLACE FUNCTION "sch_weblogistica"."sp_wl_seguridad_usuario_obtener_opciones"("var_id_usuario" int4)
  RETURNS "pg_catalog"."refcursor" AS $BODY$
	DECLARE resultado REFCURSOR;

BEGIN

	CREATE TEMP TABLE tmp_consulta_opciones as (
	select distinct op.* from sch_weblogistica.tb_opciones op
	inner join sch_weblogistica.tb_rol_opciones rol on rol.idOpcion = op.idOpcion and rol.vigente = 'S' and op.vigente = 'S' and op.esMenu = 'S'
	inner join sch_weblogistica.tb_rol_usuario usr on usr.idRol = rol.idRol and usr.idUsuario = var_id_usuario and usr.vigente = 'S'
	order by op.orden asc); 

	--Listar los padres
	insert into tmp_consulta_opciones
	select op.* from sch_weblogistica.tb_opciones op where op.idOpcion in (select idPadre from tmp_consulta_opciones);

	OPEN resultado for
	SELECT X.* from(
	select tmp.* from tmp_consulta_opciones tmp order by tmp.orden asc
	)X;
RETURN resultado;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for sp_wl_seguridad_usuario_registrar
-- ----------------------------
DROP FUNCTION IF EXISTS "sch_weblogistica"."sp_wl_seguridad_usuario_registrar"(IN "var_id" int4, IN "var_codigo_usuario" varchar, IN "var_clave" varchar, IN "var_email" varchar, IN "var_nombres" varchar, IN "var_apellidopaterno" varchar, IN "var_apellidomaterno" varchar, IN "var_estado" int4, IN "usuario_auditoria" varchar, IN "fecha_auditoria" varchar, OUT "resultado" int4);
CREATE OR REPLACE FUNCTION "sch_weblogistica"."sp_wl_seguridad_usuario_registrar"(IN "var_id" int4, IN "var_codigo_usuario" varchar, IN "var_clave" varchar, IN "var_email" varchar, IN "var_nombres" varchar, IN "var_apellidopaterno" varchar, IN "var_apellidomaterno" varchar, IN "var_estado" int4, IN "usuario_auditoria" varchar, IN "fecha_auditoria" varchar, OUT "resultado" int4)
  RETURNS "pg_catalog"."int4" AS $BODY$
DECLARE dt_fecha_auditoria timestamp;
DECLARE var_id_seq int;
BEGIN

	resultado := -1;
	IF COALESCE(fecha_auditoria,'') <> '' THEN
		SELECT to_timestamp(fecha_auditoria,'DD/MM/YYYY HH24:MI') INTO dt_fecha_auditoria;
	ELSE 
		SELECT CURRENT_TIMESTAMP INTO dt_fecha_auditoria;
	END IF;
	
	IF var_id = 0 then

		select nextval('sch_weblogistica.tb_seguridad_idusuario_seq'::regclass) into var_id_seq;
		
		insert into sch_weblogistica.tb_usuario
		(idUsuario,codigoUsuario,clave,email,nombres,apellidopaterno,apellidomaterno,idEstado,idUsuarioCreacion,fechaCreacion)
		values
		(var_id_seq,var_codigo_usuario,var_clave,var_email,var_nombres,var_apellidopaterno,var_apellidomaterno,var_estado,usuario_auditoria,dt_fecha_auditoria);

		resultado := var_id_seq;
	else 
		update sch_weblogistica.tb_usuario
		set codigoUsuario = var_codigo_usuario,
		clave = var_clave,
		email = var_email,
		nombres = var_nombres,
		apellidopaterno = var_apellidopaterno,
		apellidomaterno = var_apellidomaterno,
		idEstado =  var_estado,
		idUsuarioModifica = usuario_auditoria,
		fechaModifica = dt_fecha_auditoria where idUsuario = var_id;

		resultado := var_id;
	end if;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for sp_wl_seguridad_usuario_rol_registrar
-- ----------------------------
DROP FUNCTION IF EXISTS "sch_weblogistica"."sp_wl_seguridad_usuario_rol_registrar"(IN "var_id_usuario" int4, IN "var_id_rol" int4, IN "var_vigente" varchar, IN "usuario_auditoria" varchar, IN "fecha_auditoria" varchar, IN "var_operacion" varchar, OUT "resultado" int4);
CREATE OR REPLACE FUNCTION "sch_weblogistica"."sp_wl_seguridad_usuario_rol_registrar"(IN "var_id_usuario" int4, IN "var_id_rol" int4, IN "var_vigente" varchar, IN "usuario_auditoria" varchar, IN "fecha_auditoria" varchar, IN "var_operacion" varchar, OUT "resultado" int4)
  RETURNS "pg_catalog"."int4" AS $BODY$
DECLARE dt_fecha_auditoria timestamp;
DECLARE var_id_seq int;
BEGIN

	IF COALESCE(fecha_auditoria,'') <> '' THEN
		SELECT to_timestamp(fecha_auditoria,'DD/MM/YYYY HH24:MI') INTO dt_fecha_auditoria;
	ELSE 
		SELECT CURRENT_TIMESTAMP INTO dt_fecha_auditoria;
	END IF;

	IF var_operacion = 'I' THEN
	
		insert into sch_weblogistica.tb_rol_usuario
		(idRol,idUsuario,vigente,idUsuarioCreacion,fechaCreacion)
		values
		(var_id_rol,var_id_usuario,var_vigente,usuario_auditoria,dt_fecha_auditoria);
	ELSE 
		UPDATE sch_weblogistica.tb_rol_usuario
		set vigente = var_vigente,
		idUsuarioModifica = usuario_auditoria,
		fechaModifica = dt_fecha_auditoria where id_rol = var_id_rol and id_usuario = var_id_usuario;
		
	END IF;

	resultado := 1;	
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for sp_wl_seguridad_usuario_roles_listar
-- ----------------------------
DROP FUNCTION IF EXISTS "sch_weblogistica"."sp_wl_seguridad_usuario_roles_listar"("var_id_usuario" int4);
CREATE OR REPLACE FUNCTION "sch_weblogistica"."sp_wl_seguridad_usuario_roles_listar"("var_id_usuario" int4)
  RETURNS "pg_catalog"."refcursor" AS $BODY$
DECLARE resultado REFCURSOR;
BEGIN

	OPEN resultado for
	select 
	coalesce(tr.idUsuario,0) as idUsuarioAsignadoInicial,
	coalesce(tr.idUsuario,0) as idUsuarioAsignado,
	r.idRol as idRol,
	r.nombreRol as nombreRol,
	coalesce(tr.vigente,'N') as vigente,
	coalesce(tr.vigente,'N') as vigenteInicial from sch_trackingbox.tb_rol r
	left join sch_weblogistica.tb_rol_usuario tr on (tr.idRol = r.idRol and tr.idUsuario = var_id_usuario)
	where r.idRol <>1
	order by r.nombreRol asc;

RETURN resultado;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for sp_wl_seguridad_usuarios_listar
-- ----------------------------
DROP FUNCTION IF EXISTS "sch_weblogistica"."sp_wl_seguridad_usuarios_listar"("var_codigo_usuario" varchar, "var_apellido_paterno" varchar, "var_apellido_materno" varchar, "var_nombres" varchar, "var_estado" varchar);
CREATE OR REPLACE FUNCTION "sch_weblogistica"."sp_wl_seguridad_usuarios_listar"("var_codigo_usuario" varchar, "var_apellido_paterno" varchar, "var_apellido_materno" varchar, "var_nombres" varchar, "var_estado" varchar)
  RETURNS "pg_catalog"."refcursor" AS $BODY$
DECLARE resultado REFCURSOR;
BEGIN

	OPEN resultado for
	SELECT X.* from(
	SELECT t.idUsuario as idUsuario,
	coalesce(t.codigoUsuario,'') as codigoUsuario,
	coalesce(t.clave,'') as clave,
	coalesce(t.email,'') as email,
	coalesce(t.nombres,'') as nombres,
	coalesce(t.apellidoPaterno,'') as apellidoPaterno,
	coalesce(t.apellidoMaterno,'') as apellidoMaterno,
	t.idEstado as idEstado
	from sch_weblogistica.tb_usuario t
	where 
	upper(coalesce(t.codigoUsuario,'')) like '%'||upper(COALESCE(var_codigo_usuario,'')::text)||'%' and
	upper(coalesce(t.apellidoPaterno,'')) like '%'||upper(COALESCE(var_apellido_paterno,'')::text)||'%' and
	upper(coalesce(t.apellidoMaterno,'')) like '%'||upper(COALESCE(var_apellido_materno,'')::text)||'%' and
	upper(coalesce(t.nombres,'')) like '%'||upper(COALESCE(var_nombres,'')::text)||'%' and
	(var_estado = '' or t.idEstado::text = var_estado::text)
	order by t.codigo_usuario asc) X;

RETURN resultado;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "sch_weblogistica"."tb_rol_idrol_seq"
OWNED BY "sch_weblogistica"."tb_rol"."idrol";
SELECT setval('"sch_weblogistica"."tb_rol_idrol_seq"', 3, false);
ALTER SEQUENCE "sch_weblogistica"."tb_usuario_idusuario_seq"
OWNED BY "sch_weblogistica"."tb_usuario"."idusuario";
SELECT setval('"sch_weblogistica"."tb_usuario_idusuario_seq"', 3, true);

-- ----------------------------
-- Primary Key structure for table tb_opciones
-- ----------------------------
ALTER TABLE "sch_weblogistica"."tb_opciones" ADD CONSTRAINT "tb_opciones_pkey" PRIMARY KEY ("idopcion");

-- ----------------------------
-- Primary Key structure for table tb_opciones_menu
-- ----------------------------
ALTER TABLE "sch_weblogistica"."tb_opciones_menu" ADD CONSTRAINT "tb_opciones_menu_pkey" PRIMARY KEY ("idopcionmenu");

-- ----------------------------
-- Primary Key structure for table tb_rol
-- ----------------------------
ALTER TABLE "sch_weblogistica"."tb_rol" ADD CONSTRAINT "pk_rol" PRIMARY KEY ("idrol");

-- ----------------------------
-- Primary Key structure for table tb_rol_opciones
-- ----------------------------
ALTER TABLE "sch_weblogistica"."tb_rol_opciones" ADD CONSTRAINT "tb_rol_opcion_pkey" PRIMARY KEY ("idrol", "idopcion");

-- ----------------------------
-- Primary Key structure for table tb_rol_usuario
-- ----------------------------
ALTER TABLE "sch_weblogistica"."tb_rol_usuario" ADD CONSTRAINT "tb_rol_usuario_pkey" PRIMARY KEY ("idrol", "idusuario");

-- ----------------------------
-- Primary Key structure for table tb_usuario
-- ----------------------------
ALTER TABLE "sch_weblogistica"."tb_usuario" ADD CONSTRAINT "pk_tb_usuario" PRIMARY KEY ("idusuario");

-- ----------------------------
-- Foreign Keys structure for table tb_rol_opciones
-- ----------------------------
ALTER TABLE "sch_weblogistica"."tb_rol_opciones" ADD CONSTRAINT "tb_rolopciones_opcion" FOREIGN KEY ("idopcion") REFERENCES "sch_weblogistica"."tb_opciones" ("idopcion") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "sch_weblogistica"."tb_rol_opciones" ADD CONSTRAINT "tb_rolopciones_rol" FOREIGN KEY ("idrol") REFERENCES "sch_weblogistica"."tb_rol" ("idrol") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table tb_rol_usuario
-- ----------------------------
ALTER TABLE "sch_weblogistica"."tb_rol_usuario" ADD CONSTRAINT "tb_rolusuario_rol" FOREIGN KEY ("idrol") REFERENCES "sch_weblogistica"."tb_rol" ("idrol") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "sch_weblogistica"."tb_rol_usuario" ADD CONSTRAINT "tb_rolusuario_usuario" FOREIGN KEY ("idusuario") REFERENCES "sch_weblogistica"."tb_usuario" ("idusuario") ON DELETE NO ACTION ON UPDATE NO ACTION;
