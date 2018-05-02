//Nam Nguyen
package com.imthebest.data;


import ca.senecacollege.prg556.limara.dao.MaterialDAO;


public class MaterialDAOFactory
{
	public static MaterialDAO getMaterialDAO()
	{
		return new MaterialData(DataSourceFactory.getDataSource());
	}
}

