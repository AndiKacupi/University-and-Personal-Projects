import java.util.Scanner;

public class HumanPlayer extends Player 
{
    public HumanPlayer(String theName)
    {
        super(theName);
    }
    
    Hand playerHand= this.getPlayerHand();
    
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
}