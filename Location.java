package MyPack;

public class Location{

private Cord lat;
private Cord lon;
private Cord alt;


public Location(String lat, String lon, String alt) {
	
	this.lat = new Cord(lat);
	this.lon = new Cord(lon);
	this.alt = new Cord(alt);
}


public Cord getLat() {
	return lat;
}




public void setLat(Cord lat) {
	this.lat = lat;
}


public Cord getLon() {
	return lon;
}


public void setLon(Cord lon) {
	this.lon = lon;
}


public Cord getAlt() {
	return alt;
}


public void setAlt(Cord alt) {
	this.alt = alt;
}




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
