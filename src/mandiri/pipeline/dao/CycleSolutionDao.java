package mandiri.pipeline.dao;

import java.util.List;

import mandiri.pipeline.domain.CycleSolution;
import mandiri.pipeline.domain.UserSolution;

public interface CycleSolutionDao extends Dao<CycleSolution> {

	public List<CycleSolution> getAsUserSolution(UserSolution user);
}
