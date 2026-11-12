package TwitterTrends ;

// Represents a date with a day, month, and year. Dates can be converted to and from date strings
// in the format mm-dd-yyyy.
public class Date {
    public static final String[] MONTHS = {"January", "February", "March", "April",
                                           "May", "June", "July", "August", "September",
                                           "October", "November", "December"};
    private int day;
    private int month;
    private int year;

    public Date(int month, int day, int year) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Invalid day entered: " + day);
        }
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month entered: " + month);
        }
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public Date(String dateString) {
        this(Integer.parseInt(dateString.substring(0, 2)),
             Integer.parseInt(dateString.substring(3, 5)),
             Integer.parseInt(dateString.substring(6)));
    }

    public int getDay() { return this.day; }
    public int getMonth() { return this.month; }
    public int getYear() { return this.year; }

    public String toDateString() {
        return String.format("%02d-%02d-%04d", this.month, this.day, this.year);
    }

    public String toString() {
        return MONTHS[month - 1] + " " + this.day + ", " + this.year;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Date) {
            Date otherDate = (Date) other;
            return this.day == otherDate.day &&
                this.month == otherDate.month &&
                this.year == otherDate.year;
        }
        return false;
    }

}