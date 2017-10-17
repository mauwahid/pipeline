package mandiri.pipeline.dao;

import mandiri.pipeline.domain.AdminWeb;

public interface AdminWebDao extends Dao<AdminWeb>  {

	public AdminWeb getAdminWeb(String username, String password);
}
