/*
 * Created on 26 Feb 2017 ( Time 16:22:34 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.debtmonkeys.service.persistence.services.fake;

import java.util.List;
import java.util.Map;

import net.kumasi.debtmonkeys.service.domain.jpa.AccountOwnerEntity;
import net.kumasi.debtmonkeys.service.persistence.commons.fake.GenericFakeService;
import net.kumasi.debtmonkeys.service.persistence.services.AccountOwnerPersistence;

/**
 * Fake persistence service implementation ( entity "AccountOwner" )
 *
 * @author Telosys Tools Generator
 */
public class AccountOwnerPersistenceFAKE extends GenericFakeService<AccountOwnerEntity> implements AccountOwnerPersistence {

	public AccountOwnerPersistenceFAKE () {
		super(AccountOwnerEntity.class);
	}
	
	protected AccountOwnerEntity buildEntity(int index) {
		AccountOwnerEntity entity = new AccountOwnerEntity();
		// Init fields with mock values
		entity.setId( nextInteger() ) ;
		entity.setUserName( nextString() ) ;
		entity.setPassword( nextString() ) ;
		entity.setEmailAddress( nextString() ) ;
		entity.setFirstName( nextString() ) ;
		entity.setLastName( nextString() ) ;
		entity.setNetMonthlyIncome( nextDouble() ) ;
		entity.setIsadmin( nextString() ) ;
		return entity ;
	}
	
	
	public boolean delete(AccountOwnerEntity entity) {
		log("delete ( AccountOwnerEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Integer id ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(AccountOwnerEntity entity) {
		log("insert ( AccountOwnerEntity : " + entity + ")" ) ;
	}

	public AccountOwnerEntity load( Integer id ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<AccountOwnerEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<AccountOwnerEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<AccountOwnerEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public AccountOwnerEntity save(AccountOwnerEntity entity) {
		log("insert ( AccountOwnerEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<AccountOwnerEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}