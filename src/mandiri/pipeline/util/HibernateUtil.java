package mandiri.pipeline.util;

import mandiri.pipeline.dao.AdminWebDao;
import mandiri.pipeline.dao.AnchorDao;
import mandiri.pipeline.dao.BUCDao;
import mandiri.pipeline.dao.CategoryDao;
import mandiri.pipeline.dao.CustomerDao;
import mandiri.pipeline.dao.CycleImplementatorDao;
import mandiri.pipeline.dao.CycleSalesDao;
import mandiri.pipeline.dao.CycleSolutionDao;
import mandiri.pipeline.dao.GroupCustomerDao;
import mandiri.pipeline.dao.HeadImplementatorDao;
import mandiri.pipeline.dao.HeadSalesDao;
import mandiri.pipeline.dao.HeadSolutionDao;
import mandiri.pipeline.dao.LogSalesDao;
import mandiri.pipeline.dao.LogSolutionDao;
import mandiri.pipeline.dao.PipelineDao;
import mandiri.pipeline.dao.ProductDao;
import mandiri.pipeline.dao.UserImplementatorDao;
import mandiri.pipeline.dao.UserSalesDao;
import mandiri.pipeline.dao.UserSolutionDao;
import mandiri.pipeline.dao.impl.AdminWebDaoImpl;
import mandiri.pipeline.dao.impl.AnchorDaoImpl;
import mandiri.pipeline.dao.impl.BUCDaoImpl;
import mandiri.pipeline.dao.impl.CategoryDaoImpl;
import mandiri.pipeline.dao.impl.CustomerDaoImpl;
import mandiri.pipeline.dao.impl.CycleImplementatorDaoImpl;
import mandiri.pipeline.dao.impl.CycleSalesDaoImpl;
import mandiri.pipeline.dao.impl.CycleSolutionDaoImpl;
import mandiri.pipeline.dao.impl.GroupCustomerDaoImpl;
import mandiri.pipeline.dao.impl.HeadImplementatorDaoImpl;
import mandiri.pipeline.dao.impl.HeadSalesDaoImpl;
import mandiri.pipeline.dao.impl.HeadSolutionDaoImpl;
import mandiri.pipeline.dao.impl.LogSalesDaoImpl;
import mandiri.pipeline.dao.impl.LogSolutionDaoImpl;
import mandiri.pipeline.dao.impl.PipelineDaoImpl;
import mandiri.pipeline.dao.impl.ProductDaoImpl;
import mandiri.pipeline.dao.impl.UserImplementatorDaoImpl;
import mandiri.pipeline.dao.impl.UserSalesDaoImpl;
import mandiri.pipeline.dao.impl.UserSolutionDaoImpl;
import mandiri.pipeline.domain.CycleImplementator;
import mandiri.pipeline.domain.GroupCustomer;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	
	//Dao Object
	private static final AnchorDao anchorDao;
	private static final PipelineDao pipelineDao;
	private static final CategoryDao categoryDao;
	private static final CycleImplementatorDao cycleImplementatorDao;
	private static final CycleSalesDao cycleSalesDao;
	private static final CycleSolutionDao cycleSolutionDao;
	private static final GroupCustomerDao groupCustomerDao;
	private static final HeadImplementatorDao headImplementatorDao;
	private static final HeadSalesDao headSalesDao;
	private static final HeadSolutionDao headSolutionDao;
	private static final UserImplementatorDao userImplementatorDao;
	private static final UserSalesDao userSalesDao;
	private static final UserSolutionDao userSolutionDao;
	private static final CustomerDao customerDao;
	private static final ProductDao productDao;
	private static final BUCDao bucDao;
	private static final LogSalesDao logSalesDao;
	private static final AdminWebDao adminWebDao;
	private static final LogSolutionDao logSolutionDao;
	
	
	
	static{
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		anchorDao = new AnchorDaoImpl(sessionFactory);
		customerDao = new CustomerDaoImpl(sessionFactory);
		pipelineDao = new PipelineDaoImpl(sessionFactory);
		categoryDao = new CategoryDaoImpl(sessionFactory);
		cycleImplementatorDao = new CycleImplementatorDaoImpl(sessionFactory);
		cycleSalesDao = new CycleSalesDaoImpl(sessionFactory);
		cycleSolutionDao = new CycleSolutionDaoImpl(sessionFactory);
		groupCustomerDao = new GroupCustomerDaoImpl(sessionFactory);
		headImplementatorDao = new HeadImplementatorDaoImpl(sessionFactory);
		headSalesDao = new HeadSalesDaoImpl(sessionFactory);
		headSolutionDao = new HeadSolutionDaoImpl(sessionFactory);
		userImplementatorDao = new UserImplementatorDaoImpl(sessionFactory);
		userSalesDao = new UserSalesDaoImpl(sessionFactory);
		userSolutionDao = new UserSolutionDaoImpl(sessionFactory);
		productDao = new ProductDaoImpl(sessionFactory);
		bucDao = new BUCDaoImpl(sessionFactory);
		logSalesDao = new LogSalesDaoImpl(sessionFactory);
		adminWebDao = new AdminWebDaoImpl(sessionFactory);
		logSolutionDao = new LogSolutionDaoImpl(sessionFactory);
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static AnchorDao getAnchorDao(){
		return anchorDao;
	}
	
	
	public static PipelineDao getPipelineDao(){
		return pipelineDao;
	}
	
	public static CategoryDao getCategoryDao(){
		return categoryDao;
	}

	public static CycleImplementatorDao geCycleImplementatorDao(){
		return cycleImplementatorDao;
	}

	public static CustomerDao getCustomerDao(){
		return customerDao;
	}

	public static CycleSalesDao getCycleSalesDao(){
		return cycleSalesDao;
	}
	
	public static CycleSolutionDao getCycleSolutionDao(){
		return cycleSolutionDao;
	}
	
	public static GroupCustomerDao getGroupCustomerDao(){
		return groupCustomerDao;
	}
	
	public static HeadImplementatorDao getHeadImplementatorDao(){
		return headImplementatorDao;
	}

	public static HeadSalesDao getSalesDao(){
		return headSalesDao;
	}

	public static HeadSolutionDao getSolutionDao(){
		return headSolutionDao;
	}
	
	public static UserImplementatorDao getUserImplementatorDao(){
		return userImplementatorDao;
	}
	
	public static UserSalesDao getUserSalesDao(){
		return userSalesDao;
	}
	
	public static UserSolutionDao getUserSolutionDao(){
		return userSolutionDao;
	}

	public static CycleImplementatorDao getCycleImplementatorDao(){
		return cycleImplementatorDao;
	}

	public static ProductDao getProductDao(){
		return productDao;
	}


	public static BUCDao getBUCDao(){
		return bucDao;
	}

	public static HeadSolutionDao getHeadSolutionDao(){
		return headSolutionDao;
	}

	public static HeadSalesDao getHeadSalesDao(){
		return headSalesDao;
	}

	public static LogSalesDao getLogSalesDao(){
		return logSalesDao;
	}
	
	public static LogSolutionDao getLogSolutionDao(){
		return logSolutionDao;
	}
	
	
	public static AdminWebDao getAdminWebDao(){
		return adminWebDao;
	}

}
