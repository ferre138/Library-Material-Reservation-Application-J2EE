//Nam Nguyen
package com.imthebest.data;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import ca.senecacollege.prg556.limara.bean.AccountOwner;
import ca.senecacollege.prg556.limara.dao.AccountOwnerDAO;

class AccountOwnerData implements AccountOwnerDAO {
	
	private DataSource ds;
	
	AccountOwnerData(DataSource ds)
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

	@Override
	public AccountOwner createLibraryAccount(String firstName, String lastName,
			String username, String password) throws SQLException {
		
		Connection conn = getConnection();
		try
		{
			Statement statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			//PreparedStatement statement = conn.prepareStatement("SELECT id, first_name, last_name FROM accountowner WHERE id = ?");
			try
			{
				
				ResultSet rs = statement.executeQuery("SELECT id, first_name, last_name, username, password FROM accountowner");
				
				try
				{
					rs.moveToInsertRow();
					rs.updateString("first_name", firstName);
					rs.updateString("last_name", lastName);
					rs.updateString("username", username);
					rs.updateString("password", password);
					rs.insertRow();
				
					//AccountOwner accounts = new AccountOwner (id, firstName, lastName);
						//return new AccountOwner (id, firstName, lastName);
				
				}
					finally
					{
						rs.close();
					}
				ResultSet rsIdent = statement.executeQuery("SELECT @@IDENTITY");
				try
				{
					rsIdent.next();
					int accountId = rsIdent.getInt(1);

					return new AccountOwner(accountId, firstName, lastName);
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
	}

	@Override
	public AccountOwner getAccountOwner(int id) throws SQLException {
		///////
		//int id1 = 123;
		//int id2 = 456;
		//int id3 = 789;
		//String fname1 = "ab";
		//String fname2 = "aba";
		//String fname3 = "abc";
		//String lname1 = "cd";
		//String lname2 = "cdc";
		//String lname3 = "cdd";
		//AccountOwner acc1 = new AccountOwner (id1, fname1, lname1);
		//AccountOwner acc2 = new AccountOwner (id2, fname2, lname2);
		//AccountOwner acc3 = new AccountOwner (id3, fname3, lname3);
		///////////
		//if (id1 == id)
		//	{
		//		return acc1;
		//	}
		//else if (id2 == id)
		//	{
		//		return acc2;
		//	}
		//else if (id3 == id)
		//	{
		//		return acc3;
		//	}	
		
		//else
		Connection conn = getConnection();
		try
		{
			PreparedStatement pstmt = conn.prepareStatement("SELECT id FROM accountowner WHERE id = ?");
			try
			{
				pstmt.setInt(1, id);
				ResultSet rslt = pstmt.executeQuery();
				try
				{
					if (rslt.next())
						return new AccountOwner (id, rslt.getString("firstname"), rslt.getString("lastname"));
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
	public AccountOwner validateAccountOwner(String username, String password)
			throws SQLException {
		
	//	String uname1 = "aaa";
	//	String pass1 = "111";
	//	String uname2 = "bbb";
	//	String pass2 = "222";
	//	String uname3 = "ccc";
	//	String pass3 = "333";
		
	//	int id1 = 123;
	//	int id2 = 456;
	//	int id3 = 789;
		
	//	AccountOwner acc1 =  getAccountOwner (id1);
	//	AccountOwner acc2 = getAccountOwner (id2);
	//	AccountOwner acc3 = getAccountOwner (id3);
		
	//	if (uname1.equals(username) && pass1.equals(password))
	//	{
	//		return acc1;
	//	}
	//	else if (uname2.equals(username) && pass2.equals(password))
	//	{
	//		return acc2;
	//	}
	//	else if (uname3.equals(username) && pass3.equals(password))
	//	{
	//		return acc3;
	//	}
		
	//	else
	//	return null;
		
		Connection conn = getConnection();
		try
		{
			PreparedStatement statement = conn.prepareStatement("SELECT id, firstName, lastName FROM accountowner WHERE username = ? AND password = ?");
			try
			{
				statement.setString(1, username);
				statement.setString(2, password);
				ResultSet rs = statement.executeQuery();
				try
				{
					if (rs.next())
						return new AccountOwner(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"));
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
