package pe.com.vendemas.weblogistica.dao;

import java.util.List;

public interface GenericDao<T> {
	
	public Integer create(T entity);
	public T getById(T entity);
	public void update(T entity);
	
	public List<T> getAll();
	public List<T> getList(T entity);
	
}
