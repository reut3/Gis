package watch;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import DataBase.DataBase;
import DataBase.SQL_DB;

public class WatchSQL {
	
	public List<SQL_DB> SQLtables= new ArrayList<SQL_DB>();
	public HashMap<SQL_DB,String> LastModifiedTable= new HashMap<SQL_DB,String>();
	
	/**
	 * The function watch the list of files and checks for changes
	 * if there is a change- stop watching and update the database and return watching.
	 * @param database
	 */
	public void watchingSQLtables(DataBase database){
		ExecutorService service = Executors.newCachedThreadPool();
		service.submit(new Runnable() {
			public void run() {
				System.out.println("start watching SQLtables");
				while(Thread.interrupted()==false){
					synchronized(SQLtables){
						for(int i=0; i<SQLtables.size(); i++){	
							SQL_DB table= SQLtables.get(i);
							String update=null;
							try {
								update = table.connect(table);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							if(!update.equals(LastModifiedTable.get(table))){
								try {
									database.updateDB1(database,SQLtables);
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




	/**
	 * The function insert the receiving SQL_DB table to a hash map, the key is the table, and it stores the table's last modified 
	 * @param SQL_DB table
	 * @throws IOException
	 */
	public void SQLtable(SQL_DB table,DataBase database) throws IOException{// add new directory to the watch service
		synchronized (database) {
			database.SQLtables.add(table); //add the path to the array list of paths (as String)
		}
		synchronized (LastModifiedTable) {
			LastModifiedTable.put(table, table.getLastModified());
		}
		synchronized (SQLtables) {
			SQLtables.add(table); //add the path to the array list of paths (as String)
		}

	}
}
