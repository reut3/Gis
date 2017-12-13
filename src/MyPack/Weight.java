package MyPack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Weight{

	int strongestSignalsNum= 3;	
	int approxSignalNum= 4;



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
			double weight= 1.0/Math.pow(macs.get(i).signal, 2);
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



	
	
	public static Location findLocation2(List<Sample> sampleList, List<MacSignal> findList, int num){

		List<Sample> samples= filter.filters(sampleList, filter.equalMac(findList));//return Sample list if consist one of the macs;
		Double power=2.0;
		Double norm=10_000.0;
		Double sig_dif= 0.4;
		Double min_diff= 3.0;
		Double no_signal= -120.0;
		Double diff_no_signal= 100.0;
		int macsNum=findList.size();

		Double[] inputSignals= new Double[macsNum];
		for(int i=0; i<macsNum; i++){
			inputSignals[i]= (double) findList.get(i).signal;
		}		
		List <CordPI> datas= new ArrayList<CordPI>();

		for(int i=0; i<samples.size(); i++){

			CordPI temp= new CordPI(samples.get(i).getLocation() ,1.0);

			for(int m=0; m<macsNum; m++){

				Double signal= no_signal; 
				Double diff= diff_no_signal; 
				Double pi= norm/(Math.pow(diff, sig_dif)*Math.pow(findList.get(m).signal, power));

				for(int j=0; j<samples.get(i).getListOfWifi().size() ;j++){	
					if(samples.get(i).getListOfWifi().get(j).getMac().equals(findList.get(m).mac)){
						Double macSignal= Double.parseDouble(samples.get(i).getListOfWifi().get(j).getRssi());
						signal= macSignal; 
						diff= Math.abs(findList.get(m).signal-signal); 
						if(diff==0){
							diff= min_diff;
						}
						pi= norm/(Math.pow(diff, sig_dif)*Math.pow(findList.get(m).signal, power));
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
