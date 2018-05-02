//Nam Nguyen
package com.imthebest;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imthebest.data.AccountOwnerDAOFactory;

import ca.on.senecac.prg556.common.Control;


public class LibraryLoginControl implements Control {
	
		
	
	public String doLogic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			if ("POST".equals(request.getMethod()) && AccountOwnerDAOFactory.getAccountOwnerDAO().validateAccountOwner(userName, password) != null)
			{
				return "R:" + request.getContextPath() + "/"; // redirect to context root folder
			}
			return null;
		}
		catch (SQLException sqle)
		{
			throw new ServletException(sqle);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
