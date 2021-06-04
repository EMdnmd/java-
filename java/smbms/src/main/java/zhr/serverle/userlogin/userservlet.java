package zhr.serverle.userlogin;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.JsonArray;
import zhr.pojp.User;
import zhr.select.user.userService;
import zhr.select.user.userservicimpl;
import zhr.util.constansts;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class userservlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        System.out.println("进------");
        if (method.equals("savepwd") && method != null) {
            this.updatepwd(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        System.out.println(method);
        if (method.equals("pwdmodify") && method != null) {
            this.pwdmodify(req, resp);
        }else if (method.equals("query") && method !=null){
            this.adminuser(req,resp);
        }

    }


    //密码修改
    private void updatepwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object attribute = req.getSession().getAttribute(constansts.USER_SESSION);
        String newpassword = req.getParameter("newpassword");

        boolean flag = false;
        if (attribute != null && !StringUtils.isNullOrEmpty(newpassword)) {
            userService userService = new userservicimpl();
            flag = userService.updatapwd(((User) attribute).getId(), newpassword);
            if (flag) {
                req.setAttribute("message", "修改密码成功，请退出使用新密码登录");
                req.getSession().removeAttribute(constansts.USER_SESSION);
            } else {
                req.setAttribute("message", "密码修改失败");
            }
        } else {
            req.setAttribute("message", "新密码有问题");
        }
        req.getRequestDispatcher("pwdmodify.jsp").forward(req, resp);
    }

    //        验证旧密码
    private void pwdmodify(HttpServletRequest req, HttpServletResponse resp) {
        Object attribute = req.getSession().getAttribute(constansts.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");
//       万能map
        Map<String, String> resultMap = new HashMap<String, String>();
        if (attribute==null){
//            如果session过期
            resultMap.put("result","sessionerror");
        }else if(StringUtils.isNullOrEmpty(oldpassword)){
//            判断旧密码是否为空
           resultMap.put("result","error");
        }else {
            String userPassword = ((User) attribute).getUserPassword();//获取用户老密码
              if(oldpassword.equals(userPassword)){
                  resultMap.put("result","true");
              }else {
                  resultMap.put("result","false");
              }
        }
//        设置返回json值
        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
//            将map转换成json并且输出出去
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private  void  adminuser(HttpServletRequest req,HttpServletResponse resp) {
        System.out.println("进来了获取用户了");
        try {
            Object user = req.getSession().getAttribute(constansts.USER_SESSION);
            resp.sendRedirect("userlist.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
