package zhr.dao.user;

import com.mysql.cj.util.StringUtils;
import zhr.dao.BaseDao;
import zhr.pojp.Role;
import zhr.pojp.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Userdaoimpl implements userDao {
    //    得到登录用户
    public User getloginuser(Connection connection, String userCode) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        if (connection != null) {
            String sql = "select * from  smbms_user where userCode=?";
            Object[] params = {userCode};
            try {
                resultSet = BaseDao.execute(connection, sql, params, resultSet, preparedStatement);
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUserCode(resultSet.getString("userCode"));
                    user.setUserName(resultSet.getString("userName"));
                    user.setUserPassword(resultSet.getString("userPassword"));
                    user.setGender(resultSet.getInt("gender"));
                    user.setBirthday(resultSet.getDate("birthday"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setAddress(resultSet.getString("address"));
                    user.setUserRole(resultSet.getInt("userRole"));
                    user.setCreatedBy(resultSet.getInt("createdBy"));
                    user.setCreationDate(resultSet.getTimestamp("creationDate"));
                    user.setModifyBy(resultSet.getInt("modifyBy"));
                    user.setModifyDate(resultSet.getTimestamp("modifyDate"));

                }
                BaseDao.closeresource(connection, preparedStatement, resultSet);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return user;
    }

    //修改密码
    public int updatapwd(Connection connection, int id, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        int execute = 0;
        if (connection != null) {
            String sql = "update smbms_user set  userPassword =? where id=?";
            Object[] params = {password, id};
            execute = BaseDao.execute(connection, sql, params, preparedStatement);
            BaseDao.closeresource(null, preparedStatement, null);
        }

        return execute;
    }

    //根据用户名或者角色查询角色总数
    public int getusercount(Connection connection, String username, int userRole) throws SQLException {
        PreparedStatement patm = null;
        ResultSet rs = null;
        int count=0;
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_user u ,smbms_role r where  u.userRole=r.id ");
            ArrayList<Object> list = new ArrayList<Object>();
            if (!StringUtils.isNullOrEmpty(username)) {
                sql.append("and u.userName like ?");
               list.add("%"+username+"%");
            }
            if (userRole > 0) {
                sql.append(" and  u.userRole = ?");
                list.add(userRole);
            }

            Object[] objects = list.toArray();
            System.out.println("user-->" +sql.toString());
            rs = BaseDao.execute(connection, sql.toString(), objects, rs, patm);
            if (rs.next()){
                 count= rs.getInt("count");

            }
            BaseDao.closeresource(null ,patm,rs);

        }

        return count;

    }

    public List<User> getUserList(Connection connection, String users, int userRole, int currentPageno, int pagesize) throws Exception {
          PreparedStatement pram=null;
         ResultSet resultSet=null;
         List <User> userlist = new ArrayList <User>();
         if (connection !=null){
             StringBuffer sql=new StringBuffer();
             sql.append("select   u.* ,r.roleName from  smbms_user  u ,  smbms_role r   where  u.userRole=r.id ");
             List <Object> list = new ArrayList <Object>();
             if(!StringUtils.isNullOrEmpty(users)){
                    sql.append("and u.userName like ? ");
                    list.add("%"+users+"%");
             }
            if (userRole>0){
                sql.append("and u.userRole = ? ");
                list.add(userRole);

            }
            sql.append("order by  creationDate DESC  LIMIT  ?,?");
             System.out.println("user-->"+sql.toString());
            currentPageno =(currentPageno-1)*pagesize;
            list.add(currentPageno);
            list.add(pagesize);
            Object[] objects = list.toArray();
             resultSet = BaseDao.execute(connection, sql.toString(), objects, resultSet, pram);
             while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setUserRole(resultSet.getInt("userRole"));
                user.setCreatedBy(resultSet.getInt("createdBy"));
                user.setCreationDate(resultSet.getTimestamp("creationDate"));
                user.setModifyBy(resultSet.getInt("modifyBy"));
                user.setModifyDate(resultSet.getTimestamp("modifyDate"));
                userlist.add(user);

            }
            BaseDao.closeresource(null,pram,resultSet);
         }
         return userlist;
    }


    public List<Role> getRole(Connection connection) throws Exception {
         PreparedStatement  part=null;
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
