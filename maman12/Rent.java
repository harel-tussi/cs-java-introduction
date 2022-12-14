package maman12;

/**
 * Class that represents a rent.
 * 
 * @author Harel Tussi
 * @version 2023a
 */
public class Rent {
    // constants
    final int A_PRICE = 100, B_PRICE = 150, C_PRICE = 180, D_PRICE = 240, WEEK = 7;
    final double WEEK_DISCOUNT = 0.1;

    // instance variables
    private String _name;
    private Car _car;
    private Date _pickDate;
    private Date _returnDate;

    // constructors
    /**
     * creates a new Rent object
     * 
     * @param name       the name of the renter
     * @param car        the car rented
     * @param pickDate   the date of the pick up
     * @param returnDate the date of the return
     */
    public Rent(String name, Car car, Date pickDate, Date returnDate) {
        this._name = name;
        this._car = car;
        this._pickDate = pickDate;
        if (returnDate.after(pickDate))
            this._returnDate = new Date(returnDate);
        else
            this._returnDate = pickDate.tomorrow();
    }

    /**
     * Copy Constructor
     * 
     * @param rent to be copied
     */
    public Rent(Rent other) {
        this(other.getName(), other.getCar(), other.getPickDate(), other.getReturnDate());
    }

    // getters
    /**
     * Gets the name
     * 
     * @return the name of the renter
     */
    public String getName() {
        return this._name;
    }

    /**
     * Gets the car
     * 
     * @return the car rented
     */
    public Car getCar() {
        return this._car;
    }

    /**
     * Gets the pick date
     * 
     * @return the date of the pick up
     */
    public Date getPickDate() {
        return this._pickDate;
    }

    /**
     * Gets the return date
     * 
     * @return the date of the return
     */
    public Date getReturnDate() {
        return this._returnDate;
    }

    // setters

    /**
     * sets the name
     * 
     * @param name the name of the renter
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     * sets the rented car
     * 
     * @param car the rented car
     */
    public void setCar(Car car) {
        this._car = car;
    }

    /**
     * Sets the pick up date.
     * The pick up date must be at least one day before the return date,
     * otherwise - don't change the pick up date
     * 
     * @param pickDate
     */
    public void setPickDate(Date pickDate) {
        if (pickDate.before(_returnDate))
            this._pickDate = pickDate;
    }

    /**
     * Sets the return date.
     * The return date must be at least one day after the pick up date,
     * otherwise - don't change the return date
     * 
     * @param returnDate
     */
    public void setReturnDate(Date returnDate) {
        if (returnDate.after(_pickDate))
            this._returnDate = returnDate;
    }

    // methods

    /**
     * Check if 2 rents are the same
     * 
     * @param other the other rent to compare to
     * @return true if the rents are the same, false otherwise
     */
    public boolean equals(Rent other) {
        return this._name.equals(other.getName()) &&
                this._car.equals(other.getCar()) &&
                this._pickDate.equals(other.getPickDate()) &&
                this._returnDate.equals(other.getReturnDate());
    }

    /**
     * Calculates the number of days the car was rented
     * 
     * @return the number of days the car was rented
     */
    public int howManyDays() {
        return this._pickDate.difference(_returnDate);
    }

    /**
     * Calculates the price of the rent
     * 
     * @return the price of the rent
     */
    public int getPrice() {
        final int daysRented = this.howManyDays();
        int price;
        switch (this._car.getType()) {
            case 'A':
                price = A_PRICE;
                break;
            case 'B':
                price = B_PRICE;
                break;
            case 'C':
                price = C_PRICE;
                break;
            default:
                price = D_PRICE;
        }
        if (daysRented < WEEK) {
            return price * daysRented;
        } else {
            final int weeks = daysRented / WEEK;
            final int pricePerWeek = price * WEEK;
            final double discountPerWeek = pricePerWeek * WEEK_DISCOUNT;
            return (int) ((price * daysRented) - weeks * (discountPerWeek));
        }
    }

    /**
     * upgrades rent car and return new price difference
     * 
     * @param newCar car to upgrade to
     * @return new price difference
     */
    public int upgrade(Car newCar) {
        if (newCar.better(_car)) {
            final int priceBeforeUpdgrade = this.getPrice();
            _car = new Car(newCar);
            return Math.abs(priceBeforeUpdgrade - this.getPrice());
        } else {
            return 0;
        }
    }

    /**
     * Check if there is a double listing of a rent for the
     * same person and car with an overlap in the rental days
     * 
     * @param other the other rent to compare to
     * @return if there is an overlap return new rent otherwise return null
     */
    public Rent overlap(Rent other) {
        // check if the names are the same and the cars are the same
        if (!this._name.equals(other.getName()) || !this._car.equals(other.getCar())) {
            return null;
        }
        // if this rent ends before other starts
        // or other ends before this starts
        else if (this._returnDate.before(other.getPickDate()) || other.getReturnDate().before(this._pickDate)) {
            return null;
        } else {
            // return new rent with the overlap dates
            Date newPickDate = this._pickDate.before(other.getPickDate()) ? this._pickDate : other.getPickDate();
            Date newReturnDate = this._returnDate.after(other.getReturnDate()) ? this._returnDate
                    : other.getReturnDate();
            return new Rent(_name, _car, new Date(newPickDate), new Date(newReturnDate));
        }
    }

    /**
     * 
     * @return a string representation of the rent
     */
    public String toString() {
        return "Name:" + _name + " From:" + _pickDate + " To:" + _returnDate + " Type:" + _car.getType() + " Days:"
                + this.howManyDays() + " Price:" + this.getPrice();
    }
}
