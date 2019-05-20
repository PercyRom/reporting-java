package pe.com.vendemas.weblogistica.dao.util;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractDao{
	
	@Autowired
	@Qualifier("jdbcTemplate")
	protected JdbcTemplate jdbcTemplate;
		
	@Autowired
	protected  DataSource dataSource;
	
	
}
