//Jayme Cunha
package com.jfbc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.on.senecac.prg556.common.Control;

public class LogoutControl implements Control {

	public LogoutControl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public String doLogic(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		return "R:"+ request.getContextPath() + "/"; // Redirect to the context root path.
		// TODO Auto-generated method stub
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub

	}

}
