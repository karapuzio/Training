package edu.training.project.filter;

import edu.training.project.controller.ConfigurationManager;
import edu.training.project.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Dell on 29.01.2017.
 */
@WebFilter(urlPatterns = {"/jsp/*"})
public class ServletUserFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("currentUser");
        if (user != null) {
            String page = user.getRole().equalsIgnoreCase("admin") ? ConfigurationManager.getProperty("path.page.admin") : ConfigurationManager.getProperty("path.page.home");
//            RequestDispatcher dispatcher = servletRequest.getServletContext().getRequestDispatcher(page);
//            dispatcher.forward(req, resp);
            resp.sendRedirect(req.getContextPath() + page);
            return;
        }
        // pass the request along the filter chain
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
