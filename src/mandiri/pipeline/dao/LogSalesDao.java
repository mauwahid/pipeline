package mandiri.pipeline.dao;

import java.util.List;

import mandiri.pipeline.domain.CycleSales;
import mandiri.pipeline.domain.LogSales;

public interface LogSalesDao extends Dao<LogSales> {
	public List<LogSales> getLogSales(CycleSales entity);
}
