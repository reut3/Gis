package watch;

import java.io.IOException;

import DataBase.DataBase;

public interface watch {
	public void addTowatchList(String path,DataBase database)throws IOException ;
	public void watching(DataBase database);
	
}
