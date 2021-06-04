package zhr.serverle.userlogin;

import zhr.pojp.User;
import zhr.select.user.userService;
import zhr.select.user.userservicimpl;
import zhr.util.constansts;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userCode=req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        userService  userService =new userservicimpl();
        User user = userService.login(userCode, userPassword);
        if (user!=null&&user.getUserPassword().equals(userPassword)){
//           查有此人
          req.getSession().setAttribute(constansts.USER_SESSION,user);
//        跳转到内部主页
          resp.sendRedirect("jsp/frame.jsp");
        }else {
            req.setAttribute("error","用户名或者密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
