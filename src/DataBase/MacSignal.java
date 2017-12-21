package MyPack;

public class MacSignal {

	int signal;
	String mac;
	
	
	public MacSignal(){	
	}
	
	public MacSignal(String signal, String mac) {
		this.signal = (int)Double.parseDouble(signal);
		this.mac = mac;
	}

	@Override
	public String toString() {
		return "MacSignal [signal=" + signal + ", mac=" + mac + "]";
	}
	
	



}
