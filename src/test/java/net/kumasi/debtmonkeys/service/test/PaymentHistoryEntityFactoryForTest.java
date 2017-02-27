package net.kumasi.debtmonkeys.service.test;

import net.kumasi.debtmonkeys.service.domain.jpa.PaymentHistoryEntity;

public class PaymentHistoryEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public PaymentHistoryEntity newPaymentHistoryEntity() {

		Integer idpaymentHistory = mockValues.nextInteger();

		PaymentHistoryEntity paymentHistoryEntity = new PaymentHistoryEntity();
		paymentHistoryEntity.setIdpaymentHistory(idpaymentHistory);
		return paymentHistoryEntity;
	}
	
}
