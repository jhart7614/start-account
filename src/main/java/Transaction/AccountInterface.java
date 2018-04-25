package Transaction;

public interface AccountInterface {
	
	String GetAccounts();

	String createAccount(String newAccount);

	String deleteAccount(String accountStr);

	String updateAccount(long id, String UpdatedAccountString);

}


