package mandiri.pipeline.dao;

import mandiri.pipeline.domain.HeadSolution;

public interface HeadSolutionDao extends Dao<HeadSolution>  {

	public HeadSolution getHeadSolution(String username, String password);
}
