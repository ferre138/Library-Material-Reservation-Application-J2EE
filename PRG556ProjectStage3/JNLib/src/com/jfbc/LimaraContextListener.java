package com.jfbc;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import com.imthebest.data.DataSourceFactory;

/**
 * Application Lifecycle Listener implementation class LimaraContextListener
 *
 */
public class LimaraContextListener implements ServletContextListener {

    @Resource(name="LimaraDS")
	private DataSource ds;
    
    /**
     * Default constructor. 
     */
    public LimaraContextListener() {
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
		DataSourceFactory.setDataSource(ds);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
		DataSourceFactory.setDataSource(null);
    }
    
}
