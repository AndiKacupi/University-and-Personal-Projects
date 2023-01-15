import java.util.ArrayList;

class Table
{
  
  private Pile pile;
  private ArrayList<Card> tableCards = new ArrayList<Card>();
  private Card topCard;
  
  public Table(Pile thePile)
  {
    pile=thePile;
    pile.fill();
    topCard=pile.nextCard();
  }
  
  public void throwCard(Card theCard)
  {
    topCard=theCard;
    tableCards.add(topCard);
    //System.out.println(topCard);
  }
  
  public Card drawCard()
  {
    if(pile.isEmpty())
    {
      pile.fill(tableCards);
      return pile.nextCard();
    }
    //System.out.println(pile.nextCard());
    return pile.nextCard();
    
  }
  
  public Card getTopCard()
  {
    //System.out.println(topCard);
    return topCard;
  }
}
