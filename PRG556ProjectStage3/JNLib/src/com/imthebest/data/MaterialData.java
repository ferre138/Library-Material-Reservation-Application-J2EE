//Nam Nguyen
package com.imthebest.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import ca.senecacollege.prg556.limara.bean.Material;
import ca.senecacollege.prg556.limara.dao.MaterialDAO;

class MaterialData implements MaterialDAO{
	
	private DataSource ds;
	
	MaterialData(DataSource ds)
	{
		setDs(ds);
	}
	
	private DataSource getDs()
	{
		return ds;
	}

	private void setDs(DataSource ds)
	{
		this.ds = ds;
	}

	public List<Material> getAvailableMaterials(String title, String type)
			throws SQLException {
		List<Material> materials = new ArrayList<Material>();
		Connection conn = getConnection();
		try
		{
			PreparedStatement statement = conn.prepareStatement("SELECT title FROM material WHERE title = ? AND type = ? ORDER BY type");
			try
			{
				statement.setString(1,title);
				ResultSet rs = statement.executeQuery();
				try
				{
					
					while (rs.next())
					{
					if (rs.getString("title").contains(title))
					{
						materials.add(getMaterial(rs.getString("id")));
						return materials;
					}
					else 
						return null;
				}	
			}
				finally
				{
					rs.close();
				}	
			}
			finally
			{
				statement.close();
			}
		}
		finally
		{
			closeConnection(conn);
		}
		return null;
		
		}
			
	
	//materials.add(getMaterial("123"));
	//materials.add(getMaterial("456"));
	

	@Override
	public Material getMaterial(String materialId) throws SQLException {
			
		Connection conn = getConnection();
		try
		{
			PreparedStatement pstmt = conn.prepareStatement("SELECT id, title, type FROM material WHERE title = ?");
		try
		{
			pstmt.setString(1, materialId);
			ResultSet rslt = pstmt.executeQuery();
		
	try
	{
			if (rslt.next())
				{
				return new Material(rslt.getString("materialid"), rslt.getString("title"), rslt.getString("type")) ;
				}
			
			else
		return null;
	}
		
		finally
		{
			rslt.close();
		}
	}
	finally
	{
		pstmt.close();
	}
}
finally
{
	closeConnection(conn);
}
	}
	private Connection getConnection() throws SQLException
	{
		if (getDs() != null)
			return getDs().getConnection();
		else
			return null;
	}
	
	private void closeConnection(Connection conn) throws SQLException
	{
		if (conn != null)
			conn.close();
	}

}
