package Transaction;
import static javax.transaction.Transactional.TxType.REQUIRED;

import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;


@Transactional(SUPPORTS)
public class BusinessImplementation implements AccountInterface {

	@PersistenceContext(unitName = "primary")
    private EntityManager em;
	
	@Inject
    private JSONUtil util;
	
	@Override
    @Transactional(REQUIRED)
    public String createAccount(String newAccount) {
		Account account = util.getObjectForJSON(newAccount, Account.class);
		if (account.getAccountNumber() != "9999") {
		em.persist(account);
		return "{\"account has been sucessfully added\"}";
    }
		else {
			return "{“message”: “This account is blocked”}";

		}
	}
	
	public void setManager(EntityManager manager) {
		this.em = manager;
		
	}
	
	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	@Override
	public String GetAccounts() {
		
		return null;
	}

	@Override
	public String deleteAccount(String accountStr) {
		
		return null;
	}

	@Override
	public String updateAccount(long id, String UpdatedAccountString) {
		
		return null;
	}
}
