import java.util.Scanner;
class Player
{
  
   private String playerName;
   private Hand playerHand = new Hand();
   
   public Player(String theName)
   {
     playerName=theName;
   }
   
   public void draw(Table table)
   {
     Card helper = table.drawCard();
     playerHand.addCard(helper);
     //System.out.println(helper);
   }
   
  public void draw(Table table, int cardNum)
   {
     for(int i =0 ; i<cardNum;i++)
     {
      Card helper = table.drawCard();
      playerHand.addCard(helper);
      //System.out.println(helper);
     }  
   }
   
   public void throwCard(Table table, Card thecard)
   {
     table.throwCard(thecard);
     playerHand.removeCard(thecard);
   }
   
   public boolean isDone()
   {
     if(playerHand.isEmpty())
     {
       return true;
     }
     return false;
   }
   
   public String toString()
   {
     return playerName + "";
   }
   
   public Card selectCard(Table table)
   {
     playerHand.printHand();
     System.out.println("Choose card:");
     Scanner input= new Scanner (System.in);
     int cardPos = input.nextInt();
     
     if (cardPos != (-1))
     {
        Card helpC =  playerHand.getCard(cardPos);
        if(helpC.isEight())
        {
          System.out.println("Decide the colour");
          String colHelp = input.next();
          helpC.setCardColour(colHelp);
          return helpC;
        }
        else
        {
          if(helpC.matches(table.getTopCard()))
          {
            return helpC;
          }
          else
          {
              return null;
          }
        }
     }
     else
     {
        return null;
     }
     
   }
   
   public Card computerSelectCard(Table table)
   {
     playerHand.printHand();
     return playerHand.findMatchingCard(table.getTopCard());
   }
}
