package net.kumasi.debtmonkeys.service.test;

import net.kumasi.debtmonkeys.service.domain.AccountOwner;

public class AccountOwnerFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public AccountOwner newAccountOwner() {

		Integer id = mockValues.nextInteger();

		AccountOwner accountOwner = new AccountOwner();
		accountOwner.setId(id);
		return accountOwner;
	}
	
}
