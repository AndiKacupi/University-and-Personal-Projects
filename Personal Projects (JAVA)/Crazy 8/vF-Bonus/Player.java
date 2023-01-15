import java.util.Scanner;
public abstract class Player
{
  
   private String playerName;
   private Hand playerHand = new Hand();
   
   public Player(String theName)
   {
     playerName=theName;
   }
   
   public Hand getPlayerHand()
   {
       return playerHand;
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
   
   public abstract Card selectCard(Table table);
}