//Nam Nguyen
package com.imthebest;

import java.sql.SQLException;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import com.jfbc.data.MaterialReservationDAOFactory;

/**
 * Application Lifecycle Listener implementation class LimaraRequestListener
 *
 */
public class LimaraRequestListener implements ServletRequestListener {

    /**
     * Default constructor. 
     */
    public LimaraRequestListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent sre) {
        // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent sre) {
        // TODO Auto-generated method stub
    	try 
    	{
    		MaterialReservationDAOFactory.getMaterialReservationDAO().deleteExpiredMaterialReservations();
    	}
    	catch (SQLException sqle) 
    	{
    		// do nothing
    	}
    }
	
}
