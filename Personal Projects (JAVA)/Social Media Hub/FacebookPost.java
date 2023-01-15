class FacebookPost extends SocialMediaPost
{
  private int likes;
  
  public FacebookPost(String theName, String theText)
  {
    super(theName,theText);
  }
  
  public int getPopularity()
  {
    return likes;
  }
  
  public String getPlatform()
  {
    return "Facebook";
  }
  
  public void addLike()
  {
    likes+=likes+1;
  }
}