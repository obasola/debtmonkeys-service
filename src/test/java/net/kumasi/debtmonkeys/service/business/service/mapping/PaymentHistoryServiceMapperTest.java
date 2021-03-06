/*
 * Created on 26 Feb 2017 ( Time 16:22:45 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.debtmonkeys.service.business.service.mapping;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import net.kumasi.debtmonkeys.service.domain.PaymentHistory;
import net.kumasi.debtmonkeys.service.domain.jpa.PaymentHistoryEntity;
import net.kumasi.debtmonkeys.service.domain.jpa.AccountEntity;
import net.kumasi.debtmonkeys.service.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class PaymentHistoryServiceMapperTest {

	private PaymentHistoryServiceMapper paymentHistoryServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		paymentHistoryServiceMapper = new PaymentHistoryServiceMapper();
		paymentHistoryServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'PaymentHistoryEntity' to 'PaymentHistory'
	 * @param paymentHistoryEntity
	 */
	@Test
	public void testMapPaymentHistoryEntityToPaymentHistory() {
		// Given
		PaymentHistoryEntity paymentHistoryEntity = new PaymentHistoryEntity();
		paymentHistoryEntity.setBalanceRemaining(mockValues.nextDouble());
		paymentHistoryEntity.setAmountPaid(mockValues.nextDouble());
		paymentHistoryEntity.setPaymentDate(mockValues.nextDate());
		paymentHistoryEntity.setNextPaymentDate(mockValues.nextDate());
		paymentHistoryEntity.setDateModified(mockValues.nextDate());
		paymentHistoryEntity.setDateCreated(mockValues.nextDate());
		paymentHistoryEntity.setAccount(new AccountEntity());
		paymentHistoryEntity.getAccount().setId(mockValues.nextInteger());
		
		// When
		PaymentHistory paymentHistory = paymentHistoryServiceMapper.mapPaymentHistoryEntityToPaymentHistory(paymentHistoryEntity);
		
		// Then
		assertEquals(paymentHistoryEntity.getBalanceRemaining(), paymentHistory.getBalanceRemaining());
		assertEquals(paymentHistoryEntity.getAmountPaid(), paymentHistory.getAmountPaid());
		assertEquals(paymentHistoryEntity.getPaymentDate(), paymentHistory.getPaymentDate());
		assertEquals(paymentHistoryEntity.getNextPaymentDate(), paymentHistory.getNextPaymentDate());
		assertEquals(paymentHistoryEntity.getDateModified(), paymentHistory.getDateModified());
		assertEquals(paymentHistoryEntity.getDateCreated(), paymentHistory.getDateCreated());
		assertEquals(paymentHistoryEntity.getAccount().getId(), paymentHistory.getAccountId());
	}
	
	/**
	 * Test : Mapping from 'PaymentHistory' to 'PaymentHistoryEntity'
	 */
	@Test
	public void testMapPaymentHistoryToPaymentHistoryEntity() {
		// Given
		PaymentHistory paymentHistory = new PaymentHistory();
		paymentHistory.setBalanceRemaining(mockValues.nextDouble());
		paymentHistory.setAmountPaid(mockValues.nextDouble());
		paymentHistory.setPaymentDate(mockValues.nextDate());
		paymentHistory.setNextPaymentDate(mockValues.nextDate());
		paymentHistory.setDateModified(mockValues.nextDate());
		paymentHistory.setDateCreated(mockValues.nextDate());
		paymentHistory.setAccountId(mockValues.nextInteger());

		PaymentHistoryEntity paymentHistoryEntity = new PaymentHistoryEntity();
		
		// When
		paymentHistoryServiceMapper.mapPaymentHistoryToPaymentHistoryEntity(paymentHistory, paymentHistoryEntity);
		
		// Then
		assertEquals(paymentHistory.getBalanceRemaining(), paymentHistoryEntity.getBalanceRemaining());
		assertEquals(paymentHistory.getAmountPaid(), paymentHistoryEntity.getAmountPaid());
		assertEquals(paymentHistory.getPaymentDate(), paymentHistoryEntity.getPaymentDate());
		assertEquals(paymentHistory.getNextPaymentDate(), paymentHistoryEntity.getNextPaymentDate());
		assertEquals(paymentHistory.getDateModified(), paymentHistoryEntity.getDateModified());
		assertEquals(paymentHistory.getDateCreated(), paymentHistoryEntity.getDateCreated());
		assertEquals(paymentHistory.getAccountId(), paymentHistoryEntity.getAccount().getId());
	}

}