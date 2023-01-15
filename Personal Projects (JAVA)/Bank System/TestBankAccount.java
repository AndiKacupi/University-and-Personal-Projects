/*import java.util.Scanner;

class TestBankAccount
{
	public static void main(String[] args){
		BankAccount professorAccount = new BankAccount("Professor");
		BankAccount tokyoAccount = new BankAccount("Tokyo");
		BankAccount denverAccount = new BankAccount("Denver");
		
		professorAccount.deposit(1000);
		professorAccount.withdraw(1200);
		professorAccount.withdraw(500);
				
		tokyoAccount.deposit(500);
		
		// The tokyoAccount issues a check for "Professor" for 1000 euros
		tokyoAccount.issueCheck("Professor", 1000);
		
		denverAccount.deposit(2500);
		
		// The professorAccount issues a check for Tokyo for 300 euros
		BankCheck check1=professorAccount.issueCheck("Tokyo",300);
		
		// If the check is valid (not null) then 
		// first try to deposit it to the denverAccount and then to the tokyoAccount
		if(check1!=null)
		{
		  denverAccount.deposit(check1);
		  tokyoAccount.deposit(check1);
		}

		professorAccount.printStatement();
		tokyoAccount.printStatement();
		denverAccount.printStatement();
	}
}
*/

