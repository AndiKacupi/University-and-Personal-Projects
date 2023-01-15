import java.util.Scanner;
public class HumanPlayer extends Player 
{
    Scanner input = new Scanner(System.in);
    public HumanPlayer(String theName)
    {
        super(theName);
    }
    
    public int selectNumber()
    {
        System.out.println("Choose a number between 1 and 5.");
        int numSelected = input.nextInt();
        return numSelected;
        
    }
}