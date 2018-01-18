package watch;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import DataBase.DataBase;

public class WatchFile implements watch {
	public ArrayList<String> FilePaths= new ArrayList<String>();//num=1
	public HashMap<File,Long> LastModified= new HashMap<File,Long>(); 


	@Override
	public void addTowatchList(String file,DataBase database) {
		synchronized(database) {
			database.FilePaths.add(file); //add the path to the arraylist of paths (as String)
		}
		synchronized(FilePaths) {
			FilePaths.add(file); //add the path to the arraylist of paths (as String)
		}
		Path filepath= Paths.get(file); //get the path of the String

		if(Files.exists(filepath)){
			File fileF = new File(file);

			//		adding to the hash map the watch key(the id of the registered directory) with the path
			synchronized (LastModified) {
				LastModified.put(fileF, fileF.lastModified());
			}
		}
	}



	@Override
	public void watching(DataBase database) {
		// TODO Auto-generated method stub
		ExecutorService service = Executors.newCachedThreadPool();
		service.submit(new Runnable() {
			public void run() {
				System.out.println("start watching Files");
				while(Thread.interrupted()==false){
					synchronized(database){
						for(int i=0; i<FilePaths.size(); i++){	
							Path Directory = Paths.get(FilePaths.get(i));
							File fileF =Directory.toFile();
							if(fileF.lastModified()!=LastModified.get(fileF)){
								try {
									database.updateDB(database,FilePaths,1);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		});
	}


}
