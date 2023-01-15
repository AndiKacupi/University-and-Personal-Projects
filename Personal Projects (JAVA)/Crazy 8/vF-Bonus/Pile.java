import java.util.Random;
import java.util.ArrayList;

class Pile 
{
  private int capacity;
  private int size =0;
  private Card[] cards = new Card[40];
  private Card[] stack = new Card[40];
  private String[] colours = {"R", "G", "Y", "B"};
  Random rand = new Random();
  
  public void fill() 
  {
    int randNum= rand.nextInt(9);
    int randCol= rand.nextInt(4);
    
    for(int i=0; i<40;i++)
    {
      cards[i]=new Card(colours[randCol],randNum);
      randNum=rand.nextInt(9);
      randCol= rand.nextInt(4);
    }
    
    for(int i=0; i<40;i++)
    {
      stack[size]=cards[i];
      size++;
    }
    shuffle();
  }
  
  public void fill(ArrayList<Card> cardList)
  {
    for(int i=0; i<=cardList.size();i++)
    {
      stack[size]= cardList.get(i);
      size++;
    }
    shuffle();
  }
  
  private void shuffle()
  {
    for(int i=0; i<stack.length;i++)
    {
      int randomStackElement = rand.nextInt(stack.length);
      Card temp = stack[randomStackElement];
      stack[randomStackElement]=stack[i];
      stack[i]=temp;
    }
  }
  
  public Card nextCard()
  {
    if(size==0)
    {
      System.out.println("Stack is empty.");
      return null;
    }
    size--;
    return stack[size];
  }
  
  public boolean isEmpty()
  {
    if(size==0)
    {
      return true;
    }
    
    else 
    {
      return false;
    }
  }
  
  public void print()
  {
    for(int i=0;i<stack.length;i++)
    {
      System.out.print(stack[i]);
    }
    System.out.println(" ");
  }
}