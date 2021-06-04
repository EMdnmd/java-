package zhr.dao.role;

import zhr.pojp.Role;

import java.sql.Connection;
import java.util.List;

public interface roleDao {
    //获取角色列表
    public List<Role> getRole(Connection connection) throws Exception;


}
