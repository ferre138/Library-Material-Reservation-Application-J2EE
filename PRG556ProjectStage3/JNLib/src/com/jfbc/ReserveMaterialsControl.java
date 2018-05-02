//Jayme Cunha
package com.jfbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.on.senecac.prg556.common.Control;
import ca.on.senecac.prg556.common.StringHelper;
import ca.senecacollege.prg556.limara.bean.AccountOwner;
import ca.senecacollege.prg556.limara.bean.Material;

import com.imthebest.BadRequestException;
import com.imthebest.LibraryLoginControl.UserSession;
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
			
			boolean sOK = true;
			
			if(button1 != null || button2 != null){
				
				if (StringHelper.isNotNullOrEmpty(type)){ //Check if type is not null or empty
										
					if ("All".equals(type))
					{
						type = null;
						if (StringHelper.isNotNullOrEmpty(title))
							request.setAttribute("title", StringHelper.xmlEscape(title));
						else
							request.setAttribute("emptyTitle", true);
							sOK = false;
					}
					else
					{
						if (StringHelper.isNotNullOrEmpty(title))
							request.setAttribute("title", StringHelper.xmlEscape(title));
						else
						{
							title = null;
							request.setAttribute("title", StringHelper.xmlEscape(title));
						}
					}
					request.setAttribute("type", StringHelper.xmlEscape(type));
				}
				else
				{
					request.setAttribute("invalidtype", true);
					sOK = false;
				}
				
				if(sOK)
				{
					try
					{
						List<Material> list;
						list = MaterialDAOFactory.getMaterialDAO().getAvailableMaterials(title,type);
						if(type == "All" || (StringHelper.isNullOrEmpty(title)))
							list = null;
						request.setAttribute("list", list);
					}
					catch (SQLException e)
					{
						throw new ServletException(e);
					}
				}
				else return null;
			}
			
			if(button2 != null){
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
					} 
					catch (SQLException e) {
						throw new ServletException(e);
					}
					if(mat == null)
						throw new BadRequestException();
					else{
						synchronized (this){
							try {
								if(MaterialReservationDAOFactory.getMaterialReservationDAO().isMaterialAvailable(mat.getId()))
								{
									int currentUser = ((UserSession)request.getSession().getAttribute("userSession")).getUser().getId();
									MaterialReservationDAOFactory.getMaterialReservationDAO().reserveMaterial(currentUser,mat.getId()); 		
									return "R:"+ request.getContextPath() + "/"; // Redirect to the context root path.
								}
								else
								{
									request.setAttribute("unavailableMaterial", true);
									return null;
								}
							} catch (SQLException e) {
							// TODO Auto-generated catch block
								throw new ServletException(e);
							}
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public void init() throws ServletException {
	}

}
