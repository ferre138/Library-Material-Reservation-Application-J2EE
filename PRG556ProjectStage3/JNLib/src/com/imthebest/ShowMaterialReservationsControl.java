//Nam Nguyen
package com.imthebest;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.jfbc.data.MaterialReservationDAOFactory;


import java.sql.SQLException;
import java.util.List;
import ca.on.senecac.prg556.common.Control;

import ca.senecacollege.prg556.limara.bean.MaterialReservation;

public class ShowMaterialReservationsControl implements Control {
	
	public String doLogic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		try
		{
			int id = Integer.parseInt(request.getParameter("material_id"));
			List<MaterialReservation> materialreservations; //=  new ArrayList<MaterialReservation>();
					
			materialreservations = MaterialReservationDAOFactory.getMaterialReservationDAO().getMaterialReservations(id);
			request.setAttribute("materialreservations", materialreservations);
			
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
