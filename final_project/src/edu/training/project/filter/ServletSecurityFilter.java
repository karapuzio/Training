package edu.training.project.filter;

import edu.training.project.controller.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Protects the servlet from a direct appeal to jsp.
 *
 * @author Skidan Olya
 * @version 1.0
 * @see Filter
 */

@WebFilter(urlPatterns = {"/jsp/*"})
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
        resp.sendRedirect(req.getContextPath() + ConfigurationManager.getProperty("path.page.index"));
        // pass the request along the filter chain
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
