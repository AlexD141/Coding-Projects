package TwitterTrends ;
import java.util.*;

// This program tests the Tweet class. It creates a tweet, prints its information, likes and
// retweets it, and prints its updated popularity and string representation.
public class TestingClient {
    public static void main(String[] args) {
        Date date = new Date(1, 11, 2000);
        Tweet tweet = new Tweet("hello", date, 10, 10);
        System.out.println(tweet.getLikes());
        System.out.println(tweet.getRetweets());
        System.out.println(tweet.getText());
        System.out.println(tweet.getDate());
        System.out.println(tweet.popularity());
        tweet.like();
        tweet.retweet();
        System.out.println(tweet.popularity());
        System.out.println(tweet.toString());
    }
}