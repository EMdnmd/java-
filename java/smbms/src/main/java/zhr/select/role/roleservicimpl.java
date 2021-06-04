package zhr.select.role;

import org.junit.jupiter.api.Test;
import zhr.dao.BaseDao;
import zhr.dao.role.roleDao;
import zhr.dao.role.roledaoimpl;
import zhr.pojp.Role;

import java.sql.Connection;
import java.util.List;

public class roleservicimpl implements  roleService {
        public roleDao  roles;

        public  roleservicimpl(){
                     roles=  new roledaoimpl();


        }
    public List<Role> getrolelist() {
        Connection connection =null;
        List<Role> role=null;
        try {
            connection= BaseDao.getConnection();
            role = roles.getRole(connection);
        } catch (Exception e) {
            e.printStackTrace();
            BaseDao.closeresource(connection,null,null);
        }

        return  role;
    }



@Test
    public  void  ada(){
            roleservicimpl s= new roleservicimpl();
            List<Role> getrolelist = s.getrolelist();
            System.out.println(getrolelist);

}

}
