package mandiri.pipeline.dao;

import mandiri.pipeline.domain.HeadImplementator;

public interface HeadImplementatorDao extends Dao<HeadImplementator> {

	public HeadImplementator getHeadImplementator(String username, String password);
	
}
