import java.util.ArrayList;
class SocialMediaHub
{
  
  private ArrayList<SocialMediaPost> posts = new ArrayList<SocialMediaPost>();
  
  public void addPost(SocialMediaPost post)
  {
    posts.add(post);
  }
  
  public void displayPosts()
  {
    for(SocialMediaPost post:posts)
    {
      post.display();
    }
  }
  
  public void displayMostPopular()
  {
    System.out.println("Most Popular post: ");
    if(posts.size()==0)
    {
      System.out.println("No posts!");
      return;
    }
    SocialMediaPost mostPopualar = posts.get(0);
    for(SocialMediaPost post:posts)
    {
      if(mostPopualar.getPopularity()< post.getPopularity())
      {
        mostPopualar=post;
      }
    }
    mostPopualar.display();
    
  }
}

