package Filter;

import java.io.Serializable;
import java.util.function.Predicate;

import DataBase.DataBase;
import DataBase.Sample;

public class CheckFilter {




	public static void WhichOP(String s,DataBase database)
	{

		String[] WhatShouldIdo=s.split(",");
		switch(WhatShouldIdo[0])
		{
		case "and":
			if (WhatShouldIdo[1].equals("ID") && WhatShouldIdo[4].equals("Date"))
			{
				database.FinalFilterDatabase.addAll(Filter.filter.filterAnd(database.FinalDataBase, Filter.filter.equalId(WhatShouldIdo[2],WhatShouldIdo[3]), Filter.filter.equalTime(WhatShouldIdo[5], WhatShouldIdo[6],WhatShouldIdo[7])));				
			}
			else if (WhatShouldIdo[1].equals("ID") && WhatShouldIdo[4].equals("Location"))
			{
				database.FinalFilterDatabase.addAll(Filter.filter.filterAnd(database.FinalDataBase, Filter.filter.equalId(WhatShouldIdo[2],WhatShouldIdo[3]),Filter.filter.equalAltLon(WhatShouldIdo[5], WhatShouldIdo[6],WhatShouldIdo[7],WhatShouldIdo[8],WhatShouldIdo[9])));
			}
			else if (WhatShouldIdo[1].equals("Date") && WhatShouldIdo[5].equals("ID"))
			{
				database.FinalFilterDatabase.addAll(Filter.filter.filterAnd(database.FinalDataBase, Filter.filter.equalTime(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4]), Filter.filter.equalId(WhatShouldIdo[5], WhatShouldIdo[6])));
			}
			else if (WhatShouldIdo[1].equals("Date") && WhatShouldIdo[5].equals("Location"))
			{
				database.FinalFilterDatabase.addAll(Filter.filter.filterAnd(database.FinalDataBase, Filter.filter.equalTime(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4]), Filter.filter.equalAltLon(WhatShouldIdo[6], WhatShouldIdo[7],WhatShouldIdo[8],WhatShouldIdo[9],WhatShouldIdo[10])));
			}
			else if ((WhatShouldIdo[1].equals("Location") && WhatShouldIdo[7].equals("ID")))
			{
				database.FinalFilterDatabase.addAll(Filter.filter.filterAnd(database.FinalDataBase,  Filter.filter.equalAltLon(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4],WhatShouldIdo[5],WhatShouldIdo[6]),Filter.filter.equalId(WhatShouldIdo[8],WhatShouldIdo[9])));
			}
			else if ((WhatShouldIdo[1].equals("Location") && WhatShouldIdo[7].equals("Date")))
			{
				database.FinalFilterDatabase.addAll(Filter.filter.filterAnd(database.FinalDataBase,  Filter.filter.equalAltLon(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4],WhatShouldIdo[5],WhatShouldIdo[6]), Filter.filter.equalTime(WhatShouldIdo[8], WhatShouldIdo[9],WhatShouldIdo[10])));
			}
		case "or":
			if (WhatShouldIdo[1].equals("ID") && WhatShouldIdo[4].equals("Date"))
			{
				database.FinalFilterDatabase.addAll(Filter.filter.filters(database.FinalDataBase, Filter.filter.equalId(WhatShouldIdo[2],WhatShouldIdo[3])));			
				database.FinalFilterDatabase.addAll(Filter.filter.filters(database.FinalDataBase,  Filter.filter.equalTime(WhatShouldIdo[5], WhatShouldIdo[6],WhatShouldIdo[7])));				
			}
			else if (WhatShouldIdo[1].equals("ID")&&WhatShouldIdo[4].equals("Location"))
			{
				database.FinalFilterDatabase.addAll(Filter.filter.filters(database.FinalDataBase, Filter.filter.equalId(WhatShouldIdo[2],WhatShouldIdo[3])));			
				database.FinalFilterDatabase.addAll(Filter.filter.filters(database.FinalDataBase,  Filter.filter.equalAltLon(WhatShouldIdo[5], WhatShouldIdo[6],WhatShouldIdo[7],WhatShouldIdo[8],WhatShouldIdo[9])));				
			}
			else if ((WhatShouldIdo[1].equals("Date") && WhatShouldIdo[5].equals("ID")))
			{
				database.FinalFilterDatabase.addAll(Filter.filter.filters(database.FinalDataBase, Filter.filter.equalId(WhatShouldIdo[6],WhatShouldIdo[7])));			
				database.FinalFilterDatabase.addAll(Filter.filter.filters(database.FinalDataBase,  Filter.filter.equalTime(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4])));	
			}
			else if (WhatShouldIdo[1].equals("Date") &&WhatShouldIdo[5].equals("Location"))
			{
				database.FinalFilterDatabase.addAll(Filter.filter.filters(database.FinalDataBase,  Filter.filter.equalAltLon(WhatShouldIdo[6], WhatShouldIdo[7],WhatShouldIdo[8],WhatShouldIdo[9], WhatShouldIdo[10])));				
				database.FinalFilterDatabase.addAll(Filter.filter.filters(database.FinalDataBase,  Filter.filter.equalTime(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4])));
			}
			else if ((WhatShouldIdo[1].equals("Location") && WhatShouldIdo[7].equals("ID")))
			{
				database.FinalFilterDatabase.addAll(Filter.filter.filters(database.FinalDataBase, Filter.filter.equalId(WhatShouldIdo[8],WhatShouldIdo[9])));			
				database.FinalFilterDatabase.addAll(Filter.filter.filters(database.FinalDataBase,  Filter.filter.equalAltLon(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4],WhatShouldIdo[5],WhatShouldIdo[6])));				
			}
			else if ((WhatShouldIdo[1].equals("Location") && WhatShouldIdo[7].equals("Date")))
			{
				database.FinalFilterDatabase.addAll(Filter.filter.filters(database.FinalDataBase,  Filter.filter.equalAltLon(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4],WhatShouldIdo[5],WhatShouldIdo[6])));				
				database.FinalFilterDatabase.addAll(Filter.filter.filters(database.FinalDataBase,  Filter.filter.equalTime(WhatShouldIdo[8], WhatShouldIdo[9],WhatShouldIdo[10])));		
			}
		case "one":
		{
			if (WhatShouldIdo[1].equals("ID"))
			{
				database.FinalFilterDatabase.addAll(Filter.filter.filters(database.FinalDataBase, Filter.filter.equalId(WhatShouldIdo[2],WhatShouldIdo[3])));
				System.out.println(database.FinalFilterDatabase.size());
			}
			else if(WhatShouldIdo[1].equals("Date"))
			{
				database.FinalFilterDatabase.addAll(Filter.filter.filters(database.FinalDataBase,  Filter.filter.equalTime(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4])));	
			}
			else if (WhatShouldIdo[1].equals("Location"))
			{
				database.FinalFilterDatabase.addAll(Filter.filter.filters(database.FinalDataBase,  Filter.filter.equalAltLon(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4],WhatShouldIdo[5],WhatShouldIdo[6])));				
			}
		}
		case "none":
		{
			database.FinalFilterDatabase.addAll(database.FinalFilterDatabase);
		}

		}
	}
}








