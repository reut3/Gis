package MyPack;


public class WifiLocation implements Comparable<WifiLocation> {

	int signal;
	Location location;


	public WifiLocation(){
	}

	public WifiLocation(String signal, Location location) {
		this.signal = Integer.parseInt(signal);
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





}
