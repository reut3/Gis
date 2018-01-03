package Location;
/**
 * 
 * class CordPI- the object contains two features: location(Location), Pi(double)
 * contains the functions: toString, compareTo
 */
public class CordPI implements Comparable<CordPI>{

	//CordPI features:
	Location location;
	Double Pi;
	
	/**
	 * CordPI's Explicit constructor
	 * @param location
	 * @param pi
	 */
	public CordPI(Location location, Double pi) {
		this.location = location;
		Pi = pi;
	}

	//override function
	@Override
	public String toString() {
		return "CordPI [location=" + location + ", Pi=" + Pi + "]";
	}

	//override function
	@Override
	public int compareTo(CordPI data) {
		return this.Pi.compareTo(data.Pi);
	}
	
	
}
