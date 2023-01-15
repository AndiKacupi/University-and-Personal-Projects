import java.util.HashMap;
import java.util.ArrayList;

class Hand
{
  private ArrayList<Card> playerHand = new ArrayList<Card>();
  private HashMap<String,Integer> myMap = new HashMap<String,Integer>();
  
  public Hand()
  {
    myMap.put("R",0);
    myMap.put("G",0);
    myMap.put("Y",0);
    myMap.put("B",0); 
  }
  
  public void addCard(Card theCard)
  {
    playerHand.add(theCard);
    if(!myMap.containsKey(theCard.getCardColour()))
    {
      myMap.put(theCard.getCardColour(),1);
    }
    else
    {
      myMap.put(theCard.getCardColour(),myMap.get(theCard.getCardColour())+1);
    }
  }
  
  public void removeCard(Card theCard)
  {
    playerHand.remove(theCard); 
  }
  
  public Card getCard(int cardPos)
  {
    return playerHand.get(cardPos);
  }
  
  public void printHand()
  {
    int i=0;
    for(Card card:playerHand)
    {
      System.out.println(i +": "+ card);
      i++;
    }
    //printColoursHashMap();  // to see the colours and their number in the players name 
    if(playerHand.size()==0)
    {
      System.out.println("Empty Hand");
    }
  }
  
  private void printColoursHashMap()
  {
    for(String colours: myMap.keySet())
    {
      System.out.println(colours +": "+ myMap.get(colours));
    }
  }
  
  private String dominantColour()
  {
      int help =0;
      String helper="";
      for(String colours:myMap.keySet())
      {
        if(myMap.get(colours)>help)
        {
            help = myMap.get(colours);
            helper=colours;
        }
      }
      return helper; 
  }
  
  public boolean isEmpty()
  {
    if(playerHand.size() == 0)
    {
      return true;
    }
    return false;
  }
  
  /*public Card findMatchingCard(Card otherC)
  {
      for (int i=0; i<playerHand.size(); i++)
      {
        Card helperC = playerHand.get(i);
        if(helperC.matches(otherC) && !(helperC.isEight()))
        {
          myMap.put(helperC.getCardColour(),myMap.get(helperC.getCardColour())-1);
          return helperC;
        }
        else if (helperC.isEight())
        {
          helperC.setCardColour(dominantColour());
          System.out.println("helperC changed colour to " + dominantColour());
          myMap.put(helperC.getCardColour(),myMap.get(helperC.getCardColour())-1);
          return helperC;
        }
      }
      return null;
  }*/
 
  public Card findMatchingCard(Card otherCard)
  {
      ArrayList<Card> arrayHelp = new ArrayList<Card>();
      HashMap<String,Integer> mapHelp = new HashMap<String,Integer>();

      
      for(int i=0; i <playerHand.size();i++)
      {
         Card helpCard = playerHand.get(i);
         if(helpCard.matches(otherCard) && !(helpCard.isEight()))
         {
             arrayHelp.add(helpCard);
             String domCol = helpCard.getCardColour();
            if(!mapHelp.containsKey(helpCard.getCardColour()))
            {
              mapHelp.put(helpCard.getCardColour(),1);
            }
            else
            {
              mapHelp.put(helpCard.getCardColour(),mapHelp.get(helpCard.getCardColour())+1);
            }
          } 
      }
      
      int help =0;
      String helper="";
      for(String colours:mapHelp.keySet())
      {
        if(mapHelp.get(colours)>help)
        {
            help = mapHelp.get(colours);
            helper=colours;
        }
      }
      
      for( int j =0; j<playerHand.size();j++)
      {
          Card helpCard1 = playerHand.get(j);
          if(helpCard1.matches(otherCard) && !(helpCard1.isEight()))
          {
            String domCol = helpCard1.getCardColour();
            if(domCol.equals(helper))
            {
                 return helpCard1;
            }
          }
      }
      
      for( int i=0 ; i<playerHand.size();i++)
      {
        Card helpCard = playerHand.get(i);
        if (helpCard.isEight())
        {
           helpCard.setCardColour(dominantColour());
           System.out.println("Computer player changed colour to " + dominantColour());
           myMap.put(helpCard.getCardColour(),myMap.get(helpCard.getCardColour())-1);
           return helpCard;
        }
      }
      return null;
    }
}