package MyPack;

/**
 * wifi class<br>
 * has 4 Features: <br>
 * String MAC;
	String RSSI;
	String Channel;
	String SSID;<br>
	2 constructor <br>
	3 functions: toString function, compareTo function, convertChannel function
 *
 */

public class wifi implements Comparable<wifi> {

	private String mac;
	private String rssi;
	private String channel;
	private String ssid;

	// constructor
	public wifi(wifi wifi1){
		this.mac=wifi1.mac;
		this.rssi=wifi1.rssi;
		this.channel= wifi1.channel;
		this.ssid=wifi1.ssid;

	}
	
	// constructor
	public wifi(String sSID, String mAC, String rSSI, String channel1) {
	ssid = sSID;
	mac = mAC;
	rssi = rSSI;
	channel = channel1;
	
}
	public wifi() {
	}


	@Override
	public String toString() {
		return  ssid + " , " + mac + " , " + rssi + " , " + channel;
	}

	/**
	 * return 1 if bigger, -1 if smaller, 0 even
	 */
	@Override
	public int compareTo(wifi wifi1) {
		// TODO Auto-generated method stub
		return this.rssi.compareTo(wifi1.rssi);
	}
	
	/**
	 * covert channel by calculation
	 * @param channel
	 * @return String contains the channel after the calculation
	 */
	public static String convertChannel(String channel){
		int chan = Integer.parseInt(channel);
		int answer;
		if(chan>=1 && chan<=14){
			answer= (chan-1)*5 +2412;
			return answer+"";
			
		}
		else if(chan>=36 && chan<=165){
			answer= (chan-34)*5 +5170;
			return answer+"";
		}
		else{
			return "";
		}
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getRssi() {
		return rssi;
	}

	public void setRssi(String rssi) {
		this.rssi = rssi;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	
	


}
