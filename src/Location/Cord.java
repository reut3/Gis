package Location;


/**
 * Cord class: coordinate object, one attribute: double
 * the class contains 5 functions :
 * toString function, getCord function, setCord function, equalCord function
 * one constructor
 */
public class Cord {

	private double cord;
	
	@Override
	public String toString() {
		return ""+cord;
	}

	/**
	 * Cord Explicit constructor
	 * @param cordinate
	 */
	public Cord(String cordinate){
		double cordinateDouble= Double.parseDouble(cordinate);
		this.cord= cordinateDouble;
	}

	/**
	 * 
	 * @return cord
	 */
	public double getCord() {
		return cord;
	}

	/**
	 * set cord
	 * @param cord
	 */
	public void setCord(double cord) {
		this.cord = cord;
	}

	/**
	 * the function get another cord, checks if this.cord equal to other
	 * @param other cord
	 * @return true if equal, either false
	 */
	public boolean equalCord(Cord other){
		if(other.getCord()==this.cord)
			return true;
		else return false;
	}







}
