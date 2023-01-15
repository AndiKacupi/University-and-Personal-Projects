import java.util.ArrayList;

//myCode
class BankAccount
{
	private String name;
	private double balance;
	ArrayList<String> myList = new ArrayList<String>();
	
	public BankAccount(String name){
		this.name = name;
	}
	
	public void deposit(double amount)
	{
		balance += amount;
		myList.add("Deposit of " + amount + " euros.");
	}
	
	public boolean deposit(BankCheck check)
	{
	  if(this.name.equals(check.getPayee()))
	  {
	    //int checkAm=check.getCheckAmount();
	    balance=balance+check.getCheckAmount();
	    myList.add("Deposit of check: " + check);
	    return true;
	  }
	  return false;
	}
	
	public double withdraw(double amount){
		if (balance < amount){
			return 0;
		}else{
			balance -= amount;
			myList.add("Withdrawal of " + amount + " euros.");
			return amount;
		}
	}
	
	public String getName(){
		return name;
	}
	
	public String toString()
	{
	  return name+" account";
	}
	
	public BankCheck issueCheck(String name, int amount)
	{
	  if(balance<amount)
	  {
	    return null;
	  }
	  balance-=amount;
	  myList.add("Issued check to " +name + " for " + amount + " euros.");
	  return new BankCheck(name, amount);
	}
	
		public void printStatement(){
		System.out.println(name+" account:");
		System.out.println("The balance in the account is "+balance+" euros");
		System.out.println("Transactions: ");
		for(int i=0;i<myList.size();i++)
		{
		  System.out.println(myList.get(i));
		}
	}
	
}