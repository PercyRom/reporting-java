package pe.com.vendemas.weblogistica.dao.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class DaoDefinition<T> extends BeanPropertyRowMapper<T>{
	public DaoDefinition(Class<T> mappedClass){
		super(mappedClass);
	}
	
	public boolean findColumnaEnResultSet(String columna,ResultSet rs){
		try{
			return rs.findColumn(columna) > 0 ? true : false;
		}catch(SQLException ex){
			return false;
		}
	}
}