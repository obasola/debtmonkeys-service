package net.kumasi.debtmonkeys.service.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//--- Entities
import net.kumasi.debtmonkeys.service.domain.PaymentHistory;
import net.kumasi.debtmonkeys.service.domain.Account;
import net.kumasi.debtmonkeys.service.test.PaymentHistoryFactoryForTest;
import net.kumasi.debtmonkeys.service.test.AccountFactoryForTest;

//--- Services 
import net.kumasi.debtmonkeys.service.business.service.PaymentHistoryService;
import net.kumasi.debtmonkeys.service.business.service.AccountService;

//--- List Items 
import net.kumasi.debtmonkeys.service.web.listitem.AccountListItem;

import net.kumasi.debtmonkeys.service.web.common.Message;
import net.kumasi.debtmonkeys.service.web.common.MessageHelper;
import net.kumasi.debtmonkeys.service.web.common.MessageType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RunWith(MockitoJUnitRunner.class)
public class PaymentHistoryControllerTest {
	
	@InjectMocks
	private PaymentHistoryController paymentHistoryController;
	@Mock
	private PaymentHistoryService paymentHistoryService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private AccountService accountService; // Injected by Spring

	private PaymentHistoryFactoryForTest paymentHistoryFactoryForTest = new PaymentHistoryFactoryForTest();
	private AccountFactoryForTest accountFactoryForTest = new AccountFactoryForTest();

	List<Account> accounts = new ArrayList<Account>();

	private void givenPopulateModel() {
		Account account1 = accountFactoryForTest.newAccount();
		Account account2 = accountFactoryForTest.newAccount();
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(account1);
		accounts.add(account2);
		when(accountService.findAll()).thenReturn(accounts);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<PaymentHistory> list = new ArrayList<PaymentHistory>();
		when(paymentHistoryService.findAll()).thenReturn(list);
		
		// When
		String viewName = paymentHistoryController.list(model);
		
		// Then
		assertEquals("paymentHistory/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = paymentHistoryController.formForCreate(model);
		
		// Then
		assertEquals("paymentHistory/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((PaymentHistory)modelMap.get("paymentHistory")).getIdpaymentHistory());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/paymentHistory/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<AccountListItem> accountListItems = (List<AccountListItem>) modelMap.get("listOfAccountItems");
		assertEquals(2, accountListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		PaymentHistory paymentHistory = paymentHistoryFactoryForTest.newPaymentHistory();
		Integer idpaymentHistory = paymentHistory.getIdpaymentHistory();
		when(paymentHistoryService.findById(idpaymentHistory)).thenReturn(paymentHistory);
		
		// When
		String viewName = paymentHistoryController.formForUpdate(model, idpaymentHistory);
		
		// Then
		assertEquals("paymentHistory/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(paymentHistory, (PaymentHistory) modelMap.get("paymentHistory"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/paymentHistory/update", modelMap.get("saveAction"));
		
		List<AccountListItem> accountListItems = (List<AccountListItem>) modelMap.get("listOfAccountItems");
		assertEquals(2, accountListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		PaymentHistory paymentHistory = paymentHistoryFactoryForTest.newPaymentHistory();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		PaymentHistory paymentHistoryCreated = new PaymentHistory();
		when(paymentHistoryService.create(paymentHistory)).thenReturn(paymentHistoryCreated); 
		
		// When
		String viewName = paymentHistoryController.create(paymentHistory, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/paymentHistory/form/"+paymentHistory.getIdpaymentHistory(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(paymentHistoryCreated, (PaymentHistory) modelMap.get("paymentHistory"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		PaymentHistory paymentHistory = paymentHistoryFactoryForTest.newPaymentHistory();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = paymentHistoryController.create(paymentHistory, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("paymentHistory/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(paymentHistory, (PaymentHistory) modelMap.get("paymentHistory"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/paymentHistory/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<AccountListItem> accountListItems = (List<AccountListItem>) modelMap.get("listOfAccountItems");
		assertEquals(2, accountListItems.size());
		
	}

	@Test
	public void createException() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		PaymentHistory paymentHistory = paymentHistoryFactoryForTest.newPaymentHistory();
		
		Exception exception = new RuntimeException("test exception");
		when(paymentHistoryService.create(paymentHistory)).thenThrow(exception);
		
		// When
		String viewName = paymentHistoryController.create(paymentHistory, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("paymentHistory/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(paymentHistory, (PaymentHistory) modelMap.get("paymentHistory"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/paymentHistory/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "paymentHistory.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<AccountListItem> accountListItems = (List<AccountListItem>) modelMap.get("listOfAccountItems");
		assertEquals(2, accountListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		PaymentHistory paymentHistory = paymentHistoryFactoryForTest.newPaymentHistory();
		Integer idpaymentHistory = paymentHistory.getIdpaymentHistory();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		PaymentHistory paymentHistorySaved = new PaymentHistory();
		paymentHistorySaved.setIdpaymentHistory(idpaymentHistory);
		when(paymentHistoryService.update(paymentHistory)).thenReturn(paymentHistorySaved); 
		
		// When
		String viewName = paymentHistoryController.update(paymentHistory, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/paymentHistory/form/"+paymentHistory.getIdpaymentHistory(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(paymentHistorySaved, (PaymentHistory) modelMap.get("paymentHistory"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		PaymentHistory paymentHistory = paymentHistoryFactoryForTest.newPaymentHistory();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = paymentHistoryController.update(paymentHistory, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("paymentHistory/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(paymentHistory, (PaymentHistory) modelMap.get("paymentHistory"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/paymentHistory/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<AccountListItem> accountListItems = (List<AccountListItem>) modelMap.get("listOfAccountItems");
		assertEquals(2, accountListItems.size());
		
	}

	@Test
	public void updateException() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		PaymentHistory paymentHistory = paymentHistoryFactoryForTest.newPaymentHistory();
		
		Exception exception = new RuntimeException("test exception");
		when(paymentHistoryService.update(paymentHistory)).thenThrow(exception);
		
		// When
		String viewName = paymentHistoryController.update(paymentHistory, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("paymentHistory/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(paymentHistory, (PaymentHistory) modelMap.get("paymentHistory"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/paymentHistory/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "paymentHistory.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<AccountListItem> accountListItems = (List<AccountListItem>) modelMap.get("listOfAccountItems");
		assertEquals(2, accountListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		PaymentHistory paymentHistory = paymentHistoryFactoryForTest.newPaymentHistory();
		Integer idpaymentHistory = paymentHistory.getIdpaymentHistory();
		
		// When
		String viewName = paymentHistoryController.delete(redirectAttributes, idpaymentHistory);
		
		// Then
		verify(paymentHistoryService).delete(idpaymentHistory);
		assertEquals("redirect:/paymentHistory", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		PaymentHistory paymentHistory = paymentHistoryFactoryForTest.newPaymentHistory();
		Integer idpaymentHistory = paymentHistory.getIdpaymentHistory();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(paymentHistoryService).delete(idpaymentHistory);
		
		// When
		String viewName = paymentHistoryController.delete(redirectAttributes, idpaymentHistory);
		
		// Then
		verify(paymentHistoryService).delete(idpaymentHistory);
		assertEquals("redirect:/paymentHistory", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "paymentHistory.error.delete", exception);
	}
	
	
}
