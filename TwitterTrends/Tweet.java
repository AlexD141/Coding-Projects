package TwitterTrends ;

//Alexandre Dauche
//11/10/2026
//CSE 122
//P1: Twiter trends
//TA: Naomi Alanna Morato

// This class represents a single tweet, which has text, a date it was posted, a number of
// likes, and a number of retweets. A tweet can be liked and retweeted, and can report its
// popularity.
public class Tweet {
    private String text;
    private Date date;
    private int likes;
    private int retweets;

    //B: Constructs a tweet with the given text, date, likes, and retweets.
    //E: If likes or retweets is negative, an IllegalArgumentException is thrown.
    //R: None
    //P: text: the text of the tweet
    //     date: the date the tweet was posted
    //     likes: the tweet's starting number of likes
    //     retweets: the tweet's starting number of retweets
    public Tweet(String text, Date date, int likes, int retweets) {
        if (likes < 0 || retweets < 0) {
            throw new IllegalArgumentException();
        }
        this.text = text;
        this.date = date;
        this.likes = likes;
        this.retweets = retweets;
    }

    //B: Constructs a tweet with the given text, date, likes, and retweets, where the date is
    //     given as a string.
    //E: If likes or retweets is negative, an IllegalArgumentException is thrown.
    //R: None
    //P: text: the text of the tweet
    //     date: the date the tweet was posted, formatted as "MM/DD/YYYY"
    //     likes: the tweet's starting number of likes
    //     retweets: the tweet's starting number of retweets
    public Tweet(String text, String date, int likes, int retweets) {
        this(text, new Date(Integer.parseInt(date.substring(0,2)), 
                Integer.parseInt(date.substring(3, 5)), 
            Integer.parseInt(date.substring(6,10))), likes, retweets);
    }

    //B: Gets the number of likes this tweet has.
    //E: None
    //R: int: the number of likes this tweet has
    //P: None
    public int getLikes() {
        return likes;
    }

    //B: Gets the number of retweets this tweet has.
    //E: None
    //R: int: the number of retweets this tweet has
    //P: None
    public int getRetweets() {
        return retweets;
    }

    //B: Gets the text of this tweet.
    //E: None
    //R: String: the text of this tweet
    //P: None
    public String getText() {
        return text;
    }

    //B: Gets the date this tweet was posted.
    //E: None
    //R: Date: the date this tweet was posted
    //P: None
    public Date getDate() {
        return date;
    }

    //B: Adds one like to this tweet.
    //E: None
    //R: None
    //P: None
    public void like() {
        likes++;
    }

    //B: Adds one retweet to this tweet.
    //E: None
    //R: None
    //P: None
    public void retweet() {
        retweets++;
    }

    //B: Calculates this tweet's popularity score.
    //E: None
    //R: int: this tweet's popularity score, equal to its number of likes plus twice its
    //     number of retweets
    //P: None
    public int popularity() {
        return likes + 2 * retweets;
    }

    //B: Creates a string representation of this tweet.
    //E: None
    //R: String: this tweet's text followed by its date in parentheses
    //P: None
    public String toString() {
        return text + " (" + date.toString() + ")";
    }
}