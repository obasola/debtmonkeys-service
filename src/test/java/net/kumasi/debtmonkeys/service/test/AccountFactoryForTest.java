package net.kumasi.debtmonkeys.service.test;

import net.kumasi.debtmonkeys.service.domain.Account;

public class AccountFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Account newAccount() {

		Integer id = mockValues.nextInteger();

		Account account = new Account();
		account.setId(id);
		return account;
	}
	
}
