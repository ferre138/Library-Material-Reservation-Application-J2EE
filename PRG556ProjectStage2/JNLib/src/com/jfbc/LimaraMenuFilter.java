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

import com.imthebest.data.AccountOwnerDAOFactory;
import com.jfbc.data.MaterialReservationDAOFactory;

import ca.senecacollege.prg556.limara.bean.AccountOwner;
import ca.senecacollege.prg556.limara.bean.MaterialReservation;

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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		try{

			AccountOwner owner = AccountOwnerDAOFactory.getAccountOwnerDAO().getAccountOwner(123);
			
			
			List<MaterialReservation> list = MaterialReservationDAOFactory.getMaterialReservationDAO().getMaterialReservations(owner.getId());
			
			request.setAttribute("count", list.size());
			request.setAttribute("name", owner);
		}
		catch(SQLException e)
		{
			throw new ServletException(e);
		}
				// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
