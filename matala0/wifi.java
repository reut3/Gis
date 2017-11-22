package MyPack;


public class wifi implements Comparable<wifi> {

	String MAC;
	String RSSI;
	String Channel;
	String SSID;

	
	public wifi(wifi wifi1){
		this.MAC=wifi1.MAC;
		this.RSSI=wifi1.RSSI;
		this.Channel= wifi1.Channel;
		this.SSID=wifi1.SSID;

	}
	
	
	public wifi(String sSID, String mAC, String rSSI, String channel) {
	SSID = sSID;
	MAC = mAC;
	RSSI = rSSI;
	Channel = channel;
	
}

	public wifi() {
	}


	@Override
	public String toString() {
		return  SSID + " , " + MAC + " , " + RSSI + " , " + Channel;
	}

	
	@Override
	public int compareTo(wifi wifi1) {
		// TODO Auto-generated method stub
		return this.RSSI.compareTo(wifi1.RSSI);
	}
	
	public String convertChannel(String channel){
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
	


}
