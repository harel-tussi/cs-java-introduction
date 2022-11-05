package maman12;

public class Date {
    final int DEAULT_DAY = 1;
    final int DEAULT_MONTH = 1;
    final int DEAULT_YEAR = 2000;

    private int _day = DEAULT_DAY;
    private int _month = DEAULT_MONTH;
    private int _year = DEAULT_YEAR;

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
            this._day = day;
            this._month = month;
            this._year = year;
        }
    }

    /**
     * Copy Constructor
     * 
     * @param date to be copied
     */
    public Date(Date date) {
        this(date.getDay(), date.getMonth(), date.getYear());
    }

    // getters
    /** gets the year */
    public int getYear() {
        return this._year;
    }

    /** gets the month */
    public int getMonth() {
        return this._month;
    }

    /** gets the Day */
    public int getDay() {
        return this._day;
    }

    // setters
    /**
     * sets the year
     * 
     * @param yearToSet the value to be set
     */
    public void setYear(int yearToSet) {
        if (this.isValidDate(this._day, this._month, yearToSet)) {
            this._year = yearToSet;
        }
    }

    /**
     * set the month
     * 
     * @param monthToSet the value to be set
     */
    public void setMonth(int monthToSet) {
        if (this.isValidDate(this._day, monthToSet, this._year)) {
            this._month = monthToSet;
        }
    }

    /**
     * sets the day
     * 
     * @param dayToSet the value to be set
     */
    public void setDay(int dayToSet) {
        if (this.isValidDate(dayToSet, this._month, this._year)) {
            this._day = dayToSet;
        }
    }

    // methods

    /**
     * check if a date is equal to this date
     * 
     * @param other date to be compared to
     */
    public boolean equals(Date other) {
        return this._day == other.getDay() && this._month == other.getMonth() && this._year == other.getYear();
    }

    /**
     * check if this date is before other date
     * 
     * @param other date to be compared to
     */
    public boolean before(Date other) {
        // check if dates are equal
        if (this.equals(other)) {
            return false;
        }
        return calculateDate(this._day, this._month, this._year) < calculateDate(other.getDay(), other.getMonth(),
                other.getYear());
    }

    /**
     * check if this date is after other date
     * 
     * @param other date to be compared to
     */
    public boolean after(Date other) {
        return other.before(this);
    }

    /**
     * return the difference between this date and other date
     * 
     * @param other date to be compared to
     * @return the difference between this date and other date in days
     */
    public int difference(Date other) {
        return Math.abs(
                this.calculateDate(this._day, this._month, this._year)
                        - this.calculateDate(other.getDay(), other.getMonth(), other.getYear()));
    }

    /**
     * 
     * @return a string representation of the date in the format dd/mm/yyyy
     */
    public String toString() {
        return this.getFormattedDay() + "/" + this.getFormattedMonth() + "/" + this._year;
    }

    /**
     * calculate tommorow's date
     * 
     * @return tomorrow's date
     */
    public Date tomorrow() {
        int day = this._day;
        int month = this._month;
        int year = this._year;

        if (day == 31 && month == 12) {
            day = 1;
            month = 1;
            year++;
        } else if (day == 31) {
            day = 1;
            month++;
        } else if (day == 30 && (month == 4 || month == 6 || month == 9 || month == 11)) {
            day = 1;
            month++;
        } else if (day == 28 && month == 2 && !this.isLeapYear(year)) {
            day = 1;
            month++;
        } else if (day == 29 && month == 2 && this.isLeapYear(year)) {
            day = 1;
            month++;
        } else {
            day++;
        }

        return new Date(day, month, year);

    }

    /**
     * 
     * @return a day in the format dd
     */
    private String getFormattedDay() {
        return this._day < 10 ? "0" + this._day : "" + this._day;
    }

    /**
     * 
     * @return a month in the format mm
     */
    private String getFormattedMonth() {
        return this._month < 10 ? "0" + this._month : "" + this._month;
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
     * is the year a leap year
     * 
     * @param year
     * @return true if the year is a leap year
     */
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    /**
     * Check if a given date is valid
     * 
     * @param day   the day in the month (1-31)
     * @param month the month in the year
     * @param year  the year (in 4 digits)
     * @return true if the date is valid, false otherwise
     */
    private boolean isValidDate(int day, int month, int year) {
        // check if day, month and year are in range
        if (day < 1 || day > 31 || month < 1 || month > 12 || year < 0) {
            return false;
        }
        // check if long month (31 days) than month must be in [1,3,5,7,8,10,12]
        if (day == 31 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)) {
            return false;
        }
        // check if month is february must not be 30 days
        if (day == 30 && month == 2) {
            return false;
        }
        // check if month is february and year is leap year
        if (day == 29 && month == 2 && !this.isLeapYear(year)) {
            return false;
        }

        return true;
    }

}
