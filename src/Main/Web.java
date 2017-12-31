package Main;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import DataBase.DataBase;
import DataBase.Sample;
import FileTools.CsvFile;

import java.net.InetSocketAddress;


public class Web {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		int port = 8888;
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);



		//select a folder 
		server.createContext("/folder", request -> {
			String output = null;
			try {
				String input = request.getRequestURI().getQuery();
				System.out.println("The input is: "+input);
				Path path=Paths.get(input);
				if (Files.exists(path)) {
					CsvFile.readCSV(input);
					output="1";
					System.out.println("The folder has recived, the DataBase has updated");
					System.out.println();
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
				if (DataBase.FinalDataBase.size()!=0) {
					DataBase.RemoveAll();
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
				String input = request.getRequestURI().getQuery();
				System.out.println("The input is: "+input);
				//if the database is not empty->save it to csv
				if (DataBase.FinalDataBase.size()!=0) {
					Filter.CheckFilter.WhichOP(input);
					System.out.println("now"+DataBase.FinalFilterDatabase.size());

					FileTools.CsvFile.writeCSV("finalfile1", DataBase.FinalFilterDatabase);
					output="1";
					System.out.println("The csv file has created successfully in your folder");
				}
				else {
					output = "The dataBase is empty, nothing to save";
				}
			}
			catch (Throwable ex) {
				output = "The dataBase is empty, nothing to save";
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


		DataBase database=new DataBase();
		System.out.println("WebServer is up. "+
				"To enter the web, go to http://127.0.0.1:"+port+"/file/web.html");
		server.start();

	}

}
