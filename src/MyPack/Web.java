package MyPack;
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

import java.net.InetSocketAddress;


public class Web {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		int port = 8888;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        
 
        
        //select a folder 
        server.createContext("/folder", request -> {
        	String input = request.getRequestURI().getQuery();
        	System.out.println("The input is: "+input);

    		CsvFile.readCSV(input);

    		String output="The csv file has created successfully in your folder";
        	System.out.println("   The output is: "+output);
        	

        	request.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        	request.getResponseHeaders().set("Content-Type", "text/plain");
            request.sendResponseHeaders(200 /* OK */, 0);
            try (OutputStream os = request.getResponseBody()) {
            	os.write(output.getBytes());
            } catch (Exception ex) {
            	System.out.println("Error while sending response to client");
            	ex.printStackTrace();
            }
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
		
        server.createContext("/file", request -> {
        	String fileName = request.getRequestURI().getPath().replaceAll("/file/", "");
        	System.out.println("Got new file-request: "+fileName);
        	Path path = Paths.get("client", fileName);
        	String output = null;
        	try {
        		output = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        	} catch (NoSuchFileException ex) {
        		output = "Dear user, the file '"+fileName+"' does not exist. I am sorry.";
        	} catch (Exception ex) {
        		output = "Error: "+ex;
        	}
        	
        	request.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        	request.getResponseHeaders().set("Content-Type", "text/html");
            request.sendResponseHeaders(200, 0);
            try (OutputStream os = request.getResponseBody()) {
            	os.write(output.getBytes());
            }
        });
                	
        	
        System.out.println("WebServer is up. "+
        		"To enter the web, go to http://127.0.0.1:"+port+"/file/web.html");
        server.start();
		
	}

}
