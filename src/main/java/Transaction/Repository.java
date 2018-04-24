package Transaction;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
public class Repository {
	
	@PersistenceContext(unitName = "primary")
    private EntityManager em;
	
	@Inject
    private JSONUtil util;

    public String GetAccounts() {
        Query query = em.createQuery("SELECT m FROM Account m ORDER BY m.firstName DESC");
        Collection<Account> accounts = query.getResultList();
        return util.getJSONForObject(accounts);
    }
    
    @Transactional(REQUIRED)
    public String createAccount(String newAccount) {
		Account account = util.getObjectForJSON(newAccount, Account.class);
		em.persist(account);
		return "{\"account has been sucessfully added\"}";
    }
    
    @Transactional(REQUIRED)
    public String updateAccount(long id, String UpdatedAccountString) {
		Account updatedAccountObj = util.getObjectForJSON(UpdatedAccountString, Account.class);
		Account OriginalAccount = em.find(Account.class, id);
		if (UpdatedAccountString != null) {
			OriginalAccount = updatedAccountObj;
			em.merge(OriginalAccount);
			return "{\"account sucessfully updated\"}";
		}
		else {
			return "{\"Not updated: Original account id does not exist\"}";
		}
    }
  
    @Transactional(REQUIRED)
    public String deleteAccount(String accountStr) {
    	Account AccountToDelete = util.getObjectForJSON(accountStr, Account.class);
    	
    	if (AccountToDelete != null) {
    		em.remove(AccountToDelete);
    		return "{\"Account Deleted\"}";
		}
		else {
			return "{\"Cant Delete: Account does not exist\"}";
		}
		
    }

	public void setManager(EntityManager manager) {
		this.em = manager;
		
	}
	
	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}
