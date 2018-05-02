//Jayme Cunha
package com.jfbc;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imthebest.BadRequestException;
import com.imthebest.data.MaterialDAOFactory;

import ca.on.senecac.prg556.common.Control;
import ca.senecacollege.prg556.limara.bean.Material;

public class CancelReservationConfirmationControl implements Control {

	public CancelReservationConfirmationControl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public String doLogic(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			String materialID = request.getParameter("materialID");
			if(materialID == null || materialID.isEmpty())
			{
				throw new BadRequestException();
			}
			else
			{
				Material mat;
				mat = MaterialDAOFactory.getMaterialDAO().getMaterial(materialID);

				if(mat == null)
					throw new BadRequestException();
				else
					if ("POST".equals(request.getMethod())){
						String button1 = request.getParameter("cancelshow");
						if(button1 != null)
						{
							request.setAttribute("material", mat);

						}
						String button2 = request.getParameter("cancel");
						String button3 = request.getParameter("abort");
						if(button2 != null || button3 != null)
						{
							return "R:"+ request.getContextPath() + "/"; // Redirect to the context root path.
						}
					}
			}
		} catch(SQLException e){
			throw new ServletException(e);
		}
		return "R:"+ request.getContextPath() + "/"; // Redirect to the context root path.
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub

	}

}
