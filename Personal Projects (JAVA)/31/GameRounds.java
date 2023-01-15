import java.util.Scanner;

class GameRounds
{
    Player[] players = new Player[2];
    int pos;
    Scanner input = new Scanner(System.in);
    String answer;
    public GameRounds(Player player1, Player player2)
    {
        players[0] = player1;
        players[1] = player2;
    }
    
    public void playRound()
    {
        do 
        {
            int playerOneScore = players[0].play(players[1]);
            int playerTwoScore = players[1].play(players[0]);
            
            if(playerOneScore<=31 && playerTwoScore<=31)
            {
            
                 if (playerTwoScore>playerOneScore)
                {
                    players[1].addWin();
                }
                
                 if (playerOneScore>playerTwoScore)
                {
                    players[0].addWin();
                }
                
                if(playerOneScore==playerTwoScore)
                {
                    players[0].addWin();
                }
            }
            
            if(playerTwoScore>31 && playerOneScore<=31)
            {
                players[0].addWin();
            }
            
            if(playerOneScore>31 && playerTwoScore<=31)
            {
                players[1].addWin();
            }
            
            if(playerOneScore>31 && playerTwoScore>31)
            {
                players[1].addWin();
            }
       
            System.out.println("-------------------------------------");
            System.out.println("Do you want to play again?");
            answer=input.next();
            
        }while (answer.equals("yes"));
    }
    
    public void printScore()
    {
        for(int i=0; i<2 ;i++)
        {
            System.out.println(players[i] + " " + players[i].getRoundsWon());
        }
    }
}