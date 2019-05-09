package pe.com.vendemas.standalone.controller.seguridad.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.com.vendemas.standalone.controller.seguridad.bean.UsuarioWsBean;
import pe.com.vendemas.standalone.controller.seguridad.bean.UsuarioWsSpring;

@Service("autenticacionServiceImpl")
public class AutenticacionServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioWsBean objUsuario = new UsuarioWsBean();
		/*
		if (!username.equals("vierge_paxd190")) {
			throw new UsernameNotFoundException("Failed to login as ");
		}else {
			objUsuario.setUsuario("vierge_paxd190");
			objUsuario.setClave("123456v");
		}*/

		List<String> lstRoles = new ArrayList<String>();
		List<GrantedAuthority> authorities = buildUserAuthority(lstRoles);
		return buildUserForAuthentication(objUsuario, authorities);
	}
	
	private User buildUserForAuthentication(UsuarioWsBean objUsuario,List<GrantedAuthority> authorities) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		UsuarioWsSpring sp = new UsuarioWsSpring(objUsuario, objUsuario.getUsuario() , objUsuario.getClave(),enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return sp;
	}

	private List<GrantedAuthority> buildUserAuthority(List<String> roles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		for (String rol : roles) {
			setAuths.add(new SimpleGrantedAuthority(rol));
		}
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
		return result;
	}

}
