package nico.wata.java.models.DAO;

import java.util.List;

import nico.wata.java.exceptions.DAOSQLException;
public interface DAO <T,K>{
	public void insert(T x) throws DAOSQLException;
	public void delete(K k)throws DAOSQLException;
	public T getOne(K k)throws DAOSQLException;
	public List<T> getAll()throws DAOSQLException;
	public void update(T t)throws DAOSQLException;

}
