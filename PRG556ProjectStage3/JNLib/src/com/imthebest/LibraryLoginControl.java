//Nam Nguyen
package com.imthebest;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imthebest.data.AccountOwnerDAOFactory;

import ca.on.senecac.prg556.common.Control;
import ca.on.senecac.prg556.common.StringHelper;
import ca.senecacollege.prg556.limara.bean.AccountOwner;


public class LibraryLoginControl implements Control {
	
		
	
	public String doLogic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			HttpSession session = request.getSession();
			UserSession usession = (UserSession)session.getAttribute("userSession");
			if (null == usession)
			{
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			if ("POST".equals(request.getMethod()) && StringHelper.isNotNullOrEmpty(userName) && StringHelper.isNotNullOrEmpty(password))
			{
				AccountOwner user = AccountOwnerDAOFactory.getAccountOwnerDAO().validateAccountOwner(userName, password);
				if (user != null)
				{
				session.setAttribute("userSession", new UserSession(user));
				return "R:" + request.getContextPath() + "/"; // redirect to context root folder
			
				}
				else
					request.setAttribute("unsuccessfulLogin", true);
			}
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
	public class UserSession
	{
		private AccountOwner user;
		public UserSession(AccountOwner user)
		{
			setUser(user);
		}
		public AccountOwner getUser()
		{
			return user;
		}
		private void setUser(AccountOwner user)
		{
			this.user = user;
		}
}
}
