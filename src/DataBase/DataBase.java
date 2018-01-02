package DataBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DataBase {
	public Set<Sample> FinalDataBase;
	public Set<Sample> FinalFilterDatabase;
	public HashMap<String, ArrayList<Sample>> hashMap= new HashMap<String, ArrayList<Sample>>();


	public DataBase(){
		FinalDataBase = new HashSet<Sample>();
		FinalFilterDatabase = new HashSet<Sample>();
		hashMap= new HashMap<String, ArrayList<Sample>>();
		startHash();
	}
	public void add(Set<Sample> A){
		this.FinalDataBase.addAll(A);
		this.FinalFilterDatabase.addAll(A);
		updateHash(A);
	}
	public void RemoveAll()
	{
		this.FinalDataBase.clear();
		this.FinalFilterDatabase.clear();
		this.hashMap.clear();
	}

	public void startHash(){
		this.hashMap.clear();
		ArrayList<Sample> list= new ArrayList<Sample>();
		list.addAll(this.FinalFilterDatabase);
		for(int i=0; i<list.size(); i++){
			for(int j=0; j<list.get(i).getListOfWifi().size(); j++){
				String key= list.get(i).getListOfWifi().get(j).getMac();
				Sample toAdd= list.get(i);

				if (this.hashMap.get(key) == null) {
					this.hashMap.put(key, new ArrayList<Sample>());
				}
				this.hashMap.get(key).add(toAdd);
			}
		}
	}
	
	public void updateHash(Set<Sample> A){
		ArrayList<Sample> list= new ArrayList<Sample>();
		list.addAll(A);
		for(int i=0; i<list.size(); i++){
			for(int j=0; j<list.get(i).getListOfWifi().size(); j++){
				String key= list.get(i).getListOfWifi().get(j).getMac();
				Sample toAdd= list.get(i);

				if (this.hashMap.get(key) == null) {
					this.hashMap.put(key, new ArrayList<Sample>());
				}
				this.hashMap.get(key).add(toAdd);
			}
		}
	}

}
