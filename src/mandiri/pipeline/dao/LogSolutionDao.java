package mandiri.pipeline.dao;

import java.util.List;

import mandiri.pipeline.domain.CycleSales;
import mandiri.pipeline.domain.CycleSolution;
import mandiri.pipeline.domain.LogSales;
import mandiri.pipeline.domain.LogSolution;

public interface LogSolutionDao extends Dao<LogSolution> {
	public List<LogSolution> getLogSolution(CycleSolution entity);
}
