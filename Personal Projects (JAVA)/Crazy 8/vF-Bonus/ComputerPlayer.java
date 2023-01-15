public class ComputerPlayer extends Player 
{
    public ComputerPlayer(String theName)
    {
        super(theName);
    }
    
     Hand playerHand= this.getPlayerHand();
     
     public Card selectCard(Table table)
   {
     playerHand.printHand();
     return playerHand.findMatchingCard(table.getTopCard());
   }
}