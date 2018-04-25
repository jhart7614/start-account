package Transaction;

import javax.enterprise.inject.Alternative;
import static javax.transaction.Transactional.TxType.REQUIRED;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Alternative
public class MapRepository implements AccountInterface {
	
	private Map<Long, Account> accountMap = new HashMap<Long, Account>();
	private Long ID;
	
	public MapRepository() {
		
		this.accountMap = accountMap;
		ID = 1L;
	}
	
	@PersistenceContext(unitName = "primary")
    private EntityManager em;
	
	@Inject
    private JSONUtil util;

	@Override
    public String GetAccounts() {
        return util.getJSONForObject(accountMap.values());
    }
    
	@Override
    @Transactional(REQUIRED)
    public String createAccount(String newAccount) {
		ID = ID + 1;
		Account newAccountObj = util.getObjectForJSON(newAccount, Account.class);
		accountMap.put(ID, newAccountObj);
		return "{\"account sucessfully created\"}";
		
    }
    
	@Override
    @Transactional(REQUIRED)
    public String updateAccount(long id, String UpdatedAccountString) {
		Account updatedAccountObj = util.getObjectForJSON(UpdatedAccountString, Account.class);
		if (UpdatedAccountString != null) {
			accountMap.put(id, updatedAccountObj);
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
    		accountMap.remove(AccountToDelete);
    		return "{\"Account Deleted\"}";
		}
		else {
			return "{\"Cant Delete: Account does not exist\"}";
		}
		
    }
}
