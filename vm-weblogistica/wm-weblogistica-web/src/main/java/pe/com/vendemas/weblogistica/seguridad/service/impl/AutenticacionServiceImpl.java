package pe.com.vendemas.weblogistica.seguridad.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.com.vendemas.weblogistica.dao.OpcionDao;
import pe.com.vendemas.weblogistica.dao.UsuarioDao;
import pe.com.vendemas.weblogistica.domain.Opcion;
import pe.com.vendemas.weblogistica.domain.Usuario;
import pe.com.vendemas.weblogistica.seguridad.bean.UsuarioWebBean;
import pe.com.vendemas.weblogistica.seguridad.bean.UsuarioWsSpring;

@Service("autenticacionServiceImpl")
public class AutenticacionServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier("usuarioDao")
	private UsuarioDao usuarioDao;
	
	@Autowired
	@Qualifier("opcionDao")
	private OpcionDao opcionDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UsuarioWebBean objUsuario = new UsuarioWebBean();
		try {
			
			Usuario objUsuarioBD = usuarioDao.getUsuarioByCodigo(username);
			
			if(objUsuarioBD!=null) {
				
				List<Opcion> lstOpciones = opcionDao.getOpcionesByUsuario(objUsuarioBD.getIdUsuario());
				
				objUsuario.setUsuario(objUsuarioBD.getCodigoUsuario());
				objUsuario.setClave(objUsuarioBD.getClave());
				objUsuario.setLstOpciones(lstOpciones);
			}

		} catch (Exception e) {
			e.printStackTrace();
			objUsuario = new UsuarioWebBean(); 
		}

		if (objUsuario == null || objUsuario.getUsuario() == null) {
			throw new UsernameNotFoundException("Failed to login as ");
		}

		List<String> lstRoles = new ArrayList<String>();
		List<GrantedAuthority> authorities = buildUserAuthority(lstRoles);
		return buildUserForAuthentication(objUsuario, authorities);

	}
	
	private User buildUserForAuthentication(UsuarioWebBean objUsuario,List<GrantedAuthority> authorities) {
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