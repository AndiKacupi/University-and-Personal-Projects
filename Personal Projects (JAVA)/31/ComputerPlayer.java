import java.util.Random;

public class ComputerPlayer extends Player 
{
    Random rand = new Random();
    
    public ComputerPlayer(String theName)
    {
        super(theName);
    }
    
    public int selectNumber()
    {
        int randomNumSelected = rand.nextInt(5);
        randomNumSelected++;
        System.out.println("Computer Player selected: " + randomNumSelected);
        return randomNumSelected;
    }
}