package net.kumasi.debtmonkeys.service.test;

import net.kumasi.debtmonkeys.service.domain.jpa.AccountTypeEntity;

public class AccountTypeEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public AccountTypeEntity newAccountTypeEntity() {

		Integer id = mockValues.nextInteger();

		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setId(id);
		return accountTypeEntity;
	}
	
}
