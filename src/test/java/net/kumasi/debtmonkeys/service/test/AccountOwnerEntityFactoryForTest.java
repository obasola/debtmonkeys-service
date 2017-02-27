package net.kumasi.debtmonkeys.service.test;

import net.kumasi.debtmonkeys.service.domain.jpa.AccountOwnerEntity;

public class AccountOwnerEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public AccountOwnerEntity newAccountOwnerEntity() {

		Integer id = mockValues.nextInteger();

		AccountOwnerEntity accountOwnerEntity = new AccountOwnerEntity();
		accountOwnerEntity.setId(id);
		return accountOwnerEntity;
	}
	
}
