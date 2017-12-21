package DataBase;

import Location.Location;

public class WifiLocation implements Comparable<WifiLocation> {

	private int signal;
	private Location location;


	public WifiLocation(){
	}

	public WifiLocation(String signal, Location location) {
		this.signal = (int)Double.parseDouble(signal);
		this.location = location;
	}


	@Override
	public String toString() {
		return "WifiLocation [signal=" + signal + ", location=" + location + "]";
	}
	

	public int compareTo(WifiLocation wifiLocation) {
		if (this.signal> wifiLocation.signal) return 1;
		else if (this.signal< wifiLocation.signal) return -1;
		else return 0;
	}

	public int getSignal() {
		return signal;
	}

	public void setSignal(int signal) {
		this.signal = signal;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}





}
