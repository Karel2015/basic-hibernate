package org.wx.eLearning.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDao<T> implements IBaseDao<T> {
	@Inject
	private SessionFactory sessionFactory;
	
	protected Session getSession(){
		return sessionFactory.openSession();
	}
	
	@Override
	public T add(T t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public T select(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
