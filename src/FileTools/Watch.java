//package FileTools;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.FileSystems;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardWatchEventKinds;
//import java.nio.file.WatchEvent;
//import java.nio.file.WatchKey;
//import java.nio.file.WatchService;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
//
//import DataBase.DataBase;
//import DataBase.Sample;
//import Main.SQL_DB;
//
//public class Watch {
//
//	private WatchService watchServise;
//	private HashMap<WatchKey, Path> keys;
//	private ArrayList<String> paths;
//	private ArrayList<String> FilePaths= new ArrayList<String>();
//	private HashMap<File,Long> LastModified= new HashMap<File,Long>();
//	private List<SQL_DB> SQLtables= new ArrayList<SQL_DB>();
//	private HashMap<SQL_DB,String> LastModifiedTable= new HashMap<SQL_DB,String>();
//
//
//	public Watch() throws IOException{
//		this.watchServise = FileSystems.getDefault().newWatchService();
//		this.keys = new HashMap<>();
//		paths = new ArrayList<String>();
//	}
//
//
//
//
//	/**
//	 * * The function watch the list of directories and checks for changes
//	 * if there is a change- stop watching and update the database and return watching.
//	 * @param database
//	 */
//	public void watchingDirectory(DataBase database){//start watching
//		ExecutorService service = Executors.newCachedThreadPool();
//		service.submit(new Runnable() {
//			public void run() {
//				System.out.println("start watching Directories");
//
//				while(Thread.interrupted()==false && !paths.isEmpty()){//as long as not interrupted
//					WatchKey changed = null;
//
//					try {
//						changed= watchServise.poll(10, TimeUnit.MILLISECONDS);
//					} catch (InterruptedException  e) {
//						System.out.println("stop watching");
//						break;
//					}
//					if(changed!=null){//a changed happened in this watch key
//						Path changeInPath;
//						synchronized(keys){
//							changeInPath= keys.get(changed); //get the path that change occurred in
//							for (WatchEvent<?> i : changed.pollEvents()) {
//								WatchEvent<Path> event = cast(i);
//								WatchEvent.Kind<Path> kind = event.kind();
//								System.out.println(event.context());
//
//								synchronized (paths) {
//									if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
//										File f = event.context().toAbsolutePath().toFile();
//										if(f.isDirectory())
//											paths.remove(changeInPath.toFile().getPath());
//									}
//									try {
//										updateDB(database);
//									} catch (SQLException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//								}
//							}
//						}
//						if(changed.reset()==false){
//							synchronized(keys){
//								keys.remove(changed);
//							}
//						}
//					}
//				}
//			}
//
//		});
//	}

//	/**
//	 * The function add new directory to the watch service.
//	 * @param direct -directory's path as String.
//	 * @throws IOException
//	 */
//	public void directory(String direct) throws IOException{// add new directory to the watch service
//		synchronized (paths) {
//			this.paths.add(direct); //add the path to the arraylist of paths (as String)
//		}
//		Path directory= Paths.get(direct); //get the path of the String
//
//		//register the directory path to be watch in watchServise
//		WatchKey watchKey= 	directory.register(this.watchServise, StandardWatchEventKinds.ENTRY_CREATE,
//				StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
//
//		//adding to the hash map the watch key(the id of the registered directory) with the path
//		synchronized (keys) {
//			keys.put(watchKey, directory);
//		}
//
//	}
//
//
//	/**
//	 * The function watch the list of files and checks for changes
//	 * if there is a change- stop watching and update the database and return watching.
//	 * @param database
//	 */
//	public void watchingFiles(DataBase database){
//		ExecutorService service = Executors.newCachedThreadPool();
//		service.submit(new Runnable() {
//			public void run() {
//				System.out.println("start watching Files");
//				while(Thread.interrupted()==false){
//					synchronized(FilePaths){
//						for(int i=0; i<FilePaths.size(); i++){	
//							Path Directory = Paths.get(FilePaths.get(i));
//							File fileF =Directory.toFile();
//							if(fileF.lastModified()!=LastModified.get(fileF)){
//								try {
//									updateDB(database);
//								} catch (SQLException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//							}
//						}
//					}
//				}
//			}
//		});
//	}
//
//
//	/**
//	 * The function insert the receiving file to a hash map, the key is the file, and it stores the file's last modified 
//	 * @param file
//	 * @throws IOException
//	 */
//	public void File(String file) throws IOException{// add new directory to the watch service
//		synchronized (FilePaths) {
//
//			this.FilePaths.add(file); //add the path to the arraylist of paths (as String)
//		}
//		Path filepath= Paths.get(file); //get the path of the String
//
//		if(Files.exists(filepath)){
//			File fileF = new File(file);
//
//			//		adding to the hash map the watch key(the id of the registered directory) with the path
//			synchronized (LastModified) {
//				LastModified.put(fileF, fileF.lastModified());
//			}
//		}
//	}
//
//
//
//	/**
//	 * The function returns the event that append 
//	 * @param event
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	private static <T> WatchEvent<T> cast(WatchEvent<?> event) {
//		return (WatchEvent<T>) event;
//	}
//
//
//
//
//	/**
//	 * The function watch the list of files and checks for changes
//	 * if there is a change- stop watching and update the database and return watching.
//	 * @param database
//	 */
//	public void watchingSQLtables(DataBase database){
//		ExecutorService service = Executors.newCachedThreadPool();
//		service.submit(new Runnable() {
//			public void run() {
//				System.out.println("start watching SQLtables");
//				while(Thread.interrupted()==false){
//					synchronized(SQLtables){
//						for(int i=0; i<SQLtables.size(); i++){	
//							SQL_DB table= SQLtables.get(i);
//							String update=null;
//							try {
//								update = table.connect(table);
//							} catch (SQLException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//
//							if(!update.equals(LastModifiedTable.get(table))){
//								try {
//									updateDB(database);
//								} catch (SQLException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//							}
//						}
//					}
//				}
//			}
//		});
//	}
//
//
//
//
//	/**
//	 * The function insert the receiving SQL_DB table to a hash map, the key is the table, and it stores the table's last modified 
//	 * @param SQL_DB table
//	 * @throws IOException
//	 */
//	public void SQLtable(SQL_DB table) throws IOException{// add new directory to the watch service
//		synchronized (SQLtables) {
//			this.SQLtables.add(table); //add the path to the array list of paths (as String)
//		}
//
//		synchronized (LastModifiedTable) {
//			LastModifiedTable.put(table, table.getLastModified());
//		}
//
//	}
//
//
//	/**
//	 * The function update the database 
//	 * @param database
//	 * @throws SQLException
//	 */
//	public void updateDB(DataBase database) throws SQLException{
//		synchronized(database){
//			database.RemoveAll();
//			for(int g=0; g<FilePaths.size(); g++){
//				List<Sample> temp= algos.readCSV(FilePaths.get(g));
//				Set<Sample> temp1= new HashSet<Sample>();
//				temp1.addAll(temp);
//				database.add(temp1);
//			}
//			synchronized(paths){
//				for(int g=0; g<paths.size(); g++){
//					CsvFile.readCSV(paths.get(g),database);
//				}
//			}
//
//			synchronized(SQLtables){
//				for(int g=0; g<SQLtables.size(); g++){
//					SQLtables.get(g).insertDB(database);
//				}
//			}
//		}
//	}
//
//
//
//
//}
//
//
//
//
//
//





