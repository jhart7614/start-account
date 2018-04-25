package com.qa.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.qa.util.JSONUtil;

import Transaction.BusinessImplementation;

@RunWith(MockitoJUnitRunner.class)
public class BusinessImplementaionMockTest {

	
	@InjectMocks
	private BusinessImplementation b;
	
	@Mock
	private Query query;
	
	@Mock
	private EntityManager em;
	
	private JSONUtil util;
	
	@Before
	public void setup() {
		b.setManager(em);
		util = new JSONUtil();
		b.setUtil(util);
	}
	
	private static final String MOCK_OBJECT = "{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"9999\"}";
	
	@Test
	public void createAccount() {
		String mockAccount = b.createAccount(MOCK_OBJECT);
		Assert.assertEquals(mockAccount, "{“message”: “This account is blocked”}");
	}
}
