package DataBase;

import Location.Location;

/**
 * 
 * class WifiLocation- the object contain two features: singal(int), location (Location)
 * contain the functions:
 * implement Comparable
 */
public class WifiLocation implements Comparable<WifiLocation> {

	//WifiLocation features:
	private int signal;
	private Location location;

	/**
	 * WifiLocation's empty constructor
	 */
	public WifiLocation(){
	}

	/**
	 * WifiLocation's Explicit constructor
	 * @param signal
	 * @param location
	 */
	public WifiLocation(String signal, Location location) {
		this.signal = (int)Double.parseDouble(signal);
		this.location = location;
	}

	//override function:
	@Override
	public String toString() {
		return "WifiLocation [signal=" + signal + ", location=" + location + "]";
	}


/**
 * 
 * @return WifiLocation's signal
 */
	public int getSignal() {
		return signal;
	}
	
	/**
	 * sets WifiLocation's signal
	 * @param signal
	 */
	public void setSignal(int signal) {
		this.signal = signal;
	}

	/**
	 * 
	 * @return WifiLocation's location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * sets WifiLocation's location
	 * @param location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * compare between two object of wifiLocation according to their signal<br>
	 * return 1 id bigger, -1 if smaller, 0 if even
	 */
	public int compareTo(WifiLocation wifiLocation) {
		if (this.signal> wifiLocation.signal) return 1;
		else if (this.signal< wifiLocation.signal) return -1;
		else return 0;
	}




}
