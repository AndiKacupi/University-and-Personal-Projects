class TweetPost extends SocialMediaPost
{
  private int retweets;
  
  public TweetPost(String theName, String theText)
  {
    super(theName,theText);
  }
  
  public int getPopularity()
  {
    return retweets;
  }
  
  public String getPlatform()
  {
    return "Twitter";
  }
  
  public RetweetPost retweet(String name, String text)
  {
    retweets+=+1 ;
    return new RetweetPost(name,text,this);
  }
  
}