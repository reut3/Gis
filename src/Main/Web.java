package Main;
import java.awt.Desktop;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;

import DataBase.DataBase;
import DataBase.MacSignal;
import DataBase.Sample;
import FileTools.CsvFile;
import FileTools.WatchDataBase;
import Location.Weight;
import Filter.CheckFilter;
import Filter.WriteAndReadFilter;;


public class Web {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		int port = 8888;
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
		DataBase database=new DataBase();
		ArrayList<String> paths= new ArrayList<String>();

		Desktop.getDesktop().browse(new URL("http://127.0.0.1:8888/file/web.html").toURI());
		
		




		//select a folder 
		server.createContext("/folder", request -> {
			String output = null;
			try {
				String input = request.getRequestURI().getQuery();
				System.out.println("The input is: "+input);
				Path path=Paths.get(input);
				if (Files.exists(path)) {
					CsvFile.readCSV(input,database);
					output="1";
					System.out.println("The folder has recived, the DataBase has updated");
					System.out.println();
					paths.add(input);//add to array list
					FileTools.WatchDataBase.watchForChanges(paths,database);
				}
				else {
					output = "The folder dosen't exist, please try again";
					System.out.println();
				}
			}
			catch (Throwable ex) {
				output = "The folder dosen't exist, please try again";
				System.out.println();
			}
			request.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			request.getResponseHeaders().set("Content-Type", "text/plain");
			request.sendResponseHeaders(200, 0);
			try (OutputStream os = request.getResponseBody()) {
				os.write(output.getBytes(StandardCharsets.UTF_8));
			} catch (Exception ex) {
				System.out.println("Cannot send response to client");
				ex.printStackTrace();
			}
		});





		server.createContext("/uploadFilter", request -> {
			String output = null;
			try {
				String input = request.getRequestURI().getQuery();
				System.out.println("The input is: "+input);
				Path path=Paths.get(input);
				if (Files.exists(path)) {
					String line=WriteAndReadFilter.Readfilter(input,database);
					database.startHash();
					output=CheckFilter.filterString(line);
					System.out.println("The filter file has recived, the filtered DataBase has changed");
					System.out.println();
				}
				else {
					output = "The file dosen't exist, please try again";
					System.out.println();
				}
			}
			catch (Throwable ex) {
				output = "The file dosen't exist, please try again";
				System.out.println();
			}
			System.out.println(output);
			request.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			request.getResponseHeaders().set("Content-Type", "text/plain");
			request.sendResponseHeaders(200, 0);
			try (OutputStream os = request.getResponseBody()) {
				os.write(output.getBytes(StandardCharsets.UTF_8));
			} catch (Exception ex) {
				System.out.println("Cannot send response to client");
				ex.printStackTrace();
			}
		});


		server.createContext("/filterFile", request -> {
			String output = null;
			try {
				String input = request.getRequestURI().getQuery();
				System.out.println("The input is: "+input);

				int index=0;
				while(input.charAt(index)!=','){
					index++;
				}
				String fileName= input.substring(0, index);
				String filter= input.substring(index+1);

				index++;
				int start=index;
				while(input.charAt(index)!=','){
					index++;
				}
				String ifNone= input.substring(start, index);
				if(ifNone.equals("none")){
					output="2";
				}
				else{
					WriteAndReadFilter.writefilter(fileName,filter);
					output="1";
				}

			}
			catch (Throwable ex) {
				output = "problem in writing the file, check if the file is not exist already";
			}
			request.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			request.getResponseHeaders().set("Content-Type", "text/plain");
			request.sendResponseHeaders(200, 0);
			try (OutputStream os = request.getResponseBody()) {
				os.write(output.getBytes(StandardCharsets.UTF_8));
			} catch (Exception ex) {
				System.out.println("Cannot send response to client");
				ex.printStackTrace();
			}
		});




		//upload CSV file in format of 46 columns
		server.createContext("/upload", request -> {
			String output = null;
			try {
				String input = request.getRequestURI().getQuery();
				System.out.println("The input is: "+input);
				Path path=Paths.get(input);
				if (Files.exists(path)) {

					List<Sample> temp= algos.readCSV(input);
					if(temp.size()==0){
						output="2";
					}
					else{
						Set<Sample> temp1= new HashSet<Sample>();
						temp1.addAll(temp);
						database.add(temp1);
						output="1";
						System.out.println("The file has recived, the DataBase has updated");
						System.out.println();
					}
				}
				else {
					output = "The file dosen't exist, please try again";
					System.out.println();
				}
			}
			catch (Throwable ex) {
				output = "The file dosen't exist, please try again";
				System.out.println();
			}
			System.out.println(output);
			request.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			request.getResponseHeaders().set("Content-Type", "text/plain");
			request.sendResponseHeaders(200, 0);
			try (OutputStream os = request.getResponseBody()) {
				os.write(output.getBytes(StandardCharsets.UTF_8));
			} catch (Exception ex) {
				System.out.println("Cannot send response to client");
				ex.printStackTrace();
			}
		});





