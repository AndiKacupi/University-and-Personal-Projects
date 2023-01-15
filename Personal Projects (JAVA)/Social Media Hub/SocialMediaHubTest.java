import java.util.*;

public class SocialMediaHubTest {
    public static void main(String[] args) {
      
      SocialMediaHub hub1 = new SocialMediaHub();
      FacebookPost fbPost= new FacebookPost("Mark", "Facebook is the best social media platform.");
      hub1.addPost(fbPost);
      fbPost.addLike();
      TweetPost twPost= new TweetPost("@Alice","Good morning world!");
      hub1.addPost(twPost);
      RetweetPost rtwPost= twPost.retweet ("@Bob", "");
      RetweetPost rtwPost1= twPost.retweet ("@Charlie", "Goodmorning Alice!");
      hub1.addPost(rtwPost);
      hub1.addPost(rtwPost1);
      
      hub1.displayPosts();
      hub1.displayMostPopular();
  }
}