package MyPack;

public class CordPI implements Comparable<CordPI>{

	Location location;
	Double Pi;
	
	public CordPI(Location location, Double pi) {
		super();
		this.location = location;
		Pi = pi;
	}


	@Override
	public String toString() {
		return "CordPI [location=" + location + ", Pi=" + Pi + "]";
	}


	@Override
	public int compareTo(CordPI data) {
		return this.Pi.compareTo(data.Pi);
	}
	
	
}
