package zhr.dao.user;

import zhr.pojp.Role;
import zhr.pojp.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public  interface userDao {
//        得到登录的用户
        public User getloginuser(Connection connection,String userCode);
//修改当前用户密码
        public  int   updatapwd(Connection connection, int id , String password) throws SQLException;
//        查询用户总数
        public   int getusercount(Connection connection ,String username ,int  userRole)throws     SQLException;
//获取分页列表
        public List<User> getUserList(Connection connection, String user ,int userRole, int currentPageno ,int pagesize) throws Exception;


}
