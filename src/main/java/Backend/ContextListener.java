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

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("\n\n\n\nFUNK\n\n\n\n");
        ServletContext context = servletContextEvent.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
        //System.out.println("\n\n\n\n" + fullPath + "\n\n\n\n" + log4jConfigFile + "\n\n\n\n");

        Properties props = new Properties();
        try {
            props.load(new FileInputStream("/WEB-INF/log4j.properties"));
        } catch (Exception e){}
        PropertyConfigurator.configure(props);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
