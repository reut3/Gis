package Filter;

import java.util.Set;
import DataBase.DataBase;
import DataBase.Sample;

/**
 * 
 * CheckFilter class- contain two functions: WhichOP, filterString
 * the class builded in order to help parsing information the Server gets.
 */
public class CheckFilter {

	/**
	 * The function gets String contains filter's information, 
	 * The functions execute the filter on the object DataBase
	 * @param s- string with filter information
	 * @param database
	 */
	public static void WhichOP(String s,DataBase database)
	{

		String[] WhatShouldIdo=s.split(",");
		switch(WhatShouldIdo[0])
		{
		case "and":
			if (WhatShouldIdo[1].equals("ID") && WhatShouldIdo[4].equals("Date"))
			{
				database.FinalFilterDatabase=Filter.filter.filterAnd(database.FinalFilterDatabase, Filter.filter.equalId(WhatShouldIdo[2],WhatShouldIdo[3]), Filter.filter.equalTime(WhatShouldIdo[5], WhatShouldIdo[6],WhatShouldIdo[7]));				
			}
			else if (WhatShouldIdo[1].equals("ID") && WhatShouldIdo[4].equals("Location"))
			{
				database.FinalFilterDatabase=Filter.filter.filterAnd(database.FinalDataBase, Filter.filter.equalId(WhatShouldIdo[2],WhatShouldIdo[3]),Filter.filter.equalAltLon(WhatShouldIdo[5], WhatShouldIdo[6],WhatShouldIdo[7],WhatShouldIdo[8],WhatShouldIdo[9]));
			}
			else if (WhatShouldIdo[1].equals("Date") && WhatShouldIdo[5].equals("ID"))
			{
				database.FinalFilterDatabase=Filter.filter.filterAnd(database.FinalDataBase, Filter.filter.equalTime(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4]), Filter.filter.equalId(WhatShouldIdo[5], WhatShouldIdo[6]));
			}
			else if (WhatShouldIdo[1].equals("Date") && WhatShouldIdo[5].equals("Location"))
			{
				database.FinalFilterDatabase=Filter.filter.filterAnd(database.FinalDataBase, Filter.filter.equalTime(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4]), Filter.filter.equalAltLon(WhatShouldIdo[6], WhatShouldIdo[7],WhatShouldIdo[8],WhatShouldIdo[9],WhatShouldIdo[10]));
			}
			else if ((WhatShouldIdo[1].equals("Location") && WhatShouldIdo[7].equals("ID")))
			{
				database.FinalFilterDatabase=Filter.filter.filterAnd(database.FinalDataBase,  Filter.filter.equalAltLon(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4],WhatShouldIdo[5],WhatShouldIdo[6]),Filter.filter.equalId(WhatShouldIdo[8],WhatShouldIdo[9]));
			}
			else if ((WhatShouldIdo[1].equals("Location") && WhatShouldIdo[7].equals("Date")))
			{
				database.FinalFilterDatabase=Filter.filter.filterAnd(database.FinalDataBase,  Filter.filter.equalAltLon(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4],WhatShouldIdo[5],WhatShouldIdo[6]), Filter.filter.equalTime(WhatShouldIdo[8], WhatShouldIdo[9],WhatShouldIdo[10]));
			}
		case "or":
			Set<Sample> temp= database.FinalFilterDatabase;

			if (WhatShouldIdo[1].equals("ID") && WhatShouldIdo[4].equals("Date"))
			{
				database.FinalFilterDatabase=Filter.filter.filters(temp, Filter.filter.equalId(WhatShouldIdo[2],WhatShouldIdo[3]));			
				database.FinalFilterDatabase.addAll(Filter.filter.filters(temp,  Filter.filter.equalTime(WhatShouldIdo[5], WhatShouldIdo[6],WhatShouldIdo[7])));				
			}
			else if (WhatShouldIdo[1].equals("ID")&&WhatShouldIdo[4].equals("Location"))
			{
				database.FinalFilterDatabase=Filter.filter.filters(temp, Filter.filter.equalId(WhatShouldIdo[2],WhatShouldIdo[3]));			
				database.FinalFilterDatabase.addAll(Filter.filter.filters(temp,  Filter.filter.equalAltLon(WhatShouldIdo[5], WhatShouldIdo[6],WhatShouldIdo[7],WhatShouldIdo[8],WhatShouldIdo[9])));				
			}
			else if ((WhatShouldIdo[1].equals("Date") && WhatShouldIdo[5].equals("ID")))
			{
				database.FinalFilterDatabase=Filter.filter.filters(temp, Filter.filter.equalId(WhatShouldIdo[6],WhatShouldIdo[7]));			
				database.FinalFilterDatabase.addAll(Filter.filter.filters(temp,  Filter.filter.equalTime(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4])));	
			}
			else if (WhatShouldIdo[1].equals("Date") &&WhatShouldIdo[5].equals("Location"))
			{
				database.FinalFilterDatabase=Filter.filter.filters(temp,  Filter.filter.equalAltLon(WhatShouldIdo[6], WhatShouldIdo[7],WhatShouldIdo[8],WhatShouldIdo[9], WhatShouldIdo[10]));				
				database.FinalFilterDatabase.addAll(Filter.filter.filters(temp,  Filter.filter.equalTime(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4])));
			}
			else if ((WhatShouldIdo[1].equals("Location") && WhatShouldIdo[7].equals("ID")))
			{
				database.FinalFilterDatabase=Filter.filter.filters(temp, Filter.filter.equalId(WhatShouldIdo[8],WhatShouldIdo[9]));			
				database.FinalFilterDatabase.addAll(Filter.filter.filters(temp,  Filter.filter.equalAltLon(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4],WhatShouldIdo[5],WhatShouldIdo[6])));				
			}
			else if ((WhatShouldIdo[1].equals("Location") && WhatShouldIdo[7].equals("Date")))
			{
				database.FinalFilterDatabase=Filter.filter.filters(temp,  Filter.filter.equalAltLon(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4],WhatShouldIdo[5],WhatShouldIdo[6]));				
				database.FinalFilterDatabase.addAll(Filter.filter.filters(temp,  Filter.filter.equalTime(WhatShouldIdo[8], WhatShouldIdo[9],WhatShouldIdo[10])));		
			}
		case "one":
		{
			if (WhatShouldIdo[1].equals("ID"))
			{
				database.FinalFilterDatabase= Filter.filter.filters(database.FinalFilterDatabase, Filter.filter.equalId(WhatShouldIdo[2],WhatShouldIdo[3]));
			}
			else if(WhatShouldIdo[1].equals("Date"))
			{
				database.FinalFilterDatabase=Filter.filter.filters(database.FinalFilterDatabase,  Filter.filter.equalTime(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4]));	
			}
			else if (WhatShouldIdo[1].equals("Location"))
			{
				database.FinalFilterDatabase=Filter.filter.filters(database.FinalFilterDatabase,  Filter.filter.equalAltLon(WhatShouldIdo[2], WhatShouldIdo[3],WhatShouldIdo[4],WhatShouldIdo[5],WhatShouldIdo[6]));				
			}
		}
		case "none":
		{
			return;
		}

		}
	}


	/**
	 * The function get string that contain filter's details, rearrange the details in order to send it to the client
	 * @param input -filter details
	 * @return String with rearranged filter's details.
	 */
	public static String filterString(String input){
		String[] words=input.split(",");
		String output="";
		int index=0;
		if(words[1].equals("ID")){
			index= 4;
			if(words[2].equals("0")){
				output+= "with the ID "+words[3];
			}
			else{
				output+= "Not with the ID "+words[3];
			}
		}
		else if(words[1].equals("Date")){
			index= 5;
			if(words[2].equals("0")){
				output+= "Dates between "+words[3]+" to "+words[4];
			}
			else{
				output+= "Not between the Dates  "+words[3]+" to "+words[4];
			}
		}
		else if(words[1].equals("Location")){
			index= 7;
			if(words[2].equals("0")){
				output+= "Location between "+words[3]+" "+words[4]+" to "+words[5]+" "+words[6];
			}
			else{
				output+= "Not between the location "+words[3]+" "+words[4]+" to "+words[5]+" "+words[6];
			}
		}
		if(words[0].equals("and")){
			output+= " and ";
		}
		else if(words[0].equals("or")){
			output+= " or ";
		}
		if(words[index]!=null){

			if(words[index].equals("ID")){
				if(words[index+1].equals("0")){
					output+= "with the ID "+words[index+2];
				}
				else{
					output+= "Not with the ID "+words[index+2];
				}
			}
			else if(words[index].equals("Date")){
				if(words[index+1].equals("0")){
					output+= "Dates between "+words[index+2]+" to "+words[index+3];
				}
				else{
					output+= "Not between the Dates  "+words[index+2]+" to "+words[index+3];
				}
			}
			else if(words[index].equals("Location")){
				if(words[index+1].equals("0")){
					output+= "Location between "+words[index+2]+" "+words[index+3]+" to "+words[index+4]+" "+words[index+5];
				}
				else{
					output+= "Not between the location "+words[index+2]+" "+words[index+3]+" to "+words[index+4]+" "+words[index+5];
				}
			}

		}
		return output;

	}



}

