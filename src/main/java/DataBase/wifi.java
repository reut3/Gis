package DataBase;

/**
 * wifi class, implements Comparable interface<br>
 * has 4 Features: <br>
 * String mac;
	String rssi;
	String channel;
	String ssid;<br>
	2 constructor <br>
	3 functions: toString function, compareTo function, convertChannel function
 *
 */

public class wifi implements Comparable<wifi> {

	//wifi features:
	private String mac;
	private String rssi; //signal!
	private String channel;
	private String ssid;

	/**
	 * copy constructor
	 * @param wifi1
	 */
	public wifi(wifi wifi1){
		this.mac=wifi1.mac;
		this.rssi=wifi1.rssi;
		this.channel= wifi1.channel;
		this.ssid=wifi1.ssid;

	}

	/**
	 * explicit constructor
	 * @param sSID
	 * @param mAC
	 * @param rSSI
	 * @param channel1
	 */
	public wifi(String sSID, String mAC, String rSSI, String channel1) {
		ssid = sSID;
		mac = mAC;
		rssi = rSSI;
		channel = channel1;

	}

	/**
	 * empty wifi constructor
	 */
	public wifi(){	
	}




	/**
	 * 
	 * @return wifi's mac
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * set wifi's mac
	 * @param mac
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * 
	 * @return wifi's rssi
	 */
	public String getRssi() {
		return rssi;
	}

	/**
	 * set wifi's rssi
	 * @param rssi
	 */
	public void setRssi(String rssi) {
		this.rssi = rssi;
	}

	/**
	 * 
	 * @return wifi's channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * set wifi's channel
	 * @param channel
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * 
	 * @return wifi's ssid
	 */
	public String getSsid() {
		return ssid;
	}

	/**
	 * set wifi's ssid
	 * @param ssid
	 */
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}


	/**
	 * the function covert channel by calculation
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




	//Override functions:
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		result = prime * result + ((mac == null) ? 0 : mac.hashCode());
		result = prime * result + ((rssi == null) ? 0 : rssi.hashCode());
		result = prime * result + ((ssid == null) ? 0 : ssid.hashCode());
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
		wifi other = (wifi) obj;
		if (channel == null) {
			if (other.channel != null)
				return false;
		} else if (!channel.equals(other.channel))
			return false;
		if (mac == null) {
			if (other.mac != null)
				return false;
		} else if (!mac.equals(other.mac))
			return false;
		if (rssi == null) {
			if (other.rssi != null)
				return false;
		} else if (!rssi.equals(other.rssi))
			return false;
		if (ssid == null) {
			if (other.ssid != null)
				return false;
		} else if (!ssid.equals(other.ssid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  ssid + " , " + mac + " , " + rssi + " , " + channel;
	}

	/**the function compare between this.wifi to another wifi according to their rssi 
	 * return 1 if this.wifi is bigger, -1 if smaller, 0 even
	 */
	@Override
	public int compareTo(wifi wifi1) {
		// TODO Auto-generated method stub
		return this.rssi.compareTo(wifi1.rssi);
	}

}
