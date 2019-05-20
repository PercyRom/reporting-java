package pe.com.vendemas.weblogistica.consult;

import java.util.List;

import pe.com.vendemas.weblogistica.domain.Rol;
import pe.com.vendemas.weblogistica.domain.Usuario;

public class StprUsuarioRol {
	
	private Usuario usuario;
	private List<Rol> listRoles;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Rol> getListRoles() {
		return listRoles;
	}
	public void setListRoles(List<Rol> listRoles) {
		this.listRoles = listRoles;
	}
	
}
