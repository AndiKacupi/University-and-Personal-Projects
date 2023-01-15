import java.util.HashMap;

class Bank  {
    
    private HashMap<String, BankAccount> myMap = new HashMap<String,BankAccount>();
    
    public void addAccount(BankAccount account)
    {
      String accName=account.getName();
      if(myMap.containsKey(accName))
      {
        System.out.println("There is already an account registered to this name.");
        return ;
      }
      myMap.put(accName,account);
    }
    
    public void printAccountStatement(String name)
    {
      if(myMap.containsKey(name))
      {
        myMap.get(name).printStatement();
        return;
      }
      System.out.println("No account for " +name);
    }
    
    public void printAccounts()
    {
      for( String name:myMap.keySet())
      {
        System.out.println (myMap.get(name));
      }
    }
    
    public BankAccount getBankAccount(String name)
    {
      for(String accNames:myMap.keySet())
      {
        if (accNames.equals(name))
        {
          BankAccount acc= myMap.get(name);
          return acc;
        }
      }
      return null;
    }

    /* better way
    public BankAccount getBankAccount(String name)
    {
        if (myMap.containsKey(name))
        {
          //BankAccount acc= myMap.get(name);
          return myMap.get(name);
        }
        return null;
    }*/
}