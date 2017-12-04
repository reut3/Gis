package MyPack;

import java.util.ArrayList;
import java.util.List;

public class Weight {

	int strongestSignalsNum= 3;	
	int approxSignalNum= 4;
	
	public Location sameMac(List<Sample> list, String mac1){
		List <WifiLocation> macs= new ArrayList<WifiLocation>();
		String tempMac="";
		for(int i=0; i<list.size(); i++){
			for(int j=0; j<list.get(i).getListOfWifi().size(); j++){
				tempMac= list.get(i).getListOfWifi().get(j).getMac();
				if(mac1.equals(tempMac)){
					WifiLocation wifiLocation=  new WifiLocation(list.get(i).getListOfWifi().get(j).getRssi(), list.get(i).getLocation());
					macs.add(wifiLocation);
				}
			}
		}
		macs.sort(null);
		if(macs.size()>this.strongestSignalsNum){
			while(macs.size()>this.strongestSignalsNum){
				macs.remove(macs.size()-1);
			}
		}
		
		double sumWalt=0;
		double sumWlat=0;
		double sumWlon=0;
		double sumWweight=0;
		
		for(int i=0; i<macs.size(); i++){		
			double weight= 1/Math.pow(macs.get(i).signal, 2);
			sumWalt +=weight* macs.get(i).location.getAlt().getCord();
			sumWlat +=weight* macs.get(i).location.getLat().getCord();
			sumWlon +=weight* macs.get(i).location.getLon().getCord();
			sumWweight+=weight;
		}
		if(sumWweight!=0){
		sumWalt= sumWalt/sumWweight;
		sumWlat= sumWlat/sumWweight;
		sumWlon= sumWlon/sumWweight;
		}
		Location loc= new Location(sumWlat+"", sumWlon+"", sumWalt+"");
		return loc;
	}

//	public void func(List<Sample> list, List<Integer> signals ,List<String> macs){
//		List<Sample> tempList= new ArrayList<Sample>();
//		for(int i=0; i<macs.size(); i++){
//			int signal= signals.get(i);
//			tempList= filter.MACfilter(list);
//		}	
//	}








}
