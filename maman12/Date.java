package maman12;

public class Date {
    final int DEAULT_DAY = 1;
    final int DEAULT_MONTH = 1;
    final int DEAULT_YEAR = 2000;

    private int _day;
    private int _month;
    private int _year;

    // constructors
    /**
     * creates a new Date object
     * 
     * @param _day   the day in the month (1-31)
     * @param _month the month in the year
     * @param _year  the year (in 4 digits)
     */
    public Date(int day, int month, int year) {
        if (this.isValidDate(day, month, year)) {
            _day = day;
            _month = month;
            _year = year;
        } else {
            _day = DEAULT_DAY;
            _month = DEAULT_MONTH;
            _year = DEAULT_YEAR;
        }
    }

    /**
     * Copy Constructor
     * 
     * @param date to be copied
     */
    public Date(Date date) {
        _day = date._day;
        _month = date._month;
        _year = date._year;
    }

    // getters
    /** gets the year */
    public int getYear() {
        return _year;
    }

    /** gets the month */
    public int getMonth() {
        return _month;
    }

    /** gets the Day */
    public int getDay() {
        return _day;
    }

    // setters
    /**
     * sets the year
     * 
     * @param yearToSet the value to be set
     */
    public void setYear(int yearToSet) {
        if (this.isValidDate(_day, _month, yearToSet)) {
            _year = yearToSet;
        }
    }

    /**
     * set the month
     * 
     * @param monthToSet the value to be set
     */
    public void setMonth(int monthToSet) {
        if (this.isValidDate(_day, monthToSet, _year)) {
            _month = monthToSet;
        }
    }

    /**
     * sets the day
     * 
     * @param dayToSet the value to be set
     */
    public void setDay(int dayToSet) {
        if (this.isValidDate(dayToSet, _month, _year)) {
            _day = dayToSet;
        }
    }

    // methods

    /**
     * check if a date is equal to this date
     * 
     * @param other date to be compared to
     */
    public boolean equals(Date other) {
        return _day == other._day && _month == other._month && _year == other._year;
    }

    /**
     * check if a date is before this date
     * 
     * @param other date to be compared to
     */
    public boolean before(Date other) {
        // check if dates are equal
        if (equals(other)) {
            return false;
        }
        return calculateDate(_day, _month, _year) < calculateDate(other._day, other._month, other._year);
    }

    /**
     * check if a date is after this date
     * 
     * @param other date to be compared to
     */
    public boolean after(Date other) {
        return !before(other);
    }

    /**
     * computes the day number since the beginning of the christian counting of
     * years
     * 
     * @param day   the day
     * @param month the month
     * @param year  the year
     * @return the day number
     */
    private int calculateDate(int day, int month, int year) {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year / 4 - year / 100 + year / 400 + (month + 1) * 306 / 10 + (day - 62);
    }

    /**
     * Check if a date is valid
     * 
     * @param day   the day in the month (1-31)
     * @param month the month in the year
     * @param year  the year (in 4 digits)
     * @return true if the date is valid, false otherwise
     */
    private boolean isValidDate(int day, int month, int year) {
        if (day < 1 || day > 31 || month < 1 || month > 12 || year < 0) {
            return false;
        }
        // check if the date is valid
        if (day == 31 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)) {
            return false;
        }
        // check if the date is valid
        if (day == 30 && month == 2) {
            return false;
        }
        // check if the date is valid
        if (day == 29 && month == 2 && year % 4 != 0) {
            return false;
        }

        return true;
    }

}
