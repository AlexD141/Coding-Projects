package TwitterTrends ;
import java.util.*;
import java.io.*;

// This program reads a collection of tweets from a file and prints a summary of trends
public class TwitterMain {
    public static final String TWEETS_PATH = "tweet_info.txt";
    public static final int FEBRUARY = 2;

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0) {
            printTotalTrends(args[0]);
        } else {
            printTotalTrends();
        }
    }

    public static void printTotalTrends() throws FileNotFoundException {
        File tweetsFile = new File(TWEETS_PATH);
        if (!tweetsFile.isFile()) {
            File projectFile = new File("TwitterTrends", TWEETS_PATH);
            if (projectFile.isFile()) {
                tweetsFile = projectFile;
            }
        }
        printTotalTrends(tweetsFile.getPath());
    }

    private static void printTotalTrends(String sourceFileName) throws FileNotFoundException {
        TwitterTrends trend = new TwitterTrends(sourceFileName);

        int totalRelevant = 0;
        totalRelevant = trend.relevantTweets("#cat", 2).size();
        
        System.out.println("Total relevant tweets found: " + totalRelevant);
        
        int totalPopularity = 0;
        Map<Tweet, Integer> popular = trend.popularityScores();
        for (int popularity : popular.values()) {
            totalPopularity += popularity;
        }
        System.out.println("Total popularity of all tweets: " + totalPopularity);

        String februaryWord = "";
        Map<Integer, String> words = trend.wordOfTheMonth();
        februaryWord = words.get(2);

        System.out.println("Word of the month for February: " + februaryWord);
    }
}