package TwitterTrends ;

// This class represents a single tweet, which has text, a date it was posted, a number of
// likes, and a number of retweets. A tweet can be liked and retweeted, and can report its
// popularity.
public class Tweet {
    private String text;
    private Date date;
    private int likes;
    private int retweets;

    public Tweet(String text, Date date, int likes, int retweets) {
        if (likes < 0 || retweets < 0) {
            throw new IllegalArgumentException();
        }
        this.text = text;
        this.date = date;
        this.likes = likes;
        this.retweets = retweets;
    }

    public Tweet(String text, String date, int likes, int retweets) {
        this(text, new Date(Integer.parseInt(date.substring(0,2)), 
                Integer.parseInt(date.substring(3, 5)), 
            Integer.parseInt(date.substring(6,10))), likes, retweets);
    }

    public int getLikes() {
        return likes;
    }

    public int getRetweets() {
        return retweets;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public void like() {
        likes++;
    }

    public void retweet() {
        retweets++;
    }

    public int popularity() {
        return likes + 2 * retweets;
    }

    public String toString() {
        return text + " (" + date.toString() + ")";
    }
}