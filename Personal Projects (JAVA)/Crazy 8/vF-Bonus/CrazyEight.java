import java.util.Scanner;

class CrazyEight 
{
  private Table theTable;
  private Player[] players;
  private int numOfPlayers;
  
  Scanner input = new Scanner(System.in);
  String playersN = input.nextLine();
  public CrazyEight(int playersNum)
  {
    System.out.println("Give me the players name:");
    players=new Player[playersNum];
    players[0] = new HumanPlayer(playersN);
    playersN = input.nextLine();
    players[1] = new ComputerPlayer(playersN);
  }
  
  public void play()
  {
    Pile pile = new Pile();
    theTable = new Table (pile);
    
    players[0].draw(theTable,8);
    players[1].draw(theTable,8);

    while(!players[0].isDone() || !players[1].isDone())
    {
      Card topCard = theTable.getTopCard();
      System.out.println("-----------------------------");
      System.out.println("Human Turn: ");
      System.out.println("Table top card: " + topCard);

      Card card1 = players[0].selectCard(theTable);
      if(card1 != null)
      {
        players[0].throwCard(theTable,card1);
        System.out.println("Human threw: " + card1);
      }
      else
      {
        players[0].draw(theTable);
        System.out.println("Human drew a card!");
      }
      
      if(players[0].isDone())
      {
          System.out.println("-----------------------------");
          System.out.println("!!!Human Player WIN!!!");
          System.out.println("-----------------------------");
          break;
      }
      
      topCard = theTable.getTopCard();
      System.out.println("-----------------------------");

      System.out.println("Computer Turn: ");
      System.out.println("Table top card: " + topCard);

      Card card2 = players[1].selectCard(theTable);
      
      if(card2!=null)
      {
        players[1].throwCard(theTable,card2);
        System.out.println("Computer threw: " + card2);
        
      }
      
      else
      {
        players[1].draw(theTable);
        System.out.println("Computer drew a card!");
      }
      
      if(players[1].isDone())
      {
          System.out.println("-----------------------------");
          System.out.println("!!!Computer Player WIN!!!");
          System.out.println("-----------------------------");

          break;
      }
    } 
  }
}