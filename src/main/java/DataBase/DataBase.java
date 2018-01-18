package DataBase;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import FileTools.CsvFile;
import FileTools.algos;
import Main.SQL_DB;
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
	
	
	public ArrayList<String> FilePaths= new ArrayList<String>();//num=1
	public ArrayList<String> paths= new ArrayList<String>();//num=2	
	public List<SQL_DB> SQLtables= new ArrayList<SQL_DB>();

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
	

	public void updateDB(DataBase database, ArrayList<String> paths, int num) throws SQLException{
		synchronized(database){
			database.RemoveAll();
			if(num==1){
				this.paths=paths;
			}
			for(int g=0; g<FilePaths.size(); g++){
				List<Sample> temp= algos.readCSV(FilePaths.get(g));
				Set<Sample> temp1= new HashSet<Sample>();
				temp1.addAll(temp);
				database.add(temp1);
			}
			synchronized(paths){
				if(num==2){
					this.paths=paths;
				}
				for(int g=0; g<paths.size(); g++){
					CsvFile.readCSV(paths.get(g),database);
				}
			}

			synchronized(SQLtables){
				for(int g=0; g<SQLtables.size(); g++){
					SQLtables.get(g).insertDB(database);
				}
			}
		}
	}
	
	
	public void updateDB1(DataBase database,List<SQL_DB> SQLtables) throws SQLException{
		synchronized(database){
			database.RemoveAll();

			for(int g=0; g<FilePaths.size(); g++){
				List<Sample> temp= algos.readCSV(FilePaths.get(g));
				Set<Sample> temp1= new HashSet<Sample>();
				temp1.addAll(temp);
				database.add(temp1);
			}
			synchronized(paths){
				for(int g=0; g<paths.size(); g++){
					CsvFile.readCSV(paths.get(g),database);
				}
			}

			synchronized(SQLtables){
				this.SQLtables= SQLtables;
				for(int g=0; g<SQLtables.size(); g++){
					SQLtables.get(g).insertDB(database);
				}
			}
		}
	}
	
}
