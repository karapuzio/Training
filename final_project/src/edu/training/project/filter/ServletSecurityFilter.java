package edu.training.project.filter;

import edu.training.project.controller.ConfigurationManager;
import edu.training.project.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Dell on 29.01.2017.
 */
@WebFilter(urlPatterns = {"/jsp/admin/*", "/jsp/song/*", "/jsp/user/*", "/jsp/additional/*", "/jsp/error.jsp"})
public class ServletSecurityFilter implements Filter{
    private static final Logger LOGGER = LogManager.getLogger(ServletSecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("currentUser");
        LOGGER.log(Level.DEBUG, "In security filter : " + user);
        if (user == null) {
//            RequestDispatcher dispatcher = servletRequest.getServletContext()
//                    .getRequestDispatcher(ConfigurationManager.getProperty("path.page.login"));
//            dispatcher.forward(req, resp);
            resp.sendRedirect(req.getContextPath() + ConfigurationManager.getProperty("path.page.login"));
            return;
        }
        // pass the request along the filter chain
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
