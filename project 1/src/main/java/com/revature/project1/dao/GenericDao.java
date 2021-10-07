package com.revature.project1.dao;

import java.util.List;
;

public interface GenericDao<T> {

List<T> getAll();
	
	
	
	T getById (int id);
	
	void update(T t);
	
	void insert (T t);
	
	void delete (T t);
}
	

