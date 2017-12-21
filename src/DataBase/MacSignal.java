package DataBase;

public class MacSignal {

	private int signal;
	private String mac;
	
	
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

	public int getSignal() {
		return signal;
	}

	public void setSignal(int signal) {
		this.signal = signal;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
	



}
