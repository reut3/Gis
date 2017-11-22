package MyPack;

public class Cord {

	@Override
	public String toString() {
		return ""+cord;
	}

	private double cord;

	public Cord(String cordinate){
		double cordinateDouble= Double.parseDouble(cordinate);
		this.cord= cordinateDouble;
	}

	public double getCord() {
		return cord;
	}

	public void setCord(double cord) {
		this.cord = cord;
	}

	public boolean equalCord(Cord other){
		if(other.getCord()==this.cord)
			return true;
		else return false;
	}







}
