package Location;

/**
 * Location class: location object contains 3 features:
 * Cord lat, Cord lon, Cord alt
 * the class contains 5 functions :
 * toString function, getCord function, setCord function, equalCord function, distFrom function
 * one constructor
 */

public class Location{

	//location features:
	private Cord lat;
	private Cord lon;
	private Cord alt;


	/**
	 * Location Explicit constructor
	 * @param lat
	 * @param lon
	 * @param alt
	 */
	public Location(String lat, String lon, String alt) {
		this.lat = new Cord(lat);
		this.lon = new Cord(lon);
		this.alt = new Cord(alt);
	}

	/**
	 * 
	 * @return location's lat Cord
	 */
	public Cord getLat() {
		return lat;
	}

	/**
	 * set location's lat Cord
	 * @param lat
	 */
	public void setLat(Cord lat) {
		this.lat = lat;
	}

	/**
	 * 
	 * @return location's lon Cord
	 */
	public Cord getLon() {
		return lon;
	}

	/**
	 * set location's lon Cord
	 * @param lon
	 */
	public void setLon(Cord lon) {
		this.lon = lon;
	}

	/**
	 * 
	 * @return location's alt Cord
	 */
	public Cord getAlt() {
		return alt;
	}

	/**
	 * set location's alt Cord
	 * @param alt
	 */
	public void setAlt(Cord alt) {
		this.alt = alt;
	}



	/**
	 * the functoion checks if this.location equal to another location
	 * checks if all parameters(alt,lon,lat) are equal:
	 * @param location
	 * @return true if equal either false
	 */
	public boolean equal(Location location) {
		if(location.getAlt().equalCord(this.alt) && location.getLat().equalCord(this.lat) && location.getLon().equalCord(this.lon)){
			return true;
		}
		else return false;

	}


	@Override
	public String toString() {
		return "Location [lat=" + lat + ", lon=" + lon + ", alt=" + alt + "]";
	}



	
	





}
