package Filter;

import java.io.Serializable;
import java.util.function.Predicate;

import DataBase.Sample;

public class CheckFilter {




	public void WhichOP(String s)
	{

		String[] WhatShouldIdo=s.split(",");
		switch(WhatShouldIdo[0])
		{
		case "And":
			if (WhatShouldIdo[1]=="Id" && WhatShouldIdo[4]=="Date")
			{
				DataBase.DataBase.FinalFilterDatabase=Filter.filter.filterAnd(DataBase.DataBase.FinalDataBase, Filter.filter.equalId(WhatShouldIdo[2],WhatShouldIdo[3]), Filter.filter.equalTime(WhatShouldIdo[5], WhatShouldIdo[6],WhatShouldIdo[7]));				
			}
			else if ((WhatShouldIdo[1]=="Id" && WhatShouldIdo[4]=="Location"))
			{
				DataBase.DataBase.FinalFilterDatabase=Filter.filter.filterAnd(DataBase.DataBase.FinalDataBase, Filter.filter.equalId(WhatShouldIdo[2],WhatShouldIdo[3]),Filter.filter.equalAltLon(WhatShouldIdo[5], WhatShouldIdo[6],WhatShouldIdo[7],WhatShouldIdo[8],WhatShouldIdo[9]));
			}
			else if ((WhatShouldIdo[1]=="Date" && WhatShouldIdo[5]=="Id"))
			{
				DataBase.DataBase.FinalFilterDatabase=Filter.filter.filterAnd(DataBase.DataBase.FinalDataBase, Filter.filter.equalTime(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4]), Filter.filter.equalId(WhatShouldIdo[5], WhatShouldIdo[6]));
			}
			else if ((WhatShouldIdo[1]=="Date" && WhatShouldIdo[5]=="Location"))
			{
				DataBase.DataBase.FinalFilterDatabase=Filter.filter.filterAnd(DataBase.DataBase.FinalDataBase, Filter.filter.equalTime(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4]), Filter.filter.equalAltLon(WhatShouldIdo[6], WhatShouldIdo[7],WhatShouldIdo[8],WhatShouldIdo[9],WhatShouldIdo[10]));
			}
			else if ((WhatShouldIdo[1]=="Location" && WhatShouldIdo[7]=="Id"))
			{
				DataBase.DataBase.FinalFilterDatabase=Filter.filter.filterAnd(DataBase.DataBase.FinalDataBase,  Filter.filter.equalAltLon(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4],WhatShouldIdo[5],WhatShouldIdo[6]),Filter.filter.equalId(WhatShouldIdo[8],WhatShouldIdo[9]));
			}
			else if ((WhatShouldIdo[1]=="Location" && WhatShouldIdo[7]=="Date"))
			{
				DataBase.DataBase.FinalFilterDatabase=Filter.filter.filterAnd(DataBase.DataBase.FinalDataBase,  Filter.filter.equalAltLon(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4],WhatShouldIdo[5],WhatShouldIdo[6]), Filter.filter.equalTime(WhatShouldIdo[8], WhatShouldIdo[9],WhatShouldIdo[10]));
			}
		case "Or":
			if (WhatShouldIdo[1]=="Id"&&WhatShouldIdo[4]=="Date")
			{
				DataBase.DataBase.FinalFilterDatabase.addAll(Filter.filter.filters(DataBase.DataBase.FinalDataBase, Filter.filter.equalId(WhatShouldIdo[2],WhatShouldIdo[3])));			
				DataBase.DataBase.FinalFilterDatabase.addAll(Filter.filter.filters(DataBase.DataBase.FinalDataBase,  Filter.filter.equalTime(WhatShouldIdo[5], WhatShouldIdo[6],WhatShouldIdo[7])));				
			}
			else if (WhatShouldIdo[1]=="Id"&&WhatShouldIdo[4]=="Location")
			{
				DataBase.DataBase.FinalFilterDatabase.addAll(Filter.filter.filters(DataBase.DataBase.FinalDataBase, Filter.filter.equalId(WhatShouldIdo[2],WhatShouldIdo[3])));			
				DataBase.DataBase.FinalFilterDatabase.addAll(Filter.filter.filters(DataBase.DataBase.FinalDataBase,  Filter.filter.equalAltLon(WhatShouldIdo[5], WhatShouldIdo[6],WhatShouldIdo[7],WhatShouldIdo[8],WhatShouldIdo[9])));				
			}
			else if ((WhatShouldIdo[1]=="Date"&&WhatShouldIdo[5]=="Id"))
			{
				DataBase.DataBase.FinalFilterDatabase.addAll(Filter.filter.filters(DataBase.DataBase.FinalDataBase, Filter.filter.equalId(WhatShouldIdo[6],WhatShouldIdo[7])));			
				DataBase.DataBase.FinalFilterDatabase.addAll(Filter.filter.filters(DataBase.DataBase.FinalDataBase,  Filter.filter.equalTime(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4])));	
			}
			else if ((WhatShouldIdo[1]=="Date"&&WhatShouldIdo[5]=="Location"))
			{
				DataBase.DataBase.FinalFilterDatabase.addAll(Filter.filter.filters(DataBase.DataBase.FinalDataBase,  Filter.filter.equalAltLon(WhatShouldIdo[6], WhatShouldIdo[7],WhatShouldIdo[8],WhatShouldIdo[9], WhatShouldIdo[10])));				
				DataBase.DataBase.FinalFilterDatabase.addAll(Filter.filter.filters(DataBase.DataBase.FinalDataBase,  Filter.filter.equalTime(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4])));
			}
			else if ((WhatShouldIdo[1]=="Location"&&WhatShouldIdo[7]=="Id"))
			{
				DataBase.DataBase.FinalFilterDatabase.addAll(Filter.filter.filters(DataBase.DataBase.FinalDataBase, Filter.filter.equalId(WhatShouldIdo[8],WhatShouldIdo[9])));			
				DataBase.DataBase.FinalFilterDatabase.addAll(Filter.filter.filters(DataBase.DataBase.FinalDataBase,  Filter.filter.equalAltLon(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4],WhatShouldIdo[5],WhatShouldIdo[6])));				
			}
			else if ((WhatShouldIdo[1]=="Location"&&WhatShouldIdo[7]=="Date"))
			{
				DataBase.DataBase.FinalFilterDatabase.addAll(Filter.filter.filters(DataBase.DataBase.FinalDataBase,  Filter.filter.equalAltLon(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4],WhatShouldIdo[5],WhatShouldIdo[6])));				
				DataBase.DataBase.FinalFilterDatabase.addAll(Filter.filter.filters(DataBase.DataBase.FinalDataBase,  Filter.filter.equalTime(WhatShouldIdo[8], WhatShouldIdo[9],WhatShouldIdo[10])));		
			}
		case "OnlyOne":
		{
			if (WhatShouldIdo[1]=="Id")
			{
				DataBase.DataBase.FinalFilterDatabase.addAll(Filter.filter.filters(DataBase.DataBase.FinalDataBase, Filter.filter.equalId(WhatShouldIdo[2],WhatShouldIdo[3])));			
			}
			else if (WhatShouldIdo[1]=="Date")
			{
				DataBase.DataBase.FinalFilterDatabase.addAll(Filter.filter.filters(DataBase.DataBase.FinalDataBase,  Filter.filter.equalTime(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4])));	
			}
			else if (WhatShouldIdo[1]=="Location")
			{
				DataBase.DataBase.FinalFilterDatabase.addAll(Filter.filter.filters(DataBase.DataBase.FinalDataBase,  Filter.filter.equalAltLon(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4],WhatShouldIdo[5],WhatShouldIdo[6])));				
			}

		}

		}
	}
}








