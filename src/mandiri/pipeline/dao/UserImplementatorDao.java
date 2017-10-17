package mandiri.pipeline.dao;

import mandiri.pipeline.domain.UserImplementator;

public interface UserImplementatorDao extends Dao<UserImplementator> {

	public UserImplementator getUserImplementator(String username, String password);
}
