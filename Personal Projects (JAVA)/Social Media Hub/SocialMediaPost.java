abstract class SocialMediaPost
{
  private String username;
  private String text;
  
  public SocialMediaPost (String theUsername, String theText)
  {
    username=theUsername;
    text=theText;
  }
  
  public abstract String getPlatform();
  
  public abstract int getPopularity();
  
  public String toString()
  {
    return username  + ":" + text;
  }
  
  public void display()
  {
    System.out.println(getPlatform()+ " post:\n" + toString() + "\n" + "Popularity: " + getPopularity());
  }
}
