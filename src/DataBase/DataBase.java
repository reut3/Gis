package DataBase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DataBase {
  public Set<Sample> FinalDataBase;
  public Set<Sample> FinalFilterDatabase;

  public DataBase ()
  {
	  FinalDataBase = new HashSet<Sample>();
	  FinalFilterDatabase = new HashSet<Sample>();
  }
	public  void add(Set<Sample> A)
	{
		this.FinalDataBase.addAll(A);
	}
	  public void RemoveAll()
	  {
		  this.FinalDataBase.clear();
		  this.FinalFilterDatabase.clear();
	  }
	  
	

}
