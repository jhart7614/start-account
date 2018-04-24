package com.qa.service;

import static org.junit.Assert.*;

import javax.management.*;
import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

import Transaction.Repository;

@RunWith(MockitoJUnitRunner.class)
public class QueryMockTests {

	
	@InjectMocks
	private Repository repo;
	
	@Mock
	private Query query;
	
	@Mock
	private EntityManager em;
	
	private JSONUtil util;
	
	@Before
	public void setup() {
		repo.setManager(em);
		util = new JSONUtil();
		repo.setUtil(util);
	}
	
	private static final String MOCK_OBJECT = "{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}";
	
	@Test
	public void createAccount( ) {
		String mockAccount = repo.createAccount(MOCK_OBJECT);
		Assert.assertEquals(mockAccount, "{\"account has been sucessfully added\"}");
	}
	
	
}
