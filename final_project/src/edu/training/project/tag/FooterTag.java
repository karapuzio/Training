package edu.training.project.tag;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Dell on 28.01.2017.
 */
public class FooterTag extends TagSupport{
    private static final Logger LOGGER = LogManager.getLogger(FooterTag.class);
    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write("Final Project Epam 2016-2017, © Skidan Olya");
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Error in footer tag.", e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return  EVAL_PAGE;
    }
}
