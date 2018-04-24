package Transaction;

public interface AccountInterface {
	
	String GetAccounts();

	String createAccount(String newAccount);

	String updateAccount(Long id, String UpdatedAccountString);

	String deleteAccount(String accountStr);

}


