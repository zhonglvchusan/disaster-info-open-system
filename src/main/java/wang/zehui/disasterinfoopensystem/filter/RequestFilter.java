package wang.zehui.disasterinfoopensystem.filter;

import wang.zehui.disasterinfoopensystem.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: wangxiaobai
 * @date: 2022/3/17 10:14
 * @Description: 对页面进行拦截，不登陆无法访问某些网页
*/
@WebFilter(urlPatterns = {"/*"})
public class RequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //强制类型转换，使用子类实现的一些方法
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //获取请求地址
        //getRequestURI()方法获取的为http://localhost:8080/后的地址;getRequestURL()方法获取为所有地址
        String url = httpServletRequest.getRequestURI();
        int index = url.lastIndexOf("/");
        String endwith = url.substring(index+1);
        User sessionUser = (User) httpServletRequest.getSession().getAttribute("user");

        switch (endwith){
            case "model":
            case "commit":
            case "relation":
                if(sessionUser == null){
                    httpServletResponse.sendRedirect("/login");
                }
                else {
                    filterChain.doFilter(servletRequest,servletResponse);
                }
                break;
            case "getInfo":
            case "update":
                if(sessionUser == null){
                    httpServletResponse.sendRedirect("/Error");
                }
                else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
                break;
            case "rescueCommit":
            case "rescueNeed":
                if(sessionUser == null){
                    httpServletResponse.sendRedirect("/login");
                }
                else if(!sessionUser.getRight().equals("organization")){
                    httpServletResponse.sendRedirect("/Error");
                }
                else {
                    filterChain.doFilter(servletRequest,servletResponse);
                }
                break;
            case "release":
            case "selectMaterialNeedData":
            case "selectRescueNeedData":
            case "apply":
            case "selectUserData":
            case "admin":
            case "adminUpdate":
                if(sessionUser == null){
                    httpServletResponse.sendRedirect("/login");
                }
                else if(!sessionUser.getRight().equals("admin")){
                    httpServletResponse.sendRedirect("/Error");
                }
                else {
                    filterChain.doFilter(servletRequest,servletResponse);
                }
                break;
            default:
                filterChain.doFilter(servletRequest,servletResponse);
        }

        //执行下一个filter
//        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
