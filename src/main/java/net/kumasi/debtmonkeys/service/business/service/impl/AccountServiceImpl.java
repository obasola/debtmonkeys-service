/*
 * Created on 26 Feb 2017 ( Time 16:22:45 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.debtmonkeys.service.business.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.kumasi.debtmonkeys.service.domain.Account;
import net.kumasi.debtmonkeys.service.domain.jpa.AccountEntity;
import java.util.Date;
import java.util.List;
import net.kumasi.debtmonkeys.service.business.service.AccountService;
import net.kumasi.debtmonkeys.service.business.service.mapping.AccountServiceMapper;
import net.kumasi.debtmonkeys.service.persistence.PersistenceServiceProvider;
import net.kumasi.debtmonkeys.service.persistence.services.AccountPersistence;
import org.springframework.stereotype.Component;

/**
 * Implementation of AccountService
 */
@Component
public class AccountServiceImpl implements AccountService {

	private AccountPersistence accountPersistence;

	@Resource
	private AccountServiceMapper accountServiceMapper;
	
	public AccountServiceImpl() {
		accountPersistence = PersistenceServiceProvider.getService(AccountPersistence.class);
	}
		
	@Override
	public Account findById(Integer id) {
		AccountEntity entity = accountPersistence.load(id);
		return accountServiceMapper.mapAccountEntityToAccount(entity);
	}

	@Override
	public List<Account> findAll() {
		List<AccountEntity> entities = accountPersistence.loadAll();
		List<Account> beans = new ArrayList<Account>();
		for(AccountEntity entity : entities) {
			beans.add(accountServiceMapper.mapAccountEntityToAccount(entity));
		}
		return beans;
	}

	@Override
	public Account save(Account account) {
		return update(account) ;
	}

	@Override
	public Account create(Account account) {
		if(accountPersistence.load(account.getId()) != null) {
			throw new IllegalStateException("already.exists");
		}
		AccountEntity accountEntity = new AccountEntity();
		accountServiceMapper.mapAccountToAccountEntity(account, accountEntity);
		AccountEntity accountEntitySaved = accountPersistence.save(accountEntity);
		return accountServiceMapper.mapAccountEntityToAccount(accountEntitySaved);
	}

	@Override
	public Account update(Account account) {
		AccountEntity accountEntity = accountPersistence.load(account.getId());
		accountServiceMapper.mapAccountToAccountEntity(account, accountEntity);
		AccountEntity accountEntitySaved = accountPersistence.save(accountEntity);
		return accountServiceMapper.mapAccountEntityToAccount(accountEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		accountPersistence.delete(id);
	}

	public AccountPersistence getAccountPersistence() {
		return accountPersistence;
	}

	public void setAccountPersistence(AccountPersistence accountPersistence) {
		this.accountPersistence = accountPersistence;
	}

	public AccountServiceMapper getAccountServiceMapper() {
		return accountServiceMapper;
	}

	public void setAccountServiceMapper(AccountServiceMapper accountServiceMapper) {
		this.accountServiceMapper = accountServiceMapper;
	}

}
