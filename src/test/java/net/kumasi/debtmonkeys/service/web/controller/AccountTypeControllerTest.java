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
import net.kumasi.debtmonkeys.service.domain.AccountType;
import net.kumasi.debtmonkeys.service.test.AccountTypeFactoryForTest;

//--- Services 
import net.kumasi.debtmonkeys.service.business.service.AccountTypeService;


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
public class AccountTypeControllerTest {
	
	@InjectMocks
	private AccountTypeController accountTypeController;
	@Mock
	private AccountTypeService accountTypeService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private AccountTypeFactoryForTest accountTypeFactoryForTest = new AccountTypeFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<AccountType> list = new ArrayList<AccountType>();
		when(accountTypeService.findAll()).thenReturn(list);
		
		// When
		String viewName = accountTypeController.list(model);
		
		// Then
		assertEquals("accountType/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = accountTypeController.formForCreate(model);
		
		// Then
		assertEquals("accountType/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((AccountType)modelMap.get("accountType")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/accountType/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		AccountType accountType = accountTypeFactoryForTest.newAccountType();
		Integer id = accountType.getId();
		when(accountTypeService.findById(id)).thenReturn(accountType);
		
		// When
		String viewName = accountTypeController.formForUpdate(model, id);
		
		// Then
		assertEquals("accountType/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(accountType, (AccountType) modelMap.get("accountType"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/accountType/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		AccountType accountType = accountTypeFactoryForTest.newAccountType();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		AccountType accountTypeCreated = new AccountType();
		when(accountTypeService.create(accountType)).thenReturn(accountTypeCreated); 
		
		// When
		String viewName = accountTypeController.create(accountType, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/accountType/form/"+accountType.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(accountTypeCreated, (AccountType) modelMap.get("accountType"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		AccountType accountType = accountTypeFactoryForTest.newAccountType();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = accountTypeController.create(accountType, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("accountType/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(accountType, (AccountType) modelMap.get("accountType"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/accountType/create", modelMap.get("saveAction"));
		
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

		AccountType accountType = accountTypeFactoryForTest.newAccountType();
		
		Exception exception = new RuntimeException("test exception");
		when(accountTypeService.create(accountType)).thenThrow(exception);
		
		// When
		String viewName = accountTypeController.create(accountType, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("accountType/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(accountType, (AccountType) modelMap.get("accountType"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/accountType/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "accountType.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		AccountType accountType = accountTypeFactoryForTest.newAccountType();
		Integer id = accountType.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		AccountType accountTypeSaved = new AccountType();
		accountTypeSaved.setId(id);
		when(accountTypeService.update(accountType)).thenReturn(accountTypeSaved); 
		
		// When
		String viewName = accountTypeController.update(accountType, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/accountType/form/"+accountType.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(accountTypeSaved, (AccountType) modelMap.get("accountType"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		AccountType accountType = accountTypeFactoryForTest.newAccountType();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = accountTypeController.update(accountType, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("accountType/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(accountType, (AccountType) modelMap.get("accountType"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/accountType/update", modelMap.get("saveAction"));
		
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

		AccountType accountType = accountTypeFactoryForTest.newAccountType();
		
		Exception exception = new RuntimeException("test exception");
		when(accountTypeService.update(accountType)).thenThrow(exception);
		
		// When
		String viewName = accountTypeController.update(accountType, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("accountType/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(accountType, (AccountType) modelMap.get("accountType"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/accountType/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "accountType.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		AccountType accountType = accountTypeFactoryForTest.newAccountType();
		Integer id = accountType.getId();
		
		// When
		String viewName = accountTypeController.delete(redirectAttributes, id);
		
		// Then
		verify(accountTypeService).delete(id);
		assertEquals("redirect:/accountType", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		AccountType accountType = accountTypeFactoryForTest.newAccountType();
		Integer id = accountType.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(accountTypeService).delete(id);
		
		// When
		String viewName = accountTypeController.delete(redirectAttributes, id);
		
		// Then
		verify(accountTypeService).delete(id);
		assertEquals("redirect:/accountType", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "accountType.error.delete", exception);
	}
	
	
}
