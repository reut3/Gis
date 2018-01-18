package watch;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import DataBase.DataBase;

public class WatchFolder implements watch  {
	private WatchService watchServise;
	public HashMap<WatchKey, Path> keys = new HashMap<>();
	public ArrayList<String> paths= new ArrayList<String>();
	
	public WatchFolder() throws IOException{
		this.watchServise = FileSystems.getDefault().newWatchService();
	}
	
	@Override
	public void addTowatchList(String direct,DataBase database) throws IOException {
		
		synchronized (database) {
			database.paths.add(direct); //add the path to the array list of paths (as String)
		}
		
		synchronized (paths) {
			paths.add(direct); //add the path to the array list of paths (as String)
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

	@Override
	public void watching(DataBase database) {
		// TODO Auto-generated method stub
		ExecutorService service = Executors.newCachedThreadPool();
		service.submit(new Runnable() {
			public void run() {
				System.out.println("start watching Directories");

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
									try {
										database.updateDB(database,paths,2);
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
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
	
	@SuppressWarnings("unchecked")
	private static <T> WatchEvent<T> cast(WatchEvent<?> event) {
		return (WatchEvent<T>) event;
	}



	
	
	
	
	
	
	
	
	
	
	
	
}
