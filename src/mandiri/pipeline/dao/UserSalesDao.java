package mandiri.pipeline.dao;

import mandiri.pipeline.domain.UserSales;

public interface UserSalesDao extends Dao<UserSales> {

	public UserSales getUserSales(String username, String password);
		
}
