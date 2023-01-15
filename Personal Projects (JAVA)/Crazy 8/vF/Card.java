class Card
{
  private String colour;
  private int number;
  
  public Card (String theColour, int theNumber)
  {
    if(theNumber<=9 && theNumber>=0)
    {
      colour=theColour;
      number=theNumber;
    }
    else{
      System.out.println("Not valid number! Constructor here!");
    }
  }
  
  public String getCardColour()
  {
    return colour;
  }
  
  public String setCardColour(String theColour)
  {
    return this.colour=theColour;
  }
  
  public String toString()
  {
    return number + "" + colour + " ";
  }
  
  public boolean isEight()
  {
    if (number==8)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  public boolean matches(Card other)
  {
    if(this.number == other.number || this.colour.equals(other.colour))
    {
      return true;
    }
    else
    {
      return false;
    }
  }
}