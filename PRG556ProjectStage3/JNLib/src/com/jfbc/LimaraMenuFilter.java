//Jayme Cunha
package com.jfbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.imthebest.LibraryLoginControl.UserSession;
import com.imthebest.data.AccountOwnerDAOFactory;
import com.jfbc.data.MaterialReservationDAOFactory;

import ca.senecacollege.prg556.limara.bean.AccountOwner;
import ca.senecacollege.prg556.limara.bean.MaterialReservation;
import ca.senecacollege.prg556.limara.dao.MaterialReservationDAO;

/**
 * Servlet Filter implementation class LimaraMenuFilter
 */
public class LimaraMenuFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LimaraMenuFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		try{
			
			HttpServletRequest request = (HttpServletRequest)req;
			MaterialReservationDAO dao = MaterialReservationDAOFactory.getMaterialReservationDAO();
			int currentUser = ((UserSession)request.getSession().getAttribute("userSession")).getUser().getId();
			request.setAttribute("count", dao.getMaterialReservations(currentUser));
			chain.doFilter(req, resp);
		}
		catch(SQLException e)
		{
			throw new ServletException(e);
		}
				// pass the request along the filter chain
		chain.doFilter(req, resp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
