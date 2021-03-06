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
				
				if ((fname.length()>0 && fname != null)&&(lname.length()>0 && fname != null)&&(username.length()>0 && fname != null)&&(pass.length()>0 && pass != null))
				{
					request.setAttribute("fname", StringHelper.xmlEscape(fname));
					request.setAttribute("lname", StringHelper.xmlEscape(lname));
					request.setAttribute("username", StringHelper.xmlEscape(username));
					request.setAttribute("pass", StringHelper.xmlEscape(pass));
				
				if ((AccountOwnerDAOFactory.getAccountOwnerDAO().validateAccountOwner(username, pass)) != null && pass.equals(com_pass))
				{
					AccountOwner account;
				return "R:" + request.getContextPath() + "/"; // redirect to context root folder
				}
				}
			}
			return null;
		}
		catch (SQLException sqle)
		{
			throw new ServletException(sqle);
		}
	}
}
