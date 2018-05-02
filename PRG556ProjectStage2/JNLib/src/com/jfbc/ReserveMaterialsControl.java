//Jayme Cunha
package com.jfbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.on.senecac.prg556.common.Control;
import ca.on.senecac.prg556.common.StringHelper;
import ca.senecacollege.prg556.limara.bean.Material;

import com.imthebest.BadRequestException;
import com.imthebest.data.MaterialDAOFactory;
import com.jfbc.data.MaterialReservationDAOFactory;

public class ReserveMaterialsControl implements Control {

	public ReserveMaterialsControl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public String doLogic(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		if ("POST".equals(request.getMethod())){
			
			String type = request.getParameter("type");
			String title = request.getParameter("title");
			
			String button1 = request.getParameter("search");
			String button2 = request.getParameter("reserve");
			
			if(button1 != null){
				
				if (type != null && !type.isEmpty()){
					
					if (type == "All")
						type = null;
					
					//All validations for title and type passed
					if (title != null && !title.isEmpty()){
						
						List<Material> list;
						try {
							list = MaterialDAOFactory.getMaterialDAO().getAvailableMaterials(title,type);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							throw new ServletException(e);
						}
						request.setAttribute("list", list);					
					}
					else
						title = null;
				}
				else
					type = null;
				
				request.setAttribute("type", StringHelper.xmlEscape(type));
				request.setAttribute("title", StringHelper.xmlEscape(title));
			}
			
			else if(button2 != null){
				String materialID = request.getParameter("materialID");
				if(materialID == null || materialID.isEmpty())
				{
					throw new BadRequestException();
				}
				else
				{
					Material mat;
					try {
						mat = MaterialDAOFactory.getMaterialDAO().getMaterial(materialID);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						throw new ServletException(e);
					}
					if(mat == null)
						throw new BadRequestException();
					else{
						synchronized (this){
							try {
								if(MaterialReservationDAOFactory.getMaterialReservationDAO().isMaterialAvailable(mat.getId()))
									MaterialReservationDAOFactory.getMaterialReservationDAO().reserveMaterial(123,mat.getId());
							} catch (SQLException e) {
							// TODO Auto-generated catch block
								throw new ServletException(e);
							}
						}
					}
				}
			}			
		}
		// TODO Auto-generated method stub
 		return "R:"+ request.getContextPath() + "/"; // Redirect to the context root path.
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub

	}

}
