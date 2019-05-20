package pe.com.vendemas.weblogistica.seguridad;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import pe.com.vendemas.weblogistica.domain.Usuario;

public class SpringUser extends User {

	private static final long serialVersionUID = -3412180338469178220L;

	private Usuario usuario;
	
	public SpringUser(Usuario usuario, String user, String password,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(user, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}