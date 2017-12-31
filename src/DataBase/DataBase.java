package DataBase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DataBase {
  public static  Set<Sample> FinalDataBase = new HashSet<Sample>();
  public static  Set<Sample> FinalFilterDatabase = new HashSet<Sample>();

	public static  void add(Set<Sample> A)
	{
		FinalDataBase.addAll(A);
		//FinalFilterDatabase=FinalDataBase;
		System.out.println(FinalDataBase.size());
		System.out.println(A.size());	
	}
	  static void RemoveAll(Set<Sample> A)
	  {
		  FinalDataBase.clear();
		  FinalFilterDatabase.clear();
	  }
	  
	

}
