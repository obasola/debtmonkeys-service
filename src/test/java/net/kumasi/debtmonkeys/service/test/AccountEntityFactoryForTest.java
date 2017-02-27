package net.kumasi.debtmonkeys.service.test;

import net.kumasi.debtmonkeys.service.domain.jpa.AccountEntity;

public class AccountEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public AccountEntity newAccountEntity() {

		Integer id = mockValues.nextInteger();

		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId(id);
		return accountEntity;
	}
	
}
