package mandiri.pipeline.dao;

import mandiri.pipeline.domain.HeadSales;

public interface HeadSalesDao extends Dao<HeadSales> {

	public HeadSales getHeadSales(String username, String password);
		
}
