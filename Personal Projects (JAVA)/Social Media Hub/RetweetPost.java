class RetweetPost extends TweetPost 
{
  private TweetPost tweet;
  
  public RetweetPost(String thisName, String thisText, TweetPost thisTweet)
  {
    super(thisName, thisText);
    tweet=thisTweet;
  }
  
  public String toString()
  {
    return super.toString() + " RT " + tweet.toString();
  }
  
}