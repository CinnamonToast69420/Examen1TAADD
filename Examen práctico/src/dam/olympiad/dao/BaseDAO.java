package dam.olympiad.dao;

import java.util.List;

public interface BaseDAO<T, K> {

	void insert(T t) throws DAOException;
	
	void update(T t) throws DAOException;
	
	void delete(K k) throws DAOException;
	
	List<T> getAll() throws DAOException;
	
	T getById(K k) throws DAOException;


}
