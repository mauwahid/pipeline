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

import mandiri.pipeline.dao.LogSolutionDao;
import mandiri.pipeline.dao.LogSolutionDao;
import mandiri.pipeline.domain.CycleSales;
import mandiri.pipeline.domain.CycleSolution;
import mandiri.pipeline.domain.LogSolution;

public class LogSolutionDaoImpl implements LogSolutionDao {

private SessionFactory factory;
	
	public LogSolutionDaoImpl(SessionFactory factory){
		this.factory = factory;
	}
	
	@Override
	public void insert(LogSolution entity) {
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
	public void update(LogSolution entity) {
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
	public void delete(LogSolution entity) {
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
	public LogSolution getById(Long id) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			LogSolution logSolution = (LogSolution) session.get(LogSolution.class,id);
			session.getTransaction().commit();
			return logSolution;
		}catch(HibernateException exception){
			session.getTransaction().rollback();
			throw exception;
		}finally{
			session.close();
		}
	}


	@Override
	public List<LogSolution> getAll() {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			List<LogSolution> list = session.createCriteria(LogSolution.class).addOrder(Order.desc("id")).list();
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
	public List<LogSolution> getAllByName(String name) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			Criteria criteria = session.createCriteria(LogSolution.class);
			Criterion criteria1 = Restrictions.ilike("nama", "%"+name+"%");
			criteria.add(criteria1).addOrder(Order.desc("id"));
		//	LogSolution pengguna = (Pengguna) criteria.list();
	
			List<LogSolution> list = criteria.list();
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
	public LogSolution getByName(String name) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			LogSolution anc;
			Criteria criteria = session.createCriteria(LogSolution.class);
			Criterion criteria1 = Restrictions.like("nama", name);
			criteria.add(criteria1).addOrder(Order.desc("id"));
			
	
			anc = (LogSolution) criteria.uniqueResult();
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
			Query query = session.createSQLQuery("select distinct nama from LogSolution as logSolution inner join distributionfinance on  logSolution.id = distributionfinance.logSolution_id");
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
	public List<LogSolution> getLogSolution(CycleSolution entity) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			Criteria crit = session.createCriteria(LogSolution.class);
			crit.add(Restrictions.eq("cycleSolution", entity)).addOrder(Order.desc("id"));
			List<LogSolution> list = crit.list();
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
