package mandiri.pipeline.dao;

import java.util.List;

import mandiri.pipeline.domain.CycleSales;
import mandiri.pipeline.domain.CycleSolution;
import mandiri.pipeline.domain.UserSales;
import mandiri.pipeline.domain.UserSolution;

public interface CycleSalesDao extends Dao<CycleSales> {

	public List<CycleSales> getAsUserSales(UserSales user);

}
