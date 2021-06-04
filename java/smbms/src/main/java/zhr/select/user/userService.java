package zhr.select.user;

import zhr.pojp.Role;
import zhr.pojp.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public  interface userService   {
//用户登录
        public User login(String userCode ,String password);
//根据用户id修改密码
        public  boolean  updatapwd(int id , String password);
//        public   User     adminuser(String use);

      public  int getUsercount(String usernamr ,int userRoit);

      public List<User> getgetlist(String user ,int userRole, int currentPageno ,int pagesize);


}
