/*
 * Created on 26 Feb 2017 ( Time 16:22:45 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.debtmonkeys.service.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import net.kumasi.debtmonkeys.service.domain.Account;
import net.kumasi.debtmonkeys.service.domain.jpa.AccountEntity;
import java.util.Date;
import java.util.List;
import net.kumasi.debtmonkeys.service.business.service.mapping.AccountServiceMapper;
import net.kumasi.debtmonkeys.service.persistence.services.jpa.AccountPersistenceJPA;
import net.kumasi.debtmonkeys.service.test.AccountFactoryForTest;
import net.kumasi.debtmonkeys.service.test.AccountEntityFactoryForTest;
import net.kumasi.debtmonkeys.service.test.MockValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test : Implementation of AccountService
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

	@InjectMocks
	private AccountServiceImpl accountService;
	@Mock
	private AccountPersistenceJPA accountPersistenceJPA;
	@Mock
	private AccountServiceMapper accountServiceMapper;
	
	private AccountFactoryForTest accountFactoryForTest = new AccountFactoryForTest();

	private AccountEntityFactoryForTest accountEntityFactoryForTest = new AccountEntityFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void findById() {
		// Given
		Integer id = mockValues.nextInteger();
		
		AccountEntity accountEntity = accountPersistenceJPA.load(id);
		
		Account account = accountFactoryForTest.newAccount();
		when(accountServiceMapper.mapAccountEntityToAccount(accountEntity)).thenReturn(account);

		// When
		Account accountFound = accountService.findById(id);

		// Then
		assertEquals(account.getId(),accountFound.getId());
	}

	@Test
	public void findAll() {
		// Given
		List<AccountEntity> accountEntitys = new ArrayList<AccountEntity>();
		AccountEntity accountEntity1 = accountEntityFactoryForTest.newAccountEntity();
		accountEntitys.add(accountEntity1);
		AccountEntity accountEntity2 = accountEntityFactoryForTest.newAccountEntity();
		accountEntitys.add(accountEntity2);
		when(accountPersistenceJPA.loadAll()).thenReturn(accountEntitys);
		
		Account account1 = accountFactoryForTest.newAccount();
		when(accountServiceMapper.mapAccountEntityToAccount(accountEntity1)).thenReturn(account1);
		Account account2 = accountFactoryForTest.newAccount();
		when(accountServiceMapper.mapAccountEntityToAccount(accountEntity2)).thenReturn(account2);

		// When
		List<Account> accountsFounds = accountService.findAll();

		// Then
		assertTrue(account1 == accountsFounds.get(0));
		assertTrue(account2 == accountsFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		Account account = accountFactoryForTest.newAccount();

		AccountEntity accountEntity = accountEntityFactoryForTest.newAccountEntity();
		when(accountPersistenceJPA.load(account.getId())).thenReturn(null);
		
		accountEntity = new AccountEntity();
		accountServiceMapper.mapAccountToAccountEntity(account, accountEntity);
		AccountEntity accountEntitySaved = accountPersistenceJPA.save(accountEntity);
		
		Account accountSaved = accountFactoryForTest.newAccount();
		when(accountServiceMapper.mapAccountEntityToAccount(accountEntitySaved)).thenReturn(accountSaved);

		// When
		Account accountResult = accountService.create(account);

		// Then
		assertTrue(accountResult == accountSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		Account account = accountFactoryForTest.newAccount();

		AccountEntity accountEntity = accountEntityFactoryForTest.newAccountEntity();
		when(accountPersistenceJPA.load(account.getId())).thenReturn(accountEntity);

		// When
		Exception exception = null;
		try {
			accountService.create(account);
		} catch(Exception e) {
			exception = e;
		}

		// Then
		assertTrue(exception instanceof IllegalStateException);
		assertEquals("already.exists", exception.getMessage());
	}

	@Test
	public void update() {
		// Given
		Account account = accountFactoryForTest.newAccount();

		AccountEntity accountEntity = accountEntityFactoryForTest.newAccountEntity();
		when(accountPersistenceJPA.load(account.getId())).thenReturn(accountEntity);
		
		AccountEntity accountEntitySaved = accountEntityFactoryForTest.newAccountEntity();
		when(accountPersistenceJPA.save(accountEntity)).thenReturn(accountEntitySaved);
		
		Account accountSaved = accountFactoryForTest.newAccount();
		when(accountServiceMapper.mapAccountEntityToAccount(accountEntitySaved)).thenReturn(accountSaved);

		// When
		Account accountResult = accountService.update(account);

		// Then
		verify(accountServiceMapper).mapAccountToAccountEntity(account, accountEntity);
		assertTrue(accountResult == accountSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer id = mockValues.nextInteger();

		// When
		accountService.delete(id);

		// Then
		verify(accountPersistenceJPA).delete(id);
		
	}

}
