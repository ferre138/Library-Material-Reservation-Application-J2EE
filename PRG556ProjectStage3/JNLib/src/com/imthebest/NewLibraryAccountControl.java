//Nam Nguyen
package com.imthebest;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imthebest.data.AccountOwnerDAOFactory;


import ca.on.senecac.prg556.common.Control;
import ca.on.senecac.prg556.common.StringHelper;
import ca.senecacollege.prg556.limara.bean.AccountOwner;

public class NewLibraryAccountControl implements Control {



	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String doLogic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try
		{
			String fname = request.getParameter("first_name");
			String lname = request.getParameter("last_name");
			String username = request.getParameter("user_name");
			String pass = request.getParameter("password");
			String com_pass = request.getParameter("comfirm_password");
			if ("POST".equals(request.getMethod()) && StringHelper.isNotNullOrEmpty(request.getParameter("submit")))
			{
				
				
				if ((fname != null && fname.length()>0))
				{
					request.setAttribute("fname", StringHelper.xmlEscape(fname));
				}
				else 
					request.setAttribute("firstnamenotvalid", true);
				if ((lname.length()>0 && fname != null))
				{
					request.setAttribute("lname", StringHelper.xmlEscape(lname));
				}
				else 
					request.setAttribute("lastnamenotvalid", true);
				if ((username.length()>0 && fname != null))
				{
					request.setAttribute("username", StringHelper.xmlEscape(username));
				}
				else 
					request.setAttribute("usernamenotvalid", true);
				if ((pass.length()>0 && pass != null))
				{
					request.setAttribute("pass", StringHelper.xmlEscape(pass));
				}
				else 
					request.setAttribute("passwordnotvalid", true);
					if (pass.equals(com_pass))
					{
						AccountOwner account = AccountOwnerDAOFactory.getAccountOwnerDAO().createLibraryAccount(fname, lname, username, pass);
						 return "R:" + request.getContextPath() + "/";
					}
					else
						request.setAttribute("passworddonotmatch", true);
				}
			
			return null;
		}
		catch (SQLException sqle)
		{
			throw new ServletException(sqle);
		}
	}
}
