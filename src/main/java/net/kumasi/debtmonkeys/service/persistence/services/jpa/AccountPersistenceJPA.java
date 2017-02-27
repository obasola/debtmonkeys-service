/*
 * Created on 26 Feb 2017 ( Time 16:22:34 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package net.kumasi.debtmonkeys.service.persistence.services.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import net.kumasi.debtmonkeys.service.domain.jpa.AccountEntity;
import net.kumasi.debtmonkeys.service.persistence.commons.jpa.GenericJpaService;
import net.kumasi.debtmonkeys.service.persistence.commons.jpa.JpaOperation;
import net.kumasi.debtmonkeys.service.persistence.services.AccountPersistence;

/**
 * JPA implementation for basic persistence operations ( entity "Account" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class AccountPersistenceJPA extends GenericJpaService<AccountEntity, Integer> implements AccountPersistence {

	/**
	 * Constructor
	 */
	public AccountPersistenceJPA() {
		super(AccountEntity.class);
	}

	@Override
	public AccountEntity load( Integer id ) {
		return super.load( id );
	}

	@Override
	public boolean delete( Integer id ) {
		return super.delete( id );
	}

	@Override
	public boolean delete(AccountEntity entity) {
		if ( entity != null ) {
			return super.delete( entity.getId() );
		}
		return false ;
	}

	@Override
	public long countAll() {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				Query query = em.createNamedQuery("AccountEntity.countAll");
				return query.getSingleResult() ;
			}
		} ;
		// JPA operation execution 
		return (Long) execute(operation);
	}

}
