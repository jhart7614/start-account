package Transaction;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.*;

import java.security.Provider.Service;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
public class JTA {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;
    
    @Inject
    private JSONUtil util;

    public Account findAccount(Long id) {
        return em.find(Account.class, id);
    }
    
    @Override
    public String getAllAccounts() {
    	
    	Query query = em.createQuery("SELECT m FROM Account m");
    }

    @Transactional(REQUIRED)
    public Account create(Account Account) {
        em.persist(Account.class);
        return Account;   
    }
    
    @Transactional(REQUIRED)
    public Account delete(Account Account) {
        return Account;   
    }
    
    
}

