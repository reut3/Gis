package Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import DataBase.MacSignal;
import DataBase.Sample;
import DataBase.WifiLocation;

/**
 * class Weight- find accurate location of mac or sample <br>
 * hsa two finding location functions:<br>
 * findLocation1 function and findLocation2 function
 */
public class Weight{

	//Weight features:
	int strongestSignalsNum= 4;	
	//	int approxSignalNum= 4;


	/**
	 * The function get mac as string, and list of samples that contain the mac.<br>
	 * <br> the propse of the function is to find the most accurate Location of the mac<br>
	 * The function does calculations with the locations of all the mac in the list, and returning 
	 * the most accurate location of the given mac.
	 * @param list
	 * @param mac1
	 * @return object type Location.
	 */
	public Location findLocation1(List<Sample> list, String mac1){
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
		if(mac1.equals("1c:b9:c4:16:05:38")){
			System.out.println(macs);
		}
		macs.sort(null);
		if(mac1.equals("1c:b9:c4:16:05:38")){
			System.out.println(macs);
		}
		while(macs.size()>this.strongestSignalsNum){
			macs.remove(0);
		}


		double sumWalt=0;
		double sumWlat=0;
		double sumWlon=0;
		double sumWweight=0;

		for(int i=0; i<macs.size(); i++){		
			double weight= 1.0/Math.pow(macs.get(i).getSignal(), 2);
			sumWalt +=weight* macs.get(i).getLocation().getAlt().getCord();
			sumWlat +=weight* macs.get(i).getLocation().getLat().getCord();
			sumWlon +=weight* macs.get(i).getLocation().getLon().getCord();
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



	/**
	 * Given number of WiFi samples and signal strength the function calculate the accurate location of the user.
	 * The fuctions use calculation of weighted center of gravity, 
	 * combined with a method to test the suitability of each sample to our input,
	 * each of the samples can be graded relative to the similarity of our input (MacSignal list),
	 * the functions will take a number (int num) of samples that are the most similar,
	 * And will calculate their weighted center of gravity which will find the most accurate location.
	 * @param sampleList
	 * @param findList
	 * @param num
	 * @return Location.
	 */
	public static Location findLocation2(Set<Sample> sampleList, List<MacSignal> findList, int num){
		List<Sample> samples = new ArrayList<Sample>();
		samples.addAll(sampleList);


		Double power=2.0;
		Double norm=10_000.0;
		Double sig_dif= 0.4;
		Double min_diff= 3.0;
		Double no_signal= -120.0;
		Double diff_no_signal= 100.0;
		int macsNum=findList.size();

		Double[] inputSignals= new Double[macsNum];
		for(int i=0; i<macsNum; i++){
			inputSignals[i]= (double) findList.get(i).getSignal();
		}		
		List <CordPI> datas= new ArrayList<CordPI>();

		for(int i=0; i<samples.size(); i++){

			CordPI temp= new CordPI(samples.get(i).getLocation() ,1.0);

			for(int m=0; m<macsNum; m++){

				Double signal= no_signal; 
				Double diff= diff_no_signal; 
				Double pi= norm/(Math.pow(diff, sig_dif)*Math.pow(findList.get(m).getSignal(), power));

				for(int j=0; j<samples.get(i).getListOfWifi().size() ;j++){	
					if(samples.get(i).getListOfWifi().get(j).getMac().equals(findList.get(m).getMac())){
						Double macSignal= Double.parseDouble(samples.get(i).getListOfWifi().get(j).getRssi());
						signal= macSignal; 
						diff= Math.abs(findList.get(m).getSignal()-signal); 
						if(diff==0){
							diff= min_diff;
						}
						pi= norm/(Math.pow(diff, sig_dif)*Math.pow(findList.get(m).getSignal(), power));
						break;
					}
				}
				temp.Pi= temp.Pi*pi;
			}
			datas.add(temp);
		}
		datas.sort(null);//datas kind is CordPI


		while(datas.size()>num){
			datas.remove(0);
		}

		double weight=0;
		double sumWalt=0;
		double sumWlat=0;
		double sumWlon=0;

		for(int i=0; i<datas.size();i++){
			weight+=datas.get(i).Pi;
			sumWalt+= datas.get(i).Pi*datas.get(i).location.getAlt().getCord();
			sumWlat+= datas.get(i).Pi*datas.get(i).location.getLat().getCord();
			sumWlon+= datas.get(i).Pi*datas.get(i).location.getLon().getCord();
		}

		if(weight!=0){
			sumWalt= sumWalt/weight;
			sumWlat= sumWlat/weight;
			sumWlon= sumWlon/weight;
		}
		Location loc= new Location(sumWlat+"", sumWlon+"", sumWalt+"");
		return loc;

	}













}
