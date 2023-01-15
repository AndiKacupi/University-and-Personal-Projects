import java.util.Scanner;

public class Game
{
	public static void main(String[] args) {
	    Player player1 = new HumanPlayer("Mike");
	    Player player2 = new ComputerPlayer("PC");
	    Player player3 = new ComputerPlayer("Super PC");
	    Player winner;
	    
	    System.out.println("Press 1 to play human vs computer or press 2 to play computer vs other computer");
	    Scanner input = new Scanner(System.in);
	    int inp = input.nextInt();
	    
	    if(inp==1)
	    {
	        GameRounds game = new GameRounds(player1,player2);
	        game.playRound();
	        game.printScore();
	        
    	    winner = player1.declareWinner(player2);
    	    if(winner != null)
    	    {
    	        System.out.println("The winner is " + winner);
    	    }
    	    else
    	    {
    	        System.out.println("It is a draw!");
    	    }
	    }
	    
	    if(inp==2)
	    {
	        GameRounds game = new GameRounds(player2,player3);
	        game.playRound();
	        game.printScore();
	    
	    
    	    winner = player2.declareWinner(player3);
    	    if(winner != null)
    	    {
    	        System.out.println("The winner is " + winner);
    	    }
    	    else
    	    {
    	        System.out.println("It is a draw!");
    	    }
	    }

		//System.out.println("Hello World");
	}
}