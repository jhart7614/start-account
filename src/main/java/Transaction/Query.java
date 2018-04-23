package Transaction;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.qa.domain.Account;

public class Query {
	
	@PersistenceContext(unitName = "primary")
    private EntityManager em;

    public List <Account> findAllMovies() {
        TypedQuery<Account> query = em.createQuery("SELECT m FROM Account m ORDER BY m.firstName DESC", Account.class);
        return query.getResultList();
    }
  
}
