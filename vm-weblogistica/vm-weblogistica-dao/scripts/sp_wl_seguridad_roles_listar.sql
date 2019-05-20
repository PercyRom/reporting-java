CREATE OR REPLACE FUNCTION sch_weblogistica.sp_wl_seguridad_roles_listar(vv_nombre_rol character varying, vv_vigente character varying, OUT vc_resultado refcursor, OUT vv_message character varying)
 RETURNS record
 LANGUAGE plpgsql
AS $function$
BEGIN

	OPEN vc_resultado for
	SELECT X.* from(
	SELECT tr.idRol as idRol,
	coalesce(tr.nombreRol,'') as nombreRol,
	coalesce(tr.descripcionRol,'') as descripcionRol,
	coalesce(tr.vigente,'N') as vigente,
	COALESCE(tr.idUsuarioCreacion,'') as idUsuarioCreacion,
	COALESCE(to_char(tr.fechaCreacion,'DD/MM/YYYY HH24:MI'),'') as fechaCreacion,
	COALESCE(tr.idUsuarioModifica,'') as idUsuarioModifica,
	COALESCE(to_char(tr.fechaModifica,'DD/MM/YYYY HH24:MI'),'') as fechaModifica
	from sch_weblogistica.tb_rol tr 
	where 
	upper(coalesce(tr.nombreRol,'')) like '%'||upper(COALESCE(vv_nombre_rol,'')::text)||'%' and
	upper(coalesce(tr.vigente,'')) like '%'||upper(COALESCE(vv_vigente,'')::text)||'%'
	order by tr.nombreRol asc) X;

EXCEPTION
		WHEN OTHERS THEN
			vv_message := '[ERROR][sch_weblogistica.sp_wl_seguridad_roles_listar]';
			vv_message := vv_message ||'[SQLERRM]['||sqlerrm||']';
END;
$function$
;