		server.createContext("/delete", request -> {
			String output = null;
			try {
				if (database.FinalDataBase.size()!=0) {
					database.RemoveAll();
					paths.clear();
					output="1";
					System.out.println("deleted");
				}
				else {
					output = "The dataBase is already empty";
				}
			}
			catch (Throwable ex) {
				output = "The dataBase is already empty";
			}
			request.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			request.getResponseHeaders().set("Content-Type", "text/plain");
			request.sendResponseHeaders(200, 0);
			try (OutputStream os = request.getResponseBody()) {
				os.write(output.getBytes(StandardCharsets.UTF_8));
			} catch (Exception ex) {
				System.out.println("Cannot send response to client");
				ex.printStackTrace();
			}
		});




		server.createContext("/toCSV", request -> {
			String output = null;
			try {
				//if the database is not empty->save it to csv
				if (database.FinalFilterDatabase.size()!=0) {

					FileTools.CsvFile.writeCSV("finalfile", database.FinalFilterDatabase);
					output="1";
					System.out.println("The csv file has created successfully in your folder");
				}
				else {
					output = "The dataBase is empty, nothing to save";
				}
			}
			catch (Throwable ex) {
				output = "The file is open in your computer, please close it before saving";
			}
			System.out.println(output);
			request.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			request.getResponseHeaders().set("Content-Type", "text/plain");
			request.sendResponseHeaders(200, 0);
			try (OutputStream os = request.getResponseBody()) {
				os.write(output.getBytes(StandardCharsets.UTF_8));
			} catch (Exception ex) {
				System.out.println("Cannot send response to client");
				ex.printStackTrace();
			}
		});








		server.createContext("/reset", request -> {
			String output = null;
			try {
				//if the database is not empty
				if (database.FinalDataBase.size()!=0) {
					output="1";
					database.FinalFilterDatabase.addAll(database.FinalDataBase);
					database.startHash();
					System.out.println("The filtered DataBase is reset");
				}
				else {
					output = "The dataBase is empty";
				}
			}
			catch (Throwable ex) {
				output = "The dataBase is empty";
			}
			System.out.println(output);
			request.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			request.getResponseHeaders().set("Content-Type", "text/plain");
			request.sendResponseHeaders(200, 0);
			try (OutputStream os = request.getResponseBody()) {
				os.write(output.getBytes(StandardCharsets.UTF_8));
			} catch (Exception ex) {
				System.out.println("Cannot send response to client");
				ex.printStackTrace();
			}
		});







		server.createContext("/applyFilter", request -> {
			String output = null;
			try {
				String input = request.getRequestURI().getQuery();
				System.out.println("The input is: "+input);
				//if the database is not empty->save it to csv
				if (database.FinalFilterDatabase.size()!=0) {
					Filter.CheckFilter.WhichOP(input,database);
					database.startHash();

					output="";
				}
				else {
					output = "";
				}
			}
			catch (Throwable ex) {
				output = "";
			}
			System.out.println(output);
			request.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			request.getResponseHeaders().set("Content-Type", "text/plain");
			request.sendResponseHeaders(200, 0);
			try (OutputStream os = request.getResponseBody()) {
				os.write(output.getBytes(StandardCharsets.UTF_8));
			} catch (Exception ex) {
				System.out.println("Cannot send response to client");
				ex.printStackTrace();
			}
		});




		server.createContext("/lines", request -> {
			String output = "0";
			try {
				if (database.FinalDataBase.size()!=0) {
					output= database.FinalDataBase.size()+"";
				}
				else {
					output = "0";
				}
			}
			catch (Throwable ex) {
				output = "0";
			}
			request.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			request.getResponseHeaders().set("Content-Type", "text/plain");
			request.sendResponseHeaders(200, 0);
			try (OutputStream os = request.getResponseBody()) {
				os.write(output.getBytes(StandardCharsets.UTF_8));
			} catch (Exception ex) {
				System.out.println("Cannot send response to client");
				ex.printStackTrace();
			}
		});




		server.createContext("/Flines", request -> {
			String output = "0";

			try {
				if (database.FinalFilterDatabase.size()!=0) {
					output= database.FinalFilterDatabase.size()+"";
				}
				else {
					output = "0";
				}
			}
			catch (Throwable ex) {
				output = "0";
			}
			request.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			request.getResponseHeaders().set("Content-Type", "text/plain");
			request.sendResponseHeaders(200, 0);
			try (OutputStream os = request.getResponseBody()) {
				os.write(output.getBytes(StandardCharsets.UTF_8));
			} catch (Exception ex) {
				System.out.println("Cannot send response to client");
				ex.printStackTrace();
			}
		});


		server.createContext("/router", request -> {
			String output = "0";

			try {
				if (database.FinalFilterDatabase.size()!=0) {
					output= database.hashMap.size()+"";
				}
				else {
					output = "0";
				}
			}
			catch (Throwable ex) {
				output = "0";
			}
			request.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			request.getResponseHeaders().set("Content-Type", "text/plain");
			request.sendResponseHeaders(200, 0);
			try (OutputStream os = request.getResponseBody()) {
				os.write(output.getBytes(StandardCharsets.UTF_8));
			} catch (Exception ex) {
				System.out.println("Cannot send response to client");
				ex.printStackTrace();
			}
		});






