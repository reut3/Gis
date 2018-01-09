package FileTools;

import java.util.List;

//
//import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
//import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
//import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
//
//import java.io.IOException;
//import java.nio.file.FileSystems;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardWatchEventKinds;
//import java.nio.file.WatchEvent;
//import java.nio.file.WatchKey;
//import java.nio.file.WatchService;
//import java.util.ArrayList;
//
//import DataBase.DataBase;
//
public class WatchDataBase {
	//
	//	public static void watcher(ArrayList<String> paths,DataBase database) throws InterruptedException, IOException{
	//
	//		try{
	//			boolean change= true;
	//			while(change){
	//				//				System.out.println("START MONITORING  **************");
	//				WatchService watchService = FileSystems.getDefault().newWatchService();
	//
	//				for(int i=0; i<paths.size(); i++){
	//					Path path=Paths.get(paths.get(i));
	//					path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
	//				}
	//
	//				boolean valid = true;
	//				WatchKey watchKey = watchService.take();
	//				for (WatchEvent<?> event : watchKey.pollEvents()) {
	//					WatchEvent.Kind kind = event.kind();
	//					if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
	//						String fileName = event.context().toString();
	//						System.out.println(fileName);
	//						change=false;
	//					}
	//					else if (StandardWatchEventKinds.ENTRY_DELETE.equals(event.kind())) {
	//						String fileName = event.context().toString();
	//						System.out.println(fileName);
	//						change=false;
	//
	//					}
	//					else if (StandardWatchEventKinds.ENTRY_MODIFY.equals(event.kind())) {
	//						String fileName = event.context().toString();
	//						System.out.println(fileName);
	//						change=false;
	//					}
	//				}
	//			}
	//			if(change==false){
	//				System.out.println("something changed");
	//				Thread.currentThread().interrupt();
	//				database.FinalDataBase.clear();
	//				for(int i=0; i<paths.size(); i++){
	//					CsvFile.readCSV(paths.get(i),database);
	//				}
	//				watchForChanges(paths,database);
	//			}
	//		}
	//		catch (Exception e){
	//		}
	//	}
	//
	//
	//
	////	public static void watchForChanges(ArrayList<String> paths,DataBase database){
	////		Thread thread=new Thread(new Runnable(){
	////
	////			@Override
	////			public void run() {
	////				try{
	////					//					System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
	////					//					System.out.println("START MONITORING  dad**************");
	////
	////					watcher(paths,database);
	////				}catch (InterruptedException e) {
	////					// TODO Auto-generated catch block
	////					e.printStackTrace();
	////				} catch (IOException e) {
	////					// TODO Auto-generated catch block
	////					e.printStackTrace();
	////				}
	////			}
	////		});thread.start();
	////
	////	}
	//
	//	public static void watchForChanges(ArrayList<String> paths,DataBase database){
	//		WatchService watchService;
	//		try {
	//			watchService = FileSystems.getDefault().newWatchService();
	//		} catch (IOException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//
	//		for(int i=0; i<paths.size(); i++){
	//			Path path=Paths.get(paths.get(i));
	//			try {
	//				path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
	//			} catch (IOException e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//			}
	//		}
	//
	//
	//	}
	public static void main(String[] args) throws Exception{
		List<DataBase.Sample> temp= algos.readCSV("C:\\Users\\reut\\git\\matala\\finalfile.csv");
		System.out.println(temp);
	}
	//
	//
}
//
//
//
//
//
//
//
//
