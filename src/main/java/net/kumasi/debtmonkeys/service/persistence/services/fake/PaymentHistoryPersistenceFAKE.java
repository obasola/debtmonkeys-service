/*
 * Created on 26 Feb 2017 ( Time 16:22:34 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.debtmonkeys.service.persistence.services.fake;

import java.util.List;
import java.util.Map;

import net.kumasi.debtmonkeys.service.domain.jpa.PaymentHistoryEntity;
import net.kumasi.debtmonkeys.service.persistence.commons.fake.GenericFakeService;
import net.kumasi.debtmonkeys.service.persistence.services.PaymentHistoryPersistence;

/**
 * Fake persistence service implementation ( entity "PaymentHistory" )
 *
 * @author Telosys Tools Generator
 */
public class PaymentHistoryPersistenceFAKE extends GenericFakeService<PaymentHistoryEntity> implements PaymentHistoryPersistence {

	public PaymentHistoryPersistenceFAKE () {
		super(PaymentHistoryEntity.class);
	}
	
	protected PaymentHistoryEntity buildEntity(int index) {
		PaymentHistoryEntity entity = new PaymentHistoryEntity();
		// Init fields with mock values
		entity.setIdpaymentHistory( nextInteger() ) ;
		entity.setBalanceRemaining( nextDouble() ) ;
		entity.setAmountPaid( nextDouble() ) ;
		entity.setPaymentDate( nextDate() ) ;
		entity.setNextPaymentDate( nextDate() ) ;
		entity.setDateModified( nextDate() ) ;
		entity.setDateCreated( nextDate() ) ;
		return entity ;
	}
	
	
	public boolean delete(PaymentHistoryEntity entity) {
		log("delete ( PaymentHistoryEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Integer idpaymentHistory ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(PaymentHistoryEntity entity) {
		log("insert ( PaymentHistoryEntity : " + entity + ")" ) ;
	}

	public PaymentHistoryEntity load( Integer idpaymentHistory ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<PaymentHistoryEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<PaymentHistoryEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<PaymentHistoryEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public PaymentHistoryEntity save(PaymentHistoryEntity entity) {
		log("insert ( PaymentHistoryEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<PaymentHistoryEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}