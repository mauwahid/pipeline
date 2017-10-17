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

import mandiri.pipeline.dao.UserSolutionDao;
import mandiri.pipeline.domain.UserSolution;

public class UserSolutionDaoImpl implements UserSolutionDao {

private SessionFactory factory;
	
	public UserSolutionDaoImpl(SessionFactory factory){
		this.factory = factory;
	}
	
	@Override
	public void insert(UserSolution entity) {
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
	public void update(UserSolution entity) {
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
	public void delete(UserSolution entity) {
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
	public UserSolution getById(Long id) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			UserSolution UserSolution = (UserSolution) session.get(UserSolution.class,id);
			session.getTransaction().commit();
			return UserSolution;
		}catch(HibernateException exception){
			session.getTransaction().rollback();
			throw exception;
		}finally{
			session.close();
		}
	}


	@Override
	public List<UserSolution> getAll() {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			List<UserSolution> list = session.createCriteria(UserSolution.class).addOrder(Order.desc("id")).list();
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
	public List<UserSolution> getAllByName(String name) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			Criteria criteria = session.createCriteria(UserSolution.class);
			Criterion criteria1 = Restrictions.ilike("nama", "%"+name+"%");
			criteria.add(criteria1).addOrder(Order.desc("id"));
		//	UserSolution pengguna = (Pengguna) criteria.list();
	
			List<UserSolution> list = criteria.list();
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
	public UserSolution getByName(String name) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			UserSolution anc;
			Criteria criteria = session.createCriteria(UserSolution.class);
			Criterion criteria1 = Restrictions.like("nama", name);
			criteria.add(criteria1).addOrder(Order.desc("id"));
			
	
			anc = (UserSolution) criteria.uniqueResult();
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
			Query query = session.createSQLQuery("select distinct nama from UserSolution as UserSolution inner join distributionfinance on  UserSolution.id = distributionfinance.UserSolution_id");
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
	public UserSolution getUserSolution(String username, String password) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			Criteria criteria = session.createCriteria(UserSolution.class);
			Criterion criteria1 = Restrictions.ilike("username", username);
			Criterion criteria2 = Restrictions.ilike("password", password);
			Criterion and = Restrictions.and(criteria1,criteria2);
			criteria.add(and);
			UserSolution pengguna = (UserSolution) criteria.uniqueResult();
			session.getTransaction().commit();
			return pengguna;
		}catch(HibernateException exception){
			session.getTransaction().rollback();
			throw exception;
		}finally{
			session.close();
		}
	}


}
