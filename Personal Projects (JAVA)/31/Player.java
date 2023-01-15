public abstract class Player
{
    private String playerName;
    private int roundsWon;
    
    public Player(String theName)
    {
        playerName=theName;
    }
    
    public int getRoundsWon()
    {
        return roundsWon;
    }
    
    public abstract int selectNumber();
    
    public int play(Player otherPlayer)
    {
        int total = 0;
        while( total <= 26)
        {
            int myThrow = this.selectNumber();
            int otherThrow = otherPlayer.selectNumber();
            total = total+myThrow+otherThrow;
        }
        
        System.out.println("Total points for the player: " + total);
        return total;
    }
    
    public Player declareWinner(Player otherP)
    {
        if(this.roundsWon > otherP.getRoundsWon())
        {
            return this;
        }
        else if (otherP.getRoundsWon() > this.roundsWon)
        {
            return otherP;
        }
        else 
        {
            return null;
        }
    }
    
    public String toString()
    {
        return playerName + "";
    }
    
    public void addWin()
    {
        roundsWon++;
    }   
}