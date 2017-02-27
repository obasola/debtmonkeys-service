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
import net.kumasi.debtmonkeys.service.domain.AccountOwner;
import net.kumasi.debtmonkeys.service.test.AccountOwnerFactoryForTest;

//--- Services 
import net.kumasi.debtmonkeys.service.business.service.AccountOwnerService;


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
public class AccountOwnerControllerTest {
	
	@InjectMocks
	private AccountOwnerController accountOwnerController;
	@Mock
	private AccountOwnerService accountOwnerService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private AccountOwnerFactoryForTest accountOwnerFactoryForTest = new AccountOwnerFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<AccountOwner> list = new ArrayList<AccountOwner>();
		when(accountOwnerService.findAll()).thenReturn(list);
		
		// When
		String viewName = accountOwnerController.list(model);
		
		// Then
		assertEquals("accountOwner/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = accountOwnerController.formForCreate(model);
		
		// Then
		assertEquals("accountOwner/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((AccountOwner)modelMap.get("accountOwner")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/accountOwner/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		AccountOwner accountOwner = accountOwnerFactoryForTest.newAccountOwner();
		Integer id = accountOwner.getId();
		when(accountOwnerService.findById(id)).thenReturn(accountOwner);
		
		// When
		String viewName = accountOwnerController.formForUpdate(model, id);
		
		// Then
		assertEquals("accountOwner/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(accountOwner, (AccountOwner) modelMap.get("accountOwner"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/accountOwner/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		AccountOwner accountOwner = accountOwnerFactoryForTest.newAccountOwner();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		AccountOwner accountOwnerCreated = new AccountOwner();
		when(accountOwnerService.create(accountOwner)).thenReturn(accountOwnerCreated); 
		
		// When
		String viewName = accountOwnerController.create(accountOwner, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/accountOwner/form/"+accountOwner.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(accountOwnerCreated, (AccountOwner) modelMap.get("accountOwner"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		AccountOwner accountOwner = accountOwnerFactoryForTest.newAccountOwner();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = accountOwnerController.create(accountOwner, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("accountOwner/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(accountOwner, (AccountOwner) modelMap.get("accountOwner"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/accountOwner/create", modelMap.get("saveAction"));
		
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

		AccountOwner accountOwner = accountOwnerFactoryForTest.newAccountOwner();
		
		Exception exception = new RuntimeException("test exception");
		when(accountOwnerService.create(accountOwner)).thenThrow(exception);
		
		// When
		String viewName = accountOwnerController.create(accountOwner, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("accountOwner/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(accountOwner, (AccountOwner) modelMap.get("accountOwner"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/accountOwner/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "accountOwner.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		AccountOwner accountOwner = accountOwnerFactoryForTest.newAccountOwner();
		Integer id = accountOwner.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		AccountOwner accountOwnerSaved = new AccountOwner();
		accountOwnerSaved.setId(id);
		when(accountOwnerService.update(accountOwner)).thenReturn(accountOwnerSaved); 
		
		// When
		String viewName = accountOwnerController.update(accountOwner, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/accountOwner/form/"+accountOwner.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(accountOwnerSaved, (AccountOwner) modelMap.get("accountOwner"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		AccountOwner accountOwner = accountOwnerFactoryForTest.newAccountOwner();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = accountOwnerController.update(accountOwner, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("accountOwner/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(accountOwner, (AccountOwner) modelMap.get("accountOwner"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/accountOwner/update", modelMap.get("saveAction"));
		
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

		AccountOwner accountOwner = accountOwnerFactoryForTest.newAccountOwner();
		
		Exception exception = new RuntimeException("test exception");
		when(accountOwnerService.update(accountOwner)).thenThrow(exception);
		
		// When
		String viewName = accountOwnerController.update(accountOwner, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("accountOwner/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(accountOwner, (AccountOwner) modelMap.get("accountOwner"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/accountOwner/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "accountOwner.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		AccountOwner accountOwner = accountOwnerFactoryForTest.newAccountOwner();
		Integer id = accountOwner.getId();
		
		// When
		String viewName = accountOwnerController.delete(redirectAttributes, id);
		
		// Then
		verify(accountOwnerService).delete(id);
		assertEquals("redirect:/accountOwner", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		AccountOwner accountOwner = accountOwnerFactoryForTest.newAccountOwner();
		Integer id = accountOwner.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(accountOwnerService).delete(id);
		
		// When
		String viewName = accountOwnerController.delete(redirectAttributes, id);
		
		// Then
		verify(accountOwnerService).delete(id);
		assertEquals("redirect:/accountOwner", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "accountOwner.error.delete", exception);
	}
	
	
}
