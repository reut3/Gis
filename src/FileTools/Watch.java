package FileTools;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


import DataBase.DataBase;
import DataBase.Sample;

public class Watch {

	private WatchService watchServise;
	private HashMap<WatchKey, Path> keys;
	private ArrayList<String> paths;
	private ArrayList<String> FilePaths= new ArrayList<String>();
	private HashMap<File,Long> LastModified= new HashMap<File,Long>();

	public Watch() throws IOException{
		this.watchServise = FileSystems.getDefault().newWatchService();
		this.keys = new HashMap<>();
		paths = new ArrayList<String>();
	}


	public void watching(DataBase database){//start watching
		ExecutorService service = Executors.newCachedThreadPool();
		service.submit(new Runnable() {
			public void run() {
				System.out.println("start watching");

				while(Thread.interrupted()==false && !paths.isEmpty()){//as long as not interrupted
					WatchKey changed = null;

					try {
						changed= watchServise.poll(10, TimeUnit.MILLISECONDS);
					} catch (InterruptedException  e) {
						System.out.println("stop watching");
						break;
					}
					if(changed!=null){//a changed happened in this watch key
						Path changeInPath;
						synchronized(keys){

							changeInPath= keys.get(changed); //get the path that change occurred in
							for (WatchEvent<?> i : changed.pollEvents()) {
								WatchEvent<Path> event = cast(i);
								WatchEvent.Kind<Path> kind = event.kind();
								System.out.println(event.context());

								synchronized (paths) {
									if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
										File f = event.context().toAbsolutePath().toFile();
										if(f.isDirectory())
											paths.remove(changeInPath.toFile().getPath());
									}
									synchronized(database){
										database.RemoveAll();
										synchronized(paths){
											for(int g=0;g<paths.size(); g++){
												CsvFile.readCSV(paths.get(g),database);
											}
										}
										synchronized(FilePaths){
											for(int g=0; g<FilePaths.size(); g++){
												List<Sample> temp= algos.readCSV(FilePaths.get(g));
												Set<Sample> temp1= new HashSet<Sample>();
												temp1.addAll(temp);
												database.add(temp1);
											}
										}
									}
								}
							}
						}
						if(changed.reset()==false){
							synchronized(keys){
								keys.remove(changed);
							}
						}
					}
				}
			}

		});
	}


	/**
	 * The function add new directory to the watch service.
	 * @param direct -directory's path as String.
	 * @throws IOException
	 */
	public void directory(String direct) throws IOException{// add new directory to the watch service
		synchronized (paths) {
			this.paths.add(direct); //add the path to the arraylist of paths (as String)
		}
		Path directory= Paths.get(direct); //get the path of the String

		//register the directory path to be watch in watchServise
		WatchKey watchKey= 	directory.register(this.watchServise, StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

		//adding to the hash map the watch key(the id of the registered directory) with the path
		synchronized (keys) {
			keys.put(watchKey, directory);
		}

	}


	public void File(String file) throws IOException{// add new directory to the watch service
		synchronized (FilePaths) {

			this.FilePaths.add(file); //add the path to the arraylist of paths (as String)
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




	@SuppressWarnings("unchecked")

	private static <T> WatchEvent<T> cast(WatchEvent<?> event) {
		return (WatchEvent<T>) event;
	}


	public void watching1(DataBase database){
		ExecutorService service = Executors.newCachedThreadPool();
		service.submit(new Runnable() {
			public void run() {
				System.out.println("1");
				while(Thread.interrupted()==false)
				{
					synchronized(FilePaths){
						for(int i=0; i<FilePaths.size(); i++)
						{	
							Path Directory = Paths.get(FilePaths.get(i));
							File fileF =Directory.toFile();
							if(fileF.lastModified()!=LastModified.get(fileF))
							{
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
								}
							}
						}
					}
				}
			}
		});
	}

	public void clear(){

		synchronized(FilePaths){
			if(!this.FilePaths.isEmpty())
				this.FilePaths.clear();
		}

		synchronized(LastModified){
			if(!this.LastModified.isEmpty())
				this.LastModified.clear();	
		}
	}







}











