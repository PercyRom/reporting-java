package pe.com.vendemas.weblogistica.seguridad.bean;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UsuarioWsSpring extends User {

	private static final long serialVersionUID = 1L;
	
	private UsuarioWebBean usuario;
	
	public UsuarioWsSpring(UsuarioWebBean usuario, String user, String password,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(user, password, enabled, accountNonExpired,credentialsNonExpired, accountNonLocked, authorities);
		this.usuario = usuario;
	}

	public UsuarioWebBean getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioWebBean usuario) {
		this.usuario = usuario;
	}

}