package mandiri.pipeline.dao;

import mandiri.pipeline.domain.UserSolution;

public interface UserSolutionDao extends Dao<UserSolution>{

	public UserSolution getUserSolution(String username, String password);
}
