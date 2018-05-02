//Jayme Cunha
package com.jfbc.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;


import com.imthebest.data.MaterialDAOFactory;


import ca.senecacollege.prg556.limara.bean.Material;
import ca.senecacollege.prg556.limara.bean.MaterialReservation;
import ca.senecacollege.prg556.limara.dao.MaterialReservationDAO;

class MaterialReservationData implements MaterialReservationDAO {
	private DataSource ds;
	
	MaterialReservationData(DataSource ds)
	{
		setDs(ds);
	}
		public MaterialReservationData() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void cancelMaterialReservation(String materialId) throws SQLException {
			Connection conn = getConnection();
			try
			{
				PreparedStatement pstmt = conn.prepareStatement("SELECT material_id FROM materialreservation WHERE material_id = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				try
				{
					pstmt.setString(1, materialId);
					ResultSet rslt = pstmt.executeQuery();
					try
					{
						if (rslt.next())
							rslt.deleteRow();
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
			// TODO Auto-generated method stub
		}

		@Override
		public void deleteExpiredMaterialReservations() throws SQLException {
			Connection conn = getConnection();
			try
			{
				PreparedStatement pstmt = conn.prepareStatement("SELECT material_id FROM materialreservation WHERE expiration_date < ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				try
				{
					Date today = new Date();
					Timestamp expirationDate = new Timestamp(today.getTime());
					pstmt.setTimestamp(1, expirationDate);
					ResultSet rslt = pstmt.executeQuery();
					try
					{
						while(rslt.next())
							rslt.deleteRow();
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
			// TODO Auto-generated method stub
		}

		@Override
		public MaterialReservation getMaterialReservation(String materialId)
				throws SQLException {
			
			Connection conn = getConnection();
			try
			{
				PreparedStatement pstmt = conn.prepareStatement("SELECT material_id, title, type, expiration_date FROM materialreservation INNER JOIN material " +
																"ON materialreservation.material_id = material.id WHERE material_id = ?");
				try
				{
					pstmt.setString(1, materialId);
					ResultSet rslt = pstmt.executeQuery();
					try
					{
						if (rslt.next())
							return new MaterialReservation(materialId, rslt.getString("title"), rslt.getString("type"), rslt.getTimestamp("expiration_date"));
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

		@Override
		public List<MaterialReservation> getMaterialReservations(int accountOwnerId)
				throws SQLException {
			
			Connection conn = getConnection();
			try
			{
				PreparedStatement pstmt = conn.prepareStatement("SELECT material_id FROM materialreservation INNER JOIN material " +
																"ON materialreservation.material_id = material.id WHERE account_owner_id = ? ORDER BY type, title");
				try
				{
					pstmt.setInt(1, accountOwnerId);
					ResultSet rslt = pstmt.executeQuery();
					try
					{
						List<MaterialReservation> materialsReserved = new ArrayList<MaterialReservation>();
						while (rslt.next())
						{
							materialsReserved.add(getMaterialReservation(rslt.getString("material_id")));
						}
						return 	materialsReserved;
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

		@Override
		public boolean isMaterialAvailable(String materialId) throws SQLException {
			Connection conn = getConnection();
			try
			{
				PreparedStatement pstmt = conn.prepareStatement("SELECT material_id FROM materialreservation WHERE material_id = ?");
				try
				{
					pstmt.setString(1, materialId);
					ResultSet rslt = pstmt.executeQuery();
					try
					{
						if (rslt.next())
							return false;
						else
							return true;
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

		@Override
		public MaterialReservation reserveMaterial(int accountOwnerId, String materialId)
				throws SQLException {
			
			if(isMaterialAvailable(materialId))
			{
				Connection conn = getConnection();
				try
				{
					Date today = new Date();
					Timestamp expirationDate = new Timestamp(today.getTime() + 7*24*60*60*1000);
					
					Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
					try
					{
						ResultSet rslt = stmt.executeQuery("SELECT material_id, account_owner_id, expiration_date FROM materialreservation");
						try
						{
							rslt.moveToInsertRow();
							rslt.updateString("material_id", materialId);
							rslt.updateInt("account_owner_id", accountOwnerId);
							rslt.updateTimestamp("expiration_date", expirationDate);
							rslt.insertRow();
						}
						finally
						{
							rslt.close();
						}
						String sql = "SELECT @@IDENTITY";
						
						rslt = stmt.executeQuery(sql); // get the generated id
						try
						{
							rslt.next();
							materialId = rslt.getString(1); // get value from first column
							Material tempMat = MaterialDAOFactory.getMaterialDAO().getMaterial(materialId);
							return new MaterialReservation(materialId,tempMat.getTitle(),tempMat.getType(),expirationDate);
						}
						finally
						{
							rslt.close();
						}
					}
					finally
					{
						stmt.close();
					}
				}
				finally
				{
					closeConnection(conn);
				}
			}
			return null;
			
			
			// TODO Auto-generated method stub
		}
		
		private DataSource getDs()
		{
			return ds;
		}

		private void setDs(DataSource ds)
		{
			this.ds = ds;
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