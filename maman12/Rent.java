package maman12;

public class Rent {
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
        _name = name;
        _car = car;
        _pickDate = pickDate;
        if (returnDate.after(pickDate))
            _returnDate = returnDate;
        else
            _returnDate = pickDate.tomorrow();
    }

    /**
     * Copy Constructor
     * 
     * @param rent to be copied
     */
    public Rent(Rent other) {
        _name = other.getName();
        _car = other.getCar();
        _pickDate = other.getPickDate();
        _returnDate = other.getReturnDate();
    }

    // getters
    /**
     * Gets the name
     * 
     * @return the name of the renter
     */
    public String getName() {
        return _name;
    }

    /**
     * Gets the car
     * 
     * @return the car rented
     */
    public Car getCar() {
        return _car;
    }

    /**
     * Gets the pick date
     * 
     * @return the date of the pick up
     */
    public Date getPickDate() {
        return _pickDate;
    }

    /**
     * Gets the return date
     * 
     * @return the date of the return
     */
    public Date getReturnDate() {
        return _returnDate;
    }

    // setters

    /**
     * sets the name
     * 
     * @param name the name of the renter
     */
    public void setName(String name) {
        _name = name;
    }

    /**
     * sets the rented car
     * 
     * @param car the rented car
     */
    public void setCar(Car car) {
        _car = car;
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
            _pickDate = pickDate;
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
            _returnDate = returnDate;
    }

    // methods

    /**
     * Check if 2 rents are the same
     * 
     * @param other the other rent to compare to
     * @return true if the rents are the same, false otherwise
     */
    public boolean equals(Rent other) {
        return _name.equals(other.getName()) &&
                _car.equals(other.getCar()) &&
                _pickDate.equals(other.getPickDate()) &&
                _returnDate.equals(other.getReturnDate());
    }

    /**
     * Calculates the number of days the car was rented
     * 
     * @return the number of days the car was rented
     */
    public int howManyDays() {
        return _pickDate.difference(_returnDate);
    }

    /**
     * Calculates the price of the rent
     * 
     * @return the price of the rent
     */
    public int getPrice() {
        final int A_PRICE = 100;
        final int B_PRICE = 150;
        final int C_PRICE = 180;
        final int D_PRICE = 240;

        final int daysRented = this.howManyDays();

        if (daysRented < 7) {
            if (_car.getType() == 'A') {
                return A_PRICE * daysRented;
            } else if (_car.getType() == 'B') {
                return B_PRICE * daysRented;
            } else if (_car.getType() == 'C') {
                return C_PRICE * daysRented;
            } else {
                return D_PRICE * daysRented;
            }
        } else {
            final int weeks = daysRented / 7;
            if (_car.getType() == 'A') {
                return (A_PRICE * daysRented) - (weeks * 7 * A_PRICE);
            } else if (_car.getType() == 'B') {
                return (B_PRICE * daysRented) - (weeks * 7 * B_PRICE);
            } else if (_car.getType() == 'C') {
                return (C_PRICE * daysRented) - (weeks * 7 * C_PRICE);
            } else {
                return (D_PRICE * daysRented) - (weeks * 7 * D_PRICE);
            }
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

    public Rent overlap(Rent other) {

        if (!_name.equals(other.getName()) || !_car.equals(other.getCar())) {
            return null;
        } else {
            // check if the rents overlap
            if (_pickDate.before(other.getReturnDate()) && _returnDate.after(other.getPickDate())) {
                // find the start date
                Date startDate = _pickDate;
                if (other.getPickDate().after(startDate)) {
                    startDate = other.getPickDate();
                }

                // find the end date
                Date endDate = _returnDate;
                if (other.getReturnDate().before(endDate)) {
                    endDate = other.getReturnDate();
                }

                return new Rent(_name, _car, startDate, endDate);
            } else {
                return null;
            }
        }
    }

    /**
     * 
     * @return a string representation of the rent
     */
    public String toString() {
        return "Name:" + _name + " From:" + _pickDate + " To:" + _returnDate + " Type:" + _car.getType() + " Days:"
                + howManyDays() + " Price:" + getPrice();
    }
}
