package Filter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import DataBase.DataBase;

public class WriteAndReadFilter {


	public static void writefilter(String fileName,String s){
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw=new FileWriter("savedFilters\\"+fileName+".txt");
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