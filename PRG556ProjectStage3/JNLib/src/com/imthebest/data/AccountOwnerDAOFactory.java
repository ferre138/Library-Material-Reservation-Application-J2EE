//Nam Nguyen
package com.imthebest.data;

import ca.senecacollege.prg556.limara.dao.AccountOwnerDAO;

public class AccountOwnerDAOFactory{

	public static AccountOwnerDAO getAccountOwnerDAO(){
		return new AccountOwnerData(DataSourceFactory.getDataSource());
	}
}
