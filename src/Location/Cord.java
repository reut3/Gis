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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cord);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cord other = (Cord) obj;
		if (Double.doubleToLongBits(cord) != Double.doubleToLongBits(other.cord))
			return false;
		return true;
	}






}
