package pe.com.vendemas.standalone.controller.seguridad.bean;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UsuarioWsSpring extends User {

	private static final long serialVersionUID = -3412180338469178220L;

	private UsuarioWsBean usuario;
	
	public UsuarioWsSpring(UsuarioWsBean usuario, String user, String password,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(user, password, enabled, accountNonExpired,credentialsNonExpired, accountNonLocked, authorities);
		this.usuario = usuario;
	}

	public UsuarioWsBean getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioWsBean usuario) {
		this.usuario = usuario;
	}

}