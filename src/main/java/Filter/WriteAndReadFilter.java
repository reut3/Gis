package Filter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import DataBase.DataBase;

public class WriteAndReadFilter {

	/**
	 * The function create text file and write filter's details that received as String to it
	 * @param fileName- the name of the text file the function creates
	 * @param s- The string that contains filter's details
	 */
	public static void writefilter(String fileName,String s){
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw=new FileWriter("testFile\\filtertestfile\\"+fileName+".txt");
			bw=new BufferedWriter(fw);
			bw.write(s);
		}
		catch(IOException e){
			System.out.println("error while write filter");
		}
		finally {
			try {
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {
				System.out.println("failed in close reader tools");
			}

		}    
	}

	/**
	 * The function read from text file details of a filter and store the details in a String 
	 * @param path - of the reading text file
	 * @param database
	 * @return 
	 */
	public static String Readfilter(String path,DataBase database){
		FileReader in=null;
		BufferedReader br=null;
		String line="";String line1="";
		try {
			in=new FileReader(path);
			br=new BufferedReader(in);

			if((line=br.readLine())!=null){
				line1=line;
				CheckFilter.WhichOP(line,database);
			}
		}
		catch(IOException ex){
			System.out.println("not succed to read the file");
		}
		finally {
			try {
				in.close();
				br.close();
			}
			catch(IOException ex)
			{
				System.out.println("failed in close reader tools");
			}

		}
		return line1;

	}


}