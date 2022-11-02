package maman12;

/**
 * Car
 */
public class Car {
    private final int DEFAULT_ID = 9999999;
    private final char DEFAULT_TYPE = 'A';

    private int _id = DEFAULT_ID;
    private char _type = DEFAULT_TYPE;
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
        if (_isValidId(id))
            _id = id;
        if (_isValidType(type))
            _type = type;
        _brand = brand;
        _isManual = isManual;
    }

    /**
     * Copy Constructor
     * 
     * @param car to be copied
     */
    public Car(Car car) {
        _id = car._id;
        _type = car._type;
        _brand = car._brand;
        _isManual = car._isManual;
    }

    // getters

    /** gets the id */
    public int getId() {
        return _id;
    }

    /** gets the type */
    public char getType() {
        return _type;
    }

    /** gets the brand */
    public String getBrand() {
        return _brand;
    }

    /** gets the isManual */
    public boolean isManual() {
        return _isManual;
    }

    // setters

    /**
     * sets the id
     * 
     * @param id the id to set
     */
    public void setId(int id) {
        if (_isValidId(id))
            _id = id;
    }

    /**
     * sets the type
     * 
     * @param type the type to set
     */
    public void setType(char type) {
        if (_isValidType(type))
            _type = type;
    }

    /**
     * sets the brand
     * 
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        _brand = brand;
    }

    /**
     * sets the isManual
     * 
     * @param isManual the isManual to set
     */
    public void setIsManual(boolean manual) {
        _isManual = manual;
    }

    // methods

    /**
     * prints the car details
     */
    public String toString() {
        return "id:" + _id + " type:" + _type + " brand:" + _brand + " gear:" + (_isManual ? "manual" : "auto");
    }

    /**
     * check if this car is equal to other car
     * 
     * @param car
     * @return true if equal, false otherwise
     */
    public boolean equals(Car other) {
        return _type == other.getType() &&
                _brand == other.getBrand() &&
                _isManual == other.isManual();
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
        else if (_type > other.getType())
            return true;
        // return false if this car type is worse than other car type
        else if (_type < other.getType())
            return false;
        else {
            if (_isManual && !other.isManual())
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
        return type == 'A' || type == 'B' || type == 'C' || type == 'D';
    }

}
// end of class