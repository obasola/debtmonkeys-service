/*
 * Created on 26 Feb 2017 ( Time 16:22:34 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.debtmonkeys.service.test.persistence;


import net.kumasi.debtmonkeys.service.domain.jpa.PaymentHistoryEntity;
import net.kumasi.debtmonkeys.service.mock.PaymentHistoryEntityMock;
import net.kumasi.debtmonkeys.service.persistence.PersistenceServiceProvider;
import net.kumasi.debtmonkeys.service.persistence.services.PaymentHistoryPersistence;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test case for PaymentHistory persistence service
 * 
 * @author Telosys Tools Generator
 *
 */
public class PaymentHistoryPersistenceTest 
{
	@Test
	public void test1() {
		
		System.out.println("Test count ..." );
		
		PaymentHistoryPersistence service = PersistenceServiceProvider.getService(PaymentHistoryPersistence.class);
		System.out.println("CountAll = " + service.countAll() );
	}
	
	@Test
	public void test2() {
		
		System.out.println("Test PaymentHistory persistence : delete + load ..." );
		
		PaymentHistoryPersistence service = PersistenceServiceProvider.getService(PaymentHistoryPersistence.class);
		
		PaymentHistoryEntityMock mock = new PaymentHistoryEntityMock();
		
		// TODO : set primary key values here 
		process( service, mock, 0  );
		// process( service, mock, ... );
	}

	private void process(PaymentHistoryPersistence service, PaymentHistoryEntityMock mock, Integer idpaymentHistory ) {
		System.out.println("----- "  );
		System.out.println(" . load : " );
		PaymentHistoryEntity entity = service.load( idpaymentHistory );
		if ( entity != null ) {
			// Found 
			System.out.println("   FOUND : " + entity );
			
			// Save (update) with the same values to avoid database integrity errors  
			System.out.println(" . save : " + entity );
			service.save(entity);
			System.out.println("   saved : " + entity );
		}
		else {
			// Not found 
			System.out.println("   NOT FOUND" );
			// Create a new instance 
			entity = mock.createInstance( idpaymentHistory ) ;
			Assert.assertNotNull(entity);

			// This entity references the following entities : 
			// . Account
			/* Insert only if references are OK
			// Try to insert the new instance
			System.out.println(" . insert : " + entity );
			service.insert(entity);
			System.out.println("   inserted : " + entity );
			*/

			System.out.println(" . delete : " );
			boolean deleted = service.delete( idpaymentHistory );
			System.out.println("   deleted = " + deleted);
		}		
	}
}