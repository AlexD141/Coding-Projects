package TwitterTrends ;
import java.util.*;
import java.io.*;

// This class represents a collection of tweets and analyzes trends among them. A collection
// can be built from a file of tweets and have more tweets added to it. It can print all of its
// tweets as a feed, score each tweet's popularity, find tweets relevant to a given word, count
// how often each word is used in each month, and find the most common word for each month.
public class TwitterTrends {
    private Set<Tweet> tweetCollection;

    public TwitterTrends(String sourceFileName) throws FileNotFoundException{
        tweetCollection = new HashSet<>();
        try (Scanner file = new Scanner(new File(sourceFileName))) {
            while (file.hasNextLine()) {
                String text = file.nextLine();
                if (text.isBlank()) {
                    continue;
                }
                if (!file.hasNextLine()) {
                    break;
                }
                String date = file.nextLine();
                if (!file.hasNextLine()) {
                    break;
                }
                String statistics = file.nextLine();
                try (Scanner lineScan = new Scanner(statistics)) {
                    int likes = Integer.parseInt(lineScan.next());
                    int retweets = Integer.parseInt(lineScan.next());
                    tweetCollection.add(new Tweet(text, date, likes, retweets));
                }
            }
        }
    }

    public void addTweet(Tweet tweet) {
        tweetCollection.add(tweet);
    }

    public void printFeed() {
        for (Tweet tweet : tweetCollection) {
            System.out.println(tweet.getText() + " (" + tweet.getDate() + ")");
            System.out.println("Likes: " + tweet.getLikes() + ", Retweets: " + tweet.getRetweets());
            System.out.println();
        }
    }

    public Map<Tweet, Integer> popularityScores() {
        Map<Tweet, Integer> map = new HashMap<>();
        for (Tweet tweet : tweetCollection) {
            map.put(tweet, tweet.popularity());
        }
        return map;
    }

    public Set<Tweet> relevantTweets(String target, int threshold) {
        Set<Tweet> tweets = new HashSet<> ();
        for (Tweet tweet : tweetCollection) {
            int found = 0;
            Scanner text = new Scanner(tweet.getText());
            while (text.hasNext()) {
                if(text.next().equalsIgnoreCase(target)) {
                    found++;
                }
            }
            if (found >= threshold) {
                tweets.add(tweet);
            }
        }
        return tweets;
    }

    public Map<Integer, Map<String, Integer>> wordCountsByMonth() {
        Map<Integer, Map<String, Integer>> wordCount = new HashMap<> ();
        for (Tweet tweet : tweetCollection) {
            Scanner text = new Scanner(tweet.getText());
            int month = tweet.getDate().getMonth();
            while (text.hasNext()) {
                String word = text.next().toLowerCase();
                if (!wordCount.containsKey(month)) {
                    wordCount.put(month, new HashMap<String, Integer> ());
                }
                if (!wordCount.get(month).containsKey(word)) {
                    wordCount.get(month).put(word, 0);
                }
                wordCount.get(month).put(word, wordCount.get(month).get(word) + 1);
            }
        }
        return wordCount;
    }

    public Map<Integer, String> wordOfTheMonth() {
        Map<Integer, String> highestWordCount = new HashMap<> ();
        Map<Integer, Map<String, Integer>> wordCount = wordCountsByMonth();
        for (int month : wordCount.keySet()) {
            int max = 0;
            String maxWord = "";
            for (String word : wordCount.get(month).keySet()) {
                if (wordCount.get(month).get(word) > max) {
                    max = wordCount.get(month).get(word);
                    maxWord = word;
                }
            }
            highestWordCount.put(month, maxWord);
        }
        return highestWordCount;
    }
}