package zhr.select.user;

import org.junit.jupiter.api.Test;
import zhr.dao.BaseDao;
import zhr.dao.user.Userdaoimpl;
import zhr.dao.user.userDao;
import zhr.pojp.Role;
import zhr.pojp.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class userservicimpl implements userService {
    private userDao userDao;

    public userservicimpl() {
        userDao = new Userdaoimpl();

    }

    //处理用户登录
    public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;
        try {
            connection = BaseDao.getConnection();
            user = userDao.getloginuser(connection, userCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeresource(connection, null, null);
        }
        return user;

    }

    //    处理用户修改密码
    public boolean updatapwd(int id, String password) {
        Connection connection = null;
        connection = BaseDao.getConnection();
        boolean flag = false;
        try {
            if (userDao.updatapwd(connection, id, password) > 0) {
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeresource(connection, null, null);
        }
        return flag;
    }

    public int getUsercount(String usernamr, int userRoit) {
        Connection connection =null;
        int count=0;
        try {
            connection= BaseDao.getConnection();
            count= userDao.getusercount(connection, usernamr, userRoit);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            BaseDao.closeresource(connection,null,null);
        }
        return count;

    }

    @Override
    public List<User> getgetlist(String user, int userRole, int currentPageno, int pagesize) {
        Connection connection =null;
        List<User> userList=null;
        try {
            connection= BaseDao.getConnection();
            userList = userDao.getUserList(connection, user, userRole, currentPageno, pagesize);
        } catch (Exception e) {
            e.printStackTrace();
            BaseDao.closeresource(connection,null,null);

        }

        return userList  ;
    }




//    @Test
//    public void test() {
//        userservicimpl userservicimpl = new userservicimpl();
//         List admin = userservicimpl.getrolelist();
//        System.out.println(admin);
//    }

}