		server.createContext("/toKML", request -> {
			String output = null;
			try {
				String input = request.getRequestURI().getQuery();
				System.out.println("The input is: "+input);
				//if the database is not empty->save it to kml
				if (database.FinalFilterDatabase.size()!=0) {
					Filter.CheckFilter.WhichOP(input,database);
					System.out.println("now"+database.FinalFilterDatabase.size());

					FileTools.KmlFile.write("kmlFile", database.FinalFilterDatabase);
					output="1";
					System.out.println("The KML file has created successfully in your folder");
				}
				else {
					output = "The dataBase is empty, nothing to save";
				}
			}
			catch (Throwable ex) {
				output = "The file is open in your computer, please close it before saving";
			}
			System.out.println(output);
			request.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			request.getResponseHeaders().set("Content-Type", "text/plain");
			request.sendResponseHeaders(200, 0);
			try (OutputStream os = request.getResponseBody()) {
				os.write(output.getBytes(StandardCharsets.UTF_8));
			} catch (Exception ex) {
				System.out.println("Cannot send response to client");
				ex.printStackTrace();
			}
		});

		server.createContext("/file", request -> {
			String output = null;

			try {
				String fileName = request.getRequestURI().getPath().replaceAll("/file/", "");
				System.out.println("Got new file-request: "+fileName);
				Path path = Paths.get("client", fileName);
				if (Files.exists(path)) {
					String contentType = (
							fileName.endsWith(".html")? "text/html":
								fileName.endsWith(".js")? "text/javascript":
									fileName.endsWith(".css")? "text/css":
										fileName.endsWith(".php")? "text/php":
										"text/plain"
							);
					request.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
					request.getResponseHeaders().set("Content-Type", contentType);
					request.sendResponseHeaders(200, 0);
					try (OutputStream os = request.getResponseBody()) {
						os.write(Files.readAllBytes(path));
					}
					return;
				} else {
					output = "File "+path+" not found!";
				}
			} catch (Throwable ex) {
				output = "Sorry, an error occured: "+ex;
			}
			System.out.println(output);
			request.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			request.getResponseHeaders().set("Content-Type", "text/plain");
			request.sendResponseHeaders(200, 0);
			try (OutputStream os = request.getResponseBody()) {
				os.write(output.getBytes(StandardCharsets.UTF_8));
			} catch (Exception ex) {
				System.out.println("Cannot send response to client");
				ex.printStackTrace();
			}
		});



		server.createContext("/algo1", request -> {
			String output = null;
			try {
				String input = request.getRequestURI().getQuery();
				System.out.println("The input is: "+input);
				//if the database is not empty->save it to csv

				if (database.FinalFilterDatabase.size()!=0) {
					ArrayList<Sample> temp= database.hashMap.get(input);
					Weight w= new Weight();
					Location.Location lock= w.findLocation1(temp, input);
					output=lock.toString();
				}
				else {
					output = "The dataBase is empty";
				}
			}
			catch (Throwable ex) {
				output = "Not found";
			}
			System.out.println(output);
			request.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			request.getResponseHeaders().set("Content-Type", "text/plain");
			request.sendResponseHeaders(200, 0);
			try (OutputStream os = request.getResponseBody()) {
				os.write(output.getBytes(StandardCharsets.UTF_8));
			} catch (Exception ex) {
				System.out.println("Cannot send response to client");
				ex.printStackTrace();
			}
		});






		server.createContext("/algo2", request -> {
			String output = null;
			try {
				String input = request.getRequestURI().getQuery();
				System.out.println("The input is: "+input);
				//if the database is not empty
				if (database.FinalFilterDatabase.size()!=0) {

					int index=0;
					while(input.charAt(index)!=','){
						index++;
					}
					int num= Integer.parseInt(input.substring(0, index));
					input= input.substring(index+1);
					System.out.println(input);
					ArrayList<MacSignal> macSignal= MacSignal.parsing(input);
					Set<Sample> sampleSet = new HashSet<Sample>();
					for(int i=0; i<macSignal.size();i++){
						String mac= macSignal.get(i).getMac();
						sampleSet.addAll(database.hashMap.get(mac));
					}
					Location.Location lock= Location.Weight.findLocation2(sampleSet, macSignal, num);

					output=lock.toString();
				}
				else {
					output = "The dataBase is empty";
				}
			}
			catch (Throwable ex) {
				output = "Not found";
			}
			System.out.println(output);
			request.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			request.getResponseHeaders().set("Content-Type", "text/plain");
			request.sendResponseHeaders(200, 0);
			try (OutputStream os = request.getResponseBody()) {
				os.write(output.getBytes(StandardCharsets.UTF_8));
			} catch (Exception ex) {
				System.out.println("Cannot send response to client");
				ex.printStackTrace();
			}
		});










		System.out.println("WebServer is up. "+
				"To enter the web, go to http://127.0.0.1:"+port+"/file/web.html");
		server.start();

	}

}  














