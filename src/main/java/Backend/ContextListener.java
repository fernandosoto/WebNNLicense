package Backend;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by Isak on 2015-05-20.
 */
@WebListener("application context listener")
public class ContextListener implements ServletContextListener {
    public static Logger log = Logger.getRootLogger();

    /**Initializes the log4j with the log4j.properties file.
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;

        Properties props = new Properties();
        try {
            props.load(new FileInputStream("/WEB-INF/log4j.properties"));
        } catch (Exception e){}
        PropertyConfigurator.configure(props);

    }

    /**
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
