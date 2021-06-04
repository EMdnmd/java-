package zhr.dao.role;

import zhr.dao.BaseDao;
import zhr.pojp.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class roledaoimpl implements  roleDao {


    public List<Role> getRole(Connection connection) throws Exception {
        PreparedStatement part=null;
        ResultSet rs=null;
        List<Role>  listrole=new ArrayList<Role>();
        if (connection !=null){
            String sql="select * from smbms_role;";
            Object [] objects ={};
            rs= BaseDao.execute(connection, sql, objects, rs, part);
            while (rs.next()){
                Role role =new Role();
                role.setId(rs.getInt("id"));
                role.setRoleCode(rs.getString("roleCode"));
                role.setRoleName(rs.getString("roleName"));
                role.setCreatedBy(rs.getInt("createdBy"));
                role.setCreationDate(rs.getDate("creationDate"));
                role.setModifyBy(rs.getInt("modifyby"));
                role.setModifyDate(rs.getDate("modifyDate"));
                System.out.println(role.getId() );
                listrole.add(role);
            }
            BaseDao.closeresource(null ,part,rs);
        }
        return listrole;
    }

}
