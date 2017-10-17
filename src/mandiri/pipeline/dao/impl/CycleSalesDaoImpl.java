package mandiri.pipeline.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import mandiri.pipeline.dao.CycleSalesDao;
import mandiri.pipeline.domain.CycleSales;
import mandiri.pipeline.domain.CycleSolution;
import mandiri.pipeline.domain.UserSales;
import mandiri.pipeline.domain.UserSolution;

public class CycleSalesDaoImpl implements CycleSalesDao {

private SessionFactory factory;
	
	public CycleSalesDaoImpl(SessionFactory factory){
		this.factory = factory;
	}
	
	@Override
	public void insert(CycleSales entity) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
		}catch(HibernateException exception){
			session.getTransaction().rollback();
			throw exception;
		}finally{
			session.close();
		}
	}

	@Override
	public void update(CycleSales entity) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			session.update(entity);
			session.getTransaction().commit();
		}catch(HibernateException exception){
			session.getTransaction().rollback();
			throw exception;
		}finally{
			session.close();
		}
	}

	@Override
	public void delete(CycleSales entity) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			session.delete(entity);
			session.getTransaction().commit();
		}catch(HibernateException exception){
			session.getTransaction().rollback();
			throw exception;
		}finally{
			session.close();
		}
	}

	@Override
	public CycleSales getById(Long id) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			CycleSales CycleSales = (CycleSales) session.get(CycleSales.class,id);
			session.getTransaction().commit();
			return CycleSales;
		}catch(HibernateException exception){
			session.getTransaction().rollback();
			throw exception;
		}finally{
			session.close();
		}
	}


	@Override
	public List<CycleSales> getAll() {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			List<CycleSales> list = session.createCriteria(CycleSales.class).addOrder(Order.desc("id")).list();
			session.getTransaction().commit();
			return list;
		}catch(HibernateException exception){
			session.getTransaction().rollback();
			throw exception;
		}finally{
			session.close();
		}
	}

	@Override
	public List<CycleSales> getAllByName(String name) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CycleSales.class);
			Criterion criteria1 = Restrictions.ilike("nama", "%"+name+"%");
			criteria.add(criteria1).addOrder(Order.desc("id"));
		//	CycleSales pengguna = (Pengguna) criteria.list();
	
			List<CycleSales> list = criteria.list();
			session.getTransaction().commit();
			return list;
		}catch(HibernateException exception){
			session.getTransaction().rollback();
			throw exception;
		}finally{
			session.close();
		}	
		
	}


	@Override
	public CycleSales getByName(String name) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			CycleSales anc;
			Criteria criteria = session.createCriteria(CycleSales.class);
			Criterion criteria1 = Restrictions.like("nama", name);
			criteria.add(criteria1).addOrder(Order.desc("id"));
			
	
			anc = (CycleSales) criteria.uniqueResult();
			session.getTransaction().commit();
			return anc;
		}catch(HibernateException exception){
			session.getTransaction().rollback();
			throw exception;
		}finally{
			session.close();
		}	
	}

	@Override
	public List<String> getAllString() {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			Query query = session.createSQLQuery("select distinct nama from CycleSales as CycleSales inner join distributionfinance on  CycleSales.id = distributionfinance.CycleSales_id");
			List<String> list = query.list();
			
			session.getTransaction().commit();
			return list;
		}catch(HibernateException exception){
			session.getTransaction().rollback();
			throw exception;
		}finally{
			session.close();
		}
	
	}
	
	@Override
	public List<CycleSales> getAsUserSales(UserSales user) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CycleSales.class);
			List<CycleSales> list = criteria.add(Restrictions.eq("userSales", user)).list();
			session.getTransaction().commit();
			return list;
		}catch(HibernateException exception){
			session.getTransaction().rollback();
			throw exception;
		}finally{
			session.close();
		}
	}



}
