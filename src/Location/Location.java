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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alt == null) ? 0 : alt.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lon == null) ? 0 : lon.hashCode());
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
		Location other = (Location) obj;
		if (alt == null) {
			if (other.alt != null)
				return false;
		} else if (!alt.equals(other.alt))
			return false;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (lon == null) {
			if (other.lon != null)
				return false;
		} else if (!lon.equals(other.lon))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Location [lat=" + lat + ", lon=" + lon + ", alt=" + alt + "]";
	}



	
	





}
