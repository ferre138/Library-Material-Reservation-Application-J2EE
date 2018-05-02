//Jayme Cunha
package com.jfbc;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imthebest.BadRequestException;
import com.jfbc.data.MaterialReservationDAOFactory;

import ca.on.senecac.prg556.common.Control;
import ca.senecacollege.prg556.limara.bean.MaterialReservation;

public class CancelReservationConfirmationControl implements Control {

	public CancelReservationConfirmationControl() {
	}

	@Override
	public void destroy() {
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
				MaterialReservation mat;
				mat = MaterialReservationDAOFactory.getMaterialReservationDAO().getMaterialReservation(materialID);

				if(mat == null)
					throw new BadRequestException();
				else
					if ("POST".equals(request.getMethod())){
						String button1 = request.getParameter("cancelshow");
						if(button1 != null)
						{
							request.setAttribute("material", mat);
							return null;
						}
						String button2 = request.getParameter("cancel");
						String button3 = request.getParameter("abort");
						if(button2 != null)
							MaterialReservationDAOFactory.getMaterialReservationDAO().cancelMaterialReservation(materialID);
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
