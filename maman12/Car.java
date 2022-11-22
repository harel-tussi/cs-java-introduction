package maman12;

/**
 * Car
 */
public class Car {
    // constants
    private final int DEFAULT_ID = 9999999;
    private final char CAR_TYPE_A = 'A';
    private final char CAR_TYPE_B = 'B';
    private final char CAR_TYPE_C = 'C';
    private final char CAR_TYPE_D = 'D';

    // instance variables
    private int _id = DEFAULT_ID;
    private char _type = CAR_TYPE_A;
    private String _brand;
    private boolean _isManual;

    // constructors
    /**
     * creates a new Car
     * 
     * @param id     the car id
     * @param type   the car type
     * @param brand  the car brand
     * @param manual is the car manual
     */
    public Car(int id, char type, String brand, boolean isManual) {
        if (this._isValidId(id))
            this._id = id;
        if (this._isValidType(type))
            this._type = type;
        this._brand = brand;
        this._isManual = isManual;
    }

    /**
     * Copy Constructor
     * 
     * @param car to be copied
     */
    public Car(Car car) {
        this(car.getId(), car.getType(), car.getBrand(), car.isManual());
    }

    // getters
    /** gets the id */
    public int getId() {
        return this._id;
    }

    /** gets the type */
    public char getType() {
        return this._type;
    }

    /** gets the brand */
    public String getBrand() {
        return this._brand;
    }

    /** gets the isManual */
    public boolean isManual() {
        return this._isManual;
    }

    // setters
    /**
     * sets the id
     * 
     * @param id the id to set
     */
    public void setId(int id) {
        if (_isValidId(id))
            this._id = id;
    }

    /**
     * sets the type
     * 
     * @param type the type to set
     */
    public void setType(char type) {
        if (_isValidType(type))
            this._type = type;
    }

    /**
     * sets the brand
     * 
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this._brand = brand;
    }

    /**
     * sets the isManual
     * 
     * @param isManual the isManual to set
     */
    public void setIsManual(boolean manual) {
        this._isManual = manual;
    }

    // methods

    /**
     * prints the car details
     */
    public String toString() {
        return "id:" + this._id + " type:" + this._type + " brand:" + this._brand + " gear:"
                + (this._isManual ? "manual" : "auto");
    }

    /**
     * check if this car is equal to other car
     * 
     * @param car
     * @return true if equal, false otherwise
     */
    public boolean equals(Car other) {
        return this._type == other.getType() &&
                this._brand == other.getBrand() &&
                this._isManual == other.isManual();
    }

    /**
     * check if this car is better than other car
     * 
     * @param other car to compare to
     * @return true if this car is better, false otherwise
     */
    public boolean better(Car other) {
        // return false if this is equal to other
        if (this.equals(other))
            return false;
        // return true if this car type is better than other car type
        else if (this._type > other.getType())
            return true;
        // return false if this car type is worse than other car type
        else if (_type < other.getType())
            return false;
        else {
            if (this._isManual && !other.isManual())
                return true;
            else
                return false;
        }

    }

    /**
     * check if this car is worse than other car
     * 
     * @param other car to compare to
     * @return true if this car is worse, false otherwise
     */
    public boolean worse(Car other) {
        return other.better(this);
    }

    // validators

    /**
     * checks if an id is valid
     * 
     * @param id car id to check
     * @return true if the car id is valid
     */
    private boolean _isValidId(int id) {
        return id >= 1000000 && id <= 9999999;
    }

    /**
     * checks if a type is valid
     * 
     * @param type car type to check
     * @return true if the car type is valid
     * 
     */
    private boolean _isValidType(char type) {
        return type == CAR_TYPE_A || type == CAR_TYPE_B || type == CAR_TYPE_C || type == CAR_TYPE_D;
    }

}
// end of class