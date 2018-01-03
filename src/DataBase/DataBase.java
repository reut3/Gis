package DataBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
/**
 * 
 * The class contains database object- which contains information stored in two sets of Samples.<br>
 * one set is for all the receiving info and the other is for storing the filtered info.<br>
 * the object also contains hashmap that store samples according to macs.
 * The class contains some functions: add, removeall, starthash, updatehash
 *
 */
public class DataBase {
	public Set<Sample> FinalDataBase;
	public Set<Sample> FinalFilterDatabase;
	public HashMap<String, ArrayList<Sample>> hashMap= new HashMap<String, ArrayList<Sample>>();

/**
 * empty constructor
 */
	public DataBase(){
		FinalDataBase = new HashSet<Sample>();
		FinalFilterDatabase = new HashSet<Sample>();
		hashMap= new HashMap<String, ArrayList<Sample>>();
		startHash();
	}
	
	/**
	 * The function add receiving set of samples to the dataBase and call updateHash function
	 * @param A- set of samples
	 */
	public void add(Set<Sample> A){
		this.FinalDataBase.addAll(A);
		this.FinalFilterDatabase.addAll(A);
		updateHash(A);
	}
	
	/**
	 * The functions clear the object
	 */
	public void RemoveAll(){
		this.FinalDataBase.clear();
		this.FinalFilterDatabase.clear();
		this.hashMap.clear();
	}

	/**
	 * The function build hash map from the filtered dataBase
	 */
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
	
	/*
	 * The function update the dataBase's hash map.
	 */
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
