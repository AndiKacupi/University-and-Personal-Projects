class TestBankAccount
{
	public static void main(String[] args){
		Bank centralBank = new Bank();
		BankAccount professorAccount = new BankAccount("Professor");
		centralBank.addAccount(professorAccount);
		BankAccount tokyoAccount = new BankAccount("Tokyo");
		centralBank.addAccount(tokyoAccount);
		BankAccount denverAccount = new BankAccount("Denver");
		centralBank.addAccount(denverAccount);
		
		professorAccount.deposit(1000);
		professorAccount.withdraw(1200);
		professorAccount.withdraw(500);
				
		tokyoAccount.deposit(500);
		
		denverAccount.deposit(2500);
		
		// The professorAccount issues a check for Tokyo for 300 euros
		// If the check is valid (not null) then 
		// Get the name for the bank account in the check and get the bank account from the bank
		// If the name has a bank account, then deposit the check to this account.
		BankCheck check1=professorAccount.issueCheck("Tokyo",300);
		
		if(check1!=null)
		{
		   String payeeName=check1.getPayee();
		   BankAccount containsAccount = centralBank.getBankAccount(payeeName);
		   if(containsAccount!=null)
		   {
		     tokyoAccount.deposit(check1);
		   }
		   
		}


		centralBank.printAccountStatement("Rio");
		centralBank.printAccountStatement("Professor");
		centralBank.printAccountStatement("Tokyo");
		
    System.out.println("Bank Account: ");
		centralBank.printAccounts();
	}
}