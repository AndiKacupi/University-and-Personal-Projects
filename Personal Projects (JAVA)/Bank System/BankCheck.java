
//myCode
class BankCheck
{
  private String payee;
  private int checkAmount;
  
  public BankCheck(String payee, int checkAmount)
  {
    this.payee=payee;
    this.checkAmount=checkAmount;
  }
  
  public String getPayee()
  {
    return payee;
  }
  
  public int getCheckAmount()
  {
    return checkAmount;
  }
  
  public void setPayeeName(String payeeName)
  {
    this.payee=payeeName;
  }
  
  public String toString()
  {
    return "[Check " + payee + ": " + checkAmount + "E]";
  }
  
  public boolean equals(BankCheck other)
  {
    if(this.payee.equals(other.payee) && this.checkAmount==other.checkAmount)
    {
      return true;
    }
    return false;
  }
}