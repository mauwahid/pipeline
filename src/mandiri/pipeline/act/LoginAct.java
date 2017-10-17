package mandiri.pipeline.act;

import mandiri.pipeline.dao.AdminWebDao;
import mandiri.pipeline.dao.HeadImplementatorDao;
import mandiri.pipeline.dao.HeadSalesDao;
import mandiri.pipeline.dao.HeadSolutionDao;
import mandiri.pipeline.dao.UserImplementatorDao;
import mandiri.pipeline.dao.UserSalesDao;
import mandiri.pipeline.dao.UserSolutionDao;
import mandiri.pipeline.domain.AdminWeb;
import mandiri.pipeline.domain.HeadImplementator;
import mandiri.pipeline.domain.HeadSales;
import mandiri.pipeline.domain.HeadSolution;
import mandiri.pipeline.domain.UserImplementator;
import mandiri.pipeline.domain.UserSales;
import mandiri.pipeline.domain.UserSolution;
import mandiri.pipeline.util.HibernateUtil;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.theme.Themes;

public class LoginAct extends GenericForwardComposer<Component>{

	
	Textbox tb_username;
	Textbox tb_password;
	Label tb_confirm;
	
	String username;
	String password;
	
	private UserSolutionDao userSolDao;
	private UserSalesDao userSalesDao;
	private UserImplementatorDao userImplementDao;
	private UserSolution userSol;
	private UserSales userSales;
	private UserImplementator userImpl;
	private HeadSolution headSol;
	private HeadSales headSales;
	private HeadImplementator headImp;
	private HeadSolutionDao headSolutionDao;
	private HeadImplementatorDao headImplementatorDao;
	private HeadSalesDao headSalesDao;
	private AdminWebDao adminWebDao;
	private AdminWeb adminWeb;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		Themes.setTheme(Executions.getCurrent(), "sapphire");
		
	}
	
	
	
	public void onLogin(){
		username = tb_username.getValue();
		password = tb_password.getValue();
		
		userImplementDao = HibernateUtil.getUserImplementatorDao();
		userSalesDao = HibernateUtil.getUserSalesDao();
		userSolDao = HibernateUtil.getUserSolutionDao();
		headImplementatorDao = HibernateUtil.getHeadImplementatorDao();
		headSalesDao = HibernateUtil.getSalesDao();
		headSolutionDao = HibernateUtil.getHeadSolutionDao();
		adminWebDao = HibernateUtil.getAdminWebDao();
		
		userImpl = userImplementDao.getUserImplementator(username, password);
		userSales = userSalesDao.getUserSales(username, password);
		userSol = userSolDao.getUserSolution(username, password);
		headSol = headSolutionDao.getHeadSolution(username, password);
		headImp = headImplementatorDao.getHeadImplementator(username, password);
		headSales = headSalesDao.getHeadSales(username, password);
		adminWeb = adminWebDao.getAdminWeb(username, password);
		
	
		System.out.println("password : "+password);
		
		if(userImpl!=null){
			session.setAttribute("pengguna", userImpl);
			Executions.sendRedirect("./implementator");
			
		}
		else if(adminWeb!=null){
			session.setAttribute("pengguna", adminWeb);
			Executions.sendRedirect("./admin");
			
		}
		else if(userSol!=null){
			session.setAttribute("pengguna", userSol);
			Executions.sendRedirect("./solution");
			
			
		}
		else if(userSales!=null){
			session.setAttribute("pengguna", userSales);
			Executions.sendRedirect("./sales");
			
		}
		else if(headSales!=null){
			session.setAttribute("pengguna", headSales);
			Executions.sendRedirect("./headsales");
			
		}
		else if(headSol!=null){
			session.setAttribute("pengguna", headSol);
			Executions.sendRedirect("./headsolution");
			
		}
		else if(headImp!=null){
			session.setAttribute("pengguna", headImp);
			Executions.sendRedirect("./headimpl");
			
		}else {
			showConfirm();
		}
			
	

	}

	private void showConfirm(){
		tb_password.setText("");
		tb_username.setText("");
		tb_confirm.setVisible(true);
		tb_username.setFocus(true);
	}

}
